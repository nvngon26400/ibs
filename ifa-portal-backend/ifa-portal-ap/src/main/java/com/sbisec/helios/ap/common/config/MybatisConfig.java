package com.sbisec.helios.ap.common.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.sbisec.helios.ap.common.annotation.dao.CordysMapper;
import com.sbisec.helios.ap.common.annotation.dao.EtintraMapper;
import com.sbisec.helios.ap.common.annotation.dao.MariadbMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * Mybatis設定クラス。
 * 
 * @author SCSK宮坂
 */
@Configuration
@EnableTransactionManagement
@Slf4j
public class MybatisConfig {
	/** Mapperスキャン対象基底パッケージ */
	private static final String MAPPER_BASE_PACKAGE = "com.sbisec.helios.ap";

	/** プロパティ基底パッケージ接頭辞 */
	private static final String PROPS_BASE_PACKAGE_PREFIX = "spring.datasource.";
	/** プロパティ基底パッケージ接尾辞（DataSourceProperties） */
	private static final String PROPS_BASE_PACKAGE_SUFFIX_DATA_SOURCE = "";
	/** プロパティ基底パッケージ接尾辞（MybatisProperties） */
	private static final String PROPS_BASE_PACKAGE_SUFFIX_MYBATIS = "." + MybatisProperties.MYBATIS_PREFIX;
	/** プロパティ基底パッケージ接尾辞（TransactionProperties） */
	private static final String PROPS_BASE_PACKAGE_SUFFIX_TRANSACTION = ".transaction";

	/** Bean基底名（DataSourceProperties） */
	private static final String BEAN_BASE_NAME_PROPS_DATA_SOURCE = "DataSourceProperties";
	/** Bean基底名（MybatisProperties） */
	private static final String BEAN_BASE_NAME_PROPS_MYBATIS = "MybatisProperties";
	/** Bean基底名（TransactionProperties） */
	private static final String BEAN_BASE_NAME_PROPS_TRANSACTION = "TransactionProperties";
	/** Bean基底名（DataSource） */
	private static final String BEAN_BASE_NAME_DATA_SOURCE = "DataSource";
	/** Bean基底名（SqlSessionFactory） */
	private static final String BEAN_BASE_NAME_SESSION_FACTORY = "SqlSessionFactory";
	/** Bean基底名（SqlSessionTemplate） */
	private static final String BEAN_BASE_NAME_SESSION_TEMPLATE = "SqlSessionTemplate";
	/** Bean基底名（TransactionManager） */
	private static final String BEAN_BASE_NAME_TRANSACTION_MANAGER = "TransactionManager";

	/**
	 * cordysデータソース用設定クラス。 プライマリBeanはすべて当データソースとする。
	 * また、Mybatis標準のMapperアノテーションは、互換性維持のため当データソースに属することとする。
	 */
	@Configuration
	@MapperScan(basePackages = MAPPER_BASE_PACKAGE, annotationClass = Mapper.class, sqlSessionFactoryRef = Cordys.SESSION_FACTORY_BEAN_NAME)
	@MapperScan(basePackages = MAPPER_BASE_PACKAGE, annotationClass = CordysMapper.class, sqlSessionFactoryRef = Cordys.SESSION_FACTORY_BEAN_NAME)
	public static class Cordys {
		/** データソース名 */
		public static final String DATA_SOURCE_NAME = "cordys";
		/** セッションファクトリーBean名 */
		public static final String SESSION_FACTORY_BEAN_NAME = DATA_SOURCE_NAME + BEAN_BASE_NAME_SESSION_FACTORY;
		/** トランザクションマネージャーBean名 */
		public static final String TRANSACTION_MANAGER_BEAN_NAME = DATA_SOURCE_NAME + BEAN_BASE_NAME_TRANSACTION_MANAGER;

		/**
		 * Beanを登録する（DataSourceProperties）。
		 * 
		 * @return DataSourcePropertiesのBeanインスタンス。
		 */
		@Bean(name = DATA_SOURCE_NAME + BEAN_BASE_NAME_PROPS_DATA_SOURCE)
		@ConfigurationProperties(prefix = PROPS_BASE_PACKAGE_PREFIX + DATA_SOURCE_NAME + PROPS_BASE_PACKAGE_SUFFIX_DATA_SOURCE)
		@Primary
		public DataSourceProperties dataSourceProperties() {
			return new DataSourceProperties();
		}

