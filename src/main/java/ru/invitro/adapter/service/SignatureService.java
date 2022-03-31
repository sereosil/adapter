package ru.invitro.adapter.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import ru.invitro.adapter.config.WebClientConfig;
import ru.invitro.adapter.esb.AttributeEsbEntityConverter;
import ru.invitro.adapter.esb.EntityUploadService;
import ru.invitro.adapter.esb.OperationType;
import ru.invitro.adapter.model.contact.ContactAttribute;
import ru.invitro.adapter.model.document.MetadataModel;
import ru.invitro.adapter.model.exception.DocumentNotFoundException;
import ru.invitro.adapter.model.exception.MissingEntityException;
import ru.invitro.adapter.repository.ContactAttributeRepository;
import ru.invitro.adapter.util.ContactAttributeBuilder;
import ru.invitro.adapter.util.DocumentCache;
import ru.invitro.adapter.util.MetadataReader;
import ru.invitro.armps.integration.esb.remote.contact.Contacts;
import ru.invitro.filestore.client.FileStoreRestClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

@Service
public class SignatureService {

    private final FileStoreRestClient fileStoreRestClient = new FileStoreRestClient("edo", new RestTemplate(), new ObjectMapper());

    @Autowired
    private WebClientConfig clientConfig;

    @Autowired
    public EntityUploadService entityUploadService;

    @Autowired
    DocumentCache documentCache;

    @Autowired
    ContactAttributeRepository contactAttributeRepository;

    @Autowired
    ContactAttributeBuilder contactAttributeBuilder;

    public String sendSignRequest(String[] fileNames, String interfaceLanguage) throws IOException, DocumentNotFoundException, WebClientResponseException {
        List<byte[]> fileBytes = documentCache.getByteList(fileNames);
        MultipartBodyBuilder builder = prepareBuilder(fileBytes);
        MetadataModel entity = MetadataReader.getMetadata(fileBytes.get(0));
        builder.part("Language", interfaceLanguage);
        builder.part("ContactEsbId", entity.getContactId());
        builder.part("OfficeEsbId", entity.getOfficeId());

        List<ContactAttribute> contactAttributeList = contactAttributeBuilder.metadataToAttributeConverter(MetadataReader.getMetadataList(fileBytes));
        String url = clientConfig.EDM_URL + "signature-requests";
        String response = clientConfig.prepareRequest(url, HttpMethod.POST, MediaType.MULTIPART_FORM_DATA_VALUE)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        for (ContactAttribute contactAttribute : contactAttributeList) {
            contactAttribute.setPackageId(response);
            contactAttributeRepository.saveAndFlush(contactAttribute);
        }
        return response;
    }

    public String signDocuments(String confirmationCode,
                                String packageId) throws MissingEntityException {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("confirmationCode", confirmationCode);
        String url = clientConfig.EDM_URL + "signature-requests/" + packageId + "/confirm";
        String response = clientConfig.prepareRequest(url, HttpMethod.POST, MediaType.APPLICATION_JSON_VALUE)
                .body(BodyInserters.fromMultipartData("confirmationCode", confirmationCode))
                .retrieve()
                .bodyToMono(String.class)
                .block();
       List<ContactAttribute> contactAttributeList = contactAttributeRepository
                .findAllByPackageId(response)
                .orElseThrow(MissingEntityException::new);
        Contacts esbContacts = AttributeEsbEntityConverter.INSTANCE.buildEsbEntity(contactAttributeList);
        entityUploadService.handlePush(esbContacts, OperationType.UPDATE, true);
        return response;
    }


    private MultipartBodyBuilder prepareBuilder(List<byte[]> fileBytes) throws IOException {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        for (byte[] bytes : fileBytes) {
            String fileName = UUID.randomUUID() + ".pdf";
            OutputStream out = new FileOutputStream(fileName);
            out.write(bytes);
            out.close();
            String fileNames = storeDocument(fileName);
            builder.part("URLs", fileNames);
        }
        return builder;
    }

    private String storeDocument(String fileName) {
        String docURL = fileStoreRestClient.store(new File(fileName)).getUrl().getDefaultURL();
        System.out.println("URL IS " + docURL);
        return docURL;
    }

}
