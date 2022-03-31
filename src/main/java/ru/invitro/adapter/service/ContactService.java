package ru.invitro.adapter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import ru.invitro.adapter.config.WebClientConfig;
import ru.invitro.adapter.controller.converter.ContactEditModelToContactCreateDTOConverter;
import ru.invitro.adapter.controller.converter.ContactEditModelToMetaConverter;
import ru.invitro.adapter.model.contact.ContactEditModel;
import ru.invitro.adapter.model.dto.ContactCreateRequestDTO;
import ru.invitro.adapter.model.dto.ContactEditRequestDTO;

@Service
public class ContactService {


    @Autowired
    private WebClientConfig clientConfig;

    public String createContact(ContactEditModel model,
                                String visibilityScope,
                                String regionId) {
        ContactCreateRequestDTO requestDTO = ContactEditModelToContactCreateDTOConverter.INSTANCE.convert(model, regionId);
        String url = clientConfig.CONTACTS_URL + "contacts?visibilityScopeId=" + visibilityScope;
        return clientConfig.prepareRequest(url, HttpMethod.POST, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(requestDTO)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String editContact(ContactEditModel model,
                              String visibilityScope,
                              String regionId,
                              String contactId) {
        ContactEditRequestDTO contactEditRequestDTO = ContactEditModelToMetaConverter.INSTANCE.convert(model, regionId);
        String url = clientConfig.CONTACTS_URL + "contacts/ + " + contactId + "?visibilityScopeId=" + visibilityScope;
        return clientConfig.prepareRequest(url, HttpMethod.PUT, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(contactEditRequestDTO)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
