package org.tangquan.microservices.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.ContextSource;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.security.ldap.userdetails.NestedLdapAuthoritiesPopulator;

/**
 * Created by mtang on 1/10/16.
 */
//@Configuration
public class LdapConfig {

    @Value("${ldap.url}")
    String ldapUrl;

    @Value("${ldap.group.search.base}")
    String groupSearchBase;

    @Bean
    public ContextSource contextSource() {
        ContextSource contextSource = new DefaultSpringSecurityContextSource(ldapUrl);

        return contextSource;
    }

    @Bean
    public LdapAuthoritiesPopulator ldapAuthoritiesPopulator() {
        return new NestedLdapAuthoritiesPopulator(contextSource(), groupSearchBase);
    }
}
