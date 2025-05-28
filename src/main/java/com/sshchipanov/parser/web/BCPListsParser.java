package com.sshchipanov.parser.web;

import com.sshchipanov.parser.model.Detachment;
import com.sshchipanov.parser.model.Faction;
import com.sshchipanov.parser.model.BCPTournamentList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@AllArgsConstructor
@Slf4j
public class BCPListsParser implements TournamentPortalParser {

    private String bcpListsUrl;

    @Override
    public List<BCPTournamentList> parseRosters() {
        log.info("Parsing BCP Lists url: {}", bcpListsUrl);
        // TODO: Implement the actual parsing logic here.
        String authenticationToken = getAuthenticationToken();
        log.info("Generated authentication token: {}", authenticationToken);
        return getTournamentRosters(authenticationToken);
    }

    private static List<BCPTournamentList> getTournamentRosters(String authenticationToken) {
        log.info("Calling BCP API with authentication token: {}", authenticationToken);
        return List.of(new BCPTournamentList(
                Faction.ADEPTUS_CUSTODES,
                Detachment.SOLAR_SPEARHEAD,
                "Player1",
                "https://bcp-url.com/list1",
                "tournament-path-1",
                3,
                "tournament-id-1"
        ));
    }


    protected String getAuthenticationToken() {
        return "dummy-auth-token";
    }


}
