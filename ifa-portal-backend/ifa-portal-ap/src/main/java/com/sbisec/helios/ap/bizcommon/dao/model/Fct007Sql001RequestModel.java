package com.sbisec.helios.ap.bizcommon.dao.model;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;

/**
 * 証券営業日付

 * @author 鄒
 *
 */
@Data
public class Fct007Sql001RequestModel extends ModelBase {

    private String date;

    private int numberDay;

}
