package com.leviathanstudio.lib.common.registration.annotation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;

@Retention(RUNTIME)
@Target(FIELD)
public @interface RegisterBlock
{
    // Helper values
    public static final String DEFAULT = "[default]";
    public static final String NONE    = "[none]";

    public int order() default 0;

    public boolean isEnabled() default true;

    public boolean fullRegistration() default true;

    public String[] param() default {};

    public String name();

    public String oreName() default "";

    public Class<? extends TileEntity> tileEntity() default TileEntity.class;

    public RegisterTileEntity[] tileEntities() default {};

    public Class<? extends ItemBlock> itemBlock() default ItemBlock.class;

    public String[] itemBlockParam() default {};

}
