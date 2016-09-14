package com.leviathanstudio.lib.common;

import java.awt.Color;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.concurrent.Callable;

import com.leviathanstudio.lib.common.capability.CapabilityDefaultFactory;
import com.leviathanstudio.lib.common.capability.CapabilityDefaultStorage;
import com.leviathanstudio.lib.common.entity.EntityHelper;
import com.leviathanstudio.lib.common.entity.spawn.IEntitySelector;
import com.leviathanstudio.lib.common.entity.spawn.PreventSpawn;
import com.leviathanstudio.lib.common.potion.PotionHelper;
import com.leviathanstudio.lib.common.recipe.RecipeHelper;
import com.leviathanstudio.lib.common.recipe.anvil.AnvilRecipeManager;
import com.leviathanstudio.lib.common.recipe.anvil.IAnvilRecipe;
import com.leviathanstudio.lib.common.server.ServerHelper;
import com.leviathanstudio.lib.common.util.MinecraftUtil;
import com.leviathanstudio.lib.common.world.WorldHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPressurePlate.Sensitivity;
import net.minecraft.block.material.Material;
import net.minecraft.command.ICommand;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityPainting.EnumArt;
import net.minecraft.entity.player.EntityPlayer.SleepResult;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.potion.PotionType;
import net.minecraft.stats.Achievement;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.DimensionType;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.GameRules;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.gen.structure.StructureStrongholdPieces.Stronghold.Door;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.network.FMLEmbeddedChannel;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.ExistingSubstitutionException;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IncompatibleSubstitutionException;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import io.netty.channel.ChannelHandler;

public class LSLRegister
{
    // *********************************************************************************************

    /**
     * Registers a capability to be consumed by others. APIs who define the
     * capability should call this. To retrieve the Capability instance, use
     * the @CapabilityInject annotation.
     * 
     * @param type
     *            The Interface to be registered
     * @param <T>
     *            The Capability class
     */
    public static <T> void registerCapability(Class<T> type)
    {
        CapabilityManager.INSTANCE.register(type, new CapabilityDefaultStorage<T>(), new CapabilityDefaultFactory<T>());
    }

    /**
     * Registers a capability to be consumed by others. APIs who define the
     * capability should call this. To retrieve the Capability instance, use
     * the @CapabilityInject annotation.
     *
     * @param type
     *            The Interface to be registered
     * @param storage
     *            A default implementation of the storage handler.
     * @param factory
     *            A Factory that will produce new instances of the default
     *            implementation.
     * @param <T>
     *            The Capability class
     */
    public static <T> void registerCapability(Class<T> type, Capability.IStorage<T> storage,
            Callable<? extends T> factory)
    {
        CapabilityManager.INSTANCE.register(type, storage, factory);
    }

    /**
     * Registers a capability to be consumed by others. APIs who define the
     * capability should call this. To retrieve the Capability instance, use
     * the @CapabilityInject annotation.
     *
     * @param type
     *            The Interface to be registered
     * @param storage
     *            A default implementation of the storage handler.
     * @param implementation
     *            A default implementation of the interface.
     * @param <T>
     *            The Capability class
     */
    public static <T> void registerCapability(Class<T> type, Capability.IStorage<T> storage,
            final Class<? extends T> implementation)
    {
        CapabilityManager.INSTANCE.register(type, storage, implementation);
    }

    // *********************************************************************************************

    /**
     * Register a dimension
     * 
     * @param id
     *            The id of the dimension
     * @param type
     *            The type of the dimension
     */
    public static void registerDimension(int id, DimensionType type)
    {
        WorldHelper.registerDimension(id, type);
    }

    /**
     * Register a DimensionType
     * 
     * @param name
     *            The enum name
     * @param suffix
     *            The folder suffix
     * @param id
     *            The dimension id
     * @param provider
     *            The world provider
     * @param keepLoaded
     *            Should keep the dimension loaded
     * @return The DimensionType
     */
    public static DimensionType registerDimensionType(String name, String suffix, int id,
            Class<? extends WorldProvider> provider, boolean keepLoaded)
    {
        return WorldHelper.registerDimensionType(name, suffix, id, provider, keepLoaded);
    }

