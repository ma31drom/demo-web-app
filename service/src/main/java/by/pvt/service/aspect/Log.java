package by.pvt.service.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Target;

@Inherited
@Target(ElementType.METHOD)
public @interface Log {

	String operation() default "Unknown";

	boolean mesureDuration() default false;

}
