package com.leviathanstudio.lib.common.data2.interfaces.stream;

import java.io.DataOutput;
import java.io.IOException;

public interface IStreamWriter<T>
{
    public void writeStream(DataOutput data, T value) throws IOException;
}
