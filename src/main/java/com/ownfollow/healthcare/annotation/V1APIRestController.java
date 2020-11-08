package com.ownfollow.healthcare.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
@RequestMapping(V1APIRestController.API_PREFIX)
public @interface V1APIRestController {
  String API_PREFIX = "/api/v1";

  @AliasFor(annotation = RestController.class)
  String value() default "";
}
