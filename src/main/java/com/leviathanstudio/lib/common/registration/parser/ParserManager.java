package com.leviathanstudio.lib.common.registration.parser;

import java.util.HashMap;

import com.google.common.collect.Maps;
import com.leviathanstudio.lib.common.registration.RegistrationProvider;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.ResourceLocation;

public class ParserManager
{
    private static HashMap<Class<?>, IParser> parsers = Maps.newHashMap();
    private static boolean                    isInit  = false;

    public static void init()
    {
        if (isInit)
            return;

        add(Long.class, new IExceptionParser()
        {
            @Override
            public Object parse(String value)
            {
                return Long.parseLong(value);
            }
        });

        add(Integer.class, new IExceptionParser()
        {
            @Override
            public Object parse(String value)
            {
                return Integer.parseInt(value);
            }
        });

        add(Short.class, new IExceptionParser()
        {
            @Override
            public Object parse(String value)
            {
                return Short.parseShort(value);
            }
        });

        add(Byte.class, new IExceptionParser()
        {
            @Override
            public Object parse(String value)
            {
                return Byte.parseByte(value);
            }
        });

        add(Float.class, new IExceptionParser()
        {
            @Override
            public Object parse(String value)
            {
                return Float.parseFloat(value);
            }
        });

        add(Double.class, new IExceptionParser()
        {
            @Override
            public Object parse(String value)
            {
                return Double.parseDouble(value);
            }
        });

        add(Boolean.class, new IParser()
        {
            @Override
            public Object parse(String value)
            {
                return Boolean.parseBoolean(value);
            }

            @Override
            public boolean canParse(String value)
            {
                return value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false");
            }
        });

        add(String.class, new IParser()
        {
            @Override
            public Object parse(String value)
            {
                return value;
            }

            @Override
            public boolean canParse(String value)
            {
                return true;
            }
        });

        add(Character.class, new IParser()
        {
            @Override
            public Object parse(String value)
            {
                return value.toCharArray()[0];
            }

            @Override
            public boolean canParse(String value)
            {
                return value.length() == 1;
            }
        });

        add(Material.class, new MaterialUtil());

        add(Block.class, new IParser()
        {
            @Override
            public Object parse(String value)
            {
                if (value.endsWith("!"))
                    return RegistrationProvider.blocks.get(value.substring(0, value.length() - 1));
                else
                    return Block.REGISTRY.getObject(new ResourceLocation(value));
            }

            @Override
            public boolean canParse(String value)
            {
                if (value.endsWith("!"))
                    return RegistrationProvider.blocks.containsKey(value.substring(0, value.length() - 1));
                else
                    return Block.REGISTRY.containsKey(new ResourceLocation(value));
            }
        });

        add(Item.class, new IParser()
        {
            @Override
            public Object parse(String value)
            {
                if (value.endsWith("!"))
                    return RegistrationProvider.items.get(value.substring(0, value.length() - 1));
                else
                    return Item.REGISTRY.getObject(new ResourceLocation(value));
            }

            @Override
            public boolean canParse(String value)
            {
                if (value.endsWith("!"))
                    return RegistrationProvider.items.containsKey(value.substring(0, value.length() - 1));
                else
                    return Item.REGISTRY.containsKey(new ResourceLocation(value));
            }
        });

        add(ToolMaterial.class, new IObjectParser()
        {
            @Override
            public Object parse(String value)
            {
                return ToolMaterial.valueOf(value.toUpperCase());
            }
        });

        add(ArmorMaterial.class, new IObjectParser()
        {
            @Override
            public Object parse(String value)
            {
                return ArmorMaterial.valueOf(value.toUpperCase());
            }
        });

        add(EnumDyeColor.class, new IObjectParser()
        {
            @Override
            public Object parse(String value)
            {
                return EnumDyeColor.valueOf(value.toUpperCase());
            }
        });

        isInit = true;
    }

    public static boolean add(Class<?> type, IParser parser)
    {
        boolean added = false;
        if (type != null && parser != null && !parsers.containsKey(type))
        {
            added = parsers.put(type, parser) == null;
            if (added)
                parser.init();
        }
        return added;
    }

    public static IParser getParser(Class<?> c)
    {
        return parsers.get(c);
    }

    public static boolean canParse(String value, Class<?> c)
    {
        value = patchValue(value, c);
        IParser parser = getParser(c);
        if (parser != null)
            return parser.canParse(value);
        return false;
    }

    @SuppressWarnings("unchecked")
    public static <T> T parse(String value, Class<T> c)
    {
        value = patchValue(value, c);
        IParser parser = getParser(c);
        if (parser != null)
            return (T) parser.parse(value);
        return null;
    }

    private static <T> String patchValue(String value, Class<T> c)
    {
        if (c.getSimpleName().equalsIgnoreCase(value.split(":")[0]))
            return value.substring(value.indexOf(":") + 1, value.length());
        return value;
    }
}