		/**
		 * Beanを登録する（MybatisProperties）。
		 * 
		 * @return MybatisPropertiesBeanのインスタンス。
		 */
		@Bean(name = DATA_SOURCE_NAME + BEAN_BASE_NAME_PROPS_MYBATIS)
		@ConfigurationProperties(prefix = PROPS_BASE_PACKAGE_PREFIX + DATA_SOURCE_NAME + PROPS_BASE_PACKAGE_SUFFIX_MYBATIS)
		@Primary
		public MybatisProperties mybatisProperties() {
			return new MybatisProperties();
		}

		/**
		 * Beanを登録する（TransactionProperties）。
		 * 
		 * @return TransactionPropertiesのBeanインスタンス。
		 */
		@Bean(name = DATA_SOURCE_NAME + BEAN_BASE_NAME_PROPS_TRANSACTION)
		@ConfigurationProperties(prefix = PROPS_BASE_PACKAGE_PREFIX + DATA_SOURCE_NAME + PROPS_BASE_PACKAGE_SUFFIX_TRANSACTION)
		@Primary
		public TransactionProperties transactionProperties() {
			return new TransactionProperties();
		}

		/**
		 * Beanを登録する（DataSource）。 JNDIベースのデータソースについては、
		 * {@link org.springframework.boot.autoconfigure.jdbc.JndiDataSourceAutoConfiguration
		 * JndiDataSourceAutoConfiguration}に倣って実装。
		 * 
		 * @param dataSourceProperties DataSourcePropertiesのBeanインスタンス。
		 * @return DataSourceのBeanインスタンス。
		 * @throws NamingException
		 * @throws IllegalArgumentException
		 */
		@Bean(name = DATA_SOURCE_NAME + BEAN_BASE_NAME_DATA_SOURCE)
		@Primary
		public DataSource dataSource(@Qualifier(DATA_SOURCE_NAME + BEAN_BASE_NAME_PROPS_DATA_SOURCE) DataSourceProperties dataSourceProperties) {
			if (log.isDebugEnabled()) {
				debugProperty(DATA_SOURCE_NAME, dataSourceProperties);
			}

			if (StringUtils.isEmpty(dataSourceProperties.getJndiName())) {
				// JNDI名が指定されていない場合はユーザ名・パスワード・URLベースでデータソースを生成
				return dataSourceProperties.initializeDataSourceBuilder().build();
			} else {
				// JNDI名が指定されている場合はJNDIベースでデータソースを生成
				JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();

				return dataSourceLookup.getDataSource(dataSourceProperties.getJndiName());
			}
		}

		/**
		 * Beanを登録する（SqlSessionFactory）。
		 * {@link org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration
		 * MybatisAutoConfiguration}に倣って実装。 ただし、設定対象項目については必要最低限にとどめている。
		 * 
		 * @param dataSourceProperties DataSourcePropertiesのBeanインスタンス。
		 * @param mybatisProperties    MybatisPropertiesのBeanインスタンス。
		 * @return SqlSessionFactoryのBeanインスタンス。
		 */
		@Bean(name = DATA_SOURCE_NAME + BEAN_BASE_NAME_SESSION_FACTORY)
		@Primary
		public SqlSessionFactory sqlSessionFactory(@Qualifier(DATA_SOURCE_NAME + BEAN_BASE_NAME_DATA_SOURCE) DataSource dataSource,
				@Qualifier(DATA_SOURCE_NAME + BEAN_BASE_NAME_PROPS_MYBATIS) MybatisProperties mybatisProperties) throws Exception {
			if (log.isDebugEnabled()) {
				debugProperty(DATA_SOURCE_NAME, mybatisProperties);
			}

			// SqlSessionFactoryBeanに基本設定を適用
			SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
			sqlSessionFactoryBean.setDataSource(dataSource);
			sqlSessionFactoryBean.setVfs(SpringBootVFS.class);
			if (!StringUtils.isEmpty(mybatisProperties.getTypeAliasesPackage())) {
				sqlSessionFactoryBean.setTypeAliasesPackage(mybatisProperties.getTypeAliasesPackage());
			}
			if (mybatisProperties.getTypeAliasesSuperType() != null) {
				sqlSessionFactoryBean.setTypeAliasesSuperType(mybatisProperties.getTypeAliasesSuperType());
			}
			if (!StringUtils.isEmpty(mybatisProperties.getTypeHandlersPackage())) {
				sqlSessionFactoryBean.setTypeHandlersPackage(mybatisProperties.getTypeHandlersPackage());
			}

			// SqlSessionFactoryBeanにMybatisカスタマイズ設定を適用
			if (mybatisProperties.getConfiguration() != null) {
				sqlSessionFactoryBean.setConfiguration(mybatisProperties.getConfiguration());
			}

			return sqlSessionFactoryBean.getObject();
		}

