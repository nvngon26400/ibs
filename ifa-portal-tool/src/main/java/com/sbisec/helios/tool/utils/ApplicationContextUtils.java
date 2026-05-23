package com.sbisec.helios.tool.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * アプリケーションコンテキストユーティリティクラス。
 * 
 * @author SCSK宮坂
 */
public class ApplicationContextUtils {
	/**
	 * アプリケーションコンテキストプロバイダクラス。
	 */
	@Component
	protected static class ApplicationContextProvider implements ApplicationContextAware {
		/** アプリケーションコンテキスト */
		private static ApplicationContext applicationContext;

		/**
		 * コンストラクタ。
		 */
		public ApplicationContextProvider() {
		}

		/**
		 * アプリケーションコンテキストを取得する。
		 * 
		 * @return
		 */
		public static ApplicationContext getContext() {
			return applicationContext;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
			ApplicationContextProvider.applicationContext = applicationContext;
		}
	}

	/**
	 * アプリケーションコンテキストを取得する。
	 * 
	 * @return アプリケーションコンテキスト。
	 */
	public static ApplicationContext getContext() {
		return ApplicationContextProvider.getContext();
	}

	/**
	 * Bean登録されたクラスインスタンスを取得する。
	 * 
	 * @param <T>         Bean登録されたクラス。
	 * @param classObject 取得対象クラスオブジェクト。
	 * @return Beanインスタンス。
	 * @throws BeansException 取得に失敗した場合。
	 */
	public static <T> T getBean(Class<T> classObject) throws BeansException {
		return getContext().getBean(classObject);
	}

	/**
	 * Bean登録されたクラスインスタンスを取得する。
	 * 
	 * @param <T>         Bean登録されたクラス。
	 * @param beanName    Bean名。
	 * @param classObject 取得対象クラスオブジェクト。
	 * @return Beanインスタンス。
	 * @throws BeansException 取得に失敗した場合。
	 */
	public static <T> T getBean(String beanName, Class<T> classObject) throws BeansException {
		return getContext().getBean(beanName, classObject);
	}

	/**
	 * コンストラクタ。
	 */
	private ApplicationContextUtils() {
	}
}
