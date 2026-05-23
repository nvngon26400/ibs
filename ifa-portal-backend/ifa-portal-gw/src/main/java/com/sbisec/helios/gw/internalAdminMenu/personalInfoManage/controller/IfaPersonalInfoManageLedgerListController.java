package com.sbisec.helios.gw.internalAdminMenu.personalInfoManage.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.DateUtil;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.DateFormatUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dto.IfaPersonalInfoManageLedgerListA001DtoRequest;
import com.sbisec.helios.gw.internalAdminMenu.personalInfoManage.form.IfaPersonalInfoManageLedgerListA001ApiRequest;
import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dto.IfaPersonalInfoManageLedgerListA001DtoResponse;
import com.sbisec.helios.gw.internalAdminMenu.personalInfoManage.form.IfaPersonalInfoManageLedgerListA001ApiResponse;
import com.sbisec.helios.gw.internalAdminMenu.personalInfoManage.form.IfaPersonalInfoManageLedgerListA002aApiRequest;
import com.sbisec.helios.gw.internalAdminMenu.personalInfoManage.form.IfaPersonalInfoManageLedgerListA002aApiResponse;
import com.sbisec.helios.gw.internalAdminMenu.personalInfoManage.form.IfaPersonalInfoManageLedgerListA002bApiRequest;
import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dto.IfaPersonalInfoManageLedgerListA003DtoRequest;
import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dto.IfaPersonalInfoManageLedgerListA003DtoResponse;
import com.sbisec.helios.gw.internalAdminMenu.personalInfoManage.form.IfaPersonalInfoManageLedgerListA003ApiRequest;
import com.sbisec.helios.gw.internalAdminMenu.personalInfoManage.form.IfaPersonalInfoManageLedgerListA003ApiResponse;

import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dto.IfaPersonalInfoManageLedgerListA005aDtoRequest;
import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dto.IfaPersonalInfoManageLedgerListA005aDtoResponse;
import com.sbisec.helios.gw.internalAdminMenu.personalInfoManage.form.IfaPersonalInfoManageLedgerListA005aApiRequest;
import com.sbisec.helios.gw.internalAdminMenu.personalInfoManage.form.IfaPersonalInfoManageLedgerListA005aApiResponse;
import com.sbisec.helios.gw.internalAdminMenu.personalInfoManage.form.IfaPersonalInfoManageLedgerListA005bApiRequest;

import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dto.IfaPersonalInfoManageLedgerListA006DtoRequest;
import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dto.IfaPersonalInfoManageLedgerListA006DtoResponse;
import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.util.IfaPersonalInfoManageLedgerListCsvOut;
import com.sbisec.helios.gw.internalAdminMenu.personalInfoManage.form.IfaPersonalInfoManageLedgerListA006ApiRequest;
import com.sbisec.helios.gw.internalAdminMenu.personalInfoManage.form.IfaPersonalInfoManageLedgerListA006ApiRequestPersonalInfoManageLedger;
import com.sbisec.helios.gw.internalAdminMenu.personalInfoManage.form.IfaPersonalInfoManageLedgerListA006ApiResponse;

import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbibits.earth.servlet.annotation.ResponseFile;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sbibits.earth.servlet.annotation.ResponseJson;
import org.springframework.web.util.UriUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.apache.commons.io.IOUtils;

/**
 * 画面ID：SUB0403-01
 * 画面名：個人情報管理台帳一覧

 * @author 大崎辰弥
    2023/12/19 新規作成
 */

@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN04", id = "SUB0403-01", screenNumber = "")
public class IfaPersonalInfoManageLedgerListController extends BaseController {

    /** "{0}ToにはFromと同日以降の日付を指定して下さい。" */
    
    private static final String ERRORS_DATE_SPECIFY_FROM_TO = "errors.date.specifyFromTo";

    /** "{0}は{1}以内を正しく入力して下さい。" */
    
    private static final String ERRORS_DATA_RANGE = "errors.dateRange";

    /** "ファイルが存在しません。管理者に連絡してください。" */
        
    private static final String INFO_ITA_PERSONALY_LEDGER_FILE_NOT_FOUND = "info.ita.PersonalyLedgerFile.notfound";

    private static final String COR_DEPOSIT_OUTLINE_ERROR_COM = "摘要（預託先詳細）は30文字以内で入力してください。";

    private static final String COR_DONATION_OUTLINE_ERROR_COM = "摘要（提供先詳細）は30文字以内で入力してください。";

    private static final String COR_DEPOSITORY_OUTLINE_ERROR_COM = "摘要（保管場所詳細）は30文字以内で入力してください。";

