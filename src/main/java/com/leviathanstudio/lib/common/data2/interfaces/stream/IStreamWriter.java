package com.leviathanstudio.lib.common.data2.interfaces.stream;

import java.io.DataOutput;
import java.io.IOException;

public interface IStreamWriter<T>
{
    public void writeData(DataOutput data, T value) throws IOException;
}