    // *********************************************************************************************

    /**
     * 
     * @param name
     *            The name for the channel
     * @param handlers
     *            Some {@link ChannelHandler} for the channel
     * @return an {@link EnumMap} of the pair of channels. keys are
     *         {@link Side}. There will always be two entries.
     */
    public static EnumMap<Side, FMLEmbeddedChannel> registerChannel(String name, ChannelHandler... handlers)
    {
        return NetworkRegistry.INSTANCE.newChannel(name, handlers);
    }

    /**
     * INTERNAL Create a new channel pair with the specified name and channel
     * handlers. This is used internally in forge and FML
     *
     * @param container
     *            The container to associate the channel with
     * @param name
     *            The name for the channel
     * @param handlers
     *            Some {@link ChannelHandler} for the channel
     * @return an {@link EnumMap} of the pair of channels. keys are
     *         {@link Side}. There will always be two entries.
     */
    public static EnumMap<Side, FMLEmbeddedChannel> registerChannel(ModContainer container, String name,
            ChannelHandler... handlers)
    {
        return NetworkRegistry.INSTANCE.newChannel(container, name, handlers);
    }

    // *********************************************************************************************

    /**
     * Register a new OreDictionnary value
     * 
     * @param name
     *            The ore name
     * @param ore
     *            The item
     */
    public static void registerOre(String name, Item ore)
    {
        OreDictionary.registerOre(name, ore);
    }

    /**
     * Register a new OreDictionnary value
     * 
     * @param name
     *            The ore name
     * @param ore
     *            The block
     */
    public static void registerOre(String name, Block ore)
    {
        OreDictionary.registerOre(name, ore);
    }

    /**
     * Register a new OreDictionnary value
     * 
     * @param name
     *            The ore name
     * @param ore
     *            The stack
     */
    public static void registerOre(String name, ItemStack ore)
    {
        OreDictionary.registerOre(name, ore);
    }

    // *********************************************************************************************

    /**
     * Register a new event listener
     * 
     * @param listener
     *            The listener
     */
    public static void registerEvent(Object listener)
    {
        MinecraftForge.EVENT_BUS.register(listener);
    }

    /**
     * Unregister a new event listener
     * 
     * @param listener
     *            The listener
     */
    public static void unregisterEvent(Object listener)
    {
        MinecraftForge.EVENT_BUS.unregister(listener);
    }

    // *********************************************************************************************

    /**
     * Register a new seed to be dropped when breaking tall grass.
     *
     * @param seed
     *            The item to drop as a seed.
     * @param weight
     *            The relative probability of the seeds, where wheat seeds are
     *            10.
     *
     *            Note: These functions may be going away soon, we're looking
     *            into loot tables....
     */
    public static void addGrassSeed(ItemStack seed, int weight)
    {
        MinecraftForge.addGrassSeed(seed, weight);
    }

