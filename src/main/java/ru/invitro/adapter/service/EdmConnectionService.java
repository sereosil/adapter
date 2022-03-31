package ru.invitro.adapter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import ru.invitro.adapter.config.WebClientConfig;

import java.util.UUID;

@Service
public class EdmConnectionService {

    @Autowired
    private WebClientConfig clientConfig;

    //TODO include info message
    public String getConnectionStatus(String clientId) {
        String url = clientConfig.EDM_URL + "contacts/" + clientId + "/connection-status";
        return clientConfig.prepareRequest(url, HttpMethod.GET, MediaType.TEXT_PLAIN_VALUE)
                .retrieve()
                .bodyToMono(String.class).thenReturn("Информационное сообщение с преимуществами подключения")
                .block();
    }

    public String connectToEDM(String clientId,
                               UUID phoneId) {
        String url = clientConfig.EDM_URL + "contacts/" + clientId + "/connect";
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        return clientConfig.prepareRequest(url, HttpMethod.POST, MediaType.APPLICATION_JSON_VALUE)
                .body(BodyInserters.fromMultipartData("personalCabinetPhoneId", phoneId))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
