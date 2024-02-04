package yuriy.weiss.reactor.training;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import yuriy.weiss.common.ThreadUtils;

import java.util.UUID;

@Slf4j
@Component
public class GeneratingFlux {

    private static final long AVG_PROCESSING_TIME = 1000L;

    public void launch() {
        log.info( "start processing" );
        Flux<ProcessingMessage> flux = Flux.range( 0, 10 )
                .map( i ->
                        new ProcessingMessage( i, UUID.randomUUID().toString(),
                                ThreadUtils.nextGaussian( AVG_PROCESSING_TIME ) ) );
        flux.subscribe( message -> {
            log.info( "start message processing: {}", message );
            ThreadUtils.sleep( message.processingTime() );
            log.info( "processing finished: {}", message );
        } );
    }
}
