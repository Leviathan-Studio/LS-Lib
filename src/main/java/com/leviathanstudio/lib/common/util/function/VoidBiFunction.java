package com.leviathanstudio.lib.common.util.function;

import javax.annotation.Nullable;

@FunctionalInterface
public interface VoidBiFunction<I, U>
{
    void apply(@Nullable I input1, @Nullable U input2);
}
