package com.example.demoproject.kodtar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KodtarApiIT {

    private static final long TEST_ID = 910001L;
    private static final String TEST_CODE = "IT-KT015-001";
    private static final String TEST_VALUE = "Integration Test Country";
    private static final String TEST_GROUP_CODE = "KT015";

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final String baseUrl = System.getProperty("integration.base-url", "http://127.0.0.1:8080");
    private final String dbUrl = System.getProperty("integration.db.url",
            "jdbc:mysql://127.0.0.1:3306/demo_project?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
    private final String dbUsername = System.getProperty("integration.db.username", "root");
    private final String dbPassword = System.getProperty("integration.db.password", "");

    @BeforeEach
    void setUp() throws SQLException {
        deleteTestData();
        insertTestData();
    }

    @AfterEach
    void tearDown() throws SQLException {
        deleteTestData();
    }

    @Test
    void shouldReturnInjectedIntegrationData() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/api/szabi?validOn=2026-03-14"))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode());
        assertTrue(response.body().contains("\"csoportKod\":\"KT015\""));
        assertTrue(response.body().contains("\"ertekKod\":\"" + TEST_CODE + "\""));
        assertTrue(response.body().contains("\"ertek\":\"" + TEST_VALUE + "\""));
    }

    @Test
    void shouldKeepSwaggerAvailableWhileApiIsRunning() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/v3/api-docs"))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode());
        assertTrue(response.body().contains("\"/api/szabi\""));
    }

    private void insertTestData() throws SQLException {
        try (Connection connection = openConnection();
             PreparedStatement statement = connection.prepareStatement("""
                     INSERT INTO KR_KODTAR_TBL
                     (ID, CSOPORT_KOD, ERTEK_KOD, ERTEK, LETREHOZO_FELH_ID, LETREHOZVA, ERV_KEZD, ERV_VEGE, SORREND)
                     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                     """)) {
            statement.setLong(1, TEST_ID);
            statement.setString(2, TEST_GROUP_CODE);
            statement.setString(3, TEST_CODE);
            statement.setString(4, TEST_VALUE);
            statement.setLong(5, 1L);
            statement.setTimestamp(6, Timestamp.valueOf(LocalDateTime.of(2026, 3, 14, 9, 0)));
            statement.setTimestamp(7, Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0)));
            statement.setTimestamp(8, Timestamp.valueOf(LocalDateTime.of(3999, 12, 31, 0, 0)));
            statement.setLong(9, 99L);
            statement.executeUpdate();
        }
    }

    private void deleteTestData() throws SQLException {
        try (Connection connection = openConnection();
             PreparedStatement statement = connection.prepareStatement("""
                     DELETE FROM KR_KODTAR_TBL
                     WHERE ID = ?
                        OR (CSOPORT_KOD = ? AND ERTEK_KOD = ?)
                     """)) {
            statement.setLong(1, TEST_ID);
            statement.setString(2, TEST_GROUP_CODE);
            statement.setString(3, TEST_CODE);
            statement.executeUpdate();
        }
    }

    private Connection openConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }
}