    // *********************************************************************************************

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
        EntityHelper.registerEntity(mod, clazz, name, foregroundColor, backgroundColor);
    }

    // *********************************************************************************************

    /**
     * Register the command
     * 
     * @param command
     *            the command to register
     */
    public static void registerServerCommand(ICommand command)
    {
        ServerHelper.registerServerCommand(command);
    }

    /**
     * Replace the command
     * 
     * @param command
     *            the command to replace
     */
    public static void replaceServerCommand(ICommand command)
    {
        ServerHelper.replaceCommand(MinecraftUtil.getServer(), command);
    }

    // *********************************************************************************************

    /**
     * Register the block and the item associated
     * 
     * @param block
     *            The block instance
     * @param name
     *            The register name
     */
    public static void registerBlock(Block block, String name)
    {
        registerBlock(block, new ItemBlock(block), name);
    }

    /**
     * Register the block and the item associated
     * 
     * @param block
     *            The block instance
     * @param itemblock
     *            The ItemStack for the block
     * @param name
     *            The register name
     */
    public static void registerBlock(Block block, ItemBlock itemblock, String name)
    {
        block.setRegistryName(name);
        registerBlock(block, itemblock, block.getRegistryName());
    }

    /**
     * Register the block and the item associated
     * 
     * @param block
     *            The block instance
     * @param itemblock
     *            The ItemStack for the block
     * @param name
     *            The register name
     */
    private static void registerBlock(Block block, ItemBlock itemblock, ResourceLocation name)
    {
        registerBlockOnly(block);
        registerItem(itemblock, name);
    }

    /**
     * Register the block only
     * 
     * @param block
     *            The block instance
     */
    public static void registerBlockOnly(Block block)
    {
        GameRegistry.<Block> register(block);
    }

    /**
     * Register the item
     * 
     * @param item
     *            The item instance
     * @param name
     *            The register name
     */
    public static void registerItem(Item item, ResourceLocation name)
    {
        GameRegistry.<Item> register(item, name);
    }

    // *********************************************************************************************

    /**
     * Add a forced persistent substitution alias for the block or item to
     * another block or item. This will have the effect of using the substituted
     * block or item instead of the original, where ever it is referenced.
     *
     * @param nameToSubstitute
     *            The name to link to (this is the NEW block or item)
     * @param type
     *            The type (Block or Item)
     * @param object
     *            a NEW instance that is type compatible with the existing
     *            instance
     * @throws ExistingSubstitutionException
     *             if someone else has already registered an alias either from
     *             or to one of the names
     * @throws IncompatibleSubstitutionException
     *             if the substitution is incompatible
     */
    public static void addSubstitutionAlias(String nameToSubstitute, GameRegistry.Type type, Object object)
            throws ExistingSubstitutionException
    {
        GameRegistry.addSubstitutionAlias(nameToSubstitute, type, object);
    }

    /**
     * Register a world generator - something that inserts new block types into
     * the world
     *
     * @param generator
     *            the generator
     * @param modGenerationWeight
     *            a weight to assign to this generator. Heavy weights tend to
     *            sink to the bottom of list of world generators (i.e. they run
     *            later)
     */
    public static void registerWorldGenerator(IWorldGenerator generator, int modGenerationWeight)
    {
        GameRegistry.registerWorldGenerator(generator, modGenerationWeight);
    }

    /**
     * Get the fuel value for a given stack
     * 
     * @param stack
     *            The fuel stack
     * @return The fuel value
     */
    public static int getFuelValue(ItemStack stack)
    {
        return GameRegistry.getFuelValue(stack);
    }

    /**
     * Register a new fuel handler
     * 
     * @param handler
     *            The fuel handler
     */
    public static void registerFuelHandler(IFuelHandler handler)
    {
        GameRegistry.registerFuelHandler(handler);
    }

    /**
     * Makes an {@link ItemStack} based on the itemName reference, with supplied
     * meta, stackSize and nbt, if possible
     * 
     * Will return null if the item doesn't exist (because it's not from a
     * loaded mod for example) Will throw a {@link RuntimeException} if the
     * nbtString is invalid for use in an {@link ItemStack}
     *
     * @param itemName
     *            a registry name reference
     * @param meta
     *            the meta
     * @param stackSize
     *            the stack size
     * @param nbtString
     *            an nbt stack as a string, will be processed by
     *            {@link JsonToNBT}
     * @return a new itemstack
     */
    public static ItemStack makeItemStack(String itemName, int meta, int stackSize, String nbtString)
    {
        return GameRegistry.makeItemStack(itemName, meta, stackSize, nbtString);
    }

    public static void registerTileEntity(Class<? extends TileEntity> tileEntityClass)
    {
        registerTileEntity(tileEntityClass, tileEntityClass.getCanonicalName());
    }

    public static void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String id)
    {
        GameRegistry.registerTileEntity(tileEntityClass, id);
    }

    /**
     * Register a tile entity, with alternative TileEntity identifiers. Use with
     * caution! This method allows for you to "rename" the 'id' of the tile
     * entity.
     *
     * @param tileEntityClass
     *            The tileEntity class to register
     * @param id
     *            The primary ID, this will be the ID that the tileentity saves
     *            as
     * @param alternatives
     *            A list of alternative IDs that will also map to this class.
     *            These will never save, but they will load
     */
    public static void registerTileEntityWithAlternatives(Class<? extends TileEntity> tileEntityClass, String id,
            String... alternatives)
    {
        GameRegistry.registerTileEntityWithAlternatives(tileEntityClass, id, alternatives);
    }

    // *********************************************************************************************

    /**
     * Register a new gamerule
     * 
     * @param rules
     *            The GameRules instance that will receive the game rule
     * @param ruleName
     *            The name of the rule
     * @param defaultValue
     *            The default value of the game rule
     * @param type
     *            The type of the game rule value
     */
    public static void registerGamerule(GameRules rules, String ruleName, String defaultValue, GameRules.ValueType type)
    {
        ServerHelper.registerGamerule(rules, ruleName, defaultValue, type);
    }

    // *********************************************************************************************

    /**
     * Remove a crafting recipe
     *
     * @param item
     *            The item to remove
     */

    public static void removeRecipe(Item item)
    {
        RecipeHelper.removeRecipe(item);
    }

    /**
     * Remove a crafting recipe
     *
     * @param block
     *            The block to remove
     */
    public static void removeRecipe(Block block)
    {
        RecipeHelper.removeRecipe(block);
    }

    /**
     * Remove a crafting recipe
     *
     * @param stack
     *            The ItemStack to remove
     */
    public static void removeRecipe(ItemStack stack)
    {
        RecipeHelper.removeRecipe(stack);
    }

    // *********************************************************************************************

    /**
     * Add a new crafting recipe
     * 
     * @param recipe
     *            The IRecipe
     */
    public static void addRecipe(IRecipe recipe)
    {
        GameRegistry.addRecipe(recipe);
    }

    /**
     * Add a new crafting recipe
     * 
     * @param output
     *            The result
     * @param params
     *            The recipe
     */
    public static void addRecipe(ItemStack output, Object... params)
    {
        GameRegistry.addRecipe(output, params);
    }

    /**
     * Add a new shapeless crafting recipe
     * 
     * @param output
     *            The result
     * @param params
     *            The ingredients
     */
    public static void addShapelessRecipe(ItemStack output, Object... params)
    {
        GameRegistry.addShapelessRecipe(output, params);
    }

    // *********************************************************************************************

    /**
     * Add a new ore crafting recipe
     * 
     * @param output
     *            The result
     * @param params
     *            The recipe
     */
    public static void addOreRecipe(ItemStack output, Object... params)
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(output, params));
    }

    /**
     * Add a new shapeless ore crafting recipe
     * 
     * @param output
     *            The result
     * @param params
     *            The ingredients
     */
    public static void addShapelessOreRecipe(ItemStack output, Object... params)
    {
        GameRegistry.addRecipe(new ShapelessOreRecipe(output, params));
    }

    // *********************************************************************************************

    /**
     * Replace a existing recipe
     * 
     * @param result
     *            The result
     * @param recipe
     *            The new recipe
     */
    public static void replaceRecipe(ItemStack result, IRecipe recipe)
    {
        removeRecipe(result);
        addRecipe(recipe);
    }

    /**
     * Replace a existing recipe
     * 
     * @param result
     *            The result
     * @param params
     *            The new recipe
     */
    public static void replaceRecipe(ItemStack result, Object... params)
    {
        removeRecipe(result);
        addRecipe(result, params);
    }

    // *********************************************************************************************

    /**
     * Remove a smelting recipe
     * 
     * @param output
     *            The ItemStack to remove
     */
    public static void removeSmeltingRecipe(ItemStack output)
    {
        RecipeHelper.removeSmeltingRecipe(output);
    }

    /**
     * Remove a smelting recipe
     * 
     * @param output
     *            The item to remove
     * @param damage
     *            The damage of the item
     */
    public static void removeSmeltingRecipe(Item output, int damage)
    {
        RecipeHelper.removeSmeltingRecipe(output, damage);
    }

    /**
     * Remove a smelting recipe
     * 
     * @param output
     *            The item to remove
     */
    public static void removeSmeltingRecipe(Item output)
    {
        RecipeHelper.removeSmeltingRecipe(output);
    }

    /**
     * Remove a smelting recipe
     * 
     * @param output
     *            The block to remove
     */
    public static void removeSmeltingRecipe(Block output)
    {
        RecipeHelper.removeSmeltingRecipe(output);
    }

    // *********************************************************************************************

    /**
     * Add a new smelting recipe
     * 
     * @param input
     *            The input block
     * @param output
     *            The result
     * @param xp
     *            The given experience
     */
    public static void addSmelting(Block input, ItemStack output, float xp)
    {
        GameRegistry.addSmelting(input, output, xp);
    }

    /**
     * Add a new smelting recipe
     * 
     * @param input
     *            The input item
     * @param output
     *            The result
     * @param xp
     *            The given experience
     */
    public static void addSmelting(Item input, ItemStack output, float xp)
    {
        GameRegistry.addSmelting(input, output, xp);
    }

    /**
     * Add a new smelting recipe
     * 
     * @param input
     *            The input stack
     * @param output
     *            The result
     * @param xp
     *            The given experience
     */
    public static void addSmelting(ItemStack input, ItemStack output, float xp)
    {
        GameRegistry.addSmelting(input, output, xp);
    }

    // *********************************************************************************************

    /**
     * Adds a recipe to the registry.
     * 
     * @param recipe
     *            IBrewingRecipe representing the recipe
     * @return true if the recipe was added.
     */
    public static boolean addBrewingRecipe(IBrewingRecipe recipe)
    {
        return BrewingRecipeRegistry.addRecipe(recipe);
    }

    /**
     * Adds a recipe to the registry.
     *
     * @param input
     *            The ItemStack that goes in same slots as the water bottles
     *            would.
     * @param ingredient
     *            The ItemStack that goes in the same slot as nether wart would.
     * @param output
     *            The ItemStack that will replace the input once the brewing is
     *            done.
     * @return true if the recipe was added.
     */
    public static boolean addBrewingRecipe(ItemStack input, ItemStack ingredient, ItemStack output)
    {
        return BrewingRecipeRegistry.addRecipe(input, ingredient, output);
    }

    /**
     * Adds a recipe to the registry.
     *
     * @param input
     *            The ItemStack that goes in same slots as the water bottles
     *            would.
     * @param ingredient
     *            The ItemStack that goes in the same slot as nether wart would.
     * @param output
     *            The ItemStack that will replace the input once the brewing is
     *            done.
     * @return true if the recipe was added.
     */
    public static boolean addBrewingRecipe(ItemStack input, String ingredient, ItemStack output)
    {
        return BrewingRecipeRegistry.addRecipe(input, ingredient, output);
    }

    // *********************************************************************************************

    /**
     * Remove a vanilla brewing recipe
     * 
     * @param stack
     *            The ItemStack to remove
     */
    public static void removeVanillaBrewingRecipe(ItemStack stack)
    {
        RecipeHelper.removeVanillaBrewingRecipe(stack);
    }

    /**
     * Remove a brewing recipe
     * 
     * @param stack
     *            The ItemStack to remove
     */
    public static void removeBrewingRecipe(ItemStack stack)
    {
        RecipeHelper.removeBrewingRecipe(stack);
    }

    /**
     * Remove a brewing recipe
     * 
     * @param item
     *            The item to remove
     */
    public static void removeBrewingRecipe(Item item)
    {
        RecipeHelper.removeBrewingRecipe(item);
    }

    // *********************************************************************************************

    /**
     * Add a new anvil recipe
     * 
     * @param recipe
     *            The anvil recipe
     */
    public static void addAnvilRecipe(IAnvilRecipe recipe)
    {
        AnvilRecipeManager.getInstance().addRecipe(recipe);
    }

    // *********************************************************************************************

    /**
     * Register a new potion
     * 
     * @param id
     *            The potion id
     * @param name
     *            The potion name
     * @param type
     *            The potion type (effect)
     */
    public static void registerPotion(int id, ResourceLocation name, PotionType type)
    {
        PotionHelper.registerPotion(id, name, type);
    }

    // *********************************************************************************************

    /**
     * Registers an achievement page.
     * 
     * @param page
     *            The achievement page
     */
    public static void registerAchievementPage(AchievementPage page)
    {
        AchievementPage.registerAchievementPage(page);
    }

    /**
     * Add Achievements to an page
     * 
     * @param page
     *            The achievement page
     * @param achievements
     *            The achievements
     */
    public static void addAchievements(AchievementPage page, Achievement... achievements)
    {
        LinkedList<Achievement> oldAchievements = new LinkedList<>();
        LinkedList<Achievement> addAchievement = new LinkedList<>();

        oldAchievements = ObfuscationReflectionHelper.getPrivateValue(AchievementPage.class, page, "achievements");

        LinkedList<Achievement> nevAchievements = new LinkedList<>(Arrays.asList(achievements));

        addAchievement.addAll(oldAchievements);
        addAchievement.addAll(nevAchievements);

        ObfuscationReflectionHelper.setPrivateValue(AchievementPage.class, page, addAchievement, "achievements");
    }

    // *********************************************************************************************

    /**
     * Add a new action
     * 
     * @param name
     *            The action name (field name)
     * @return The action
     */
    public static EnumAction addAction(String name)
    {
        return EnumHelper.addAction(name);
    }

    /**
     * Add a new armor material
     * 
     * @param name
     *            The material name (field name)
     * @param textureName
     *            The texture name
     * @param durability
     *            The armor durability factor
     * @param reductionAmounts
     *            The damage reduction factor
     * @param enchantability
     *            The enchantability of the armor
     * @param soundOnEquip
     *            The sound played when the armor is equip
     * @param toughness
     *            The toughness factor
     * @return The armor material
     */
    public static ArmorMaterial addArmorMaterial(String name, String textureName, int durability,
            int[] reductionAmounts, int enchantability, SoundEvent soundOnEquip, float toughness)
    {
        return EnumHelper.addArmorMaterial(name, textureName, durability, reductionAmounts, enchantability,
                soundOnEquip, toughness);
    }

    /**
     * Add a new armor material
     * 
     * @param name
     *            The material name (field name)
     * @param textureName
     *            The texture name
     * @param durability
     *            The armor durability factor
     * @param reductionAmounts
     *            The damage reduction factor
     * @param enchantability
     *            The enchantability of the armor
     * @param soundOnEquip
     *            The sound played when the armor is equip
     * @param toughness
     *            The toughness factor
     * @param repairItem
     *            The item use for repair the armor
     * @return The armor material
     */
    public static ArmorMaterial addArmorMaterial(String name, String textureName, int durability,
            int[] reductionAmounts, int enchantability, SoundEvent soundOnEquip, float toughness, Item repairItem)
    {
        ArmorMaterial material = addArmorMaterial(name, textureName, durability, reductionAmounts, enchantability,
                soundOnEquip, toughness);
        material.customCraftingMaterial = repairItem;
        return material;
    }

    /**
     * Add a new painting
     * 
     * @param name
     *            The art name (field name)
     * @param tile
     *            The painting name
     * @param sizeX
     *            The painting size on x
     * @param sizeY
     *            The painting size on y
     * @param offsetX
     *            The offset texture on x
     * @param offsetY
     *            The offset texture on y
     * @return The painting
     */
    public static EnumArt addArt(String name, String tile, int sizeX, int sizeY, int offsetX, int offsetY)
    {
        return EnumHelper.addArt(name, tile, sizeX, sizeY, offsetX, offsetY);
    }

    /**
     * Add a new creature attribute
     * 
     * @param name
     *            The attribute name (field name)
     * @return The attribute
     */
    public static EnumCreatureAttribute addCreatureAttribute(String name)
    {
        return EnumHelper.addCreatureAttribute(name);
    }

    /**
     * Add a new creature type
     * 
     * @param name
     *            The creature type name (field name)
     * @param typeClass
     *            The creature base class
     * @param maxNumber
     *            The max number of creature by chunk
     * @param material
     *            The material within the creature evolve
     * @param peaceful
     *            Should the creature survive in peaceful mode
     * @param animal
     *            Is the creature an animal
     * @return The creature type
     */
    public static EnumCreatureType addCreatureType(String name, Class<?> typeClass, int maxNumber, Material material,
            boolean peaceful, boolean animal)
    {
        return EnumHelper.addCreatureType(name, typeClass, maxNumber, material, peaceful, animal);
    }

    /**
     * Add a new door
     * 
     * @param name
     *            The door name (field name)
     * @return The door
     */
    public static Door addDoor(String name)
    {
        return EnumHelper.addDoor(name);
    }

    /**
     * Add a new enchantment type
     * 
     * @param name
     *            The enchantment type name (field name)
     * @return The enchantment type
     */
    public static EnumEnchantmentType addEnchantmentType(String name)
    {
        return EnumHelper.addEnchantmentType(name);
    }

    /**
     * Add a new sensitivity for pressure plate
     * 
     * @param name
     *            The sensitivity name (field name)
     * @return The sensitivity
     */
    public static Sensitivity addSensitivity(String name)
    {
        return EnumHelper.addSensitivity(name);
    }

    /**
     * Add a new RayTraceResult type
     * 
     * @param name
     *            The sensitivity name (field name)
     * @return The RayTraceResult type
     */
    public static RayTraceResult.Type addMovingObjectType(String name)
    {
        return EnumHelper.addMovingObjectType(name);
    }

    /**
     * Add a new SkyBlock
     * 
     * @param name
     *            The sky block name (field name)
     * @param lightValue
     *            The light value
     * @return The SkyBlock
     */
    public static EnumSkyBlock addSkyBlock(String name, int lightValue)
    {
        return EnumHelper.addSkyBlock(name, lightValue);
    }

    /**
     * Add a new action status
     * 
     * @param name
     *            The status name (field name)
     * @return The status
     */
    public static SleepResult addStatus(String name)
    {
        return EnumHelper.addStatus(name);
    }

    /**
     * Add a new tool material
     * 
     * @param name
     *            The Tool material name (field name)
     * @param harvestLevel
     *            The harvest level for the tool
     * @param maxUses
     *            The number of use
     * @param efficiency
     *            The tool efficiency
     * @param damage
     *            The damage afflicted to entity
     * @param enchantability
     *            The enchantability of the tool
     * @return The tool material
     */
    public static ToolMaterial addToolMaterial(String name, int harvestLevel, int maxUses, float efficiency,
            float damage, int enchantability)
    {
        return EnumHelper.addToolMaterial(name, harvestLevel, maxUses, efficiency, damage, enchantability);
    }

    /**
     * Add a new tool material
     * 
     * @param name
     *            The Tool material name (field name)
     * @param harvestLevel
     *            The harvest level for the tool
     * @param maxUses
     *            The number of use
     * @param efficiency
     *            The tool efficiency
     * @param damage
     *            The damage afflicted to entity
     * @param enchantability
     *            The enchantability of the tool
     * @param repairMaterial
     *            The item stack use to repair the tool
     * @return The tool material
     */
    public static ToolMaterial addToolMaterial(String name, int harvestLevel, int maxUses, float efficiency,
            float damage, int enchantability, ItemStack repairMaterial)
    {
        ToolMaterial material = addToolMaterial(name, harvestLevel, maxUses, efficiency, damage, enchantability);
        material.setRepairItem(repairMaterial);
        return material;
    }

    /**
     * Add a new rarity
     * 
     * @param name
     *            The rarity name (field name)
     * @param color
     *            The rarity color
     * @param displayName
     *            The rarity display name
     * @return The rarity
     */
    public static EnumRarity addRarity(String name, TextFormatting color, String displayName)
    {
        return EnumHelper.addRarity(name, color, displayName);
    }

    // *********************************************************************************************

    /**
     * Prevent the entity to spawn
     * 
     * @param entity
     *            The entity class
     */
    public static void preventEntitytoSpawn(Class<? extends Entity> entity)
    {
        PreventSpawn.getInstance().preventEntitytoSpawn(entity);
    }

    /**
     * Prevent the entity to spawn
     * 
     * @param entity
     *            The entity class
     * @param selector
     *            The selector for the entity
     */
    public static void preventEntitytoSpawn(Class<? extends Entity> entity, IEntitySelector selector)
    {
        PreventSpawn.getInstance().preventEntitytoSpawn(entity, selector);
    }
}
