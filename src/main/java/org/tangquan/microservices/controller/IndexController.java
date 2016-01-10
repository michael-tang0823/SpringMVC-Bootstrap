package org.tangquan.microservices.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mtang on 1/10/16.
 */
public class IndexController {

    @RequestMapping("/")
    public String index() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "index";
    }
}

