package ru.invitro.adapter.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import ru.invitro.armps.database.entities.api.ContactCreatedInType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Сущность для редактирования контакта.
 */
@Getter
@Setter
@Schema(name = "ContactEditRequest", description = "Сущность для редактирования контакта")
public class ContactEditRequestDTO {

    /**
     * Фамилия.
     */
    @Schema(description = "Фамилия")
    @JsonProperty("lastName")
    @NotBlank
    @Size(max = 50, message = "{validation.field.length}")
    private String lastName;

    /**
     * Имя.
     */
    @Schema(description = "Имя")
    @JsonProperty("firstName")
    @NotBlank
    @Size(max = 50, message = "{validation.field.length}")
    private String firstName;

    /**
     * Отчество.
     */
    @Schema(description = "Отчество")
    @JsonProperty("middleName")
    @Size(max = 50, message = "{validation.field.length}")
    private String middleName;

    /**
     * Дата рождения.
     */
    @Schema(description = "Дата рождения")
    @JsonProperty("birthDate")
    @NotNull
    private LocalDate birthDate;

    /**
     * Телефон ESB.
     */
    @Schema(description = "Телефон ESB")
    @JsonProperty("phoneESB")
    private String phoneESB;

    /**
     * Второй Телефон ESB.
     */
    @Schema(description = "Второй Телефон ESB")
    @JsonProperty("phoneESB2")
    private String phoneESB2;

    /**
     * Электронная почта ESB.
     */
    @Schema(description = "Электронная почта ESB")
    @JsonProperty("emailESB")
    private String emailESB;

    /**
     * Вторая электронная почта ESB.
     */
    @Schema(description = "Вторая электронная почта ESB")
    @JsonProperty("emailESB2")
    private String emailESB2;

    /**
     * Третья электронная почта ESB.
     */
    @Schema(description = "Третья электронная почта ESB")
    @JsonProperty("emailESB3")
    private String emailESB3;

    /**
     * Пол.
     */
    @Schema(description = "Пол")
    @JsonProperty("gender")
    @NotNull
    private String gender;

    /**
     * Статус Сотрудник.
     */
    @Schema(implementation = Boolean.class, description = "Статус Сотрудник")
    @JsonProperty("isEmployee")
    private Optional<Boolean> employee;

    /**
     * Дата завершения испытательного срока.
     */
    @Schema(implementation = LocalDate.class, description = "Дата завершения испытательного срока")
    @JsonProperty("pped")
    private Optional<LocalDate> probabilityPeriodEndDate;

    /**
     * Дата регистрации.
     */
    @Schema(implementation = ZonedDateTime.class, description = "Дата регистрации")
    @JsonProperty("registrationDate")
    private Optional<ZonedDateTime> registrationDate;

    /**
     * Фамилия (EN).
     */
    @Schema(implementation = String.class, description = "Фамилия (EN)", maxLength = 50)
    @JsonProperty("lastNameEN")
    private Optional<String> lastNameEN;

    /**
     * Имя (EN).
     */
    @Schema(implementation = String.class, description = "Имя (EN)", maxLength = 50)
    @JsonProperty("firstNameEN")
    private Optional<String> firstNameEN;

    /**
     * Отчество (EN).
     */
    @Schema(implementation = String.class, description = "Отчество (EN)", maxLength = 50)
    @JsonProperty("middleNameEN")
    private Optional<String> middleNameEN;

    /**
     * Код клиента.
     */
    @Schema(implementation = String.class, description = "Код клиента", maxLength = 30)
    @JsonProperty("clientCode")
    private Optional<String> clientCode;

    /**
     * Предыдущие коды клиентов.
     */
    @Schema(implementation = String.class, description = "Предыдущие коды клиентов")
    @JsonProperty("previousClientCodes")
    private Optional<String> previousClientCodes;

    /**
     * Кодовое слово.
     */
    @Schema(implementation = String.class, description = "Кодовое слово", maxLength = 50)
    @JsonProperty("codeWord")
    private Optional<String> codeWord;

    /**
     * Врач.
     */
    @Schema(implementation = Boolean.class, description = "Врач")
    @JsonProperty("doctor")
    private Optional<Boolean> doctor;

    /**
     * Код региона
     */
    @Schema(implementation = Boolean.class, description = "Код региона")
    @JsonProperty("regionId")
    private UUID regionId;

    /**
     * Анонимный.
     */
    @Schema(implementation = Boolean.class, description = "Анонимный")
    @JsonProperty("anonymous")
    private Optional<Boolean> anonymous;

    /**
     * Отображать врача на бланке.
     */
    @Schema(implementation = Boolean.class, description = "Отображать врача на бланке")
    @JsonProperty("displayDoctorOnBlank")
    private Optional<Boolean> displayDoctorOnBlank;

    /**
     * Отображать на бланке код вместо ФИО.
     */
    @Schema(implementation = Boolean.class, description = "Отображать на бланке код вместо ФИО")
    @JsonProperty("displayCodeOnBlank")
    private Optional<Boolean> displayCodeOnBlank;

    /**
     * Система Источник.
     */
    @Schema(implementation = ContactCreatedInType.class, description = "Система Источник")
    @JsonProperty("createdIn")
    private String createdIn;

    /**
     * Место работы/учебы.
     */
    @Schema(implementation = String.class, description = "Место работы/учебы", maxLength = 255)
    @JsonProperty("educationOrWorkPlace")
    private Optional<String> educationOrWorkPlace;

    /**
     * Адрес работы/учебы.
     */
    @Schema(implementation = String.class, description = "Адрес работы/учебы", maxLength = 255)
    @JsonProperty("educationOrWorkAddress")
    private Optional<String> educationOrWorkAddress;

    /**
     * Должность.
     */
    @Schema(implementation = String.class, description = "Должность", maxLength = 255)
    @JsonProperty("position")
    private Optional<String> position;

    /**
     * Место регистрации.
     */
    @Schema(implementation = UUID.class, description = "Место регистрации")
    @JsonProperty("regMO")
    private Optional<UUID> regMO;

    /**
     * Город.
     */
    @Schema(implementation = UUID.class, description = "Город")
    @JsonProperty("city")
    private Optional<UUID> city;

    @Override
    public String toString() {
        return "ContactEditRequestDTO{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthDate=" + birthDate +
                ", phoneESB='" + phoneESB + '\'' +
                ", phoneESB2='" + phoneESB2 + '\'' +
                ", emailESB='" + emailESB + '\'' +
                ", emailESB2='" + emailESB2 + '\'' +
                ", emailESB3='" + emailESB3 + '\'' +
                ", gender='" + gender + '\'' +
                ", employee=" + employee +
                ", probabilityPeriodEndDate=" + probabilityPeriodEndDate +
                ", registrationDate=" + registrationDate +
                ", lastNameEN=" + lastNameEN +
                ", firstNameEN=" + firstNameEN +
                ", middleNameEN=" + middleNameEN +
                ", clientCode=" + clientCode +
                ", previousClientCodes=" + previousClientCodes +
                ", codeWord=" + codeWord +
                ", doctor=" + doctor +
                ", anonymous=" + anonymous +
                ", displayDoctorOnBlank=" + displayDoctorOnBlank +
                ", displayCodeOnBlank=" + displayCodeOnBlank +
                ", createdIn='" + createdIn + '\'' +
                ", educationOrWorkPlace=" + educationOrWorkPlace +
                ", educationOrWorkAddress=" + educationOrWorkAddress +
                ", position=" + position +
                ", regMO=" + regMO +
                ", city=" + city +
                '}';
    }
}
