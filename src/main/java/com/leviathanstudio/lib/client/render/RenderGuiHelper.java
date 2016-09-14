package com.leviathanstudio.lib.client.render;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Project;

public class RenderGuiHelper
{
    /**
     * Prepare render. Load block texture map. Must be call before start using
     * draw block method
     * 
     * @param mc
     *            The Minecraft instance
     */
    public static void preInitRender(Minecraft mc)
    {
        GlStateManager.pushMatrix();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enableAlpha();
        GL11.glEnable(GL11.GL_NORMALIZE);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        mc.getRenderManager().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
    }

    /**
     * Set default opengl state. Must be call after using draw block method
     */
    public static void postInitRender()
    {
        GlStateManager.disableBlend();
        GlStateManager.disableAlpha();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.popMatrix();
    }

    // *********************************************************************************************

    /**
     * Draw a block
     * 
     * @param mc
     *            The Minecraft instance
     * @param blockrendererdispatcher
     *            The Block Renderer Dispatcher
     * @param blockState
     *            The state of the block
     * @param x
     *            The x position
     * @param y
     *            The y position
     * @param z
     *            The z position (zLevel)
     * @param rotX
     *            The x rotation
     * @param rotY
     *            The y rotation
     * @param rotZ
     *            The z rotation
     * @param scale
     *            The scale of the block
     */
    public static void draw3DBlock(Minecraft mc, BlockRendererDispatcher blockrendererdispatcher,
            IBlockState blockState, float x, float y, float z, float rotX, float rotY, float rotZ, float scale)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        GlStateManager.rotate(rotX, 1, 0, 0);
        GlStateManager.rotate(rotY, 0, 1, 0);
        GlStateManager.rotate(rotZ, 0, 0, 1);

        GlStateManager.scale(scale, scale, scale);
        blockrendererdispatcher.renderBlockBrightness(blockState, 0.75F);
        GlStateManager.translate(0.0F, 0.0F, 1.0F);
        GlStateManager.popMatrix();
    }

    /**
     * Draw a block
     * 
     * @param mc
     *            The Minecraft instance
     * @param blockrendererdispatcher
     *            The Block Renderer Dispatcher
     * @param block
     *            The block
     * @param x
     *            The x position
     * @param y
     *            The y position
     * @param z
     *            The z position (zLevel)
     * @param rotX
     *            The x rotation
     * @param rotY
     *            The y rotation
     * @param rotZ
     *            The z rotation
     * @param scale
     *            The scale of the block
     */
    public static void draw3DBlock(Minecraft mc, BlockRendererDispatcher blockrendererdispatcher, Block block, float x,
            float y, float z, float rotX, float rotY, float rotZ, float scale)
    {
        draw3DBlock(mc, blockrendererdispatcher, block.getDefaultState(), x, y, z, rotX, rotY, rotZ, scale);
    }

    // *********************************************************************************************

    /**
     * Draw an ItemStack
     * 
     * @param render
     *            The RenderItem instance
     * @param stack
     *            The ItemStack to render
     * @param x
     *            The x position
     * @param y
     *            The y position
     * @param zLevel
     *            The zLevel
     */
    public static void drawItemStack(RenderItem render, ItemStack stack, int x, int y, int zLevel)
    {
        GlStateManager.pushMatrix();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.disableLighting();
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableColorMaterial();
        GlStateManager.enableLighting();

        float oldZLevel = render.zLevel;

        render.zLevel = zLevel;
        render.renderItemAndEffectIntoGUI(stack, x, y);
        render.zLevel = oldZLevel;

        GlStateManager.disableLighting();

        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.popMatrix();
    }

    // *********************************************************************************************

    /**
     * 
     * @param mc
     *            The Minecraft instance
     * @param texture
     *            The model texture
     * @param model
     *            The model to draw
     * @param posX
     *            The x position of the model
     * @param posY
     *            The y position of the model
     * @param posZ
     *            The z position of the model
     * @param scale
     *            The scale of the model
     */
    public static void draw3DModel(Minecraft mc, ResourceLocation texture, ModelBase model, float posX, float posY,
            float posZ, float scale)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.pushMatrix();
        GlStateManager.matrixMode(5889);
        GlStateManager.pushMatrix();
        GlStateManager.loadIdentity();
        ScaledResolution scaledresolution = new ScaledResolution(mc);
        GlStateManager.viewport((scaledresolution.getScaledWidth() - 320) / 2 * scaledresolution.getScaleFactor(),
                (scaledresolution.getScaledHeight() - 240) / 2 * scaledresolution.getScaleFactor(),
                320 * scaledresolution.getScaleFactor(), 240 * scaledresolution.getScaleFactor());
        GlStateManager.translate(-0.34F, 0.23F, 0.0F);
        Project.gluPerspective(90.0F, 1.3333334F, 9.0F, 80.0F);
        GlStateManager.matrixMode(5888);
        GlStateManager.loadIdentity();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.translate(0.0F, 0F, -13.0F);
        GlStateManager.scale(scale, scale, scale);

        mc.getTextureManager().bindTexture(texture);
        GlStateManager.rotate(20.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.translate(posX, posY, posZ);
        GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);

        GlStateManager.enableRescaleNormal();
        model.render((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        GlStateManager.disableRescaleNormal();

        RenderHelper.disableStandardItemLighting();
        GlStateManager.matrixMode(5889);
        GlStateManager.viewport(0, 0, mc.displayWidth, mc.displayHeight);
        GlStateManager.popMatrix();
        GlStateManager.matrixMode(5888);
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
    }

}
