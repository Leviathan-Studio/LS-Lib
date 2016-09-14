package test;

import static org.hamcrest.CoreMatchers.*;

import com.leviathanstudio.lib.common.data.DataManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDataManagerSize
{
    DataManager data;

    @Before
    public void setupUp()
    {
        data = DataManager.createDataManager();
    }

    @Test
    public void initSize()
    {
        int expected = 0;

        int value = data.getEntries().size();

        Assert.assertThat("list not empty", value, is(expected));
    }

    @Test
    public void clearSize()
    {
        int expected1 = 1;
        int expected2 = 0;

        data.addBoolean("boolean1", true);
        int value1 = data.getEntries().size();
        data.clear();
        int value2 = data.getEntries().size();

        Assert.assertThat("list empty", value1, is(expected1));

        Assert.assertThat("list not empty", value2, is(expected2));
    }
}
