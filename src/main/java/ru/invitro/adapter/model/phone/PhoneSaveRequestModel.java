package ru.invitro.adapter.model.phone;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Schema(description = "Модель сохранения сущности 'телефон' в сервисе контактов")
public class PhoneSaveRequestModel {

    /**
     * Номер телефона.
     */
    @NotNull
    @Schema
    private String phoneNumber;

    /**
     * Флаг разрешающий направление СМС с результатми анализов.
     */
    @Schema
    private Boolean allowSmsNotificationsForResultsDelivery = false;

    /**
     * Флаг разрешающий рассылку рекламных сообщений.
     */
    @Schema
    private Boolean allowBulkSms = false;
}
