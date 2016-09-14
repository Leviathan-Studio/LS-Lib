package com.leviathanstudio.lib.client.asset;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

import com.google.common.collect.Sets;
import com.leviathanstudio.lib.common.util.MinecraftUtil;

import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.MetadataSerializer;
import net.minecraft.util.ResourceLocation;

public class FakeResourcePack implements IResourcePack
{
    public static final HashSet<String> resourceDomains = Sets.newHashSet();

    // TODO Patch
    @Override
    public InputStream getInputStream(ResourceLocation location) throws IOException
    {
        String resourcePath = "";

        if (location.getResourcePath().contains("/"))
            resourcePath = location.getResourcePath().substring(location.getResourcePath().lastIndexOf("/"),
                    location.getResourcePath().length());
        else
            resourcePath = location.getResourcePath();
        return new FileInputStream(new File(MinecraftUtil.getMcDir(),
                "/cache/lsl/" + location.getResourceDomain() + "/assets/" + resourcePath));
    }

    @Override
    public boolean resourceExists(ResourceLocation location)
    {
        return true;
    }

    @Override
    public Set<String> getResourceDomains()
    {
        return resourceDomains;
    }

    @Override
    public <T extends IMetadataSection> T getPackMetadata(MetadataSerializer metadataSerializer,
            String metadataSectionName) throws IOException
    {
        return null;
    }

    @Override
    public BufferedImage getPackImage() throws IOException
    {
        return ImageIO.read(DefaultResourcePack.class
                .getResourceAsStream("/" + (new ResourceLocation("pack.png")).getResourcePath()));

    }

    @Override
    public String getPackName()
    {
        return "LSL-fake-resourcepack";
    }

}
