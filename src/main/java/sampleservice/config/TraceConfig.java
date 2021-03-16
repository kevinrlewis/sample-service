package sampleservice.config;

import brave.Span;
import brave.Tracing;
import brave.handler.SpanHandler;
import brave.messaging.MessagingTracing;
import brave.propagation.B3Propagation;
import brave.propagation.Propagation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * A configuration class to initialize beans for the Spring Cloud Sleuth B3 Propagation config.
 * propagationFactory       Sets the format of the headers
 * tracing                  Sets the reporter to noop in order to remove logged trace calls
 * messagingTracing         Builds the messagingTracing object used to create the KafkaProducer
 * */
@Configuration
public class TraceConfig {

    @Bean
    Propagation.Factory propagationFactory() {
        return B3Propagation
                .newFactoryBuilder()
                // Add both X-B3-* headers and the b3 single header
                .injectFormats(Span.Kind.PRODUCER, B3Propagation.Format.MULTI, B3Propagation.Format.SINGLE)
                .injectFormats(Span.Kind.CONSUMER, B3Propagation.Format.MULTI, B3Propagation.Format.SINGLE)
                .build();
    }

    @Bean
    Tracing tracing(Propagation.Factory propagationFactory) {
        return Tracing
                .newBuilder()
                // Remove trace reporter logging
                .addSpanHandler(new SpanHandler() {})
                .propagationFactory(propagationFactory)
                .build();
    }

    @Bean
    MessagingTracing messagingTracing(Tracing tracing) {
        return MessagingTracing
                .newBuilder(tracing)
                .build();
    }
}

