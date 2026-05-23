package com.sbisec.helios.common.unittest.controller;

import javax.servlet.http.HttpServletRequest;

import com.sbibits.earth.servlet.annotation.ResponseJson;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.common.unittest.form.ValidationCheckForm;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * GlobalExceptionHandler用テストコントローラ
 *
 * @author SCSK
 *
 */
@ScreenId(groupId = "TEST", id = "GlobalException", ignoreCheck = true)
@RestController
@RequestMapping(value = "/v1/test/global-exception-handler", method = { RequestMethod.POST })
public class GlobalExceptionHandlerTestController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * handleMethodArgumentNotValidExceptionテスト用<br/>
     * 正しい入力値の場合、"returnCode":"NotValidationError","errorLevel":"0"を返却する<br/>
     *
     * @param validationCheckForm 入力値
     * @param request HttpRequest
     * @return 戻り値
     * @throws Exception 例外
     */
    @PostMapping("/handle-method-argument-not-valid-exception")
    @ResponseJson
    public String testHandleMethodArgumentNotValidException(
            @RequestBody @Validated ValidationCheckForm validationCheckForm, HttpServletRequest request)
            throws Exception {
        
        // バリデーションエラーがない場合、SUCCESSを返却する。
        return jc.toString(IfaCommonUtil.createDataList(null, ErrorLevel.SUCCESS, "NotValidationError", ""));
    }
    
}
