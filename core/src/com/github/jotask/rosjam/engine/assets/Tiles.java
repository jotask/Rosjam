package com.github.jotask.rosjam.engine.assets;

/**
 * Tiles
 *
 * @author Jose Vives Iznardo
 * @since 29/01/2017
 */
public enum Tiles {

    DOOR_TOP_01("sprite1"),
    DOOR_TOP_02("sprite2"),
    DOOR_TOP_03("sprite3"),
    DOOR_TOP_04("sprite4"),

    DOOR_RIGHT_01("sprite32"),
    DOOR_RIGHT_02("sprite28"),
    DOOR_RIGHT_03("sprite19"),
    DOOR_RIGHT_04("sprite6"),

    DOOR_BOTTOM_01("sprite14"),
    DOOR_BOTTOM_02("sprite15"),
    DOOR_BOTTOM_03("sprite16"),
    DOOR_BOTTOM_04("sprite17"),

    DOOR_LEFT_01("sprite31"),
    DOOR_LEFT_02("sprite27"),
    DOOR_LEFT_03("sprite18"),
    DOOR_LEFT_04("sprite5"),

    DOOR_NEXT_LEVEL_OPEN("sprite121"),
    DOOR_NEXT_LEVEL_CLOSE("sprite122");

    final String name;

    Tiles(final String name ){
        this.name = name;
    }

}
