package com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteEmployeeSql002ResponseModel;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.IfaReleaseNoteEmployeeDao;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model.IfaReleaseNoteEmployeeSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteEmployeeA001RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteEmployeeA001ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteEmployeeA002RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteEmployeeA002ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.service.IfaReleaseNoteEmployeeService;

/**
 * 画面ID：SUB0512-01
 * 画面名：リリースノート(社員用)
 * 2025/11/06 新規作成
 *
 * @author 大連 葉
 */
@Component(value = "cmpIfaReleaseNoteEmployeeService")
public class IfaReleaseNoteEmployeeServiceImpL implements IfaReleaseNoteEmployeeService{

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaReleaseNoteEmployeeServiceImpL.class);

    @Autowired
    private IfaReleaseNoteEmployeeDao dao;

    /**
     * アクションID：A001
     * アクション名：初期化
     * リクエスト：IfaReleaseNoteEmployeeA001RequestDto
     * レスポンス：IfaReleaseNoteEmployeeA001ResponseDto
     *
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaReleaseNoteEmployeeA001ResponseDto> initializeA001(IfaReleaseNoteEmployeeA001RequestDto dtoReq)
            throws Exception {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaReleaseNoteEmployeeServiceImpL.initializeA001");
        }

        // ①リリースノート一覧を取得する。
        DataList<IfaReleaseNoteEmployeeSql001ResponseModel> sql001Res = dao.selectIfaReleaseNoteEmployeeSql001(dtoReq.getDisplayYear());

        // ②リリースノート表示対象年を取得する。
        DataList<IfaReleaseNoteEmployeeSql002ResponseModel> sql002Res = dao.selectIfaReleaseNoteEmployeeSql002();
        int currentYear = LocalDate.now().getYear();
        // SQL002.最大年数<現在年の場合、最大年数を現在年に更新する
        String maxYear = sql002Res.getDataList().get(0).getMaxYear();
        if (Integer.parseInt(maxYear) < currentYear) {
            maxYear = String.valueOf(currentYear);
        }
        // 現在年<SQL002.最小年数の場合、最小年数を現在年に更新する
        String minYear = sql002Res.getDataList().get(0).getMinYear();
        if (currentYear < Integer.parseInt(minYear)) {
            minYear = String.valueOf(currentYear);
        }

        // レスポンスの設定
        IfaReleaseNoteEmployeeA001ResponseDto res = new IfaReleaseNoteEmployeeA001ResponseDto();
        List<IfaReleaseNoteEmployeeA001ResponseDto> resList = new ArrayList<IfaReleaseNoteEmployeeA001ResponseDto>();
        if (sql001Res.getDataList().isEmpty()) {
            res.setIfaReleaseNoteEmployeeList(new ArrayList <IfaReleaseNoteEmployeeSql001ResponseModel>());
        } else {
            res.setIfaReleaseNoteEmployeeList(sql001Res.getDataList());
        }
        res.setMinYear(minYear);
        res.setMaxYear(maxYear);
        resList.add(res);

        DataList<IfaReleaseNoteEmployeeA001ResponseDto> dtoRes = new DataList<IfaReleaseNoteEmployeeA001ResponseDto>();
        dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), StringUtil.EMPTY_STRING);
        return dtoRes;
    }

    /**
     * アクションID：A002
     * アクション名：表示対象年選択
     * リクエスト：IfaReleaseNoteEmployeeA002RequestDto
     * レスポンス：IfaReleaseNoteEmployeeA002ResponseDto
     *
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 表示対象年選択処理で例外が発生した場合
     */
    public DataList<IfaReleaseNoteEmployeeA002ResponseDto> selectDisplayYearA002(IfaReleaseNoteEmployeeA002RequestDto dtoReq)
            throws Exception {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaExternalLinkServiceImpL.selectDisplayYearA002");
        }

        // ②表示対象年選択
        DataList<IfaReleaseNoteEmployeeSql001ResponseModel> sql001Res = dao.selectIfaReleaseNoteEmployeeSql001(dtoReq.getDisplayYear());

        // レスポンスの設定
        List<IfaReleaseNoteEmployeeA002ResponseDto> resList = new ArrayList<IfaReleaseNoteEmployeeA002ResponseDto>();
        if (sql001Res.getDataList().size() > 0) {
            for (IfaReleaseNoteEmployeeSql001ResponseModel model : sql001Res.getDataList()) {
                IfaReleaseNoteEmployeeA002ResponseDto res = new IfaReleaseNoteEmployeeA002ResponseDto();
                res.setTitle(model.getTitle());
                res.setDisplayedDate(model.getDisplayedDate());
                res.setUpdateTime(model.getUpdateTime());
                res.setDetailedFile(model.getDetailedFile());
                res.setReleaseNoteNo(model.getReleaseNoteNo());
                res.setMenuName(model.getMenuName());
                res.setScreenName(model.getScreenName());
                res.setContent(model.getContent());
                res.setReleaseNoteCoNo(model.getReleaseNoteCoNo());
                resList.add(res);
            }
        }

        DataList<IfaReleaseNoteEmployeeA002ResponseDto> dtoRes = new DataList<IfaReleaseNoteEmployeeA002ResponseDto>();
        dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), StringUtil.EMPTY_STRING);
        return dtoRes;
    }
}
