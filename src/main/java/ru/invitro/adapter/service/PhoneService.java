package ru.invitro.adapter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import ru.invitro.adapter.config.WebClientConfig;
import ru.invitro.adapter.model.phone.CodeRequestDTO;
import ru.invitro.adapter.model.phone.CodeSendRequestDTO;
import ru.invitro.adapter.model.phone.PhoneSaveRequestModel;

@Service
public class PhoneService {

    @Autowired
    private WebClientConfig clientConfig;

    public String createPhone(PhoneSaveRequestModel model,
                              String contactId,
                              String visibilityScope) {
        String url = clientConfig.CONTACTS_URL + "contacts/ + " + contactId + "?visibilityScopeId=" + visibilityScope;
        return clientConfig.prepareRequest(url, HttpMethod.POST, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(model)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }


    public String deletePhone(String contactId,
                              String visibilityScope,
                              String phoneId) {
        String url = clientConfig.CONTACTS_URL + "contacts/ + " + contactId + "/phones/" + phoneId + "?visibilityScopeId=" + visibilityScope;
        return clientConfig.prepareRequest(url, HttpMethod.DELETE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }


    public String sendSMSCode(CodeSendRequestDTO code,
                              String contactId,
                              String visibilityScope) {
        String url = clientConfig.CONTACTS_URL + "contacts/ + " + contactId + "/phones/codes?visibilityScopeId=" + visibilityScope;
        return clientConfig.prepareRequest(url, HttpMethod.POST, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(code)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String validateCode(CodeRequestDTO code,
                               String contactId,
                               String visibilityScope) {
        String url = clientConfig.CONTACTS_URL + "contacts/ + " + contactId + "/phones/validate?visibilityScopeId=" + visibilityScope;
        return clientConfig.prepareRequest(url, HttpMethod.POST, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(code)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}


