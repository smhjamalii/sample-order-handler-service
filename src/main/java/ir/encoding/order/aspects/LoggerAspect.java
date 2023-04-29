package ir.encoding.order.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "prod")
@Slf4j
public class LoggerAspect {

	@Pointcut("within(ir.encoding.order.data.jpa.repositories.*) || "
			+ "within(ir.encoding.order.data.jpa.repositories.*.custom.*) || "
			+ "within(ir.encoding.order.service.impls.*)")
	public void logAfterPublicMethodsOfRepositoryAndServices() {}
	
	@AfterReturning("logAfterPublicMethodsOfRepositoryAndServices()")
	public void logAfterPublicMethodsOfRepositoryAndServicesAdvice(JoinPoint joinPoint) {
		log.info("{}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
		log.info("args: {}", joinPoint.getArgs());
	}
	
	@AfterThrowing("logAfterPublicMethodsOfRepositoryAndServices()")
	public void logAfterPublicMethodsOfRepositoryAndServicesThrowExceptionAdvice(JoinPoint joinPoint) {
		log.error("{}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
		log.error("args: {}", joinPoint.getArgs());
	}
}
