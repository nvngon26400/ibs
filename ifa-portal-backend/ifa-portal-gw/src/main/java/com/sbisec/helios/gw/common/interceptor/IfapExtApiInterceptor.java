package com.sbisec.helios.gw.common.interceptor;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.cache.CacheManager;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.ServletUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.ExtApiHelper;
import com.sbisec.helios.gw.common.util.ExtApiHelper.ExtApiAuthHttpHeader;

/**
 * 
 * @author SCSK
 *
 * 外部APIの認可チェック用インターセプター
 */
public class IfapExtApiInterceptor implements HandlerInterceptor {
    
    private static final String RETURN_CODE = "E92";

	/** 外部API向けヘルパー */
	private ExtApiHelper extApiHelper = null;

	/**
	 * コンストラクタ。
	 * 
	 * @param cacheManager キャッシュマネージャー。
	 */
	public IfapExtApiInterceptor(CacheManager cacheManager) {
		this.extApiHelper = new ExtApiHelper(cacheManager);
	}
	
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        
        // 外部API向け認証HTTPヘッダ情報を取得
        ExtApiAuthHttpHeader extApiAuthHttpHeader = extApiHelper.getAuthHttpHeader(httpServletRequest);

        // 外部API呼び出しではない場合、チェックをスルー
        if (!extApiHelper.isRequestedExtApi(extApiAuthHttpHeader)) {
            return true;
        }
        
        // エラーメッセージの設定
        String errorMessage = IfaCommonUtil.getMessage(BaseController.ERRORS_EXTAPI_ACCESS_DENIED,
                new String[] { extApiAuthHttpHeader.getSystemId(), request.getServletPath() });
        
        try {
            // 該当システムIDのホワイトリストを取得
            List<String> whiteList = extApiHelper.getWhiteList(extApiAuthHttpHeader);
            
            // ホワイトリストが取得できない場合はエラー
            if (Objects.isNull(whiteList)) {
                ServletUtil.instance(httpServletResponse).errorLevel(ErrorLevel.SYSTEM_ERROR).returnCd(RETURN_CODE)
                        .message(errorMessage).print();
                return false;
            }
            
            // 呼び出したコントローラのURLがホワイトリストに存在するかチェック
            if (whiteList.contains(request.getServletPath())) {
                // 存在した場合はチェックOK。リクエストスコープの外部API認可フラグを設定する。
                httpServletRequest.setAttribute(IfaCommonUtil.ATTR_EXT_API_PERMISSION_FLAG, "true");
                return true;
            } else {
                // 存在しない場合はエラー
                ServletUtil.instance(httpServletResponse).errorLevel(ErrorLevel.SYSTEM_ERROR).returnCd(RETURN_CODE)
                        .message(errorMessage).print();
                return false;
            }
            
        } catch (Exception e) {
            // 全ての想定外エラーはシステムエラー
            ServletUtil.instance(httpServletResponse).errorLevel(ErrorLevel.SYSTEM_ERROR).returnCd("errors.systemError")
                    .print();
            
            return false;
        }
    }
}
