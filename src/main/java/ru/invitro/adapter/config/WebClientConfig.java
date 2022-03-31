package ru.invitro.adapter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.invitro.adapter.model.exception.CustomApiException;

import java.time.ZonedDateTime;

@Component
public class WebClientConfig {

    public final WebClient INSTANCE = WebClient.builder().filter(errorResponseHandler()).build();

    @Value("${ru.invitro.edm.service.url}")
    public String EDM_URL;
    @Value("${ru.invitro.contact.service.url}")
    public String CONTACTS_URL;

    public WebClient.RequestBodySpec prepareRequest(String url, HttpMethod method, String contentHeader) {
        return INSTANCE.method(method)
                .uri(url)
                .header(HttpHeaders.CONTENT_TYPE, contentHeader)
                .accept(MediaType.APPLICATION_JSON)
                .ifModifiedSince(ZonedDateTime.now());
    }

    public static ExchangeFilterFunction errorResponseHandler() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            if (clientResponse.statusCode().isError()) {
                System.out.println("Error code : " + clientResponse.statusCode() + " Reason " + clientResponse.bodyToMono(String.class));
                return clientResponse.bodyToMono(ErrorBody.class).flatMap(errorBody -> Mono.error(new CustomApiException(errorBody.message)));
            } else {
                System.out.println("Error code in non error : " + clientResponse.statusCode() + " Reason " + clientResponse.bodyToMono(String.class));
                return Mono.just(clientResponse);
            }
        });
    }

    public static class ErrorBody {
        private String message;
        private String httpCode;

        public ErrorBody() {
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
