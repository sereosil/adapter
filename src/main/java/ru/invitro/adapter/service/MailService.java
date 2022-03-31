package ru.invitro.adapter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import ru.invitro.adapter.config.WebClientConfig;
import ru.invitro.adapter.model.mail.MailSaveRequestModel;

@Service
public class MailService {

    @Autowired
    private WebClientConfig clientConfig;

    public String createMail(MailSaveRequestModel model,
                             String contactId,
                             String visibilityScopeId) {
        String url = clientConfig.LK3_URL + contactId + "/emails?" + "visibilityScopeId=" + visibilityScopeId;
        return clientConfig.prepareRequest(url, HttpMethod.POST, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(model)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String deleteMail(String contactId,
                             String visibilityScopeId,
                             String emailId) {
        String url = clientConfig.LK3_URL  + contactId + "/emails/" + emailId + "?visibilityScopeId=" + visibilityScopeId;
        return clientConfig.prepareRequest(url, HttpMethod.DELETE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
