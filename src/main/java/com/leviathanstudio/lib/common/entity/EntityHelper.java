package com.leviathanstudio.lib.common.entity;

import java.awt.Color;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityHelper
{
    /**
     * Register a new Entity
     * 
     * @param mod
     *            The mod instance
     * @param clazz
     *            The entity class
     * @param name
     *            The entity name
     * @param foregroundColor
     *            The foreground egg color
     * @param backgroundColor
     *            The background egg color
     */
    public static void registerEntity(final Object mod, final Class<? extends Entity> clazz, final String name,
            final Color foregroundColor, final Color backgroundColor)
    {
        final int id = EntityList.NAME_TO_CLASS.size();
        final int trackingRange = 40;
        final int updateFrequency = 1;
        EntityRegistry.registerModEntity(clazz, name, id, mod, trackingRange, updateFrequency, true);
        EntityRegistry.registerEgg(clazz, backgroundColor.getRGB(), foregroundColor.getRGB());
    }

    /**
     * Spawn smoke particles(ENTITY ONLY)
     * 
     * @param speed
     *            the speed of the particle
     * @param entity
     *            instance of the entity
     * @param velX
     *            velocity x of the particle
     * @param velY
     *            velocity y of the particle
     * @param velZ
     *            velocity z of the particle
     */
    public static void spawnSmokeParticles(int speed, EntityLiving entity, double velX, double velY, double velZ)
    {
        spawnSmokeParticles(speed, entity, velX, velY, velZ, 0, 0, 0, new int[0]);
    }

    /**
     * Spawn smoke particles(ENTITY ONLY)
     * 
     * @param speed
     *            the speed of the particle
     * @param entity
     *            instance of the entity
     * @param xVel
     *            velocity x of the particle
     * @param yVel
     *            velocity y of the particle
     * @param zVel
     *            velocity z of the particle
     * @param xPosition
     *            x
     * @param yPosition
     *            y
     * @param zPosition
     *            z
     * @param something
     *            i don't know what is it
     */
    public static void spawnSmokeParticles(int speed, EntityLiving entity, double xVel, double yVel, double zVel,
            double xPosition, double yPosition, double zPosition, int... something)
    {
        for (int i = 0; i < speed; i++)
        {
            entity.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, entity.posX + xPosition,
                    entity.posY + yPosition, entity.posZ + zPosition, xVel, yVel, zVel, something);
        }
    }

    /**
     * Set infinite health to specified entity
     *
     * @param entity
     *            (entity to set infinite health)
     * @author phenix246
     */
    public static void setInfiniteHealth(EntityLivingBase entity)
    {
        entity.setHealth(Float.POSITIVE_INFINITY);
    }

    /**
     * Set infinite absorption to specified entity
     *
     * @param entity
     *            (entity to set infinite absorption)
     * @author phenix246
     */
    public static void setInfiniteAbsorption(EntityLivingBase entity)
    {
        entity.setAbsorptionAmount(Float.POSITIVE_INFINITY);
    }

    /**
     * Set infinite walk speed to specified player
     *
     * @param player
     *            (player to set infinite walk speed)
     * @author phenix246
     */
    public static void setInfiniteWalkSpeed(EntityPlayer player)
    {
        player.capabilities.setPlayerWalkSpeed(Float.POSITIVE_INFINITY);
    }

    /**
     * Set infinite fly speed to specified player
     *
     * @param player
     *            (player to set infinite fly speed)
     * @author phenix246
     */
    public static void setInfiniteFlySpeed(EntityPlayer player)
    {
        player.capabilities.setFlySpeed(Float.POSITIVE_INFINITY);
    }

    /**
     * Set infinite potion effect to specified entity
     *
     * @param entity
     *            (entity to set infinite potion effect)
     * @param potion
     *            (the potion)
     * @param amplifier
     *            (the potion amplifier)
     * @author phenix246
     */
    public static void setInfinitePotion(EntityLivingBase entity, Potion potion, int amplifier)
    {
        entity.addPotionEffect(new PotionEffect(potion, Integer.MAX_VALUE, amplifier));
    }

    /**
     * Return if the entity is in water
     *
     * @param entity
     *            the entity to test
     * @return true if entity is in water
     */
    public static boolean inWater(Entity entity)
    {
        return isInMaterial(entity, Material.WATER);
    }

    /**
     * Return if the entity is in lava
     *
     * @param entity
     *            the entity to test
     * @return true if entity is in lava
     */
    public static boolean inLava(Entity entity)
    {
        return isInMaterial(entity, Material.LAVA);
    }

    /**
     * Return if the entity is in air
     *
     * @param entity
     *            the entity to test
     * @return true if entity is in air
     */
    public static boolean inAir(Entity entity)
    {
        return isInMaterial(entity, Material.AIR);
    }

    /**
     * Return if the entity is in specified material
     *
     * @param entity
     *            (Entity instance)
     * @param material
     *            (e.g Material.ice)
     * @return true if entity is the given material
     */
    public static boolean isInMaterial(Entity entity, Material material)
    {
        return entity.worldObj.isMaterialInBB(entity.getEntityBoundingBox(), material);
    }
}
