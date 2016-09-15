package com.leviathanstudio.lib.common.math.tree;

public class Tree extends AbstractTree<Node>
{

    public Tree()
    {
        super();
    }

    @Override
    protected boolean canAdd(Node parent, Node target)
    {
        boolean res = true;

        // Test if target is root node
        if (target.equals(this.getRoot()))
            res = false;
        else
        {
            // Test to prevent loop
            for (final Node test : this.getNodes())
            {
                // Test if the node isn't a parent
                if (test.getParent() != null && test.getParent().equals(target))
                {
                    res = false;
                }
            }
        }
        return res;
    }

    @Override
    protected boolean add(Node parent, Node node)
    {
        node.setParent(parent);
        return parent.addChild(node);
    }

}
