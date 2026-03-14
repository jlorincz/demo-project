package com.example.demoproject.kodtar;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class KodtarServiceTest {

    private final KrKodtarRepository repository = mock(KrKodtarRepository.class);
    private final KodtarService service = new KodtarService(repository);

    @Test
    void shouldUseRequestedDayAtNoon() {
        when(repository.findRelevantEntries(anyCollection(), any())).thenReturn(List.of());

        service.getKodtar(LocalDate.of(2026, 3, 14));

        ArgumentCaptor<LocalDateTime> validAtCaptor = ArgumentCaptor.forClass(LocalDateTime.class);
        verify(repository).findRelevantEntries(anyCollection(), validAtCaptor.capture());
        assertEquals(LocalDateTime.of(2026, 3, 14, 12, 0), validAtCaptor.getValue());
    }

    @Test
    void shouldUseCurrentTimestampWhenDateIsMissing() {
        when(repository.findRelevantEntries(anyCollection(), any())).thenReturn(List.of());

        LocalDateTime before = LocalDateTime.now().minusSeconds(1);
        service.getKodtar(null);
        LocalDateTime after = LocalDateTime.now().plusSeconds(1);

        ArgumentCaptor<LocalDateTime> validAtCaptor = ArgumentCaptor.forClass(LocalDateTime.class);
        verify(repository).findRelevantEntries(anyCollection(), validAtCaptor.capture());
        assertTrue(!validAtCaptor.getValue().isBefore(before));
        assertTrue(!validAtCaptor.getValue().isAfter(after));
    }
}
