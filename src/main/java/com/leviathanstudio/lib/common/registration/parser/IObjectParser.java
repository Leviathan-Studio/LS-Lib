package com.leviathanstudio.lib.common.registration.parser;

@FunctionalInterface
public interface IObjectParser extends IParser
{
    @Override
    default boolean canParse(String value)
    {
        return parse(value) != null;
    }
}
