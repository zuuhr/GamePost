package es.codeurjc.gamepost;

import java.util.Collections;
import java.util.Properties;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.web.WebFilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

@Configuration
@SpringBootApplication
@EnableHazelcastHttpSession
public class GamepostApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamepostApplication.class, args);
	}

	@Bean
	public Config config() {
		return new Config();
	}

	@Bean
	public WebFilter webFilter(HazelcastInstance hazelcastInstance) {

		Properties properties = new Properties();
		properties.put("instance-name", hazelcastInstance.getName());
		properties.put("sticky-session", "false");

		return new WebFilter(properties);
	}

	@Bean 
	public ServletListenerRegistrationBean<HttpSessionEventPublisher> getHttpSessionEventPublisher() {
		     return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
	}
	/*
	 * @Bean public Config config(){ Config config = new Config();
	 * 
	 * JoinConfig joinConfig = config.getNetworkConfig().getJoin();
	 * 
	 * joinConfig.getMulticastConfig().setEnabled(false);
	 * joinConfig.getTcpIpConfig().setEnabled(true).setMembers(Collections.
	 * singletonList("127.0.0.1"));
	 * 
	 * return config; }
	 */

}
