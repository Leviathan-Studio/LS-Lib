package com.leviathanstudio.lib.common.other;

import com.leviathanstudio.lib.common.LSLRegister;

import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class LSLAchievementPage extends AchievementPage
{
    public LSLAchievementPage(String name, Achievement... achievements)
    {
        super(name, achievements);
        LSLAchievementPage.registerAchievementPage(this);
    }

    public LSLAchievementPage(String name)
    {
        this(name, new Achievement[] {});
    }

    public void addAchievements(Achievement... achievements)
    {
        LSLRegister.addAchievements(this, achievements);
    }
}
