package com.sbisec.helios.ap.internalAdminMenu.formAcquire.service.impl;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.IfaBrokerageSubLedgerAcquireDao;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaBrokerageSubLedgerAcquireSql001RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaBrokerageSubLedgerAcquireSql001ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaBrokerageSubLedgerAcquireSql002RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaBrokerageSubLedgerAcquireSql002ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaBrokerageSubLedgerAcquireSql003RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaBrokerageSubLedgerAcquireSql003ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaBrokerageSubLedgerAcquireSql004RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaBrokerageSubLedgerAcquireSql005RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dto.IfaBrokerageSubLedgerAcquireA001ReponseDto;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dto.IfaBrokerageSubLedgerAcquireA001RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dto.IfaBrokerageSubLedgerAcquireA002RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dto.IfaBrokerageSubLedgerAcquireA002ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.service.IfaBrokerageSubLedgerAcquireService;
import com.sbisec.helios.ap.wholePortal.service.impl.FileDownloadUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * 画面ID：SUB0402_01-01
 * 画面名：仲介業補助簿取得
 *
 * @author BASE 丁
 2024/06/21 新規作成
 */
@Component(value = "cmpIfaBrokerageSubLedgerAcquireService")
public class IfaBrokerageSubLedgerAcquireServiceImpL implements IfaBrokerageSubLedgerAcquireService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaBrokerageSubLedgerAcquireServiceImpL.class);
    
    @Autowired
    private IfaBrokerageSubLedgerAcquireDao dao;
    
    @Autowired
    private Fct030 fct030;
    
    @Autowired
    FileDownloadUtil fileDownloadUtil;
    
    /** ユーザ権限コード '1':SBI証券本店 */
    private static final String PRI_ID_1 = "1";
    
    private static final String PRI_ID_2 = "2";
    
    private static final String PRI_ID_3 = "3";
    
    private static final String PRI_ID_4 = "4";
    
    private static final String PRI_ID_6 = "6";
    
    /** 画面.作成日（From）、画面.作成日（To）のチェックエラーメッセージパラメータ　パラメータ。 */
    private static final String ERRORS_SPECIFYFROMTO_0 = "作成日";
    
    private static final String ERRORS_DATERANGE_0 = "作成日に";
    
    private static final String ERRORS_DATERANGE_1 = "3ヶ月";
    
    private static final String ERRORS_SPECIFYFORMAT_0 = "作成日From\"、又は、”作成日To";
    
    private static final String ERRORS_SPECIFYFORMAT_1 = "YYYY/MM/DD";
    
    /** SQL003.総件数が5000件超過エラーメッセージパラメータ　パラメータ。 */
    private static final String QUERY_SIZE_LIMIT = "5000";
    
    private static final String OFF = "false";
    
    private static final String ON = "true";
    
    /**
     * エラーメッセージId
     *
     * @author BASE丁
     *
     */
    private enum MessageId {
        
        INFO_ITA_BROKERAUXILIARYBOOKDIRECTORY_NOTFOUND(
                "info.ita.BrokerAuxiliaryBookDirectory.notfound"), ERRORS_DATE_SPECIFYFROMTO(
                        "errors.date.specifyFromTo"), ERRORS_DATERANGE("errors.dateRange"), ERRORS_DATE_SPECIFYFORMAT(
                                "errors.date.specifyFormat"), ERRORS_CMN_IFAAGENTCODES_NOTEXIST(
                                        "errors.cmn.ifaAgentCodes.notExist"), ERRORS_DATALIST_NOTFOUND(
                                                "errors.dataList.notfound"), WARNINGS_DATALIST_OVERMAXROWNUM(
                                                        "warnings.dataList.overMaxRownum"),;
        
        private String key;
        
        MessageId(String key) {
            
            this.key = key;
        }
    }
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaBrokerageSubLedgerAcquireA001RequestDto
     * Dto レスポンス：IfaBrokerageSubLedgerAcquireA001ResponseDto
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBrokerageSubLedgerAcquireA001ReponseDto> initializeA001(
            IfaBrokerageSubLedgerAcquireA001RequestDto dtoReq) throws Exception {
        
        DataList<IfaBrokerageSubLedgerAcquireA001ReponseDto> dtoRes = new DataList<IfaBrokerageSubLedgerAcquireA001ReponseDto>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaBrokerageSubLedgerAcquireServiceImplL.initializeA001");
        
        IfaBrokerageSubLedgerAcquireSql001RequestModel IfaBrokerageSubLedgerAcquireSql001RequestModel = new IfaBrokerageSubLedgerAcquireSql001RequestModel();
        BeanUtils.copyProperties(IfaBrokerageSubLedgerAcquireSql001RequestModel, dtoReq);
        DataList<IfaBrokerageSubLedgerAcquireSql001ResponseModel> sql001Res = dao
                .selectIfaBrokerageSubLedgerAcquireSql001(IfaBrokerageSubLedgerAcquireSql001RequestModel);
        
        //②仲介業補助簿ファイルディレクトリ取得結果が0件の場合、メッセージを表示する。
        if (sql001Res.size() == 0) {
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.INFO,
                    MessageId.INFO_ITA_BROKERAUXILIARYBOOKDIRECTORY_NOTFOUND.key,
                    IfaCommonUtil.getMessage(MessageId.INFO_ITA_BROKERAUXILIARYBOOKDIRECTORY_NOTFOUND.key));
        } else {
            for (IfaBrokerageSubLedgerAcquireSql001ResponseModel sql001ResModel : sql001Res.getDataList()) {
                IfaBrokerageSubLedgerAcquireA001ReponseDto IfaBrokerageSubLedgerAcquireA001ReponseDto = new IfaBrokerageSubLedgerAcquireA001ReponseDto();
                BeanUtils.copyProperties(IfaBrokerageSubLedgerAcquireA001ReponseDto, sql001ResModel);
                dtoRes.getDataList().add(IfaBrokerageSubLedgerAcquireA001ReponseDto);
            }
        }
        
        return dtoRes;
    }
    
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaBrokerageSubLedgerAcquireA002RequestDto
     * Dto レスポンス：IfaBrokerageSubLedgerAcquireA002ResponseDto
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBrokerageSubLedgerAcquireA002ResponseDto> displayA002(
            IfaBrokerageSubLedgerAcquireA002RequestDto dtoReq) throws Exception {
        
        DataList<IfaBrokerageSubLedgerAcquireA002ResponseDto> dtoRes = new DataList<IfaBrokerageSubLedgerAcquireA002ResponseDto>();
        
        IfaBrokerageSubLedgerAcquireA002ResponseDto ifaBrokerageSubLedgerAcquireA002ResponseDto = new IfaBrokerageSubLedgerAcquireA002ResponseDto();
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaBrokerageSubLedgerAcquireServiceImplL.displayA002");
        
        BeanUtils.copyProperties(ifaBrokerageSubLedgerAcquireA002ResponseDto, dtoReq);
        
        //①画面.作成日（From）、画面.作成日（To）のチェックを行う。
        
        //画面.作成日（From）、画面.作成日（To）に対し以下のチェックを実施しチェックに引っかかる場合、メッセージを表示し、処理終了
        String createDateFrom = dtoReq.getCreateDateFrom();
        String createDateTo = dtoReq.getCreateDateTo();
        if (!Pattern.matches("^[\\d/]{1,10}$", createDateFrom) || !Pattern.matches("^[\\d/]{1,10}$", createDateTo)) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    MessageId.ERRORS_DATE_SPECIFYFORMAT.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_DATE_SPECIFYFORMAT.key,
                            new String[] { ERRORS_SPECIFYFORMAT_0, ERRORS_SPECIFYFORMAT_1 }));
            return dtoRes;
        }
        
        //画面.作成日（To） ＜ 画面.作成日（From）の場合、メッセージを表示し、処理終了。
        if (StringUtils.compare(createDateFrom, createDateTo) > 0) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    MessageId.ERRORS_DATE_SPECIFYFROMTO.key, IfaCommonUtil.getMessage(
                            MessageId.ERRORS_DATE_SPECIFYFROMTO.key, new String[] { ERRORS_SPECIFYFROMTO_0 }));
            return dtoRes;
        }

        //画面.作成日（From）から画面.作成日（To）までの期間が3ヶ月間を超える場合、メッセージを表示し、処理終了。
        if (LocalDate.parse(createDateTo, DateTimeFormatter.ofPattern("yyyy/MM/dd")).minusMonths(3)
                .compareTo(LocalDate.parse(createDateFrom, DateTimeFormatter.ofPattern("yyyy/MM/dd"))) > 0) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    MessageId.ERRORS_DATERANGE.key, IfaCommonUtil.getMessage(MessageId.ERRORS_DATERANGE.key,
                            new String[] { ERRORS_DATERANGE_0, ERRORS_DATERANGE_1 }));
            return dtoRes;
        }
         
        //②ユーザ共通情報.権限コード ≠ '1':SBI証券本店 の場合：参照可能な仲介業者コードと営業員コードを取得する。   
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        String privId = userAccount.getPrivId();
        List<String> empCodeList = new ArrayList<>();
        if (!PRI_ID_1.equals(privId)) {
            OutputFct030Dto outputFct030Dto = fct030.getData(null);
            List<BrokerCharge> brokerChargeList = outputFct030Dto.getBrokerChargeList();
            if (brokerChargeList.size() == 0) {
                return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                        MessageId.ERRORS_CMN_IFAAGENTCODES_NOTEXIST.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_IFAAGENTCODES_NOTEXIST.key));
            }
            empCodeList = brokerChargeList.stream().map(BrokerCharge::getBrokerCode).collect(Collectors.toList());
        }
        
        //③ログインユーザーＩＤに関する仲介業補助簿一時テーブル情報のデータを削除する。
        IfaBrokerageSubLedgerAcquireSql004RequestModel ifaBrokerageSubLedgerAcquireSql004RequestModel = new IfaBrokerageSubLedgerAcquireSql004RequestModel();
        ifaBrokerageSubLedgerAcquireSql004RequestModel.setUserId(userAccount.getUserId());

        dao.deleteIfaBrokerageSubLedgerAcquireSql004(ifaBrokerageSubLedgerAcquireSql004RequestModel);
        
        //⑤ディレクトリリストのディレクトリごとに、ファイル名を取得し、ファイルがなくなるまで下記を繰り返す。ファイルがなくなった場合、次の処理へ。
        IfaBrokerageSubLedgerAcquireSql002RequestModel ifaBrokerageSubLedgerAcquireSql002RequestModel = new IfaBrokerageSubLedgerAcquireSql002RequestModel();
        List<IfaBrokerageSubLedgerAcquireSql002ResponseModel> sql002Res = new ArrayList<>();
        
          //④仲介業補助簿ファイルディレクトリ内にディレクトリが存在するかのチェックを行う。
            String fileDirectory = dtoReq.getFileDirectory();
            File fileD = new File(fileDirectory);
            if (!fileD.exists() || !fileD.isDirectory()) {
                dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.INFO,
                        MessageId.ERRORS_DATALIST_NOTFOUND.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_DATALIST_NOTFOUND.key));
                return dtoRes; 
            }
            File[] filePaths = fileD.listFiles();
            if (null == filePaths || filePaths.length == 0) {
                dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.INFO,
                        MessageId.ERRORS_DATALIST_NOTFOUND.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_DATALIST_NOTFOUND.key));
                return dtoRes;
            }
            for (File filePath : filePaths) {
                //ファイルパス
                String createDate = filePath.getName();
                
                //ファイル名
                File[] files = new File(fileDirectory + createDate).listFiles();
                for (File file : files) {
                    String fileName = file.getName();
                    String fileOnlyName = fileName.substring(0, fileName.lastIndexOf("."));
                    
                    //仲介業者コード
                    String brokerCode = "";
                    if (fileOnlyName.length() >= 22) {
                        brokerCode = fileOnlyName.substring(17, 21);
                    }
                    
                    //商品コード
                    String productCode = "";
                    if (fileOnlyName.length() >= 15) {
                        productCode = fileOnlyName.substring(13, 16);
                    }
                    
                    //上記取得した情報より、仲介補助簿情報取得対象かを判定する。
                    if (PRI_ID_1.equals(privId) && !ObjectUtils.isEmpty(dtoReq.getBrokerCode())) {
                        if (StringUtils.equals(dtoReq.getChkBrokerCodeExclude(), OFF)
                                && !dtoReq.getBrokerCode().contains(brokerCode)) {
                            continue;
                        }
                        if (StringUtils.equals(dtoReq.getChkBrokerCodeExclude(), ON)
                                && dtoReq.getBrokerCode().contains(brokerCode)) {
                            continue;
                        }
                    }
                    if (PRI_ID_2.equals(privId)
                            && ((!empCodeList.contains(brokerCode)) || (!ObjectUtils.isEmpty(dtoReq.getBrokerCode()))
                                    && !dtoReq.getBrokerCode().contains(brokerCode))) {
                        continue;
                    }
                    if ((PRI_ID_3.equals(privId) || PRI_ID_4.equals(privId) || PRI_ID_6.equals(privId))
                            && (!empCodeList.contains(brokerCode) || !ObjectUtils.isEmpty(dtoReq.getBrokerCode()))
                                    && !dtoReq.getBrokerCode().contains(brokerCode)) {
                        continue;
                    }
                    if (LocalDate.parse(createDateFrom, DateTimeFormatter.ofPattern("yyyy/MM/dd"))
                            .isAfter(LocalDate.parse(createDate, DateTimeFormatter.ofPattern("yyyyMMdd")))
                            || LocalDate.parse(createDateTo, DateTimeFormatter.ofPattern("yyyy/MM/dd"))
                                    .isBefore(LocalDate.parse(createDate, DateTimeFormatter.ofPattern("yyyyMMdd")))) {
                        continue;
                    }
                    ifaBrokerageSubLedgerAcquireSql002RequestModel.setCreateTime(createDate);
                    ifaBrokerageSubLedgerAcquireSql002RequestModel.setFileName(fileName);
                    ifaBrokerageSubLedgerAcquireSql002RequestModel.setBrokerCode(brokerCode);
                    ifaBrokerageSubLedgerAcquireSql002RequestModel.setProductCode(productCode);

                    sql002Res.add(dao.selectIfaBrokerageSubLedgerAcquireSql002(ifaBrokerageSubLedgerAcquireSql002RequestModel).getDataList().get(0));
                }
            }
        
        String userId = userAccount.getUserId();
        //⑥仲介業補助簿リストの情報を、仲介業者補助簿一時テーブルへ登録する。
       for(IfaBrokerageSubLedgerAcquireSql002ResponseModel sql002ResModel : sql002Res) {
           IfaBrokerageSubLedgerAcquireSql005RequestModel ifaBrokerageSubLedgerAcquireSql005RequestModel = new IfaBrokerageSubLedgerAcquireSql005RequestModel();

           String createDate = sql002ResModel.getCreateTime();
           
           String brokerCode = sql002ResModel.getBrokerCode();
           
           String brokerName = sql002ResModel.getBrokerName();
           
           String codeName = sql002ResModel.getCodeName();
           
           String dl = sql002ResModel.getDl();
           
           String fileName = sql002ResModel.getFileName();
           
           ifaBrokerageSubLedgerAcquireSql005RequestModel.setUserId(userId);
           ifaBrokerageSubLedgerAcquireSql005RequestModel.setCreateDate(createDate);
           ifaBrokerageSubLedgerAcquireSql005RequestModel.setBrokerCode(brokerCode);
           ifaBrokerageSubLedgerAcquireSql005RequestModel.setBrokerName(brokerName);
           ifaBrokerageSubLedgerAcquireSql005RequestModel.setCodeName(codeName);
           ifaBrokerageSubLedgerAcquireSql005RequestModel.setDl(dl);
           ifaBrokerageSubLedgerAcquireSql005RequestModel.setDownloadFileName(fileName);
           
           dao.insertIfaBrokerageSubLedgerAcquireSql005(ifaBrokerageSubLedgerAcquireSql005RequestModel);
       }
          
        //⑦画面.作成日で指定From～Toの期間で仲介業者補助簿一時テーブルから仲介業補助簿一覧を取得する。   
        IfaBrokerageSubLedgerAcquireSql003RequestModel ifaBrokerageSubLedgerAcquireSql003RequestModel = new IfaBrokerageSubLedgerAcquireSql003RequestModel();
        BeanUtils.copyProperties(ifaBrokerageSubLedgerAcquireSql003RequestModel, dtoReq);
        ifaBrokerageSubLedgerAcquireSql003RequestModel.setUserId(userId);
        
        DataList<IfaBrokerageSubLedgerAcquireSql003ResponseModel> sql003Res = dao
                .selectIfaBrokerageSubLedgerAcquireSql003(ifaBrokerageSubLedgerAcquireSql003RequestModel);
        
        //SQL003.総件数が0件の場合、対象メッセージを表示し、処理終了
        if (sql003Res.size() == 0) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.INFO,
                    MessageId.ERRORS_DATALIST_NOTFOUND.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_DATALIST_NOTFOUND.key));
            return dtoRes;
        } else if (Integer.parseInt(sql003Res.getDataList().get(0).getTotalCount()) > 5000) {
            //SQL003.総件数が5000件超過の場合、レスポンスに仲介業補助簿一覧リストをセットし対象メッセージを設定し次の処理へ
            dtoRes.setErrorLevel(ErrorLevel.WARNING.getId());
            dtoRes.setMessage(IfaCommonUtil.getMessage(MessageId.WARNINGS_DATALIST_OVERMAXROWNUM.key,
                    new String[] { QUERY_SIZE_LIMIT, sql003Res.getDataList().get(0).getTotalCount() }));
            dtoRes.setReturnCode(MessageId.WARNINGS_DATALIST_OVERMAXROWNUM.key);
        }
        
        for (IfaBrokerageSubLedgerAcquireSql003ResponseModel sql003ResModel : sql003Res.getDataList()) {
            IfaBrokerageSubLedgerAcquireA002ResponseDto IfaBrokerageSubLedgerAcquireA002ReponseDto = new IfaBrokerageSubLedgerAcquireA002ResponseDto();
            BeanUtils.copyProperties(IfaBrokerageSubLedgerAcquireA002ReponseDto, sql003ResModel);
            dtoRes.getDataList().add(IfaBrokerageSubLedgerAcquireA002ReponseDto);
        }
        
        return dtoRes;
    }
    
    
}
