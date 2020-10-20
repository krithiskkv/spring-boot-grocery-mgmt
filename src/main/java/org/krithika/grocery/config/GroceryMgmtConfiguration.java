package org.krithika.grocery.config;

import org.krithika.grocery.authenticationEntryPoint.CustomAuthenticationEntryPoint;
import org.krithika.grocery.filter.CustomLoggingFilter;
import org.krithika.grocery.filter.CustomTransactionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;
/**
 * Created by administrator on 11/10/20.
 */
@Configuration
@EnableWebSecurity
public class GroceryMgmtConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(customTransactionFilterRegistrationBean().getFilter(), WebAsyncManagerIntegrationFilter.class);
        http
                .addFilterAfter(customLoggingFilterRegistrationBean().getFilter(), FilterSecurityInterceptor.class);
        http
                .authorizeRequests()
                .antMatchers("/groceryMgmt/users/register")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                    .authenticationEntryPoint(new CustomAuthenticationEntryPoint());
    }

    @Bean
    private FilterRegistrationBean<CustomLoggingFilter> customLoggingFilterRegistrationBean() {
        FilterRegistrationBean<CustomLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CustomLoggingFilter());
        return registrationBean;
    }

    @Bean
    private FilterRegistrationBean<CustomTransactionFilter> customTransactionFilterRegistrationBean() {
        FilterRegistrationBean<CustomTransactionFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CustomTransactionFilter());
        return registrationBean;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

