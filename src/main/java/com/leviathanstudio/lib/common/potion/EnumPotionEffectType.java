package com.leviathanstudio.lib.common.potion;

public enum EnumPotionEffectType
{
    NORMAL(""), STRONG("strong"), EXTENDED("long");

    public static final EnumPotionEffectType[] VALUES = values();
    private final String                       text;

    EnumPotionEffectType(String text)
    {
        this.text = text;
    }

    public String getText()
    {
        return this.text.isEmpty() ? "" : this.text + "_";
    }
}
