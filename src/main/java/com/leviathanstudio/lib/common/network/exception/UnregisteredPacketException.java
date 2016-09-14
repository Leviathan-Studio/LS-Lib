package com.leviathanstudio.lib.common.network.exception;

public class UnregisteredPacketException extends RuntimeException
{

    private static final long serialVersionUID = 7971106706649694950L;

    public UnregisteredPacketException(Class<?> packet)
    {
        super("Unregistered packet: " + packet);
    }
}