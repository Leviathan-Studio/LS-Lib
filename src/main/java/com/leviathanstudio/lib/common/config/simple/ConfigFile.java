package com.leviathanstudio.lib.common.config.simple;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ConfigFile
{
    /**
     * The modid parameter
     * 
     * @return The modid
     */
    public String modid();

    /**
     * The configuration file parameter, by default equal to modid
     * 
     * @return The configuration file name
     */
    public String configFile() default "";

    /**
     * The configuration folder parameter, by default it's the config folder
     * 
     * @return The configuration folder name
     */
    public String configFolder() default "";

    /**
     * The base category parameter, by default it's
     * 
     * @return The base category
     */
    public String baseCategory() default "";
}
