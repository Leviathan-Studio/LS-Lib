package com.leviathanstudio.lib.common.util.io.stream;

public interface IStringSerializer<T>
{
    public T readFromString(String s);
}
