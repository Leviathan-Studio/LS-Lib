package com.leviathanstudio.test;

import com.leviathanstudio.lib.common.LSLRegister;
import com.leviathanstudio.lib.common.config.simple.ConfigFile;
import com.leviathanstudio.lib.common.config.simple.ConfigProperty;
import com.leviathanstudio.lib.common.event.CraftMatrixUpdateEvent;
import com.leviathanstudio.lib.common.network.LSLNetwork;
import com.leviathanstudio.lib.common.other.EnchantHelper;
import com.leviathanstudio.lib.common.potion.EnumPotionEffectType;
import com.leviathanstudio.lib.common.potion.EnumVanilliaPotion;
import com.leviathanstudio.lib.common.potion.PotionHelper;
import com.leviathanstudio.lib.common.recipe.anvil.IAnvilRecipe;
import com.leviathanstudio.lib.common.registration.RegistrationProvider;
import com.leviathanstudio.lib.common.registration.annotation.RegisterBlock;
import com.leviathanstudio.lib.common.registration.parser.ParserManager;
import com.leviathanstudio.lib.common.util.MinecraftUtil;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Test.MODID, version = Test.VERSION, name = Test.NAME, dependencies = "required-after:lslib")
public class Test
{
    public static final String     MODID       = "testmod";
    public static final String     NAME        = "testmod";
    public static final String     VERSION     = "1.0";
    public static final String     BASE_DOMAIN = "com.leviathanstudio.test";

    public static final Logger     LOGGER      = LogManager.getLogger(Test.NAME);

    public static final LSLNetwork NETWORK     = new LSLNetwork(MODID);

    @Instance(Test.MODID)
    public static Test             INSTANCE;

    @ConfigFile(configFile = "Test.cfg", modid = Test.MODID, configFolder = "Test", baseCategory = "modTest1")
    public static Test             config;

    @ConfigFile(configFile = "Test.cfg", modid = Test.MODID, configFolder = "Test", baseCategory = "modTest2")
    public static TestConfig2      config2;

    @ConfigProperty(category = "test", name = "test", comment = "test")
    public static boolean          test        = true;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        // Test Enchant Helper
        LSLRegister.addAnvilRecipe(new IAnvilRecipe()
        {
            int       cost = 0;
            ItemStack out;

            @Override
            public void update(ItemStack left, ItemStack right)
            {
                out = new ItemStack(Items.ENCHANTED_BOOK);
                EnchantHelper.copyEnchant(left, out);
                cost = EnchantHelper.getEnchantmentsCost(left);
            }

            @Override
            public boolean matches(ItemStack left, ItemStack right)
            {
                return right.getItem() == Items.BOOK && left.isItemEnchanted();
            }

            @Override
            public ItemStack getRecipeOutput()
            {
                return out;
            }

            @Override
            public int getCost()
            {
                return cost;
            }
        });

        // Test Potion Helper
        LSLRegister.addAnvilRecipe(new IAnvilRecipe()
        {

            @Override
            public boolean matches(ItemStack left, ItemStack right)
            {
                return right.getItem() == Items.WATER_BUCKET && left.getItem() == Items.APPLE;
            }

            @Override
            public ItemStack getRecipeOutput()
            {
                return PotionHelper.getPotion(EnumVanilliaPotion.STRENGTH_POTION, EnumPotionEffectType.EXTENDED);
            }

            @Override
            public int getCost()
            {
                return 1;
            }
        });

        // PreventSpawn.getInstance().preventEntitytoSpawn(EntityCreeper.class);
        // PreventSpawn.getInstance().preventEntitytoSpawn(EntityZombie.class);
        // PreventSpawn.getInstance().preventEntitytoSpawn(EntitySkeleton.class);
        // PreventSpawn.getInstance().preventEntitytoSpawn(EntitySpider.class);

        // Event
        LSLRegister.registerEvent(new Test());

        // Network
        NETWORK.registerPacket("com.leviathanstudio.test");

        // Register
        RegistrationProvider provider = new RegistrationProvider(Test.MODID);
        provider.registerBlocks(Test.class);
        System.out.println(b);

        // Recipe
        LSLRegister.removeVanillaBrewingRecipe(PotionHelper.getPotion(EnumVanilliaPotion.AWKWARD_POTION));
        LSLRegister.addBrewingRecipe(new ItemStack(Items.WATER_BUCKET), new ItemStack(Items.NETHER_WART),
                PotionHelper.getPotion(EnumVanilliaPotion.INSTANT_HEALTH_POTION));
    }

    @RegisterBlock(name = "Test", param = { "Material:anvil", "EnumDyeColor:light_blue" })
    public static BlockTest b;

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        System.out.println(ParserManager.parse("Block:minecraft:stone", Block.class));
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        // Network
        NETWORK.flip();
    }

    @SubscribeEvent
    public void onEntityJoinWorldEvent(EntityJoinWorldEvent event)
    {
        if (event.getEntity() instanceof EntityPlayer)
        {
            System.out.println("join");
            if (MinecraftUtil.getEffectiveSide().isServer())
            {
                if (event.getEntity() instanceof EntityPlayerMP)
                    NETWORK.sendTo(new PacketTest2(68), (EntityPlayerMP) event.getEntity());
            }
            else
            {
                NETWORK.sendToServer(new PacketTest(41));

            }
        }
    }

    @SubscribeEvent
    public void onCraftMatrixUpdate(CraftMatrixUpdateEvent event)
    {
        // System.out.println("hook");
        if (event.getRecipeResult() != null)
        {
            if (event.getRecipeResult().getItem() == Items.DIAMOND_PICKAXE)
            {
                event.setRecipeResult(new ItemStack(Items.GOLDEN_APPLE));
            }
            else if (event.getRecipeResult().getItem() == Item.getItemFromBlock(Blocks.CRAFTING_TABLE)
                    && event.getEntityPlayer().isCreative())

            {
                event.setResult(Result.DENY);
            }
        }
    }
}
