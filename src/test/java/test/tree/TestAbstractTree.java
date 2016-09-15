package test.tree;

import static org.hamcrest.CoreMatchers.*;

import java.util.List;

import com.google.common.collect.Lists;
import com.leviathanstudio.lib.common.math.tree.Node;
import com.leviathanstudio.lib.common.math.tree.Tree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestAbstractTree
{
    Tree tree = null;

    @Before
    public void setupUp()
    {
        tree = new Tree();
    }

    @Test
    public void testInit1()
    {
        Node expected = null;

        Node value = tree.getRoot();

        Assert.assertThat("root not null", value, is(expected));
    }

    @Test
    public void testInit2()
    {
        List<Node> expected1 = Lists.newArrayList();
        int expected2 = 0;

        List<Node> value1 = tree.getNodes();
        int value2 = tree.getNodes().size();

        Assert.assertThat("node list null", value1, is(expected1));
        Assert.assertThat("node list not empty", value2, is(expected2));
    }

    @Test
    public void testSetRoot()
    {
        Node node = new Node("test")
        {
        };

        tree.setRoot(node);

        Node expected1 = node;
        Node expected2 = node;

        Node value1 = tree.getRoot();
        Node value2 = tree.getNodes().get(0);

        Assert.assertThat("root null", value1, is(expected1));
        Assert.assertThat("root doesn't match", value2, is(expected2));

    }

    @Test
    public void testAddRoot()
    {
        Node nodeR = new Node("root")
        {
        };

        Node node = new Node("test")
        {
        };

        tree.setRoot(nodeR);
        tree.addNodeToRoot(node);

        Node expected = node;

        Node value = tree.getNodes().get(1);

        Assert.assertThat("node null", value, is(expected));

    }
}
