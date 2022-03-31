package ru.invitro.adapter.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.invitro.adapter.model.phone.CodeRequestDTO;
import ru.invitro.adapter.model.phone.CodeSendRequestDTO;
import ru.invitro.adapter.model.phone.PhoneSaveRequestModel;
import ru.invitro.adapter.service.PhoneService;


@Api(tags = "Phone Controller")
@RestController
@RequestMapping(value = "/phone", produces = "application/json")
public class PhoneController {

    @Autowired
    private PhoneService phoneService;

    @Operation(description = "Формирует запрос на создание телефона контакта в ЛК")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/createPhone/")
    @ResponseBody
    public String createPhone(@RequestBody PhoneSaveRequestModel model,
                              @RequestParam String contactId,
                              @RequestParam String visibilityScope) {
        return phoneService.createPhone(model, contactId, visibilityScope);
    }

    @Operation(description = "Формирует запрос удаление телефона контакта в ЛК")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @DeleteMapping("/deletePhone/")
    @ResponseBody
    public String deletePhone(@RequestParam String contactId,
                              @RequestParam String visibilityScope,
                              @RequestParam String phoneId) {
        return phoneService.deletePhone(contactId, visibilityScope, phoneId);
    }

    @Operation(description = "Формирует запрос на создание телефона контакта в ЛК")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/sendSMSCode/")
    @ResponseBody
    public String sendSMSCode(@RequestBody CodeSendRequestDTO code,
                              @RequestParam String contactId,
                              @RequestParam String visibilityScope) {

        return phoneService.sendSMSCode(code, contactId, visibilityScope);
    }

    @Operation(description = "Формирует запрос в ЛК на направление СМС кода для подтверждения номера телефона.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/validateCode/")
    @ResponseBody
    public String validateCode(@RequestBody CodeRequestDTO code,
                               @RequestParam String contactId,
                               @RequestParam String visibilityScope) {

        return phoneService.validateCode(code, contactId, visibilityScope);
    }
}
