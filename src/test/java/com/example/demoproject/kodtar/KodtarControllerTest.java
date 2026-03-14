package com.example.demoproject.kodtar;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class KodtarControllerTest {

    private final KodtarService service = mock(KodtarService.class);
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new KodtarController(service)).build();

    @Test
    void shouldReturnKodtarEntries() throws Exception {
        when(service.getKodtar(LocalDate.of(2026, 3, 14))).thenReturn(List.of(
                new KodtarItemResponse("KT007", "001", "kozterulet")
        ));

        mockMvc.perform(get("/api/kodtar").param("validOn", "2026-03-14"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].csoportKod").value("KT007"))
                .andExpect(jsonPath("$[0].ertekKod").value("001"))
                .andExpect(jsonPath("$[0].ertek").value("kozterulet"));

        verify(service).getKodtar(LocalDate.of(2026, 3, 14));
    }
}