    private static final String ERROR_MSG01 = "修正が必要な項目:保管期間 <br/>更新できなかった理由:保管期間が未入力のため<br/>解決方法:<br/>保管期間が決まっている場合→「1ヶ月」、「3ヶ月」、「6ヶ月」、「1年等」「2012年9月末まで」期間を入力 <br/>"
                        + "保管期間が決まっていない場合→「案件終了まで」と入力<br/>"
                        + "ファイルの廃棄は行わず保存しておく場合→項目「破棄しない」＝「Ｏ」を選択<br/>"
                        + "※保管期間が変更になった場合は随時書き換え可能です。";

    private static final String ERROR_MSG02 = "修正が必要な項目:廃棄方法<br/>更新できなかった理由:保管書類の廃棄方法が選択されていません<br/>解決方法:<br/>廃棄方法を選択してください<br/>"
                        + "項目「保管/送付媒体」＝「紙」の場合…「シュレッダー」を選択<br/>"
                        + "項目「保管/送付媒体」＝「紙とデータ」の場合…「シュレッダーとデータ削除」を選択"
                        + "※破棄しない場合は、項目「破棄しない」の選択を「-」から「Ｏ」に変更し、項目「保管期間」「廃棄方法」「廃棄した年月日」で「－」を選択してください";

    private static final String ERROR_MSG03 = "修正が必要な項目:預託先,提供先,保管場所<br/>更新できなかった理由:預託先　提供先　保管場所　の3項目すべてに対し「なし」を選択することはできません<br/>解決方法:<br/>どれかひとつ以上の項目で「なし」以外を選択してください<br/>"
                        + "どのような目的でファイルをダウンロード/印刷しましたか？<br/>"
                        + "外部業者等へ預けるため　…項目「預託先」＝「あり」を選択<br/>"
                        + "顧客へ渡すため　→項目「提供先」＝「顧客」を選択<br/>"
                        + "（親権者・代理人等）顧客以外へ渡すため　→項目「提供先」＝「顧客以外」を選択→項目「摘要（提供先詳細）」に入力<br/>"
                        + "外務員様が業務を行うため。ファイルは印刷し、紙で保存した　→項目「保管場所」＝「施錠キャビネ」を選択<br/>"
                        + "外務員様が業務を行うため。ファイルはダウンロードし、データをＰＣに保存した　→項目「保管場所」＝「ＰＣ共有フォルダ」を選択→項目「摘要（保管場所詳細）」に入力<br/>"
                        + "外務員様が業務を行うため。ファイルは紙で印刷しダウンロードも行い、それぞれ施錠キャビネとＰＣに保存した　→項目「保管場所」＝「施錠キャビネとＰＣ共有フォルダ」を選択→項目「摘要（保管場所詳細）」に入力<br/>"
                        + "<br/>"
                        + "上記に当てはまるものがない場合…<br/>"
                        + "外部業者へは預けておらず、顧客等へも渡していない。外務員様が業務を行うためファイルを印刷（またはダウンロード）して利用したが、ファイルはその場で処分し保存は行わなかった　→項目「保管/送付媒体」＝「保管/送付しない」を選択<br/>"
                        + "操作ミス等により、誤ってファイルを印刷（またはダウンロード）したが、ファイルはその場で処分し保存は行わなかった　→項目「保管/送付媒体」＝「保管/送付しない」を選択";

    private static final String ERROR_MSG04 = "修正が必要な項目:保管場所<br/>更新できなかった理由:項目「保管/送付媒体」で「紙」を選択している場合、「施錠キャビネ」か「なし」のどちらかを選択してください<br/>解決方法:<br/>ダウンロード/印刷したファイルをどのように処理しましたか？<br/>"
                        + "外部へのファイルの持ち出しの有無にかかわらず、ファイルを印刷し紙で保存した場合は、項目「保管場所」＝「施錠キャビネ」を選択してください<br/>"
                        + "外部業者等への預託や、顧客等への提供のみで、外務員様によるファイルの保管を行わない場合は、項目「保管場所」＝「なし」を選択してください";


    private static final String ERROR_MSG05 = "修正が必要な項目:保管場所<br/>更新できなかった理由:項目「保管/送付媒体」で「データ」を選択している場合、「ＰＣ共有フォルダ」か「なし」のどちらかを選択してください<br/>解決方法:<br/>ダウンロード/印刷したファイルをどのように処理しましたか？<br/>"
                        + "外部へのファイルの持ち出しの有無にかかわらず、ファイルをダウンロードしデータでＰＣに保存した場合は、項目「保管場所」＝「ＰＣ共有フォルダ」を選択してください<br/>"
                        + "外部業者等への預託や、顧客等への提供のみで、外務員様によるファイルの保管を行わない場合は、項目「保管場所」＝「なし」を選択してください<br/>";


