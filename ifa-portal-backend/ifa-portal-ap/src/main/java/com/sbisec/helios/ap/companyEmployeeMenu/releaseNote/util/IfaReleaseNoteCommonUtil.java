package com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.util.DateUtil;
import com.sbisec.helios.ap.common.dao.SystemDateDao;

/**
 * SUB0512-02 リリースノート共通
 *
 * @author SBI大連 夏
 * @date 2025/11/07
 */

@Component
public class IfaReleaseNoteCommonUtil {

    @Autowired
    private SystemDateDao g_sysdate;

    /**
     * システム日時の取得
     * 
     * @return String (yyyyMMddHHmmss)
     * @throws Exception
     */
    public String systemDate() throws Exception {
        return DateUtil.format(g_sysdate.getSystemDate(), DateUtil.NOT_SEPARATED_YYYYMMDD_HHMMSS);
    }

}
