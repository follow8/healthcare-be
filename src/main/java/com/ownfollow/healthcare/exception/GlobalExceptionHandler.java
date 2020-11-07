package com.ownfollow.healthcare.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait;

@RestControllerAdvice
public class GlobalExceptionHandler implements ProblemHandling, SecurityAdviceTrait {
}
