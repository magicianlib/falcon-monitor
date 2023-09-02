package io.ituknown.falcon;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(PerformanceMonitorConfig.class)
public @interface EnableFalconClient {
}
