package ru.invitro.adapter.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.itextpdf.text.pdf.PdfReader;
import ru.invitro.adapter.model.document.DocumentType;
import ru.invitro.adapter.model.document.MetadataModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MetadataReader {

    //TODO change docType
    public static MetadataModel getMetadata(byte[] documentBytes) throws IOException {
        PdfReader reader = new PdfReader(documentBytes);
        Map<String, String> info = reader.getInfo();
        JsonNode node = new ObjectMapper().readValue(info.get("Subject"), ObjectNode.class);
        ObjectMapper mapper = new ObjectMapper();
        String contactId = node.findValue("participants").findPath("persons").findPath("id").asText();
        String officeId = node.findValue("office").findPath("id").asText();
        String documentType = node.findValue("documentType").asText();
        UUID documentId = UUID.fromString(node.findValue("id").asText());
        //return new MetadataModel(contactId, officeid, DocumentType.valueOf("documentType"));
        return new MetadataModel(contactId, officeId, DocumentType.EDM_CONSENT_TYPE, documentId);
    }

    public static List<MetadataModel> getMetadataList(List<byte[]> documentBytes) throws IOException {
        List<MetadataModel> metadataModelList = new ArrayList<>();
        for (byte[] bytes : documentBytes) {
            MetadataModel model = getMetadata(bytes);
            metadataModelList.add(model);
        }
        return metadataModelList;
    }

}
