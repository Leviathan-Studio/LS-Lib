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
    public void testSetRoot1()
    {
        Node node = new Node("test");

        tree.setRoot(node);

        Node expected1 = node;
        int expected2 = 1;
        Node expected3 = node;

        Node value1 = tree.getRoot();
        int value2 = tree.getNodes().size();
        Node value3 = tree.getNodes().get(0);

        Assert.assertThat("root null", value1, is(expected1));
        Assert.assertThat("wrong list size", value2, is(expected2));
        Assert.assertThat("root doesn't match", value3, is(expected3));
    }

    @Test
    public void testSetRoot2()
    {
        Node node1 = new Node("test1");

        Node node2 = new Node("test2");

        tree.setRoot(node1);
        boolean value2 = tree.setRoot(node2);

        Node expected1 = node1;
        boolean expected2 = false;

        Node value1 = tree.getRoot();

        Assert.assertThat("root null", value1, is(expected1));
        Assert.assertThat("root re-set", value2, is(expected2));
    }

    @Test
    public void testSetRoot3()
    {
        Node node = null;

        boolean value1 = tree.setRoot(node);

        boolean expected1 = false;

        Assert.assertThat("root set as null", value1, is(expected1));
    }

    @Test
    public void testAddToRoot1()
    {
        Node nodeR = new Node("root");

        Node node = new Node("test");

        tree.setRoot(nodeR);
        tree.addNodeToRoot(node);

        int expected1 = 2;
        Node expected2 = node;

        int value1 = tree.getNodes().size();
        Node value2 = tree.getNodes().get(1);

        Assert.assertThat("wrong list size", value1, is(expected1));
        Assert.assertThat("node null", value2, is(expected2));
    }

    @Test
    public void testAddToRoot2()
    {
        Node node = new Node("test");

        boolean value1 = tree.addNodeToRoot(node);

        boolean expected1 = false;

        Assert.assertThat("node add to a null root", value1, is(expected1));
    }

    //
}
