package tw.com.order.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import tw.com.order.demo.service.impl.MemberServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	//Spring
	@Bean
	public UserDetailsService userDetailsService() {
		return new MemberServiceImpl();
	}
	
	//處理密碼編碼轉換
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//連接資料庫
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		
		.antMatchers("/dashboard/**").hasAnyAuthority("admin","Member","editor")
		.antMatchers("/editor/**").hasAnyAuthority("admin","editor")
		.antMatchers("/admin/**").hasAnyAuthority("admin")
//		.antMatchers().authenticated()
		.anyRequest().permitAll()
		
		
		
		.and()
		.formLogin()
		.loginPage("/login") //自訂login畫面
		.usernameParameter("userEmail") //使用email當帳號
		.defaultSuccessUrl("/dashboard")
		.permitAll()
		.and()
		.logout()
		.logoutSuccessUrl("/login")
		.deleteCookies("JSESSIONID")
		.invalidateHttpSession(true)
		.and()
	    .csrf().disable();;
//		.permitAll();
		
		
	}

	
		
	}


