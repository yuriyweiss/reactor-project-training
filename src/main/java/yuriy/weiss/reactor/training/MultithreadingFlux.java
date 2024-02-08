package yuriy.weiss.reactor.training;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;
import yuriy.weiss.common.ThreadUtils;

import java.util.UUID;

@Slf4j
@Component
public class MultithreadingFlux {

    private static final long AVG_PROCESSING_TIME = 1000L;

    public void launch() {
        log.info( "available processors: {}", Runtime.getRuntime().availableProcessors() );

        ParallelFlux<ProcessingMessage> flux = Flux.range( 0, 1000 )
                .parallel( 32 )
                .runOn( Schedulers.newBoundedElastic( 1024, 2048, "yuriy.weiss.bouneded.pool.01" ) )
                .log()
                .map( i ->
                        new ProcessingMessage( i, UUID.randomUUID().toString(),
                                ThreadUtils.nextGaussian( AVG_PROCESSING_TIME ) ) );
        // flux.subscribe( o -> log.info( "processing {}", o ) );
        BackpressureReadySubscriber[] subscribers = new BackpressureReadySubscriber[32];
        for ( int i = 0; i < 32; i++ ) {
            subscribers[i] = new BackpressureReadySubscriber();
        }
        flux.subscribe( subscribers );
    }
}
