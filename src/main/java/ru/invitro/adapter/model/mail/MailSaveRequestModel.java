package ru.invitro.adapter.model.mail;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Schema(description = "Объект для сохранения данных об изменении электронной почты")
public class MailSaveRequestModel {

    /**
     * Адрес электронной почты.
     */
    @NotNull
    @Schema(description = "Адрес электронной почты")
    private String email;

    @Schema(description = "Флаг, указывающий на возможность направления результатов по почте")
    private Boolean allowResultsDelivery = false;

}
