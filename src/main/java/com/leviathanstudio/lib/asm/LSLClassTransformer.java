package com.leviathanstudio.lib.asm;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.leviathanstudio.lib.common.util.function.BiFunction;
import com.leviathanstudio.lib.common.util.function.VoidBiFunction;

import net.minecraft.launchwrapper.IClassTransformer;

public abstract class LSLClassTransformer implements IClassTransformer
{
    private File location;

    public LSLClassTransformer(File location)
    {
        this.location = location;
    }

    protected static final Map<String, BiFunction<String, byte[], byte[]>> transformers = Maps.newHashMap();

    protected static final List<VoidBiFunction<String, byte[]>>            catchers     = Lists.newArrayList();

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass)
    {
        for (VoidBiFunction<String, byte[]> catcher : catchers)
        {
            catcher.apply(name, basicClass);
        }
        if (transformers.containsKey(transformedName))
        {
            System.out.println("Patch: " + transformedName);
            return transformers.get(transformedName).apply(name.replace('.', '/'), basicClass);
        }

        return basicClass;
    }

    /**
     * Hard Patch a class, please don't use it if you can use ASM
     * 
     * @param name
     *            The Class name
     * @param byteCode
     *            The original byteCode
     * @return The modify byteCode
     */
    public byte[] patchClassInJar(String name, byte[] byteCode)
    {
        try
        {
            ZipFile zip = new ZipFile(location);
            ZipEntry entry = zip.getEntry(name.replace('.', '/') + ".class");

            if (entry == null)
                System.err.println(name + ".class" + " not found in " + location.getName());
            else
            {
                InputStream zin = zip.getInputStream(entry);
                byteCode = new byte[(int) entry.getSize()];
                zin.read(byteCode);
                zin.close();
                System.out.println("Class " + name + " patched!");
            }
            zip.close();
        } catch (Exception e)
        {
            throw new RuntimeException("Error overriding " + name + " from " + location.getName(), e);
        }
        return byteCode;
    }

}
