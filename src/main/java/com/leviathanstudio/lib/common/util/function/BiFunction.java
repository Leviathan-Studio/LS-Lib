package com.leviathanstudio.lib.common.util.function;

import javax.annotation.Nullable;

@FunctionalInterface
public interface BiFunction<F, I, T>
{
    @Nullable T apply(@Nullable F input1, @Nullable I input2);

}
