package com.venti.shiro.filter;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited//子类可继承该注解
public @interface NotAuthc {

}
