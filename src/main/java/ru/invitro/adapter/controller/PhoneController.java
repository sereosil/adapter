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
import ru.invitro.adapter.model.phone.CodeRequestDTO;
import ru.invitro.adapter.model.phone.CodeSendRequestDTO;
import ru.invitro.adapter.model.phone.PhoneSaveRequestModel;


@Api(tags = "Phone Controller")
@RestController
@RequestMapping(value = "/phone", produces = "application/json")
public class PhoneController {

    private final WebClient client = WebClient.builder().build();

    @Operation(description = "Формирует запрос на создание телефона контакта в ЛК")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/createPhone/")
    @ResponseBody
    public String createPhone(@RequestBody PhoneSaveRequestModel model,
                              @RequestParam String contactId,
                              @RequestParam String visibilityScope) {
        return client.post().uri("http://10.10.10.35:8088/rest/api/v1/contacts/" + contactId + "/phones?" + "visibilityScopeId=" + visibilityScope)
                .bodyValue(model)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().isError()) {
                        clientResponse.body((clientHttpResponse, context) -> clientHttpResponse.getBody());
                    }
                    return clientResponse.bodyToMono(String.class);
                }).block();
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
        return client.delete().uri("http://10.10.10.35:8088/rest/api/v1/contacts/" + contactId + "/phones/" + phoneId + "?visibilityScopeId=" + visibilityScope)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().isError()) {
                        clientResponse.body((clientHttpResponse, context) -> clientHttpResponse.getBody());
                    }
                    return clientResponse.bodyToMono(String.class);
                }).block();
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

        return client.post().uri("http://10.10.10.35:8088/rest/api/v1/contacts/" + contactId + "/phones/codes?" + "visibilityScopeId=" + visibilityScope)
                .bodyValue(code)
                .exchangeToMono(clientResponse ->
                        clientResponse.statusCode().isError() ? clientResponse.createException().flatMap(Mono::error) :
                                clientResponse.bodyToMono(String.class))
                .block();
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

        return client.post().uri("http://10.10.10.35:8088/rest/api/v1/contacts/" + contactId + "/phones/validate?" + "visibilityScopeId=" + visibilityScope)
                .bodyValue(code)
                .exchangeToMono(clientResponse ->
                        clientResponse.statusCode().isError() ? clientResponse.createException().flatMap(Mono::error) :
                                clientResponse.bodyToMono(String.class))
                .block();
    }
}
