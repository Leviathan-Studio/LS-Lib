package com.leviathanstudio.lib.client;

import java.lang.reflect.Field;
import java.util.List;

import com.leviathanstudio.lib.client.asset.FakeResourcePack;
import com.leviathanstudio.lib.client.asset.FakeResourcePackStorage;
import com.leviathanstudio.lib.common.asm.MappingHelper;
import com.leviathanstudio.lib.common.core.CommonProxy;
import com.leviathanstudio.lib.common.util.MinecraftUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourcePack;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class ClientProxy extends CommonProxy
{

    @Override
    public void preInit()
    {
        addFakePack();
        FakeResourcePackStorage.addDomain("test");
    }

    @Override
    public void init()
    {
    }

    @Override
    public void postInit()
    {
    }

    @SuppressWarnings("unchecked")
    public void addFakePack()
    {
        final Field f = ReflectionHelper.findField(Minecraft.class,
                MappingHelper.getNames("net.minecraft.client.Minecraft.defaultResourcePacks"));
        List<IResourcePack> res = null;

        try
        {
            res = (List<IResourcePack>) f.get(MinecraftUtil.getClient());
        } catch (final IllegalArgumentException e)
        {
            e.printStackTrace();
        } catch (final IllegalAccessException e)
        {
            e.printStackTrace();
        }
        if (res != null)
            res.add(new FakeResourcePack());
    }
}