    private static final String ERROR_MSG06 = "修正が必要な項目:保管場所<br/>更新できなかった理由:項目「保管/送付媒体」で「紙とデータ」を選択している場合、「施錠キャビネとＰＣ共有フォルダ」か「なし」のどちらかを選択してください<br/>解決方法:<br/>ダウンロード/印刷したファイルをどのように処理しましたか？<br/>"
                        + "外部へのファイルの持ち出しの有無にかかわらず、ファイルを紙とデータの両方で保管する場合は、項目「保管場所」＝「施錠キャビネとＰＣ共有フォルダ」を選択してください<br/>"
                        + "外部業者等への預託や、顧客等への提供のみで、外務員様によるファイルの保管を行わない場合は、項目「保管場所」＝「なし」を選択してください";

    private static final String ERROR_MSG07 = "修正が必要な項目:保管期間<br/>更新できなかった理由: 保管期間の入力文字が制限文字数30文字を超えたため<br/>解決方法:<br/>保管期間が決まっている場合→「1ヶ月」、「3ヶ月」、「6ヶ月」、「1年」「2012年9月末まで」等、期間を入力<br/>"
                        + "保管期間が決まっていない場合→「案件終了まで」と入力<br/>"
                        + "ファイルの廃棄は行わず保存しておく場合→項目「破棄しない」＝「Ｏ」を選択<br/>"
                        + "※保管期間が変更になった場合は随時書き換え可能です。";

    private static final String ERROR_MSG08 = "修正が必要な項目:預託先,提供先,保管場所,保管期間,廃棄方法,破棄しない,廃棄した年月日,摘要（預託先詳細）,摘要（提供先詳細）,摘要（保管場所詳細）<br/>解決方法:"
                        + "<br/>項目「保管/送付媒体」で「保管/送付しない」を選択した場合は、それ以外の入力項目にはすべて「－」を選択（または入力）してください<br/>"
                        + "※「保管送付媒体」で「保管/送付しない」を選択するケースは、以下の場合に限られます<br/>"
                        + "●外部業者へは預けておらず、顧客等へも渡していない。外務員様が業務を行うためファイルを印刷（またはダウンロード）して利用したが、ファイルはその場で処分し保存は行わなかった場合<br/>"
                        + "●操作ミス等により、誤ってファイルを印刷（またはダウンロード）したが、ファイルはその場で処分し外部への持ち出しも保存も行わなかった場合<br/>"
                        + "上記に該当しない場合は、項目「保管/送付媒体」で「保管/送付しない」以外の該当する項目を選択してください";

    private static final String ERROR_MSG09 = "解決方法:摘要（預託先詳細）に個人情報の預託先を手入力してください。"
                        + "※基本的に個人情報を第三者へ預託する場合（印刷業者等にDM発送業務を委託する場合などがこれにあたります）"
                        + "は当社への事前申請が必要です。預託する前に当社担当者へ必ずご連絡ください。なお、預託は行わない場合は、項目「預託先」で「なし」を選択しなおしてください。";

    private static final String ERROR_MSG10 = "解決方法:<br/>摘要（提供先詳細）に個人情報の提供先を手入力してください。例）「口座名義人の親権者」「口座名義人の法定相続人」等";

    private static final String ERROR_MSG11 = "解決方法:<br/>摘要（保管場所詳細）にデータを保管したPC共有フォルダのフォルダ名を手入力してください。";

    private static final String ERROR_MSG12 = "解決方法:<br/>項目「預託先」で「なし」を選択していますので、摘要欄（預託先詳細）には「-」を手入力してください。";

    private static final String ERROR_MSG13 = "解決方法:<br/>摘要欄（提供先詳細）に「-」を手入力してください。※この項目は、項目「提供先」が「顧客以外」の場合のみ提供先詳細入力を行ってください";

    private static final String ERROR_MSG14 = "修正が必要な項目:「保管期間」「廃棄方法」「廃棄年月日」<br/>更新できなかった理由:"
                        + "項目「廃棄しない」＝Ｏは、データ等は廃棄せず永久に保存するという意味ですので、「保管期間」「廃棄方法」「廃棄年月日」は記入不要です。"
                        + "<br/>解決方法:<br/>「保管期間」「廃棄方法」「廃棄年月日」に「-」を選択（または手入力してください）";
               
    private static final String HEADER_KEY_CONTENT_DISPOSITION = "Content-Disposition";
    private static final String HEADER_VALUE_ATTACHMENT = "attachment; filename=";
    private static final String CONTENT_TYPE = "application/octet-stream;";

    private JsonConverter jc = JsonConverter.getInstance();

    private static final Logger logger = LoggerFactory.getLogger(IfaPersonalInfoManageLedgerListController.class);

    @Override
    public String getCsvHeader() {
        return IfaPersonalInfoManageLedgerListCsvOut.getHeaders();
    }