		/**
		 * Beanを登録する（DataSourceProperties）。
		 * {@link org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration
		 * MybatisAutoConfiguration}に倣って実装。
		 * Mapperスキャンでは使わないが、DAOで個別にDIしたりMybatis内部で使用している可能性もあるので念のため用意しておく。
		 * 
		 * @param sqlSessionFactory SqlSessionTemplateのBeanインスタンス。
		 * @param mybatisProperties MybatisPropertiesのBeanインスタンス。
		 * @return SqlSessionTemplateのBeanインスタンス。
		 */
		@Bean(name = DATA_SOURCE_NAME + BEAN_BASE_NAME_SESSION_TEMPLATE)
		@Primary
		public SqlSessionTemplate sqlSessionTemplate(@Qualifier(DATA_SOURCE_NAME + BEAN_BASE_NAME_SESSION_FACTORY) SqlSessionFactory sqlSessionFactory,
				@Qualifier(DATA_SOURCE_NAME + BEAN_BASE_NAME_PROPS_MYBATIS) MybatisProperties mybatisProperties) {
			ExecutorType executorType = mybatisProperties.getExecutorType();

			if (executorType != null) {
				return new SqlSessionTemplate(sqlSessionFactory, executorType);
			} else {
				return new SqlSessionTemplate(sqlSessionFactory);
			}
		}

		/**
		 * Beanを登録する（PlatformTransactionManager）。
		 * {@link org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration
		 * DataSourceTransactionManagerAutoConfiguration}に倣って実装。
		 * 
		 * @param dataSource            DataSourceのBeanインスタンス。
		 * @param transactionProperties TransactionPropertiesのBeanインスタンス。
		 * @return PlatformTransactionManagerのBeanインスタンス。
		 */
		@Bean(name = DATA_SOURCE_NAME + BEAN_BASE_NAME_TRANSACTION_MANAGER)
		@Primary
		public PlatformTransactionManager transactionManager(@Qualifier(DATA_SOURCE_NAME + BEAN_BASE_NAME_DATA_SOURCE) DataSource dataSource,
				@Qualifier(DATA_SOURCE_NAME + BEAN_BASE_NAME_PROPS_TRANSACTION) TransactionProperties transactionProperties) {
			if (log.isDebugEnabled()) {
				debugProperty(DATA_SOURCE_NAME, transactionProperties);
			}

			// TransactionManagerにプロパティを適用
			DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
			transactionProperties.customize(dataSourceTransactionManager);

			return dataSourceTransactionManager;
		}
	}

	/**
	 * etintraデータソース用設定クラス。
	 */
	@Configuration
	@MapperScan(basePackages = MAPPER_BASE_PACKAGE, annotationClass = EtintraMapper.class, sqlSessionFactoryRef = Etintra.SESSION_FACTORY_BEAN_NAME)
	public static class Etintra {
		/** データソース名 */
		public static final String DATA_SOURCE_NAME = "etintra";
		/** セッションファクトリーBean名 */
		public static final String SESSION_FACTORY_BEAN_NAME = DATA_SOURCE_NAME + BEAN_BASE_NAME_SESSION_FACTORY;
		/** トランザクションマネージャーBean名 */
		public static final String TRANSACTION_MANAGER_BEAN_NAME = DATA_SOURCE_NAME + BEAN_BASE_NAME_TRANSACTION_MANAGER;

