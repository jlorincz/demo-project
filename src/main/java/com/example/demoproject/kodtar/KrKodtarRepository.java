package com.example.demoproject.kodtar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface KrKodtarRepository extends JpaRepository<KrKodtar, BigDecimal> {

    @Query("""
            select new com.example.demoproject.kodtar.KodtarItemResponse(
                k.csoportKod,
                k.ertekKod,
                k.ertek
            )
            from KrKodtar k
            where k.csoportKod in :groupCodes
              and :validAt between k.ervKezd and k.ervVege
            order by k.csoportKod, k.ertekKod
            """)
    List<KodtarItemResponse> findRelevantEntries(
            @Param("groupCodes") Collection<String> groupCodes,
            @Param("validAt") LocalDateTime validAt
    );
}
