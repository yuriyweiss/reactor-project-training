package yuriy.weiss.reactor.training.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import yuriy.weiss.reactor.training.jackson.dto.MessageDto;

import static yuriy.weiss.reactor.training.jackson.JsonSourceTextHolder.JSON1;

@Component
@Slf4j
public class JacksonTraining {

    private final ObjectMapper objectMapper;

    @Autowired
    public JacksonTraining( ObjectMapper objectMapper ) {
        this.objectMapper = objectMapper;
    }

    public void run() {
        try {
            MessageDto messageDto = objectMapper.readValue( JSON1, MessageDto.class );
            String serialized = objectMapper.writeValueAsString( messageDto );
            log.info( "messageDto: {}", messageDto );
            log.info( "messageDto json: {}", serialized );
        } catch ( JsonProcessingException e ) {
            log.error( "parsing error", e );
        }
    }
}
