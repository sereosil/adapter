package ru.invitro.adapter.model.phone;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PhoneSaveRequestModel {

    /**
     * Номер телефона.
     */
    @NotNull
    private String phoneNumber;

    private Boolean allowSmsNotificationsForResultsDelivery = false;

    private Boolean allowBulkSms = false;
}
