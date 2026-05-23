package com.sbisec.helios.ap.systemManageMenu.loginUserManage.service.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.IfaLoginUserManageManagerLookupDao;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginUserManageManagerLookupSql001RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginUserManageManagerLookupSql001ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginUserManageManagerLookupSql002RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginUserManageManagerLookupSql002ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginUserManageManagerLookupSql003RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginUserManageManagerLookupSql004RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginUserManageManagerLookupSql005RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginUserManageManagerLookupSql006RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginUserManageManagerLookupSql007RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginUserManageManagerLookupSql007ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginUserManageManagerLookupA001RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginUserManageManagerLookupA001ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginUserManageManagerLookupA001ResponseDto_MenuList;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginUserManageManagerLookupA002RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginUserManageManagerLookupA002ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginUserManageManagerLookupA002ResponseDto_LoginIdManageList;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginUserManageManagerLookupA007RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginUserManageManagerLookupA007ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.service.IfaLoginUserManageManagerLookupService;

/**
 * 画面ID：SUB0601_01-01
 * 画面名：ログイン者管理（管理者用）照会
 * @author 布施佑太
 *
 * 2023/11/02 新規作成
 */
@Component(value = "cmpIfaLoginUserManageManagerLookupService")
public class IfaLoginUserManageManagerLookupServiceImpL implements IfaLoginUserManageManagerLookupService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaLoginUserManageManagerLookupServiceImpL.class);
    
    /** 利用者権限　SBI証券本店 */
    private static final String USER_PERMISSION_SBI = "1";
    
    /** 検索結果0件 */
    private static final int SEARCH_RESULT_0 = 0;
    
    /** 検索結果1件 */
    private static final int SEARCH_RESULT_1 = 1;
    
    /**　取得パスワード　空文字設定 */
    private static final String PASSWORD_EMPTY = "";
    
    /** リクエスト.担当数　1 */
    private static final int REQUEST_MANAGERCOUNT_1 = 1;
    
    /** リクエスト.担当数　2以上 */
    private static final int REQUEST_MANAGERCOUNT_2 = 2;
    
    /** 削除処理結果 正常 */
    private static final int DELETE_RESULT_OK = 1;
    
    /** アクセス権限がありません。 */
    private static final String ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE = "errors.cmn.loginUsers.insufficientPrivilege";
    
    /** 検索結果が0件です。条件を設定して再度検索して下さい。 */
    private static final String ERRORS_DATALIST_NOTFOUND = "errors.dataList.notfound";
    
    /** 担当者情報が他に登録されているため削除できません。 */
    private static final String ERRORS_EMP_LOGINUSERS_REGISTERED = "errors.emp.loginUsers.registered";
    
    /** {0}を削除しました。 */
    private static final String INFO_DELETECOMPLETED = "info.deleteCompleted";
    
    /**　ユーザー情報 */
    private static final String ERROR_MSG_USER_INFO = "ユーザー情報";
    
    @Autowired
    private IfaLoginUserManageManagerLookupDao dao;
    
    /**
     * アクションID：A001
     * アクション名：初期表示
     * Dto リクエスト：IfaLoginUserManageManagerLookupA001RequestDto
     * Dto レスポンス：IfaLoginUserManageManagerLookupA001ResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginUserManageManagerLookupA001ResponseDto> initialDisplayA001(
            IfaLoginUserManageManagerLookupA001RequestDto dtoReq) throws Exception {
        
        DataList<IfaLoginUserManageManagerLookupA001ResponseDto> dtoRes = new DataList<IfaLoginUserManageManagerLookupA001ResponseDto>();
        List<IfaLoginUserManageManagerLookupA001ResponseDto> resDto = new ArrayList<IfaLoginUserManageManagerLookupA001ResponseDto>();
        IfaLoginUserManageManagerLookupA001ResponseDto response = new IfaLoginUserManageManagerLookupA001ResponseDto();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaLoginUserManageManagerLookupServiceImplL.initialDisplayA001");
        
        //利用者権限チェック
        if (!checkPrivId()) {
            
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE));
        }
        
        //メニューリストの取得
        //SQL007
        DataList<IfaLoginUserManageManagerLookupSql007ResponseModel> selSql007Res = new DataList<IfaLoginUserManageManagerLookupSql007ResponseModel>();
        selSql007Res = callSQL007();
        
        response.setMenuList(getMenuList(selSql007Res));
        resDto.add(response);
        dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
        
        return dtoRes;
    }
    
    /**
     * アクションID：A002
     * アクション名：検索
     * Dto リクエスト：IfaLoginUserManageManagerLookupA002RequestDto
     * Dto レスポンス：IfaLoginUserManageManagerLookupA002ResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginUserManageManagerLookupA002ResponseDto> searchA002(
            IfaLoginUserManageManagerLookupA002RequestDto dtoReq) throws Exception {
        
        DataList<IfaLoginUserManageManagerLookupA002ResponseDto> dtoRes = new DataList<IfaLoginUserManageManagerLookupA002ResponseDto>();
        List<IfaLoginUserManageManagerLookupA002ResponseDto> resDto = new ArrayList<IfaLoginUserManageManagerLookupA002ResponseDto>();
        IfaLoginUserManageManagerLookupA002ResponseDto response = new IfaLoginUserManageManagerLookupA002ResponseDto();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaLoginUserManageManagerLookupServiceImplL.searchA002");
        
        //利用者権限チェック
        if (!checkPrivId()) {
            
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE));
        }
        
        //検索条件設定
        String userId = dtoReq.getLoginId();
        String branchNameBrokerName = dtoReq.getBranchNameBrokerName();
        String employeeNameChargeName = dtoReq.getEmployeeNameChargeName();
        
        //ユーザ情報検索
        //SQL001
        DataList<IfaLoginUserManageManagerLookupSql001ResponseModel> selSql001Res = new DataList<IfaLoginUserManageManagerLookupSql001ResponseModel>();
        selSql001Res = callSQL001(userId, branchNameBrokerName, employeeNameChargeName);
        
        //検索結果が0件の場合、メッセージを表示
        if (SEARCH_RESULT_0 == selSql001Res.getDataList().size()) {
            
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.INFO, ERRORS_DATALIST_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATALIST_NOTFOUND));
        }
        
        response.setLoginIdManageList(getLoginIdManageList(selSql001Res));
        resDto.add(response);
        dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
        
        return dtoRes;
    }
    
    /**
     * アクションID：A007
     * アクション名：削除
     * Dto リクエスト：IfaLoginUserManageManagerLookupA007RequestDto
     * Dto レスポンス：IfaLoginUserManageManagerLookupA007ResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginUserManageManagerLookupA007ResponseDto> deleteA007(
            IfaLoginUserManageManagerLookupA007RequestDto dtoReq) throws Exception {
        
        DataList<IfaLoginUserManageManagerLookupA007ResponseDto> dtoRes = new DataList<IfaLoginUserManageManagerLookupA007ResponseDto>();
        List<IfaLoginUserManageManagerLookupA007ResponseDto> resDto = new ArrayList<IfaLoginUserManageManagerLookupA007ResponseDto>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaLoginUserManageManagerLookupServiceImplL.deleteA007");
        
        //利用者権限チェック
        if (!checkPrivId()) {
            
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE));
        }
        
        //削除処理
        int reqManagerCount = Integer.parseInt(dtoReq.getManagerCount());
        String reqLoginId = dtoReq.getLoginId();
        
        //リクエスト.担当数 = 1の場合
        if (REQUEST_MANAGERCOUNT_1 == reqManagerCount) {
            
            //SQL002
            List<IfaLoginUserManageManagerLookupSql002ResponseModel> selSql002ResCount = new ArrayList<IfaLoginUserManageManagerLookupSql002ResponseModel>();
            selSql002ResCount = callSQL002(reqLoginId);
            
            if (null == selSql002ResCount) {
                throw new Exception();
            }
            
            //1件以上の場合エラー
            if (SEARCH_RESULT_1 <= Integer.parseInt(selSql002ResCount.get(0).getCount())) {
                
                return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_EMP_LOGINUSERS_REGISTERED,
                        IfaCommonUtil.getMessage(ERRORS_EMP_LOGINUSERS_REGISTERED));
            }
            
            //SQL003
            int delSql003Res = callSQL003(reqLoginId);
            if (delSql003Res < DELETE_RESULT_OK) {
                throw new Exception();
            }
            
            //SQL004
            int delSql004Res = callSQL004(reqLoginId);
            if (delSql004Res != DELETE_RESULT_OK) {
                throw new Exception();
            }
            
            //SQL005
            callSQL005(reqLoginId);
            
            //リクエスト.担当数 >= 2の場合
        } else if (REQUEST_MANAGERCOUNT_2 <= reqManagerCount) {
            
            //SQL006
            int delSql006Res = callSQL006(reqLoginId, dtoReq.getSbiSecurityCode(), dtoReq.getBrokerCode(),
                    dtoReq.getSubBrokerId(), dtoReq.getEmployeeId());
            if (delSql006Res != DELETE_RESULT_OK) {
                throw new Exception();
            }
        }
        
        //再検索
        //検索条件設定
        String userId = dtoReq.getLoginId();
        
        String branchNameBrokerName = null;
        
        String employeeNameChargeName = null;
        
        //ユーザ情報検索
        //SQL001
        callSQL001(userId, branchNameBrokerName, employeeNameChargeName);
        
        dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, INFO_DELETECOMPLETED,
                IfaCommonUtil.getMessage(INFO_DELETECOMPLETED, new String[] { ERROR_MSG_USER_INFO }));
        
        return dtoRes;
    }
    
    /**
     * 利用者権限チェック
     * @return チェック結果
     */
    private boolean checkPrivId() {
        
        return StringUtils.equals(IfaCommonUtil.getUserAccount().getPrivId(), USER_PERMISSION_SBI);
    }
    
    /**
     * SQL001呼出し
     * @param userId
     * @param branchNameBrokerName
     * @param employeeNameChargeName
     * @return selSql001Res
     * @throws Exception
     */
    private DataList<IfaLoginUserManageManagerLookupSql001ResponseModel> callSQL001(String userId,
            String branchNameBrokerName, String employeeNameChargeName) throws Exception {
        
        IfaLoginUserManageManagerLookupSql001RequestModel selSql001Req = new IfaLoginUserManageManagerLookupSql001RequestModel();
        DataList<IfaLoginUserManageManagerLookupSql001ResponseModel> selSql001Res = new DataList<IfaLoginUserManageManagerLookupSql001ResponseModel>();
        
        selSql001Req.setUserId(userId);
        selSql001Req.setBranchNameBrokerName(branchNameBrokerName);
        selSql001Req.setEmployeeNameChargeName(employeeNameChargeName);
        selSql001Res = dao.selectIfaLoginUserManageManagerLookupSql001(selSql001Req);
        
        return selSql001Res;
    }
    
    /**
     * SQL002呼出し
     * @param reqLoginId
     * @return
     * @throws Exception
     */
    private List<IfaLoginUserManageManagerLookupSql002ResponseModel> callSQL002(String reqLoginId) throws Exception {
        
        IfaLoginUserManageManagerLookupSql002RequestModel selSql002Req = new IfaLoginUserManageManagerLookupSql002RequestModel();
        DataList<IfaLoginUserManageManagerLookupSql002ResponseModel> selSql002Res = new DataList<IfaLoginUserManageManagerLookupSql002ResponseModel>();
        
        selSql002Req.setLoginId(reqLoginId);
        selSql002Res = dao.selectIfaLoginUserManageManagerLookupSql002(selSql002Req);
        
        return selSql002Res.getDataList();
    }
    
    /**
     * SQL003呼出し
     * @param reqLoginId
     * @return　delSql003Res
     * @throws Exception 
     */
    private int callSQL003(String reqLoginId) throws Exception {
        
        IfaLoginUserManageManagerLookupSql003RequestModel delSql003Req = new IfaLoginUserManageManagerLookupSql003RequestModel();
        delSql003Req.setLoginId(reqLoginId);
        int delSql003Res = dao.deleteIfaLoginUserManageManagerLookupSql003(delSql003Req);
        
        return delSql003Res;
    }
    
    /**
     * SQL004呼出し
     * @param reqLoginId
     * @return　delSql004Res
     * @throws Exception 
     */
    private int callSQL004(String reqLoginId) throws Exception {
        
        IfaLoginUserManageManagerLookupSql004RequestModel delSql004Req = new IfaLoginUserManageManagerLookupSql004RequestModel();
        delSql004Req.setLoginId(reqLoginId);
        int delSql004Res = dao.deleteIfaLoginUserManageManagerLookupSql004(delSql004Req);
        
        return delSql004Res;
    }
    
    /**
     * SQL005呼出し
     * @param reqLoginId
     * @return　delSql005Res
     * @throws Exception 
     */
    private void callSQL005(String reqLoginId) throws Exception {
        
        IfaLoginUserManageManagerLookupSql005RequestModel delSql005Req = new IfaLoginUserManageManagerLookupSql005RequestModel();
        delSql005Req.setLoginId(reqLoginId);
        dao.deleteIfaLoginUserManageManagerLookupSql005(delSql005Req);
        
    }
    
    /**
     * SQL006を実行する
     * @param reqLoginId
     * @return　delSql006Res
     * @throws Exception 
     */
    private int callSQL006(String reqLoginId, String sbiSecurityCode, String brokerCode, String subBrokerId,
            String employeeId) throws Exception {
        
        IfaLoginUserManageManagerLookupSql006RequestModel delSql006Req = new IfaLoginUserManageManagerLookupSql006RequestModel();
        delSql006Req.setLoginId(reqLoginId);
        delSql006Req.setSbiSecurityCode(sbiSecurityCode);
        delSql006Req.setBrokerCode(brokerCode);
        delSql006Req.setSubBrokerId(subBrokerId);
        delSql006Req.setEmployeeId(employeeId);
        
        int delSql006Res = dao.deleteIfaLoginUserManageManagerLookupSql006(delSql006Req);
        
        return delSql006Res;
    }
    
    /** *
     * SQL007呼出し
     * @return selSql007Res
     * @throws Exception
     */
    private DataList<IfaLoginUserManageManagerLookupSql007ResponseModel> callSQL007() throws Exception {
        
        IfaLoginUserManageManagerLookupSql007RequestModel selSql007Req = new IfaLoginUserManageManagerLookupSql007RequestModel();
        DataList<IfaLoginUserManageManagerLookupSql007ResponseModel> selSql007Res = new DataList<IfaLoginUserManageManagerLookupSql007ResponseModel>();
        selSql007Res = dao.selectIfaLoginUserManageManagerLookupSql007(selSql007Req);
        
        return selSql007Res;
    }
    
    /**
     * 別クラスの同名フィールドをコピーする
     * (BeanUtils.copyPropertiesが機能しないため)
     * @param source コピー元のインスタンス
     * @param target コピー先のインスタンス
     */
    private static void copyFields(Object source, Object target) {
        
        final String prefixSet = "set";
        final String prefixGet = "get";
        
        Set<String> methodSet = new HashSet<String>();
        for (Method method : target.getClass().getMethods())
            if (method.getName().startsWith(prefixSet))
                methodSet.add(method.getName().substring(prefixSet.length()));
            
        try {
            for (Method method : source.getClass().getMethods()) {
                if (!method.getName().startsWith(prefixGet))
                    continue;
                String curName = method.getName().substring(prefixGet.length());
                if (methodSet.contains(curName)) {
                    Method newMethod = target.getClass().getMethod(prefixSet + curName, method.getReturnType());
                    newMethod.invoke(target, method.invoke(source));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * メニューリストを取得する
     * @param selSql007Res
     * @return menuList
     */
    private List<IfaLoginUserManageManagerLookupA001ResponseDto_MenuList> getMenuList(
            DataList<IfaLoginUserManageManagerLookupSql007ResponseModel> selSql007Res) {
        
        List<IfaLoginUserManageManagerLookupA001ResponseDto_MenuList> menuList = new ArrayList<IfaLoginUserManageManagerLookupA001ResponseDto_MenuList>();
        
        for (IfaLoginUserManageManagerLookupSql007ResponseModel res : selSql007Res.getDataList()) {
            
            IfaLoginUserManageManagerLookupA001ResponseDto_MenuList menuInfo = new IfaLoginUserManageManagerLookupA001ResponseDto_MenuList();
            copyFields(res, menuInfo);
            menuList.add(menuInfo);
        }
        
        return menuList;
    }
    
    /**
     * ログインID管理リストを取得する
     * @param selSql001Res
     * @return loginIdManageList
     */
    private List<IfaLoginUserManageManagerLookupA002ResponseDto_LoginIdManageList> getLoginIdManageList(
            DataList<IfaLoginUserManageManagerLookupSql001ResponseModel> selSql001Res) {
        
        List<IfaLoginUserManageManagerLookupA002ResponseDto_LoginIdManageList> loginIdManageList = new ArrayList<IfaLoginUserManageManagerLookupA002ResponseDto_LoginIdManageList>();
        for (IfaLoginUserManageManagerLookupSql001ResponseModel res : selSql001Res.getDataList()) {
            
            IfaLoginUserManageManagerLookupA002ResponseDto_LoginIdManageList loginManageInfo = new IfaLoginUserManageManagerLookupA002ResponseDto_LoginIdManageList();
            
            //取得したパスワードを空文字に設定
            res.setPassword(PASSWORD_EMPTY);
            
            //ログインIDをセット
            loginManageInfo.setLoginId(res.getUserId());
            //管理者フラグをセット
            loginManageInfo.setGovernorFlag(res.getGovernorFlag());
            //権限所属をセット
            loginManageInfo.setPrivId(res.getPrivId());
            //担当数をセット
            loginManageInfo.setManagerCount(res.getManagerCount());
            //本支店コードをセット
            loginManageInfo.setBranchCode(res.getSbiSecurityCode());
            //支店名をセット
            loginManageInfo.setHeadOfficeBranchName(res.getHeadOfficeBranchName());
            //仲介業者コードをセット
            loginManageInfo.setBrokerCode(res.getBrokerCode());
            //仲介業者名をセット
            loginManageInfo.setBrokerName(res.getBrokerName());
            //仲介業者支店コードをセット
            loginManageInfo.setSubBrokerId(res.getSubBrokerId());
            //仲介業者担当者コードをセット
            loginManageInfo.setEmployeeId(res.getEmployeeId());
            //仲介業者担当者名をセット
            loginManageInfo.setEmployeeName(res.getEmployeeName());
            //社員名担当者名をセット
            loginManageInfo.setEmployeeNameChargeName(res.getUserName());
            //メールアドレスをセット
            loginManageInfo.setMailAddress(res.getMailAddress());
            
            loginIdManageList.add(loginManageInfo);
        }
        
        return loginIdManageList;
    }
    
}
