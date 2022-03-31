package ru.invitro.adapter.model.phone;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Запрос на проверку кода подтверждения.
 */
@Getter
@Setter
@Schema(name = "CodeRequest", description = "Запрос на проверку кода подтверждения")
public class CodeRequestDTO {

    /**
     * Код
     */
    @NotEmpty
    @Schema(description = "Код")
    private String code;

    /**
     * Телефон.
     */
    @NotNull
    @Schema(description = "Телефон")
    private String phoneNumber;

    /**
     * ID рабочего места.
     */
    @NotNull
    @Schema(description = "ID рабочего места")
    private String workplaceId;

    /**
     * Имя сотрудника, проводящего процедуру подтверждения номера.
     */
    @NotNull
    @Schema(description = "Имя сотрудника, проводящего процедуру подтверждения номера")
    private String userName;


}
