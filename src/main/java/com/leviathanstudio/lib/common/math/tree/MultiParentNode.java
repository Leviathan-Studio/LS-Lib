package com.leviathanstudio.lib.common.math.tree;

import java.util.List;

import com.google.common.collect.Lists;

public abstract class MultiParentNode extends AbstractNode<MultiParentNode>
{
    /** The parent node **/
    private final List<MultiParentNode> parent;

    private List<MultiParentNode>       cpParent;

    /**
     * 
     * @param name
     *            The node name
     */
    protected MultiParentNode(String name)
    {
        super(name);
        this.parent = Lists.newArrayList();
        this.cpParent = Lists.newArrayList();
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
            this.cpParent = Lists.newArrayList(this.parent);
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
        return this.cpParent;
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
