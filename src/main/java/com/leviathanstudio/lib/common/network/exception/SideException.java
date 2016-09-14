package com.leviathanstudio.lib.common.network.exception;

public class SideException extends RuntimeException
{
    private static final long serialVersionUID = -6863695714042962705L;

    public SideException(String message)
    {
        super("Wrong side: " + message);
    }
}
