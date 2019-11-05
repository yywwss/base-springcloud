package com.zhwl.config;


import com.zhwl.bean.Customer;
import com.zhwl.feign.CustomerServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yihe on 2017/12/28.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "userDetailsServiceImpl")
    private UserDetailsService userDetailsService;
    @Resource(name = "loginSuccessHandler")
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Resource(name = "passwordLoginFailureHandler")
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Resource(name = "dropoutSuccessHandler")
    private LogoutSuccessHandler logoutSuccessHandler;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder()); //添加自定义的userDetailsService认证
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/index.html","/smsCode/**","/static/**",
                "/**/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/security",
                "/swagger-ui.html", "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").loginProcessingUrl("/login").usernameParameter("username").passwordParameter("password").permitAll()
                .failureHandler(authenticationFailureHandler).successHandler(authenticationSuccessHandler)
                .and()
                .logout().logoutSuccessHandler(logoutSuccessHandler).permitAll().and().csrf().disable();
    }

    @Component("userDetailsServiceImpl")
    class UserDetailsServiceImpl implements UserDetailsService{

        @Autowired
        private CustomerServiceFeign customerServiceFeign;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            UserDetails userDetails = customerServiceFeign.loadUserByUsername(username);
            if (userDetails == null)
                throw new UsernameNotFoundException("用户名错误");
            return userDetails;
        }
    }

}

