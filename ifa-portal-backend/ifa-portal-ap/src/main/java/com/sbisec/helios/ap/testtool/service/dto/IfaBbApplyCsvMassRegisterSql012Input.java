package com.sbisec.helios.ap.testtool.service.dto;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoUploadBBAcceptModel;

import lombok.Data;

@Data
public class IfaBbApplyCsvMassRegisterSql012Input {
    
    List<IpopoUploadBBAcceptModel> insertList;

}
