
package ru.invitro.adapter.controller;


import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.invitro.adapter.service.EdmConnectionService;

@Api(tags = "Connect Controller")
@RestController
@RequestMapping(value = "/adapter")
public class EdmConnectionController {

    @Autowired
    EdmConnectionService connectionService;

    /**
     * Направляет запрос в сервис ЭДО с целью проверки статуса подключения клиента.
     */

    @Operation(description = "Получить статус подключения клиента к ЭДО")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping(value = "/isConnected", produces = {"text/plain"})
    @ResponseBody
    public String getConnectionStatus(@RequestParam String clientId) {
        return connectionService.getConnectionStatus(clientId);
    }

    /**
     * Подключить контакт с системе электронного документооборота.
     */

    @Operation(description = "Подключить контакт с системе документооборота")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping(value = "/connectToEDM", produces = {"text/plain"})
    @ResponseBody
    public String connectToEDM(@RequestParam String clientId,
                               @RequestParam String phoneId,
                               @RequestParam String branchOfficeId) {
        return connectionService.connectToEDM(clientId, phoneId, branchOfficeId);
    }
}

