package com.sbisec.helios.gw.common.util;

import java.util.List;

import com.sbisec.helios.ap.common.util.ServletUtil;
import com.sbisec.helios.gw.common.constants.AppConstants;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component("auth")
public class AuthorityUtil {

    /**
     * Verify that the user has certain permissions.
     * 
     * @param permission permission
     * @return
     */
    public boolean hasPermi(String permission) {
        if (StringUtils.isEmpty(permission)) {
            return false;
        }

        List<String> auths = ServletUtil.instance().getLoginUserAuths();
        if (auths == null || auths.isEmpty()) {
            return false;
        }
        return hasPermissions(auths, permission);
    }

    /**
     * Verify whether the user does not have a certain permission, contrary to the logic of hasPermi.
     * 
     * @param permission permission
     * @return
     */
    public boolean lacksPermi(String permission) {
        return hasPermi(permission) != true;
    }

    private boolean hasPermissions(List<String> auths, String permission) {
        return auths.contains(AppConstants.ALL_PERMISSION) || auths.contains(permission.trim());
    }

}
