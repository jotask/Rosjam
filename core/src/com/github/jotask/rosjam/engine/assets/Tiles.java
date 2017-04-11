package com.github.jotask.rosjam.engine.assets;

/**
 * Tiles
 *
 * @author Jose Vives Iznardo
 * @since 29/01/2017
 */
public enum Tiles {

    DOOR_WOOD_TOP_01("sprite1"),
    DOOR_WOOD_TOP_02("sprite2"),
    DOOR_WOOD_TOP_03("sprite3"),
    DOOR_WOOD_TOP_04("sprite4"),

    DOOR_WOOD_RIGHT_01("sprite78"),
    DOOR_WOOD_RIGHT_02("sprite54"),
    DOOR_WOOD_RIGHT_03("sprite30"),
    DOOR_WOOD_RIGHT_04("sprite6"),

    DOOR_WOOD_BOTTOM_01("sprite25"),
    DOOR_WOOD_BOTTOM_02("sprite25"),
    DOOR_WOOD_BOTTOM_03("sprite27"),
    DOOR_WOOD_BOTTOM_04("sprite28"),

    DOOR_WOOD_LEFT_01("sprite5"),
    DOOR_WOOD_LEFT_02("sprite29"),
    DOOR_WOOD_LEFT_03("sprite53"),
    DOOR_WOOD_LEFT_04("sprite77"),

    DOOR_IRON_TOP_01("sprite7"),
    DOOR_IRON_TOP_02("sprite8"),
    DOOR_IRON_TOP_03("sprite9"),
    DOOR_IRON_TOP_04("sprite10"),
    DOOR_IRON_TOP_05("sprite11"),

    DOOR_IRON_RIGHT_01("sprite13"),
    DOOR_IRON_RIGHT_02("sprite37"),
    DOOR_IRON_RIGHT_03("sprite61"),
    DOOR_IRON_RIGHT_04("sprite85"),
    DOOR_IRON_RIGHT_05("sprite109"),

    DOOR_IRON_BOTTOM_01("sprite31"),
    DOOR_IRON_BOTTOM_02("sprite32"),
    DOOR_IRON_BOTTOM_03("sprite33"),
    DOOR_IRON_BOTTOM_04("sprite34"),
    DOOR_IRON_BOTTOM_05("sprite35"),

    DOOR_IRON_LEFT_01("sprite12"),
    DOOR_IRON_LEFT_02("sprite36"),
    DOOR_IRON_LEFT_03("sprite60"),
    DOOR_IRON_LEFT_04("sprite84"),
    DOOR_IRON_LEFT_05("sprite108"),

    DOOR_NEXT_LEVEL_OPEN("sprite121"),
    DOOR_NEXT_LEVEL_CLOSE("sprite122"),

    ROCK_01("sprite145"),
    ROCK_02("sprite146")
    ;

    final String name;

    Tiles(final String name ){
        this.name = name;
    }

}
