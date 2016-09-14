package com.leviathanstudio.lib.asm;

import java.io.File;
import java.util.Map;

import com.leviathanstudio.lib.common.asm.MappingHelper;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.MCVersion;

@MCVersion(value = "1.10.2")
public class LSLLoadingPlugin implements IFMLLoadingPlugin
{
    public static File location;

    public LSLLoadingPlugin()
    {
        MappingHelper.init();
    }

    @Override
    public String[] getASMTransformerClass()
    {
        return new String[] { LSLLibClassTransformer.class.getName() };
    }

    @Override
    public String getModContainerClass()
    {
        return null;
    }

    @Override
    public String getSetupClass()
    {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data)
    {
        location = (File) data.get("coremodLocation");
    }

    @Override
    public String getAccessTransformerClass()
    {
        return null;
    }

}
