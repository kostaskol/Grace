/* This file was generated by SableCC (http://www.sablecc.org/). */

package graceLang.node;

import graceLang.analysis.*;

@SuppressWarnings("nls")
public final class AOffs extends POffs
{
    private TOpenBrack _openBrack_;
    private PExpression _expression_;
    private TCloseBrack _closeBrack_;

    public AOffs()
    {
        // Constructor
    }

    public AOffs(
        @SuppressWarnings("hiding") TOpenBrack _openBrack_,
        @SuppressWarnings("hiding") PExpression _expression_,
        @SuppressWarnings("hiding") TCloseBrack _closeBrack_)
    {
        // Constructor
        setOpenBrack(_openBrack_);

        setExpression(_expression_);

        setCloseBrack(_closeBrack_);

    }

    @Override
    public Object clone()
    {
        return new AOffs(
            cloneNode(this._openBrack_),
            cloneNode(this._expression_),
            cloneNode(this._closeBrack_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAOffs(this);
    }

    public TOpenBrack getOpenBrack()
    {
        return this._openBrack_;
    }

    public void setOpenBrack(TOpenBrack node)
    {
        if(this._openBrack_ != null)
        {
            this._openBrack_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._openBrack_ = node;
    }

    public PExpression getExpression()
    {
        return this._expression_;
    }

    public void setExpression(PExpression node)
    {
        if(this._expression_ != null)
        {
            this._expression_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._expression_ = node;
    }

    public TCloseBrack getCloseBrack()
    {
        return this._closeBrack_;
    }

    public void setCloseBrack(TCloseBrack node)
    {
        if(this._closeBrack_ != null)
        {
            this._closeBrack_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._closeBrack_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._openBrack_)
            + toString(this._expression_)
            + toString(this._closeBrack_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._openBrack_ == child)
        {
            this._openBrack_ = null;
            return;
        }

        if(this._expression_ == child)
        {
            this._expression_ = null;
            return;
        }

        if(this._closeBrack_ == child)
        {
            this._closeBrack_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._openBrack_ == oldChild)
        {
            setOpenBrack((TOpenBrack) newChild);
            return;
        }

        if(this._expression_ == oldChild)
        {
            setExpression((PExpression) newChild);
            return;
        }

        if(this._closeBrack_ == oldChild)
        {
            setCloseBrack((TCloseBrack) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
