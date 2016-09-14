package com.leviathanstudio.lib.common.registration.interfaces;

import java.lang.annotation.Annotation;

public interface IProcessRegistration<I, A extends Annotation>
{
    public void process(I entry, A annotation);

}
