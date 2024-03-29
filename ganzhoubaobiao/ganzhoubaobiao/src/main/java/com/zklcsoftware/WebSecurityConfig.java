package com.zklcsoftware;

import java.util.List;
import java.util.Map;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

/**
 * @author audin
 */
@Configuration
@EnableOAuth2Sso
@Order(4)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
    @Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/webjars/**", "/css/**", "/fonts/**",
						"/images/**", "/img_app/**", "/static/**", "/js/**",
						"/register", "/register/**", "/img/**",
						"/fileDownload/**", "/druid/**", "/swagger-ui.html",
						"/swagger-ui.html", "/configuration/**",
						"/swagger-resources/**", "/v2/api-docs/**", "/api/**"
						);
	}

	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();
        http
        // 禁用csrf配置
        	.csrf().disable()
                .authorizeRequests()
                	.antMatchers("/login").permitAll()
                    .antMatchers("/**").hasAuthority("USER")
                    .antMatchers("/index.html").hasAnyAuthority("ROLE_ANONYMOUS,USER")//添加匿名登录
                	.anyRequest().authenticated()
	            .and()
		            .formLogin()
		            .loginPage("/login")
		            .defaultSuccessUrl("/")//登陆成功后跳转到此路径
		            .permitAll()
	            .and()
	            	.logout()
	                .permitAll();
        
    }
	
   @Bean
    public OAuth2RestTemplate oauth2RestTemplate(OAuth2ProtectedResourceDetails resource, OAuth2ClientContext context) {
        return new OAuth2RestTemplate(resource, context);
    }
	   
	@Bean
	public AuthoritiesExtractor authoritiesExtractor(OAuth2RestOperations template) {
		return new AuthoritiesExtractor() {
			@Override
			public List<GrantedAuthority> extractAuthorities(Map<String, Object> map) {
				String userTypeStr = (String)map.get("userType");
				 if ("教师".equals(userTypeStr)) {
					String post = (String)map.get("post");
					if (post.indexOf("信息组") != -1 || post.indexOf("德育") != -1 || post.indexOf("校长") != -1
							|| post.indexOf("网管") != -1 || post.indexOf("系统管理员") != -1) {
						return AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN,USER");
					}
				}
				return AuthorityUtils.commaSeparatedStringToAuthorityList("USER");
			}
		};
	}
}
