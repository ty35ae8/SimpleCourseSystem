package student.ams.web.config;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;


@Configuration
//掃描包
@ComponentScan(basePackages="student.ams.*",
		//排除掃描標註@Controller和@RestController的類
		excludeFilters = @Filter(type=FilterType.ANNOTATION,classes={Controller.class,RestController.class})
)


public class BackendConfig {
	//數據源
	private DataSource dataSource = null;
	//配置數據源
	@Bean(name="dataSource")
	public DataSource initDataSource(){
		if(dataSource!=null){
				return dataSource;
		}
		Properties props = new Properties();
		props.setProperty("driverClassName","com.mysql.cj.jdbc.Driver");
		props.setProperty("url","jdbc:mysql://localhost:3306/student_ams?serverTimezone=Asia/Taipei");
		props.setProperty("username","root");
		props.setProperty("password","a8q12py4");
		props.setProperty("maxActive","200");
		props.setProperty("maxIdle","20");
		props.setProperty("maxWait","30000");
		try {
			dataSource = BasicDataSourceFactory.createDataSource(props);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dataSource;
	}
	
	
	@Bean(name="jdbcTemplate")
	public JdbcTemplate initjdbcTemplate(@Autowired DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);
		return jdbcTemplate;
	}
	
	
	
	//創建SqlSessionFactory
	@Bean("sqlSessionFactory")
	public SqlSessionFactory createSqlSessionFactoryBean(@Autowired DataSource dataSource, ApplicationContext applicationContext) throws Exception {
		//配置文件
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setConfigLocation( 
				applicationContext.getResource("/mapper/mapper_configuration.xml")
		);
		sqlSessionFactoryBean.setMapperLocations(
                applicationContext.getResources("/mapper/*_mapper.xml")
        );

		return sqlSessionFactoryBean.getObject();
	
	}
	
	
	 // Transaction Manager
	 @Autowired
	 @Bean(name = "transactionManager")
	 public DataSourceTransactionManager getTransactionManager(DataSource dataSource) {
	       DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
	       return transactionManager;
	 }
}
