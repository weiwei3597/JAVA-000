package com.mysql.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

@SpringBootApplication
		(exclude = { DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		JdbcTemplateAutoConfiguration.class})
@Slf4j
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

//	@Bean
//	@ConfigurationProperties("m.datasource")
//	public DataSourceProperties mDataSourceProperties() {
//		return new DataSourceProperties();
//	}
//
//	@Bean
//	public DataSource mDataSource() {
//		DataSourceProperties dataSourceProperties = mDataSourceProperties();
//		log.info("m datasource: {}", dataSourceProperties.getUrl());
//		return dataSourceProperties.initializeDataSourceBuilder().build();
//	}
//
//
//	@Bean
//	@Resource
//	public PlatformTransactionManager mTxManager(DataSource mDataSource) {
//		return new DataSourceTransactionManager(mDataSource);
//	}
//
//	@Bean
//	public JdbcTemplate mJdbcTemplate(DataSource mDataSource){
//		return new JdbcTemplate(mDataSource);
//	}
//
//	@Bean
//	@ConfigurationProperties("s.datasource")
//	public DataSourceProperties sDataSourceProperties() {
//		return new DataSourceProperties();
//	}
//
//	@Bean
//	public DataSource sDataSource() {
//		DataSourceProperties dataSourceProperties = sDataSourceProperties();
//		log.info("s datasource: {}", dataSourceProperties.getUrl());
//		return dataSourceProperties.initializeDataSourceBuilder().build();
//	}
//
//	@Bean
//	@Resource
//	public PlatformTransactionManager sTxManager(DataSource sDataSource) {
//		return new DataSourceTransactionManager(sDataSource);
//	}
//
//	@Bean
//	public JdbcTemplate sJdbcTemplate(DataSource sDataSource){
//		return new JdbcTemplate(sDataSource);
//	}


	@Bean
	public JdbcTemplate ssJdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}


	@Bean
	@Resource
	public PlatformTransactionManager ssTxManager(DataSource shardingSphereDataSource) {
		return new DataSourceTransactionManager(shardingSphereDataSource);
	}

}
