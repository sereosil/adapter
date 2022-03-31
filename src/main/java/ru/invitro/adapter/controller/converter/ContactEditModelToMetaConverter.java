package ru.invitro.adapter.controller.converter;


import ru.invitro.adapter.model.contact.ContactEditModel;
import ru.invitro.adapter.model.dto.ContactEditRequestDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

public class ContactEditModelToMetaConverter {

    public static final ContactEditModelToMetaConverter INSTANCE = new ContactEditModelToMetaConverter();

    public ContactEditRequestDTO convert(ContactEditModel model, String regionId) {
        ContactEditRequestDTO contactEdit = new ContactEditRequestDTO();
        contactEdit.setFirstName(model.getFirstName());
        contactEdit.setLastName(model.getLastName());
        contactEdit.setMiddleName(model.getMiddleName());
        contactEdit.setGender(model.getGender());
        contactEdit.setBirthDate(convertDate(model.getBirthday()));
        contactEdit.setRegionId(UUID.fromString(regionId));
        contactEdit.setCodeWord(Optional.ofNullable(model.getCodeWord()));
        contactEdit.setAnonymous(Optional.of(model.isAnonymous()));

        contactEdit.setLastNameEN(Optional.ofNullable(model.getLastNameEn()));
        contactEdit.setFirstNameEN(Optional.ofNullable(model.getFirstNameEn()));
        contactEdit.setMiddleNameEN(Optional.ofNullable(model.getMiddleNameEn()));

        contactEdit.setEducationOrWorkPlace(Optional.ofNullable(model.getEducationOrWorkPlace()));
        contactEdit.setEducationOrWorkAddress(Optional.ofNullable(model.getEducationOrWorkAddress()));
        contactEdit.setPosition(Optional.ofNullable(model.getPosition()));

        return contactEdit;
    }


    public LocalDate convertDate(String dateToConvert) {
        return dateToConvert == null ? null : LocalDate.parse(dateToConvert, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
