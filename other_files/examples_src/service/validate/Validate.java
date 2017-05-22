package org.cendra.commons.util.service.validate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Validate {

	boolean required() default false;

	int lengthMin() default 1;
	
	boolean requiredExpectedValueDomain() default false;
	
	boolean expectedValueBoolean() default false;

}