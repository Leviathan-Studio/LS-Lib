package com.leviathanstudio.lib.common.entity.spawn;

import java.util.HashMap;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.minecraft.entity.Entity;

public class PreventSpawn
{
    /** The static instance of this class */
    private static final PreventSpawn                               INSTANCE  = new PreventSpawn();
    private final List<Class<? extends Entity>>                     entities  = Lists.newArrayList();
    private final HashMap<Class<? extends Entity>, IEntitySelector> selectors = Maps.newHashMap();

    /**
     * Returns the static instance of this class
     * 
     * @return the instance of the PreventSpawn
     */
    public static PreventSpawn getInstance()
    {
        /** The static instance of this class */
        return INSTANCE;
    }

    public List<Class<? extends Entity>> getEntityList()
    {
        return this.entities;
    }

    public boolean hasSelector(Class<? extends Entity> entityClass)
    {
        return selectors.containsKey(entityClass);
    }

    public IEntitySelector getSelector(Class<? extends Entity> entityClass)
    {
        return selectors.get(entityClass);
    }

    public boolean canSpawn(Entity entity)
    {
        boolean spawn = true;
        Class<? extends Entity> entityClass = entity.getClass();
        for (int i = 0; i < entities.size() && spawn; i++)
        {
            Class<? extends Entity> clazz = entities.get(i);
            if (clazz.isAssignableFrom(entityClass))
            {
                if (!hasSelector(clazz))
                {
                    spawn = false;
                }
                else
                {
                    return getSelector(clazz).canSpawn(entity);
                }
            }
        }
        return spawn;
    }

    /**
     * Prevent the spawn of an entity
     * 
     * @param entity
     *            The entity
     */
    public void preventEntitytoSpawn(Class<? extends Entity> entity)
    {
        if (!entities.contains(entity))
            entities.add(entity);
    }

    /**
     * Prevent the spawn of an entity
     * 
     * @param entity
     *            The entity
     * @param selector
     *            The selector for more control on the entity
     */
    public void preventEntitytoSpawn(Class<? extends Entity> entity, IEntitySelector selector)
    {
        if (!entities.contains(entity))
        {
            entities.add(entity);
            selectors.put(entity, selector);
        }
    }
}
