package yuriy.weiss.reactor.training.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Web part added to application to prevent main thread stopping before parallel Flux work finished.
 */
@Configuration( proxyBeanMethods = false )
public class ApplicationRouter {

    @Bean
    public RouterFunction<ServerResponse> route() {
        return RouterFunctions
                .route()
                .GET( "/isAlive", this::isAlive )
                .build();
    }

    private Mono<ServerResponse> isAlive( ServerRequest serverRequest ) {
        return ServerResponse.ok()
                .contentType( MediaType.APPLICATION_JSON )
                .body( BodyInserters.fromValue( "I'm alive" ) );
    }
}