		/**
		 * Beanを登録する（DataSourceProperties）。
		 * 
		 * @return DataSourcePropertiesのBeanインスタンス。
		 */
		@Bean(name = DATA_SOURCE_NAME + BEAN_BASE_NAME_PROPS_DATA_SOURCE)
		@ConfigurationProperties(prefix = PROPS_BASE_PACKAGE_PREFIX + DATA_SOURCE_NAME + PROPS_BASE_PACKAGE_SUFFIX_DATA_SOURCE)
		public DataSourceProperties dataSourceProperties() {
			return new DataSourceProperties();
		}

		/**
		 * Beanを登録する（MybatisProperties）。
		 * 
		 * @return MybatisPropertiesBeanのインスタンス。
		 */
		@Bean(name = DATA_SOURCE_NAME + BEAN_BASE_NAME_PROPS_MYBATIS)
		@ConfigurationProperties(prefix = PROPS_BASE_PACKAGE_PREFIX + DATA_SOURCE_NAME + PROPS_BASE_PACKAGE_SUFFIX_MYBATIS)
		public MybatisProperties mybatisProperties() {
			return new MybatisProperties();
		}

		/**
		 * Beanを登録する（TransactionProperties）。
		 * 
		 * @return TransactionPropertiesのBeanインスタンス。
		 */
		@Bean(name = DATA_SOURCE_NAME + BEAN_BASE_NAME_PROPS_TRANSACTION)
		@ConfigurationProperties(prefix = PROPS_BASE_PACKAGE_PREFIX + DATA_SOURCE_NAME + PROPS_BASE_PACKAGE_SUFFIX_TRANSACTION)
		public TransactionProperties transactionProperties() {
			return new TransactionProperties();
		}

		/**
		 * Beanを登録する（DataSource）。 JNDIベースのデータソースについては、
		 * {@link org.springframework.boot.autoconfigure.jdbc.JndiDataSourceAutoConfiguration
		 * JndiDataSourceAutoConfiguration}に倣って実装。
		 * 
		 * @param dataSourceProperties DataSourcePropertiesのBeanインスタンス。
		 * @return DataSourceのBeanインスタンス。
		 */
		@Bean(name = DATA_SOURCE_NAME + BEAN_BASE_NAME_DATA_SOURCE)
		public DataSource dataSource(@Qualifier(DATA_SOURCE_NAME + BEAN_BASE_NAME_PROPS_DATA_SOURCE) DataSourceProperties dataSourceProperties) {
			if (log.isDebugEnabled()) {
				debugProperty(DATA_SOURCE_NAME, dataSourceProperties);
			}

			if (StringUtils.isEmpty(dataSourceProperties.getJndiName())) {
				// JNDI名が指定されていない場合はユーザ名・パスワード・URLベースでデータソースを生成
				return dataSourceProperties.initializeDataSourceBuilder().build();
			} else {
				// JNDI名が指定されている場合はJNDIベースでデータソースを生成
				JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();

				return dataSourceLookup.getDataSource(dataSourceProperties.getJndiName());
			}
		}

		/**
		 * Beanを登録する（SqlSessionFactory）。
		 * {@link org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration
		 * MybatisAutoConfiguration}に倣って実装。 ただし、設定対象項目については必要最低限にとどめている。
		 * 
		 * @param dataSourceProperties DataSourcePropertiesのBeanインスタンス。
		 * @param mybatisProperties    MybatisPropertiesのBeanインスタンス。
		 * @return SqlSessionFactoryのBeanインスタンス。
		 */
		@Bean(name = DATA_SOURCE_NAME + BEAN_BASE_NAME_SESSION_FACTORY)
		public SqlSessionFactory sqlSessionFactory(@Qualifier(DATA_SOURCE_NAME + BEAN_BASE_NAME_DATA_SOURCE) DataSource dataSource,
				@Qualifier(DATA_SOURCE_NAME + BEAN_BASE_NAME_PROPS_MYBATIS) MybatisProperties mybatisProperties) throws Exception {
			if (log.isDebugEnabled()) {
				debugProperty(DATA_SOURCE_NAME, mybatisProperties);
			}

			// SqlSessionFactoryBeanに基本設定を適用
			SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
			sqlSessionFactoryBean.setDataSource(dataSource);
			sqlSessionFactoryBean.setVfs(SpringBootVFS.class);
			if (!StringUtils.isEmpty(mybatisProperties.getTypeAliasesPackage())) {
				sqlSessionFactoryBean.setTypeAliasesPackage(mybatisProperties.getTypeAliasesPackage());
			}
			if (mybatisProperties.getTypeAliasesSuperType() != null) {
				sqlSessionFactoryBean.setTypeAliasesSuperType(mybatisProperties.getTypeAliasesSuperType());
			}
			if (!StringUtils.isEmpty(mybatisProperties.getTypeHandlersPackage())) {
				sqlSessionFactoryBean.setTypeHandlersPackage(mybatisProperties.getTypeHandlersPackage());
			}

			// SqlSessionFactoryBeanにMybatisカスタマイズ設定を適用
			if (mybatisProperties.getConfiguration() != null) {
				sqlSessionFactoryBean.setConfiguration(mybatisProperties.getConfiguration());
			}

			return sqlSessionFactoryBean.getObject();
		}

