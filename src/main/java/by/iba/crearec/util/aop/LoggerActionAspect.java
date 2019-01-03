package by.iba.crearec.util.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggerActionAspect {

//	@Pointcut("execution(private * *Action(..))")
//	public void callAction() {
//	}
//
//	@Around("callAction()")
//	public Object around(ProceedingJoinPoint pjp) throws Throwable {
//		System.out.println(pjp.getKind());
//		return pjp.proceed();
//	}

}
