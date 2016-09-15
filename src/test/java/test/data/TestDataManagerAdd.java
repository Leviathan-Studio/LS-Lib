package test.data;

import static org.hamcrest.CoreMatchers.*;

import java.util.UUID;

import com.leviathanstudio.lib.common.data.DataManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDataManagerAdd
{
    DataManager data;

    @Before
    public void setupUp()
    {
        data = DataManager.createDataManager();
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
        boolean expected1 = false;
        boolean expected2 = true;

        data.addBoolean("boolean1", false, true);

        boolean value1 = data.getBoolean("boolean1");
        boolean value2 = (boolean) data.getEntry("boolean1").getDefaultValue();

        Assert.assertThat("different value", value1, is(expected1));
        Assert.assertThat("different default value", value2, is(expected2));
    }

    @Test
    public void addBoolean4()
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
        byte expected1 = 2;
        byte expected2 = 4;

        data.addByte("byte1", 2, 4);

        byte value1 = data.getByte("byte1");
        byte value2 = (byte) data.getEntry("byte1").getDefaultValue();

        Assert.assertThat("different value", value1, is(expected1));
        Assert.assertThat("different default value", value2, is(expected2));
    }

    @Test
    public void addByte4()
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
        short expected1 = 2;
        short expected2 = 4;

        data.addShort("short1", 2, 4);

        short value1 = data.getShort("short1");
        short value2 = (short) data.getEntry("short1").getDefaultValue();

        Assert.assertThat("different value", value1, is(expected1));
        Assert.assertThat("different default value", value2, is(expected2));
    }

    @Test
    public void addShort4()
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
        int expected1 = 2;
        int expected2 = 4;

        data.addInteger("int1", 2, 4);

        int value1 = data.getInteger("int1");
        int value2 = (int) data.getEntry("int1").getDefaultValue();

        Assert.assertThat("different value", value1, is(expected1));
        Assert.assertThat("different default value", value2, is(expected2));
    }

    @Test
    public void addInteger4()
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
        long expected1 = 2L;
        long expected2 = 4L;

        data.addLong("long1", 2L, 4L);

        long value1 = data.getLong("long1");
        long value2 = (long) data.getEntry("long1").getDefaultValue();

        Assert.assertThat("different value", value1, is(expected1));
        Assert.assertThat("different default value", value2, is(expected2));
    }

    @Test
    public void addLong4()
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

        boolean value = data.addFloat("float1", 1L);

        Assert.assertThat("not add", value, is(expected));
    }

    @Test
    public void addFloat2()
    {
        float expected = 2L;

        data.addFloat("float1", 2L);

        float value = data.getFloat("float1");

        Assert.assertThat("different value", value, is(expected));
    }

    @Test
    public void addFloat3()
    {
        float expected1 = 2L;
        float expected2 = 4L;

        data.addFloat("float1", 2L, 4L);

        float value1 = data.getFloat("float1");
        float value2 = (float) data.getEntry("float1").getDefaultValue();

        Assert.assertThat("different value", value1, is(expected1));
        Assert.assertThat("different default value", value2, is(expected2));
    }

    @Test
    public void addFloat4()
    {
        boolean expected1 = true;
        boolean expected2 = false;

        boolean value1 = data.addFloat("float1", 1L);
        boolean value2 = data.addFloat("float1", 42L);

        Assert.assertThat("adding problem 1", value1, is(expected1));
        Assert.assertThat("adding problem 2", value2, is(expected2));
    }

    @Test
    public void addDouble1()
    {
        boolean expected = true;

        boolean value = data.addDouble("double1", 1L);

        Assert.assertThat("not add", value, is(expected));
    }

    @Test
    public void addDouble2()
    {
        double expected = 2L;

        data.addDouble("double1", 2L);

        double value = data.getDouble("double1");

        Assert.assertThat("different value", value, is(expected));
    }

    @Test
    public void addDouble3()
    {
        double expected1 = 2L;
        double expected2 = 4L;

        data.addDouble("double1", 2L, 4L);

        double value1 = data.getDouble("double1");
        double value2 = (double) data.getEntry("double1").getDefaultValue();

        Assert.assertThat("different value", value1, is(expected1));
        Assert.assertThat("different default value", value2, is(expected2));
    }

    @Test
    public void addDouble4()
    {
        boolean expected1 = true;
        boolean expected2 = false;

        boolean value1 = data.addDouble("double1", 1L);
        boolean value2 = data.addDouble("double1", 42L);

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
        char expected1 = 'p';
        char expected2 = 'h';

        data.addCharacter("char1", 'p', 'h');

        char value1 = data.getCharacter("char1");
        char value2 = (char) data.getEntry("char1").getDefaultValue();

        Assert.assertThat("different value", value1, is(expected1));
        Assert.assertThat("different default value", value2, is(expected2));
    }

    @Test
    public void addCharacter4()
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
        String expected1 = "phoenix";
        String expected2 = "wolf";

        data.addString("String1", "phoenix", "wolf");

        String value1 = data.getString("String1");
        String value2 = (String) data.getEntry("String1").getDefaultValue();

        Assert.assertThat("different value", value1, is(expected1));
        Assert.assertThat("different default value", value2, is(expected2));
    }

    @Test
    public void addString4()
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
        UUID expected1 = UUID.fromString("0c22e844-4ecb-48d4-a3c3-f083ddb1df3b");
        UUID expected2 = UUID.fromString("ac56a704-260b-45f5-85ac-e1b451bb79bc");

        data.addUUID("UUID1", UUID.fromString("0c22e844-4ecb-48d4-a3c3-f083ddb1df3b"),
                UUID.fromString("ac56a704-260b-45f5-85ac-e1b451bb79bc"));

        UUID value1 = data.getUUID("UUID1");
        UUID value2 = (UUID) data.getEntry("UUID1").getDefaultValue();

        Assert.assertThat("different value", value1, is(expected1));
        Assert.assertThat("different default value", value2, is(expected2));
    }

    @Test
    public void addUUID4()
    {
        boolean expected1 = true;
        boolean expected2 = false;

        boolean value1 = data.addUUID("UUID1", UUID.fromString("0c22e844-4ecb-48d4-a3c3-f083ddb1df3b"));
        boolean value2 = data.addUUID("UUID1", UUID.fromString("ac56a704-260b-45f5-85ac-e1b451bb79bc"));

        Assert.assertThat("adding problem 1", value1, is(expected1));
        Assert.assertThat("adding problem 2", value2, is(expected2));
    }

}
