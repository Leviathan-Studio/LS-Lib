package com.leviathanstudio.lib.common.registration.parser;

@FunctionalInterface
public interface IExceptionParser extends IParser
{
    @Override
    default boolean canParse(String value)
    {
        try
        {
            parse(value);
            return true;
        } catch (Exception e)
        {
            return false;
        }
    }

}
