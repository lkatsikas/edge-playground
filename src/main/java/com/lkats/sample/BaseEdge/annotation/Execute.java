package com.lkats.sample.BaseEdge.annotation;


import org.springframework.core.annotation.AliasFor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@RequestMapping(method = {RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public @interface Execute {

    @AliasFor(annotation = RequestMapping.class, attribute = "value")
    String[] value() default {};

    @AliasFor(annotation = RequestMapping.class, attribute = "method")
    RequestMethod[] method() default RequestMethod.POST;

    @AliasFor(annotation = RequestMapping.class, attribute = "consumes")
    String[] consumes() default MediaType.APPLICATION_JSON_UTF8_VALUE;

    @AliasFor(annotation = RequestMapping.class, attribute = "produces")
    String[] produces() default MediaType.APPLICATION_JSON_UTF8_VALUE;
}
