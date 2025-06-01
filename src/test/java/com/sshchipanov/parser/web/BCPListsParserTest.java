package com.sshchipanov.parser.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sshchipanov.parser.model.BCPTournamentList;
import com.sshchipanov.parser.model.Faction;
import com.sshchipanov.parser.model.bcp.BCPResponse;
import com.sshchipanov.parser.model.bcp.Datum;
import com.sshchipanov.parser.model.bcp.Player;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class BCPListsParserTest {


    public static MockWebServer mockBackEnd;
    public static BCPListsParser bcpListsParser;

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @BeforeEach
    void initialize() {
        String baseUrl = String.format("http://localhost:%s",
                mockBackEnd.getPort());
        bcpListsParser = new BCPListsParser(baseUrl);
    }


    @Test
    void testParseRosters() throws Exception {
        BCPResponse response = createTestBCPResponse();
        mockBackEnd.enqueue(new MockResponse()
                .setBody(new ObjectMapper().writeValueAsString(response))
                .setHeader("Content-Type", "application/json"));

        StepVerifier.create(bcpListsParser.parseRosters())
                .expectNextMatches(list ->
                        list.getFaction() == Faction.ADEPTUS_CUSTODES &&
                                list.getPlayerName().equals("John Doe") &&
                                list.getBcpListUrl().endsWith("list456") &&
                                list.getResultRecord().equals(Arrays.asList(2, 0, 0)) &&
                                list.getNumberOfWins() == 1.0 &&
                                list.getTournamentId().equals("event99")
                )
                .verifyComplete();
    }

    @Test
    void testParseRosters_HttpError() {
        mockBackEnd.enqueue(new MockResponse()
                .setResponseCode(500)
                .setBody("Internal Server Error"));

        Flux<BCPTournamentList> bcpTournamentListFlux = bcpListsParser.parseRosters();
        StepVerifier.create(bcpTournamentListFlux).expectErrorMatches(error ->
                error instanceof IllegalStateException &&
                        error.getMessage().equals("Failed to retrieve data from BCP"))
                .verify();

    }

    @Test
    void testMapDataToTournamentList() {
        Player player = new Player();
        player.setArmyName(Faction.ADEPTUS_CUSTODES.getDisplayName());
        player.firstName = "Jane";
        player.setLastName("Smith");
        player.setArmyListObjectId("list123");
        player.setTotalResultRecord(Arrays.asList(2, 0, 0));
        player.setTotalNumWins(1.0);

        Datum datum = new Datum();
        datum.setPlayer(player);
        datum.setEventId("event42");

        BCPListsParser parser = new BCPListsParser("dummyUrl");
        BCPTournamentList result = parser.mapDataToTournamentList(datum);

        assertEquals(Faction.ADEPTUS_CUSTODES, result.getFaction());
        assertEquals("Jane Smith", result.getPlayerName());
        assertTrue(result.getBcpListUrl().endsWith("list123"));
        assertEquals(Arrays.asList(2, 0, 0), result.getResultRecord());
        assertEquals(1.0, result.getNumberOfWins());
        assertEquals("event42", result.getTournamentId());
    }

    @Test
    void testConvertResponseToEntities() {
        BCPResponse response = createTestBCPResponse();

        BCPListsParser parser = new BCPListsParser("dummyUrl");

        StepVerifier.create(parser.convertResponseToEntities(Mono.just(response)))
                .expectNextMatches(list ->
                        list.getFaction() == Faction.ADEPTUS_CUSTODES &&
                                list.getPlayerName().equals("John Doe") &&
                                list.getBcpListUrl().endsWith("list456") &&
                                list.getResultRecord().equals(Arrays.asList(2, 0, 0)) &&
                                list.getNumberOfWins() == 1.0 &&
                                list.getTournamentId().equals("event99")
                )
                .verifyComplete();
    }

    private static BCPResponse createTestBCPResponse() {
        Player player = new Player();
        player.setArmyName(Faction.ADEPTUS_CUSTODES.getDisplayName());
        player.firstName = "John";
        player.setLastName("Doe");
        player.setArmyListObjectId("list456");
        player.setTotalResultRecord(Arrays.asList(2, 0, 0));
        player.setTotalNumWins(1.0);

        Datum datum = new Datum();
        datum.setPlayer(player);
        datum.setEventId("event99");

        BCPResponse response = new BCPResponse();
        response.setData(Collections.singletonList(datum));
        return response;
    }
}