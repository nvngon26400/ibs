package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaRegisterInfoDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaRegisterInfoRequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaRegisterInfoResponseSql001Model;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaRegisterInfoResponseSql002Model;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaRegisterInfoResponseSql003Model;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaRegisterInfoResponseSql004Model;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRegisterInfoDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRegisterInfoDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRegisterInfoRegisterInfoDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRegisterInfoHeaderValueDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRegisterInfoValue01Value16DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaRegisterInfoService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.PrivId;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 登録情報
 * 2025/02/21 新規作成
 *
 * @author 大連 苗
 */
@Component(value = "cmpIfaRegisterInfoService")
public class IfaRegisterInfoServiceImpL implements IfaRegisterInfoService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaRegisterInfoServiceImpL.class);
    
    /** 権限チェックエラー値 */
    private static final String AUTH_ERROR_VALUE = "0";
    
    /** 権限チェックエラー  */
    private static final String ERRORS_ACCOUNT_NOT_EXISTS = "errors.butenAccountNotExist";
    
    /** 情報分類枝番 */
    private static final String EXTENSION_DETAIL_CD = "0";
    
    /** 情報分類コード:ER{24,1}銀行引落サービス */
    private static final String EXTENSION_CD_24 = "24";
    private static final String EXTENSION_DETAIL_CD_1 = "1";
    
    /** 情報分類コード:ER{1,0}顧客追加情報、ER{1,7}自動引落金融機関 */
    private static final String EXTENSION_CD_1 = "1";
    private static final String EXTENSION_DETAIL_CD_7 = "7";
    private static final String EXTENSION_DETAIL_CD_0 = "0";
    
    /** 分類名補足説明ER{1,0} */
    private static final String REMARKS_1_0 = "顧客情報の補足項目(<B>バーチャルアカウント</B>、<B>コンプラランク</B>、<B>加入者口座コード</B><FONT color=red>※</FONT>、"
                                            + "<B>マイナンバー</B><FONT color=red>※</FONT><BR>、<B>開設支店</B>、<B>仲介業者</B>など)を表示します。"
                                            + "<BR><FONT color=red>※加入者口座コードの機構通知状況が「未」の場合には申込み画面から登録必須。"
                                            + "</FONT><BR><FONT color=red>※マイナンバー受入「未」の場合には、受入必須。</FONT>";
   
    /** 登録情報分類名称ER{1,7} */
    private static final String EXTENSION_NAME_1_7 = "【書面】銀行引落サービス";
    
    /** 分類名補足説明ER{1,7} */
    private static final String REMARKS_1_7 = "書面による銀行引落サービスの詳細情報。";
    
    /** 登録情報分類名称ER{24,1} */
    private static final String EXTENSION_NAME_24_1 = "【WEB】銀行引落サービス";
    
    /** 分類名補足説明ER{24,1} */
    private static final String REMARKS_24_1 = "WEBによる銀行引落サービスの詳細情報。";
    
    @Autowired
    private IfaRegisterInfoDao dao;
    
    @Autowired
    private Fct001 fct001;
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaRegisterInfoDtoRequest
     * Dto レスポンス：IfaRegisterInfoDtoResponse
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    public DataList<IfaRegisterInfoDtoResponse> initializeA001(IfaRegisterInfoDtoRequest dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaRegisterInfoServicepl.initializeA001");
        }

        DataList<IfaRegisterInfoDtoResponse> dtoRes = new DataList<IfaRegisterInfoDtoResponse>();
        List<IfaRegisterInfoDtoResponse> resList = new ArrayList<IfaRegisterInfoDtoResponse>();

        // ①：利用者の口座に対する権限チェックを行う。
        // 顧客情報の取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        // 顧客共通情報.部店コード
        String butenCode = customerCommon.getButenCode();
        // 顧客共通情報.口座番号
        String accountNumber = customerCommon.getAccountNumber();

        //  権限あり（対象顧客参照権限有無＝"1"）：次の処理へ。
        //  権限なし（対象顧客参照権限有無＝"0"：権限なしエラーを返す。
        InputFct001Dto fct001InData = new InputFct001Dto();
        fct001InData.setButenCode(butenCode);
        fct001InData.setAccountNumber(accountNumber);
        OutputFct001Dto fct001Dto = fct001.doCheck(fct001InData);
        if (fct001Dto == null || StringUtils.equals(fct001Dto.getTargetCustomerRefAuthFlag(),AUTH_ERROR_VALUE)) {
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.FATAL,ERRORS_ACCOUNT_NOT_EXISTS,
                    IfaCommonUtil.getMessage(ERRORS_ACCOUNT_NOT_EXISTS,new String[] { butenCode, accountNumber }));
            return dtoRes;
        }

        
        // 分類一覧エリア
        List<IfaRegisterInfoRegisterInfoDtoResponse> categoryExtensionNameList = new ArrayList<IfaRegisterInfoRegisterInfoDtoResponse>();
        List<IfaRegisterInfoRegisterInfoDtoResponse> categoryExtensionNameListSql002 = new ArrayList<IfaRegisterInfoRegisterInfoDtoResponse>();
        List<IfaRegisterInfoRegisterInfoDtoResponse> categoryExtensionNameListSql003 = new ArrayList<IfaRegisterInfoRegisterInfoDtoResponse>();
        IfaRegisterInfoRegisterInfoDtoResponse category24 = new IfaRegisterInfoRegisterInfoDtoResponse();
        // 登録情報詳細情報01~16
        List<IfaRegisterInfoValue01Value16DtoResponse> registerInfoValueList = new ArrayList<IfaRegisterInfoValue01Value16DtoResponse>();
        List<IfaRegisterInfoValue01Value16DtoResponse> registerInfoValueListSql002 = new ArrayList<IfaRegisterInfoValue01Value16DtoResponse>();
        List<IfaRegisterInfoValue01Value16DtoResponse> registerInfoValueListSql004 = new ArrayList<IfaRegisterInfoValue01Value16DtoResponse>();
        List<IfaRegisterInfoValue01Value16DtoResponse> valueList_Sql4_1_7 = new ArrayList<IfaRegisterInfoValue01Value16DtoResponse>();
        List<IfaRegisterInfoValue01Value16DtoResponse> valueList24 = new ArrayList<IfaRegisterInfoValue01Value16DtoResponse>();

        // 登録情報ヘッダ情報リスト
        List<IfaRegisterInfoHeaderValueDtoResponse> registerInfoSqHeaderList = new ArrayList<IfaRegisterInfoHeaderValueDtoResponse>();
        List<IfaRegisterInfoHeaderValueDtoResponse> registerInfoSqHeaderListSql002 = new ArrayList<IfaRegisterInfoHeaderValueDtoResponse>();
        List<IfaRegisterInfoHeaderValueDtoResponse> registerInfoSqHeaderListSql004 = new ArrayList<IfaRegisterInfoHeaderValueDtoResponse>();
        IfaRegisterInfoHeaderValueDtoResponse header_Sql4_1_7 = new IfaRegisterInfoHeaderValueDtoResponse();
        List<IfaRegisterInfoHeaderValueDtoResponse> headerList24 = new ArrayList<IfaRegisterInfoHeaderValueDtoResponse>();
        
        // SQL001~SQL004のリクエスト値を設定
        IfaRegisterInfoRequestModel selReq = new IfaRegisterInfoRequestModel();

        // SQL001のコード2
        List<String> extensionCdListSql001 = new ArrayList<String>();
        // SQL002情報分類コード
        List<String> extensionCdListSql002 = new ArrayList<String>();
        // SQL004情報分類コード
        List<String> extensionCdListSql004 = new ArrayList<String>();

        IfaRegisterInfoDtoResponse res = new IfaRegisterInfoDtoResponse();


        // Sql001:優先表示分類情報取得
        DataList<IfaRegisterInfoResponseSql001Model> selSql001ResList = new DataList<IfaRegisterInfoResponseSql001Model>();
        selSql001ResList = dao.selectIfaRegisterInfoSql001(selReq);

        // Sql001 レスポンスの設定
        for (IfaRegisterInfoResponseSql001Model selSql001Res : selSql001ResList.getDataList()) {
            // 分類コードの情報分類コード Sql001
            extensionCdListSql001.add(selSql001Res.getCategoryExtensionCd());
        }

        Boolean code24inSql1 = false;
        Boolean code1inSql1 = false;
        // 情報分類コード=24のデータはSQL1に存在するかを判断
        if(extensionCdListSql001.contains(EXTENSION_CD_24)) {
            // Sql001で取得した情報分類コード
            selReq.setCategoryExtensionCdList(extensionCdListSql001);
            code24inSql1 =true;
        } else {
            // Sql001で取得した情報分類コード + 情報分類コード = 24
            extensionCdListSql001.add(EXTENSION_CD_24);
            selReq.setCategoryExtensionCdList(extensionCdListSql001);
        }

        // 情報分類コード=1のデータはSQL1に存在するかを判断
        if(extensionCdListSql001.contains(EXTENSION_CD_1)) {
            code1inSql1 =true;
        }
        
        
        // Sql002:優先表示分類ヘッダ取得
        DataList<IfaRegisterInfoResponseSql002Model> selSql002ResList = new DataList<IfaRegisterInfoResponseSql002Model>();
        selSql002ResList = dao.selectIfaRegisterInfoSql002(selReq);

        // Sql002 レスポンスの設定
        for (IfaRegisterInfoResponseSql002Model selSql002Res : selSql002ResList.getDataList()) {
            //情報分類枝番 = 0(分類の属性)
            if(EXTENSION_DETAIL_CD.equals(selSql002Res.getExtensionDetailCd())) {

                IfaRegisterInfoRegisterInfoDtoResponse registerInfoSql002 = new IfaRegisterInfoRegisterInfoDtoResponse();

                // 分類一覧コード
                registerInfoSql002.setCategoryExtensionCd(Integer.valueOf(selSql002Res.getInforExtensionCd()));
                // 類一覧名称
                registerInfoSql002.setCategoryExtensionName(selSql002Res.getInforExtensionName());
                // 順序
                registerInfoSql002.setDisplaySeq(Integer.valueOf(selSql002Res.getDisplaySeq()));
                
                // ER{1,0}顧客追加情報
                if(EXTENSION_CD_1.equals(selSql002Res.getInforExtensionCd()) && 
                        EXTENSION_DETAIL_CD_0.equals(selSql002Res.getExtensionDetailCd())) {

                    // 分類名補足説明
                    registerInfoSql002.setRemarks(REMARKS_1_0);
                } else {
                    // 分類名補足説明
                    registerInfoSql002.setRemarks(selSql002Res.getRemarks());
                }

                // 分類エリアに表示する情報に設定する Sql002
                categoryExtensionNameListSql002.add(registerInfoSql002);
                // 分類コードの情報分類コード Sql002
                extensionCdListSql002.add(selSql002Res.getInforExtensionCd());
                
                // 分類コードの情報分類コード:24
                if(EXTENSION_CD_24.equals(selSql002Res.getInforExtensionCd())) {
                    category24 = registerInfoSql002;
                }
                
                
            }else if(!(EXTENSION_CD_1.equals(selSql002Res.getInforExtensionCd()) && EXTENSION_DETAIL_CD_7.equals(selSql002Res.getExtensionDetailCd()))){
               // 登録情報の情報分類コード: ≠ ER{1,7}自動引落金融機関

                IfaRegisterInfoValue01Value16DtoResponse sql002Value01Value16 = new IfaRegisterInfoValue01Value16DtoResponse();
                IfaRegisterInfoHeaderValueDtoResponse sql002Header = new IfaRegisterInfoHeaderValueDtoResponse();

                // 登録情報詳細情報01~16に設定する
                sql002Value01Value16=this.getValueNull(selSql002Res);
                registerInfoValueListSql002.add(sql002Value01Value16);

                // 登録情報ヘッダ情報に設定する
                sql002Header = this.getRegisterInfoSqHeader002List(selSql002Res);
                registerInfoSqHeaderListSql002.add(sql002Header);
                
                // 登録情報の情報分類コード:24
                if(EXTENSION_CD_24.equals(selSql002Res.getInforExtensionCd())) {
                    valueList24.add(sql002Value01Value16);
                    headerList24.add(sql002Header);
                  }
            }
            
            
        }
        // 登録情報ヘッダ情報の重複データ削除 Sql002
        registerInfoSqHeaderListSql002= registerInfoSqHeaderListSql002.stream().distinct().collect(Collectors.toList());
        
        
        // Sql003:分類情報一覧取得
        DataList<IfaRegisterInfoResponseSql003Model> selSql003ResList = new DataList<IfaRegisterInfoResponseSql003Model>();
        //顧客共通情報.顧客コード
        selReq.setCustomerId(customerCommon.getCustomerCode());
        selSql003ResList = dao.selectIfaRegisterInfoSql003(selReq);


        // Sql003 レスポンスの設定
        for (IfaRegisterInfoResponseSql003Model selSql003Res : selSql003ResList.getDataList()) {

            IfaRegisterInfoRegisterInfoDtoResponse registerInfoSql003 = new IfaRegisterInfoRegisterInfoDtoResponse();

            // 分類一覧コード
            registerInfoSql003.setCategoryExtensionCd(Integer.valueOf(selSql003Res.getCategoryExtensionCd()));
            // 類一覧名称
            registerInfoSql003.setCategoryExtensionName(selSql003Res.getCategoryExtensionName());
            // 順序
            registerInfoSql003.setDisplaySeq(Integer.valueOf(selSql003Res.getDisplaySeq()));
            
            // ER{1,0}顧客追加情報
            if(EXTENSION_CD_1.equals(selSql003Res.getCategoryExtensionCd())) {

                // 分類名補足説明
                registerInfoSql003.setRemarks(REMARKS_1_0);
            } else {
                // 分類名補足説明
                registerInfoSql003.setRemarks(selSql003Res.getRemarks());
            }
            
            // 分類エリアに表示する情報に設定する
            categoryExtensionNameListSql003.add(registerInfoSql003);
        }


        // ⑥： 登録情報エリアの情報を検索する
        // Sql004:登録情報取得
        DataList<IfaRegisterInfoResponseSql004Model> selSql004ResList = new DataList<IfaRegisterInfoResponseSql004Model>();

        // 顧客共通情報.顧客コード
        selReq.setCustomerId(customerCommon.getCustomerCode());
        selSql004ResList = dao.selectIfaRegisterInfoSql004(selReq);
        
        int valueCount_1 = 0;
        int valueCount_1_7 = 0;
        // Sql004 レスポンスの設定
        for (IfaRegisterInfoResponseSql004Model selSql004Res : selSql004ResList.getDataList()) {

            IfaRegisterInfoValue01Value16DtoResponse sql004Value01Value16 = new IfaRegisterInfoValue01Value16DtoResponse();
            IfaRegisterInfoHeaderValueDtoResponse sql004Header = new IfaRegisterInfoHeaderValueDtoResponse();

            // 登録情報詳細情報01~16を取得
            sql004Value01Value16=this.getValue01Value16(selSql004Res);
            // 登録情報ヘッダ情報を取得
            sql004Header = this.getRegisterInfoSqHeader004List(selSql004Res);
            
            // 情報分類コード = 1 and  情報分類枝番 >0 の件数
            if(EXTENSION_CD_1.equals(selSql004Res.getInforExtensionCd()) && EXTENSION_DETAIL_CD_7.equals(selSql004Res.getExtensionDetailCd())) {
                // ER{1,7}自動引落金融機関 のデータが存在する
                valueList_Sql4_1_7.add(sql004Value01Value16);
                header_Sql4_1_7= sql004Header;
                
                //ER{1,7}の件数
                valueCount_1_7++;

          } else {
              // 登録情報詳細情報01~16に設定する
              registerInfoValueListSql004.add(sql004Value01Value16);
              // 登録情報ヘッダ情報に設定する
              registerInfoSqHeaderListSql004.add(sql004Header);
          }

            
            // 情報分類コード = 1 の件数
            if(EXTENSION_CD_1.equals(selSql004Res.getInforExtensionCd())) {
                  valueCount_1++;
            }
            
            // SQL004の全て情報分類コード
            extensionCdListSql004.add(selSql004Res.getInforExtensionCd());
        }

        // ※※※※※以下は特殊処理※※※※※
         // 情報分類コード=24のデータはSQL1に存在しない、SQL2に存在する：（24：第二優先）
         if(!code24inSql1 && extensionCdListSql002.contains(EXTENSION_CD_24)){
             
             Boolean code24 = false;
             // SQL3（第二優先）に情報分類コード=24のデータが存在すれば、SQL2の24を削除する
             for(IfaRegisterInfoRegisterInfoDtoResponse extensionCd003:categoryExtensionNameListSql003) {
                 if(EXTENSION_CD_24.equals(String.valueOf(extensionCd003.getCategoryExtensionCd()))) {
                     // SQL2のER{24,0}を削除する
                     categoryExtensionNameListSql002.remove(category24);

                     code24=true;
                 }
             }
             
             // SQL3（第二優先）に情報分類コード=24のデータが存在しなければ、
             // SQL2のER{24,0}のレコードに対し、第二優先のルールに従って分類情報を差し込む
             if(!code24) {
                 // ER{1,7}のデータが存在する場合、ER{24,0}を表示する
                 if(valueCount_1_7 > 0) {

                     // SQL2のER{24,0}を削除する
                     categoryExtensionNameListSql002.remove(category24);
                     // SQL3のER{24,0}を追加する
                     categoryExtensionNameListSql003.add(category24);

                     // SQL2のER{24,xx}を削除する
                     // ヘッダ情報
                     for(IfaRegisterInfoHeaderValueDtoResponse header24:headerList24) {
                         registerInfoSqHeaderListSql002.remove(header24);
                     }
                     // 詳細情報01~16
                     for(IfaRegisterInfoValue01Value16DtoResponse value24:valueList24) {
                         registerInfoValueListSql002.remove(value24);
                     }
                     
                     // 下記の条件で並び替えて表示する
                     // DISPLAY_SEQ昇順 > EXTENSION_CD昇順
                     categoryExtensionNameListSql003.sort(Comparator
                             .comparing(IfaRegisterInfoRegisterInfoDtoResponse::getDisplaySeq)
                             .thenComparing(IfaRegisterInfoRegisterInfoDtoResponse::getCategoryExtensionCd));
                 }else {
                     // ER{1,7}のデータが存在しない場合、ER{24,0}を非表示する
                     // SQL2のER{24,0}を削除する
                     categoryExtensionNameListSql002.remove(category24);
                     // SQL2のER{24,xx}を削除する
                     // ヘッダ情報
                     for(IfaRegisterInfoHeaderValueDtoResponse header24:headerList24) {
                         registerInfoSqHeaderListSql002.remove(header24);
                     }
                     // 詳細情報01~16
                     for(IfaRegisterInfoValue01Value16DtoResponse value24:valueList24) {
                         registerInfoValueListSql002.remove(value24);
                     }
                 }
             }
         }

         // 第一優先 コード1昇順
         List<IfaRegisterInfoRegisterInfoDtoResponse> categoryExtensionNameListSql002Sort = new ArrayList<IfaRegisterInfoRegisterInfoDtoResponse>();
         for(String code001:extensionCdListSql001) {
             for(IfaRegisterInfoRegisterInfoDtoResponse code002:categoryExtensionNameListSql002) {
                 if(code001.equals(String.valueOf(code002.getCategoryExtensionCd()))){
                     categoryExtensionNameListSql002Sort.add(code002);
                 }
             }
         }
         
         // ※※※※分類エリアの全て情報※※※※
         categoryExtensionNameList.addAll(categoryExtensionNameListSql002Sort);
         categoryExtensionNameList.addAll(categoryExtensionNameListSql003);
         // 分類エリアの重複データ削除 : SQL002とSQL003は取得できた同じ情報分類コードの場合、SQL002のデータを保留、SQL003のデータを除外
         categoryExtensionNameList = categoryExtensionNameList.stream().distinct().collect(Collectors.toList());

         // EXTENSION_CD=1　⇒　第二優先
         // SQL4で取得したEXTENSION_CD=1のレコード内にER{1,7}のみが含まれる場合、EXTENSION_CD=1の分類は表示から除外する。
         // 分類一覧エリア
         List<IfaRegisterInfoRegisterInfoDtoResponse> categoryExtensionNameListEnd = new ArrayList<IfaRegisterInfoRegisterInfoDtoResponse>();
         categoryExtensionNameListEnd.addAll(categoryExtensionNameList);
         if(!code1inSql1 && valueCount_1_7 == valueCount_1) {
             for(IfaRegisterInfoRegisterInfoDtoResponse categoryExtensionName : categoryExtensionNameList) {
                 // EXTENSION_CD = 1のレコード、除外する。
                 if(EXTENSION_CD_1.equals(String.valueOf(categoryExtensionName.getCategoryExtensionCd()))) {
                     categoryExtensionNameListEnd.remove(categoryExtensionName);
                 }
             }
         }


         // ※※※※※登録情報エリアの全て情報※※※※※
         // 全て登録情報詳細情報01~16
         registerInfoValueList.addAll(registerInfoValueListSql002);
         registerInfoValueList.addAll(registerInfoValueListSql004);
         // 全て登録情報ヘッダ情報
         registerInfoSqHeaderList.addAll(registerInfoSqHeaderListSql002);
         registerInfoSqHeaderList.addAll(registerInfoSqHeaderListSql004);
         // 登録情報ヘッダ情報の重複データを削除
         registerInfoSqHeaderList = registerInfoSqHeaderList.stream().distinct().collect(Collectors.toList());
        
        
         //  登録情報エリア: ヘッダ情報 + value01~16リスト
         // ER{24,1}のデータ存在するか判断
         Boolean code24_1 = false;
        List<IfaRegisterInfoHeaderValueDtoResponse> registerInfoHeaderValueListEnd = new ArrayList<IfaRegisterInfoHeaderValueDtoResponse>();
        for(IfaRegisterInfoHeaderValueDtoResponse header : registerInfoSqHeaderList) {
            // value列件数
            int valueCount = 0;
            List<IfaRegisterInfoValue01Value16DtoResponse> value01Value16List01 = new ArrayList<IfaRegisterInfoValue01Value16DtoResponse>();
            List<IfaRegisterInfoValue01Value16DtoResponse> value01Value16List02 = new ArrayList<IfaRegisterInfoValue01Value16DtoResponse>();
            //登録情報詳細情報01~16に設定する
            for(IfaRegisterInfoValue01Value16DtoResponse value : registerInfoValueList) {

                // 情報分類枝番、登録情報分類コードと同じ
                if(header.getInforExtensionCd().equals(value.getInforExtensionCd())
                        && header.getExtensionDetailCd().equals(value.getExtensionDetailCd())) {

                    value01Value16List01.add(value);
                    valueCount++;
                }
            }
            value01Value16List02.addAll(value01Value16List01);
            
            // SQL002とSQL004は取得できた同じ情報分類コードの場合、SQL002のデータを除外、SQL004のデータを保留
            if(valueCount > 1) {
                for(IfaRegisterInfoValue01Value16DtoResponse value01:value01Value16List01) {
                    // 詳細情報01 not null
                    if(StringUtils.equals(value01.getDetailValue01(), null)) {
                        // SQL002のVALUEデータを除外
                        value01Value16List02.remove(value01);
                        
                        valueCount--;
                    }
                }
            }
            
            // 登録情報詳細情報01~16
            header.setRegisterInfoValueList(value01Value16List02);
            // 登録情報分類列件数
            header.setValueCount(valueCount);
            // 登録情報 ヘッダ情報 + value01~16リスト
            registerInfoHeaderValueListEnd.add(header);
            
            
            // ER{1,7}=顧客追加情報.自動引落金融機関　、ER{24,1}=銀行引落サービス.引落属性はオウンコーディング
            // ER{24,1}のデータが存在する
            if(EXTENSION_CD_24.equals(header.getInforExtensionCd()) && EXTENSION_DETAIL_CD_1.equals(header.getExtensionDetailCd())) {
              // Sql4のER{1,7}=顧客追加情報.自動引落金融機関追加
              header_Sql4_1_7.setRegisterInfoValueList(valueList_Sql4_1_7);
              // VALUE 列件数
              header_Sql4_1_7.setValueCount(valueList_Sql4_1_7.size());
              registerInfoHeaderValueListEnd.add(header_Sql4_1_7);
              
              code24_1=true;
            }
         }
        
        // ER{24,1}のデータが存在しない
        // ER{1,7}=顧客追加情報.自動引落金融機関追加： R{24,x}の最後尾の次に表示
        if(!code24_1) {
        
            // ER{1,7}=顧客追加情報.自動引落金融機関追加
            header_Sql4_1_7.setRegisterInfoValueList(valueList_Sql4_1_7);
            // VALUE 列件数
            header_Sql4_1_7.setValueCount(valueList_Sql4_1_7.size());
            registerInfoHeaderValueListEnd.add(header_Sql4_1_7);
        }
        
        // SQL002、SQL004情報分類コード
        extensionCdListSql004.addAll(extensionCdListSql002);
        // 情報分類コードは情報分類コード分類一覧エリアに存在して、登録情報エリアに存在しない場合
        // 分類一覧エリア
        List<IfaRegisterInfoRegisterInfoDtoResponse> extensionList = new ArrayList<IfaRegisterInfoRegisterInfoDtoResponse>();
        for(IfaRegisterInfoRegisterInfoDtoResponse extension : categoryExtensionNameListEnd) {
            // 分類一覧エリアの情報分類コードは登録情報エリアに存在する場合
            if (extensionCdListSql004.contains(String.valueOf(extension.getCategoryExtensionCd()))) {
                extensionList.add(extension);
            }
        }
        
        // 分類一覧エリア
        res.setCategoryExtensionNameList(extensionList);
        // 登録情報
        res.setRegisterInfoHeaderValueList(registerInfoHeaderValueListEnd);

        // CCSログインIDとして設定する文字列の生成 （ユーザID用設定値）
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        String privId = userAccount.getPrivId();
        String ccsOpId = userAccount.getUserId();
        if (StringUtils.equals(privId, PrivId.HEAD_OFFICE.getId()) || StringUtils.equals(privId, PrivId.BRANCH.getId())) {
            ccsOpId = ccsOpId.length() >= 7 ? ccsOpId.substring(ccsOpId.length()-7) + "@" : ccsOpId + "@";
        } else {
            ccsOpId = ccsOpId.length() >= 8 ? ccsOpId.substring(ccsOpId.length()-8) : ccsOpId;
        }
        // ユーザID用設定値
        res.setCcsOpId(ccsOpId);

        resList.add(res);
        dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");

        return dtoRes;
    }

    
    /**
     * 登録情報詳細情報取得002
     *
     * @param selSql002Res リクエスト
     * @return 登録情報詳細情報リスト
     */
    private IfaRegisterInfoValue01Value16DtoResponse getValueNull(IfaRegisterInfoResponseSql002Model selSql002Res) {

        IfaRegisterInfoValue01Value16DtoResponse registerInfoValue = new IfaRegisterInfoValue01Value16DtoResponse();

        // 情報分類枝番
        registerInfoValue.setExtensionDetailCd(selSql002Res.getExtensionDetailCd());
        // 登録情報分類コード
        registerInfoValue.setInforExtensionCd(selSql002Res.getInforExtensionCd());
        // 詳細情報01
        registerInfoValue.setDetailValue01(null);

        return registerInfoValue;
    }

    /**
     * 登録情報詳細情報取得004
     *
     * @param value リクエスト
     * @return 登録情報詳細情報リスト
     */
    private IfaRegisterInfoValue01Value16DtoResponse getValue01Value16(
            IfaRegisterInfoResponseSql004Model value) {

        IfaRegisterInfoValue01Value16DtoResponse registerInfoValue = new IfaRegisterInfoValue01Value16DtoResponse();

        // 情報分類枝番
        registerInfoValue.setExtensionDetailCd(value.getExtensionDetailCd());
        // 登録情報分類コード
        registerInfoValue.setInforExtensionCd(value.getInforExtensionCd());
        // 詳細情報01
        registerInfoValue.setDetailValue01(value.getDetailValue01());
        // 詳細情報02
        registerInfoValue.setDetailValue02(value.getDetailValue02());
        // 詳細情報03
        registerInfoValue.setDetailValue03(value.getDetailValue03());
        // 詳細情報04
        registerInfoValue.setDetailValue04(value.getDetailValue04());
        // 詳細情報05
        registerInfoValue.setDetailValue05(value.getDetailValue05());
        // 詳細情報06
        registerInfoValue.setDetailValue06(value.getDetailValue06());
        // 詳細情報07
        registerInfoValue.setDetailValue07(value.getDetailValue07());
        // 詳細情報08
        registerInfoValue.setDetailValue08(value.getDetailValue08());
        // 詳細情報09
        registerInfoValue.setDetailValue09(value.getDetailValue09());
        // 詳細情報10
        registerInfoValue.setDetailValue10(value.getDetailValue10());
        // 詳細情報11
        registerInfoValue.setDetailValue11(value.getDetailValue11());
        // 詳細情報12
        registerInfoValue.setDetailValue12(value.getDetailValue12());
        // 詳細情報13
        registerInfoValue.setDetailValue13(value.getDetailValue13());
        // 詳細情報14
        registerInfoValue.setDetailValue14(value.getDetailValue14());
        // 詳細情報15
        registerInfoValue.setDetailValue15(value.getDetailValue15());
        // 詳細情報16
        registerInfoValue.setDetailValue16(value.getDetailValue16());

        return registerInfoValue;
    }


  /**
   * 登録情報ヘッダ情報取得002
   *
   * @param registerInfo リクエスト
   * @return 登録情報ヘッダ情報リスト
   */
    private IfaRegisterInfoHeaderValueDtoResponse getRegisterInfoSqHeader002List(
            IfaRegisterInfoResponseSql002Model registerInfo) {

        IfaRegisterInfoHeaderValueDtoResponse registerInfoSqHeader = new IfaRegisterInfoHeaderValueDtoResponse();

        // 情報分類枝番
        registerInfoSqHeader.setExtensionDetailCd(registerInfo.getExtensionDetailCd());
        // 登録情報分類コード
        registerInfoSqHeader.setInforExtensionCd(registerInfo.getInforExtensionCd());
        
        // ER{1,7}自動引落金融機関
        if(EXTENSION_CD_1.equals(registerInfo.getInforExtensionCd()) && 
                EXTENSION_DETAIL_CD_7.equals(registerInfo.getExtensionDetailCd())) {
            // 登録情報分類名称
            registerInfoSqHeader.setInforExtensionName(EXTENSION_NAME_1_7);
            // 分類名補足説明
            registerInfoSqHeader.setRemarks(REMARKS_1_7);
            
        } else if(EXTENSION_CD_24.equals(registerInfo.getInforExtensionCd()) && 
                EXTENSION_DETAIL_CD_1.equals(registerInfo.getExtensionDetailCd())) {
            // ER{24,1}銀行引落サービス
            // 登録情報分類名称
            registerInfoSqHeader.setInforExtensionName(EXTENSION_NAME_24_1);
            // 分類名補足説明
            registerInfoSqHeader.setRemarks(REMARKS_24_1);
        } else {

            // 登録情報分類名称
            registerInfoSqHeader.setInforExtensionName(registerInfo.getInforExtensionName());
            // 分類名補足説明
            registerInfoSqHeader.setRemarks(registerInfo.getRemarks());
        }

        // 縦の最大行数
        registerInfoSqHeader.setMaxColumn(registerInfo.getMaxColumn());
        // 詳細情報ヘッダ01
        registerInfoSqHeader.setHeaderValue01(registerInfo.getHeaderValue01());
        // 詳細情報ヘッダ02
        registerInfoSqHeader.setHeaderValue02(registerInfo.getHeaderValue02());
        // 詳細情報ヘッダ03
        registerInfoSqHeader.setHeaderValue03(registerInfo.getHeaderValue03());
        // 詳細情報ヘッダ04
        registerInfoSqHeader.setHeaderValue04(registerInfo.getHeaderValue04());
        // 詳細情報ヘッダ05
        registerInfoSqHeader.setHeaderValue05(registerInfo.getHeaderValue05());
        // 詳細情報ヘッダ06
        registerInfoSqHeader.setHeaderValue06(registerInfo.getHeaderValue06());
        // 詳細情報ヘッダ07
        registerInfoSqHeader.setHeaderValue07(registerInfo.getHeaderValue07());
        // 詳細情報ヘッダ08
        registerInfoSqHeader.setHeaderValue08(registerInfo.getHeaderValue08());
        // 詳細情報ヘッダ09
        registerInfoSqHeader.setHeaderValue09(registerInfo.getHeaderValue09());
        // 詳細情報ヘッダ10
        registerInfoSqHeader.setHeaderValue10(registerInfo.getHeaderValue10());
        // 詳細情報ヘッダ11
        registerInfoSqHeader.setHeaderValue11(registerInfo.getHeaderValue11());
        // 詳細情報ヘッダ12
        registerInfoSqHeader.setHeaderValue12(registerInfo.getHeaderValue12());
        // 詳細情報ヘッダ13
        registerInfoSqHeader.setHeaderValue13(registerInfo.getHeaderValue13());
        // 詳細情報ヘッダ14
        registerInfoSqHeader.setHeaderValue14(registerInfo.getHeaderValue14());
        // 詳細情報ヘッダ15
        registerInfoSqHeader.setHeaderValue15(registerInfo.getHeaderValue15());
        // 詳細情報ヘッダ16
        registerInfoSqHeader.setHeaderValue16(registerInfo.getHeaderValue16());

        return registerInfoSqHeader;
    }

    
    
  /**
   * 登録情報ヘッダ情報取得004
   *
   * @param registerInfo リクエスト
   * @return 登録情報ヘッダ情報リスト
   */
    private IfaRegisterInfoHeaderValueDtoResponse getRegisterInfoSqHeader004List(
            IfaRegisterInfoResponseSql004Model registerInfo) {

        IfaRegisterInfoHeaderValueDtoResponse registerInfoSqHeader = new IfaRegisterInfoHeaderValueDtoResponse();
        
        // 情報分類枝番
        registerInfoSqHeader.setExtensionDetailCd(registerInfo.getExtensionDetailCd());
        // 登録情報分類コード
        // 分類コードの情報分類コード:ER{1,7}自動引落金融機関
        if(EXTENSION_CD_1.equals(registerInfo.getInforExtensionCd()) && EXTENSION_DETAIL_CD_7.equals(registerInfo.getExtensionDetailCd())) {
             // 情報分類コード = 1 ⇒24に変更
            registerInfoSqHeader.setInforExtensionCd(EXTENSION_CD_24);
        } else {
            registerInfoSqHeader.setInforExtensionCd(registerInfo.getInforExtensionCd());
        }
        
        // ER{1,7}自動引落金融機関
        if(EXTENSION_CD_1.equals(registerInfo.getInforExtensionCd()) && 
                EXTENSION_DETAIL_CD_7.equals(registerInfo.getExtensionDetailCd())) {
            // 登録情報分類名称
            registerInfoSqHeader.setInforExtensionName(EXTENSION_NAME_1_7);
            // 分類名補足説明
            registerInfoSqHeader.setRemarks(REMARKS_1_7);
            
        } else if(EXTENSION_CD_24.equals(registerInfo.getInforExtensionCd()) && 
                EXTENSION_DETAIL_CD_1.equals(registerInfo.getExtensionDetailCd())) {
            // ER{24,1}銀行引落サービス
            // 登録情報分類名称
            registerInfoSqHeader.setInforExtensionName(EXTENSION_NAME_24_1);
            // 分類名補足説明
            registerInfoSqHeader.setRemarks(REMARKS_24_1);
        } else {

            // 登録情報分類名称
            registerInfoSqHeader.setInforExtensionName(registerInfo.getInforExtensionName());
            // 分類名補足説明
            registerInfoSqHeader.setRemarks(registerInfo.getRemarks());
        }
        // 縦の最大行数
        registerInfoSqHeader.setMaxColumn(registerInfo.getMaxColumn());
        // 詳細情報ヘッダ01
        registerInfoSqHeader.setHeaderValue01(registerInfo.getHeaderValue01());
        // 詳細情報ヘッダ02
        registerInfoSqHeader.setHeaderValue02(registerInfo.getHeaderValue02());
        // 詳細情報ヘッダ03
        registerInfoSqHeader.setHeaderValue03(registerInfo.getHeaderValue03());
        // 詳細情報ヘッダ04
        registerInfoSqHeader.setHeaderValue04(registerInfo.getHeaderValue04());
        // 詳細情報ヘッダ05
        registerInfoSqHeader.setHeaderValue05(registerInfo.getHeaderValue05());
        // 詳細情報ヘッダ06
        registerInfoSqHeader.setHeaderValue06(registerInfo.getHeaderValue06());
        // 詳細情報ヘッダ07
        registerInfoSqHeader.setHeaderValue07(registerInfo.getHeaderValue07());
        // 詳細情報ヘッダ08
        registerInfoSqHeader.setHeaderValue08(registerInfo.getHeaderValue08());
        // 詳細情報ヘッダ09
        registerInfoSqHeader.setHeaderValue09(registerInfo.getHeaderValue09());
        // 詳細情報ヘッダ10
        registerInfoSqHeader.setHeaderValue10(registerInfo.getHeaderValue10());
        // 詳細情報ヘッダ11
        registerInfoSqHeader.setHeaderValue11(registerInfo.getHeaderValue11());
        // 詳細情報ヘッダ12
        registerInfoSqHeader.setHeaderValue12(registerInfo.getHeaderValue12());
        // 詳細情報ヘッダ13
        registerInfoSqHeader.setHeaderValue13(registerInfo.getHeaderValue13());
        // 詳細情報ヘッダ14
        registerInfoSqHeader.setHeaderValue14(registerInfo.getHeaderValue14());
        // 詳細情報ヘッダ15
        registerInfoSqHeader.setHeaderValue15(registerInfo.getHeaderValue15());
        // 詳細情報ヘッダ16
        registerInfoSqHeader.setHeaderValue16(registerInfo.getHeaderValue16());

        return registerInfoSqHeader;
    }
    
}
