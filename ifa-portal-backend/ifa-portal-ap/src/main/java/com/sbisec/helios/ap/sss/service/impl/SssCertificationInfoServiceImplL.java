package com.sbisec.helios.ap.sss.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.sss.model.SssCertificationInfoModel;
import com.sbisec.helios.ap.sss.protocol.ConnectSssResp;
import com.sbisec.helios.ap.sss.service.SssCertificationInfoService;
import com.sbisec.helios.ap.sss.service.SssConnectionService;
import com.sbisec.helios.ap.sss.util.SssApiUtil;

@Component(value = "sssCertificationInfoService")
public class SssCertificationInfoServiceImplL implements SssCertificationInfoService {

	/** Sss認証情報取得用のServiceクラス */
	@Autowired
	private SssConnectionService sssConnectionService;

	@Override
	public DataList<SssCertificationInfoModel> getSssCertificationInfo(String userId, String apiKey, String sssType)
			throws Exception {

		// 取得したレスポンスを画面返却用のデータリストに格納する。
		SssCertificationInfoModel model = new SssCertificationInfoModel();
		List<SssCertificationInfoModel> sssCertificationInfoModelList = new ArrayList<SssCertificationInfoModel>();
		DataList<SssCertificationInfoModel> dataList = new DataList<SssCertificationInfoModel>();

		ConnectSssResp resp = null;

		// sssAPIに接続して、認証情報を取得する。
		try {
			resp = sssConnectionService.getSssCertificationInfo(userId, apiKey, sssType);
		}catch(Exception e) {
			SssApiUtil.getValidationException(dataList, e);
			return dataList;
		}

		// APIから取得した値をモデルに格納する。
		setSssCertificationInfoModel(model, resp);

		// モデルをdataListに格納して返却する。
		sssCertificationInfoModelList.add(model);
		dataList.setDataList(sssCertificationInfoModelList);

		return dataList;
	}

	/**
	 * SSS接続情報をModelに格納する
	 * @param resp SSS接続情報レスポンス
	 * @return model SssCertificationInfoModelモデル
	 */
	private SssCertificationInfoModel setSssCertificationInfoModel(SssCertificationInfoModel model, ConnectSssResp resp) {
		// ModelにSSS接続情報を格納する
		model.setCode(resp.getCode());
		model.setErrorCode(resp.getErrorCode());
		model.setErrorMessage(resp.getErrorMessage());
		// SSS接続情報を格納したModelを返却する
		return model;
	}
}
