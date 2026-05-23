package jp.co.sbisec.pcenter.dto.heracross;

import jp.co.sbisec.pcenter.AppConfig;

public class Initializer {
	public static void init(AppConfig cfg) {

		HashBuilder.init(cfg.getHashKey());
	}
}
