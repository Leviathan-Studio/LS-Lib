package com.leviathanstudio.lib.common.math.tree;

import java.util.List;

public class OrientedTree extends AbstractTree<MultiParentNode>
{

    public OrientedTree()
    {
        super();
    }

    @Override
    protected boolean canAdd(MultiParentNode parent, MultiParentNode target)
    {
        boolean res = true;

        // Test to prevent loop
        res = canAdd(this.getRoot(), target, target);

        return res;
    }

    private boolean canAdd(MultiParentNode root, MultiParentNode target, MultiParentNode work)
    {
        boolean res = true;

        List<MultiParentNode> parents = work.getParent();

        for (int i = 0; i < parents.size() && res; i++)
        {
            MultiParentNode workI = parents.get(i);
            if (workI.equals(target))
                res = false;
            else if (workI.equals(root))
                res = true;
            else
                res = canAdd(root, target, workI);
        }
        return res;
    }

    @Override
    protected boolean add(MultiParentNode parent, MultiParentNode node)
    {
        node.addParent(parent);
        return parent.addChild(node);
    }
}