		/**
		 * Beanを登録する（DataSourceProperties）。
		 * {@link org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration
		 * MybatisAutoConfiguration}に倣って実装。
		 * Mapperスキャンでは使わないが、DAOで個別にDIしたりMybatis内部で使用している可能性もあるので念のため用意しておく。
		 * 
		 * @param sqlSessionFactory SqlSessionTemplateのBeanインスタンス。
		 * @param mybatisProperties MybatisPropertiesのBeanインスタンス。
		 * @return SqlSessionTemplateのBeanインスタンス。
		 */
		@Bean(name = DATA_SOURCE_NAME + BEAN_BASE_NAME_SESSION_TEMPLATE)
		public SqlSessionTemplate sqlSessionTemplate(@Qualifier(DATA_SOURCE_NAME + BEAN_BASE_NAME_SESSION_FACTORY) SqlSessionFactory sqlSessionFactory,
				@Qualifier(DATA_SOURCE_NAME + BEAN_BASE_NAME_PROPS_MYBATIS) MybatisProperties mybatisProperties) {
			ExecutorType executorType = mybatisProperties.getExecutorType();

			if (executorType != null) {
				return new SqlSessionTemplate(sqlSessionFactory, executorType);
			} else {
				return new SqlSessionTemplate(sqlSessionFactory);
			}
		}

		/**
		 * Beanを登録する（PlatformTransactionManager）。
		 * {@link org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration
		 * DataSourceTransactionManagerAutoConfiguration}に倣って実装。
		 * 
		 * @param dataSource            DataSourceのBeanインスタンス。
		 * @param transactionProperties TransactionPropertiesのBeanインスタンス。
		 * @return PlatformTransactionManagerのBeanインスタンス。
		 */
		@Bean(name = DATA_SOURCE_NAME + BEAN_BASE_NAME_TRANSACTION_MANAGER)
		public PlatformTransactionManager transactionManager(@Qualifier(DATA_SOURCE_NAME + BEAN_BASE_NAME_DATA_SOURCE) DataSource dataSource,
				@Qualifier(DATA_SOURCE_NAME + BEAN_BASE_NAME_PROPS_TRANSACTION) TransactionProperties transactionProperties) {
			if (log.isDebugEnabled()) {
				debugProperty(DATA_SOURCE_NAME, transactionProperties);
			}

			// TransactionManagerにプロパティを適用
			DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
			transactionProperties.customize(dataSourceTransactionManager);

			return dataSourceTransactionManager;
		}
	}

	/**
	 * mariadbデータソース用設定クラス。 参照のみのデータソースのため、トランザクション管理は考慮しない。
	 */
	@Configuration
	@MapperScan(basePackages = MAPPER_BASE_PACKAGE, annotationClass = MariadbMapper.class, sqlSessionFactoryRef = Mariadb.SESSION_FACTORY_BEAN_NAME)
	public static class Mariadb {
		/** データソース名 */
		public static final String DATA_SOURCE_NAME = "mariadb";
		/** セッションファクトリーBean名 */
		public static final String SESSION_FACTORY_BEAN_NAME = DATA_SOURCE_NAME + BEAN_BASE_NAME_SESSION_FACTORY;

