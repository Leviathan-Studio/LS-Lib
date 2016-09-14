package com.leviathanstudio.lib.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionUtil
{
    public static Object call(Method method, Object... params)
    {
        return call(null, method, params);
    }

    public static Object call(Object instance, Method method, Object... params)
    {
        try
        {
            return method.invoke(instance, params);
        } catch (Exception e)
        {
            throw new RuntimeException(
                    String.format("Unable to call method %s", method == null ? null : method.getName()));
        }
    }

    public static void setFinalField(Field field, Object instance, Object value) throws RuntimeException
    {
        field.setAccessible(true);
        try
        {
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            field.set(instance, value);
        } catch (ReflectiveOperationException e)
        {
            throw new RuntimeException(
                    String.format("Cannot set field %s with value %s", field.getName(), value.toString()));
        }
    }

    public static Object getValue(Object instance, Field field)
    {
        try
        {
            return field.get(instance);
        } catch (IllegalAccessException e)
        {
            return null;
        }
    }

    public static void setValue(Object instance, Field field, Object value)
    {
        try
        {
            field.set(instance, value);
        } catch (IllegalAccessException e)
        {
        }
    }

    public static <E> boolean getBoolean(Class<? super E> classToAccess, E instance, String fieldName)
    {
        Object obj = getPrivateValue(classToAccess, instance, fieldName);
        if (obj instanceof Boolean)
            return (boolean) obj;
        return false;
    }

    public static <E> double getDouble(Class<? super E> classToAccess, E instance, String fieldName)
    {
        Object obj = getPrivateValue(classToAccess, instance, fieldName);
        if (obj instanceof Double)
            return (double) obj;
        return 0;
    }

    public static <E> float getFloat(Class<? super E> classToAccess, E instance, String fieldName)
    {
        Object obj = getPrivateValue(classToAccess, instance, fieldName);
        if (obj instanceof Float)
            return (float) obj;
        return 0;
    }

    public static <E> int getInteger(Class<? super E> classToAccess, E instance, String fieldName)
    {
        Object obj = getPrivateValue(classToAccess, instance, fieldName);
        if (obj instanceof Integer)
            return (int) obj;
        return 0;
    }

    public static <E> long getLong(Class<? super E> classToAccess, E instance, String fieldName)
    {
        Object obj = getPrivateValue(classToAccess, instance, fieldName);
        if (obj instanceof Long)
            return (long) obj;
        return 0;
    }

    public static <E> short getShort(Class<? super E> classToAccess, E instance, String fieldName)
    {
        Object obj = getPrivateValue(classToAccess, instance, fieldName);
        if (obj instanceof Short)
            return (short) obj;
        return 0;
    }

    public static <E> byte getbyte(Class<? super E> classToAccess, E instance, String fieldName)
    {
        Object obj = getPrivateValue(classToAccess, instance, fieldName);
        if (obj instanceof Byte)
            return (byte) obj;
        return 0;
    }

    public static <E> char getCharacter(Class<? super E> classToAccess, E instance, String fieldName)
    {
        Object obj = getPrivateValue(classToAccess, instance, fieldName);
        if (obj instanceof Character)
            return (char) obj;
        return 0;
    }

    public static <E> String getString(Class<? super E> classToAccess, E instance, String fieldName)
    {
        Object obj = getPrivateValue(classToAccess, instance, fieldName);
        if (obj instanceof String)
            return (String) obj;
        return "";
    }

    public static <T, E> void setValue(Class<? super T> classToAccess, T instance, E value, String fieldName)
    {
        setPrivateValue(classToAccess, instance, value, fieldName);
    }

    // Bases methods
    public static Field findField(Class<?> clazz, String... fieldNames)
    {
        Exception failed = null;
        for (String fieldName : fieldNames)
        {
            try
            {
                Field f = clazz.getDeclaredField(fieldName);
                f.setAccessible(true);
                return f;
            } catch (Exception e)
            {
                failed = e;
            }
        }
        throw new UnableToFindFieldException(fieldNames, failed);
    }

    public static <T, E> void setPrivateValue(Class<? super T> classToAccess, T instance, E value, int fieldIndex)
    {
        try
        {
            Field f = classToAccess.getDeclaredFields()[fieldIndex];
            f.setAccessible(true);
            f.set(instance, value);
        } catch (Exception e)
        {
            throw new UnableToAccessFieldException(new String[0], e);
        }
    }

    public static <T, E> void setPrivateValue(Class<? super T> classToAccess, T instance, E value, String... fieldNames)
    {
        try
        {
            findField(classToAccess, fieldNames).set(instance, value);
        } catch (Exception e)
        {
            throw new UnableToAccessFieldException(fieldNames, e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T, E> T getPrivateValue(Class<? super E> classToAccess, E instance, int fieldIndex)
    {
        try
        {
            Field f = classToAccess.getDeclaredFields()[fieldIndex];
            f.setAccessible(true);
            return (T) f.get(instance);
        } catch (Exception e)
        {
            throw new UnableToAccessFieldException(new String[0], e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T, E> T getPrivateValue(Class<? super E> classToAccess, E instance, String... fieldNames)
    {
        try
        {
            return (T) findField(classToAccess, fieldNames).get(instance);
        } catch (Exception e)
        {
            throw new UnableToAccessFieldException(fieldNames, e);
        }
    }

    @SuppressWarnings("unchecked")
    public static Class<? super Object> getClass(ClassLoader loader, String... classNames)
    {
        Exception err = null;
        for (String className : classNames)
        {
            try
            {
                return (Class<? super Object>) Class.forName(className, false, loader);
            } catch (Exception e)
            {
                err = e;
            }
        }

        throw new UnableToFindClassException(classNames, err);
    }

    public static <E> Method findMethod(Class<? super E> clazz, E instance, String[] methodNames,
            Class<?>... methodTypes)
    {
        Exception failed = null;
        for (String methodName : methodNames)
        {
            try
            {
                Method m = clazz.getDeclaredMethod(methodName, methodTypes);
                m.setAccessible(true);
                return m;
            } catch (Exception e)
            {
                failed = e;
            }
        }
        throw new UnableToFindMethodException(methodNames, failed);
    }

    // Exceptions
    public static class UnableToFindMethodException extends RuntimeException
    {
        private static final long serialVersionUID = 1L;
        @SuppressWarnings("unused")
        private String[]          methodNames;

        public UnableToFindMethodException(String[] methodNames, Exception failed)
        {
            super(failed);
            this.methodNames = methodNames;
        }

    }

    public static class UnableToFindClassException extends RuntimeException
    {
        private static final long serialVersionUID = 1L;
        @SuppressWarnings("unused")
        private String[]          classNames;

        public UnableToFindClassException(String[] classNames, Exception err)
        {
            super(err);
            this.classNames = classNames;
        }

    }

    public static class UnableToAccessFieldException extends RuntimeException
    {
        private static final long serialVersionUID = 1L;
        @SuppressWarnings("unused")
        private String[]          fieldNameList;

        public UnableToAccessFieldException(String[] fieldNames, Exception e)
        {
            super(e);
            this.fieldNameList = fieldNames;
        }
    }

    public static class UnableToFindFieldException extends RuntimeException
    {
        private static final long serialVersionUID = 1L;
        @SuppressWarnings("unused")
        private String[]          fieldNameList;

        public UnableToFindFieldException(String[] fieldNameList, Exception e)
        {
            super(e);
            this.fieldNameList = fieldNameList;
        }
    }

    public static boolean isInstanceof(Class<?> left, Class<?> right)
    {
        return right.isAssignableFrom(left);
    }
}
