package com.leviathanstudio.lib.common.registration.interfaces;

import java.lang.reflect.Field;
import java.util.Comparator;

public interface ISortRegistration extends Comparator<Field>
{
    public int getOrder(Field f);

    @Override
    default int compare(Field o1, Field o2)
    {
        return getOrder(o1) - getOrder(o2);
    };
}
