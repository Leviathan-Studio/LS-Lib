package com.leviathanstudio.lib.common.entity.spawn;

import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Event use for the prevent entity spawn system
 */
public class PreventSpawnEvent
{
    @SubscribeEvent
    public void preventSpawn(LivingSpawnEvent event)
    {
        if (!PreventSpawn.getInstance().canSpawn(event.getEntity()))
            event.setResult(Result.DENY);
    }
}
