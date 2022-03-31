package ru.invitro.adapter.model.phone;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Бизнес объект телефона.
 */
@Getter
@Setter
public class Phone {

    /**
     * Идентификатор телефона.
     */
    private UUID id;

    /**
     * Номер телефона.
     */
    private String phoneNumber;

    /**
     * Контакт.
     */
    private UUID contactId;

    /**
     * Подтвержденный.
     */
    private Boolean ownershipConfirmed;

    /**
     * Доверенный номер ЭДО.
     */
    private Boolean digSignTrust;

    /**
     * Оповещения о результатах по SMS.
     */
    private Boolean allowSmsNotificationsForResultsDelivery;

    /**
     * Рассылка по SMS.
     */
    private Boolean allowBulkSms;

    /**
     * Проверочный код.
     */
    private String pinCodeValue;

    /**
     * Момент отправки последнего Pin.
     */
    private ZonedDateTime startTime;

}