    /**
    * アクセス：/internalAdminMenu/personalInfoManage/ifaPersonalInfoManageLedgerListInitializeA001
    * アクションID：A001
    * アクション名：初期化
    * APIリクエスト：IfaPersonalInfoManageLedgerListA001ApiRequest
    * Apiレスポンス：IfaPersonalInfoManageLedgerListA001ApiResponse
    * Dtoリクエスト：IfaPersonalInfoManageLedgerListA001DtoRequest
    * Dtoレスポンス：IfaPersonalInfoManageLedgerListA001DtoResponse

     * @param apiReq リクエスト
     * @return appRes
     * @exception Exception SQLExceptionなど 
    */
    @PostMapping("/internalAdminMenu/personalInfoManage/ifaPersonalInfoManageLedgerListInitializeA001")
    public String initializeA001(@RequestBody @Validated IfaPersonalInfoManageLedgerListA001ApiRequest apiReq)throws Exception {

        IfaPersonalInfoManageLedgerListA001DtoRequest appReq = new IfaPersonalInfoManageLedgerListA001DtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaPersonalInfoManageLedgerListA001DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaPersonalInfoManageLedgerListService",
                "initializeA001", new TypeReference<DataList<IfaPersonalInfoManageLedgerListA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaPersonalInfoManageLedgerListA001ApiResponse> apiRes = new DataList<IfaPersonalInfoManageLedgerListA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**
    * アクセス：/internalAdminMenu/personalInfoManage/ifaPersonalInfoManageLedgerListIfaPersonalInfoManageLedgerOperateManualDownloadA002a
    * アクションID：A002a
    * アクション名：個人情報管理台帳操作マニュアルダウンロード（存在チェック）
    * APIリクエスト：IfaPersonalInfoManageLedgerListA002aApiRequest

     * @param apiReq リクエスト
     * @return appRes
     * @exception Exception SQLExceptionなど 
    */
    @PostMapping("/internalAdminMenu/personalInfoManage/ifaPersonalInfoManageLedgerListIfaPersonalInfoManageLedgerOperateManualDownloadA002a")
    @ResponseJson
    @ResponseBody
    public String ifaPersonalInfoManageLedgerOperateManualDownloadA002a(@Validated @RequestBody IfaPersonalInfoManageLedgerListA002aApiRequest apiReq, 
            HttpServletResponse response) throws Exception {

        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        String fileName = apiReq.getFileDirectory();
        
        // ファイルの存在チェック
        File tmp = new File(fileName);
        if (!tmp.exists()) {
            String errorMessage = getMessage(INFO_ITA_PERSONALY_LEDGER_FILE_NOT_FOUND, new String[] {});
            return jc.toString(IfaCommonUtil.createDataList(null, ErrorLevel.INFO,
                    INFO_ITA_PERSONALY_LEDGER_FILE_NOT_FOUND, errorMessage));            
        }
        
        IfaPersonalInfoManageLedgerListA002aApiResponse res = new IfaPersonalInfoManageLedgerListA002aApiResponse();
        res.setPdfFileName(fileName);
        
        return jc.toString(IfaCommonUtil.createDataList(List.of(res), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null));  
    }
    
    /**
    * アクセス：/internalAdminMenu/personalInfoManage/ifaPersonalInfoManageLedgerListIfaPersonalInfoManageLedgerOperateManualDownloadA002b
    * アクションID：A002b
    * アクション名：個人情報管理台帳操作マニュアルダウンロード（ダウンロード）
    * APIリクエスト：IfaPersonalInfoManageLedgerListA002bApiRequest

     * @param apiReq リクエスト
     * @throws Exception コンテンツファイル処理で例外が発生した場合
    */
                             
    @PostMapping("/internalAdminMenu/personalInfoManage/ifaPersonalInfoManageLedgerListIfaPersonalInfoManageLedgerOperateManualDownloadA002b")
    @ResponseJson
    @ResponseBody
    public void ifaPersonalInfoManageLedgerOperateManualDownloadA002b(@Validated @RequestBody IfaPersonalInfoManageLedgerListA002bApiRequest apiReq, 
            HttpServletResponse response) throws Exception {
        
        // ファイルダウンロード
        try {
            String fmFileName = new File(apiReq.getPdfFileName()).getName();
            response.setContentType(CONTENT_TYPE);
            response.setHeader(HEADER_KEY_CONTENT_DISPOSITION, HEADER_VALUE_ATTACHMENT + UriUtils.encode(fmFileName, "UTF-8"));

            IOUtils.copy(new FileInputStream(apiReq.getPdfFileName()), response.getOutputStream());
            
        } catch (FileNotFoundException e) {
            //例外処理
            if (logger.isDebugEnabled()) {
                logger.debug("{IfaPersonalInfoManageLedgerListController、ifaPersonalInfoManageLedgerOperateManualDownloadA002b、FileNotFoundException}");
            }  
        }
    }
    
    /**
    * アクセス：/internalAdminMenu/personalInfoManage/ifaPersonalInfoManageLedgerListDisplayA003
    * アクションID：A003
    * アクション名：表示
    * APIリクエスト：IfaPersonalInfoManageLedgerListA003ApiRequest
    * Apiレスポンス：IfaPersonalInfoManageLedgerListA003ApiResponse
    * Dtoリクエスト：IfaPersonalInfoManageLedgerListA003DtoRequest
    * Dtoレスポンス：IfaPersonalInfoManageLedgerListA003DtoResponse

     * @param apiReq リクエスト
     * @return appRes
     * @exception Exception SQLExceptionなど 
    */
    @PostMapping("/internalAdminMenu/personalInfoManage/ifaPersonalInfoManageLedgerListDisplayA003")
    public String displayA003(@RequestBody @Validated IfaPersonalInfoManageLedgerListA003ApiRequest apiReq)throws Exception {

        IfaPersonalInfoManageLedgerListA003DtoRequest appReq = new IfaPersonalInfoManageLedgerListA003DtoRequest();

        // 顧客共通情報
        IfaGwCommonUtil.setCustomerCommonToRequestScope();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        String processDayTimeFrom = DateFormatUtil.dateFormatToHyphen(apiReq.getProcessDayTimeFrom());
        String processDayTimeTo = DateFormatUtil.dateFormatToHyphen(apiReq.getProcessDayTimeTo());
        
        // 画面.処理対象期間（To） < 画面.処理対象期間（From）のチェック
        if (!DateUtil.isValidFromTo(processDayTimeFrom, processDayTimeTo, DateFormatUtil.YYYYMMDD, DateFormatUtil.SEPARATED_YYYYMMDD)) {
            return jc.toString(IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, 
                    ERRORS_DATE_SPECIFY_FROM_TO, IfaCommonUtil.getMessage(ERRORS_DATE_SPECIFY_FROM_TO, new String[] {"処理対象期間"})));
        }

        if (isDifferenceDate(processDayTimeFrom, processDayTimeTo)) {
            return jc.toString(IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, 
                    ERRORS_DATA_RANGE, IfaCommonUtil.getMessage(ERRORS_DATA_RANGE, new String[] {"処理対象期間", "3ヶ月"})));
        }

