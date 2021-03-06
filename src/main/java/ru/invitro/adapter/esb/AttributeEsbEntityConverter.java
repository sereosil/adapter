package ru.invitro.adapter.esb;

import ru.invitro.adapter.model.contact.ContactAttribute;
import ru.invitro.armps.integration.esb.publisher.converter.entity.factory.HeaderFactory;
import ru.invitro.armps.integration.esb.publisher.converter.entity.util.EsbStringAssigner;
import ru.invitro.armps.integration.esb.remote.contact.AddAttributesContact;
import ru.invitro.armps.integration.esb.remote.contact.Contacts;
import ru.invitro.armps.integration.esb.remote.contact.ObjectFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Конвертер, собирающий из атрибутов контакта сущность Contacts для передачи по шине.
 */
public class AttributeEsbEntityConverter {

    private static final ObjectFactory FACTORY = new ObjectFactory();

    public static final AttributeEsbEntityConverter INSTANCE = new AttributeEsbEntityConverter();

    public Contacts buildEsbEntity(final List<ContactAttribute> attributes) {
        List<AddAttributesContact> esbAttributes = convertAll(attributes);
        final Contacts esbContacts = FACTORY.createContacts();
        esbContacts.setPackageHeader(HeaderFactory.create());
        for (AddAttributesContact attributesContact : esbAttributes) {
            esbContacts.getAddAttributesContact().add(attributesContact);
        }
        return esbContacts;
    }

    public Contacts buildEsbEntity(ContactAttribute attribute) {
        AddAttributesContact esbAttribute = convert(attribute);
        final Contacts esbContacts = FACTORY.createContacts();
        esbContacts.setPackageHeader(HeaderFactory.create());
        esbContacts.getAddAttributesContact().add(esbAttribute);
        return esbContacts;
    }

    public List<AddAttributesContact> convertAll(List<ContactAttribute> attributes) {
        List<AddAttributesContact> esbAttributes = new ArrayList<>();
        for (ContactAttribute attribute : attributes) {
            esbAttributes.add(convert(attribute));
        }
        return esbAttributes;
    }

    public AddAttributesContact convert(ContactAttribute attribute) {
        AddAttributesContact esbContactProperty = new AddAttributesContact();
        esbContactProperty.setContactID(
                FACTORY.createAddAttributesContactContactID(EsbTypesConversionUtils.convert(attribute.getContact()))
        );
        esbContactProperty.setDataType(
                FACTORY.createAddAttributesContactDataType(EsbTypesConversionUtils.convertPickList(attribute.getType()))
        );
        final AddAttributesContact.Name name = FACTORY.createAddAttributesContactName();
        name.setValue(attribute.getName());
        esbContactProperty.setName(FACTORY.createAddAttributesContactName(name));
        final AddAttributesContact.Value value = FACTORY.createAddAttributesContactValue();
        EsbStringAssigner.setEsbString(value, attribute.getValue());
        esbContactProperty.setValue(FACTORY.createAddAttributesContactValue(value));
        return esbContactProperty;
    }
}