		/**
		 * Beanを登録する（DataSourceProperties）。
		 * 
		 * @return DataSourcePropertiesのBeanインスタンス。
		 */
		@Bean(name = DATA_SOURCE_NAME + BEAN_BASE_NAME_PROPS_DATA_SOURCE)
		@ConfigurationProperties(prefix = PROPS_BASE_PACKAGE_PREFIX + DATA_SOURCE_NAME + PROPS_BASE_PACKAGE_SUFFIX_DATA_SOURCE)
		public DataSourceProperties dataSourceProperties() {
			return new DataSourceProperties();
		}

		/**
		 * Beanを登録する（MybatisProperties）。
		 * 
		 * @return MybatisPropertiesBeanのインスタンス。
		 */
		@Bean(name = DATA_SOURCE_NAME + BEAN_BASE_NAME_PROPS_MYBATIS)
		@ConfigurationProperties(prefix = PROPS_BASE_PACKAGE_PREFIX + DATA_SOURCE_NAME + PROPS_BASE_PACKAGE_SUFFIX_MYBATIS)
		public MybatisProperties mybatisProperties() {
			return new MybatisProperties();
		}

		/**
		 * Beanを登録する（DataSource）。 JNDIベースのデータソースについては、
		 * {@link org.springframework.boot.autoconfigure.jdbc.JndiDataSourceAutoConfiguration
		 * JndiDataSourceAutoConfiguration}に倣って実装。
		 * 
		 * @param dataSourceProperties DataSourcePropertiesのBeanインスタンス。
		 * @return DataSourceのBeanインスタンス。
		 * @throws NamingException
		 * @throws IllegalArgumentException
		 */
		@Bean(name = DATA_SOURCE_NAME + BEAN_BASE_NAME_DATA_SOURCE)
		public DataSource dataSource(@Qualifier(DATA_SOURCE_NAME + BEAN_BASE_NAME_PROPS_DATA_SOURCE) DataSourceProperties dataSourceProperties) {
			if (log.isDebugEnabled()) {
				debugProperty(DATA_SOURCE_NAME, dataSourceProperties);
			}

			if (StringUtils.isEmpty(dataSourceProperties.getJndiName())) {
				// JNDI名が指定されていない場合はユーザ名・パスワード・URLベースでデータソースを生成
				return dataSourceProperties.initializeDataSourceBuilder().build();
			} else {
				// JNDI名が指定されている場合はJNDIベースでデータソースを生成
				JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();

				return dataSourceLookup.getDataSource(dataSourceProperties.getJndiName());
			}
		}

		/**
		 * Beanを登録する（SqlSessionFactory）。
		 * {@link org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration
		 * MybatisAutoConfiguration}に倣って実装。 ただし、設定対象項目については必要最低限にとどめている。
		 * 
		 * @param dataSourceProperties DataSourcePropertiesのBeanインスタンス。
		 * @param mybatisProperties    MybatisPropertiesのBeanインスタンス。
		 * @return SqlSessionFactoryのBeanインスタンス。
		 */
		@Bean(name = DATA_SOURCE_NAME + BEAN_BASE_NAME_SESSION_FACTORY)
		public SqlSessionFactory sqlSessionFactory(@Qualifier(DATA_SOURCE_NAME + BEAN_BASE_NAME_DATA_SOURCE) DataSource dataSource,
				@Qualifier(DATA_SOURCE_NAME + BEAN_BASE_NAME_PROPS_MYBATIS) MybatisProperties mybatisProperties) throws Exception {
			if (log.isDebugEnabled()) {
				debugProperty(DATA_SOURCE_NAME, mybatisProperties);
			}

			// SqlSessionFactoryBeanに基本設定を適用
			SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
			sqlSessionFactoryBean.setDataSource(dataSource);
			sqlSessionFactoryBean.setVfs(SpringBootVFS.class);
			if (!StringUtils.isEmpty(mybatisProperties.getTypeAliasesPackage())) {
				sqlSessionFactoryBean.setTypeAliasesPackage(mybatisProperties.getTypeAliasesPackage());
			}
			if (mybatisProperties.getTypeAliasesSuperType() != null) {
				sqlSessionFactoryBean.setTypeAliasesSuperType(mybatisProperties.getTypeAliasesSuperType());
			}
			if (!StringUtils.isEmpty(mybatisProperties.getTypeHandlersPackage())) {
				sqlSessionFactoryBean.setTypeHandlersPackage(mybatisProperties.getTypeHandlersPackage());
			}

			// SqlSessionFactoryBeanにMybatisカスタマイズ設定を適用
			if (mybatisProperties.getConfiguration() != null) {
				sqlSessionFactoryBean.setConfiguration(mybatisProperties.getConfiguration());
			}

			return sqlSessionFactoryBean.getObject();
		}

