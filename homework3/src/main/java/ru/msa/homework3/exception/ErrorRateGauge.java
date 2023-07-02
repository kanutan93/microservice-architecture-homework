package ru.msa.homework3.exception;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
public class ErrorRateGauge {

  private static final String ERROR_RATE_COUNTER = "error_rate_counter";
  private static final String METHOD = "method";
  private static final String URI = "uri";

  private final MeterRegistry meterRegistry;
  private final AtomicInteger stateObject = new AtomicInteger(0);

  public void increment(String method, String uri) {
    Tag methodTag = Tag.of(METHOD, method);
    Tag uriTag = Tag.of(URI, uri);
    List<Tag> tags = List.of(methodTag, uriTag);

    stateObject.getAndIncrement();
    meterRegistry
        .gauge(ERROR_RATE_COUNTER, tags, this.stateObject, it -> it.getAndSet(0));
  }
}
