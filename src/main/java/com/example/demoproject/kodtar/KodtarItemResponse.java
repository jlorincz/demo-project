package com.example.demoproject.kodtar;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "KodtarItem")
public record KodtarItemResponse(
        @Schema(example = "KT007") String csoportKod,
        @Schema(example = "001") String ertekKod,
        @Schema(example = "kozterulet") String ertek
) {
}
