package com.leviathanstudio.lib.common.registration.annotation;

import net.minecraft.tileentity.TileEntity;

public @interface RegisterTileEntity
{
    public String name();

    public Class<? extends TileEntity> cls();
}
