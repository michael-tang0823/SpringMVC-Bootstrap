package org.tangquan.microservices.profile.dev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;

@Configuration
@Profile("dev")
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/css/**").permitAll()
				.anyRequest().fullyAuthenticated()
				.and()
			.formLogin();
	}

	@Configuration
	@Profile("dev")
	protected static class AuthenticationConfiguration extends
			GlobalAuthenticationConfigurerAdapter {

		@Value("${ldap.url}")
		String ldapUrl;
        @Autowired
        LdapAuthoritiesPopulator ldapAuthoritiesPopulator;

		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			auth
				.ldapAuthentication()
                    .ldapAuthoritiesPopulator(ldapAuthoritiesPopulator)
					.userDnPatterns("uid={0},ou=serviceaccounts,ou=accounts")
					.groupSearchBase("ou=groups")
					.groupSearchFilter("(&(cn=*Application*)(member={0}))")
					.contextSource().url(ldapUrl);
		}
	}
}