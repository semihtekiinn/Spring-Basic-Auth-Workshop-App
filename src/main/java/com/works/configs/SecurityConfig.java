package com.works.configs;

import com.works.services.UserService;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(userService.encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/repair/save").hasRole("customer")
                .antMatchers("/repair/productUpdate").hasRole("admin")
                .antMatchers("/repair/listAll").hasRole("admin")
                .antMatchers("/repair/listByStatus").hasRole("admin")
                .antMatchers("/role/**").hasRole("admin")
                .antMatchers("/user/**").permitAll()
                .and()
                .csrf().disable().formLogin().disable();
    }


    //Cache config
    @Bean
    public CacheManager cacheManager(){
        return new ConcurrentMapCacheManager("repairList","repairListByStatus");
    }

}
