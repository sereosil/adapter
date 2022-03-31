package ru.invitro.adapter.model.phone;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Запрос на отправку кода подтверждения пользователю.
 */
@Getter
@Setter
@Schema(name = "CodeSendRequest", description = "Запрос на отправку кода подтверждения пользователю")
public class CodeSendRequestDTO {

    /**
     * Телефон.
     */
    @NotNull
    private String phoneNumber;

}
