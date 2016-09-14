package com.leviathanstudio.test;

import com.leviathanstudio.lib.common.config.simple.ConfigProperty;

public class TestConfig2
{
    @ConfigProperty(category = "test", name = "testInt", comment = "testInt")
    public static int test = 42;
}
