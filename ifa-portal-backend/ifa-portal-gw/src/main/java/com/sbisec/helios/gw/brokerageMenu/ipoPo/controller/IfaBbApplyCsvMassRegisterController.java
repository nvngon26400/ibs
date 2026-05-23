package com.sbisec.helios.gw.brokerageMenu.ipoPo.controller;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ResponseJson;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.DateUtil;
import com.sbibits.earth.util.StringUtil;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct006Dto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.BBInvestorAttModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoBBApplyEdelivAgreementModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoBBApplyUploadCheckResultModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoBBApplyUploadModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoBBCustomerInfoModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoUploadBBAcceptModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoUploadBBCustomerInfoModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoUploadBBCustomerOverMaxCheckModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.SectionModel;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.ipopo.BBGestureValue;
import com.sbisec.helios.ap.common.enums.ipopo.BBInvestorAtt;
import com.sbisec.helios.ap.common.enums.ipopo.BBIpoPoKbn;
import com.sbisec.helios.ap.common.enums.ipopo.BBStrikePrice;
import com.sbisec.helios.ap.common.enums.ipopo.BBUrgentStop;
import com.sbisec.helios.ap.common.enums.ipopo.ChoseReason;
import com.sbisec.helios.ap.common.enums.ipopo.CorporationKbn;
import com.sbisec.helios.ap.common.enums.ipopo.EdelivOnlyFlag;
import com.sbisec.helios.ap.common.enums.ipopo.ErrorType;
import com.sbisec.helios.ap.common.enums.ipopo.FinancialAssets;
import com.sbisec.helios.ap.common.enums.ipopo.TradeMethod;
import com.sbisec.helios.ap.common.model.MCode;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyCsvMassRegisterA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyCsvMassRegisterA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyCsvMassRegisterA003ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyCsvMassRegisterA007ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyCsvMassRegisterA007ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyCsvMassRegisterBbApplyListApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyCsvMassRegisterBbApplyListApiResponse;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaBbApplyCsvMassRegisterCheckResultSetApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.CheckUtil;
import com.sbisec.helios.gw.common.util.UnicodeCheckUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 画面ID：SUB0204_01-03_1
 * 画面名：BB申込(一括登録)
 *
 * @author SCSK
 *
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0204_01-03_1", screenNumber = StringUtil.EMPTY_STRING)
public class IfaBbApplyCsvMassRegisterController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    private static final Logger logger = LoggerFactory.getLogger(IfaBbApplyCsvMassRegisterController.class);
    
    /** コメント取得用 */
    private static final String INFORMATION_CODE_TYPE = "99";
    
    private static final String INFORMATION_CODE_1 = "01";
    
    private static final String INFORMATION_CODE_2 = "14";
    
    /** タグ変換用 */
    private static final String REPLACE_TAG = "<br/>";
    
    private static final String REPLACE_TAG_BR = "<br>";
    
    /** CSV処理MAX件数 */
    private static final int LIMIT = 1000;
    
    /**　FCT001 条件判定用 */
    public static final String FCT001_NO_AUTHORITY = "0";
    
    public static final String FCT001_TRADING_STOP = "1";
    
    /**　FCT003 条件判定用 */
    public static final String FCT003_NOT_TRADEABLE = "0";
    
    /**　FCT006 条件判定用 */
    public static final String FCT006_NORMAL = "0";
    
    public static final String FCT006_ALERT = "1";
    
    public static final String FCT006_ERROR = "2";
    
    /**　区分.対象取引（メッセージ表示用）：　BB申込 */
    private static final String CODE_LIST_ID = "MSG_DISPLAY_TARGET_TRADE";
    
    private static final String BB_APPLY = "7";
    
    private static final String DISPLAY_PATTERN = "1";
    
    /**
    * A001 初期化
    *
    * @param apiReq {@code IfaBbApplyCsvMassRegisterA001ApiRequest }
    * @return {@code String}
    * @throws Exception 初期化処理で例外が発生した場合
    */
    @PostMapping(value = "/brokerageMenu/ipoPo/ifaBbApplyCsvMassRegisterInitializeA001")
    @ResponseBody
    @ResponseJson
    public String initializeA001(@RequestBody IfaBbApplyCsvMassRegisterA001ApiRequest apiReq) throws Exception {
        
        final long start = System.currentTimeMillis();
        logger.debug("IpopoBBApplyUploadController.initializeA001 >> {}", hashCode());
        
        List<IfaBbApplyCsvMassRegisterA001ApiResponse> apiResList = new ArrayList<IfaBbApplyCsvMassRegisterA001ApiResponse>();
        IfaBbApplyCsvMassRegisterA001ApiResponse apiRes = new IfaBbApplyCsvMassRegisterA001ApiResponse();
        
        // 画面用コメントを取得する
        List<MCode> codeList = ApiRequestUtil.invoke("comIpopoBBApplyUploadService", "getMCodeList",
                new TypeReference<List<MCode>>() {
                }, INFORMATION_CODE_TYPE, INFORMATION_CODE_1, INFORMATION_CODE_2);
        
        StringBuilder txaComment = new StringBuilder();
        // 画面用コメントを結合
        if (codeList == null) {
            txaComment.append("");
        } else {
            for (MCode code : codeList) {
                String comment = StringUtil.nullToEmpty(code.getName());
                comment = StringUtils.replace(comment, REPLACE_TAG, LF);
                txaComment.append(comment);
            }
        }
        
        DataList<IfaBbApplyCsvMassRegisterA001ApiResponse> apiResDataList = new DataList<IfaBbApplyCsvMassRegisterA001ApiResponse>();
        
        apiRes.setTxaComment(txaComment.toString());
        apiResList.add(apiRes);
        apiResDataList = IfaCommonUtil.createDataList(apiResList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        
        logger.debug("cost -> {}", (System.currentTimeMillis() - start));
        
        return jc.toString(apiResDataList);
    }
    
    /**
    * A003 確認
    *
    * @param file {@code MultipartFile }
    * @return {@code String}
    * @throws Exception 確認処理で例外が発生した場合
    */
    @PostMapping(value = "/brokerageMenu/ipoPo/ifaBbApplyCsvMassRegisterConfirmA003")
    @ResponseBody
    @ResponseJson
    public String confirmA003(@RequestParam("uploadFile") MultipartFile file) throws Exception {
        
        final long start = System.currentTimeMillis();
        logger.debug("IpopoBBApplyUploadController.confirmA003 >> {}", hashCode());
        
        // ユーザ情報を取得する
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        // エラーメッセージ
        String errorMessage = StringUtil.EMPTY_STRING;
        
        // 戻り結果を設定する変数
        DataList<IfaBbApplyCsvMassRegisterA003ApiResponse> apiResDataList = new DataList<IfaBbApplyCsvMassRegisterA003ApiResponse>();
        List<IfaBbApplyCsvMassRegisterA003ApiResponse> apiResList = new ArrayList<IfaBbApplyCsvMassRegisterA003ApiResponse>();
        IfaBbApplyCsvMassRegisterA003ApiResponse apiRes = new IfaBbApplyCsvMassRegisterA003ApiResponse();
        List<IfaBbApplyCsvMassRegisterBbApplyListApiResponse> bbApplyList = new ArrayList<IfaBbApplyCsvMassRegisterBbApplyListApiResponse>();
        
        // 取込用
        Workbook workbook = null;
        
        // ファイルパスの拡張子チェック
        if (!checkFileName(file.getOriginalFilename())) {
            // xlsまたはxlsxファイル以外の場合
            errorMessage = getMessage(ERRORS_NOT_EXCEL_FILE, new String[] {});
            apiResDataList = IfaCommonUtil.createDataList(apiResList, ErrorLevel.FATAL, ERRORS_NOT_EXCEL_FILE,
                    errorMessage);
            return jc.toString(apiResDataList);
        }
        
        try (InputStream is = file.getInputStream()) {
            // Excelファイルを読み込む
            workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);
            
            // 行数チェック用
            int lastRowNum = getMaxCount(sheet);
            
            // 行数チェック（見出しレコードを含む件数）
            if (lastRowNum < 1) {
                // 1行以下の場合
                errorMessage = getMessage(ERRORS_EXCEL_ONE_MORE_DATA, new String[] {});
                apiResDataList = IfaCommonUtil.createDataList(apiResList, ErrorLevel.FATAL, ERRORS_EXCEL_ONE_MORE_DATA,
                        errorMessage);
                return jc.toString(apiResDataList);
            }
            // 最大件数チェックを行う、1000件以上の場合、エラーが発生する。
            if (lastRowNum > LIMIT) {
                errorMessage = getMessage(ERRORS_MAX_UPLOAD, new String[] { String.valueOf(LIMIT) });
                apiResDataList = IfaCommonUtil.createDataList(apiResList, ErrorLevel.FATAL, ERRORS_MAX_UPLOAD,
                        errorMessage);
                return jc.toString(apiResDataList);
            }
            
            // CSVファイル内に重複キーチェック用リスト
            List<String> keyList = new ArrayList<String>();
            
            // CSV取込を行う
            for (Row row : sheet) {
                // ヘッダを飛ぶ
                if (row.getRowNum() == 0) {
                    continue;
                }
                // CSVファイルにデータを取得する
                String[] arr = toArray(row);
                // 該当行目の内容がなしの場合
                if (arr == null) {
                    continue;
                }
                
                // 定義
                IpopoBBApplyUploadCheckResultModel msgModel = new IpopoBBApplyUploadCheckResultModel();
                IpopoUploadBBCustomerInfoModel csvModel = new IpopoUploadBBCustomerInfoModel();
                IfaBbApplyCsvMassRegisterBbApplyListApiResponse bbApply = new IfaBbApplyCsvMassRegisterBbApplyListApiResponse();
                
                // 取得内容を設定とチェック
                addScreenByArr(arr, csvModel, msgModel, userAccount, keyList);
                
                // レスポンス内容を設定する。
                setApiResponse(bbApply, csvModel, msgModel);
                
                bbApplyList.add(bbApply);
            }
            apiRes.setBbApplyList(bbApplyList);
            apiResList.add(apiRes);
            
        } catch (EncryptedDocumentException e) {
            logger.info("EncryptedDocumentException occured.", e);
            errorMessage = getMessage(ERRORS_HAS_PASSWORD, new String[] {});
            apiResDataList = IfaCommonUtil.createDataList(apiResList, ErrorLevel.FATAL, ERRORS_HAS_PASSWORD,
                    errorMessage);
            return jc.toString(apiResDataList);
            
        } catch (Exception e) {
            // サーバ側でエラーが発生
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            errorMessage = getMessage(ERRORS_SERVERERROR, new String[] {});
            apiResDataList = IfaCommonUtil.createDataList(apiResList, ErrorLevel.FATAL, ERRORS_SERVERERROR,
                    errorMessage);
            return jc.toString(apiResDataList);
            
        }
        
        logger.debug("cost -> {}", (System.currentTimeMillis() - start));
        
        // 戻り値をJsonのStringに変換
        apiResDataList = IfaCommonUtil.createDataList(apiResList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return jc.toString(apiResDataList);
    }
    
    /**
    * 総件数を取得する
    *
    * @param sheet {@code Sheet }
    * @return {@code Integer}
    */
    private int getMaxCount(Sheet sheet) {
        
        int count = 0;
        for (Row row : sheet) {
            // ヘッダを飛ぶ
            if (row.getRowNum() == 0) {
                continue;
            }
            // CSVファイルにデータを取得する
            String[] arr = toArray(row);
            
            if (arr == null) {
                continue;
            }
            count++;
            // 件数が1000以上の場合
            if (count > LIMIT) {
                return count;
            }
        }
        return count;
    }
    
    /**
    * 取得内容の設定とチェック
    *
    * @param nextLine {@code String[] }
    * @param csvModel {@code IpopoUploadBBCustomerInfoModel }
    * @param msgModel {@code IpopoBBApplyUploadCheckResultModel }
    * @param userAccount {@code UserAccount }
    * @param keyList {@code List<String> }
    * @return {@code String}
    * @throws Exception 取得内容の設定とチェック処理で例外が発生した場合
    */
    private void addScreenByArr(String[] nextLine, IpopoUploadBBCustomerInfoModel csvModel,
            IpopoBBApplyUploadCheckResultModel msgModel, UserAccount userAccount, List<String> keyList)
            throws Exception {
        
        // CSVファイルにデータを取得する
        String brandCode = nextLine[0]; // 銘柄コード
        String butenCode = nextLine[1]; // 部店
        if (!StringUtil.isNullOrEmpty(butenCode)) {
            butenCode = butenCode.toUpperCase();
        }
        String accountNumber = nextLine[2]; // 口座番号
        String bbQuantity = nextLine[3]; // 希望株数
        String bbPrice = nextLine[4]; // 申込価格
        String bbSeq = nextLine[5]; // 投資家属性
        String quantitySairyou = nextLine[6]; // 裁量希望株数
        String choseReason = nextLine[7]; // 裁量選定理由
        String bbRemark = nextLine[8]; // 備考
        String kanyuKbn = nextLine[9]; // 勧誘区分
        String receiveOrderType = nextLine[10]; // 受注方法
        
        csvModel.setBrandCode(brandCode);
        csvModel.setButenCode(butenCode);
        csvModel.setAccountNumber(accountNumber);
        csvModel.setBbQuantity(bbQuantity);
        csvModel.setBbPrice(bbPrice);
        csvModel.setBbSeq(bbSeq);
        // 投資家属性名を設定する
        BBInvestorAtt bbInvestorAtt = BBInvestorAtt.valueOfId(bbSeq);
        if (bbInvestorAtt != null) {
            csvModel.setBbInvestorAttName(bbInvestorAtt.getLabel());
        } else {
            csvModel.setBbInvestorAttName(bbSeq);
        }
        csvModel.setQuantitySairyou(quantitySairyou);
        csvModel.setChoseReason(choseReason);
        // 画面表示の裁量選定理由を設定する
        ChoseReason cr = ChoseReason.valueOfId(choseReason);
        if (cr != null) {
            csvModel.setChoseReasonForShow(cr.getLabel());
        } else {
            csvModel.setChoseReasonForShow(choseReason);
        }
        csvModel.setBbRemark(bbRemark);
        csvModel.setBbRemarkForShow(bbRemark.replaceAll("\n", REPLACE_TAG_BR));
        csvModel.setKanyuKbn(kanyuKbn);
        csvModel.setReceiveOrderType(receiveOrderType);
        
        // チェックを行う。
        toCheck(userAccount, csvModel, brandCode, butenCode, accountNumber, bbQuantity, bbPrice, bbSeq, quantitySairyou,
                choseReason, bbRemark, kanyuKbn, receiveOrderType, msgModel, keyList);
    }
    
    /**
    * A007 登録
    *
    * @param apiReq {@code IfaBbApplyCsvMassRegisterA007ApiRequest }
    * @return {@code String}
    * @throws Exception 登録処理で例外が発生した場合
    */
    @PostMapping(value = "/brokerageMenu/ipoPo/ifaBbApplyCsvMassRegisterRegisterA007")
    @ResponseJson
    @ResponseBody
    public String registerA007(@RequestBody IfaBbApplyCsvMassRegisterA007ApiRequest apiReq) throws Exception {
        
        long start = System.currentTimeMillis();
        String resultJson = StringUtil.EMPTY_STRING;
        
        // ユーザ情報を取得する
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        // データリスト
        DataList<IfaBbApplyCsvMassRegisterA007ApiResponse> apiResDataList = new DataList<IfaBbApplyCsvMassRegisterA007ApiResponse>();
        List<IfaBbApplyCsvMassRegisterA007ApiResponse> apiResList = new ArrayList<IfaBbApplyCsvMassRegisterA007ApiResponse>();
        IfaBbApplyCsvMassRegisterA007ApiResponse apiRes = new IfaBbApplyCsvMassRegisterA007ApiResponse();
        List<IfaBbApplyCsvMassRegisterBbApplyListApiResponse> bbApplyList = new ArrayList<IfaBbApplyCsvMassRegisterBbApplyListApiResponse>();
        List<IpopoUploadBBCustomerInfoModel> screenList = new ArrayList<IpopoUploadBBCustomerInfoModel>();
        
        // CSVファイル内に重複キーチェック用リスト
        List<String> keyList = new ArrayList<String>();
        for (IfaBbApplyCsvMassRegisterBbApplyListApiRequest bbApply : apiReq.getBbApplyList()) {
            
            IpopoUploadBBCustomerInfoModel csvModel = new IpopoUploadBBCustomerInfoModel();
            IpopoBBApplyUploadCheckResultModel msgModel = new IpopoBBApplyUploadCheckResultModel();
            
            String brandCode = bbApply.getBrandCode(); // 銘柄コード
            String butenCode = bbApply.getButenCode(); // 部店
            String accountNumber = bbApply.getAccountNumber(); // 口座番号
            String bbQuantity = bbApply.getBbQuantity(); // 希望株数
            String bbPrice = bbApply.getBbPrice(); // 申込価格
            String bbSeq = bbApply.getBbSeq(); // 投資家属性
            String quantitySairyou = bbApply.getQuantitySairyou(); // 裁量希望株数
            String choseReason = bbApply.getChoseReason(); // 裁量選定理由
            String bbRemark = bbApply.getBbRemark(); // 備考
            String kanyuKbn = bbApply.getKanyuKbn(); // 勧誘区分
            String receiveOrderType = bbApply.getReceiveOrderType(); // 受注方法
            
            // リクエストのデータをモデルに設定する
            setApiReqToModel(bbApply, csvModel);
            
            // チェックを行う。
            toCheck(userAccount, csvModel, brandCode, butenCode, accountNumber, bbQuantity, bbPrice, bbSeq,
                    quantitySairyou, choseReason, bbRemark, kanyuKbn, receiveOrderType, msgModel, keyList);
            
            csvModel.setMsgModel(msgModel);
            screenList.add(csvModel);
        }
        
        // 登録用リストを作成する
        List<IpopoUploadBBAcceptModel> insertList = new ArrayList<IpopoUploadBBAcceptModel>();
        for (IpopoUploadBBCustomerInfoModel screenModel : screenList) {
            if (ErrorType.WARNING.getLabel().equals(screenModel.getCheckResult())
                    || ErrorType.OK.getLabel().equals(screenModel.getCheckResult())) {
                IpopoUploadBBAcceptModel model = new IpopoUploadBBAcceptModel();
                model.setBrandCode(screenModel.getBrandCode());
                if (screenModel.getBbPresentationFrom() != null) {
                    model.setBbPresentationFrom(
                            DateUtil.format(screenModel.getBbPresentationFrom(), DateUtil.YYYYMMDD));
                }
                if (!StringUtil.isNullOrEmpty(screenModel.getButenCode())) {
                    model.setButenCode(StringUtil.fillRight(screenModel.getButenCode(), ' ', 4));
                }
                if (!StringUtil.isNullOrEmpty(screenModel.getAccountNumber())) {
                    model.setAccountNumber(StringUtil.fillLeft(screenModel.getAccountNumber(), '0', 7));
                }
                model.setDealerNumber(screenModel.getDealerNumber());
                model.setUaiFamilyNameKanji(screenModel.getUaiFamilyNameKanji());
                model.setUaiNameKanji(screenModel.getUaiNameKanji());
                model.setUaiFamilyNameKana(screenModel.getUaiFamilyNameKana());
                model.setUaiNameKana(screenModel.getUaiNameKana());
                model.setBbQuantity(screenModel.getBbQuantity());
                if (!TradeMethod.NARIYUKI.getLabel().equals(screenModel.getBbPrice())
                        && BBGestureValue.PRICE.getId().equals(screenModel.getBbGestureValue())) {
                    model.setBbPrice(screenModel.getBbPrice());
                }
                if (!TradeMethod.NARIYUKI.getLabel().equals(screenModel.getBbPrice())
                        && BBGestureValue.DISCOUNT.getId().equals(screenModel.getBbGestureValue())) {
                    model.setBbDiscount(screenModel.getBbPrice());
                }
                model.setBbSeq(screenModel.getBbSeq());
                model.setBbInvestorAttName(screenModel.getBbInvestorAttName());
                model.setBbRemark(screenModel.getBbRemark());
                if (TradeMethod.NARIYUKI.getLabel().equals(screenModel.getBbPrice())) {
                    model.setBbStrikePrice(BBStrikePrice.NARIYUKI_ARI.getId());
                } else {
                    model.setBbStrikePrice(BBStrikePrice.NARIYUKI_NASHI.getId());
                }
                model.setUserId(userAccount.getUserId());
                model.setUserName(userAccount.getUserNm());
                
                // 裁量部分
                model.setQuantitySairyou(screenModel.getQuantitySairyou());
                model.setBbQuantityForSairyou(screenModel.getBbQuantity());
                if (!StringUtil.isNullOrEmpty(screenModel.getQuantitySairyou())) {
                    model.setChoseReason(ChoseReason.valueOfId(screenModel.getChoseReason()).getLabel());
                }
                // セクションIDと支店名を設定する
                model.setSectionId(screenModel.getSectionId());
                model.setSectionName(screenModel.getSectionName());
                
                if (ErrorType.WARNING.getLabel().equals(screenModel.getCheckResult())
                        || ErrorType.OK.getLabel().equals(screenModel.getCheckResult())) {
                    insertList.add(model);
                }
            }
        }
        
        // DBに登録
        int result = 0;
        if (insertList.size() > 0) {
            result = ApiRequestUtil.invoke("comIpopoBBApplyUploadService", "insertUploadIpopoBBApplyInfo",
                    new TypeReference<Integer>() {
                    }, insertList);
        } else {
            // 注文可能なデータが存在しない場合、レスポンス内容を設定する。
            for (IpopoUploadBBCustomerInfoModel screenModel : screenList) {
                IfaBbApplyCsvMassRegisterBbApplyListApiResponse resBbApply = new IfaBbApplyCsvMassRegisterBbApplyListApiResponse();
                setApiResponse(resBbApply, screenModel, screenModel.getMsgModel());
                bbApplyList.add(resBbApply);
            }
            apiRes.setBbApplyList(bbApplyList);
            apiResList.add(apiRes);
            
            String msg = getMessage(INFO_ORDERED_DATA_NOT_EXIST, new String[] { "OKと警告あり" });
            apiResDataList = IfaCommonUtil.createDataList(apiResList, ErrorLevel.INFO, INFO_ORDERED_DATA_NOT_EXIST,
                    msg);
            resultJson = jc.toString(apiResDataList);
            logger.debug("IpopoBBApplyUploadController.registerA007 >> {}", hashCode());
            logger.debug("cost -> {}", (System.currentTimeMillis() - start));
            return resultJson;
        }
        
        if (result == 1) {
            // 登録済みのデータのステータスは登録済みへ更新する
            int insertCount = 0;
            for (IpopoUploadBBCustomerInfoModel screenModel : screenList) {
                if (ErrorType.OK.getLabel().equals(screenModel.getCheckResult())
                        || ErrorType.WARNING.getLabel().equals(screenModel.getCheckResult())) {
                    screenModel.setCheckResult(ErrorType.DATA_EXIST.getLabel());
                    insertCount++;
                }
                // レスポンス内容を設定する。
                IfaBbApplyCsvMassRegisterBbApplyListApiResponse resBbApply = new IfaBbApplyCsvMassRegisterBbApplyListApiResponse();
                setApiResponse(resBbApply, screenModel, screenModel.getMsgModel());
                bbApplyList.add(resBbApply);
            }
            apiRes.setBbApplyList(bbApplyList);
            apiResList.add(apiRes);
            
            // 登録成功の場合
            String msg = getMessage(INFO_UPLOAD_INSERT_COMPLETED, new String[] { String.valueOf(insertCount) });
            apiResDataList = IfaCommonUtil.createDataList(apiResList, ErrorLevel.INFO, INFO_UPLOAD_INSERT_COMPLETED,
                    msg);
        }
        resultJson = jc.toString(apiResDataList);
        logger.debug("IpopoBBApplyUploadController.registerA007 >> {}", hashCode());
        logger.debug("cost -> {}", (System.currentTimeMillis() - start));
        return resultJson;
    }
    
    /**
    * チェックを行う。
    *
    * @param userAccount {@code UserAccount }
    * @param csvModel {@code IpopoUploadBBCustomerInfoModel }
    * @param brandCode {@code String }
    * @param butenCode {@code String }
    * @param accountNumber {@code String }
    * @param bbQuantity {@code String }
    * @param bbPrice {@code String }
    * @param bbSeq {@code String }
    * @param quantitySairyou {@code String }
    * @param choseReason {@code String }
    * @param bbRemark {@code String }
    * @param kanyuKbn {@code String }
    * @param receiveOrderType {@code String }
    * @param msgModel {@code IpopoBBApplyUploadCheckResultModel }
    * @param keyList {@code List<String> }
    * @return {@code void}
    * @throws Exception チェック処理で例外が発生した場合
    */
    private void toCheck(UserAccount userAccount, IpopoUploadBBCustomerInfoModel csvModel, String brandCode,
            String butenCode, String accountNumber, String bbQuantity, String bbPrice, String bbSeq,
            String quantitySairyou, String choseReason, String bbRemark, String kanyuKbn, String receiveOrderType,
            IpopoBBApplyUploadCheckResultModel msgModel, List<String> keyList) throws Exception {
        
        // CSVファイル内に重複キーチェックを行う。
        String accountNumberFormat = StringUtil.fillLeft(accountNumber, '0', 7);
        String insertKey = brandCode.concat(butenCode).concat(accountNumberFormat);
        if (keyList.contains(insertKey)) {
            msgModel.setExistMsg(getMessage(ERRORS_KEY_EXIST, new String[] {}));
            msgModel.setExistMsgStatus(ErrorType.ERROR.getLabel());
            csvModel.setCheckResult(ErrorType.ERROR.getLabel());
            // CSVファイル内に重複キーの場合、下記の処理を行わない
            return;
        } else {
            keyList.add(insertKey);
        }
        
        // 入力チェックを行う。
        uploadValidationCheck(brandCode, butenCode, accountNumber, bbQuantity, bbPrice, bbSeq, quantitySairyou,
                choseReason, bbRemark, kanyuKbn, receiveOrderType, csvModel, msgModel);
        
        // 利用者の口座に対する権限チェックを行う。(FCT001)
        OutputFct001Dto fct001Dto = new OutputFct001Dto();
        fct001Dto = ApiRequestUtil.invoke("comIpopoBBApplyUploadService", "getFct001",
                new TypeReference<OutputFct001Dto>() {
                }, butenCode, accountNumber);
        
        if (FCT001_NO_AUTHORITY.equals(fct001Dto.getTargetCustomerRefAuthFlag())) {
            // 対象顧客参照権限なしの場合、メッセージを設定して移行の処理は行わない
            String existMsg = StringUtil.EMPTY_STRING;
            existMsg = getMessage(ERRORS_BUTEN_ACCOUNT_NOT_EXIST, new String[] { butenCode, accountNumber });
            msgModel.setExistMsg(existMsg);
            msgModel.setExistMsgStatus(ErrorType.ERROR.getLabel());
            csvModel.setCheckResult(ErrorType.ERROR.getLabel());
            return;
        } else if (FCT001_TRADING_STOP.equals(fct001Dto.getTradeSuspendFlag())) {
            // 取引停止口座の場合、エラーメッセージを設定して以降の処理は行わない
            String existMsg = StringUtil.EMPTY_STRING;
            existMsg = getMessage(ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE, new String[] {});
            msgModel.setExistMsg(existMsg);
            msgModel.setExistMsgStatus(ErrorType.ERROR.getLabel());
            csvModel.setCheckResult(ErrorType.ERROR.getLabel());
            return;
        }
        
        // 取引コース媒介可否チェックを行う。(FCT003) 
        OutputFct003Dto fct003Dto = new OutputFct003Dto();
        fct003Dto = ApiRequestUtil.invoke("comIpopoBBApplyUploadService", "getFct003",
                new TypeReference<OutputFct003Dto>() {
                }, butenCode, accountNumber);
        
        if (FCT003_NOT_TRADEABLE.equals(fct003Dto.getMediateAbleTradeFlag())) {
            // 取引不可の場合、エラーメッセージを設定して以降の処理は行わない
            String existMsg = StringUtil.EMPTY_STRING;
            String targetTradeKbn = StringUtil.EMPTY_STRING;
            // 区分値変換（区分.対象取引（メッセージ表示用） - 7:BB申込）
            targetTradeKbn = ApiRequestUtil.invoke("codeListService", "getValue", new TypeReference<String>() {
            }, CODE_LIST_ID, BB_APPLY, DISPLAY_PATTERN);
            existMsg = getMessage(ERRORS_SELECTED_ACCOUNT_COURSE_UNAVAILABLE, new String[] { targetTradeKbn });
            msgModel.setExistMsg(existMsg);
            msgModel.setExistMsgStatus(ErrorType.ERROR.getLabel());
            csvModel.setCheckResult(ErrorType.ERROR.getLabel());
            return;
        }
        
        // 部店口座存在チェック(入力チェックの部店口座結果がOKの場合、実行する。)
        if (StringUtil.isNullOrEmpty(msgModel.getButenCode())
                && StringUtil.isNullOrEmpty(msgModel.getAccountNumber())) {
            // 顧客情報を取得する
            IpopoBBCustomerInfoModel ipopoBbCustomerInfoModel = ApiRequestUtil.invoke("comIpopoBBApplyUploadService",
                    "getIpopoBBCustomerInfo", new TypeReference<IpopoBBCustomerInfoModel>() {
                    }, butenCode, accountNumber);
            
            String existMsg = StringUtil.EMPTY_STRING;
            
            if (ipopoBbCustomerInfoModel == null) {
                existMsg = StringUtils.replace(
                        getMessage(ERRORS_BUTEN_ACCOUNT_NOT_EXIST, new String[] { butenCode, accountNumber }),
                        REPLACE_TAG_BR, LF);
                msgModel.setExistMsg(existMsg);
                msgModel.setExistMsgStatus(ErrorType.ERROR.getLabel());
                csvModel.setCheckResult(ErrorType.ERROR.getLabel());
                return;
            }
            // コンプラランクチェックを行う。(FCT006)
            OutputFct006Dto fct006Dto = new OutputFct006Dto();
            fct006Dto = ApiRequestUtil.invoke("comIpopoBBApplyUploadService", "getFct006",
                    new TypeReference<OutputFct006Dto>() {
                    }, butenCode, accountNumber, brandCode, kanyuKbn, receiveOrderType);
            
            if (FCT006_NORMAL.equals(fct006Dto.getJudgementResult())) {
                // 判定結果がノーマルの場合、次のチェックへ
            } else if (FCT006_ALERT.equals(fct006Dto.getJudgementResult())) {
                // 判定結果がアラートの場合、ワーニングメッセージを設定して次のチェックへ
                msgModel.setExistMsg(getMessage(fct006Dto.getMessageId(), new String[] {}));
                msgModel.setExistMsgStatus(ErrorType.WARNING.getLabel());
            } else if (FCT006_ERROR.equals(fct006Dto.getJudgementResult())) {
                // 判定結果がエラーの場合、エラーメッセージを設定して以降の処理は行わない
                msgModel.setExistMsg(getMessage(fct006Dto.getMessageId(), new String[] {}));
                msgModel.setExistMsgStatus(ErrorType.ERROR.getLabel());
                csvModel.setCheckResult(ErrorType.ERROR.getLabel());
                return;
            } else {
                // 判定結果が上記以外の場合、エラーメッセージを設定して以降の処理は行わない
                msgModel.setExistMsg(getMessage(fct006Dto.getMessageId(), new String[] {}));
                msgModel.setExistMsgStatus(ErrorType.ERROR.getLabel());
                csvModel.setCheckResult(ErrorType.ERROR.getLabel());
                return;
            }
            
            // 画面リストに顧客名を設定する
            csvModel.setCustomerName(ipopoBbCustomerInfoModel.getNameKanji());
            // 画面リストに扱者番号を設定する
            csvModel.setDealerNumber(ipopoBbCustomerInfoModel.getDealerNumber());
            // 画面リストに顧客名_姓(漢字)を設定する
            csvModel.setUaiFamilyNameKanji(ipopoBbCustomerInfoModel.getUaiFamilyNameKanji());
            // 画面リストに顧客名_名(漢字)を設定する
            csvModel.setUaiNameKanji(ipopoBbCustomerInfoModel.getUaiNameKanji());
            // 画面リストに顧客名_姓(カナ)を設定する
            csvModel.setUaiFamilyNameKana(ipopoBbCustomerInfoModel.getUaiFamilyNameKana());
            // 画面リストに顧客名_名(カナ)を設定する
            csvModel.setUaiNameKana(ipopoBbCustomerInfoModel.getUaiNameKana());
            
            // セクションIDとセクション名を検索する
            SectionModel sectionModel = null;
            sectionModel = ApiRequestUtil.invoke("comIpopoBBApplyUploadService", "getSectionInfo",
                    new TypeReference<SectionModel>() {
                    }, butenCode, accountNumber);
            if (sectionModel == null) {
                msgModel.setExistMsg(getMessage(ERRORS_SECTION_ID, new String[] { "セクションID" }));
                msgModel.setExistMsgStatus(ErrorType.ERROR.getLabel());
                csvModel.setCheckResult(ErrorType.ERROR.getLabel());
                return;
            } else {
                // 作成セクションIDと作成セクション名を設定する
                csvModel.setSectionId(sectionModel.getSectionId());
                csvModel.setSectionName(sectionModel.getSectionName());
            }
            
            // 業務チェックを行う。(入力チェックでチェック結果がERRORに設定したら、下記の処理を行わない。)
            if (!ErrorType.ERROR.getLabel().equals(csvModel.getCheckResult())) {
                uploadBusinessCheck(brandCode, butenCode, accountNumber, bbQuantity, bbPrice, bbSeq, quantitySairyou,
                        choseReason, bbRemark, msgModel, csvModel, ipopoBbCustomerInfoModel);
            }
            
        }
    }
    
    /**
    * 業務チェックを行う。
    *
    * @param brandCode {@code String }
    * @param butenCode {@code String }
    * @param accountNumber {@code String }
    * @param bbQuantity {@code String }
    * @param bbPrice {@code String }
    * @param bbSeq {@code String }
    * @param quantitySairyou {@code String }
    * @param choseReason {@code String }
    * @param bbRemark {@code String }
    * @param msgModel {@code IpopoBBApplyUploadCheckResultModel }
    * @param csvModel {@code IpopoUploadBBCustomerInfoModel }
    * @param ipopoBBCustomerInfoModel {@code IpopoBBCustomerInfoModel }
    * @return {@code void}
    * @throws Exception 業務チェック処理で例外が発生した場合
    */
    private void uploadBusinessCheck(String brandCode, String butenCode, String accountNumber, String bbQuantity,
            String bbPrice, String bbSeq, String quantitySairyou, String choseReason, String bbRemark,
            IpopoBBApplyUploadCheckResultModel msgModel, IpopoUploadBBCustomerInfoModel csvModel,
            IpopoBBCustomerInfoModel ipopoBbCustomerInfoModel) throws Exception {
        
        // 業務チェック用なデータを検索する
        IpopoBBApplyUploadModel model = new IpopoBBApplyUploadModel();
        model = ApiRequestUtil.invoke("comIpopoBBApplyUploadService", "getIpopoBBBrandInfo",
                new TypeReference<IpopoBBApplyUploadModel>() {
                }, brandCode);
        String brandMsg = StringUtil.EMPTY_STRING;
        // 銘柄コード関連チェック
        // 銘柄存在チェック、modelがNULLの場合該当銘柄は不存在とする
        if (model != null) {
            csvModel.setBrandName(model.getBrandName());
            csvModel.setBbGestureValue(model.getBbGestureValue());
            csvModel.setBbPresentationFrom(model.getBbPresentationFrom());
            // IFA・BB申込期間の入力チェックを行う(入力しなければ、BB申込できません)
            if (model.getIfaBbPresentationFrom() == null || model.getIfaBbPresentationTo() == null) {
                msgModel.setBrandCode(getMessage(ERRORS_PERIOD_INPUT_CHECK, new String[] { "IFA・BB申込期間", "BB申込" }));
                msgModel.setBrandCodeStatus(ErrorType.ERROR.getLabel());
                csvModel.setCheckResult(ErrorType.ERROR.getLabel());
                return;
            }
        } else {
            brandMsg = getMessage(ERRORS_BRANDCODE_NOT_EXIST_OR_OVER_TIME, new String[] { brandCode });
            msgModel.setBrandCode(StringUtils.replace(brandMsg, REPLACE_TAG_BR, LF));
            msgModel.setBrandCodeStatus(ErrorType.ERROR.getLabel());
            csvModel.setCheckResult(ErrorType.ERROR.getLabel());
            return;
        }
        
        // 緊急入力停止チェックを行う
        if (BBUrgentStop.ON.getId().equals(model.getBbUrgentStop())) {
            csvModel.setCheckResult(ErrorType.ERROR.getLabel());
            msgModel.setExistMsg(getMessage(ERRORS_URGENT_STOP_CHECK, new String[] { "BB申込" }));
            msgModel.setExistMsgStatus(ErrorType.ERROR.getLabel());
            return;
        }
        
        // 仮条件チェックを行う
        boolean brandInfoCheckResult = brandInfoCheck(model.getBbGestureValue(), model.getBbPriceFrom(),
                model.getBbPriceTo(), model.getBbPriceCut(), model.getBbDiscountFrom(), model.getBbDiscountTo(),
                model.getBbDiscountCut());
        if (brandInfoCheckResult) {
            csvModel.setCheckResult(ErrorType.ERROR.getLabel());
            msgModel.setExistMsg(getMessage(ERRORS_SECTION_ID, new String[] { "価格またはディスカウント率" }));
            msgModel.setExistMsgStatus(ErrorType.ERROR.getLabel());
            return;
        }
        
        // 電子交付同意書の同意チェックを行う
        IpopoBBApplyEdelivAgreementModel edelivModel = new IpopoBBApplyEdelivAgreementModel();
        if (EdelivOnlyFlag.EDELIV_ONLY.getId().equals(model.getEdelivOnlyFlag())) {
            edelivModel = ApiRequestUtil.invoke("comIpopoBBApplyUploadService", "getEdelivAgreementInfo",
                    new TypeReference<IpopoBBApplyEdelivAgreementModel>() {
                    }, butenCode, accountNumber);
            if (edelivModel == null) {
                csvModel.setCheckResult(ErrorType.ERROR.getLabel());
                msgModel.setExistMsg(getMessage(ERRORS_ONLY_EDELIV_AGREEMENT_CHECK, new String[] {}));
                msgModel.setExistMsgStatus(ErrorType.ERROR.getLabel());
                return;
            } else if (StringUtil.isNullOrEmpty(edelivModel.getEdelivAgreementDate())) {
                csvModel.setCheckResult(ErrorType.ERROR.getLabel());
                msgModel.setExistMsg(getMessage(ERRORS_ONLY_EDELIV_AGREEMENT_CHECK, new String[] {}));
                msgModel.setExistMsgStatus(ErrorType.ERROR.getLabel());
                return;
            }
        }
        
        // 銘柄コードチェック成功のみ、下記の処理を行う。
        // 登録済みチェックを行う。
        String bbPresentationFromYmd = DateUtil.format(model.getBbPresentationFrom(), DateUtil.YYYYMMDD);
        int bbCount = ApiRequestUtil.invoke("comIpopoBBApplyUploadService", "getBBAcceptInfoCount",
                new TypeReference<Integer>() {
                }, brandCode, bbPresentationFromYmd, butenCode, accountNumber);
        if (bbCount > 0) {
            csvModel.setCheckResult(ErrorType.OTHERS_DATA_EXIST.getLabel());
            // 登録済みの場合、下記の処理を行わない
            return;
        }
        
        // 希望株数チェック（入力チェックの希望株数結果がOKの場合、実行する。）
        if (StringUtil.isNullOrEmpty(msgModel.getBbQuantity())) {
            // 希望株数/売買単位の余数は0以外の場合、エラー
            if (!BigDecimal.ZERO.equals(new BigDecimal(bbQuantity).remainder(new BigDecimal(model.getBbStock())))) {
                msgModel.setBbQuantity(getMessage(ERRORS_UNIT_CHECK,
                        new String[] { "希望株数", model.getBbStock(), model.getBbUnitKbn() }));
                msgModel.setBbQuantityStatus(ErrorType.ERROR.getLabel());
            }
        }
        
        // 申込価格（入力チェックの申込価格結果がOKの場合、実行する。）
        if (StringUtil.isNullOrEmpty(msgModel.getBbPrice())) {
            bbPriceCheck(bbPrice, msgModel, model);
        }
        
        // 年間裁量配分チェック(申込価格チェック結果がOKの場合、実行する。)
        if (!StringUtil.isNullOrEmpty(quantitySairyou) && StringUtil.isNullOrEmpty(msgModel.getBbPrice())) {
            sairyouCheck(brandCode, butenCode, accountNumber, bbPrice, msgModel, model);
        }
        
        // 裁量希望株数チェック（入力チェックの裁量希望株数結果がOKの場合、実行する。）
        if (StringUtil.isNullOrEmpty(msgModel.getQuantitySairyou()) && !StringUtil.isNullOrEmpty(quantitySairyou)) {
            quantitySairyouCheck(quantitySairyou, msgModel, model);
        }
        
        // 投資家属性チェック（入力チェックの投資家属性結果がOKの場合、実行する。）
        if (StringUtil.isNullOrEmpty(msgModel.getBbSeq())) {
            bbSeqCheck(bbSeq, csvModel, model, msgModel);
        }
        
        // 登録金融資産が3,000万円以上チェック
        if (!StringUtil.isNullOrEmpty(quantitySairyou)) {
            kinyuShysanCheck(ipopoBbCustomerInfoModel, msgModel);
        }
        
        // チェック結果を設定する
        // コンプラランクチェック(ワーニングの可能性があり)
        String existMsgStatus = msgModel.getExistMsgStatus();
        // 希望株数
        String bbQuantityStatus = msgModel.getBbQuantityStatus();
        // 申込価格
        String bbpriceStatus = msgModel.getBbPriceStatus();
        // 裁量配分チェック(ワーニングの可能性があり)
        String sairyouStatus = msgModel.getSairyouStatus();
        // 裁量希望株数
        String quantitySairyouStatus = msgModel.getQuantitySairyouStatus();
        // 投資家属性
        String bbSeqStatus = msgModel.getBbSeqStatus();
        // 登録金融資産(ワーニングの可能性があり)
        String financialAssetsStatus = msgModel.getFinancialAssetsStatus();
        
        if (ErrorType.ERROR.getLabel().equals(existMsgStatus) || ErrorType.ERROR.getLabel().equals(bbQuantityStatus)
                || ErrorType.ERROR.getLabel().equals(bbpriceStatus) || ErrorType.ERROR.getLabel().equals(sairyouStatus)
                || ErrorType.ERROR.getLabel().equals(quantitySairyouStatus)
                || ErrorType.ERROR.getLabel().equals(bbSeqStatus)
                || ErrorType.ERROR.getLabel().equals(financialAssetsStatus)) {
            // エラーが1つでもある場合、ERRORとする
            csvModel.setCheckResult(ErrorType.ERROR.getLabel());
            return;
        } else if (ErrorType.WARNING.getLabel().equals(existMsgStatus)
                || ErrorType.WARNING.getLabel().equals(sairyouStatus)
                || ErrorType.WARNING.getLabel().equals(financialAssetsStatus)) {
            // コンプラランクチェック または 裁量配分割当回数5回 または 投資家属性がワーニングの場合
            csvModel.setCheckResult(ErrorType.WARNING.getLabel());
            return;
        } else {
            csvModel.setCheckResult(ErrorType.OK.getLabel());
        }
    }
    
    /**
    * 仮条件チェックを行う
    *
    * @param bbGestureValue {@code String }
    * @param bbPriceFrom {@code BigDecimal }
    * @param bbPriceTo {@code BigDecimal }
    * @param bbPriceCut {@code BigDecimal }
    * @param bbDiscountFrom {@code BigDecimal }
    * @param bbDiscountTo {@code BigDecimal }
    * @param bbDiscountCut {@code BigDecimal }
    * @return {@code boolean}
    */
    private boolean brandInfoCheck(String bbGestureValue, BigDecimal bbPriceFrom, BigDecimal bbPriceTo,
            BigDecimal bbPriceCut, BigDecimal bbDiscountFrom, BigDecimal bbDiscountTo, BigDecimal bbDiscountCut) {
        
        // 下記の項目はNULLがだったら、TRUEを設定する
        // 価格表示（開始）
        boolean bbPriceFromIsNull = bbPriceFrom == null;
        // 価格表示（終了）
        boolean bbPriceToIsNull = bbPriceTo == null;
        // 価格表示（刻み）
        boolean bbPriceCutIsNull = bbPriceCut == null;
        // ディスカウント率（開始）
        boolean bbDiscountFromIsNull = bbDiscountFrom == null;
        // ディスカウント率（終了）
        boolean bbDiscountToIsNull = bbDiscountTo == null;
        // ディスカウント率（刻み）
        boolean bbDiscountCutIsNull = bbDiscountCut == null;
        
        if (BBGestureValue.PRICE.getId().equals(bbGestureValue)) {
            // 発行価格区分は価格表示であり、価格表示（開始）か価格表示（終了）か価格表示（刻み）は空の場合
            if (bbPriceFromIsNull || bbPriceToIsNull || bbPriceCutIsNull) {
                return true;
            }
        } else {
            // 発行価格区分はディスカウント率であり、ディスカウント率（開始）かディスカウント率（終了）かディスカウント率（刻み）は空の場合
            if (bbDiscountFromIsNull || bbDiscountToIsNull || bbDiscountCutIsNull) {
                return true;
            }
        }
        return false;
    }
    
    /**
    * 登録金融資産が3,000万円以上チェック(個人のみ)
    *
    * @param ipopoBBCustomerInfoModel {@code IpopoBBCustomerInfoModel }
    * @param msgModel {@code IpopoBBApplyUploadCheckResultModel }
    * @return {@code void}
    */
    private void kinyuShysanCheck(IpopoBBCustomerInfoModel ipopoBbCustomerInfoModel,
            IpopoBBApplyUploadCheckResultModel msgModel) {
        
        // 法人区分は0の場合、個人とする
        boolean individualFlg = CorporationKbn.INDIVIDUAL.getId()
                .equals(ipopoBbCustomerInfoModel.getUaiCorporationKbn());
        
        if (individualFlg) {
            // 金融資産区分が06,07,08,99の場合
            boolean overFlg = !FinancialAssets.THIRTY_MILLION_TO_FIFTY_MILLION.getId()
                    .equals(ipopoBbCustomerInfoModel.getUaiQaFinancialAssets())
                    && !FinancialAssets.FIVE_MILLION_TO_ONE_HUNDRED_MILLION.getId()
                            .equals(ipopoBbCustomerInfoModel.getUaiQaFinancialAssets())
                    && !FinancialAssets.ONE_HUNDRED_MILLION_TO_FIVE_HUNDRED_MILLION.getId()
                            .equals(ipopoBbCustomerInfoModel.getUaiQaFinancialAssets())
                    && !FinancialAssets.OVER_FIVE_HUNDRED_MILLION.getId()
                            .equals(ipopoBbCustomerInfoModel.getUaiQaFinancialAssets());
            // 金融資産区分が06,07,08,99以外の場合、3000万未満
            if (overFlg) {
                msgModel.setFinancialAssets((getMessage(ERRORS_FINANCIAL_ASSETS_CHECK, new String[] {})));
                msgModel.setFinancialAssetsStatus(ErrorType.WARNING.getLabel());
            }
        }
    }
    
    /**
    * 年間裁量配分チェック
    *
    * @param brandCode {@code String }
    * @param butenCode {@code String }
    * @param accountNumber {@code String }
    * @param bbPrice {@code String }
    * @param msgModel {@code IpopoBBApplyUploadCheckResultModel }
    * @param model {@code IpopoBBApplyUploadModel }
    * @return {@code void}
    * @throws Exception 業務チェック処理で例外が発生した場合
    */
    private void sairyouCheck(String brandCode, String butenCode, String accountNumber, String bbPrice,
            IpopoBBApplyUploadCheckResultModel msgModel, IpopoBBApplyUploadModel model) throws Exception {
        
        // INDIGOでエラーのデータがあるの対応
        String bbDiscountFrom = null;
        if (model.getBbDiscountFrom() != null) {
            bbDiscountFrom = model.getBbDiscountFrom().toString();
        }
        String bbPriceTo = null;
        if (model.getBbPriceTo() != null) {
            bbPriceTo = model.getBbPriceTo().toString();
        }
        // 年間裁量配分割当回数チェック用データを検索する
        IpopoUploadBBCustomerOverMaxCheckModel overMaxCheckModel = new IpopoUploadBBCustomerOverMaxCheckModel();
        overMaxCheckModel = ApiRequestUtil.invoke("comIpopoBBApplyUploadService", "getOverMaxSairyouCount",
                new TypeReference<IpopoUploadBBCustomerOverMaxCheckModel>() {
                }, butenCode, accountNumber, null);
        
        String bbPresentationFromYmd = DateUtil.format(model.getBbPresentationFrom(), DateUtil.YYYYMMDD);
        
        // 年間裁量配分割当回数上限チェック
        String bbGestureValue = model.getBbGestureValue();
        String bbIpoPoKbn = model.getBbIpoPoKbn();
        boolean overMaxFlg = toCheckOverMaxSairyouCount(brandCode, butenCode, accountNumber, bbPresentationFromYmd,
                bbPrice, bbPriceTo, bbDiscountFrom, overMaxCheckModel, bbGestureValue, bbIpoPoKbn, msgModel);
        
        if (overMaxFlg) {
            return;
        }
        // 裁量配分割当回数5回以上ワーニングチェック。
        // 上限チェックした後で、5回以上チェックをしない
        if (BBIpoPoKbn.IPO.getId().equals(model.getBbIpoPoKbn())) {
            boolean sairyouAlloCountFlg = toCheckSairyouAlloCountOver(overMaxCheckModel);
            
            if (sairyouAlloCountFlg) {
                msgModel.setSairyou(getMessage(ERRORS_ABOVE_LIMIT, new String[] { "裁量配分割当回数", "5回" }));
                msgModel.setSairyouStatus(ErrorType.WARNING.getLabel());
                return;
            } else {
                return;
            }
        } else {
            return;
        }
        
    }
    
    /**
    * 裁量配分割当回数5回以上ワーニングチェック。
    *
    * @param overMaxCheckModel {@code IpopoUploadBBCustomerOverMaxCheckModel }
    * @return {@code boolean}
    */
    private boolean toCheckSairyouAlloCountOver(IpopoUploadBBCustomerOverMaxCheckModel overMaxCheckModel) {
        
        if (overMaxCheckModel != null) {
            int sairyouAlloCount = 0;
            sairyouAlloCount = overMaxCheckModel.getSairyouAlloCountTotal();
            if (sairyouAlloCount >= 5) {
                return true;
            }
        }
        return false;
    }
    
    /**
    * 年間裁量配分割当回数上限チェック
    *
    * @param brandCode {@code String }
    * @param butenCode {@code String }
    * @param accountNumber {@code String }
    * @param bbPresentationFromYmd {@code String }
    * @param bbPrice {@code String }
    * @param bbPriceTo {@code String }
    * @param bbDiscountFrom {@code String }
    * @param overMaxCheckModel {@code IpopoUploadBBCustomerOverMaxCheckModel }
    * @param bbGestureValue {@code String }
    * @param bbIpoPoKbn {@code String }
    * @param msgModel {@code IpopoBBApplyUploadCheckResultModel }
    * @return {@code boolean}
    */
    private boolean toCheckOverMaxSairyouCount(String brandCode, String butenCode, String accountNumber,
            String bbPresentationFromYmd, String bbPrice, String bbPriceTo, String bbDiscountFrom,
            IpopoUploadBBCustomerOverMaxCheckModel overMaxCheckModel, String bbGestureValue, String bbIpoPoKbn,
            IpopoBBApplyUploadCheckResultModel msgModel) {
        
        if (overMaxCheckModel != null) {
            int maybeSairyouCount = overMaxCheckModel.getMaybeSairyouCount();
            // IPOの場合
            if (BBIpoPoKbn.IPO.getId().equals(bbIpoPoKbn)) {
                // 成行の場合
                if (TradeMethod.NARIYUKI.getLabel().equals(bbPrice)) {
                    // 上限超過
                    if (maybeSairyouCount <= 0) {
                        msgModel.setSairyou(getMessage(ERRORS_IPO_OVER_LIMIT, new String[] { "裁量回数", "年間裁量配分割当回数上限" }));
                        msgModel.setSairyouStatus(ErrorType.ERROR.getLabel());
                        return true;
                    } else {
                        // 上限未超過
                        return false;
                    }
                }
                // 成行以外の場合、価格表示とディスカウント率によって、判断する
                if (BBGestureValue.PRICE.getId().equals(bbGestureValue)) {
                    // 価格表示の場合
                    // 価格（申込） < 価格表示（終了）
                    if (new BigDecimal(bbPrice).compareTo(new BigDecimal(bbPriceTo)) == -1) {
                        msgModel.setSairyou(getMessage(ERRORS_SELECTED, new String[] { "裁量希望の場合、価格の最大値" }));
                        msgModel.setSairyouStatus(ErrorType.ERROR.getLabel());
                        return true;
                        // 価格（申込） = 価格表示（終了）
                    } else if (new BigDecimal(bbPrice).compareTo(new BigDecimal(bbPriceTo)) == 0) {
                        if (maybeSairyouCount <= 0) {
                            // 上限超過
                            msgModel.setSairyou(
                                    getMessage(ERRORS_IPO_OVER_LIMIT, new String[] { "裁量回数", "年間裁量配分割当回数上限" }));
                            msgModel.setSairyouStatus(ErrorType.ERROR.getLabel());
                            return true;
                        } else {
                            // 上限未超過
                            return false;
                        }
                    }
                } else {
                    // ディスカウント率の場合
                    if (maybeSairyouCount <= 0) {
                        // 上限超過
                        msgModel.setSairyou(getMessage(ERRORS_IPO_OVER_LIMIT, new String[] { "裁量回数", "年間裁量配分割当回数上限" }));
                        msgModel.setSairyouStatus(ErrorType.ERROR.getLabel());
                        return true;
                    } else {
                        // 上限未超過
                        return false;
                    }
                    
                }
            } else {
                if (TradeMethod.NARIYUKI.getLabel().equals(bbPrice)) {
                    return false;
                }
                if (BBGestureValue.PRICE.getId().equals(bbGestureValue)) {
                    return false;
                } else {
                    // ディスカウント率（申込） > ディスカウント率（開始）
                    if (new BigDecimal(bbPrice).compareTo(new BigDecimal(bbDiscountFrom)) == 1) {
                        msgModel.setSairyou(getMessage(ERRORS_SELECTED, new String[] { "裁量希望の場合、ディスカウント率の最小値" }));
                        msgModel.setSairyouStatus(ErrorType.ERROR.getLabel());
                        return true;
                    } else if (new BigDecimal(bbPrice).compareTo(new BigDecimal(bbDiscountFrom)) == 0) {
                        return false;
                    }
                }
                
            }
        }
        return false;
    }
    
    /**
    * 申込価格チェック
    *
    * @param bbPrice {@code String }
    * @param msgModel {@code IpopoBBApplyUploadCheckResultModel }
    * @param model {@code IpopoBBApplyUploadModel }
    * @return {@code void}
    * @throws Exception 申込価格チェック処理で例外が発生した場合
    */
    private void bbPriceCheck(String bbPrice, IpopoBBApplyUploadCheckResultModel msgModel,
            IpopoBBApplyUploadModel model) throws Exception {
        
        // 成行（ストライクプライス）が'0'(0:成行なし)の場合
        if (BBStrikePrice.NARIYUKI_NASHI.getId().equals(model.getBbStrikePrice())) {
            // 成行を入力するの場合
            if (TradeMethod.NARIYUKI.getLabel().equals(bbPrice)) {
                msgModel.setBbPrice(getMessage(ERRORS_APPLICATION_PRICE_INSERT, new String[] {}));
                msgModel.setBbPriceStatus(ErrorType.ERROR.getLabel());
                return;
            } else if (BBGestureValue.PRICE.getId().equals(model.getBbGestureValue())) {
                // "成行"以外を入力して、発行価格区分が１の場合（1:価格表示）
                // 半角数字チェック
                if (!isNumber(bbPrice)) {
                    msgModel.setBbPrice(getMessage(ERRORS_TYPE, new String[] { "申込価格", "半角数字" }));
                    msgModel.setBbPriceStatus(ErrorType.ERROR.getLabel());
                    return;
                }
                // 範囲チェック
                if (new BigDecimal(bbPrice).compareTo(model.getBbPriceFrom()) == -1
                        || new BigDecimal(bbPrice).compareTo(model.getBbPriceTo()) == 1) {
                    msgModel.setBbPrice(getMessage(ERRORS_APPLICATION_PRICE_RANGE,
                            new String[] { model.getBbPriceFrom().toString(), model.getBbPriceTo().toString(), "数字" }));
                    msgModel.setBbPriceStatus(ErrorType.ERROR.getLabel());
                    return;
                } else if (new BigDecimal(bbPrice).compareTo(model.getBbPriceFrom()) == 0
                        || new BigDecimal(bbPrice).compareTo(model.getBbPriceTo()) == 0) {
                    return;
                }
                // 申込単位外チェック、価格表示（刻み）があるの場合のみ
                if (model.getBbPriceCut() != null) {
                    BigDecimal minusBbPrice = new BigDecimal(bbPrice).subtract(model.getBbPriceFrom());
                    if (!BigDecimal.ZERO.equals(minusBbPrice.remainder(model.getBbPriceCut()))) {
                        msgModel.setBbPrice(getMessage(ERRORS_APPLICATION_PRICE_UNIT,
                                new String[] { model.getBbPriceCut().toString() }));
                        msgModel.setBbPriceStatus(ErrorType.ERROR.getLabel());
                        return;
                    }
                    
                }
                
                // "成行"以外を入力して、O.発行価格区分が2の場合（2:ディスカウント率）
            } else if (BBGestureValue.DISCOUNT.getId().equals(model.getBbGestureValue())) {
                // フォーマットチェック
                Pattern fourDecimalCheck = Pattern.compile("^[0-9]{1,3}+(\\.[0-9]{1,4})?$"); // 半角小数4桁
                Matcher lastPriceDecimalCheck = fourDecimalCheck.matcher(bbPrice);
                if (!lastPriceDecimalCheck.matches()) {
                    msgModel.setBbPrice(getMessage(ERRORS_NUMBER_FORMAT, new String[] { "申込価格", "999.9999" }));
                    msgModel.setBbPriceStatus(ErrorType.ERROR.getLabel());
                    return;
                }
                // 範囲チェック
                if (new BigDecimal(bbPrice).compareTo(model.getBbDiscountFrom()) == -1
                        || new BigDecimal(bbPrice).compareTo(model.getBbDiscountTo()) == 1) {
                    msgModel.setBbPrice(getMessage(ERRORS_APPLICATION_PRICE_RANGE, new String[] {
                            model.getBbDiscountFrom().toString(), model.getBbDiscountTo().toString(), "小数" }));
                    msgModel.setBbPriceStatus(ErrorType.ERROR.getLabel());
                    return;
                } else if (new BigDecimal(bbPrice).compareTo(model.getBbDiscountFrom()) == 0
                        || new BigDecimal(bbPrice).compareTo(model.getBbDiscountTo()) == 0) {
                    return;
                }
                
                // 申込単位外チェック
                if (model.getBbDiscountCut() != null) {
                    BigDecimal minusDiscount = new BigDecimal(bbPrice).subtract(model.getBbDiscountFrom());
                    if (BigDecimal.ZERO.compareTo(minusDiscount.remainder(model.getBbDiscountCut())) != 0) {
                        msgModel.setBbPrice(getMessage(ERRORS_APPLICATION_PRICE_UNIT,
                                new String[] { model.getBbDiscountCut().toString() }));
                        msgModel.setBbPriceStatus(ErrorType.ERROR.getLabel());
                        return;
                    }
                }
                
            }
            // O.成行（ストライクプライス）が'1'(1:成行あり)であり、発行価格は成行ではないの場合、
        } else if (BBStrikePrice.NARIYUKI_ARI.getId().equals(model.getBbStrikePrice())
                && !TradeMethod.NARIYUKI.getLabel().equals(bbPrice)) {
            // 発行価格区分が１の場合（1:価格表示）
            if (BBGestureValue.PRICE.getId().equals(model.getBbGestureValue())) {
                // 半角数字チェック
                if (!isNumber(bbPrice)) {
                    msgModel.setBbPrice(getMessage(ERRORS_TYPE, new String[] { "申込価格", "半角数字または\"成行\"" }));
                    msgModel.setBbPriceStatus(ErrorType.ERROR.getLabel());
                    return;
                }
                // 範囲チェック
                if (new BigDecimal(bbPrice).compareTo(model.getBbPriceFrom()) == -1
                        || new BigDecimal(bbPrice).compareTo(model.getBbPriceTo()) == 1) {
                    msgModel.setBbPrice(getMessage(ERRORS_DISCOUNT_RANGE_CHECK, new String[] {
                            model.getBbPriceFrom().toString(), model.getBbPriceTo().toString(), "数字または\"成行\"" }));
                    msgModel.setBbPriceStatus(ErrorType.ERROR.getLabel());
                    return;
                } else if (new BigDecimal(bbPrice).compareTo(model.getBbPriceFrom()) == 0
                        || new BigDecimal(bbPrice).compareTo(model.getBbPriceTo()) == 0) {
                    return;
                }
                // 申込単位外チェック
                BigDecimal minusBbPrice = new BigDecimal(bbPrice).subtract(model.getBbPriceFrom());
                if (!BigDecimal.ZERO.equals(minusBbPrice.remainder(model.getBbPriceCut()))) {
                    msgModel.setBbPrice(
                            getMessage(ERRORS_DISCOUNT_UNIT_CHECK, new String[] { model.getBbPriceCut().toString() }));
                    msgModel.setBbPriceStatus(ErrorType.ERROR.getLabel());
                    return;
                }
                
            } else {
                // フォーマットチェック
                Pattern fourDecimalCheck = Pattern.compile("^[0-9]{1,3}+(\\.[0-9]{1,4})?$"); // 半角小数4桁
                Matcher lastPriceDecimalCheck = fourDecimalCheck.matcher(bbPrice);
                if (!lastPriceDecimalCheck.matches()) {
                    msgModel.setBbPrice(
                            getMessage(ERRORS_NUMBER_FORMAT_FOR_NARIYUKI, new String[] { "申込価格", "999.9999" }));
                    msgModel.setBbPriceStatus(ErrorType.ERROR.getLabel());
                    return;
                }
                // 範囲チェック
                if (new BigDecimal(bbPrice).compareTo(model.getBbDiscountFrom()) == -1
                        || new BigDecimal(bbPrice).compareTo(model.getBbDiscountTo()) == 1) {
                    msgModel.setBbPrice(getMessage(ERRORS_DISCOUNT_RANGE_CHECK, new String[] {
                            model.getBbDiscountFrom().toString(), model.getBbDiscountTo().toString(), "数字または\"成行\"" }));
                    msgModel.setBbPriceStatus(ErrorType.ERROR.getLabel());
                    return;
                } else if (new BigDecimal(bbPrice).compareTo(model.getBbDiscountFrom()) == 0
                        || new BigDecimal(bbPrice).compareTo(model.getBbDiscountTo()) == 0) {
                    return;
                }
                // 申込単位外チェック
                if (model.getBbDiscountCut() != null) {
                    BigDecimal minusDiscount = new BigDecimal(bbPrice).subtract(model.getBbDiscountFrom());
                    
                    if (BigDecimal.ZERO.compareTo(minusDiscount.remainder(model.getBbDiscountCut())) != 0) {
                        msgModel.setBbPrice(getMessage(ERRORS_DISCOUNT_UNIT_CHECK,
                                new String[] { model.getBbDiscountCut().toString() }));
                        msgModel.setBbPriceStatus(ErrorType.ERROR.getLabel());
                        return;
                    }
                }
                
            }
        }
    }
    
    /**
    * 裁量希望株数チェック
    *
    * @param quantitySairyou {@code String }
    * @param msgModel {@code IpopoBBApplyUploadCheckResultModel }
    * @param model {@code IpopoBBApplyUploadModel }
    * @return {@code void}
    */
    private void quantitySairyouCheck(String quantitySairyou, IpopoBBApplyUploadCheckResultModel msgModel,
            IpopoBBApplyUploadModel model) {
        
        // 上限チェック
        BigDecimal maxLimit = new BigDecimal(model.getBbStock())
                .multiply(new BigDecimal(model.getMaxAllocationUnits()));
        
        if (new BigDecimal(quantitySairyou).compareTo(maxLimit) == 1) {
            
            String errMsg = StringUtils.replace(getMessage(ERRORS_IPO_TRADE_RANGE,
                    new String[] { "裁量希望株数", "上限数量", "上限数量", String.valueOf(maxLimit) }), REPLACE_TAG, LF);
            msgModel.setQuantitySairyou(errMsg);
            msgModel.setQuantitySairyouStatus(ErrorType.ERROR.getLabel());
            return;
        }
        
        // 裁量希望株数が上限単元数量単位以外の場合、
        // 余数
        BigDecimal remainderres = new BigDecimal(quantitySairyou).remainder(new BigDecimal(model.getBbStock()));
        if (!BigDecimal.ZERO.equals(remainderres)) {
            msgModel.setQuantitySairyou(
                    getMessage(ERRORS_UNIT_CHECK, new String[] { "裁量希望株数", model.getBbStock(), model.getBbUnitKbn() }));
            msgModel.setQuantitySairyouStatus(ErrorType.ERROR.getLabel());
        }
        
    }
    
    /**
    * 投資家属性チェック
    *
    * @param bbSeq {@code String }
    * @param csvModel {@code IpopoUploadBBCustomerInfoModel }
    * @param model {@code IpopoBBApplyUploadModel }
    * @param msgModel {@code IpopoBBApplyUploadCheckResultModel }
    * @return {@code void}
    * @throws Exception 投資家属性チェックで例外が発生した場合
    */
    private void bbSeqCheck(String bbSeq, IpopoUploadBBCustomerInfoModel csvModel, IpopoBBApplyUploadModel model,
            IpopoBBApplyUploadCheckResultModel msgModel) throws Exception {
        
        List<BBInvestorAttModel> bbInvestorAttInfoList = new ArrayList<BBInvestorAttModel>();
        String bbPresentAtionFromYmd = DateUtil.format(model.getBbPresentationFrom(), DateUtil.YYYYMMDD);
        bbInvestorAttInfoList = ApiRequestUtil.invoke("comIpopoBBApplyUploadService", "getBBInvestorAttInfoList",
                new TypeReference<List<BBInvestorAttModel>>() {
                }, csvModel.getBrandCode(), bbPresentAtionFromYmd);
        boolean bbSeqFlg = false;
        
        for (BBInvestorAttModel bbInvestorAttModel : bbInvestorAttInfoList) {
            if (bbSeq.equals(String.valueOf(bbInvestorAttModel.getBbSeq()))) {
                bbSeqFlg = true;
                csvModel.setBbInvestorAttName(bbInvestorAttModel.getBbInvestorAttName());
                break;
            }
        }
        
        // エラーメッセージを作成
        if (!bbSeqFlg) {
            StringBuilder seq = new StringBuilder();
            StringBuilder investorAttName = new StringBuilder();
            for (int i = 0; i < bbInvestorAttInfoList.size(); i++) {
                if (i < bbInvestorAttInfoList.size() - 1) {
                    seq.append(bbInvestorAttInfoList.get(i).getBbSeq()).append("、");
                    investorAttName.append(bbInvestorAttInfoList.get(i).getBbSeq()).append(":")
                            .append(bbInvestorAttInfoList.get(i).getBbInvestorAttName()).append("、");
                } else {
                    seq.append(bbInvestorAttInfoList.get(i).getBbSeq());
                    investorAttName.append(bbInvestorAttInfoList.get(i).getBbSeq()).append(":")
                            .append(bbInvestorAttInfoList.get(i).getBbInvestorAttName());
                }
            }
            String msg = getMessage(ERRORS_INVESTOR_CHECK, new String[] { seq.toString(), investorAttName.toString() });
            msgModel.setBbSeq(StringUtils.replace(msg, "<br/>", LF));
            msgModel.setBbSeqStatus(ErrorType.ERROR.getLabel());
        }
    }
    
    /**
    * 入力チェックを行う。
    *
    * @param brandCode {@code String }
    * @param butenCode {@code String }
    * @param accountNumber {@code String }
    * @param bbQuantity {@code String }
    * @param bbPrice {@code String }
    * @param bbSeq {@code String }
    * @param quantitySairyou {@code String }
    * @param choseReason {@code String }
    * @param bbRemark {@code String }
    * @param kanyuKbn {@code String }
    * @param receiveOrderType {@code String }
    * @param csvModel {@code IpopoUploadBBCustomerInfoModel }
    * @param msgModel {@code String }
    * @return {@code void}
    * @throws Exception 入力チェック処理で例外が発生した場合
    */
    private void uploadValidationCheck(String brandCode, String butenCode, String accountNumber, String bbQuantity,
            String bbPrice, String bbSeq, String quantitySairyou, String choseReason, String bbRemark, String kanyuKbn,
            String receiveOrderType, IpopoUploadBBCustomerInfoModel csvModel,
            IpopoBBApplyUploadCheckResultModel msgModel) throws Exception {
        
        // 下記のチェックはどの項目がエラーがあれば、該当項目の他のチェックは行わない。
        
        // 銘柄コード:必須入力チェック
        if (StringUtil.isNullOrEmpty(brandCode)) {
            msgModel.setBrandCode(getMessage(ERRORS_REQUIRED, new String[] { "銘柄コード" }));
            msgModel.setBrandCodeStatus(ErrorType.ERROR.getLabel());
            // 銘柄コード:桁数チェック
        } else if (StringUtil.isNullOrEmpty(msgModel.getBrandCode()) && brandCode.length() > 5) {
            msgModel.setBrandCode(getMessage(ERRORS_MAX_SIZE, new String[] { "銘柄コード", "5" }));
            msgModel.setBrandCodeStatus(ErrorType.ERROR.getLabel());
            // 銘柄コード:英数字チェック
        } else if (StringUtil.isNullOrEmpty(msgModel.getBrandCode()) && !isAlphaNumber(brandCode)) {
            msgModel.setBrandCode(getMessage(ERRORS_TYPE, new String[] { "銘柄コード", "英数字" }));
            msgModel.setBrandCodeStatus(ErrorType.ERROR.getLabel());
        }
        
        // 部店:必須入力チェック
        if (StringUtil.isNullOrEmpty(butenCode)) {
            msgModel.setButenCode(getMessage(ERRORS_REQUIRED, new String[] { "部店" }));
            msgModel.setButenCodeStatus(ErrorType.ERROR.getLabel());
            // 部店:桁数チェック
        } else if (StringUtil.isNullOrEmpty(msgModel.getButenCode()) && butenCode.length() > 3) {
            msgModel.setButenCode(getMessage(ERRORS_MAX_SIZE, new String[] { "部店", "3" }));
            msgModel.setButenCodeStatus(ErrorType.ERROR.getLabel());
            // 部店:英数字チェック
        } else if (StringUtil.isNullOrEmpty(msgModel.getButenCode()) && !isAlphaNumber(butenCode)) {
            msgModel.setButenCode(getMessage(ERRORS_TYPE, new String[] { "部店", "英数字" }));
            msgModel.setButenCodeStatus(ErrorType.ERROR.getLabel());
        }
        
        // 口座番号:必須入力チェック
        if (StringUtil.isNullOrEmpty(accountNumber)) {
            msgModel.setAccountNumber(getMessage(ERRORS_REQUIRED, new String[] { "口座番号" }));
            msgModel.setAccountNumberStatus(ErrorType.ERROR.getLabel());
            // 口座番号:桁数チェック
        } else if (StringUtil.isNullOrEmpty(msgModel.getAccountNumber()) && accountNumber.length() > 7) {
            msgModel.setAccountNumber(getMessage(ERRORS_MAX_SIZE, new String[] { "口座番号", "7" }));
            msgModel.setAccountNumberStatus(ErrorType.ERROR.getLabel());
            // 口座番号:数字チェック
        } else if (StringUtil.isNullOrEmpty(msgModel.getAccountNumber()) && !isNumber(accountNumber)) {
            msgModel.setAccountNumber(getMessage(ERRORS_TYPE, new String[] { "口座番号", "半角数字" }));
            msgModel.setAccountNumberStatus(ErrorType.ERROR.getLabel());
        }
        
        // 希望株数:必須入力チェック
        if (StringUtil.isNullOrEmpty(bbQuantity)) {
            msgModel.setBbQuantity(getMessage(ERRORS_REQUIRED, new String[] { "希望株数" }));
            msgModel.setBbQuantityStatus(ErrorType.ERROR.getLabel());
            // 希望株数:桁数チェック
        } else if (StringUtil.isNullOrEmpty(msgModel.getBbQuantity()) && bbQuantity.length() > 10) {
            msgModel.setBbQuantity(getMessage(ERRORS_MAX_SIZE, new String[] { "希望株数", "10" }));
            msgModel.setBbQuantityStatus(ErrorType.ERROR.getLabel());
            // 希望株数:英数字チェック
        } else if (StringUtil.isNullOrEmpty(msgModel.getBbQuantity()) && !isNumber(bbQuantity)) {
            msgModel.setBbQuantity(getMessage(ERRORS_TYPE, new String[] { "希望株数", "半角数字" }));
            msgModel.setBbQuantityStatus(ErrorType.ERROR.getLabel());
            // 希望株数:0チェック
        } else if (StringUtil.isNullOrEmpty(msgModel.getBbQuantity())
                && BigDecimal.ZERO.compareTo(new BigDecimal(bbQuantity)) == 0) {
            msgModel.setBbQuantity(getMessage(ERRORS_NUMBER_INSERT_CHECK, new String[] { "希望株数" }));
            msgModel.setBbQuantityStatus(ErrorType.ERROR.getLabel());
        }
        
        // 申込価格:必須入力チェック
        if (StringUtil.isNullOrEmpty(bbPrice)) {
            msgModel.setBbPrice(getMessage(ERRORS_REQUIRED, new String[] { "申込価格" }));
            msgModel.setBbPriceStatus(ErrorType.ERROR.getLabel());
        }
        
        // 投資家属性:必須入力チェック
        if (StringUtil.isNullOrEmpty(bbSeq)) {
            msgModel.setBbSeq(getMessage(ERRORS_REQUIRED, new String[] { "投資家属性" }));
            msgModel.setBbSeqStatus(ErrorType.ERROR.getLabel());
        }
        
        // 裁量希望株数は必須入力ではないので、裁量希望株数は非空の場合、裁量希望株数チェックを行う。
        if (!StringUtil.isNullOrEmpty(quantitySairyou)) {
            // 半角数字のみチェック
            if (!isNumber(quantitySairyou)) {
                msgModel.setQuantitySairyou(getMessage(ERRORS_TYPE, new String[] { "裁量希望株数", "半角数字" }));
                msgModel.setQuantitySairyouStatus(ErrorType.ERROR.getLabel());
                // 0チェック
            } else if (StringUtil.isNullOrEmpty(msgModel.getQuantitySairyou())
                    && BigDecimal.ZERO.compareTo(new BigDecimal(quantitySairyou)) == 0) {
                msgModel.setQuantitySairyou(getMessage(ERRORS_NUMBER_INSERT_CHECK, new String[] { "裁量希望株数" }));
                msgModel.setQuantitySairyouStatus(ErrorType.ERROR.getLabel());
                // 桁数チェック
            } else if (StringUtil.isNullOrEmpty(msgModel.getQuantitySairyou()) && quantitySairyou.length() > 10) {
                msgModel.setQuantitySairyou(getMessage(ERRORS_MAX_SIZE, new String[] { "裁量希望株数", "10" }));
                msgModel.setQuantitySairyouStatus(ErrorType.ERROR.getLabel());
                // 希望株数以下チェック
            } else if (StringUtil.isNullOrEmpty(msgModel.getBbQuantity())
                    && StringUtil.isNullOrEmpty(msgModel.getQuantitySairyou())
                    && new BigDecimal(quantitySairyou).compareTo(new BigDecimal(bbQuantity)) == 1) {
                msgModel.setQuantitySairyou(getMessage(ERRORS_TRADE_RANGE, new String[] { "裁量希望株数", "希望株数" }));
                msgModel.setQuantitySairyouStatus(ErrorType.ERROR.getLabel());
            }
        }
        
        // 裁量希望株数は非空かつ裁量希望株数チェック成功の場合、裁量選定理由チェックを行う。
        if (!StringUtil.isNullOrEmpty(quantitySairyou) && StringUtil.isNullOrEmpty(msgModel.getQuantitySairyou())) {
            // 裁量申込時は裁量選定理由を入力してください。
            if (StringUtil.isNullOrEmpty(choseReason)) {
                msgModel.setChoseReason(getMessage(ERRORS_REQUIRED, new String[] { "裁量申込時は裁量選定理由" }));
                msgModel.setChoseReasonStatus(ErrorType.ERROR.getLabel());
            } else if (StringUtil.isNullOrEmpty(msgModel.getChoseReason())) {
                // 正確性チェック
                if (ChoseReason.valueOfId(choseReason) == null) {
                    String choseReasonMsg = StringUtils.replace(getMessage(ERRORS_SR_REASON_CHECK, new String[] {}),
                            REPLACE_TAG, LF);
                    msgModel.setChoseReason(choseReasonMsg);
                    msgModel.setChoseReasonStatus(ErrorType.ERROR.getLabel());
                }
            }
        }
        
        // 裁量希望株数は空の場合、裁量選定理由が入力不可チェック
        if (StringUtil.isNullOrEmpty(quantitySairyou)) {
            if (!StringUtil.isNullOrEmpty(choseReason)) {
                msgModel.setChoseReason(getMessage(ERRORS_NOT_INSERT_REASON, new String[] {}));
                msgModel.setChoseReasonStatus(ErrorType.ERROR.getLabel());
            }
        }
        
        // 備考は非空の場合、桁数チェックを行う。
        if (!StringUtil.isNullOrEmpty(bbRemark)) {
            // 環境依存文字チェック
            String utf8Str = UnicodeCheckUtil.getErrorUtf8Str(bbRemark);
            if (!utf8Str.isEmpty()) {
                msgModel.setBbRemark(getMessage(ERRORS_SPECIAL_WORDS, new String[] { "備考", utf8Str }));
                msgModel.setBbRemarkStatus(ErrorType.ERROR.getLabel());
            } else if (!CheckUtil.checkStringBytes(bbRemark, 400)) {
                msgModel.setBbRemark(getMessage(ERRORS_SIZE, new String[] { "備考", "0", "200" }));
                msgModel.setBbRemarkStatus(ErrorType.ERROR.getLabel());
            }
        }
        
        //　勧誘区分:必須入力チェック
        if (StringUtil.isNullOrEmpty(kanyuKbn)) {
            msgModel.setKanyuKbn(getMessage(ERRORS_REQUIRED, new String[] { "勧誘区分" }));
            msgModel.setKanyuKbnStatus(ErrorType.ERROR.getLabel());
            //　勧誘区分:コードチェック
        } else if (!(kanyuKbn.equals("1") || kanyuKbn.equals("2"))) {
            msgModel.setKanyuKbn(getMessage(ERRORS_TYPE, new String[] { "勧誘区分", "コード(1:勧誘あり、2:勧誘なし)" }));
            msgModel.setKanyuKbnStatus(ErrorType.ERROR.getLabel());
        }
        
        //　受注方法:必須入力チェック
        if (StringUtil.isNullOrEmpty(receiveOrderType)) {
            msgModel.setReceiveOrderType(getMessage(ERRORS_REQUIRED, new String[] { "受注方法" }));
            msgModel.setReceiveOrderTypeStatus(ErrorType.ERROR.getLabel());
            //　受注方法:コードチェック
        } else if (!(receiveOrderType.equals("1") || receiveOrderType.equals("2") || receiveOrderType.equals("3"))) {
            msgModel.setReceiveOrderType(getMessage(ERRORS_TYPE, new String[] { "受注方法", "コード(1:店頭、2:訪問、3:電話他)" }));
            msgModel.setReceiveOrderTypeStatus(ErrorType.ERROR.getLabel());
        }
        
        // チェック結果を設定する
        boolean brandCodeFlg = !StringUtil.isNullOrEmpty(msgModel.getBrandCode());
        boolean butenCodeFlg = !StringUtil.isNullOrEmpty(msgModel.getButenCode());
        boolean accountNumberFlg = !StringUtil.isNullOrEmpty(msgModel.getAccountNumber());
        boolean bbQuantityFlg = !StringUtil.isNullOrEmpty(msgModel.getBbQuantity());
        boolean bbPriceFlg = !StringUtil.isNullOrEmpty(msgModel.getBbPrice());
        boolean bbSeqFlg = !StringUtil.isNullOrEmpty(msgModel.getBbSeq());
        boolean quantitySairyouFlg = !StringUtil.isNullOrEmpty(msgModel.getQuantitySairyou());
        boolean choseReasonFlg = !StringUtil.isNullOrEmpty(msgModel.getChoseReason());
        boolean bbRemarkFlg = !StringUtil.isNullOrEmpty(msgModel.getBbRemark());
        boolean kanyuKbnFlg = !StringUtil.isNullOrEmpty(msgModel.getKanyuKbn());
        boolean receiveOrderTypeFlg = !StringUtil.isNullOrEmpty(msgModel.getReceiveOrderType());
        if (brandCodeFlg || butenCodeFlg || accountNumberFlg || bbQuantityFlg || bbPriceFlg || bbSeqFlg
                || quantitySairyouFlg || choseReasonFlg || bbRemarkFlg || kanyuKbnFlg || receiveOrderTypeFlg) {
            csvModel.setCheckResult(ErrorType.ERROR.getLabel());
        } else {
            csvModel.setCheckResult(ErrorType.OK.getLabel());
        }
    }
    
    /**
    * ファイル内容をarrayに設定する
    *
    * @param row {@code Row }
    * @return {@code String[]}
    */
    private String[] toArray(Row row) {
        
        String[] arr = new String[11];
        Cell cell = null;
        // フォーマットを設定する
        DecimalFormat df = new DecimalFormat("#.#########");
        boolean isNull = true;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = StringUtil.EMPTY_STRING;
            cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            if (cell != null) {
                isNull = false;
                CellType cellType = cell.getCellType();
                String value = StringUtil.EMPTY_STRING;
                switch (cellType) {
                case NUMERIC:
                    // NUMERICの場合、フォーマットを利用する必要があります。
                    value = df.format(cell.getNumericCellValue());
                    break;
                case STRING:
                    value = cell.getStringCellValue();
                    break;
                default:
                    value = StringUtil.EMPTY_STRING;
                    break;
                }
                arr[i] = value;
            }
        }
        if (isNull) {
            return null;
        }
        return arr;
    }
    
    /**
    * 数字フォーマットチェック
    *
    * @param str {@code String }
    * @return {@code boolean}
    * @throws Exception 数字フォーマットチェック処理で例外が発生した場合
    */
    private boolean isNumber(String str) throws Exception {
        
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    
    /**
    * 英数字フォーマットチェック
    *
    * @param str {@code String }
    * @return {@code boolean}
    * @throws Exception 英数字フォーマットチェック処理で例外が発生した場合
    */
    private boolean isAlphaNumber(String str) throws Exception {
        
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]*$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    
    /**
    * ファイルパスの拡張子チェックを追加
    *
    * @param fileName {@code String }
    * @return {@code boolean}
    */
    private boolean checkFileName(String fileName) {
        
        boolean result = false;
        fileName = fileName.toLowerCase();
        int index = fileName.lastIndexOf(".");
        String ext = fileName.substring(index + 1);
        if ("xlsx".equals(ext) || "xls".equals(ext)) {
            result = true;
        }
        return result;
    }
    
    /**
    * レスポンス内容を設定する。
    *
    * @param bbApply {@code IfaBbApplyCsvMassRegisterBBApplyApiResponse }
    * @param csvModel {@code IpopoUploadBBCustomerInfoModel }
    * @param msgModel {@code IpopoBBApplyUploadCheckResultModel }
    * @return {@code void}
    */
    private void setApiResponse(IfaBbApplyCsvMassRegisterBbApplyListApiResponse bbApply,
            IpopoUploadBBCustomerInfoModel csvModel, IpopoBBApplyUploadCheckResultModel msgModel) {
        
        List<IfaBbApplyCsvMassRegisterCheckResultSetApiResponse> checkResultSetList = new ArrayList<IfaBbApplyCsvMassRegisterCheckResultSetApiResponse>();
        
        bbApply.setBrandCode(csvModel.getBrandCode());
        bbApply.setBrandName(csvModel.getBrandName());
        bbApply.setButenCode(csvModel.getButenCode());
        bbApply.setAccountNumber(csvModel.getAccountNumber());
        bbApply.setCustomerName(csvModel.getCustomerName());
        bbApply.setBbQuantity(csvModel.getBbQuantity());
        bbApply.setBbPrice(csvModel.getBbPrice());
        bbApply.setBbGestureValue(csvModel.getBbGestureValue());
        bbApply.setBbInvestorAttName(csvModel.getBbInvestorAttName());
        bbApply.setBbSeq(csvModel.getBbSeq());
        bbApply.setQuantitySairyou(csvModel.getQuantitySairyou());
        bbApply.setChoseReason(csvModel.getChoseReason());
        bbApply.setBbRemark(csvModel.getBbRemarkForShow());
        bbApply.setKanyuKbn(csvModel.getKanyuKbn());
        bbApply.setReceiveOrderType(csvModel.getReceiveOrderType());
        bbApply.setCheckResult(csvModel.getCheckResult());
        
        // チェック結果セットの登録
        // 銘柄コード
        if (!StringUtil.isNullOrEmpty(msgModel.getBrandCode())) {
            IfaBbApplyCsvMassRegisterCheckResultSetApiResponse checkResultSet = new IfaBbApplyCsvMassRegisterCheckResultSetApiResponse();
            checkResultSet.setMsg(msgModel.getBrandCode());
            checkResultSet.setStatus(msgModel.getBrandCodeStatus());
            checkResultSetList.add(checkResultSet);
        } // 部店
        if (!StringUtil.isNullOrEmpty(msgModel.getButenCode())) {
            IfaBbApplyCsvMassRegisterCheckResultSetApiResponse checkResultSet = new IfaBbApplyCsvMassRegisterCheckResultSetApiResponse();
            checkResultSet.setMsg(msgModel.getButenCode());
            checkResultSet.setStatus(msgModel.getButenCodeStatus());
            checkResultSetList.add(checkResultSet);
        } // 口座番号
        if (!StringUtil.isNullOrEmpty(msgModel.getAccountNumber())) {
            IfaBbApplyCsvMassRegisterCheckResultSetApiResponse checkResultSet = new IfaBbApplyCsvMassRegisterCheckResultSetApiResponse();
            checkResultSet.setMsg(msgModel.getAccountNumber());
            checkResultSet.setStatus(msgModel.getAccountNumberStatus());
            checkResultSetList.add(checkResultSet);
        } // 顧客名
        if (!StringUtil.isNullOrEmpty(msgModel.getCustomerName())) {
            IfaBbApplyCsvMassRegisterCheckResultSetApiResponse checkResultSet = new IfaBbApplyCsvMassRegisterCheckResultSetApiResponse();
            checkResultSet.setMsg(msgModel.getCustomerName());
            checkResultSet.setStatus(msgModel.getCustomerNameStatus());
            checkResultSetList.add(checkResultSet);
        } // 希望株数
        if (!StringUtil.isNullOrEmpty(msgModel.getBbQuantity())) {
            IfaBbApplyCsvMassRegisterCheckResultSetApiResponse checkResultSet = new IfaBbApplyCsvMassRegisterCheckResultSetApiResponse();
            checkResultSet.setMsg(msgModel.getBbQuantity());
            checkResultSet.setStatus(msgModel.getBbQuantityStatus());
            checkResultSetList.add(checkResultSet);
        } // 申込価格
        if (!StringUtil.isNullOrEmpty(msgModel.getBbPrice())) {
            IfaBbApplyCsvMassRegisterCheckResultSetApiResponse checkResultSet = new IfaBbApplyCsvMassRegisterCheckResultSetApiResponse();
            checkResultSet.setMsg(msgModel.getBbPrice());
            checkResultSet.setStatus(msgModel.getBbPriceStatus());
            checkResultSetList.add(checkResultSet);
        } // 投資家属性
        if (!StringUtil.isNullOrEmpty(msgModel.getBbSeq())) {
            IfaBbApplyCsvMassRegisterCheckResultSetApiResponse checkResultSet = new IfaBbApplyCsvMassRegisterCheckResultSetApiResponse();
            checkResultSet.setMsg(msgModel.getBbSeq());
            checkResultSet.setStatus(msgModel.getBbSeqStatus());
            checkResultSetList.add(checkResultSet);
        } // 裁量希望株数
        if (!StringUtil.isNullOrEmpty(msgModel.getQuantitySairyou())) {
            IfaBbApplyCsvMassRegisterCheckResultSetApiResponse checkResultSet = new IfaBbApplyCsvMassRegisterCheckResultSetApiResponse();
            checkResultSet.setMsg(msgModel.getQuantitySairyou());
            checkResultSet.setStatus(msgModel.getQuantitySairyouStatus());
            checkResultSetList.add(checkResultSet);
        } // 裁量選定理由
        if (!StringUtil.isNullOrEmpty(msgModel.getChoseReason())) {
            IfaBbApplyCsvMassRegisterCheckResultSetApiResponse checkResultSet = new IfaBbApplyCsvMassRegisterCheckResultSetApiResponse();
            checkResultSet.setMsg(msgModel.getChoseReason());
            checkResultSet.setStatus(msgModel.getChoseReasonStatus());
            checkResultSetList.add(checkResultSet);
        } // 備考
        if (!StringUtil.isNullOrEmpty(msgModel.getBbRemark())) {
            IfaBbApplyCsvMassRegisterCheckResultSetApiResponse checkResultSet = new IfaBbApplyCsvMassRegisterCheckResultSetApiResponse();
            checkResultSet.setMsg(msgModel.getBbRemark());
            checkResultSet.setStatus(msgModel.getBbRemarkStatus());
            checkResultSetList.add(checkResultSet);
        } // 勧誘区分
        if (!StringUtil.isNullOrEmpty(msgModel.getKanyuKbn())) {
            IfaBbApplyCsvMassRegisterCheckResultSetApiResponse checkResultSet = new IfaBbApplyCsvMassRegisterCheckResultSetApiResponse();
            checkResultSet.setMsg(msgModel.getKanyuKbn());
            checkResultSet.setStatus(msgModel.getKanyuKbnStatus());
            checkResultSetList.add(checkResultSet);
        } // 受注方法
        if (!StringUtil.isNullOrEmpty(msgModel.getReceiveOrderType())) {
            IfaBbApplyCsvMassRegisterCheckResultSetApiResponse checkResultSet = new IfaBbApplyCsvMassRegisterCheckResultSetApiResponse();
            checkResultSet.setMsg(msgModel.getReceiveOrderType());
            checkResultSet.setStatus(msgModel.getReceiveOrderTypeStatus());
            checkResultSetList.add(checkResultSet);
        } // 存在と権限チェック用
        if (!StringUtil.isNullOrEmpty(msgModel.getExistMsg())) {
            IfaBbApplyCsvMassRegisterCheckResultSetApiResponse checkResultSet = new IfaBbApplyCsvMassRegisterCheckResultSetApiResponse();
            checkResultSet.setMsg(msgModel.getExistMsg());
            checkResultSet.setStatus(msgModel.getExistMsgStatus());
            checkResultSetList.add(checkResultSet);
        } // 裁量配分の業務チェック用
        if (!StringUtil.isNullOrEmpty(msgModel.getSairyou())) {
            IfaBbApplyCsvMassRegisterCheckResultSetApiResponse checkResultSet = new IfaBbApplyCsvMassRegisterCheckResultSetApiResponse();
            checkResultSet.setMsg(msgModel.getSairyou());
            checkResultSet.setStatus(msgModel.getSairyouStatus());
            checkResultSetList.add(checkResultSet);
        } // 登録金融資産3000万未満チェック用
        if (!StringUtil.isNullOrEmpty(msgModel.getFinancialAssets())) {
            IfaBbApplyCsvMassRegisterCheckResultSetApiResponse checkResultSet = new IfaBbApplyCsvMassRegisterCheckResultSetApiResponse();
            checkResultSet.setMsg(msgModel.getFinancialAssets());
            checkResultSet.setStatus(msgModel.getFinancialAssetsStatus());
            checkResultSetList.add(checkResultSet);
        }
        
        bbApply.setCheckResultSetList(checkResultSetList);
    }
    
    /**
    * リクエストのデータをモデルに設定する。
    *
    * @param apiReq {@code IfaBbApplyCsvMassRegisterApiRequestBBApply }
    * @param customerInfoModel {@code IpopoUploadBBCustomerInfoModel }
    * @return {@code void}
     */
    private void setApiReqToModel(IfaBbApplyCsvMassRegisterBbApplyListApiRequest apiReq,
            IpopoUploadBBCustomerInfoModel customerInfoModel) {
        
        customerInfoModel.setBrandCode(apiReq.getBrandCode());
        customerInfoModel.setBrandName(apiReq.getBrandName());
        customerInfoModel.setButenCode(apiReq.getButenCode());
        customerInfoModel.setAccountNumber(apiReq.getAccountNumber());
        customerInfoModel.setAccountNumber(apiReq.getAccountNumber());
        customerInfoModel.setAccountNumber(apiReq.getAccountNumber());
        customerInfoModel.setBbQuantity(apiReq.getBbQuantity());
        customerInfoModel.setBbPrice(apiReq.getBbPrice());
        customerInfoModel.setBbGestureValue(apiReq.getBbGestureValue());
        customerInfoModel.setBbInvestorAttName(apiReq.getBbInvestorAttName());
        customerInfoModel.setBbSeq(apiReq.getBbSeq());
        customerInfoModel.setQuantitySairyou(apiReq.getQuantitySairyou());
        customerInfoModel.setChoseReason(apiReq.getChoseReason());
        customerInfoModel.setBbRemark(apiReq.getBbRemark());
        customerInfoModel.setBbRemarkForShow(apiReq.getBbRemark().replaceAll(REPLACE_TAG_BR, "\n"));
        customerInfoModel.setKanyuKbn(apiReq.getKanyuKbn());
        customerInfoModel.setReceiveOrderType(apiReq.getReceiveOrderType());
        customerInfoModel.setCheckResult(apiReq.getCheckResult());
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
    
}
