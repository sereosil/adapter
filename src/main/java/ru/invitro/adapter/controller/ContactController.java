package ru.invitro.adapter.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.invitro.adapter.controller.converter.ContactEditModelToContactCreateDTOConverter;
import ru.invitro.adapter.controller.converter.ContactEditModelToMetaConverter;
import ru.invitro.adapter.model.dto.ContactCreateRequestDTO;
import ru.invitro.adapter.model.dto.ContactEditRequestDTO;
import ru.invitro.adapter.model.contact.ContactEditModel;

@Api(tags = "Contact Controller")
@RestController
@RequestMapping(value = "/adapter")
public class ContactController {

    private final WebClient client = WebClient.builder().build();

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
        ContactCreateRequestDTO requestDTO = ContactEditModelToContactCreateDTOConverter.INSTANCE.convert(model, regionId);
        System.out.println(requestDTO.toString());
        System.out.println(model.toString());
        return client.post().uri("http://10.10.10.35:8088/rest/api/v1/contacts?visibilityScopeId=" + visibilityScope)
                .bodyValue(requestDTO)
                .exchangeToMono(clientResponse ->
                        clientResponse.statusCode().isError() ? clientResponse.createException().flatMap(Mono::error) :
                                clientResponse.bodyToMono(String.class))
                .block();
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
        ContactEditRequestDTO contactEditRequestDTO = ContactEditModelToMetaConverter.INSTANCE.convert(model, regionId);
        return client.put().uri("http://10.10.10.35:8088/rest/api/v1/contacts/" + contactId + "?visibilityScopeId=" + visibilityScope)
                .bodyValue(contactEditRequestDTO)
                .exchangeToMono(clientResponse ->
                        clientResponse.statusCode().isError() ? clientResponse.createException().flatMap(Mono::error) :
                                clientResponse.bodyToMono(String.class))
                .block();
    }

}
