package com.leviathanstudio.lib.common.data2.interfaces.bytebuf;

import io.netty.buffer.ByteBuf;

public interface IByteBufWriter<T>
{
    public void writeBuffer(ByteBuf buffer, T value);
}