		/**
		 * Beanを登録する（DataSourceProperties）。
		 * {@link org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration
		 * MybatisAutoConfiguration}に倣って実装。
		 * Mapperスキャンでは使わないが、DAOで個別にDIしたりMybatis内部で使用している可能性もあるので念のため用意しておく。
		 * 
		 * @param sqlSessionFactory SqlSessionTemplateのBeanインスタンス。
		 * @param mybatisProperties MybatisPropertiesのBeanインスタンス。
		 * @return SqlSessionTemplateのBeanインスタンス。
		 */
		@Bean(name = DATA_SOURCE_NAME + BEAN_BASE_NAME_SESSION_TEMPLATE)
		public SqlSessionTemplate sqlSessionTemplate(@Qualifier(DATA_SOURCE_NAME + BEAN_BASE_NAME_SESSION_FACTORY) SqlSessionFactory sqlSessionFactory,
				@Qualifier(DATA_SOURCE_NAME + BEAN_BASE_NAME_PROPS_MYBATIS) MybatisProperties mybatisProperties) {
			ExecutorType executorType = mybatisProperties.getExecutorType();

			if (executorType != null) {
				return new SqlSessionTemplate(sqlSessionFactory, executorType);
			} else {
				return new SqlSessionTemplate(sqlSessionFactory);
			}
		}
	}

	/**
	 * データソース設定をデバッグログ出力する。
	 * 
	 * @param dataSourceName       データソース名。
	 * @param dataSourceProperties データソースプロパティ。
	 */
	private static void debugProperty(String dataSourceName, DataSourceProperties dataSourceProperties) {
		debugProperty(dataSourceName, ".jndiName", dataSourceProperties.getJndiName());
		debugProperty(dataSourceName, ".url", dataSourceProperties.getUrl());
		debugProperty(dataSourceName, ".username", dataSourceProperties.getUsername());
		debugProperty(dataSourceName, ".driverClassName", dataSourceProperties.getDriverClassName());
		debugProperty(dataSourceName, ".type", dataSourceProperties.getType() == null ? "null" : dataSourceProperties.getType().getName());
	}

