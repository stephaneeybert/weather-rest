package com.thalasoft.weatherrest.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class EnvProdCondition implements Condition {

	private static final String ENV = "prod";

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
    boolean isProd = false;
    String env = context.getEnvironment().getProperty("env");
    if (env == null || env.equals(ENV)) {
      isProd = true;
    }
    return isProd;
	}

}