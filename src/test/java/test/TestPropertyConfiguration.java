package test;

import static org.hamcrest.CoreMatchers.*;

import java.io.File;

import com.leviathanstudio.lib.common.config.property.PropertyConfiguration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestPropertyConfiguration
{
    PropertyConfiguration config = null;

    @Before
    public void setupUp()
    {
        config = new PropertyConfiguration(new File("test/test.properties"));
    }

    @Test
    public void getBoolean1()
    {
        boolean expected = true;

        boolean value = config.getBooleanProperty("boolean1", false);

        Assert.assertThat("not add", value, is(expected));
    }

    @Test
    public void getBoolean2()
    {
        boolean expected = false;

        boolean value = config.getBooleanProperty("boolean2", true);

        Assert.assertThat("not add", value, is(expected));
    }

    @Test
    public void getByte1()
    {
        byte expected = 1;

        byte value = config.getByteProperty("byte1", 2);

        Assert.assertThat("not add", value, is(expected));
    }

    @Test
    public void getShort1()
    {
        short expected = 2;

        short value = config.getShortProperty("short1", 3);

        Assert.assertThat("not add", value, is(expected));
    }

    @Test
    public void getint1()
    {
        short expected = 3;

        short value = config.getShortProperty("int1", 4);

        Assert.assertThat("not add", value, is(expected));
    }

    @Test
    public void getlong1()
    {
        short expected = 4;

        short value = config.getShortProperty("long1", 5);

        Assert.assertThat("not add", value, is(expected));
    }
}
