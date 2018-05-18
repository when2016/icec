package org.icec.web.config;

import javax.sql.DataSource;

import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.beetl.sql.ext.spring4.BeetlSqlScannerConfigurer;
import org.beetl.sql.ext.spring4.SqlManagerFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class SystemConfig {
	@Bean(name = "beetlSqlScannerConfigurer")
	public BeetlSqlScannerConfigurer getBeetlSqlScannerConfigurer(
			@Qualifier("sqlManagerFactoryBean") SqlManagerFactoryBean fb) {
		BeetlSqlScannerConfigurer conf = new BeetlSqlScannerConfigurer();
		conf.setBasePackage("org.icec.web");
		conf.setDaoSuffix("Dao");
		conf.setSqlManagerFactoryBeanName("sqlManagerFactoryBean");
		return conf;
	}

	@Bean(name = "sqlManagerFactoryBean")
	@Primary
	public SqlManagerFactoryBean getSqlManagerFactoryBean(@Qualifier("datasource") DataSource datasource) {
		SqlManagerFactoryBean factory = new SqlManagerFactoryBean();

		BeetlSqlDataSource source = new BeetlSqlDataSource();
		source.setMasterSource(datasource);
		factory.setCs(source);
		factory.setDbStyle(new MySqlStyle());
		factory.setInterceptors(new Interceptor[] { new DebugInterceptor() });
		factory.setNc(new UnderlinedNameConversion());
		factory.setSqlLoader(new ClasspathLoader("/sql"));
		return factory;
	}

	@Bean(name = "datasource")
	public DataSource druidDataSource(Environment env) {
		DruidDataSource druidDataSource = new DruidDataSource();

		druidDataSource.setDriverClassName(env.getProperty("datasource.driver"));
		druidDataSource.setUrl(env.getProperty("datasource.url"));
		druidDataSource.setUsername(env.getProperty("datasource.username"));
		druidDataSource.setPassword(env.getProperty("datasource.password"));
		druidDataSource.setValidationQuery("SELECT 1 ");
		druidDataSource.setInitialSize(5);
		druidDataSource.setMaxActive(10);
		return druidDataSource;
	}
}
