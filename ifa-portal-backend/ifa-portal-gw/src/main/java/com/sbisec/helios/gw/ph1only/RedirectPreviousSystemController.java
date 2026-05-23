package com.sbisec.helios.gw.ph1only;

import java.text.MessageFormat;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbisec.helios.ap.common.constants.ServiceNameConstants;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

/**
 * 現行システムへのリダイレクトを行うコントローラ
 *
 * @author 河口
 *
 */
@RestController
@ScreenId(groupId = "SUB99-01", id = "SUB99-99", screenNumber = "")
public class RedirectPreviousSystemController {
    
    /** レスポンス文字列テンプレート */
    private static final String RESPONSE_TEXT_TMPL = "<form id=\"form\" method=\"post\" action=\"{0}\">"
            + "<input name=\"userId\" value=\"{1}\" type=\"hidden\" />"
            + "<input name=\"encryptPassword\" value=\"{2}\" type=\"hidden\" />"
            + "<input name=\"redirectTo\" value=\"{3}\" type=\"hidden\" />"
            + "</form>";
    
    /** SSOログインのエントリポイント */
    @Value("${url.horus.pl}")
    private String urlHorusPl;
    
    /**
     * 通常ログイン認証
     *
     * @param map 入力項目
     * @return 結果文字列
     * @throws Exception 例外
     */
    @PostMapping("/common/redirectPreviousSystem")
    public String login(@RequestBody Map<String, String> map) throws Exception {
        
        // APサーバのユーザ情報取得を呼び出し
        final UserAccount userAccount = ApiRequestUtil.invoke(ServiceNameConstants.USER_ADMINISTRATION_SERVICE,
                "getUserAccount", new TypeReference<UserAccount>() {
                }, IfaCommonUtil.getUserAccount().getUserId(), null);
        
        // レスポンス文字列を成形
        String responseText = MessageFormat.format(RESPONSE_TEXT_TMPL, urlHorusPl + "/ssoLogin",
                userAccount.getUserId(), userAccount.getPassword(), map.get("redirectTo"));
        
        return responseText;
    }
}
