package io.ituknown.falcon;

import io.ituknown.falcon.interceptor.PerformanceMonitorMethodInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.StringJoiner;

@Configuration
@EnableConfigurationProperties(FalconMonitorConfigurationProperties.class)
public class PerformanceMonitorConfig {

    @Bean("performanceMonitorMethodInterceptor")
    public PerformanceMonitorMethodInterceptor performanceMonitorMethodInterceptor(FalconMonitorConfigurationProperties properties) {
        return new PerformanceMonitorMethodInterceptor(properties.getAppId(), properties.getReportServerUrl());
    }

    @Bean("performanceMonitorMonitorPointcut")
    public AspectJExpressionPointcut performanceMonitorMonitorPointcut(FalconMonitorConfigurationProperties properties) {

        StringJoiner joiner = new StringJoiner(" || ");
        for (String expression : properties.getPointcutExpression()) {
            joiner.add(expression);
        }

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(joiner.toString());
        return pointcut;
    }

    @Bean("performanceMonitorAdvice")
    public Advisor performanceMonitorAdvice(FalconMonitorConfigurationProperties properties) {
        return new DefaultPointcutAdvisor(performanceMonitorMonitorPointcut(properties), performanceMonitorMethodInterceptor(properties));
    }
}
