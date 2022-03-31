package ru.invitro.adapter.model.document;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * Enum, ассоциирующий типы входящих документов с их типами для передачи в шину
 */
@Getter
@AllArgsConstructor
public enum DocumentType implements Serializable {
    /**
     * Подписание заявления на подключение к системе электронного документооборота
     */
    EDM_CONSENT_TYPE("341"),
    /**
     * Подписание заявления на отключение от системы электронного документооборота
     */
    EDM_DENIAL_TYPE("342"),
    /**
     * Подписание заявления на изменение персональных данных
     */
    EDM_PERSONAL_DATA_CHANGE_TYPE("343"),
    /**
     * Подписание заявления на отзыв персональных данных
     */
    EDM_PERSONAL_DATA_WAIVER_TYPE("344"),
    /**
     * Подписание заявления на возврат денежных средств
     */
    EDM_REFUND_TYPE("345");

    /**
     * Код документа для передачи в шину
     */
    private final String consentCode;

}
