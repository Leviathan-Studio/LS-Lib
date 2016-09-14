package com.leviathanstudio.lib.common.util.io.stream;

import java.io.DataOutput;
import java.io.IOException;

public interface IStreamWriter<T>
{
    public void writeToStream(T o, DataOutput output) throws IOException;
}
