package com.leviathanstudio.lib.common.math.tree;

import java.util.List;

import com.google.common.collect.Lists;

public abstract class AbstractTree<T extends AbstractNode<T>>
{

    /** List of all node into the tree **/
    private final List<T> nodes;
    /** The root node of the tree **/
    private T             root;

    protected AbstractTree()
    {
        this.nodes = Lists.newArrayList();
    }

    public final List<T> getNodes()
    {
        return Lists.newArrayList(nodes);
    }

    /**
     * Get the root node
     *
     * @return The root node
     */
    public final T getRoot()
    {
        return this.root;
    }

    /**
     * Set the root node if ins't already set
     *
     * @param root
     *            The node to set
     * @return True if the node has been set, False else
     */
    public boolean setRoot(T root)
    {
        boolean res = false;
        if (this.root == null && root != null)
        {
            root.setRoot();
            this.root = root;
            this.nodes.add(root);
            res = true;
        }

        return res;
    }

    /**
     * Add a node to the root
     *
     * @param node
     *            The node to add
     * @return True if the node has been add, False else
     */
    public boolean addNodeToRoot(T node)
    {
        if (this.root != null)
            return this.addNode(this.root, node);
        return false;
    }

    /**
     * Add a node to its parent
     *
     * @param parent
     *            The parent node
     * @param node
     *            The node to add
     * @return True if the node has been add, False else
     */
    public boolean addNode(T parent, T node)
    {
        // Method result
        boolean res = false;
        // Boolean use to say if node can be added
        boolean canAdd = true;

        // Test the configuration to prevent wrong behavior
        canAdd = canAdd(parent, node);

        // Add node if the test is passed
        if (canAdd)
        {
            this.nodes.add(node);
            res = add(parent, node);

        }

        // Return the result
        return res;
    }

    protected abstract boolean canAdd(T parent, T target);

    protected abstract boolean add(T parent, T node);
}
