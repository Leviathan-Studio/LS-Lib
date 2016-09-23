package com.leviathanstudio.lib.common.data2.interfaces.bytebuf;

import io.netty.buffer.ByteBuf;

public interface IByteBufReader<T>
{
    public T readBuffer(ByteBuf buffer);
}
