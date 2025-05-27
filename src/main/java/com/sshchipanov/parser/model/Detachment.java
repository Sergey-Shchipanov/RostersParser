package com.sshchipanov.parser.model;

import lombok.Getter;

@Getter
public enum Detachment {
    // Adepta Sororitas
    HALLOWED_MARTYRS("Hallowed Martyrs"),
    PENITENT_HOST("Penitent Host"),
    BRINGERS_OF_FLAME("Bringers of Flame"),
    ARMY_OF_FAITH("Army of Faith"),
    CHAMPIONS_OF_FAITH("Champions of Faith"),

    // Adeptus Custodes
    SHIELD_HOST("Shield Host"),
    TALONS_OF_THE_EMPEROR("Talons of the Emperor"),
    NULL_MAIDEN_VIGIL("Null Maiden Vigil"),
    AURIC_CHAMPIONS("Auric Champions"),
    SOLAR_SPEARHEAD("Solar Spearhead"),

    // Adeptus Mechanicus
    RAD_ZONE_CORPS("Rad-Zone Corps"),
    HUNTER_COHORT("Hunter Cohort"),
    DATA_PSALM_CONCLAVE("Data Psalm Conclave"),
    EXPLORATOR_MANIPLE("Explorator Maniple"),
    COHORT_CYBERNETICA("Cohort Cybernetica"),
    HALOSCREED_BATTLE_CLADE("Haloscreed Battle Clade"),

    // Aeldari
    BATTLE_HOST("Battle Host"),
    ARMORED_WARHOST("Armored Warhost"),

    // Space Marines (examples)
    GLADIUS_TASK_FORCE("Gladius Task Force"),
    ANVIL_SIEGE_FORCE("Anvil Siege Force"),
    FIRESTORM_ASSAULT_FORCE("Firestorm Assault Force"),
    IRONSTORM_SPEARHEAD("Ironstorm Spearhead"),
    STORMLANCE_TASK_FORCE("Stormlance Task Force"),
    VANGUARD_SPEARHEAD("Vanguard Spearhead"),
    FIRST_COMPANY_TASK_FORCE("1st Company Task Force");

    private final String displayName;

    Detachment(String displayName) {
        this.displayName = displayName;
    }

}

