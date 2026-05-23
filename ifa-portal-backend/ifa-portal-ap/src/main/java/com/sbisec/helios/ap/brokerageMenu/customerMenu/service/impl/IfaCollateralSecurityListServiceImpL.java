package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCollateralSecurityListA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCollateralSecurityListA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCollateralSecurityListA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCollateralSecurityListA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaCollateralSecurityListService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.exception.IfaRuntimeException;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import jp.co.sbisec.pcenter.dto.yanap.QueryAccountSubSecSumInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountSubSecSumOutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountSubSecSumOutVec;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountSubSecValueInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountSubSecValueOutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountSubSecValueOutVec;

/**
 * 画面ID：SUB0202_010204-01
 * 画面名：代用有価証券一覧
 * アクションID：A001
 * アクション名：初期表示
 *
 * @author SCSK
 *
 */
@Component(value = "ifaCollateralSecurityListService")
public class IfaCollateralSecurityListServiceImpL implements IfaCollateralSecurityListService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaCollateralSecurityListServiceImpL.class);
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private ApiWrapper apiwrapper;
    
    /** 数値（0～9、.）のみの文字列判定 */
    private static final Pattern NUMBER_PATTERN = Pattern.compile("-?[0-9.]+");
    
    /** 権限チェックエラー  */
    public static final String ERRORS_ACCOUNT_NOT_EXISTS = "errors.butenAccountNotExist";
    
    /** 国内信用口座開設状況のチェックエラー */
    public static final String ERRORS_DMS_NOT_OPEN = "errors.dms.domesticMarginAccount.notOpen";
    
    /** 件数超過エラー */
    public static final String ERRORS_EXCEEDED_MAXIMUM = "errors.exceededMaximum";
    
    /** 権限チェックエラー値 */
    public static final String AUTH_ERROR_VALUE = "0";
    
    /** 商品区分(投信,投信一般口) */
    public static final String SEC_ID_GENERAL = "T";
    
    /** 商品区分(投信累投口) */
    public static final String SEC_ID_CUMULATIVE = "Y";
    
    /** 非特定預り区分(特定口座における特定預り) */
    public static final String HITOKUTEI_KBN_SPECIFIC_DEPOSIT = "0";
    
    /** A004.表示基準日（受渡日） を YYYMMDDに変換するための正規表現パターン */
    public static final Pattern DISPLAY_BASE_DATE = Pattern.compile("/|\\(.+$|～.+$");
    
    /**
     * Dto リクエスト：DtoRequest
     * Dto レスポンス：DtoResponse
     * model リクエスト：IfaCollateralSecurityListA001RequestDto
     * model レスポンス：IfaCollateralSecurityListA001ResponseDto
     *
     * @param dtoReq リクエストDTO
     * @return レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaCollateralSecurityListA001ResponseDto> initialA001(
            IfaCollateralSecurityListA001RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaCollateralSecurityListServiceImplL.initialDisplayA001");
        }
        
        List<IfaCollateralSecurityListA001ResponseDto> resDtoList = new ArrayList<IfaCollateralSecurityListA001ResponseDto>();
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // 顧客情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        
        //        １．共通関数FCT001の呼び出し
        //        利用者の口座に対する権限チェックを行う。
        //        権限あり（対象顧客参照権限有無＝"1"）：次の処理へ。
        //        権限なし（対象顧客参照権限有無＝"0"：権限なしエラーを返す。
        InputFct001Dto fct001InData = new InputFct001Dto();
        fct001InData.setButenCode(butenCode);
        fct001InData.setAccountNumber(accountNumber);
        OutputFct001Dto fct001Dto = fct001.doCheck(fct001InData);
        if (fct001Dto == null || StringUtils.equals(fct001Dto.getTargetCustomerRefAuthFlag(),
                IfaCollateralSecurityListServiceImpL.AUTH_ERROR_VALUE)) {
            
            DataList<IfaCollateralSecurityListA001ResponseDto> dtoRes = IfaCommonUtil.createDataList(resDtoList,
                    ErrorLevel.FATAL, IfaCollateralSecurityListServiceImpL.ERRORS_ACCOUNT_NOT_EXISTS,
                    IfaCommonUtil.getMessage(IfaCollateralSecurityListServiceImpL.ERRORS_ACCOUNT_NOT_EXISTS,
                            new String[] { butenCode, accountNumber }));
            return dtoRes;
        }
        
        // 顧客情報から信用口座区分(国内)の取得
        String domesticMarginAccountType = cc.getDomesticMarginAccountType();
        
        //      ２．顧客共通情報の「信用口座区分(国内)」より、信用口座開設状況をチェックを行う。
        //    「開設済」：次の処理へ。
        //    「未開設」：信用口座未開設エラーを返す。
        if (StringUtils.equals(domesticMarginAccountType, "1") == false) {
            DataList<IfaCollateralSecurityListA001ResponseDto> dtoRes = IfaCommonUtil.createDataList(resDtoList,
                    ErrorLevel.FATAL, IfaCollateralSecurityListServiceImpL.ERRORS_DMS_NOT_OPEN,
                    IfaCommonUtil.getMessage(IfaCollateralSecurityListServiceImpL.ERRORS_DMS_NOT_OPEN));
            return dtoRes;
        }
        
        //      ３．代用有価証券評価額合計欄の情報を取得する。（API001）
        //      パラメタ：部店コード ⇒顧客共通情報.部店コード
        //          口座番号     ⇒顧客共通情報.口座番号
        //                  入力パラメータ長
        QueryAccountSubSecValueInData api001Req = new QueryAccountSubSecValueInData();
        api001Req.setAccountNo(String.format("%7s", accountNumber).replace(" ", "0"));
        api001Req.setOfficeCode(butenCode);
        QueryAccountSubSecValueOutData api001ResDto = apiwrapper.queryAccountSubSecValue(api001Req);
        apiErrorUtil.checkApiResponse(api001ResDto.getShubetu(), api001ResDto.getCode(), api001ResDto.getMessage());
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        
        IfaCollateralSecurityListA001ResponseDto resDto = new IfaCollateralSecurityListA001ResponseDto();
        
        //  "データ取得件数が0件の場合、メッセージを表示し、処理を終了する。
        //  上記以外の場合、処理を続行する。"
        if (ObjectUtils.isEmpty(api001ResDto.getSubstituteT()) || api001ResDto.getSubstituteT().size() == 0) {
            resDto.setSettlementDateList(new ArrayList<IfaCollateralSecurityListA001ResponseDto.SettlementDate>());
            resDto.setDetailList(new ArrayList<IfaCollateralSecurityListA001ResponseDto.Detail>());
            resDtoList.add(resDto);
            DataList<IfaCollateralSecurityListA001ResponseDto> dtoRes = IfaCommonUtil.createDataList(resDtoList,
                    ErrorLevel.SUCCESS, "", "");
            
            return dtoRes;
        }
        
        //  API001の戻り値からレスポンスに値を設定する
        this.setApi001ResDtoToA001ResDto(dtoReq, api001ResDto, resDto);
        
        //API001.代用有価証券評価額情報(T+0）.受渡日 の取得
        String settleDate = api001ResDto.getSubstituteT().get(0).getSettleDate();
        
        //  ④  代用有価証券評価額合計欄の情報を取得する。（API002）
        //  API002呼び出し時に以下のパラメタを設定する。
        //           パラメタ：部店コード ⇒顧客共通情報.部店コード
        //                 口座番号  ⇒顧客共通情報.口座番号
        //                 受渡日    ⇒API001.代用有価証券評価額情報(T+0）.受渡日
        //                 代用種別  ⇒A001.代用種別
        //  検索番号指定ＦＲＯＭ～検索番号指定ＴＯの設定について
        //  1回目の呼び出しでOUT.検索結果件数>1000の場合、2回目以降は 検索結果件数分をすべて取得するまで設定を変えて実行
        //  例）１回目 From:00001、To:01000
        //            2回目 From:01001、To:02000
        //            3回目 From02001、To:03000
        //            ～
        String searchFrom = "00001";
        String searchTo = "01000";
        QueryAccountSubSecSumInData api002Req = new QueryAccountSubSecSumInData();
        api002Req.setOfficeCode(butenCode.substring(0, 3));
        api002Req.setAccountNo(String.format("%7s", accountNumber).replace(" ", "0"));
        api002Req.setSettleDate(settleDate);
        api002Req.setRefFrom(searchFrom);
        api002Req.setRefTo(searchTo);
        api002Req.setDaiyoShubetu(dtoReq.getCollateralClass());
        
        QueryAccountSubSecSumOutData api002ResDto = apiwrapper.queryAccountSubSecSum(api002Req);
        apiErrorUtil.checkApiResponse(api002ResDto.getShubetu(), api002ResDto.getCode(), api002ResDto.getMessage());
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        
        if (ObjectUtils.isEmpty(api002ResDto.getSubstituteT()) || api002ResDto.getSubstituteT().size() == 0) {
            resDto.setDetailList(new ArrayList<IfaCollateralSecurityListA001ResponseDto.Detail>());
        } else {
            //  API002の戻り値からレスポンスに値を設定する
            this.setApi002ResDtoToA001ResDto(butenCode, accountNumber, settleDate, dtoReq, searchFrom, searchTo,
                    api002ResDto, resDto);
        }
        
        resDtoList.add(resDto);
        DataList<IfaCollateralSecurityListA001ResponseDto> dtoRes = apiErrorUtil.createDataList(resDtoList, null);
        return dtoRes;
    }
    
    /**
     * Dto リクエスト：DtoRequest
     * Dto レスポンス：DtoResponse
     * model リクエスト：IfaCollateralSecurityListA004RequestDto
     * model レスポンス：IfaCollateralSecurityListA004ResponseDto
     *
     * @param dtoReq リクエストDTO
     * @return レスポンスDTO
     * @exception Exception 更新処理で例外が発生した場合
     */
    public DataList<IfaCollateralSecurityListA004ResponseDto> updateA004(IfaCollateralSecurityListA004RequestDto dtoReq)
            throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaCollateralSecurityListServiceImplL.initialDisplayA004");
        }
        
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // 顧客情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        String butenCode = cc.getButenCode();
        String kouzaNo = cc.getAccountNumber();
        
        //  ① 代用有価証券評価額合計欄の情報を取得する。（API001）
        //        パラメタ：部店コード ⇒顧客共通情報.部店コード
        //                          口座番号     ⇒顧客共通情報.口座番号
        //                入力パラメータ長"
        QueryAccountSubSecValueInData api001Req = new QueryAccountSubSecValueInData();
        api001Req.setAccountNo(String.format("%7s", kouzaNo).replace(" ", "0"));
        api001Req.setOfficeCode(butenCode);
        QueryAccountSubSecValueOutData api001ResDto = apiwrapper.queryAccountSubSecValue(api001Req);
        apiErrorUtil.checkApiResponse(api001ResDto.getShubetu(), api001ResDto.getCode(), api001ResDto.getMessage());
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        
        IfaCollateralSecurityListA004ResponseDto resDto = new IfaCollateralSecurityListA004ResponseDto();
        
        List<IfaCollateralSecurityListA004ResponseDto> resDtoList = new ArrayList<IfaCollateralSecurityListA004ResponseDto>();
        
        //  "データ取得件数が0件の場合、メッセージを表示し、処理を終了する。
        //  上記以外の場合、処理を続行する。"
        if (ObjectUtils.isEmpty(api001ResDto.getSubstituteT()) || api001ResDto.getSubstituteT().size() == 0) {
            resDto.setDisplayBaseDate(dtoReq.getDisplayBaseDate());
            resDto.setSettlementDateList(new ArrayList<IfaCollateralSecurityListA004ResponseDto.SettlementDate>());
            resDto.setDetailList(new ArrayList<IfaCollateralSecurityListA004ResponseDto.Detail>());
            resDtoList.add(resDto);
            DataList<IfaCollateralSecurityListA004ResponseDto> dtoRes = IfaCommonUtil.createDataList(resDtoList,
                    ErrorLevel.SUCCESS, "", "");
            
            return dtoRes;
        }
        
        //  API001の戻り値からレスポンスに値を設定する
        this.setApi001ResDtoToA004ResDto(dtoReq, api001ResDto, resDto);
        
        //  ②   表示基準日（受渡日）の編集
        //  "A004.表示基準日（受渡日） が API001.代用有価証券評価額情報(T+0）.受渡日　より過去日の場合、
        //  A004.表示基準日（受渡日）に API001.代用有価証券評価額情報(T+0）.受渡日 を設定する"
        String settleDate = DISPLAY_BASE_DATE.matcher(dtoReq.getDisplayBaseDate()).replaceAll("");
        if (api001ResDto.getSubstituteT().size() > 0) {
            String editDisplayBaseDate = DISPLAY_BASE_DATE.matcher(dtoReq.getDisplayBaseDate()).replaceAll("");
            String apiSettleDate = api001ResDto.getSubstituteT().get(0).getSettleDate();
            
            if (editDisplayBaseDate.compareTo(apiSettleDate) < 0) {
                settleDate = apiSettleDate;
                resDto.setDisplayBaseDate(resDto.getSettlementDateList().get(0).getDisplayBaseDate());
            } else {
                settleDate = editDisplayBaseDate;
                resDto.setDisplayBaseDate(dtoReq.getDisplayBaseDate());
            }
        } else {
            resDto.setDisplayBaseDate(dtoReq.getDisplayBaseDate());
        }
        
        //
        //   ②  A001の④と同様
        //   "API002呼び出し時に以下のパラメタを設定する。
        //        パラメタ：部店コード ⇒顧客共通情報.部店コード
        //                          口座番号     ⇒顧客共通情報.口座番号
        //                受渡日        ⇒A004.表示基準日（受渡日）
        //                          代用種別    ⇒A004.代用種別"
        //   検索番号指定ＦＲＯＭ～検索番号指定ＴＯの設定値についてはA001の④と同様
        
        //API002呼び出し時に以下のパラメタを設定する。
        //         パラメタ：部店コード ⇒顧客共通情報.部店コード
        //                           口座番号     ⇒顧客共通情報.口座番号
        //                 受渡日        ⇒A001.表示基準日（受渡日）
        //                           代用種別    ⇒A001.代用種別
        //検索番号指定ＦＲＯＭ～検索番号指定ＴＯの設定について
        //1回目の呼び出しでOUT.検索結果件数>1000の場合、2回目以降は 検索結果件数分をすべて取得するまで設定を変えて実行
        //例）１回目 From:00001、To:01000
        //          2回目 From:01001、To:02000
        //          3回目 From02001、To:03000
        //          ～
        String searchFrom = "00001";
        String searchTo = "01000";
        QueryAccountSubSecSumInData api002Req = new QueryAccountSubSecSumInData();
        api002Req.setOfficeCode(butenCode);
        api002Req.setAccountNo(String.format("%7s", kouzaNo).replace(" ", "0"));
        api002Req.setSettleDate(settleDate);
        api002Req.setRefFrom(searchFrom);
        api002Req.setRefTo(searchTo);
        api002Req.setDaiyoShubetu(dtoReq.getCollateralClass());
        
        QueryAccountSubSecSumOutData api002ResDto = apiwrapper.queryAccountSubSecSum(api002Req);
        apiErrorUtil.checkApiResponse(api001ResDto.getShubetu(), api001ResDto.getCode(), api001ResDto.getMessage());
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        
        if (ObjectUtils.isEmpty(api002ResDto.getSubstituteT()) || api002ResDto.getSubstituteT().size() == 0) {
            resDto.setDetailList(new ArrayList<IfaCollateralSecurityListA004ResponseDto.Detail>());
        } else {
            //  API002の戻り値からレスポンスに値を設定する
            this.setApi002ResDtoToA004ResDto(butenCode, kouzaNo, settleDate, dtoReq, searchFrom, searchTo, api002ResDto,
                    resDto);
        }
        
        resDtoList.add(resDto);
        DataList<IfaCollateralSecurityListA004ResponseDto> dtoRes = apiErrorUtil.createDataList(resDtoList, null);
        return dtoRes;
    }
    
    /**
     * A001:API001の戻り値からレスポンスに値を設定する
     *
     * @param dtoReq        A001のリクエスト
     * @param api001ResDto  API001のレスポンス
     * @param resDto        A001のレスポンス
     */
    private void setApi001ResDtoToA001ResDto(IfaCollateralSecurityListA001RequestDto dtoReq,
            QueryAccountSubSecValueOutData api001ResDto, IfaCollateralSecurityListA001ResponseDto resDto)
            throws Exception {
        
        //  ・更新日時
        //  「API001.更新日 ＋ API001.更新時間」（yyyy/MM/dd HH:mm:ss）を設定する
        resDto.setUpdateTime(api001ResDto.getUpdateDate().substring(0, 4) + "/"
                + api001ResDto.getUpdateDate().substring(4, 6) + "/" + api001ResDto.getUpdateDate().substring(6, 8)
                + " " + api001ResDto.getUpdateTime().substring(0, 2) + ":"
                + api001ResDto.getUpdateTime().substring(2, 4) + ":" + api001ResDto.getUpdateTime().substring(4, 6));
        
        List<IfaCollateralSecurityListA001ResponseDto.SettlementDate> settlementDateList = new ArrayList<IfaCollateralSecurityListA001ResponseDto.SettlementDate>();
        
        //当日～5営業日後の6営業日分のデータを営業日でソートする
        List<QueryAccountSubSecValueOutVec> substituteList = api001ResDto.getSubstituteT();
        Collections.sort(substituteList, new Comparator<QueryAccountSubSecValueOutVec>() {
            
            @Override
            //順序付けのために2つの引数を比較します。
            public int compare(QueryAccountSubSecValueOutVec p1, QueryAccountSubSecValueOutVec p2) {
                
                return Integer.parseInt(p1.getSettleDate()) - Integer.parseInt(p2.getSettleDate());
            }
        });
        
        for (int index = 0; index < substituteList.size(); index++) {
            
            QueryAccountSubSecValueOutVec substitute = substituteList.get(index);
            
            IfaCollateralSecurityListA001ResponseDto.SettlementDate settlementDate = new IfaCollateralSecurityListA001ResponseDto.SettlementDate();
            
            //・受渡日（T+0）～受渡日（T+5）リスト.受渡日
            //  API001.代用有価証券評価額情報(T+0）～(T+5).受渡日を「YYYY/MM/DD」形式で設定する
            //  ※）画面定義書に記載されている 「改行 + "(XXXXX)"」部分はフロント側で設定する。
            settlementDate.setSettlementDate(substitute.getSettleDate().substring(0, 4) + "/"
                    + substitute.getSettleDate().substring(4, 6) + "/" + substitute.getSettleDate().substring(6, 8));
            
            //  ・レスポンス.受渡日（T+0）～受渡日（T+5）リスト.代用有価証券評価額合計
            if (substitute.getSumSubGaku().trim().length() == 0) {
                
                //  1) API001.受渡日（T+0）～受渡日（T+5）リスト.代用有価証券評価額合計＝スペースの場合
                //  '-'を設定する。
                settlementDate.setAlternativeSecuritiesTotal(null);
            } else {
                
                //  2) API001.受渡日（T+0）～受渡日（T+5）リスト.代用有価証券評価額合計がスペース以外の場合
                //  API001.受渡日（T+0）～受渡日（T+5）リスト.代用有価証券評価額合計を設定する
                settlementDate.setAlternativeSecuritiesTotal(substitute.getSumSubGaku());
            }
            // 代用有価証券入庫（数値(整数)）  の設定
            settlementDate.setCollateralSecurityDeliverIn(substitute.getSubStore());
            
            // 代用有価証券出庫（数値(整数)）  の設定
            settlementDate.setCollateralSecurityDeliverOut(substitute.getSubShip());
            
            //・レスポンス.受渡日（T+0）～受渡日（T+5）リスト.表示基準日の編集
            //以下の内容および並び順でYYYY/MM/DDにAPI001.代用有価証券評価額情報(T+0）～(T+5).受渡日を設定
            //YYYY/MM/DD(当日)
            //YYYY/MM/DD(1営業日後)
            //YYYY/MM/DD(2営業日後)
            //YYYY/MM/DD(3営業日後)
            //YYYY/MM/DD(4営業日後)
            //YYYY/MM/DD(5営業日後)
            if (substitute.getSettleDate().trim().length() == 0) {
                //表示基準日の設定
                settlementDateList.add(null);
            } else {
                
                StringBuffer editSettleDate = new StringBuffer();
                editSettleDate.append(substitute.getSettleDate().substring(0, 4));
                editSettleDate.append("/");
                editSettleDate.append(substitute.getSettleDate().substring(4, 6));
                editSettleDate.append("/");
                editSettleDate.append(substitute.getSettleDate().substring(6, 8));
                //表示基準日の設定
                switch (index) {
                case 0:
                    settlementDate.setDisplayBaseDate(editSettleDate.toString() + "(当日)");
                    break;
                case 1:
                    settlementDate.setDisplayBaseDate(editSettleDate.toString() + "(1営業日後)");
                    break;
                case 2:
                    settlementDate.setDisplayBaseDate(editSettleDate.toString() + "(2営業日後)");
                    break;
                case 3:
                    settlementDate.setDisplayBaseDate(editSettleDate.toString() + "(3営業日後)");
                    break;
                case 4:
                    settlementDate.setDisplayBaseDate(editSettleDate.toString() + "(4営業日後)");
                    break;
                case 5:
                    settlementDate.setDisplayBaseDate(editSettleDate.toString() + "～(5営業日後)～");
                    break;
                default:
                    throw new IfaRuntimeException(IfaCommonUtil.getMessage(ERRORS_EXCEEDED_MAXIMUM,
                            new String[] { Integer.toString(settlementDateList.size()) }));
                }
            }
            settlementDateList.add(settlementDate);
        }
        resDto.setSettlementDateList(settlementDateList);
    }
    
    /**
     * A001:API002の戻り値からレスポンスに値を設定する
     *
     * @param butenCode     部店コード
     * @param accountNumber 口座番号
     * @param settleDate    API001.代用有価証券評価額情報(T+0）.受渡日
     * @param dtoReq        A001のリクエスト
     * @param searchFrom    検索NoFrom
     * @param searchTo      検索NoTo
     * @param api001ResDto  API001のレスポンス
     * @param resDto        A001のレスポンス
     */
    private void setApi002ResDtoToA001ResDto(String butenCode, String accountNumber, String settleDate,
            IfaCollateralSecurityListA001RequestDto dtoReq, String searchFrom, String searchTo,
            QueryAccountSubSecSumOutData api002ResDto, IfaCollateralSecurityListA001ResponseDto resDto)
            throws Exception {
        
        List<IfaCollateralSecurityListA001ResponseDto.Detail> detailList = new ArrayList<IfaCollateralSecurityListA001ResponseDto.Detail>();
        
        //検索結果件数の取得
        if (StringUtils.equals(searchFrom, "00001")) {
            
            //  ・国内株式代用評価金額合計の編集（検索番号指定ＦＲＯＭ＝00001の場合のみ。）
            if (api002ResDto.getStockTotalValue().trim().length() == 0
                    || NUMBER_PATTERN.matcher(api002ResDto.getStockTotalValue().trim()).replaceAll("").length() != 0) {
                
                //  1).API002.国内株式代用評価金額合計＝データなし又はフォーマット不正の場合
                //  '-'を設定する。
                resDto.setDomesticStockCollateralValuationTotal(null);
            } else {
                
                //  2).API002.国内株式代用評価金額合計が上記以外の場合
                //  API002.国内株式代用評価金額合計を設定する
                //  ・国内投信代用評価金額合計の編集（検索番号指定ＦＲＯＭ＝00001の場合のみ。）
                resDto.setDomesticStockCollateralValuationTotal(api002ResDto.getStockTotalValue());
            }
            
            //  ・国内投信代用評価金額合計の編集（検索番号指定ＦＲＯＭ＝00001の場合のみ。）
            if (api002ResDto.getInvestTotalValue().trim().length() == 0
                    || NUMBER_PATTERN.matcher(api002ResDto.getInvestTotalValue().trim()).replaceAll("").length() != 0) {
                
                //  1).API002.国内投信代用評価金額合計＝データなし又はフォーマット不正の場合
                //  '-'を設定する。
                resDto.setDomesticMutualCollateralValuationTotal(null);
            } else {
                
                //  2).API002.国内投信代用評価金額合計が上記以外の場合
                //  API002.国内投信代用評価金額合計を設定する
                resDto.setDomesticMutualCollateralValuationTotal(api002ResDto.getInvestTotalValue());
            }
            
            //  ・代用有価証券評価額合計の編集（検索番号指定ＦＲＯＭ＝00001の場合のみ。）
            if (api002ResDto.getTotalValue().trim().length() == 0
                    || NUMBER_PATTERN.matcher(api002ResDto.getTotalValue().trim()).replaceAll("").length() != 0) {
                
                //  1).API002.代用評価金額合計＝データなし又はフォーマット不正の場合
                //  '-'を設定する。
                resDto.setAlternativeSecuritiesTotal(null);
            } else {
                
                //  2).API002.代用評価金額合計が上記以外の場合
                //  API002.代用評価金額合計を設定する
                resDto.setAlternativeSecuritiesTotal(api002ResDto.getTotalValue());
            }
        }
        
        for (int index = 0; index < api002ResDto.getSubstituteT().size(); index++) {
            QueryAccountSubSecSumOutVec substitute = api002ResDto.getSubstituteT().get(index);
            
            IfaCollateralSecurityListA001ResponseDto.Detail detail = new IfaCollateralSecurityListA001ResponseDto.Detail();
            
            String secType = substitute.getSecTypeName();
            substitute.setSecName(trimFullSpace(substitute.getSecName()));
            if (StringUtils.equals(secType, "K0")) {
                //  ・代用有価証券明細部リスト.商品分類の編集
                //  1).API002.商品タイプ（商品区分＋国内外国区分）＝  Ｋ０：国内株式の場合
                //  ”国内株式”を設定
                detail.setSecurityClass("国内株式");
                
                //  ・代用有価証券明細部リスト.銘柄コード編集
                //  API002.商品タイプ（商品区分＋国内外国区分）＝  Ｋ０：国内株式の場合
                //  「API002.会社コードの頭4桁＋API002.新旧区分」を設定
                detail.setBrandCode(substitute.getCompanyCode().substring(0, 4) + substitute.getNewOldId());
                
                //  ・代用有価証券明細部リスト.銘柄名編集
                //  API002.商品タイプ（商品区分＋国内外国区分）＝  Ｋ０：国内株式の場合
                //  1).API002.銘柄名（漢字）が全スペース以外かつ API002.新旧区分が"1"(新株)の場合
                //  「API002.銘柄名（漢字）+""－新株""」を設
                //  2).API002.銘柄名（漢字）が全スペース以外かつ API002.新旧区分が"1"(新株)以外の場合
                //  「API002.銘柄名（漢字）」を設定
                //  3).API002.銘柄名（漢字）が全スペースの場合
                //  「-」を設定
                if (substitute.getSecName().length() != 0) {
                    if (StringUtils.equals(substitute.getNewOldId(), "1")) {
                        detail.setBrandName(substitute.getSecName() + "－新株");
                    } else {
                        detail.setBrandName(substitute.getSecName());
                    }
                } else {
                    detail.setBrandName(null);
                }
                
            } else if (StringUtils.equals(secType, "T0")) {
                //  ・代用有価証券明細部リスト.商品分類の編集
                //  2).API002.商品タイプ（商品区分＋国内外国区分）＝  Ｔ０：国内投信の場合
                //  ”投資信託”を設定
                detail.setSecurityClass("投資信託");
                
                //  ・代用有価証券明細部リスト.銘柄コード編集
                //  API002.商品タイプ（商品区分＋国内外国区分）＝  Ｔ０：国内投信の場
                //  「API002.回数+"."+API002.号1+API002.号2" 」を設定
                detail.setBrandCode(substitute.getSerNo() + "." + substitute.getSubCode1() + substitute.getSubCode2());
                
                //  ・代用有価証券明細部リスト.銘柄名編集
                //  API002.商品タイプ（商品区分＋国内外国区分）＝  Ｔ０：国内投信の場合、
                //  1).API002.銘柄名（漢字）が全スペース以外の場合
                //  「API002.銘柄名（漢字）」を設定
                //  2).API002.銘柄名（漢字）が全スペースの場合
                //  「-」を設定
                if (substitute.getSecName().length() != 0) {
                    detail.setBrandName(substitute.getSecName());
                } else {
                    detail.setBrandName(null);
                }
                
            } else {
                detail.setSecurityClass(null);
                detail.setBrandCode(null);
                detail.setBrandName(null);
            }
            
            //  ・代用有価証券明細部リスト.預り区分の編集
            //  「①特定口座区分  ＋  ②口数金額」を設定する
            //  ①特定口座区分の編集
            //  1).API002.非特定預り区分＝  "0"'(特定口座における特定預り)の場合、
            //  ”特定”を設定
            //  2).API002.非特定預り区分＝  "0"'(特定口座における特定預り)以外の場合、
            //  ”一般”を設定
            String specificAccountType = (StringUtils.equals(substitute.getHitokuteiKbn(),
                    IfaCollateralSecurityListServiceImpL.HITOKUTEI_KBN_SPECIFIC_DEPOSIT)) ? "特定" : "一般";
            
            //  ②口数金額の編集
            //  1).API002.商品区分='T'(投信,投信一般口)の場合
            //  ”(口数)”を設定
            //  2).API002.商品区分='Y'(投信累投口)の場合
            //  ”(金額)”を設定
            String mutualFundUnitAmount = "";
            if (StringUtils.equals(substitute.getSecId(), IfaCollateralSecurityListServiceImpL.SEC_ID_GENERAL)) {
                mutualFundUnitAmount = "(口数)";
            } else if (StringUtils.equals(substitute.getSecId(),
                    IfaCollateralSecurityListServiceImpL.SEC_ID_CUMULATIVE)) {
                mutualFundUnitAmount = "(金額)";
            }
            detail.setDepositType(specificAccountType + mutualFundUnitAmount);
            
            //  ・代用有価証券明細部リスト.残高数量の編集
            //  1).API002.残高数量＝  データなし又はフォーマット不正の場合
            //  ”-”を設定
            //  2).API002.残高数量＝  上記以外の場合
            //  API002.残高数量を設定
            String quantity = substitute.getQuantity().trim();
            if (quantity.length() == 0 || NUMBER_PATTERN.matcher(quantity).replaceAll("").length() != 0) {
                detail.setContPosition(null);
            } else {
                detail.setContPosition(quantity);
            }
            
            //    ・代用有価証券明細部リスト.評価単価の編集
            //    API002.評価単価＝  データなし又はフォーマット不正の場合”-”を設定
            //    API002.評価単価＝  上記以外の場合、
            //    API002.評価単価を設定"
            String valuationPrice = substitute.getMarketPrice().trim();
            if (valuationPrice.length() == 0 || NUMBER_PATTERN.matcher(valuationPrice).replaceAll("").length() != 0) {
                detail.setValuationPrice(null);
            } else {
                detail.setValuationPrice(valuationPrice);
            }
            
            //  ・代用有価証券明細部リスト.代用掛目の編集
            //  1).API002.代用掛目＝  全スペースの場合
            //  未設定
            //  2).API002.代用掛目＝  上記以外の場合
            //  API002.代用掛目を設定
            if (substitute.getCollateralRate().trim().length() != 0) {
                detail.setCollateralAssessment(substitute.getCollateralRate());
            }
            
            //  ・代用有価証券明細部リスト.代用評価金額の編集
            //  1).API002.代用評価金額＝  データなし又はフォーマット不正の場合
            //  ”-”を設定
            //  2).API002.代用評価金額＝  上記以外の場合
            //  API002.代用評価金額を設定
            String collateralValue = substitute.getCollateralValue().trim();
            if (collateralValue.length() == 0 || NUMBER_PATTERN.matcher(collateralValue).replaceAll("").length() != 0) {
                detail.setCollateralValuation(null);
            } else {
                detail.setCollateralValuation(substitute.getCollateralValue());
            }
            
            //  ・代用有価証券明細部リスト.担保貸株区分の編集
            //  1).API002.担保貸株区分＝　’1’（担保貸株）の場合
            //  ”担保貸株”を設定
            //  2).API002.担保貸株区分＝　上記以外の場合、
            //  ”-”を設定
            String lsubstituteStockFlg = substitute.getLSubstituteStockFlg().trim();
            if (StringUtils.equals(lsubstituteStockFlg, "1")) {
                detail.setSecurityStockLendingClassification("担保貸株");
            } else {
                detail.setSecurityStockLendingClassification(null);
            }
            detailList.add(detail);
        }
        
        resDto.setDetailList(detailList);
    }
    
    /**
     * A004:API001の戻り値からレスポンスに値を設定する
     *
     * @param dtoReq        A004のリクエスト
     * @param api001ResDto  API001のレスポンス
     * @param resDto        A004のレスポンス
     */
    private void setApi001ResDtoToA004ResDto(IfaCollateralSecurityListA004RequestDto dtoReq,
            QueryAccountSubSecValueOutData api001ResDto, IfaCollateralSecurityListA004ResponseDto resDto)
            throws Exception {
        
        //  ・更新日時
        //   「API001.更新日　＋　API001.更新時間」（yyyy/MM/dd HH:mm:ss）を設定する
        resDto.setUpdateTime(api001ResDto.getUpdateDate().substring(0, 4) + "/"
                + api001ResDto.getUpdateDate().substring(4, 6) + "/" + api001ResDto.getUpdateDate().substring(6, 8)
                + " " + api001ResDto.getUpdateTime().substring(0, 2) + ":"
                + api001ResDto.getUpdateTime().substring(2, 4) + ":" + api001ResDto.getUpdateTime().substring(4, 6));
        
        List<IfaCollateralSecurityListA004ResponseDto.SettlementDate> settlementDateList = new ArrayList<IfaCollateralSecurityListA004ResponseDto.SettlementDate>();
        
        //当日～5営業日後の6営業日分のデータを営業日でソートする
        List<QueryAccountSubSecValueOutVec> substituteList = api001ResDto.getSubstituteT();
        Collections.sort(substituteList, new Comparator<QueryAccountSubSecValueOutVec>() {
            
            @Override
            //順序付けのために2つの引数を比較します。
            public int compare(QueryAccountSubSecValueOutVec p1, QueryAccountSubSecValueOutVec p2) {
                
                return Integer.parseInt(p1.getSettleDate()) - Integer.parseInt(p2.getSettleDate());
            }
        });
        
        for (int index = 0; index < substituteList.size(); index++) {
            
            QueryAccountSubSecValueOutVec substitute = substituteList.get(index);
            
            IfaCollateralSecurityListA004ResponseDto.SettlementDate settlementDate = new IfaCollateralSecurityListA004ResponseDto.SettlementDate();
            
            //・受渡日（T+0）～受渡日（T+5）リスト.受渡日
            //  API001.代用有価証券評価額情報(T+0）～(T+5).受渡日を「YYYY/MM/DD」形式で設定する
            //  ※）画面定義書に記載されている 「改行 + "(XXXXX)"」部分はフロント側で設定する。
            settlementDate.setSettlementDate(substitute.getSettleDate().substring(0, 4) + "/"
                    + substitute.getSettleDate().substring(4, 6) + "/" + substitute.getSettleDate().substring(6, 8));
            
            //  ・レスポンス.受渡日（T+0）～受渡日（T+5）リスト.代用有価証券評価額合計
            if (substitute.getSumSubGaku().trim().length() == 0) {
                
                //  1) API001.受渡日（T+0）～受渡日（T+5）リスト.代用有価証券評価額合計＝スペースの場合
                //  '-'を設定する。
                settlementDate.setAlternativeSecuritiesTotal(null);
            } else {
                
                //  2) API001.受渡日（T+0）～受渡日（T+5）リスト.代用有価証券評価額合計がスペース以外の場合
                //  API001.受渡日（T+0）～受渡日（T+5）リスト.代用有価証券評価額合計を設定する
                settlementDate.setAlternativeSecuritiesTotal(substitute.getSumSubGaku());
            }
            // 代用有価証券入庫（数値(整数)）  の設定
            settlementDate.setCollateralSecurityDeliverIn(substitute.getSubStore());
            
            // 代用有価証券出庫（数値(整数)）  の設定
            settlementDate.setCollateralSecurityDeliverOut(substitute.getSubShip());
            
            //・レスポンス.受渡日（T+0）～受渡日（T+5）リスト.表示基準日の編集
            //以下の内容および並び順でYYYY/MM/DDにAPI001.代用有価証券評価額情報(T+0）～(T+5).受渡日を設定
            //YYYY/MM/DD(当日)
            //YYYY/MM/DD(1営業日後)
            //YYYY/MM/DD(2営業日後)
            //YYYY/MM/DD(3営業日後)
            //YYYY/MM/DD(4営業日後)
            //YYYY/MM/DD(5営業日後)
            if (substitute.getSettleDate().trim().length() == 0) {
                //表示基準日の設定
                settlementDateList.add(null);
            } else {
                StringBuffer editSettleDate = new StringBuffer();
                editSettleDate.append(substitute.getSettleDate().substring(0, 4));
                editSettleDate.append("/");
                editSettleDate.append(substitute.getSettleDate().substring(4, 6));
                editSettleDate.append("/");
                editSettleDate.append(substitute.getSettleDate().substring(6, 8));
                //表示基準日の設定
                switch (index) {
                case 0:
                    settlementDate.setDisplayBaseDate(editSettleDate.toString() + "(当日)");
                    break;
                case 1:
                    settlementDate.setDisplayBaseDate(editSettleDate.toString() + "(1営業日後)");
                    break;
                case 2:
                    settlementDate.setDisplayBaseDate(editSettleDate.toString() + "(2営業日後)");
                    break;
                case 3:
                    settlementDate.setDisplayBaseDate(editSettleDate.toString() + "(3営業日後)");
                    break;
                case 4:
                    settlementDate.setDisplayBaseDate(editSettleDate.toString() + "(4営業日後)");
                    break;
                case 5:
                    settlementDate.setDisplayBaseDate(editSettleDate.toString() + "～(5営業日後)～");
                    break;
                default:
                    throw new IfaRuntimeException(IfaCommonUtil.getMessage(ERRORS_EXCEEDED_MAXIMUM,
                            new String[] { Integer.toString(substituteList.size()) }));
                }
            }
            settlementDateList.add(settlementDate);
        }
        resDto.setSettlementDateList(settlementDateList);
    }
    
    /**
     * A004:API002の戻り値からレスポンスに値を設定する
     *
     * @param butenCode     部店コード
     * @param accountNumber 口座番号
     * @param settleDate    受渡日
     * @param dtoReq        A004のリクエスト
     * @param searchFrom    検索NoFrom
     * @param searchTo      検索NoTo
     * @param api001ResDto  API001のレスポンス
     * @param resDto        A004のレスポンス
     */
    private void setApi002ResDtoToA004ResDto(String butenCode, String accountNumber, String settleDate,
            IfaCollateralSecurityListA004RequestDto dtoReq, String searchFrom, String searchTo,
            QueryAccountSubSecSumOutData api002ResDto, IfaCollateralSecurityListA004ResponseDto resDto)
            throws Exception {
        
        List<IfaCollateralSecurityListA004ResponseDto.Detail> detailList = new ArrayList<IfaCollateralSecurityListA004ResponseDto.Detail>();
        
        //検索結果件数の取得
        if (StringUtils.equals(searchFrom, "00001")) {
            
            //  ・国内株式代用評価金額合計の編集（検索番号指定ＦＲＯＭ＝00001の場合のみ。）
            if (api002ResDto.getStockTotalValue().trim().length() == 0
                    || NUMBER_PATTERN.matcher(api002ResDto.getStockTotalValue().trim()).replaceAll("").length() != 0) {
                
                //  1).API002.国内株式代用評価金額合計＝データなし又はフォーマット不正の場合
                //  '-'を設定する。
                resDto.setDomesticStockCollateralValuationTotal(null);
            } else {
                
                //  2).API002.国内株式代用評価金額合計が上記以外の場合
                //  API002.国内株式代用評価金額合計を設定する
                //  ・国内投信代用評価金額合計の編集（検索番号指定ＦＲＯＭ＝00001の場合のみ。）
                resDto.setDomesticStockCollateralValuationTotal(api002ResDto.getStockTotalValue());
            }
            
            //  ・国内投信代用評価金額合計の編集（検索番号指定ＦＲＯＭ＝00001の場合のみ。）
            if (api002ResDto.getInvestTotalValue().trim().length() == 0
                    || NUMBER_PATTERN.matcher(api002ResDto.getInvestTotalValue().trim()).replaceAll("").length() != 0) {
                
                //  1).API002.国内投信代用評価金額合計＝データなし又はフォーマット不正の場合
                //  '-'を設定する。
                resDto.setDomesticMutualCollateralValuationTotal(null);
            } else {
                
                //  2).API002.国内投信代用評価金額合計が上記以外の場合
                //  API002.国内投信代用評価金額合計を設定する
                resDto.setDomesticMutualCollateralValuationTotal(api002ResDto.getInvestTotalValue());
            }
            
            //  ・代用有価証券評価額合計の編集（検索番号指定ＦＲＯＭ＝00001の場合のみ。）
            if (api002ResDto.getTotalValue().trim().length() == 0
                    || NUMBER_PATTERN.matcher(api002ResDto.getTotalValue().trim()).replaceAll("").length() != 0) {
                
                //  1).API002.代用評価金額合計＝データなし又はフォーマット不正の場合
                //  '-'を設定する。
                resDto.setAlternativeSecuritiesTotal(null);
            } else {
                
                //  2).API002.代用評価金額合計が上記以外の場合
                //  API002.代用評価金額合計を設定する
                resDto.setAlternativeSecuritiesTotal(api002ResDto.getTotalValue());
            }
        }
        for (int index = 0; index < api002ResDto.getSubstituteT().size(); index++) {
            QueryAccountSubSecSumOutVec substitute = api002ResDto.getSubstituteT().get(index);
            
            IfaCollateralSecurityListA004ResponseDto.Detail detail = new IfaCollateralSecurityListA004ResponseDto.Detail();
            substitute.setSecName(trimFullSpace(substitute.getSecName()));
            String secType = substitute.getSecTypeName();
            if (StringUtils.equals(secType, "K0")) {
                //  ・代用有価証券明細部リスト.商品分類の編集
                //  1).API002.商品タイプ（商品区分＋国内外国区分）＝  Ｋ０：国内株式の場合
                //  ”国内株式”を設定
                detail.setSecurityClass("国内株式");
                
                //  ・代用有価証券明細部リスト.銘柄コード編集
                //  API002.商品タイプ（商品区分＋国内外国区分）＝  Ｋ０：国内株式の場合
                //  「API002.会社コードの頭4桁＋API002.新旧区分」を設定
                detail.setBrandCode(substitute.getCompanyCode().substring(0, 4) + substitute.getNewOldId());
                
                //  ・代用有価証券明細部リスト.銘柄名編集
                //  API002.商品タイプ（商品区分＋国内外国区分）＝  Ｋ０：国内株式の場合
                //  1).API002.銘柄名（漢字）が全スペース以外かつ API002.新旧区分が"1"(新株)の場合
                //  「API002.銘柄名（漢字）+""－新株""」を設
                //  2).API002.銘柄名（漢字）が全スペース以外かつ API002.新旧区分が"1"(新株)以外の場合
                //  「API002.銘柄名（漢字）」を設定
                //  3).API002.銘柄名（漢字）が全スペースの場合
                //  「-」を設定
                if (substitute.getSecName().length() != 0) {
                    if (StringUtils.equals(substitute.getNewOldId(), "1")) {
                        detail.setBrandName(substitute.getSecName() + "－新株");
                    } else {
                        detail.setBrandName(substitute.getSecName());
                    }
                } else {
                    detail.setBrandName(null);
                }
            } else if (StringUtils.equals(secType, "T0")) {
                //  ・代用有価証券明細部リスト.商品分類の編集
                //  2).API002.商品タイプ（商品区分＋国内外国区分）＝  Ｔ０：国内投信の場合
                //  ”投資信託”を設定
                detail.setSecurityClass("投資信託");
                
                //  ・代用有価証券明細部リスト.銘柄コード編集
                //  API002.商品タイプ（商品区分＋国内外国区分）＝  Ｔ０：国内投信の場
                //  「API002.回数+"."+API002.号1+API002.号2" 」を設定
                detail.setBrandCode(substitute.getSerNo() + "." + substitute.getSubCode1() + substitute.getSubCode2());
                
                //  ・代用有価証券明細部リスト.銘柄名編集
                //  API002.商品タイプ（商品区分＋国内外国区分）＝  Ｔ０：国内投信の場合、
                //  1).API002.銘柄名（漢字）が全スペース以外の場合
                //  「API002.銘柄名（漢字）」を設定
                //  2).API002.銘柄名（漢字）が全スペースの場合
                //  「-」を設定
                if (substitute.getSecName().length() != 0) {
                    detail.setBrandName(substitute.getSecName());
                } else {
                    detail.setBrandName(null);
                }
            } else {
                detail.setSecurityClass(null);
                detail.setBrandCode(null);
                detail.setBrandName(null);
            }
            
            //  ・代用有価証券明細部リスト.預り区分の編集
            //  「①特定口座区分  ＋  ②口数金額」を設定する
            //  ①特定口座区分の編集
            //  1).API002.非特定預り区分＝  "0"'(特定口座における特定預り)の場合、
            //  ”特定”を設定
            //  2).API002.非特定預り区分＝  "0"'(特定口座における特定預り)以外の場合、
            //  ”一般”を設定
            String specificAccountType = (StringUtils.equals(substitute.getHitokuteiKbn(),
                    IfaCollateralSecurityListServiceImpL.HITOKUTEI_KBN_SPECIFIC_DEPOSIT)) ? "特定" : "一般";
            
            //  ②口数金額の編集
            //  1).API002.商品区分='T'(投信,投信一般口)の場合
            //  ”(口数)”を設定
            //  2).API002.商品区分='Y'(投信累投口)の場合
            //  ”(金額)”を設定
            String mutualFundUnitAmount = "";
            if (StringUtils.equals(substitute.getSecId(), IfaCollateralSecurityListServiceImpL.SEC_ID_GENERAL)) {
                mutualFundUnitAmount = "(口数)";
            } else if (StringUtils.equals(substitute.getSecId(),
                    IfaCollateralSecurityListServiceImpL.SEC_ID_CUMULATIVE)) {
                mutualFundUnitAmount = "(金額)";
            }
            detail.setDepositType(specificAccountType + mutualFundUnitAmount);
            
            //  ・代用有価証券明細部リスト.残高数量の編集
            //  1).API002.残高数量＝  データなし又はフォーマット不正の場合
            //  ”-”を設定
            //  2).API002.残高数量＝  上記以外の場合
            //  API002.残高数量を設定
            String quantity = substitute.getQuantity().trim();
            if (quantity.length() == 0 || NUMBER_PATTERN.matcher(quantity).replaceAll("").length() != 0) {
                detail.setContPosition(null);
            } else {
                detail.setContPosition(quantity);
            }
            
            //    ・代用有価証券明細部リスト.評価単価の編集
            //    API002.評価単価＝  データなし又はフォーマット不正の場合”-”を設定
            //    API002.評価単価＝  上記以外の場合、
            //    API002.評価単価を設定"
            String valuationPrice = substitute.getMarketPrice().trim();
            if (valuationPrice.length() == 0 || NUMBER_PATTERN.matcher(valuationPrice).replaceAll("").length() != 0) {
                detail.setValuationPrice(null);
            } else {
                detail.setValuationPrice(valuationPrice);
            }
            
            //  ・代用有価証券明細部リスト.代用掛目の編集
            //  1).API002.代用掛目＝  全スペースの場合
            //  未設定
            //  2).API002.代用掛目＝  上記以外の場合
            //  API002.代用掛目を設定
            if (substitute.getCollateralRate().trim().length() != 0) {
                detail.setCollateralAssessment(substitute.getCollateralRate());
            }
            
            //  ・代用有価証券明細部リスト.代用評価金額の編集
            //  1).API002.代用評価金額＝  データなし又はフォーマット不正の場合
            //  ”-”を設定
            //  2).API002.代用評価金額＝  上記以外の場合
            //  API002.代用評価金額を設定
            String collateralValue = substitute.getCollateralValue().trim();
            if (collateralValue.length() == 0 || NUMBER_PATTERN.matcher(collateralValue).replaceAll("").length() != 0) {
                detail.setCollateralValuation(null);
            } else {
                detail.setCollateralValuation(substitute.getCollateralValue());
            }
            
            //  ・代用有価証券明細部リスト.担保貸株区分の編集
            //  1).API002.担保貸株区分＝　’1’（担保貸株）の場合
            //  ”担保貸株”を設定
            //  2).API002.担保貸株区分＝　上記以外の場合、
            //  ”-”を設定
            String lsubstituteStockFlg = substitute.getLSubstituteStockFlg().trim();
            if (StringUtils.equals(lsubstituteStockFlg, "1")) {
                detail.setSecurityStockLendingClassification("担保貸株");
            } else {
                detail.setSecurityStockLendingClassification(null);
            }
            
            detailList.add(detail);
        }
        
        resDto.setDetailList(detailList);
    }
    private String trimFullSpace(String str) {
        if (str == null) {
            return "";
        }
        return str.replaceAll("^[\\u3000]+|[\\u3000]+$", "");
    }
}
