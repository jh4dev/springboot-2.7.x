/*
 * package com.amorepacific.iris.user.portal.config;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.annotation.authentication.builders.
 * AuthenticationManagerBuilder; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter; import
 * org.springframework.security.crypto.password.PasswordEncoder;
 * 
 * import com.amorepacific.iris.user.portal.encoder.NoOpEncoder; import
 * com.amorepacific.iris.user.portal.service.PortalUserDetailService;
 * 
 * @Configuration public class SecurityConfig_bak extends
 * WebSecurityConfigurerAdapter {
 * 
 * @Autowired private PortalUserDetailService portalUserDetailService;
 * 
 * @Autowired private PasswordEncoder passwordEncoder;
 * 
 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
 * Exception { System.out.println("configure auth");
 * auth.userDetailsService(portalUserDetailService).passwordEncoder(
 * passwordEncoder); }
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception {
 * System.out.println("configure http"); http .csrf().disable()
 * .authorizeRequests() .antMatchers("/login").permitAll()
 * .anyRequest().authenticated() .and() .formLogin()
 * .loginProcessingUrl("/login") .defaultSuccessUrl("/aaa", true) .permitAll()
 * .and() .logout() .logoutSuccessUrl("/").permitAll(); }
 * 
 * @Bean public PasswordEncoder passwordEncoder() { return new NoOpEncoder(); }
 * }
 */