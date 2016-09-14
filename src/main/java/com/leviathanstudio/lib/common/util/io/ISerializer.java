package com.leviathanstudio.lib.common.util.io;

import com.leviathanstudio.lib.common.util.io.nbt.INbtSerializer;
import com.leviathanstudio.lib.common.util.io.stream.IStreamSerializer;

public interface ISerializer<T> extends IStreamSerializer<T>, INbtSerializer<T>
{
}
