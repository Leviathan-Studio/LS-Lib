package com.leviathanstudio.lib.common.config.simple;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ConfigProperty
{
    /**
     * The entry name, by default the field name
     * 
     * @return The entry name
     */
    public String name() default "";

    /**
     * The category name of the entry, by default no category
     * 
     * @return The category name
     */
    public String category() default "";;

    /**
     * The comment for the entry, by default no comment
     * 
     * @return The comment
     */
    public String comment() default "";
}
