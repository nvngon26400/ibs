package com.sbisec.helios.ap.common.util;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.JarURLConnection;
import java.util.Objects;
import java.util.Map;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import com.sun.security.auth.module.*;
import java.io.InputStream;




public class Config {

	private static Config instance = null;

	public static synchronized Config init(String path)
		throws Exception
	{
		if(Objects.isNull(instance)){

			ObjectMapper mpr = new ObjectMapper(new YAMLFactory()).setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			
	        try (InputStream inputStream = classLoader.getResourceAsStream(path)) {
	            if (inputStream == null) {
	                throw new IllegalArgumentException("ファイルが見つかりません: " + path);
	            }
	            // JSONファイルからJavaオブジェクトにデシリアライズする
	            instance = mpr.readValue(inputStream, Config.class);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			//instance = mpr.readValue(new File(path), Config.class);

			instance.registClass();
			instance.appInit();

			HttpClientManager.init(
				instance.clients.get("initial").intValue()
				, instance.clients.get("max").intValue()
				, instance.clients.get("connect_timeout").intValue()
			);

			Util.init();
		}

		return instance;
	}

	public static Config get(String path) throws Exception {
		ObjectMapper mpr = new ObjectMapper(new YAMLFactory()).setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		
        try (InputStream inputStream = classLoader.getResourceAsStream(path)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("ファイルが見つかりません: " + path);
            }
            // JSONファイルからJavaオブジェクトにデシリアライズする
            instance = mpr.readValue(inputStream, Config.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
		//instance = mpr.readValue(new File(path), Config.class);
		instance.registClass();
		instance.appInit();

		HttpClientManager.init(
			instance.clients.get("initial").intValue()
			, instance.clients.get("max").intValue()
			, instance.clients.get("connect_timeout").intValue()
		);
		
		
		return instance;
	}

	private static final String PACKAGE_ROOT = "jp.co.sbisec.pcenter.dto.heracross";

	Config(){
	}

	private Map<Class<?>, String> apis = new HashMap<Class<?>, String>();

	private void registClass()
		throws Exception
	{
		ClassLoader cl = Thread.currentThread().getContextClassLoader();

		Enumeration<URL> e = cl.getResources(Config.PACKAGE_ROOT.replace('.', '/'));

		for(; e.hasMoreElements();){

			URL url = e.nextElement();

			String protocol = url.getProtocol();
			if("file".equals(protocol)){
				findForFile(url);
			}
			else if("jar".equals(protocol)){
				findForJar(url);
			}
		}
	}

	private void findForFile(URL url)
		throws Exception
	{
		File root = new File(url.getFile());

		for(File app : root.listFiles()){

			if(app.isFile()){

				continue;
			}

			for(String file : app.list()){
				if(!file.endsWith("In.class")){
					continue;
				}

				String name = String.format("%s.%s.%s"
					, Config.PACKAGE_ROOT
					, app.getName()
					, file.substring(0,file.length() - 6));
				apis.put(Class.forName(name), app.getName());
			}
		}
	}

	private void findForJar(URL url)
		throws Exception
	{
		JarURLConnection jarUrlConnection = (JarURLConnection)url.openConnection();

		try(JarFile jarFile = jarUrlConnection.getJarFile()){

			Enumeration<JarEntry> e = jarFile.entries();

			String appName = "";

			while(e.hasMoreElements()){
				JarEntry file = e.nextElement();

				if(!file.getName().startsWith(PACKAGE_ROOT.replace('.','/'))){
					continue;
				}

				if(file.isDirectory()){
					appName = file.getName();
					for(String s : file.getName().split("/")){
						if(s.isEmpty()){
							break;
						}

						appName = s;
					}
					continue;
				}
				if(!file.getName().endsWith("In.class")){
					continue;
				}

				String name = file.getName();
				name = name.replace('/','.').substring(0, name.length() - 6);
				apis.put(Class.forName(name), appName);
			}
		}
	}

	private void appInit()
	{
		for(Map.Entry<String,AppConfig> entry : applications.entrySet()){

			String name = String.format("%s.%s.Initializer", PACKAGE_ROOT, entry.getKey());
			Class<?> cls;
			try{
				cls = Class.forName(name);

				Method method = cls.getMethod("init", AppConfig.class);
				method.invoke(null, entry.getValue());
			}
			catch(Exception e){
				continue;
			}

		}
	}

	@lombok.Getter
	private Crypt crypt;

	@lombok.Getter
	private Map<String, Integer> clients;

	@lombok.Getter
	private Map<String, AppConfig> applications;
	
	private static final String SPECIFIC_APP = "heracross";

	public <T> AppConfig getAppConfig(Class<T> type){

		String appname = apis.get(type);
		if(Objects.isNull(appname)){
			return applications.get(SPECIFIC_APP);
		}

		return applications.get(appname);
	}
	
	public <T> AppConfig getAppConfig(String type){

		String appname = type;
		if(Objects.isNull(appname)){
			return applications.get(SPECIFIC_APP);
		}

		return applications.get(appname);
	}
}
