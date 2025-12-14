package com.example.ai_integration_builder.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Map;

@Service
public class GenericApiService {
    private final WebClient webClient;

    public GenericApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public String callApi(String url, Map<String, String> headers) {
        return webClient.get()
                .uri(url)
                .headers(h -> h.setAll(headers))
                .retrieve()
                .bodyToMono(String.class)
                .retryWhen(
                        Retry.backoff(3, Duration.ofSeconds(2))
                                .filter(this::isRetryable)
                )
                .block();
    }

    private boolean isRetryable(Throwable throwable) {
        return throwable instanceof WebClientResponseException ex &&
                (ex.getStatusCode().is5xxServerError()
                        || ex.getStatusCode().value() == 429);
    }
}
