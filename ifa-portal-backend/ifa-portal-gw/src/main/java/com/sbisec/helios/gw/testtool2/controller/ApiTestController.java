package com.sbisec.helios.gw.testtool2.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.testtool2.service.dto.ApiTestServiceRequest;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

/**
 * APIラッパー用のコントローラ
 *
 */
@RestController
@ScreenId(groupId = "COMMON", id = "Service", ignoreCheck = true)
public class ApiTestController extends BaseController {

	/**
	 * サービス呼び出し用エンドポイント
	 * 
	 * @param posRequest
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/api/driver")
	public ResponseEntity<Object> apiDriverTest(@RequestBody ApiTestServiceRequest posRequest, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        IfaGwCommonUtil.setCustomerCommonToRequestScope();

        // Apiテスト用サービスの呼び出し
        Object respons = ApiRequestUtil.invoke("apiTestDriverService", "doApiTest", new TypeReference<Object>() {
        }, posRequest);

        return ResponseEntity.ok(respons);

	}
}
