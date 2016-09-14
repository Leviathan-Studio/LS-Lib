package com.leviathanstudio.lib.common.registration.parser;

public interface IParser
{
    boolean canParse(String value);

    Object parse(String value);

    default void init()
    {
    }
}
