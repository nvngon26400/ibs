package com.sbisec.helios.ap.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.util.CommonExecuter;

@Controller
public class ApCommonController {

	private static final Logger logger = LoggerFactory.getLogger(ApCommonController.class);
	private JsonConverter jc = JsonConverter.getInstance();
	 @Autowired
	private ApplicationContext context;

	@RequestMapping(value = "/remoteService", method = { RequestMethod.POST, RequestMethod.HEAD })
	@ResponseBody
	public String handleRemoteServiceRequest(@RequestBody String bodyString) throws Exception {

		if (logger.isDebugEnabled()) logger.debug("RemoteServiceController start.");
		
		String result = CommonExecuter.invoke(context, bodyString);

//		String inputJson = bodyString;
//		inputJson = inputJson.substring(inputJson.indexOf("=") + 1, inputJson.length());
//
//		RemoteServiceRequest remoteServiceRequest = jc.toObject(inputJson, RemoteServiceRequest.class);
//		// MDC setting is removed at AccessLogFilter
//		MDC.put(MdcLogParameter.MDC_SYS_ID.getId(), remoteServiceRequest.getSystemId());
//		MDC.put(MdcLogParameter.MDC_INH_ID.getId(), remoteServiceRequest.getFunctionId() + "|" + remoteServiceRequest.getThreadId());
//		MDC.put(MdcLogParameter.MDC_FSID.getId(),   getServiceAndMethodName(remoteServiceRequest));
//
//		Service service = (Service) context.getBean(remoteServiceRequest.getServiceName());
//		// Service service = (Service) getApplicationContext().getBean(remoteServiceRequest.getServiceName());
//
//		Method method = service.getClass().getMethod(remoteServiceRequest.getMethodName(), remoteServiceRequest.getParamTypes());
//		Object result = method.invoke(service, remoteServiceRequest.getParams());

//		if (remoteServiceRequest.isCompress()) {
//
//			result = new ByteArray(Serializer.toZipByte(result));
//
//			if (logger.isDebugEnabled()) logger.debug("After zip:[{}]", ((ByteArray) result).getBytes().length);
//		}
//
//		String resultJson = jc.toString(result);
//
//		if (logger.isDebugEnabled()) logger.debug("RemoteServiceController end.");

		return result;
	}

//	private String getServiceAndMethodName(RemoteServiceRequest remoteServiceRequest) {
//
//		String serviceName = remoteServiceRequest.getServiceName();
//
//		if (serviceName != null) {
//
//			int index = serviceName.lastIndexOf(".");
//
//			if (-1 < index) serviceName = serviceName.substring(index + 1, serviceName.length());
//		}
//
//		String methodName = remoteServiceRequest.getMethodName();
//
//		return serviceName + "." + methodName;
//	}
}
