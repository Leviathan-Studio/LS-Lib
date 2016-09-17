package com.leviathanstudio.lib.common.math.tree;

import java.util.List;

import com.google.common.collect.Lists;

public abstract class AbstractNode<T>
{
    /** The node name **/
    private final String  name;

    /** The child nodes **/
    private final List<T> childs;

    protected abstract void setRoot();

    /**
     * 
     * @param name
     *            The node name
     */
    public AbstractNode(String name)
    {
        // Test
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("name not valid!");

        // Set value
        this.name = name;
        this.childs = Lists.newArrayList();
    }

    /**
     * 
     * @param name
     *            The node name
     */
    protected AbstractNode(AbstractNode<T> node)
    {
        // Set value
        this.name = node.name;
        this.childs = node.getChilds();
    }

    /**
     * Get the node name
     * 
     * @return The node name
     */
    public final String getName()
    {
        return this.name;
    }

    /**
     * Get a copy of the child nodes
     * 
     * @return The child nodes
     */
    public final List<T> getChilds()
    {
        return Lists.newArrayList(this.childs);
    }

    /**
     * Add a child node to this node
     * 
     * @param node
     *            The node to add
     * @return True if the node has been add, False else
     */
    public final boolean addChild(T node)
    {
        return this.childs.add(node);
    }

    protected  abstract String toStringParent();

    @Override
    public String toString()
    {
        return toStringParent() + " > " + this.name + " (" + this.childs.size() + ")";
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        else
        {
            if (obj instanceof AbstractNode)
            {
                @SuppressWarnings("rawtypes")
                final AbstractNode node = (AbstractNode) obj;
                if (hasSameParent(node) && this.name.equals(node.name) && this.childs.equals(node.childs))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            return false;
        }
    }

    @SuppressWarnings("rawtypes")
    public abstract boolean hasSameParent( AbstractNode node);

}
