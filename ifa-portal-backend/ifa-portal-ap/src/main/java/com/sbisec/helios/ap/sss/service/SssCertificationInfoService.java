
package com.sbisec.helios.ap.sss.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.service.Service;
import com.sbisec.helios.ap.sss.model.SssCertificationInfoModel;

public interface SssCertificationInfoService  extends Service {

	/**
	 * SSS認証情報を取得する。
	 * 
	 * @param userId ユーザーID
	 * @param apiKey APIキー
	 * @param sssType SSSタイプ
	 * @return モデル:SssCertificationInfoModel
	 */
	
	public DataList<SssCertificationInfoModel> getSssCertificationInfo(String userId, String apiKey, String sssType)
				throws Exception;
}
