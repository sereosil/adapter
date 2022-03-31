package ru.invitro.adapter.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.invitro.adapter.model.exception.DocumentNotFoundException;
import ru.invitro.adapter.model.exception.MissingEntityException;
import ru.invitro.adapter.service.SignatureService;

import java.io.IOException;


@Api(tags = "Signature Controller")
@RestController
@RequestMapping(value = "/adapter", produces = "text/plain")
public class SignatureController {

    @Autowired
    SignatureService signatureService;

    /**
     * Формирует запрос на подписание документов в формате JSON и передает
     * его для подписания в сервис ЭДО. Принимает список идентификаторов документов в кэше и язык пользовательского интерфейса.
     * Возвращает идентификатор пакета переданных документов.
     */

    @Operation(description = "Направить документы на подписание в ЭДО")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = String.class))),
    })
    @PostMapping("/sendSignRequest")
    @ResponseBody
    public Object sendSignRequest(@RequestParam String[] fileNames, @RequestParam String interfaceLanguage) throws IOException, DocumentNotFoundException {
        return signatureService.sendSignRequest(fileNames, interfaceLanguage);
    }

    /**
     * Формирует запрос на подписание документов в формате JSON и передает
     * его для подписания в сервис ЭДО. Возвращает идентификатор пакеты переданных документов.
     */

    @Operation(description = "Направить документы на подписание в ЭДО")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/signDocuments")
    @ResponseBody
    public String signDocuments(@RequestParam String confirmationCode,
                                @RequestParam String packageId) throws MissingEntityException {
        return signatureService.signDocuments(confirmationCode, packageId) ;
    }
}

