package corp.netizen.datastore.controller;

import corp.netizen.datastore.DatastoreApplication;
import corp.netizen.datastore.dto.MibValuesDTO;
import corp.netizen.datastore.listener.StatusMessageListener;
import corp.netizen.datastore.repository.MibValuesRepository;
import corp.netizen.datastore.service.MibValuesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.Duration;


@RequestMapping("/users")
@RestController
public class MonitorDataStream {

    public static Logger logger = LoggerFactory.getLogger(MonitorDataStream.class);

    SimpleMessageListenerContainer simpleMessageListenerContainer;
    Flux<MibValuesDTO> f;
    MibValuesService mibValuesService;

    @Autowired
    public MonitorDataStream(SimpleMessageListenerContainer simpleMessageListenerContainer, MibValuesService mibValuesService) {
        this.mibValuesService = mibValuesService;
        this.simpleMessageListenerContainer = simpleMessageListenerContainer;
        this.f = Flux.<MibValuesDTO>create(emitter -> {
            simpleMessageListenerContainer.setupMessageListener((MessageListener) m -> {
                MibValuesDTO values = (MibValuesDTO) MibValuesDTO.deserialize(m.getBody());
                emitter.next(values);
            });
            emitter.onRequest(v -> {
                simpleMessageListenerContainer.start();
            });
            emitter.onDispose(() -> {
                simpleMessageListenerContainer.stop();
            });
        });
    }

    @GetMapping(path = "/data-stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MibValuesDTO> streamFlux() {
        logger.info("Data stream opened");
        this.mibValuesService.getLastDayDataOnQueue();
        return f;
    }

    @GetMapping(value = "/second-data-stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> streamEvents() {
        String x = "SIEMA";
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> ServerSentEvent.<String>builder()
                        .id(String.valueOf(sequence))
                        .event("periodic-event")
                        .data("SSE - " + sequence)
                        .build());
    }

}
