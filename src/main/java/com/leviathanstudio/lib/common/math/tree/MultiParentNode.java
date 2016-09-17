package com.leviathanstudio.lib.common.math.tree;

import java.util.List;

import com.google.common.collect.Lists;

public abstract class MultiParentNode extends AbstractNode<MultiParentNode>
{
    /** The parent node **/
    private final List<MultiParentNode> parent;

    /**
     * 
     * @param name
     *            The node name
     */
    public MultiParentNode(String name)
    {
        super(name);
        this.parent = Lists.newArrayList();
    }

    /**
     * 
     * @param node
     *            The node to copy
     */
    protected MultiParentNode(MultiParentNode node)
    {
        super(node);
        this.parent = node.getParent();
    }

    /**
     * Add a node has parent if isn't already set
     * 
     * @param node
     *            The node to add as parent
     */
    final void addParent(MultiParentNode node)
    {
        if (!this.parent.contains(node))
        {
            this.parent.add(node);
        }
    }

    /**
     * Set the node as root
     */
    @Override
    protected final void setRoot()
    {
        this.parent.clear();
    }

    /**
     * Get a copy of the parent nodes
     * 
     * @return The parent nodes
     */
    public final List<MultiParentNode> getParent()
    {
        return Lists.newArrayList(this.parent);
    }

    @Override
    protected String toStringParent()
    {
        String res = "";
        if (this.parent.size() == 0)
            res = "|";
        else
        {
            if (this.parent.size() > 1)
                res += "{ ";
            for (MultiParentNode p : this.getParent())
            {
                res += p + (this.parent.size() > 1 ? "; " : "");
            }
            if (this.parent.size() > 1)
                res += "}";
        }

        return res;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public boolean hasSameParent(AbstractNode anode)
    {
        if (anode instanceof Node)
        {
            MultiParentNode node = (MultiParentNode) anode;
            boolean res = false;

            if (this.getParent().size() == node.getParent().size())
                if (this.getParent().containsAll(node.getParent()))
                    res = true;

            return res;
        }
        return false;
    }
}
