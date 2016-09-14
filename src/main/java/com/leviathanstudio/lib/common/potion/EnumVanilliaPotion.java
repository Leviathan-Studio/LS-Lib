package com.leviathanstudio.lib.common.potion;

public enum EnumVanilliaPotion
{
    UNCRAFTABLE_POTION("empty", false, false),
    WATER_POTION("water", false, false),
    MUNDAME_POTION("mundane", false, false),
    THICK_POTION("thick", false, false),
    AWKWARD_POTION("awkward", false, false),
    NIGHT_VISION_POTION("night_vision", false, true),
    INVISIBILITY_POTION("invisibility", false, true),
    LEAPING_POTION("leaping", true, true),
    FIRE_RESISTANCE_POTION("fire_resistance", false, true),
    SWIFTNESS_POTION("swiftness", true, true),
    SLOWNESS_POTION("slowness", false, true),
    WATER_BREATHING_POTION("water_breathing", false, true),
    INSTANT_HEALTH_POTION("healing", true, false),
    HARMING_POTION("harming", true, false),
    POISON_POTION("poison", true, true),
    REGENERATION_POTION("regeneration", true, true),
    STRENGTH_POTION("strength", true, true),
    WEAKNESS_POTION("weakness", false, true),
    LUCK_POTION("luck", false, false);

    private final boolean                hasStrong;
    private final boolean                hasExtended;
    private final String                 name;

    public static final EnumVanilliaPotion[] VALUES = values();

    EnumVanilliaPotion(String name, boolean hasStrong, boolean hasExtended)
    {
        this.name = name;
        this.hasStrong = hasStrong;
        this.hasExtended = hasExtended;
    }

    public String getName()
    {
        return this.name;
    }

    public boolean hasStrong()
    {
        return this.hasStrong;
    }

    public boolean hasExtended()
    {
        return this.hasExtended;
    }
}
