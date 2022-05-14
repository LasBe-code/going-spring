package aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class Logging {
	@Around("execution(* service.*.*(..))")
	public Object logger(ProceedingJoinPoint pjp) throws Throwable {
		StopWatch sw = new StopWatch();
		Object obj = null;
		sw.start();
		
		try {
			obj = pjp.proceed();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("["+pjp.getSignature()+"] : 오류 발생");
		} finally {
			sw.stop();
			System.out.println("["+pjp.getSignature().toShortString()+"] : "+sw.getTotalTimeMillis()/1000.0+"초");
		}
		
		return obj;
	}
}
