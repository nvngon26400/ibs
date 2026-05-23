package com.sbisec.helios.ap.internalAdminMenu.formAcquire.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.IfaContractNoteCustomerLedgerAcquireDao;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaContractNoteCustomerLedgerAcquireSql001ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaContractNoteCustomerLedgerAcquireSql002RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaContractNoteCustomerLedgerAcquireSql003RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model.IfaContractNoteCustomerLedgerAcquireSql004RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dto.IfaContractNoteCustomerLedgerAcquireA001RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dto.IfaContractNoteCustomerLedgerAcquireA001ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dto.IfaContractNoteCustomerLedgerAcquireA002RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dto.IfaContractNoteCustomerLedgerAcquireA002ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.service.IfaContractNoteCustomerLedgerAcquireService;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.util.LedgerClass;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.util.LedgerFile;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 画面ID：SUB0402_02-01
 * 画面名：取引日記帳・顧客勘定元帳取得
 *
 * @author SCSK
 2024/05/08 新規作成
 */
@Component(value = "cmpIfaContractNoteCustomerLedgerAcquireService")
public class IfaContractNoteCustomerLedgerAcquireServiceImpl implements IfaContractNoteCustomerLedgerAcquireService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaContractNoteCustomerLedgerAcquireServiceImpl.class);
    
    private static final String MSG_INFO_NO_DIR = "info.ita.StatutoryBookDirectory.notfound";
    
    private static final String MSG_ERR_AUTH = "errors.cmn.ifaAgentCodes.notExist";
    
    private static final String MSG_ERR_NO_DATA = "errors.dataList.notfound";
    
    private static final String MSG_WRN_MAX = "warnings.dataList.overMaxRownum";
    
    /** SBI証券本店 */
    private static final String SBI_HEAD_OFFICE = "1";
    
    /** 最大明細件数 */
    private static final int MAX_ITEMS = 5000;
    
    @Autowired
    private IfaContractNoteCustomerLedgerAcquireDao dao;
    
    @Autowired
    private Fct030 fct030;
    
    /**
     * アクションID：A002
     * アクション名：表示
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public DataList<IfaContractNoteCustomerLedgerAcquireA002ResponseDto> displayA002(
            IfaContractNoteCustomerLedgerAcquireA002RequestDto dtoReq) throws Exception {
        
        // 仲介業者コード
        List<String> reqBrokerCodes = StringUtils.isBlank(dtoReq.getBrokerCode()) ? Collections.emptyList()
                : Arrays.asList(dtoReq.getBrokerCode().split(","));
        
        // 作成日チェック
        var fromDate = LocalDate.parse(dtoReq.getCreateDateFrom(), DateTimeFormatter.BASIC_ISO_DATE);
        var toDate = LocalDate.parse(dtoReq.getCreateDateTo(), DateTimeFormatter.BASIC_ISO_DATE);
        // ユーザ共通情報の取得
        var ua = IfaCommonUtil.getUserAccount();
        // ユーザ共通情報.権限コード ≠ '1':SBI証券本店 の場合
        List<String> fct030BrokerCodes;
        if (!StringUtils.equals(ua.getPrivId(), SBI_HEAD_OFFICE)) {
            // FCT030　仲介業者コード営業員リスト取得
            fct030BrokerCodes = fct030.getData(null).getBrokerChargeList().stream().map(BrokerCharge::getBrokerCode)
                    .collect(Collectors.toList());
            // FCT030が0件あるいは、リクエストの仲介業者コードが1件も含まれていない場合エラー
            if (fct030BrokerCodes.isEmpty()
                    || (!reqBrokerCodes.isEmpty() && Collections.disjoint(fct030BrokerCodes, reqBrokerCodes))) {
                return IfaCommonUtil.createDtaList(null, ErrorLevel.FATAL, MSG_ERR_AUTH);
            }
        } else {
            fct030BrokerCodes = Collections.emptyList();
        }
        // 法定帳簿一時テーブルのデータを削除する。
        dao.deleteIfaContractNoteCustomerLedgerAcquireSql003(
                new IfaContractNoteCustomerLedgerAcquireSql003RequestModel(ua.getUserId()));
        // 法定帳簿ファイルディレクトリ内にディレクトリが存在するかのチェックを行う。
        var isBrokerCodeExclude = StringUtils.equals(dtoReq.getChkBrokerCodeExclude(), "true");
        // 帳票種別ディレクトリ名(指定があれば1件、なければ全件)
        var docTypeDirs = StringUtils.isNotBlank(dtoReq.getLedgerClass())
                ? List.of(LedgerClass.valueOfCode(dtoReq.getLedgerClass()).getDirName())
                : Arrays.asList(LedgerClass.values()).stream().map(LedgerClass::getDirName)
                        .collect(Collectors.toList());
        var sql004Items = new ArrayList<IfaContractNoteCustomerLedgerAcquireSql004RequestModel.Item>();
        for (var docTypeDir : docTypeDirs) {
            var docDir = Paths.get(dtoReq.getFileDirectory(), docTypeDir);
            if (!Files.isDirectory(docDir)) {
                LOGGER.info("Path: {} is not directory or not found.", docDir);
                return IfaCommonUtil.createDtaList(null, ErrorLevel.INFO, MSG_ERR_NO_DATA);
            }
            try (var stream = Files.list(docDir)) {
                stream.filter(p -> filterDir(p, docTypeDir, fromDate, toDate))//
                        .forEach(p -> {
                            try (var stream2 = Files.list(p)) {
                                stream2.map(psub -> {
                                    return toModel(psub, fromDate, toDate, docTypeDir, ua, isBrokerCodeExclude,
                                            fct030BrokerCodes, reqBrokerCodes);
                                }).filter(Objects::nonNull).forEach(m -> {
                                    sql004Items.add(m);
                                });
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
            }
        }
        if (sql004Items.isEmpty()) {
            return IfaCommonUtil.createDtaList(null, ErrorLevel.INFO, MSG_ERR_NO_DATA);
        }
        // 一時テーブル登録
        dao.insertIfaContractNoteCustomerLedgerAcquireSql004(
                new IfaContractNoteCustomerLedgerAcquireSql004RequestModel(ua.getUserId(), sql004Items));
        // 一時テーブルから取得
        var sql002List = dao
                .selectIfaContractNoteCustomerLedgerAcquireSql002(
                        new IfaContractNoteCustomerLedgerAcquireSql002RequestModel(ua.getUserId(), MAX_ITEMS))
                .getDataList();
        
        var totalCount = sql002List.stream().findFirst().map(i -> i.getTotalCount()).orElseThrow();
        var items = sql002List.stream().map(i -> {
            var dateA = LedgerFile.parse(i.getFileName()).getDate();
            return IfaContractNoteCustomerLedgerAcquireA002ResponseDto.builder() //
                    .createDateA(dateA) // ファイル名から抽出した日付
                    .createDateB(i.getCreateDate()) //
                    .brokerCode(i.getBrokerCode()) //
                    .brokerName(i.getBrokerName()) //
                    .docClass(i.getBookType()) //
                    .targetDoc(i.getBookName()) //
                    .downloadFileName(i.getFileName()) //
                    .dl(i.getDl()) //
                    .build();
        }).collect(Collectors.toList());
        if (totalCount > MAX_ITEMS) {
            // 最大件数超過の場合は、警告つきのデータを返却する
            return IfaCommonUtil.createDataList(items, ErrorLevel.WARNING, MSG_WRN_MAX,
                    IfaCommonUtil.getMessage(MSG_WRN_MAX, new String[] { MAX_ITEMS + "", totalCount + "" }));
        } else {
            return IfaCommonUtil.createDataList(items, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
        }
    }
    
    /** 帳票ファイル情報をSql004に変換する(対象外はnull応答) */
    private IfaContractNoteCustomerLedgerAcquireSql004RequestModel.Item toModel(Path p, LocalDate fromDate,
            LocalDate toDate, String docTypeDir, UserAccount ua, boolean isBrokerCodeExclude,
            List<String> fct030BrokerCodes, List<String> reqBrokerCodes) {
        
        if (!Files.isRegularFile(p)) {
            return null;
        }
        var fileName = getFileName(p);
        if (Objects.isNull(fileName)) {
            return null;
        }
        var lf = LedgerFile.parse(fileName);
        if (Objects.isNull(lf)) { // フォーマット不正
            LOGGER.info("Path: {} is violation of naming rules.", p.toAbsolutePath().toString());
            return null;
        }
        // ディレクトリが顧客勘定元帳 ＝ "03" の場合、ファイル日付をチェック
        if (StringUtils.equals(docTypeDir, LedgerClass.CUST.getDirName())) {
            
            var fileDate = LocalDate.parse(lf.getDate(), DateTimeFormatter.BASIC_ISO_DATE);
            if (fileDate.isBefore(fromDate) || fileDate.isAfter(toDate)) {
                return null;
            }
        }
        // 権限がSBI本店 かつ リクエスト.仲介業者コードがブランクではない
        if (StringUtils.equals(ua.getPrivId(), SBI_HEAD_OFFICE) && !reqBrokerCodes.isEmpty()) {
            // 除外ONで含む場合　or 除外OFFで含まない場合、除外
            if (isBrokerCodeExclude == reqBrokerCodes.contains(lf.getBrokerCode())) {
                return null;
            }
        }
        // 権限が本店以外で、仲介業者営業員リストに含まれない仲介業者コードのファイルや、画面指定以外の仲介業者コードのファイルを除外
        if (!StringUtils.equals(ua.getPrivId(), SBI_HEAD_OFFICE)) {
            if (!fct030BrokerCodes.contains(lf.getBrokerCode())
                    || (!reqBrokerCodes.isEmpty() && !reqBrokerCodes.contains(lf.getBrokerCode()))) {
                return null;
            }
        }
        var dirName = Optional.ofNullable(p.getParent()) //
                .map(Path::getFileName) //
                .map(Path::toString) //
                .orElse(null);
        if (Objects.isNull(dirName)) {
            return null;
        }
        
        return new IfaContractNoteCustomerLedgerAcquireSql004RequestModel.Item(fileName,
                LedgerClass.valueOfName(lf.getTargetLedger()).getCode(), lf.getBrokerCode(), dirName);
    }
    
    /** 日付ディレクトリをフィルターする */
    private boolean filterDir(Path p, String docTypeDir, LocalDate fromDate, LocalDate toDate) {
        
        // ディレクトリのみ
        if (!Files.isDirectory(p)) {
            return false;
        }
        var dirName = getFileName(p);
        if (Objects.isNull(dirName)) {
            return false;
        }
        // ディレクトリ日付のチェック
        var dirDate = LocalDate.parse(dirName, DateTimeFormatter.BASIC_ISO_DATE);
        // ディレクトリが顧客勘定元帳の場合
        if (StringUtils.equals(docTypeDir, LedgerClass.CUST.getDirName())) {
            var fromYm = fromDate.query(YearMonth::from);
            var toYm = toDate.query(YearMonth::from);
            var dirYm = dirDate.query(YearMonth::from);
            // リクエスト.作成日Fromの年月 + 1月 ～ リクエスト.作成日Toの年月 + 1月の期間外
            if (dirYm.isBefore(fromYm.plusMonths(1L)) || dirYm.isAfter(toYm.plusMonths(1L))) {
                return false;
            }
        } else {
            // 作成日（ディレクトリ名）がリクエスト.作成日From + 1日 ～ リクエスト.作成日To + 1日の期間外
            if (dirDate.isBefore(fromDate.plusDays(1L)) || dirDate.isAfter(toDate.plusDays(1L))) {
                return false;
            }
        }
        return true;
        
    }
    
    /**
     * アクションID：A001
     * アクション名：初期化
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaContractNoteCustomerLedgerAcquireA001ResponseDto> initializeA001(
            IfaContractNoteCustomerLedgerAcquireA001RequestDto dtoReq) throws Exception {
        
        var ret = new IfaContractNoteCustomerLedgerAcquireA001ResponseDto();
        // SQL005 検索条件エリア.帳簿種別の初期値を設定する。
        ret.setLedgerClassList(
                dao.selectIfaContractNoteCustomerLedgerAcquireSql005().getDataList().stream().map(res -> {
                    return new IfaContractNoteCustomerLedgerAcquireA001ResponseDto.LedgerClass(res.getCodeId(),
                            res.getCodeName());
                }).collect(Collectors.toList()));
        // SQL001 取引日記帳・顧客勘定元帳の格納先ファイルディレクトリを取得する。
        var fileDir = dao.selectIfaContractNoteCustomerLedgerAcquireSql001().getDataList().stream().findFirst()
                .map(IfaContractNoteCustomerLedgerAcquireSql001ResponseModel::getFileDir).orElse(null);
        if (Objects.isNull(fileDir)) {
            return IfaCommonUtil.createDtaList(null, ErrorLevel.INFO, MSG_INFO_NO_DIR);
        }
        ret.setFileDirectory(fileDir);
        return IfaCommonUtil.createDataList(List.of(ret), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
    }
    
    private String getFileName(Path path) {
        
        return Optional.ofNullable(path.getFileName()).map(Path::toString).orElse(null);
    }
    
}
