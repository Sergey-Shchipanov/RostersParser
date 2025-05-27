package com.sshchipanov.parser.web;

import com.sshchipanov.parser.model.Detachment;
import com.sshchipanov.parser.model.Faction;
import com.sshchipanov.parser.model.TournamentRoster;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@AllArgsConstructor
@Slf4j
public class BCPListsParser implements TournamentPortalParser {

    private String bcpListsUrl;

    @Override
    public List<TournamentRoster> parseRosters() {
        log.info("Parsing BCP Lists url: {}", bcpListsUrl);
        // TODO: Implement the actual parsing logic here.
        String authenticationToken = getAuthenticationToken();
        log.info("Generated authentication token: {}", authenticationToken);
        return getTournamentRosters(authenticationToken);
    }

    private static List<TournamentRoster> getTournamentRosters(String authenticationToken) {
        log.info("Calling BCP API with authentication token: {}", authenticationToken);
        return List.of(new TournamentRoster(Faction.ADEPTA_SORORITAS, Detachment.BRINGERS_OF_FLAME, "Player One", "some dummy list"));
    }


    protected String getAuthenticationToken() {
        return "dummy-auth-token";
    }


}
