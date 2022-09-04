package com.study.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

//지정된 클래스를 자바 기반의 설정 파일로 인식
@Configuration
//해당 클래스에서 참조할 properties(yml) 파일의 위치를 지정
@PropertySource("classpath:/application.yml")
public class DatabaseConfig {
	
	/*
	 * @Configuration클래스의 메서드 레벨에만 지정이 가능하며,
	 * @Bean이 지정된 객체는 컨테이너에 의해 관리되는 빈(Bean)으로 등록된다. 
	 */
	@Bean
	/*
	 * @ProeprtiesSource에 지정된 파일에서 prefix에 해당하는 설정을
	 * 모두 읽어들여 해당 메서드에 매핑(바인딩)한다.
	 */
	@ConfigurationProperties(prefix="spring.datasource.hikari")
	//히카리 CP 객체를 생성 (커넥션 풀 라이브러리)
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}
	
	@Bean
	//데이터 소스 객체를 생성(커넥션 풀을 지원하기 위한 인터페이스)
	public DataSource dataSource() {
		return new HikariDataSource(hikariConfig());
	}

}
