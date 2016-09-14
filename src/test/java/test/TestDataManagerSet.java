package test;

import static org.hamcrest.CoreMatchers.*;

import java.util.UUID;

import com.leviathanstudio.lib.common.data.DataManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDataManagerSet
{
    DataManager data;

    @Before
    public void setupUp()
    {
        data = DataManager.createDataManager();
    }

    @Test
    public void setBoolean1()
    {
        boolean expected1 = false;
        boolean expected2 = true;
        boolean expected3 = true;

        data.addBoolean("boolean1", false);

        boolean value1 = data.getBoolean("boolean1");

        boolean value2 = data.setBoolean("boolean1", true);

        boolean value3 = data.getBoolean("boolean1");

        Assert.assertThat("wrong initialization", value1, is(expected1));
        Assert.assertThat("not change", value2, is(expected2));
        Assert.assertThat("wrong value", value3, is(expected3));
    }

    @Test
    public void setBoolean2()
    {
        boolean expected1 = false;
        boolean expected2 = false;
        boolean expected3 = false;

        data.addBoolean("boolean1", false);

        boolean value1 = data.getBoolean("boolean1");

        boolean value2 = data.setBoolean("boolean1", false);

        boolean value3 = data.getBoolean("boolean1");

        Assert.assertThat("wrong initialization", value1, is(expected1));
        Assert.assertThat("not change", value2, is(expected2));
        Assert.assertThat("wrong value", value3, is(expected3));
    }

    @Test
    public void setByte1()
    {
        byte expected1 = 2;
        boolean expected2 = true;
        byte expected3 = 42;

        data.addByte("byte1", 2);

        byte value1 = data.getByte("byte1");

        boolean value2 = data.setByte("byte1", 42);

        byte value3 = data.getByte("byte1");

        Assert.assertThat("wrong initialization", value1, is(expected1));
        Assert.assertThat("not change", value2, is(expected2));
        Assert.assertThat("wrong value", value3, is(expected3));
    }

    @Test
    public void setByte2()
    {
        byte expected1 = 2;
        boolean expected2 = false;
        byte expected3 = 2;

        data.addByte("byte1", 2);

        byte value1 = data.getByte("byte1");

        boolean value2 = data.setByte("byte1", 2);

        byte value3 = data.getByte("byte1");

        Assert.assertThat("wrong initialization", value1, is(expected1));
        Assert.assertThat("not change", value2, is(expected2));
        Assert.assertThat("wrong value", value3, is(expected3));
    }

    @Test
    public void setShort1()
    {
        short expected1 = 2;
        boolean expected2 = true;
        short expected3 = 42;

        data.addShort("short1", 2);

        short value1 = data.getShort("short1");

        boolean value2 = data.setShort("short1", 42);

        short value3 = data.getShort("short1");

        Assert.assertThat("wrong initialization", value1, is(expected1));
        Assert.assertThat("not change", value2, is(expected2));
        Assert.assertThat("wrong value", value3, is(expected3));
    }

    @Test
    public void setShort2()
    {
        short expected1 = 2;
        boolean expected2 = false;
        short expected3 = 2;

        data.addShort("short1", 2);

        short value1 = data.getShort("short1");

        boolean value2 = data.setShort("short1", 2);

        short value3 = data.getShort("short1");

        Assert.assertThat("wrong initialization", value1, is(expected1));
        Assert.assertThat("not change", value2, is(expected2));
        Assert.assertThat("wrong value", value3, is(expected3));
    }

    @Test
    public void setInteger1()
    {
        int expected1 = 2;
        boolean expected2 = true;
        int expected3 = 42;

        data.addInteger("int1", 2);

        int value1 = data.getInteger("int1");

        boolean value2 = data.setInteger("int1", 42);

        int value3 = data.getInteger("int1");

        Assert.assertThat("wrong initialization", value1, is(expected1));
        Assert.assertThat("not change", value2, is(expected2));
        Assert.assertThat("wrong value", value3, is(expected3));
    }

    @Test
    public void setInteger2()
    {
        int expected1 = 2;
        boolean expected2 = false;
        int expected3 = 2;

        data.addInteger("int1", 2);

        int value1 = data.getInteger("int1");

        boolean value2 = data.setInteger("int1", 2);

        int value3 = data.getInteger("int1");

        Assert.assertThat("wrong initialization", value1, is(expected1));
        Assert.assertThat("not change", value2, is(expected2));
        Assert.assertThat("wrong value", value3, is(expected3));
    }

    @Test
    public void setLong1()
    {
        long expected1 = 2;
        boolean expected2 = true;
        long expected3 = 42;

        data.addLong("long1", 2);

        long value1 = data.getLong("long1");

        boolean value2 = data.setLong("long1", 42);

        long value3 = data.getLong("long1");

        Assert.assertThat("wrong initialization", value1, is(expected1));
        Assert.assertThat("not change", value2, is(expected2));
        Assert.assertThat("wrong value", value3, is(expected3));
    }

    @Test
    public void setLong2()
    {
        long expected1 = 2;
        boolean expected2 = false;
        long expected3 = 2;

        data.addLong("long1", 2);

        long value1 = data.getLong("long1");

        boolean value2 = data.setLong("long1", 2);

        long value3 = data.getLong("long1");

        Assert.assertThat("wrong initialization", value1, is(expected1));
        Assert.assertThat("not change", value2, is(expected2));
        Assert.assertThat("wrong value", value3, is(expected3));
    }

    @Test
    public void setFloat1()
    {
        float expected1 = 2;
        boolean expected2 = true;
        float expected3 = 42;

        data.addFloat("float1", 2);

        float value1 = data.getFloat("float1");

        boolean value2 = data.setFloat("float1", 42);

        float value3 = data.getFloat("float1");

        Assert.assertThat("wrong initialization", value1, is(expected1));
        Assert.assertThat("not change", value2, is(expected2));
        Assert.assertThat("wrong value", value3, is(expected3));
    }

    @Test
    public void setFloat2()
    {
        float expected1 = 2;
        boolean expected2 = false;
        float expected3 = 2;

        data.addFloat("float1", 2);

        float value1 = data.getFloat("float1");

        boolean value2 = data.setFloat("float1", 2);

        float value3 = data.getFloat("float1");

        Assert.assertThat("wrong initialization", value1, is(expected1));
        Assert.assertThat("not change", value2, is(expected2));
        Assert.assertThat("wrong value", value3, is(expected3));
    }

    @Test
    public void setDouble1()
    {
        double expected1 = 2;
        boolean expected2 = true;
        double expected3 = 42;

        data.addDouble("double1", 2);

        double value1 = data.getDouble("double1");

        boolean value2 = data.setDouble("double1", 42);

        double value3 = data.getDouble("double1");

        Assert.assertThat("wrong initialization", value1, is(expected1));
        Assert.assertThat("not change", value2, is(expected2));
        Assert.assertThat("wrong value", value3, is(expected3));
    }

    @Test
    public void setDouble2()
    {
        double expected1 = 2;
        boolean expected2 = false;
        double expected3 = 2;

        data.addDouble("double1", 2);

        double value1 = data.getDouble("double1");

        boolean value2 = data.setDouble("double1", 2);

        double value3 = data.getDouble("double1");

        Assert.assertThat("wrong initialization", value1, is(expected1));
        Assert.assertThat("not change", value2, is(expected2));
        Assert.assertThat("wrong value", value3, is(expected3));
    }

    @Test
    public void setCharacter1()
    {
        char expected1 = '2';
        boolean expected2 = true;
        char expected3 = '4';

        data.addCharacter("char1", '2');

        char value1 = data.getCharacter("char1");

        boolean value2 = data.setCharacter("char1", '4');

        char value3 = data.getCharacter("char1");

        Assert.assertThat("wrong initialization", value1, is(expected1));
        Assert.assertThat("not change", value2, is(expected2));
        Assert.assertThat("wrong value", value3, is(expected3));
    }

    @Test
    public void setCharacter2()
    {
        char expected1 = '2';
        boolean expected2 = false;
        char expected3 = '2';

        data.addCharacter("char1", '2');

        char value1 = data.getCharacter("char1");

        boolean value2 = data.setCharacter("char1", '2');

        char value3 = data.getCharacter("char1");

        Assert.assertThat("wrong initialization", value1, is(expected1));
        Assert.assertThat("not change", value2, is(expected2));
        Assert.assertThat("wrong value", value3, is(expected3));
    }

    @Test
    public void setString1()
    {
        String expected1 = "2";
        boolean expected2 = true;
        String expected3 = "42";

        data.addString("String1", "2");

        String value1 = data.getString("String1");

        boolean value2 = data.setString("String1", "42");

        String value3 = data.getString("String1");

        Assert.assertThat("wrong initialization", value1, is(expected1));
        Assert.assertThat("not change", value2, is(expected2));
        Assert.assertThat("wrong value", value3, is(expected3));
    }

    @Test
    public void setString2()
    {
        String expected1 = "2";
        boolean expected2 = false;
        String expected3 = "2";

        data.addString("String1", "2");

        String value1 = data.getString("String1");

        boolean value2 = data.setString("String1", "2");

        String value3 = data.getString("String1");

        Assert.assertThat("wrong initialization", value1, is(expected1));
        Assert.assertThat("not change", value2, is(expected2));
        Assert.assertThat("wrong value", value3, is(expected3));
    }

    @Test
    public void setUUID1()
    {
        UUID expected1 = UUID.fromString("0c22e844-4ecb-48d4-a3c3-f083ddb1df3b");
        boolean expected2 = true;
        UUID expected3 = UUID.fromString("ac56a704-260b-45f5-85ac-e1b451bb79bc");

        data.addUUID("UUID1", UUID.fromString("0c22e844-4ecb-48d4-a3c3-f083ddb1df3b"));

        UUID value1 = data.getUUID("UUID1");

        boolean value2 = data.setUUID("UUID1", UUID.fromString("ac56a704-260b-45f5-85ac-e1b451bb79bc"));

        UUID value3 = data.getUUID("UUID1");

        Assert.assertThat("wrong initialization", value1, is(expected1));
        Assert.assertThat("not change", value2, is(expected2));
        Assert.assertThat("wrong value", value3, is(expected3));
    }

    @Test
    public void setUUID2()
    {
        UUID expected1 = UUID.fromString("0c22e844-4ecb-48d4-a3c3-f083ddb1df3b");
        boolean expected2 = false;
        UUID expected3 = UUID.fromString("0c22e844-4ecb-48d4-a3c3-f083ddb1df3b");

        data.addUUID("UUID1", UUID.fromString("0c22e844-4ecb-48d4-a3c3-f083ddb1df3b"));

        UUID value1 = data.getUUID("UUID1");

        boolean value2 = data.setUUID("UUID1", UUID.fromString("0c22e844-4ecb-48d4-a3c3-f083ddb1df3b"));

        UUID value3 = data.getUUID("UUID1");

        Assert.assertThat("wrong initialization", value1, is(expected1));
        Assert.assertThat("not change", value2, is(expected2));
        Assert.assertThat("wrong value", value3, is(expected3));
    }
}
