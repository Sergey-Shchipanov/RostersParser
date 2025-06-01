package com.sshchipanov.parser.model;

import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Getter
public enum Faction {
    ADEPTA_SORORITAS("Adepta Sororitas"),
    ADEPTUS_CUSTODES("Adeptus Custodes"),
    ADEPTUS_MECHANICUS("Adeptus Mechanicus"),
    AELDARI("Aeldari"),
    AGENTS_OF_THE_IMPERIUM("Agents of the Imperium"),
    ASTRA_MILITARUM("Astra Militarum"),
    CHAOS_DAEMONS("Chaos Daemons"),
    CHAOS_KNIGHTS("Chaos Knights"),
    CHAOS_SPACE_MARINES("Chaos Space Marines"),
    DEATH_GUARD("Death Guard"),
    DRUKHARI("Drukhari"),
    GENESTEALER_CULTS("Genestealer Cults"),
    GREY_KNIGHTS("Grey Knights"),
    IMPERIAL_KNIGHTS("Imperial Knights"),
    LEAGUES_OF_VOTANN("Leagues of Votann"),
    NECRONS("Necrons"),
    ORKS("Orks"),
    SPACE_MARINES("Space Marines"),
    TAU_EMPIRE("T’au Empire"),
    THOUSAND_SONS("Thousand Sons"),
    TYRANIDS("Tyranids"),
    WORLD_EATERS("World Eaters"),
    EMPERORS_CHILDREN("Emperor’s Children"),
    UNKNOW(Strings.EMPTY);

    private final String displayName;

    Faction(String displayName) {
        this.displayName = displayName;
    }

    public static Faction valueOfDisplayName(String label) {
        for (Faction faction : values()) {
            if (faction.displayName.equals(label)) {
                return faction;
            }
        }
        return UNKNOW;
    }

}
