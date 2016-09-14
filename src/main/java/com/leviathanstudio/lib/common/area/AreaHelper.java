package com.leviathanstudio.lib.common.area;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class AreaHelper
{
    /**
     * create a new area
     * 
     * @param x1
     *            First x position
     * @param y1
     *            First y position
     * @param z1
     *            First z position
     * @param x2
     *            Second x position
     * @param y2
     *            Second y position
     * @param z2
     *            Second z position
     * @return The area
     */
    public static AxisAlignedBB squareArea(double x1, double y1, double z1, double x2, double y2, double z2)
    {
        return new AxisAlignedBB(x1, y1, z1, x2, y2, z2);
    }

    /**
     * 
     * @param pos1
     *            The first set of coordinates
     * @param pos2
     *            The second set of coordinates
     * @return The area
     */
    public static AxisAlignedBB squareArea(BlockPos pos1, BlockPos pos2)
    {
        return squareArea(pos1.getX(), pos1.getY(), pos1.getZ(), pos2.getX(), pos1.getY(), pos1.getZ());
    }
}
