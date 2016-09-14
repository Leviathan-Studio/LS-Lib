package com.leviathanstudio.lib.common.util.io.stream;

import java.io.DataInput;
import java.io.IOException;

public interface IStreamReader<T>
{
    public T readFromStream(DataInput input) throws IOException;
}
