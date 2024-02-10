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
import yuriy.weiss.reactor.training.jackson.JacksonTraining;

/**
 * Web part added to application to prevent main thread stopping before parallel Flux work finished.
 */
@Configuration( proxyBeanMethods = false )
public class ApplicationRouter {

    private final JacksonTraining jacksonTraining;

    public ApplicationRouter( JacksonTraining jacksonTraining ) {
        this.jacksonTraining = jacksonTraining;
    }

    @Bean
    public RouterFunction<ServerResponse> route() {
        return RouterFunctions
                .route()
                .GET( "/isAlive", this::isAlive )
                .GET( "/runJackson", this::runJackson )
                .build();
    }

    private Mono<ServerResponse> isAlive( ServerRequest serverRequest ) {
        return ServerResponse.ok()
                .contentType( MediaType.APPLICATION_JSON )
                .body( BodyInserters.fromValue( "I'm alive" ) );
    }

    private Mono<ServerResponse> runJackson( ServerRequest serverRequest ) {
        jacksonTraining.run();
        return ServerResponse.ok()
                .contentType( MediaType.APPLICATION_JSON )
                .body( BodyInserters.fromValue( "Jackson execution started" ) );
    }
}
