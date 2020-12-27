package com.thalasoft.weatherrest.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class EnvTestCondition implements Condition {

	private static final String ENV = "test";

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
    boolean isTest = false;
    String env = context.getEnvironment().getProperty("env");
    if (env != null) {
      isTest = env.equals(ENV);
    }
    return isTest;
	}

}
