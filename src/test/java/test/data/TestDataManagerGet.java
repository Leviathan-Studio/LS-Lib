package test.data;

import static org.hamcrest.CoreMatchers.*;

import java.util.UUID;

import com.leviathanstudio.lib.common.data.DataManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDataManagerGet
{
    DataManager data;

    @Before
    public void setupUp()
    {
        data = DataManager.createDataManager();
        data.addBoolean("boolean1", false);
        data.addByte("byte1", 1);
        data.addShort("short1", 2);
        data.addInteger("int1", 3);
        data.addLong("long1", 4);
        data.addFloat("float1", 5);
        data.addDouble("double1", 6);
        data.addCharacter("char1", 'c');
        data.addString("String1", "S");
        data.addUUID("UUID1", UUID.fromString("0c22e844-4ecb-48d4-a3c3-f083ddb1df3b"));

    }

    @Test(expected = IllegalArgumentException.class)
    public void getEntry1()
    {
        data.getEntry("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getEntry2()
    {
        data.getEntry(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void hasEntry1()
    {
        data.hasEntry("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void hasEntry2()
    {
        data.hasEntry(null);
    }

    @Test
    public void getBoolean1()
    {
        boolean expected = false;
        boolean value = data.getBoolean("boolean1");

        Assert.assertThat("wrong initialization", value, is(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getBoolean2()
    {
        data.getBoolean("boolean2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getBoolean3()
    {
        data.getByte("boolean1");
    }

    @Test
    public void getByte1()
    {
        byte expected = 1;
        byte value = data.getByte("byte1");

        Assert.assertThat("wrong initialization", value, is(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByte2()
    {
        data.getByte("byte2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByte3()
    {
        data.getBoolean("byte1");
    }

    @Test
    public void getShort1()
    {
        short expected = 2;
        short value = data.getShort("short1");

        Assert.assertThat("wrong initialization", value, is(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getShort2()
    {
        data.getShort("short2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getShort3()
    {
        data.getBoolean("short1");
    }

    @Test
    public void getInteger1()
    {
        int expected = 3;
        int value = data.getInteger("int1");

        Assert.assertThat("wrong initialization", value, is(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getInteger2()
    {
        data.getInteger("int2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getInteger3()
    {
        data.getBoolean("int1");
    }

    @Test
    public void getLong1()
    {
        long expected = 4;
        long value = data.getLong("long1");

        Assert.assertThat("wrong initialization", value, is(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getLong2()
    {
        data.getLong("long2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getLong3()
    {
        data.getBoolean("long1");
    }

    @Test
    public void getFloat1()
    {
        float expected = 5;
        float value = data.getFloat("float1");

        Assert.assertThat("wrong initialization", value, is(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getFloat2()
    {
        data.getFloat("float2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getFloat3()
    {
        data.getBoolean("float1");
    }

    @Test
    public void getDouble1()
    {
        double expected = 6;
        double value = data.getDouble("double1");

        Assert.assertThat("wrong initialization", value, is(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getDouble2()
    {
        data.getDouble("double2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getDouble3()
    {
        data.getBoolean("double1");
    }

    @Test
    public void getCharacter1()
    {
        char expected = 'c';
        char value = data.getCharacter("char1");

        Assert.assertThat("wrong initialization", value, is(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCharacter2()
    {
        data.getCharacter("char2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCharacter3()
    {
        data.getBoolean("char1");
    }

    @Test
    public void getString1()
    {
        String expected = "S";
        String value = data.getString("String1");

        Assert.assertThat("wrong initialization", value, is(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getString2()
    {
        data.getString("String2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getString3()
    {
        data.getBoolean("String1");
    }

    @Test
    public void getUUID1()
    {
        UUID expected = UUID.fromString("0c22e844-4ecb-48d4-a3c3-f083ddb1df3b");
        UUID value = data.getUUID("UUID1");

        Assert.assertThat("wrong initialization", value, is(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getUUID2()
    {
        data.getUUID("UUID2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getUUID3()
    {
        data.getBoolean("UUID1");
    }
}
