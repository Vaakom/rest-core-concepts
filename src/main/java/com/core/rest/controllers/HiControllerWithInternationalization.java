package com.core.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HiControllerWithInternationalization {

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/hello")
    public String hello(){
        return "Hi, dude!";
    }

    @GetMapping(path = "/hello-bean")
    public WelcomeBean helloBean(@RequestHeader(name = "Accept-Language", required = false) Locale locale){

        return new WelcomeBean(messageSource.getMessage("greetings", null, LocaleContextHolder.getLocale()));
    }

    @GetMapping(path = "/hello/warm-temperature/{temperature}")
    public WelcomeBean helloWarm(@PathVariable String temperature){
        return new WelcomeBean(String.format("%s hello!", temperature));
    }
}
