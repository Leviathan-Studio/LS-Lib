package com.leviathanstudio.lib.common.registration;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.leviathanstudio.lib.common.LSLRegister;
import com.leviathanstudio.lib.common.core.LSLib;
import com.leviathanstudio.lib.common.registration.annotation.IgnoreFeature;
import com.leviathanstudio.lib.common.registration.annotation.RegisterBlock;
import com.leviathanstudio.lib.common.registration.annotation.RegisterTileEntity;
import com.leviathanstudio.lib.common.registration.interfaces.IProcessRegistration;
import com.leviathanstudio.lib.common.registration.interfaces.ISortRegistration;
import com.leviathanstudio.lib.common.util.InstanceCreator;
import com.leviathanstudio.lib.common.util.Util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;

public class RegistrationProvider
{
    public static final Map<String, Block> blocks = Maps.newHashMap();
    public static final Map<String, Item>  items  = Maps.newHashMap();

    private final String                   modPrefix;

    private final String                   modId;

    public RegistrationProvider(String modPrefix)
    {
        this.modPrefix = modPrefix;

        ModContainer mod = Loader.instance().activeModContainer();
        Preconditions.checkNotNull(mod, "This class can only be initialized in mod  init");
        this.modId = mod.getModId();
    }

    public static <I, A extends Annotation> List<Field> getField(Class<?> config, Class<I> fieldClass,
            Class<A> annotationClass, ISortRegistration sorter)
    {
        List<Field> result = Lists.newArrayList();
        for (Field f : config.getFields())
        {
            if (Modifier.isStatic(f.getModifiers()) && fieldClass.isAssignableFrom(f.getType()))
            {
                if (f.isAnnotationPresent(IgnoreFeature.class))
                    continue;

                A annotation = f.getAnnotation(annotationClass);
                if (annotation == null)
                {
                    LSLib.LOGGER.error("Field %s has valid type %s for registration, but no annotation %s ", f,
                            fieldClass, annotationClass);
                    continue;
                }
                result.add(f);

            }
        }
        result.sort(sorter);
        return result;
    }

    @SuppressWarnings("unchecked")
    public static <I, A extends Annotation> void process(List<Field> field, Class<I> fieldClass,
            Class<A> annotationClass, Function<A, String[]> getArgument, Function<A, Boolean> isEnbale,
            IProcessRegistration<I, A> process)
    {
        for (Field f : field)
        {
            A annotation = f.getAnnotation(annotationClass);

            I entry = (I) InstanceCreator.createInstance(f.getType(), getArgument.apply(annotation));

            if (entry == null)
                continue;

            if (!isEnbale.apply(annotation))
                continue;

            try
            {
                f.set(null, entry);
            } catch (Exception e)
            {
                throw Throwables.propagate(e);
            }

            process.process(entry, annotation);
        }
    }

    public void registerBlocks(Class<?> clazz)
    {
        List<Field> blocks = getField(clazz, Block.class, RegisterBlock.class, (Field f) ->
        {
            return f.getAnnotation(RegisterBlock.class).order();
        });

        process(blocks, Block.class, RegisterBlock.class, (annotation) ->
        {
            return annotation.param();
        }, (annotation) ->
        {
            return annotation.isEnabled();
        }, (Block block, RegisterBlock annotation) ->
        {
            String name = annotation.name();
            Class<? extends TileEntity> teClass = annotation.tileEntity();
            if (teClass == TileEntity.class)
                teClass = null;
            boolean fullRegistration = annotation.fullRegistration();
            String oreName = annotation.oreName();

            if (!fullRegistration)
            {
                LSLRegister.registerBlockOnly(block);
            }
            else
            {
                ItemBlock itemBlock;
                if (annotation.itemBlock() == ItemBlock.class)
                    itemBlock = new ItemBlock(block);
                else
                {
                    // Initialize itemBlock
                    Class<? extends ItemBlock> ibClass = annotation.itemBlock();
                    itemBlock = InstanceCreator.createInstance(ibClass, annotation.itemBlockParam());
                }

                // Add block to intern list
                RegistrationProvider.blocks.put(name, block);

                // Set block value
                block.setUnlocalizedName(name);

                // Registry
                LSLRegister.registerBlock(block, itemBlock, name);

                // Add to Ore Dictionary
                if (!oreName.isEmpty())
                    LSLRegister.registerOre(oreName, block);

                // Register Tile Entity
                if (teClass != null)
                    LSLRegister.registerTileEntity(teClass, name + teClass.getCanonicalName());

                for (RegisterTileEntity te : annotation.tileEntities())
                {
                    final String teName = Util.underscoreName(RegistrationProvider.this.modPrefix, te.name());
                    LSLRegister.registerTileEntity(te.cls(), teName);
                }
            }
        });
    }
}
