package com.sbisec.helios.ap;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.sbisec.helios.ap.common.model.XmlServiceConfigModel;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import org.junit.jupiter.api.Test;

public class XmlServiceConfigModelTest {


    // コンストラクタのテスト(XMLファイルの読み込みとデシリアライズ。
    @Test
    void generateModelTest() throws Exception {
		XmlServiceConfigModel config = new XmlServiceConfigModel();
		assertNotNull(config.getServices());
	}

    // サービスのテスト。
    @Test
    void getServiceTest() throws Exception {
//		ReflectionConfigService service = new ReflectionConfigServiceImpl();
//		XmlServiceModel model = service.getServiceConfig();
//		
//		Object aaaClasses = model.getParamTypeArrayList();
//		Object ccccObject = PropertyUtils.describe(model);
//		List<XmlServiceParam> ddddObject = (List<XmlServiceParam>)PropertyUtils.getNestedProperty(model, "params");
//		Object bbbb = PropertyUtils.getIndexedProperty(model, "params",ddddObject.size() - 1);
//		assertNotNull(aaaClasses);
//		assertEquals("getCustomerBasicData", model.getMethodName());
	}

/*	
	// モデルの確認(XMLファイルの出力。)
	@Test
	public void serialize() throws Exception {
		
		DataList<IpopoOrderInfoModel>obj = new DataList<IpopoOrderInfoModel>();
		
		Object obj2 = Class.forName("java.util.List");
		
		TypeReference<DataList<IpopoOrderInfoModel>> typeReference = new TypeReference<DataList<IpopoOrderInfoModel>>() {};  

		Class sampleBaseDao =test(Class.forName("IpopoOrderInfoModel").getClass());

		String dummyString = "{\"totalSize\":0,\"maxRownum\":0,\"overMaxRownum\":false,\"dataList\":[],\"message\":\"\",\"errorLevel\":0,\"requestedTime\":\"--\\u002F--\\u002F-- --:--\",\"title\":null,\"returnCode\":\"0\"}";
		
		JsonConverter jc = JsonConverter.getInstance();
		
		DataList<IpopoOrderInfoModel>objResul =  jc.toObject(dummyString, typeReference);
		

		XmlServiceParam param = new XmlServiceParam();
		
		
		
		
		BaseDao<IpopoOrderInfoModel> xBaseDao = new BaseDao<IpopoOrderInfoModel>();
		Object aaaObject = xBaseDao.findById("");
		
		
		
//		RemoteServiceExecutor executor =
//        (RemoteServiceExecutor) SerivceContext.getInstance().getApplicationContext().getBean("remoteServiceExecutor", RemoteServiceExecutor.class);
		param.setIndex("paramindex");
		param.setType(Class.forName("java.lang.String"));
		
		List<XmlServiceParam> params = new ArrayList<XmlServiceParam>();
		params.add(param);
		params.add(param);
		params.add(param);

		XmlServiceModel service = new XmlServiceModel();
		service.setUrl("/Sample/getSample");
		service.setServiceName("SampleClass");
		service.setMethodName("SampleMethos");
		service.setParams(params);
		XmlServiceTopElement top = new XmlServiceTopElement();
		List<XmlServiceModel> servicesList = new ArrayList<XmlServiceModel>();
		servicesList.add(service);
		servicesList.add(service);
		top.setServices(servicesList);
		
		XmlMapper xmlMapper = new XmlMapper();
		
		String xml = xmlMapper.writeValueAsString(top);
		assertNotNull(xml);
	}
*/

	public class BaseDao<T> {
		public T findById(String id) {
			try {
				// 実行時の型が取れる。ここではHogeDaoなど
				Class<?> clazz = this.getClass();
				// ここではBaseDao<Hoge>がとれる
				Type type = clazz.getGenericSuperclass();
				ParameterizedType pt = (ParameterizedType)type;
				// BaseDaoの型変数に対するバインドされた型がとれる
				Type[] actualTypeArguments = pt.getActualTypeArguments();
				Class<?> entityClass = (Class<?>)actualTypeArguments[0];
				// リフレクションでインスタンスを生成
				return (T) entityClass.newInstance();
			} catch (ReflectiveOperationException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	public <T>T   test(Class<? extends T> type){ 
			BaseDao<T> sample = new BaseDao<T>();
			return (T) sample;
		}

    @Test
    void ApiWrapperTest(){ 
		ApiWrapper apiWrapper = new ApiWrapper();
	}
	
}