        DataList<IfaPersonalInfoManageLedgerListA003DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaPersonalInfoManageLedgerListService",
                "displayA003", new TypeReference<DataList<IfaPersonalInfoManageLedgerListA003DtoResponse>>() {
                }, appReq);
        
        DataList<IfaPersonalInfoManageLedgerListA003ApiResponse> apiRes = new DataList<IfaPersonalInfoManageLedgerListA003ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**
    * アクセス：/internalAdminMenu/personalInfoManage/ifaPersonalInfoManageLedgerListCsvOutputA005a
    * アクションID：A005a
    * アクション名：csv出力
    * APIリクエスト：IfaPersonalInfoManageLedgerListA005aApiRequest
    * Apiレスポンス：IfaPersonalInfoManageLedgerListA005aApiResponse
    * Dtoリクエスト：IfaPersonalInfoManageLedgerListA005aDtoRequest
    * Dtoレスポンス：IfaPersonalInfoManageLedgerListA005aDtoResponse

     * @param apiReq リクエスト
     * @return appRes
     * @exception Exception SQLExceptionなど 
    */
    @PostMapping("/internalAdminMenu/personalInfoManage/ifaPersonalInfoManageLedgerListCsvOutputA005a")
    public String csvOutputA005a(@RequestBody @Validated IfaPersonalInfoManageLedgerListA005aApiRequest apiReq)throws Exception {
        
        IfaPersonalInfoManageLedgerListA005aDtoRequest appReq = new IfaPersonalInfoManageLedgerListA005aDtoRequest();
        // 顧客共通情報
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        String processDayTimeFrom = DateFormatUtil.dateFormatToHyphen(apiReq.getProcessDayTimeFrom());
        String processDayTimeTo = DateFormatUtil.dateFormatToHyphen(apiReq.getProcessDayTimeTo());
        
        // 画面.処理対象期間（To） < 画面.処理対象期間（From）のチェック
        if (!DateUtil.isValidFromTo(processDayTimeFrom, processDayTimeTo, DateFormatUtil.YYYYMMDD, DateFormatUtil.SEPARATED_YYYYMMDD)) {
            return jc.toString(IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, 
                    ERRORS_DATE_SPECIFY_FROM_TO, IfaCommonUtil.getMessage(ERRORS_DATE_SPECIFY_FROM_TO, new String[] {"処理対象期間"})));
        }
        
        if (isDifferenceDate(processDayTimeFrom, processDayTimeTo)) {
            return jc.toString(IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, 
                    ERRORS_DATA_RANGE, IfaCommonUtil.getMessage(ERRORS_DATA_RANGE, new String[] {"処理対象期間", "3ヶ月"})));
        }
        
        String fwSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class);
        
        DataList<IfaPersonalInfoManageLedgerListA005aDtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaPersonalInfoManageLedgerListService", "csvOutputA005a",
                new TypeReference<DataList<IfaPersonalInfoManageLedgerListA005aDtoResponse>>() {
                }, appReq, fwSessionId);
        
        DataList<IfaPersonalInfoManageLedgerListA005aApiResponse> apiRes = new DataList<IfaPersonalInfoManageLedgerListA005aApiResponse>();
        
        // 共通部品「Controller基底クラス．ステータス＆メッセージのデータリスト変換」へ引き渡す変数設定であり”TRUE”固定で設定する。
        // 共通部品「Controller基底クラス．ステータス＆メッセージのデータリスト変換」にてメッセージの設定を行う。
        BeanUtils.copyProperties(apiRes, appRes);
        if (apiRes.getErrorLevel() != ErrorLevel.FATAL.getId()) {
            setStatusAndMessageToDataList(apiRes, true);
        }
        
        return jc.toString(apiRes);

    }

    /**
     * アクセス：/internalAdminMenu/personalInfoManage/ifaPersonalInfoManageLedgerListCsvOutputA005b
     * アクションID：A005 アクション名：CSV出力
     * APIリクエスト：IfaPersonalInfoManageLedgerListA005bApiRequest
     * Apiレスポンス：IfaPersonalInfoManageLedgerListA005bApiResponse
     * Dtoリクエスト：IfaPersonalInfoManageLedgerListA005bDtoRequest
     * Dtoレスポンス：IfaPersonalInfoManageLedgerListA005baDtoResponse

     * @param apiReq リクエスト
     * @exception Exception SQLExceptionなど 
     */
    @PostMapping("/internalAdminMenu/personalInfoManage/ifaPersonalInfoManageLedgerListCsvOutputA005b")
    @ResponseFile
    public void csvOutputA005b(@RequestBody IfaPersonalInfoManageLedgerListA005bApiRequest apiReq,
            HttpServletResponse response) throws Exception {

        // 顧客共通情報
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // CSVダウンロード
        doDownLoadCsvFile(response, apiReq.getCsvDownloadFile(), getCsvFileName("個人情報管理台帳一覧"),
                IfaCommonUtil.getUserAccount());
    }

    /**
     * アクセス：/internalAdminMenu/personalInfoManage/ifaPersonalInfoManageLedgerListOkA006
     * アクションID：A006
     * アクション名：OK
     * APIリクエスト：IfaPersonalInfoManageLedgerListA006ApiRequest
     * Apiレスポンス：IfaPersonalInfoManageLedgerListA006ApiResponse
     * Dtoリクエスト：IfaPersonalInfoManageLedgerListA006DtoRequest
     * Dtoレスポンス：IfaPersonalInfoManageLedgerListA006DtoResponse

     * @param apiReq リクエスト
     * @return appRes
     * @exception Exception SQLExceptionなど 
    */
    @PostMapping("/internalAdminMenu/personalInfoManage/ifaPersonalInfoManageLedgerListOkA006")
    public String okA006(@Validated @RequestBody IfaPersonalInfoManageLedgerListA006ApiRequest apiReq, BindingResult result)throws Exception {

        String errorMsg = "";

        // 単項目チェック
        List<FieldError> fieldErrors = result.getFieldErrors();
        for (FieldError error : fieldErrors) {
            // 入力チェック1
            if ("Size".equals(error.getCode()) && error.getField().contains("preservePeriod")) {
                errorMsg = ERROR_MSG07;
                break;
            }
            
            // 入力チェック3
            if ("Size".equals(error.getCode()) && error.getField().contains("corDepositOutline")) {
                errorMsg = COR_DEPOSIT_OUTLINE_ERROR_COM;
                break;
            }
            
            // 入力チェック4
            if ("Size".equals(error.getCode()) && error.getField().contains("corDonationOutline")) {
                errorMsg = COR_DONATION_OUTLINE_ERROR_COM;
                break;
            }
            
            // 入力チェック5
            if ("Size".equals(error.getCode()) && error.getField().contains("corDepositoryOutline")) {
                errorMsg = COR_DEPOSITORY_OUTLINE_ERROR_COM;
                break;
            }
        }
        
        // 1つでもチェックに引っかかればエラー返却（現行踏襲）
        if (! "".equals(errorMsg)) {
            return jc.toString(IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, null, errorMsg));  
        }
        
        // 相関チェック
        for (IfaPersonalInfoManageLedgerListA006ApiRequestPersonalInfoManageLedger personalInfo : apiReq.getPersonalInfoManageLedger()) {
            // 入力チェック2
            if (personalInfo.getDisposeDateYmd().length() > 0 && !StringUtils.equals(personalInfo.getDisposeDateYmd(), "-")) {
                String disposeDateYmd = DateFormatUtil.dateFormatToHyphen(personalInfo.getDisposeDateYmd());
                DateFormat format = new SimpleDateFormat(DateFormatUtil.SEPARATED_YYYYMMDD);
                Date disposeDateYmdDate = null;

                try {
                    disposeDateYmdDate = format.parse(disposeDateYmd);
                } catch (ParseException e) {
                    errorMsg = "廃棄した年月日は有効な日付(YYYY/MM/DD形式)で入力してください。";
                    break;
                }

                if (DateUtil.now().before(disposeDateYmdDate)) {
                    errorMsg = "廃棄した年月日に未来日は設定できません。廃棄が完了してから廃棄した年月日をご入力ください。";
                    break;
                }
                
                // DB登録用に廃棄年月日を8桁に変換
                personalInfo.setDisposeDateYmd(DateFormatUtil.dateFormatToYmdNoSign(disposeDateYmd));
            }


            // 相関チェック1
            if (StringUtils.equals(personalInfo.getDepositDestinations(), "3")
                    && (personalInfo.getCorDepositOutline().length() == 0 || StringUtils.equals(personalInfo.getCorDepositOutline(), "-"))) {
                errorMsg = ERROR_MSG09;
                break;
            }
            
            // 相関チェック2
            if (StringUtils.equals(personalInfo.getDestination(), "3")
                    && (personalInfo.getCorDonationOutline().length() == 0 || StringUtils.equals(personalInfo.getCorDonationOutline(), "-"))) {
                errorMsg = ERROR_MSG10;
                break;
            }    
            
            // 相関チェック3
            if (StringUtils.equals(personalInfo.getStorageSendingMedium(), "2")
                    && StringUtils.equals(personalInfo.getStorageSpace(), "3")
                    && (personalInfo.getCorDepositoryOutline().length() == 0 || StringUtils.equals(personalInfo.getCorDepositoryOutline(), "-"))) {
                errorMsg = ERROR_MSG11;
                break;
            }           
            
            // 相関チェック4
            if (StringUtils.equals(personalInfo.getStorageSendingMedium(), "3")
                    && StringUtils.equals(personalInfo.getStorageSpace(), "4")
                    && (personalInfo.getCorDepositoryOutline().length() == 0 || StringUtils.equals(personalInfo.getCorDepositoryOutline(), "-"))) {
                errorMsg = ERROR_MSG11;
                break;
            }

            // 相関チェック5
            if (!StringUtils.equals(personalInfo.getStorageSpace(), "1")
                    && !StringUtils.equals(personalInfo.getStorageSpace(), "5")
                    && personalInfo.getPreservePeriod().length() == 0
                    && !StringUtils.equals(personalInfo.getNotDispose(), "2")                
                ) {
                errorMsg = ERROR_MSG01;
                break;
            }       

            if (!StringUtils.equals(personalInfo.getStorageSpace(), "1") && !StringUtils.equals(personalInfo.getStorageSpace(), "5")
                    && (StringUtils.equals(personalInfo.getDisposeMethod(), "0") || StringUtils.equals(personalInfo.getDisposeMethod(), "1"))
                    && !StringUtils.equals(personalInfo.getNotDispose(), "2")) {
                errorMsg = ERROR_MSG02;
                break;
            }           
            
            // 相関チェック6
            if (!StringUtils.equals(personalInfo.getStorageSendingMedium(), "4")
                    && (
                            StringUtils.equals(personalInfo.getDepositDestinations(), "1")
                            || StringUtils.equals(personalInfo.getDepositDestinations(), "2"))
                    && (
                            StringUtils.equals(personalInfo.getDestination(), "1")
                            || StringUtils.equals(personalInfo.getDestination(), "4"))       
                    && (
                            StringUtils.equals(personalInfo.getStorageSpace(), "5")
                            || StringUtils.equals(personalInfo.getStorageSpace(), "1"))   
                ) {
                errorMsg = ERROR_MSG03;
                break;
            }    
            
            // 相関チェック7
            if (StringUtils.equals(personalInfo.getStorageSendingMedium(), "1")
                    && !StringUtils.equals(personalInfo.getStorageSpace(), "2")
                    && !StringUtils.equals(personalInfo.getStorageSpace(), "5")) {
                errorMsg = ERROR_MSG04;
                break;
            }       
            
            // 相関チェック8
            if (StringUtils.equals(personalInfo.getStorageSendingMedium(), "2")
                    && !StringUtils.equals(personalInfo.getStorageSpace(), "5")
                    && !StringUtils.equals(personalInfo.getStorageSpace(), "3")) {
                errorMsg = ERROR_MSG05;
                break;
            }       
            
            // 相関チェック9
            if (StringUtils.equals(personalInfo.getStorageSendingMedium(), "3")
                    && !StringUtils.equals(personalInfo.getStorageSpace(), "5")
                    && !StringUtils.equals(personalInfo.getStorageSpace(), "4")) {
                errorMsg = ERROR_MSG06;
                break; 
            }   
            
            // 相関チェック10
            if (StringUtils.equals(personalInfo.getStorageSendingMedium(), "4") && (
                    !StringUtils.equals(personalInfo.getDepositDestinations(), "1")
                    || !StringUtils.equals(personalInfo.getDestination(), "1")
                    || !StringUtils.equals(personalInfo.getStorageSpace(), "1")    
                    || !StringUtils.equals(personalInfo.getPreservePeriod(), "-")
                    || !StringUtils.equals(personalInfo.getDisposeMethod(), "1")
                    || !StringUtils.equals(personalInfo.getNotDispose(), "1")
                    || !StringUtils.equals(personalInfo.getDisposeDateYmd(), "-")
                    || !StringUtils.equals(personalInfo.getCorDepositOutline(), "-")
                    || !StringUtils.equals(personalInfo.getCorDonationOutline(), "-")
                    || !StringUtils.equals(personalInfo.getCorDepositoryOutline(), "-")) 
                ) {
                errorMsg = ERROR_MSG08;
                break; 
            }

            // 相関チェック11
            if (StringUtils.equals(personalInfo.getDepositDestinations(), "2")
                    && !StringUtils.equals(personalInfo.getCorDepositOutline(), "-")) {
                errorMsg = ERROR_MSG12;
                break;
            }  
            
            // 相関チェック12
            if ((StringUtils.equals(personalInfo.getDestination(), "2")
                    || StringUtils.equals(personalInfo.getDestination(), "4"))
                    && !StringUtils.equals(personalInfo.getCorDonationOutline(), "-")) {
                errorMsg = ERROR_MSG13;
                break;
            } 

            // 相関チェック13
            if (StringUtils.equals(personalInfo.getStorageSpace(), "5") && (
                    !StringUtils.equals(personalInfo.getPreservePeriod(), "-")
                    || !StringUtils.equals(personalInfo.getDisposeMethod(), "1")
                    || !StringUtils.equals(personalInfo.getNotDispose(), "1")
                    || !StringUtils.equals(personalInfo.getDisposeDateYmd(), "-")
                    || !StringUtils.equals(personalInfo.getCorDepositoryOutline(), "-"))
                ) {
                errorMsg = "[保管期間][廃棄方法][破棄しない][廃棄した年月日][摘要（保管場所詳細）]に「-」を設定してください。";
                break;  
            }  

            // 相関チェック14
            if (StringUtils.equals(personalInfo.getStorageSpace(), "2")
                    && StringUtils.equals(personalInfo.getNotDispose(), "1")
                    && !StringUtils.equals(personalInfo.getCorDepositoryOutline(), "-")) {
                errorMsg = "摘要（保管場所詳細）に「-」を手入力してください。※保管場所詳細の記述は不要です。";
                break;  
            }  
            
            // 相関チェック15
            if (StringUtils.equals(personalInfo.getNotDispose(), "2") && (
                    ! StringUtils.equals(personalInfo.getPreservePeriod(), "-")
                    || !StringUtils.equals(personalInfo.getDisposeMethod(), "1")
                    || !StringUtils.equals(personalInfo.getDisposeDateYmd(), "-"))
                ) {
                errorMsg = ERROR_MSG14;
                break;  
            }  
        }
        
        // 1つでもチェックに引っかかればエラー返却（現行踏襲）
        if (! "".equals(errorMsg)) {
            return jc.toString(IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, null, errorMsg));  
        }
        
        IfaPersonalInfoManageLedgerListA006DtoRequest appReq = new IfaPersonalInfoManageLedgerListA006DtoRequest();

        // 顧客共通情報
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaPersonalInfoManageLedgerListA006DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaPersonalInfoManageLedgerListService",
                "okA006", new TypeReference<DataList<IfaPersonalInfoManageLedgerListA006DtoResponse>>() {
                }, appReq);
        
        DataList<IfaPersonalInfoManageLedgerListA006ApiResponse> apiRes = new DataList<IfaPersonalInfoManageLedgerListA006ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**
     * 日付Fromと日付Toが3ヵ月以上離れているか

     * @param dateFrom 日付From
     * @param dateTo 日付To
     * @return SQL処理結果
     */
    private boolean isDifferenceDate(String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateFormatUtil.SEPARATED_YYYYMMDD); 

        LocalDate processDayTimeFromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate processDayTimeToDate = LocalDate.parse(dateTo, formatter);

        return processDayTimeToDate.minusMonths(3).compareTo(processDayTimeFromDate) > 0;
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

