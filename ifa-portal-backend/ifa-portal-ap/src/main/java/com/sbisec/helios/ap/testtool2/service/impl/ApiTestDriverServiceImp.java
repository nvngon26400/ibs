package com.sbisec.helios.ap.testtool2.service.impl;

import java.lang.reflect.Method;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.testtool2.service.ApiTestDriverService;
import com.sbisec.helios.ap.testtool2.service.dto.ApiTestServiceRequest;

/**
 * APIテスト用のサービスの実装クラス
 *
 */
@Component(value = "apiTestDriverService")
public class ApiTestDriverServiceImp implements ApiTestDriverService {

	@Override
	public Object doApiTest(ApiTestServiceRequest request) throws Exception {

		// クラス名の取得
		String className = request.getTestClassName();
		// クラス名のテスト対象メソッド名の取得
		String methodName = request.getTestMethodName();
		// クラス名のテスト対象メソッド に指定するパラメタDTO名の取得
		String paramDtoName = request.getParamDtoType();
		// パラメタDTOに設定する値の取得
		Map<String, Object> paramValue = request.getParamValue();

		// クラスのクラス型の取得
		Class<?> apiClass = Class.forName(className);
		// クラスのインスタンス生成
		Object apiInstance = IfaCommonUtil.getWebApplicationContext().getBean(apiClass);

		if (ObjectUtils.isEmpty(paramDtoName)) {
			Method testMethodObj = apiInstance.getClass().getMethod(methodName);
			return testMethodObj.invoke(apiInstance);
		}

		// クラスのパラメタ（DTOクラス）型の取得
		ObjectMapper mapper = new ObjectMapper();
		Class<?> apiParamDtoClass = Class.forName(paramDtoName);
		Object apiParamDtoInstance = null;
		if ("java.lang.String".equals(paramDtoName)) {
		   apiParamDtoInstance = paramValue.get("String");
		} else {
		   apiParamDtoInstance = mapper.convertValue(paramValue, apiParamDtoClass);
		}

		// クラスのテスト対象メソッドクラスの取得
		Method testMethodObj = apiInstance.getClass().getMethod(methodName, apiParamDtoClass);

		// クラスのテスト対象メソッドの呼び出し
		Object response = testMethodObj.invoke(apiInstance, apiParamDtoInstance);

		return response;
	}

}
