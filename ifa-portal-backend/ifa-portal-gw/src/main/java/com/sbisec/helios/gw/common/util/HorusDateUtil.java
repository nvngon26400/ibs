package com.sbisec.helios.gw.common.util;

import org.apache.commons.lang3.time.DateUtils;

import com.sbibits.earth.util.DateUtil;

public class HorusDateUtil extends DateUtils {

	public static boolean isParsable(String src, String format) {
		try {

			if (DateUtil.isParsable(src, format)) {
				DateUtils.parseDate(src, format);

				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			return false;
		}
	}

}
