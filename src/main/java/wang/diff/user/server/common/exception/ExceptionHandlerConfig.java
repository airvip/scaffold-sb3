package wang.diff.user.server.common.exception;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class ExceptionHandlerConfig {
//    @Bean
//    public BackendExceptionHandler backendExceptionHandler() {
//        return new BackendExceptionHandler();
//    }
}