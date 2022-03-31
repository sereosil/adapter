package ru.invitro.adapter.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
@Schema(name = "ContactCreateRequest", description = "Сущность для создания контакта")
public class ContactCreateRequestDTO {

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
     * Пол.
     */
    @Schema(description = "Пол")
    @JsonProperty("gender")
    @NotNull
    private String gender;

    /**
     * Регион.
     */
    @Schema(description = "Регион")
    @JsonProperty("regionId")
    @NotNull
    private UUID regionId;

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
    private Optional<@Size(max = 50) String> lastNameEN;

    /**
     * Имя (EN).
     */
    @Schema(implementation = String.class, description = "Имя (EN)", maxLength = 50)
    @JsonProperty("firstNameEN")
    private Optional<@Size(max = 50) String> firstNameEN;

    /**
     * Отчество (EN).
     */
    @Schema(implementation = String.class, description = "Отчество (EN)", maxLength = 50)
    @JsonProperty("middleNameEN")
    private Optional<@Size(max = 50) String> middleNameEN;

    /**
     * Код клиента.
     */
    @Schema(implementation = String.class, description = "Код клиента", maxLength = 30)
    @JsonProperty("clientCode")
    private Optional<@Size(max = 30) String> clientCode;

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
    private Optional<@Size(max = 50) String> codeWord;

    /**
     * Врач.
     */
    @Schema(implementation = Boolean.class, description = "Врач")
    @JsonProperty("doctor")
    private Optional<Boolean> doctor;

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

    @JsonProperty("createdIn")
    private String createdIn;

    /**
     * Место работы/учебы.
     */
    @Schema(implementation = String.class, description = "Место работы/учебы", maxLength = 255)
    @JsonProperty("educationOrWorkPlace")
    private Optional<@Size(max = 255) String> educationOrWorkPlace;

    /**
     * Адрес работы/учебы.
     */
    @Schema(implementation = String.class, description = "Адрес работы/учебы", maxLength = 255)
    @JsonProperty("educationOrWorkAddress")
    private Optional<@Size(max = 255) String> educationOrWorkAddress;

    /**
     * Должность.
     */
    @Schema(implementation = String.class, description = "Должность", maxLength = 255)
    @JsonProperty("position")
    private Optional<@Size(max = 255) String> position;

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
        return "ContactCreateRequestDTO{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthDate=" + birthDate +
                ", gender='" + gender + '\'' +
                ", regionId=" + regionId +
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

