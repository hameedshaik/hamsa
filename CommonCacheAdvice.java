package com.sgl.smartpra.master.app.common.aspects;

import java.util.List;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;

@Aspect
@Component
public class CommonCacheAdvice<T> {
	@Autowired
	private HazelcastInstance hazelcastInstance;

	@Around("methodToRead()")
	public Object getEntries(ProceedingJoinPoint joinpoint) throws Throwable {

		Object[] args = joinpoint.getArgs();
		Map<Object, Object> hazelcastMap = hazelcastInstance.getMap("smartrpa-master");
		StringBuffer key = new StringBuffer(joinpoint.getSignature().getName());
		for (int argIndex = 0; argIndex < args.length; argIndex++) {
			key.append(":" + args[argIndex]);
		}
		if (hazelcastMap.get(key) == null) {
			Object result = joinpoint.proceed();
			hazelcastMap.put(key, result);

			return result;
		}
		return hazelcastMap.get(key);
	}

	@Around("methodToCreate()")
	public Object createEntry(ProceedingJoinPoint joinpoint) throws Throwable {

		Map<String, List<Object>> hazelcastMap = hazelcastInstance.getMap("smartrpa-master");
		Object result = joinpoint.proceed();
		hazelcastMap.clear();
		return result;
	}

	@Around("methodTodeActivate()")
	public Object deActivateEntry(ProceedingJoinPoint joinpoint) throws Throwable {

		Map<String, List<Object>> hazelcastMap = hazelcastInstance.getMap("smartrpa-master");
		Object result = joinpoint.proceed();
		hazelcastMap.clear();
		return result;
	}

	@Around("methodToUpdate()")
	public Object updateEntry(ProceedingJoinPoint joinpoint) throws Throwable {

		Map<String, List<Object>> hazelcastMap = hazelcastInstance.getMap("smartrpa-master");
		Object result = joinpoint.proceed();
		hazelcastMap.clear();
		return result;
	}

	@Pointcut("execution(* com.sgl.smartpra.master.app.controller.*.get*(..))")
	public void methodToRead() {
	}

	@Pointcut("execution(* com.sgl.smartpra.master.app.controller.*.deactivate*(..))")
	public void methodTodeActivate() {
	}

	@Pointcut("execution(* com.sgl.smartpra.master.app.controller.*.update*(..))")
	public void methodToUpdate() {
	}

	@Pointcut("execution(* com.sgl.smartpra.master.app.controller.*.create*(..))")
	public void methodToCreate() {
	}

}
