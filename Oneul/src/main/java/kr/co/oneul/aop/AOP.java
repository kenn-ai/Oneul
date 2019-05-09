package kr.co.oneul.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AOP {

	// AOP 추가 시 root-context 같이 수정할 것

	@Around("execution(* kr.co.oneul.service.impl.ServiceImpl.*(..))")
	public Object example(ProceedingJoinPoint pjp) throws Throwable {
		
		return null;
	}
}
