package com.leviathanstudio.lib.client.asset;

import java.io.File;

import com.leviathanstudio.lib.common.util.MinecraftUtil;

public class FakeResourcePackStorage
{
    public static void addDomain(String domain)
    {
        FakeResourcePack.resourceDomains.add(domain);
        createFolder(domain);
    }

    public static void createFolder(String domain)
    {
        new File(MinecraftUtil.getMcDir(), "/cache/lsl/" + domain + "/assets/").mkdirs();
    }
}
