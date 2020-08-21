package by.pvt.service.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

	@Pointcut("@annotation(by.pvt.service.aspect.Log)")
	void annotated() {
	};

	@Around(value = "annotated()")
	Object logOperation(ProceedingJoinPoint operation) {
		try {

			MethodSignature signature = (MethodSignature) operation.getSignature();
			Method method = signature.getMethod();

			Log myAnnotation = AnnotationUtils.findAnnotation(method, Log.class);
			log.info("Operation invoked: " + operation.getSignature());
			Object proceed = operation.proceed();
			log.info("Operation finished");
			return proceed;
		} catch (Throwable e) {
			log.error("Operation failed", e);
			throw new RuntimeException(e);
		}

	}
}
