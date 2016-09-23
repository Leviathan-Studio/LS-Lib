package com.leviathanstudio.lib.common.data2.interfaces.stream;

import java.io.DataInput;
import java.io.IOException;

public interface IStreamReader<T>
{
    public T readData(DataInput data) throws IOException;
}
