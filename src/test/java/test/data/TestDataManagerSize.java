package test.data;

import static org.hamcrest.CoreMatchers.*;

import com.leviathanstudio.lib.common.data2.DataManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDataManagerSize
{
    DataManager data;

    @Before
    public void setupUp()
    {
        data = DataManager.create();
    }

    @Test
    public void initSize()
    {
        int expected = 0;

        int value = data.size();

        Assert.assertThat("list not empty", value, is(expected));
    }

    @Test
    public void clearSize()
    {
        int expected1 = 1;
        int expected2 = 0;

        data.addBoolean("boolean1", true);
        int value1 = data.size();
        data.clear();
        int value2 = data.size();

        Assert.assertThat("list empty", value1, is(expected1));

        Assert.assertThat("list not empty", value2, is(expected2));
    }
}
