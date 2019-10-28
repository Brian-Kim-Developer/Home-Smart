package com.project.project6.config;

/*
import org.apache.tomcat.jdbc.pool.DataSource;
*/
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class RootConfig {
/*
	AIzaSyDn2NrsxWbxm-8Ys3o9nsBvaCAnBlgUVs4
*/
	/*@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		*//*ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://35.232.228.46:3306/homesmart?cloudSqlInstance=fit-guide-251509:us-central1:homesmart-db&autoReconnect=true&useSSL=false");
		ds.setUsername("GK");
		ds.setPassword("kgw35128032***");*//*
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost/spring5fs?autoReconnect=true&allowPublicKeyRetrieval=true&useSSL=false");
		ds.setUsername("spring5");
		ds.setPassword("Kgw128032");
		ds.setInitialSize(2);
		ds.setMaxActive(10);
		ds.setTestWhileIdle(true);
		ds.setMinEvictableIdleTimeMillis(60000 * 3);
		ds.setTimeBetweenEvictionRunsMillis(10 * 1000);
		return ds;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource());
		return tm;
	}*/

}
