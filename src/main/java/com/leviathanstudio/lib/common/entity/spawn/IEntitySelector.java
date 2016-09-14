package com.leviathanstudio.lib.common.entity.spawn;

import net.minecraft.entity.Entity;

@FunctionalInterface
public interface IEntitySelector
{
    boolean canSpawn(Entity entity);
}
