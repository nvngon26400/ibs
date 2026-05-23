package com.sbisec.helios.gw.infoMenu.complianceReport.form;

import java.util.ArrayList;
import java.util.List;

import com.sbisec.helios.gw.common.util.DropDownList;

public class ComplianceLetterQueryForm {

    private static final String DEFAULT_SORT_COLUMN = "name_kanji";

    private String sortColumn = DEFAULT_SORT_COLUMN;    // This name must be the same name of the table column.

    //表示年月
    private List<DropDownList> slctYMList = new ArrayList<DropDownList>();
    private String slctYM;
    private String adminSlctYM;
    private String confirmFlg;
    private String directory;
    private String fileName;

    public String getConfirmFlg() {
        return confirmFlg;
    }

    public void setConfirmFlg(String confirmFlg) {
        this.confirmFlg = confirmFlg;
    }

    public String getSlctYM() {
        return slctYM;
    }

    public void setSlctYM(String slctYM) {
        this.slctYM = slctYM;
    }

    public String getAdminSlctYM() {
        return adminSlctYM;
    }

    public void setAdminSlctYM(String adminSlctYM) {
        this.adminSlctYM = adminSlctYM;
    }

    private String selected;

    public List<DropDownList> getSlctYMList() {
        return slctYMList;
    }

    public void setSlctYMList(List<DropDownList> slctYMList) {
        this.slctYMList = slctYMList;
    }

    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
