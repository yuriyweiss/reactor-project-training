package yuriy.weiss.reactor.training;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import yuriy.weiss.common.ThreadUtils;

@Slf4j
public class BackpressureReadySubscriber extends BaseSubscriber<Object> {

    @Override
    public void hookOnSubscribe( Subscription subscription ) {
        //requested the first item on subscribe
        request( 1 );
    }

    @Override
    public void hookOnNext( Object value ) {
        ProcessingMessage message = ( ProcessingMessage ) value;
        log.info( "start message processing: {}", message );
        ThreadUtils.sleep( message.processingTime() );
        log.info( "processing finished: {}", message );
        request( 1 );
    }
}