	/**
	 * Mybatis設定をデバッグログ出力する。
	 * 
	 * @param dataSourceName    データソース名。
	 * @param mybatisProperties Mybatisプロパティ。
	 */
	private static void debugProperty(String dataSourceName, MybatisProperties mybatisProperties) {
		debugProperty(dataSourceName, PROPS_BASE_PACKAGE_SUFFIX_MYBATIS + ".type-aliases-package", mybatisProperties.getTypeAliasesPackage());
		debugProperty(dataSourceName, PROPS_BASE_PACKAGE_SUFFIX_MYBATIS + ".type-aliases-super-type", mybatisProperties.getTypeAliasesSuperType());
		debugProperty(dataSourceName, PROPS_BASE_PACKAGE_SUFFIX_MYBATIS + ".type-handlers-package", mybatisProperties.getTypeHandlersPackage());

		if (mybatisProperties.getConfiguration() != null) {
			org.apache.ibatis.session.Configuration configuration = mybatisProperties.getConfiguration();

			debugProperty(dataSourceName, PROPS_BASE_PACKAGE_SUFFIX_MYBATIS + ".configuration.cacheEnabled", configuration.isCacheEnabled());
			debugProperty(dataSourceName, PROPS_BASE_PACKAGE_SUFFIX_MYBATIS + ".configuration.lazyLoadingEnabled", configuration.isLazyLoadingEnabled());
			debugProperty(dataSourceName, PROPS_BASE_PACKAGE_SUFFIX_MYBATIS + ".configuration.aggressiveLazyLoading", configuration.isAggressiveLazyLoading());
			debugProperty(dataSourceName, PROPS_BASE_PACKAGE_SUFFIX_MYBATIS + ".configuration.multipleResultSetsEnabled", configuration.isMultipleResultSetsEnabled());
			debugProperty(dataSourceName, PROPS_BASE_PACKAGE_SUFFIX_MYBATIS + ".configuration.useColumnLabel", configuration.isUseColumnLabel());
			debugProperty(dataSourceName, PROPS_BASE_PACKAGE_SUFFIX_MYBATIS + ".configuration.useGeneratedKeys", configuration.isUseGeneratedKeys());
			debugProperty(dataSourceName, PROPS_BASE_PACKAGE_SUFFIX_MYBATIS + ".configuration.autoMappingBehavior", configuration.getAutoMappingBehavior());
			debugProperty(dataSourceName, PROPS_BASE_PACKAGE_SUFFIX_MYBATIS + ".configuration.defaultExecutorType", configuration.getDefaultExecutorType());
			debugProperty(dataSourceName, PROPS_BASE_PACKAGE_SUFFIX_MYBATIS + ".configuration.defaultStatementTimeout", configuration.getDefaultStatementTimeout());
			debugProperty(dataSourceName, PROPS_BASE_PACKAGE_SUFFIX_MYBATIS + ".configuration.safeRowBoundsEnabled", configuration.isSafeRowBoundsEnabled());
			debugProperty(dataSourceName, PROPS_BASE_PACKAGE_SUFFIX_MYBATIS + ".configuration.mapUnderscoreToCamelCase", configuration.isMapUnderscoreToCamelCase());
			debugProperty(dataSourceName, PROPS_BASE_PACKAGE_SUFFIX_MYBATIS + ".configuration.localCacheScope", configuration.getLocalCacheScope());
			debugProperty(dataSourceName, PROPS_BASE_PACKAGE_SUFFIX_MYBATIS + ".configuration.jdbcTypeForNull", configuration.getJdbcTypeForNull());
			debugProperty(dataSourceName, PROPS_BASE_PACKAGE_SUFFIX_MYBATIS + ".configuration.lazyLoadTriggerMethods", configuration.getLazyLoadTriggerMethods());
			debugProperty(dataSourceName, PROPS_BASE_PACKAGE_SUFFIX_MYBATIS + ".configuration.defaultScriptingLanguage",
					configuration.getDefaultScriptingLanguageInstance() == null ? "null" : configuration.getDefaultScriptingLanguageInstance().getClass().getName());
			debugProperty(dataSourceName, PROPS_BASE_PACKAGE_SUFFIX_MYBATIS + ".configuration.callSettersOnNulls", configuration.isCallSettersOnNulls());
			debugProperty(dataSourceName, PROPS_BASE_PACKAGE_SUFFIX_MYBATIS + ".configuration.logPrefix", configuration.getLogPrefix());
			debugProperty(dataSourceName, PROPS_BASE_PACKAGE_SUFFIX_MYBATIS + ".configuration.logImpl",
					configuration.getLogImpl() == null ? "null" : configuration.getLogImpl().getName());
			debugProperty(dataSourceName, PROPS_BASE_PACKAGE_SUFFIX_MYBATIS + ".configuration.proxyFactory",
					configuration.getProxyFactory() == null ? "null" : configuration.getProxyFactory().getClass().getName());
		}
	}

	/**
	 * トランザクション設定をデバッグログ出力する。
	 * 
	 * @param dataSourceName        データソース名。
	 * @param transactionProperties トランザクションプロパティ。
	 */
	private static void debugProperty(String dataSourceName, TransactionProperties transactionProperties) {
		debugProperty(dataSourceName, PROPS_BASE_PACKAGE_SUFFIX_TRANSACTION + ".defaultTimeout",
				transactionProperties.getDefaultTimeout() == null ? "null" : transactionProperties.getDefaultTimeout().toSeconds());
		debugProperty(dataSourceName, PROPS_BASE_PACKAGE_SUFFIX_TRANSACTION + ".rollbackOnCommitFailure", transactionProperties.getRollbackOnCommitFailure());
	}

	/**
	 * プロパティをデバッグログ出力する。
	 * 
	 * @param dataSourceName データソース名。
	 * @param key            キー。
	 * @param value          値。
	 */
	private static void debugProperty(String dataSourceName, String key, Object value) {
		log.debug(MybatisConfig.class.getName() + " " + PROPS_BASE_PACKAGE_PREFIX + dataSourceName + key + "=" + value);
	}
}
