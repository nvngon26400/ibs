package com.sbisec.helios.ap.bizcommon.component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.sbisec.helios.ap.bizcommon.dao.Fct031Dao;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct031Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct031Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.model.BaseOutputDto;
import com.sbisec.helios.ap.bizcommon.model.InputFct031Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct031Dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 共通関数：FCT031
 * 顧客情報取得
 *
 * @author SCSK
 */
@Component
public class Fct031 {
    
    @Autowired
    private Fct031Dao dao;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Fct031.class);
    
    /**
     * 顧客情報取得
     *
     * @param input リクエストDto
     * @return レスポンスDto
     * @throws Exception 顧客情報取得時に例外が発生した場合
     */
    public OutputFct031Dto getData(InputFct031Dto input) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Fct031.getData");
        }
        
        OutputFct031Dto resDto = new OutputFct031Dto();
        
        // 必須項目判断処理
        // 部店コード, 口座番号のいずれかがない場合空のレスポンスを返却する
        if (input.getButenCode().isEmpty() || input.getAccountNumber().isEmpty()) {
            return resDto;
        }
        // ①　該当顧客の情報を取得する。（SQL001を呼び出し）
        Fct031Sql001RequestModel sql001Req = new Fct031Sql001RequestModel();
        sql001Req.setButenCode(input.getButenCode());
        sql001Req.setAccountNumber(input.getAccountNumber());
        List<Fct031Sql001ResponseModel> sql001Res = new ArrayList<>();
        try {
            sql001Res = dao.getCustomerInfo(sql001Req);
        } catch (Exception e) {
            LOGGER.error("Fct031.getData Fct031DaoImpl.getCustomerInfo Exception[{}]", e.getMessage());
            return new OutputFct031Dto();
        }
        
        // 取得件数 == 1件の場合
        if (sql001Res.size() == 1) {
            // 次の処理へ
        } else {
            // 上記以外：顧客情報取得失敗エラーを返す
            resDto.setReturnCode(BaseOutputDto.RETURN_CODE_E001);
            return resDto;
        }
        
        // ②　SQL001で取得した項目をレスポンス項目に設定する。
        // A.顧客コード
        resDto.setCustomerCode(sql001Res.get(0).getCustomerId());
        // A.部店コード
        resDto.setButenCode(sql001Res.get(0).getButenCode());
        // A.口座番号
        BigDecimal tmp = BigDecimal.valueOf(sql001Res.get(0).getAccountNumber());
        resDto.setAccountNumber(tmp.toString());
        // A.顧客名（漢字）
        resDto.setCustomerNameKanji(sql001Res.get(0).getNameKanji());
        // A.顧客名（カナ）
        resDto.setCustomerNameKana(sql001Res.get(0).getNameKana());
        // A.コンプラランク
        resDto.setCompRank(sql001Res.get(0).getTcCompRank());
        // A.契約締結前交付書面コード
        resDto.setCustomerAttribute(sql001Res.get(0).getCustomerAttribute());
        // A.契約締結前交付書面コード名
        resDto.setCustomerAttributeName(sql001Res.get(0).getCustomerAttributeName());
        // A.扱者コード
        resDto.setDealerNumber(sql001Res.get(0).getDealerNumber());
        // A.生年月日
        if (!Objects.isNull(sql001Res.get(0).getBirthDay())) {
            // NULL以外の場合セット（法人の場合はNULLとなる）
            resDto.setCorBirthFlg(new SimpleDateFormat("yyyyMMdd").parse(sql001Res.get(0).getBirthDay()));
        }
        // A.年齢
        if (!Objects.isNull(sql001Res.get(0).getAge())) {
            // NULL以外の場合セット（法人の場合はNULLとなる）
            resDto.setAge(Integer.valueOf(sql001Res.get(0).getAge().trim()));
        }
        // A.仲介業者コード
        resDto.setBrokerCode(sql001Res.get(0).getBrokerCode());
        // A.仲介業者営業員コード
        resDto.setBrokerChargeCode(sql001Res.get(0).getIntermediaryEmpCd());
        // B.法人区分
        resDto.setCorporationType(sql001Res.get(0).getUaiCorporationKbn());
        // B.自宅電話番号(市外局番) 
        resDto.setHomePhoneNumberAreaCode(sql001Res.get(0).getUaiPhoneLongDist());
        // B.自宅電話番号(市内局番)
        resDto.setHomePhoneNumberLocalAreaCode(sql001Res.get(0).getUaiPhoneCityCode());
        // B.自宅電話番号(番号)
        resDto.setHomePhoneNumber(sql001Res.get(0).getUaiPhoneNumber());
        // B.携帯電話番号(上5桁) 
        resDto.setPhoneNumber1(sql001Res.get(0).getUaiCellularNumber1());
        // B.携帯電話番号(中5桁) 
        resDto.setPhoneNumber2(sql001Res.get(0).getUaiCellularNumber2());
        // B.携帯電話番号(下5桁)
        resDto.setPhoneNumber3(sql001Res.get(0).getUaiCellularNumber3());
        // B.代表者名(漢字)
        resDto.setRepresentativeNameKanji(sql001Res.get(0).getUaiDaihyoNameKanji());
        // B.代表者名(カナ)
        resDto.setRepresentativeNameKana(sql001Res.get(0).getUaiDaihyoNameKana());
        // B.代理人名(漢字)
        resDto.setAgentNameKanji(sql001Res.get(0).getUaiDairiNameKanji());
        // B.代理人名(カナ)
        resDto.setAgentNameKana(sql001Res.get(0).getUaiDairiNameKana());
        // B.代理人肩書区分
        resDto.setTitleOfAgeNtType(sql001Res.get(0).getUaiOfficeDairiKbn());
        // B.特定口座区分
        resDto.setSpecificAccount(sql001Res.get(0).getUaiSpecificKbn());
        // B.特定口座配当受入開始日
        resDto.setSpecificAccountDividendStartDate(sql001Res.get(0).getUaiSpecificDividendStartDt());
        // B.特定口座配当受入終了日
        resDto.setSpecificAccountDividendEndDate(sql001Res.get(0).getUaiSpecificDividendEndDt());
        // B.マル優適格者区分
        resDto.setTaxExemptQualifiedPersonType(sql001Res.get(0).getUaiMaruyuTekikakushaKbn());
        // B.ISA契約区分
        resDto.setIsaContractType(sql001Res.get(0).getUaiIsaContractKbn());
        // B.ジュニアISA契約区分
        resDto.setJrIsaContractType(sql001Res.get(0).getUaiJnisaContractKbn());
        // B.ジュニアNISA口座番号
        resDto.setJrNisaAccountNumber(sql001Res.get(0).getUaiJnisaKozaNo());
        // B.ジュニア特定口座区分
        resDto.setJrSpecificAccount(sql001Res.get(0).getUaiJnisaSpecificKbn());
        // C.仲介業者支店名
        resDto.setBrokerName(sql001Res.get(0).getBrokerName());
        // D.仲介業者支店名
        resDto.setBranchName(sql001Res.get(0).getBranchName());
        // D.仲介業者担当者名
        resDto.setBrokerChargeName(sql001Res.get(0).getBrokerChargeName());
        // E.注意情報エラー件数
        resDto.setNoteInfoErrorCount(sql001Res.get(0).getNoteInfoErrorCount());
        // F.注意情報件数
        resDto.setNoteInfoCount(sql001Res.get(0).getNoteInfoCount());
        // G.未読の重要なお知らせによる取引制限エラー件数
        resDto.setUnreadImportantNoticeTransactionLimitErrorNumber(
                sql001Res.get(0).getUnreadImportantNoticeTransactionLimitErrorNumber());
        // H.未読の重要なお知らせによる取引制限件数
        resDto.setUnreadImportantNoticeTransactionLimitNumber(
                sql001Res.get(0).getUnreadImportantNoticeTransactionLimitNumber());
        // ③　処理を終了して呼出元にレスポンスを返却する。
        return resDto;
    }
}
