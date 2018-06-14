package com.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
public class HttpSessionConfig {

//Creating beans for redis server which would help in session management
	
	@Bean
	public LettuceConnectionFactory connectionFactory() {
		return new LettuceConnectionFactory();
	}
	@Bean
	public static ConfigureRedisAction configureRedisAction() {
	return ConfigureRedisAction.NO_OP;
	}
}
