package com.leviathanstudio.lib.common.math.tree;

public abstract class Node extends AbstractNode<Node>
{
    /** The parent node **/
    private Node parent;

    public Node(String name)
    {
        super(name);
    }

    @Override
    protected String toStringParent()
    {
        return "" + (this.parent != null ? this.parent : "|");
    }

    /**
     * Set a node has parent if isn't already set
     * 
     * @param node
     *            The node to set as parent
     */
    final void setParent(Node node)
    {
        if (this.parent == null)
            this.parent = node;
    }

    /**
     * Set the node as root
     */
    @Override
    protected void setRoot()
    {
        this.parent = null;
    }

    /**
     * Get the parent node
     * 
     * @return The parent node
     */
    public final Node getParent()
    {
        return this.parent;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public boolean hasSameParent(AbstractNode anode)
    {
        if (anode instanceof Node)
        {
            Node node = (Node) anode;
            return (this.getParent() != null && node.getParent() != null && this.getParent().equals(node.getParent()))
                    || (this.getParent() == null && node.getParent() == null);
        }
        return false;
    }
}
