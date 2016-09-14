package com.leviathanstudio.lib.common.network;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.leviathanstudio.lib.common.LSLRegister;
import com.leviathanstudio.lib.common.core.LSLib;
import com.leviathanstudio.lib.common.network.packet.AbstractPacket;
import com.leviathanstudio.lib.common.util.ModUtil;
import com.leviathanstudio.lib.common.util.ReflectionUtil;

import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.network.FMLEmbeddedChannel;
import net.minecraftforge.fml.relauncher.Side;

import io.netty.channel.ChannelHandler;

@ChannelHandler.Sharable
public class LSLNetwork extends SendPacketImpl
{

    private final String                                      modid;
    private final EnumMap<Side, FMLEmbeddedChannel>           channels;
    private final LSLMessageHandler                           messageHandler;
    private final LSLChannelHandler                           channelHandler;
    private final LinkedList<Class<? extends AbstractPacket>> packets;

    private boolean                                           isflipped = false;

    public LSLNetwork(String modid)
    {
        this(modid, modid);
    }

    public LSLNetwork(String modid, String channelName)
    {
        this.modid = modid;
        this.packets = new LinkedList<>();
        this.messageHandler = new LSLMessageHandler();
        this.channelHandler = new LSLChannelHandler();
        this.channels = LSLRegister.registerChannel(channelName, this.messageHandler, channelHandler);
    }

    /**
     * Register the packets with its name
     * 
     * @param packetPath
     *            The path to get the class
     * @return success True if all the process is done, else false
     * @throws ClassNotFoundException
     *             Throw of the class is not found
     */
    @SuppressWarnings("unchecked")
    public boolean registerPacketByPath(String packetPath) throws ClassNotFoundException
    {
        Class<?> c = Class.forName(packetPath);
        if (ReflectionUtil.isInstanceof(c, AbstractPacket.class))
        {
            return this.registerPacket((Class<? extends AbstractPacket>) c);
        }
        return false;
    }

    /**
     * Register all the packets in a given location
     * 
     * @param packetsPackage
     *            The path to the package
     * @return success True if all the process is done, else false
     */
    public boolean registerPacket(String packetsPackage)
    {
        ModContainer mod = ModUtil.getModContainer(this.modid);
        try
        {
            // Folder case
            if (mod.getSource().isDirectory())
            {
                File packetsDir = new File(mod.getSource(), packetsPackage.replace(".", "/"));
                String[] packetList = packetsDir.list();

                for (String packet : packetList)
                {
                    packet = packet.replace(".class", "");
                    registerPacketByPath(packetsPackage + "." + packet);
                }
            }
            else
            {
                // Zip case
                ZipFile zipFile = new ZipFile(mod.getSource());
                Enumeration<? extends ZipEntry> entries = zipFile.entries();

                while (entries.hasMoreElements())
                {
                    ZipEntry entry = entries.nextElement();
                    if (entry.getName().startsWith(packetsPackage.replace(".", "/"))
                            && entry.getName().endsWith(".class"))
                    {
                        String packet = entry.getName().replace(".class", "").replace("/", ".");
                        registerPacketByPath(packet);

                    }
                }
                zipFile.close();
            }
            return true;
        } catch (IOException e)
        {
            LSLib.LOGGER.error("Failed to register packet for the mod : " + mod.getModId());
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            LSLib.LOGGER.error("Failed to register packet for the mod : " + mod.getModId());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Register packets
     * 
     * @param packets
     *            The classes of the packets to register
     * @return success True if all the process is done, else false
     */
    @SuppressWarnings("unchecked")
    public boolean registerPacket(final Class<? extends AbstractPacket>... packets)
    {
        boolean res = true;
        for (Class<? extends AbstractPacket> packet : packets)
        {
            if (!registerPacket(packet))
                res = false;
        }
        return res;
    }

    /**
     * Register a packet
     * 
     * @param packet
     *            The class of the packet to register
     * @return success True if all the process is done, else false
     */
    public boolean registerPacket(final Class<? extends AbstractPacket> packet)
    {
        if (this.packets.size() > 256)
        {
            Logger.getLogger(LSLib.MODID).log(Level.SEVERE, "A Packet length was over the limit of 256!");
            return false;
        }
        if (this.packets.contains(packet))
        {
            Logger.getLogger(LSLib.MODID).log(Level.SEVERE, "The Abstract Packet have already been registered!");
            return false;
        }
        if (this.isflipped)
        {
            Logger.getLogger(LSLib.MODID).log(Level.SEVERE,
                    "A Packet have been registered while the packet have been flip!");
            return false;
        }
        // Add Packet
        this.packets.add(packet);

        return true;
    }

    /**
     * Method to call when all packet have been register and before send one of
     * them Ensures that packet discriminators are common between server and
     * client by using logical sorting
     */
    public void flip()
    {
        if (this.isflipped)
        {
            return;
        }

        // Sort
        Collections.sort(this.packets, (clazz1, clazz2) ->
        {
            int com = String.CASE_INSENSITIVE_ORDER.compare(clazz1.getCanonicalName(), clazz2.getCanonicalName());
            if (com == 0)
            {
                com = clazz1.getCanonicalName().compareTo(clazz2.getCanonicalName());
            }
            return com;
        });

        // Add
        for (int i = 0; i < this.packets.size(); i++)
        {
            this.messageHandler.addDiscriminator(i, this.packets.get(i));
        }

        this.isflipped = true;
    }

    @Override
    public EnumMap<Side, FMLEmbeddedChannel> getChannels()
    {
        return this.channels;
    }

    @Override
    public List<Class<? extends AbstractPacket>> getPackets()
    {
        return this.packets;
    }

}