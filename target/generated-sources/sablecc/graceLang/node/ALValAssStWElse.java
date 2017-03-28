/* This file was generated by SableCC (http://www.sablecc.org/). */

package graceLang.node;

import graceLang.analysis.*;

@SuppressWarnings("nls")
public final class ALValAssStWElse extends PStWElse
{
    private PLValAssign _lValAssign_;

    public ALValAssStWElse()
    {
        // Constructor
    }

    public ALValAssStWElse(
        @SuppressWarnings("hiding") PLValAssign _lValAssign_)
    {
        // Constructor
        setLValAssign(_lValAssign_);

    }

    @Override
    public Object clone()
    {
        return new ALValAssStWElse(
            cloneNode(this._lValAssign_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseALValAssStWElse(this);
    }

    public PLValAssign getLValAssign()
    {
        return this._lValAssign_;
    }

    public void setLValAssign(PLValAssign node)
    {
        if(this._lValAssign_ != null)
        {
            this._lValAssign_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._lValAssign_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._lValAssign_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._lValAssign_ == child)
        {
            this._lValAssign_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._lValAssign_ == oldChild)
        {
            setLValAssign((PLValAssign) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
