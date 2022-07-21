package com.example.springsocial.security.oauth2;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import java.lang.annotation.*;


@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
// to access the currently authenticated user in the controllers.
@AuthenticationPrincipal
public @interface CurrentUser {

}
