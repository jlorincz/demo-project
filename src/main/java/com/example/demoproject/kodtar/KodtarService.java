package com.example.demoproject.kodtar;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class KodtarService {

    private static final List<String> RELEVANT_GROUP_CODES = List.of(
            "KT007",
            "KT015",
            "KT033",
            "KT053",
            "KT020",
            "KT032",
            "KT006",
            "KT005",
            "KT025",
            "KT039",
            "KT040",
            "KT041",
            "KT036",
            "KT037"
    );

    private final KrKodtarRepository repository;

    public KodtarService(KrKodtarRepository repository) {
        this.repository = repository;
    }

    public List<KodtarItemResponse> getKodtar(LocalDate validOn) {
        LocalDateTime validAt = validOn == null
                ? LocalDateTime.now()
                : validOn.atTime(LocalTime.NOON);
        return repository.findRelevantEntries(RELEVANT_GROUP_CODES, validAt);
    }
}
