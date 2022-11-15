package net.tecgurus.gamestore.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.TransactionManager;

@Configuration
public class SpringDataJDBCConfig extends AbstractJdbcConfiguration{
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://db-game-store-do-user-12860483-0.b.db.ondigitalocean.com:25060/game_store");
		dataSource.setUsername("doadmin");
		dataSource.setPassword("AVNS_udD8O1KMZEMNzRNpYBR");
		return dataSource;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(this.dataSource());
	}
	
	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(this.dataSource());
	}
	
	@Bean
	public TransactionManager transactionManager() {
		return new DataSourceTransactionManager(this.dataSource());
	}
	
}
