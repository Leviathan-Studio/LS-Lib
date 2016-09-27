package test.data;

import static org.hamcrest.CoreMatchers.*;

import java.util.UUID;

import com.leviathanstudio.lib.common.data2.DataManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDataManagerAdd
{
    DataManager data;

    @Before
    public void setupUp()
    {
        data = DataManager.create();
    }

    @Test
    public void addBoolean1()
    {
        boolean expected = true;

        boolean value = data.addBoolean("boolean1", false);

        Assert.assertThat("not add", value, is(expected));
    }

    @Test
    public void addBoolean2()
    {
        boolean expected = false;

        data.addBoolean("boolean1", false);

        boolean value = data.getBoolean("boolean1");

        Assert.assertThat("different value", value, is(expected));
    }

    @Test
    public void addBoolean3()
    {
        boolean expected1 = true;
        boolean expected2 = false;

        boolean value1 = data.addBoolean("boolean1", false);
        boolean value2 = data.addBoolean("boolean1", true);

        Assert.assertThat("adding problem 1", value1, is(expected1));
        Assert.assertThat("adding problem 2", value2, is(expected2));
    }

    @Test
    public void addByte1()
    {
        boolean expected = true;

        boolean value = data.addByte("byte1", 1);

        Assert.assertThat("not add", value, is(expected));
    }

    @Test
    public void addByte2()
    {
        byte expected = 2;

        data.addByte("byte1", 2);

        byte value = data.getByte("byte1");

        Assert.assertThat("different value", value, is(expected));
    }

    @Test
    public void addByte3()
    {
        boolean expected1 = true;
        boolean expected2 = false;

        boolean value1 = data.addByte("byte1", 1);
        boolean value2 = data.addByte("byte1", 42);

        Assert.assertThat("adding problem 1", value1, is(expected1));
        Assert.assertThat("adding problem 2", value2, is(expected2));
    }

    @Test
    public void addShort1()
    {
        boolean expected = true;

        boolean value = data.addShort("short1", 1);

        Assert.assertThat("not add", value, is(expected));
    }

    @Test
    public void addShort2()
    {
        short expected = 2;

        data.addShort("short1", 2);

        short value = data.getShort("short1");

        Assert.assertThat("different value", value, is(expected));
    }

    @Test
    public void addShort3()
    {
        boolean expected1 = true;
        boolean expected2 = false;

        boolean value1 = data.addShort("short1", 1);
        boolean value2 = data.addShort("short1", 42);

        Assert.assertThat("adding problem 1", value1, is(expected1));
        Assert.assertThat("adding problem 2", value2, is(expected2));
    }

    @Test
    public void addInteger1()
    {
        boolean expected = true;

        boolean value = data.addInteger("int1", 1);

        Assert.assertThat("not add", value, is(expected));
    }

    @Test
    public void addInteger2()
    {
        int expected = 2;

        data.addInteger("int1", 2);

        int value = data.getInteger("int1");

        Assert.assertThat("different value", value, is(expected));
    }

    @Test
    public void addInteger3()
    {
        boolean expected1 = true;
        boolean expected2 = false;

        boolean value1 = data.addInteger("int1", 1);
        boolean value2 = data.addInteger("int1", 42);

        Assert.assertThat("adding problem 1", value1, is(expected1));
        Assert.assertThat("adding problem 2", value2, is(expected2));
    }

    @Test
    public void addLong1()
    {
        boolean expected = true;

        boolean value = data.addLong("long1", 1L);

        Assert.assertThat("not add", value, is(expected));
    }

    @Test
    public void addLong2()
    {
        long expected = 2L;

        data.addLong("long1", 2L);

        long value = data.getLong("long1");

        Assert.assertThat("different value", value, is(expected));
    }

    @Test
    public void addLong3()
    {
        boolean expected1 = true;
        boolean expected2 = false;

        boolean value1 = data.addLong("long1", 1L);
        boolean value2 = data.addLong("long1", 42L);

        Assert.assertThat("adding problem 1", value1, is(expected1));
        Assert.assertThat("adding problem 2", value2, is(expected2));
    }

    @Test
    public void addFloat1()
    {
        boolean expected = true;

        boolean value = data.addFloat("float1", 1F);

        Assert.assertThat("not add", value, is(expected));
    }

    @Test
    public void addFloat2()
    {
        float expected = 2F;

        data.addFloat("float1", 2F);

        float value = data.getFloat("float1");

        Assert.assertThat("different value", value, is(expected));
    }

    @Test
    public void addFloat3()
    {
        boolean expected1 = true;
        boolean expected2 = false;

        boolean value1 = data.addFloat("float1", 1F);
        boolean value2 = data.addFloat("float1", 42F);

        Assert.assertThat("adding problem 1", value1, is(expected1));
        Assert.assertThat("adding problem 2", value2, is(expected2));
    }

    @Test
    public void addDouble1()
    {
        boolean expected = true;

        boolean value = data.addDouble("double1", 1);

        Assert.assertThat("not add", value, is(expected));
    }

    @Test
    public void addDouble2()
    {
        double expected = 2;

        data.addDouble("double1", 2);

        double value = data.getDouble("double1");

        Assert.assertThat("different value", value, is(expected));
    }

    @Test
    public void addDouble3()
    {
        boolean expected1 = true;
        boolean expected2 = false;

        boolean value1 = data.addDouble("double1", 1);
        boolean value2 = data.addDouble("double1", 42);

        Assert.assertThat("adding problem 1", value1, is(expected1));
        Assert.assertThat("adding problem 2", value2, is(expected2));
    }

    @Test
    public void addCharacter1()
    {
        boolean expected = true;

        boolean value = data.addCharacter("char1", 'p');

        Assert.assertThat("not add", value, is(expected));
    }

    @Test
    public void addCharacter2()
    {
        char expected = 'p';

        data.addCharacter("char1", 'p');

        char value = data.getCharacter("char1");

        Assert.assertThat("different value", value, is(expected));
    }

    @Test
    public void addCharacter3()
    {
        boolean expected1 = true;
        boolean expected2 = false;

        boolean value1 = data.addCharacter("char1", 'p');
        boolean value2 = data.addCharacter("char1", 'h');

        Assert.assertThat("adding problem 1", value1, is(expected1));
        Assert.assertThat("adding problem 2", value2, is(expected2));
    }

    @Test
    public void addString1()
    {
        boolean expected = true;

        boolean value = data.addString("String1", "phoenix");

        Assert.assertThat("not add", value, is(expected));
    }

    @Test
    public void addString2()
    {
        String expected = "phoenix";

        data.addString("String1", "phoenix");

        String value = data.getString("String1");

        Assert.assertThat("different value", value, is(expected));
    }

    @Test
    public void addString3()
    {
        boolean expected1 = true;
        boolean expected2 = false;

        boolean value1 = data.addString("String1", "phoenix");
        boolean value2 = data.addString("String1", "wolf");

        Assert.assertThat("adding problem 1", value1, is(expected1));
        Assert.assertThat("adding problem 2", value2, is(expected2));
    }

    @Test
    public void addUUID1()
    {
        boolean expected = true;

        boolean value = data.addUUID("UUID1", UUID.fromString("0c22e844-4ecb-48d4-a3c3-f083ddb1df3b"));

        Assert.assertThat("not add", value, is(expected));
    }

    @Test
    public void addUUID2()
    {
        UUID expected = UUID.fromString("0c22e844-4ecb-48d4-a3c3-f083ddb1df3b");

        data.addUUID("UUID1", UUID.fromString("0c22e844-4ecb-48d4-a3c3-f083ddb1df3b"));

        UUID value = data.getUUID("UUID1");

        Assert.assertThat("different value", value, is(expected));
    }

    @Test
    public void addUUID3()
    {
        boolean expected1 = true;
        boolean expected2 = false;

        boolean value1 = data.addUUID("UUID1", UUID.fromString("0c22e844-4ecb-48d4-a3c3-f083ddb1df3b"));
        boolean value2 = data.addUUID("UUID1", UUID.fromString("ac56a704-260b-45f5-85ac-e1b451bb79bc"));

        Assert.assertThat("adding problem 1", value1, is(expected1));
        Assert.assertThat("adding problem 2", value2, is(expected2));
    }

}
