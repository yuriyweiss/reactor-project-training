package yuriy.weiss.reactor.training.jackson.dto;

import java.time.LocalDateTime;

public record MessageDto(LocalDateTime rqTm, MessageBodyDto body) {
}
