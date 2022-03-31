package ru.invitro.adapter.model.contact;

import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import ru.invitro.armps.web.model.contact.edit.AbstractContactEditModel;
import ru.invitro.armps.web.model.contact.edit.ContactAddressEditModel;
import ru.invitro.armps.web.model.contact.edit.ContactDocumentEditModel;
import ru.invitro.armps.web.validator.Server;
import ru.invitro.armps.web.validator.contact.edit.constraints.AddressUniqueness;
import ru.invitro.armps.web.validator.contact.edit.constraints.ContactDocumentUniqueness;
import ru.invitro.armps.web.validator.contact.edit.constraints.Email;
import ru.invitro.armps.web.validator.contact.edit.constraints.PhoneValid;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@AddressUniqueness
@ContactDocumentUniqueness
@Getter
@Setter
@ApiModel(description = "Модель данных контакта для отображения информации (в UI) редактирования контакта.")
public class ContactEditModel extends AbstractContactEditModel {

    /**
     * Идентификатор контакта
     */
    private String contactId;

    /**
     * Является ли контакт анонимным
     */
    private boolean isAnonymous;

    /**
     * НСС
     */
    private String nss;

    /**
     * Имя на русском
     */
    @NotBlank
    @Size(max = 50)
    private String firstName;

    /**
     * Фамилия на русском
     */
    @NotBlank
    @Size(max = 50)
    private String lastName;

    /**
     * Отчество на русском
     */
    @Size(max = 50)
    private String middleName;

    /**
     * Имя на английском
     */
    @Size(max = 50)
    private String firstNameEn;

    /**
     * Фамилия на английском
     */
    @Size(max = 50)
    private String lastNameEn;

    /**
     * Отчество на английском
     */
    @Size(max = 50)
    private String middleNameEn;

    /**
     * Дата рождения
     */
    @Valid
    private String birthday;

    /**
     * Номер мобильного телефона
     */
    @Size(max = 20)
    @PhoneValid(groups = {Server.class})
    private String mobilePhone;

    /**
     * Номер мобильного телефона
     */
    @Size(max = 20)
    @PhoneValid(groups = {Server.class})
    private String mobilePhone2;

    /**
     * Адрес электронной почты
     */
    @Size(max = 255)
    @Email(groups = {Server.class})
    private String email;

    /**
     * Дополнительный адрес электронной почты 1
     */
    @Size(max = 255)
    @Email(groups = {Server.class})
    private String email2;

    /**
     * Дополнительный адрес электронной почты 2
     */
    @Size(max = 255)
    @Email(groups = {Server.class})
    private String email3;

    /**
     * Номер мед. карты
     */
    @Size(max = 32)
    private String medicalNumber;

    /**
     * Статус сотрудника
     */
    private Boolean isEmployee;

    /**
     * Дата окончания испытательного срока
     */
    private String probabilityPeriodEndDate;

    /**
     * Описание
     */
    private String description;

    /**
     * Пол
     */
    private String gender;

    /**
     * Кодовое слово
     */
    @Size(max = 30)
    private String codeWord;

    /**
     * Место работы/учебы
     */
    @Size(max = 255)
    private String educationOrWorkPlace;

    /**
     * Адрес работы/учебы
     */
    @Size(max = 255)
    private String educationOrWorkAddress;

    /**
     * Должность
     */
    @Size(max = 255)
    private String position;

    /**
     * Список документов контакта
     */
    @Valid
    @Setter(AccessLevel.NONE)
    private List<ContactDocumentEditModel> contactDocuments = new ArrayList<>();

    /**
     * Список адресов контакта
     */
    @Valid
    @Setter(AccessLevel.NONE)
    private List<ContactAddressEditModel> addresses = new ArrayList<>();

    @Override
    public String getId() {
        return contactId;
    }

    @Override
    public String toString() {
        return "ContactEditModel{" +
                "contactId='" + contactId + '\'' +
                ", isAnonymous=" + isAnonymous +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", firstNameEn='" + firstNameEn + '\'' +
                ", lastNameEn='" + lastNameEn + '\'' +
                ", middleNameEn='" + middleNameEn + '\'' +
                ", birthdayDate='" + birthday + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", email='" + email + '\'' +
                ", email2='" + email2 + '\'' +
                ", email3='" + email3 + '\'' +
                ", description='" + description + '\'' +
                ", genderType=" + gender +
                ", codeWord='" + codeWord + '\'' +
                ", educationOrWorkPlace='" + educationOrWorkPlace + '\'' +
                ", educationOrWorkAddress='" + educationOrWorkAddress + '\'' +
                ", position='" + position + '\'' +
                ", contactDocuments=" + contactDocuments +
                ", addresses=" + addresses +
                '}';
    }
}