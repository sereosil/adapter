package ru.invitro.adapter.util;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.invitro.adapter.model.contact.ContactAttribute;
import ru.invitro.adapter.model.document.MetadataModel;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
@Component
@ComponentScan
public class ContactAttributeBuilder {

    public List<ContactAttribute> metadataToAttributeConverter (List<MetadataModel> metadataModels) {
        List<ContactAttribute> attributes = new ArrayList<>();
        for (MetadataModel model : metadataModels) {
            attributes.add(new ContactAttribute(UUID.randomUUID(), ZonedDateTime.now(), model.getDocumentType().getConsentCode(), attributeValueBuilder(model), UUID.fromString(model.getOfficeId())));
        }
        return attributes;
    }

    public String attributeValueBuilder(MetadataModel model) {
        StringBuilder builder = new StringBuilder();
        builder.append("bo:")
                .append(model.getOfficeId().toUpperCase(Locale.ROOT))
                .append(";date:")
                .append(DateTimeFormatter.ofPattern("dd.MM.yyyy").format(LocalDate.now()))
                .append(";consent:")
                .append(model.getDocumentId().toString().toUpperCase(Locale.ROOT))
                .append(";");
        return builder.toString();
    }
}
