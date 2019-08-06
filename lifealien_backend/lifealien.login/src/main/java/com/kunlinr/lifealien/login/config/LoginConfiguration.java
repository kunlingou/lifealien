package com.kunlinr.lifealien.login.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan({
	"com.kunlinr.lifealien.login"
})
@EntityScan({
	"com.kunlinr.lifealien.login"
})
@EnableJpaRepositories( 
	basePackages = {"com.kunlinr.lifealien.login"}
)
public class LoginConfiguration {
}
