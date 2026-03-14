package com.example.demoproject.kodtar;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/kodtar")
public class KodtarController {

    private final KodtarService service;

    public KodtarController(KodtarService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(
            summary = "List relevant kodtar entries",
            description = "Returns the relevant kodtar values filtered by the configured csoport_kod list and the requested validity day.",
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "Successful lookup",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = KodtarItemResponse.class)))
            )
    )
    public List<KodtarItemResponse> getKodtar(
            @Parameter(description = "Validity date in ISO format yyyy-MM-dd. If omitted, the current timestamp is used.")
            @RequestParam(name = "validOn", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate validOn
    ) {
        return service.getKodtar(validOn);
    }
}
