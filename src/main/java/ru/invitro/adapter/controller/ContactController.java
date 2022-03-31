package ru.invitro.adapter.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.invitro.adapter.model.contact.ContactEditModel;
import ru.invitro.adapter.service.ContactService;

@Api(tags = "Contact Controller")
@RestController
@RequestMapping(value = "/adapter")
public class ContactController {

    @Autowired
    ContactService contactService;

    /**
     * Формирует запрос на создание контакта в ЛК.
     */

    @Operation(description = "Формирует запрос на создание контакта в ЛК")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/createContact/")
    @ResponseBody
    public String createContact(@RequestBody ContactEditModel model,
                                @RequestParam String visibilityScope,
                                @RequestParam String regionId) {
        return contactService.createContact(model, visibilityScope, regionId);
    }

    /**
     * Формирует запрос изменение контакта в ЛК.
     */

    @Operation(description = "Формирует запрос на изменение контакта в ЛК")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PutMapping("/editContact/{contactId}")
    @ResponseBody
    public String editContact(@RequestBody ContactEditModel model,
                              @RequestParam String visibilityScope,
                              @RequestParam String regionId,
                              @PathVariable(name = "contactId") String contactId) {
        return contactService.editContact(model, visibilityScope, regionId, contactId);
    }

}
