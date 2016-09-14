package com.leviathanstudio.lib.common.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.annotation.Nullable;

import com.leviathanstudio.lib.common.registration.parser.ParserManager;

public class InstanceCreator
{
    /**
     * Create a new instance
     * 
     * @param object
     *            The Object class
     * @param args
     *            The arguments for the constructor
     * @param <T>
     *            The type of the object
     * @return The new instance or null
     */
    @SuppressWarnings("unchecked")
    @Nullable
    public static <T> T createInstance(Class<? extends T> object, String[] args)
    {
        // Construct
        Object[] argsInstance = new Object[args.length];
        try
        {
            // Get all the visible constructor for the class
            Constructor<?>[] constructors = object.getConstructors();
            Constructor<? extends T> constructor = null;

            // Variable use to know if we find a valid constructor
            boolean find = false;

            // Check each constructor
            for (int i = 0; i < constructors.length && !find; i++)
            {
                boolean ok = true;

                // Get the current constructor to test
                Constructor<?> constr = constructors[i];

                // Get the constructor arguments
                Class<?>[] params = constr.getParameterTypes();

                // Arguments list size not equals, do not process
                if (params.length != args.length)
                    continue;

                // For each argument test if it's acceptable
                for (int j = 0; j < params.length && ok; j++)
                {
                    // If can't parse notice it else initialize the argument
                    if (!ParserManager.canParse(args[j], params[j]))
                        ok = false;
                    else
                        argsInstance[j] = ParserManager.parse(args[j], params[j]);
                }

                // If all arguments match, then we have find our constructor
                find = ok;

                // Set the constructor to use
                if (find)
                    constructor = (Constructor<? extends T>) constr;
            }

            // If find a constructor then initialize it
            if (constructor != null)
                return constructor.newInstance(argsInstance);

        } catch (SecurityException e)
        {
            e.printStackTrace();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Create a new instance
     * 
     * @param object
     *            The Object class
     * @param args
     *            The arguments for the constructor
     * @param <T>
     *            The type of the object
     * @return The new instance or null
     */
    @SuppressWarnings("unchecked")
    @Nullable
    public static <T> T createInstance(Class<? extends T> object, Object... args)
    {
        // Get arguments class
        Class<?>[] argsClass = new Class[args.length];
        for (int i = 0; i < args.length; i++)
        {
            argsClass[i] = args[i].getClass();
        }

        // Construct
        try
        {
            // Get all the visible constructor for the class
            Constructor<?>[] constructors = object.getConstructors();
            Constructor<? extends T> constructor = null;

            // Variable use to know if we find a valid constructor
            boolean find = false;

            // Check each constructor
            for (int i = 0; i < constructors.length && !find; i++)
            {
                boolean ok = true;

                // Get the current constructor to test
                Constructor<?> constr = constructors[i];

                // Get the constructor arguments
                Class<?>[] params = constr.getParameterTypes();

                // Arguments list size not equals, do not process
                if (params.length != args.length)
                    continue;

                // For each argument test if it's acceptable
                for (int j = 0; j < params.length && ok; j++)
                {
                    if (!ReflectionUtil.isInstanceof(argsClass[j], params[j]))
                        ok = false;
                }

                // If all arguments match, then we have find our constructor
                find = ok;

                // Set the constructor to use
                if (find)
                    constructor = (Constructor<? extends T>) constr;
            }

            // If find a constructor then initialize it
            if (constructor != null)
                return constructor.newInstance(args);

        } catch (SecurityException e)
        {
            e.printStackTrace();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
