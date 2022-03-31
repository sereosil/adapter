package ru.invitro.adapter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import ru.invitro.adapter.config.WebClientConfig;
import ru.invitro.adapter.esb.AttributeEsbEntityConverter;
import ru.invitro.adapter.esb.EntityUploadService;
import ru.invitro.adapter.esb.OperationType;
import ru.invitro.adapter.model.contact.ContactAttribute;
import ru.invitro.adapter.repository.ContactAttributeRepository;
import ru.invitro.adapter.util.ContactAttributeBuilder;
import ru.invitro.armps.integration.esb.remote.contact.Contacts;

@Service
public class EdmConnectionService {

    @Autowired
    private WebClientConfig clientConfig;

    @Autowired
    public EntityUploadService entityUploadService;

    @Autowired
    ContactAttributeRepository contactAttributeRepository;

    //TODO include info message
    public String getConnectionStatus(String clientId) {
        String url = clientConfig.EDM_URL + "contacts/" + clientId + "/connection-status";
        return clientConfig.prepareRequest(url, HttpMethod.GET, MediaType.TEXT_PLAIN_VALUE)
                .retrieve()
                .bodyToMono(String.class).thenReturn("Информационное сообщение с преимуществами подключения")
                .block();
    }

    public String connectToEDM(String clientId,
                               String phoneId,
                               String branchOfficeId) {
        String url = clientConfig.EDM_URL + "contacts/" + clientId + "/connect";
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        String response = clientConfig.prepareRequest(url, HttpMethod.POST, MediaType.APPLICATION_JSON_VALUE)
                .body(BodyInserters.fromMultipartData("personalCabinetPhoneId", phoneId))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        ContactAttribute contactAttribute = ContactAttributeBuilder.INSTANCE.contactAttributeEDMDataConverter(branchOfficeId, clientId);
        contactAttributeRepository.saveAndFlush(contactAttribute);
        Contacts esbContacts = AttributeEsbEntityConverter.INSTANCE.buildEsbEntity(contactAttribute);
        entityUploadService.handlePush(esbContacts, OperationType.UPDATE, true);
        return response;
    }
}
