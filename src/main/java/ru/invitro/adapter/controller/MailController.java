package ru.invitro.adapter.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.invitro.adapter.model.exception.CustomApiException;
import ru.invitro.adapter.model.mail.MailSaveRequestModel;
import ru.invitro.adapter.service.MailService;

@Api(tags = "Mail Controller")
@RestController
@RequestMapping(value = "/mail", produces = "text/plain")
public class MailController {

    @Autowired
    private MailService mailService;

    @Operation(description = "Формирует запрос на создание электронной почты контакта в ЛК")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/createMail/")
    @ResponseBody
    public String createMail(@RequestBody MailSaveRequestModel model,
                              @RequestParam String contactId,
                              @RequestParam String visibilityScope) {
        return mailService.createMail(model, contactId, visibilityScope);
    }

    @Operation(description = "Формирует запрос удаление почты контакта в ЛК")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "409", description = "Api Error", content = @Content(schema = @Schema(implementation = CustomApiException.class)))
    })
    @DeleteMapping("/deleteMail/")
    @ResponseBody
    public String deleteMail(@RequestParam String contactId,
                              @RequestParam String visibilityScope,
                              @RequestParam String emailId) {
        return mailService.deleteMail(contactId, visibilityScope, emailId);
    }
}
