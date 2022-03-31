package ru.invitro.adapter.controller.converter;

import ru.invitro.adapter.model.dto.ContactCreateRequestDTO;
import ru.invitro.adapter.model.contact.ContactEditModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

public class ContactEditModelToContactCreateDTOConverter {

    public static final ContactEditModelToContactCreateDTOConverter INSTANCE = new ContactEditModelToContactCreateDTOConverter();

    public ContactCreateRequestDTO convert(ContactEditModel model, String regionId) {
        ContactCreateRequestDTO requestDTO = new ContactCreateRequestDTO();
        requestDTO.setFirstName(model.getFirstName());
        requestDTO.setLastName(model.getLastName());
        requestDTO.setMiddleName(model.getMiddleName());
        requestDTO.setGender(model.getGender());
        requestDTO.setBirthDate(convertDate(model.getBirthday()));
        requestDTO.setRegionId(UUID.fromString(regionId));
        requestDTO.setCodeWord(Optional.ofNullable(model.getCodeWord()));
        requestDTO.setAnonymous(Optional.of(model.isAnonymous()));

        requestDTO.setLastNameEN(Optional.ofNullable(model.getLastNameEn()));
        requestDTO.setFirstNameEN(Optional.ofNullable(model.getFirstNameEn()));
        requestDTO.setMiddleNameEN(Optional.ofNullable(model.getMiddleNameEn()));

        requestDTO.setEducationOrWorkPlace(Optional.ofNullable(model.getEducationOrWorkPlace()));
        requestDTO.setEducationOrWorkAddress(Optional.ofNullable(model.getEducationOrWorkAddress()));
        requestDTO.setPosition(Optional.ofNullable(model.getPosition()));

        return requestDTO;
    }


    public LocalDate convertDate(String dateToConvert) {
        return dateToConvert == null ? null : LocalDate.parse(dateToConvert, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
