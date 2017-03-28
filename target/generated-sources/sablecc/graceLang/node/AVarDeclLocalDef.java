/* This file was generated by SableCC (http://www.sablecc.org/). */

package graceLang.node;

import graceLang.analysis.*;

@SuppressWarnings("nls")
public final class AVarDeclLocalDef extends PLocalDef
{
    private PVarDecl _varDecl_;

    public AVarDeclLocalDef()
    {
        // Constructor
    }

    public AVarDeclLocalDef(
        @SuppressWarnings("hiding") PVarDecl _varDecl_)
    {
        // Constructor
        setVarDecl(_varDecl_);

    }

    @Override
    public Object clone()
    {
        return new AVarDeclLocalDef(
            cloneNode(this._varDecl_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAVarDeclLocalDef(this);
    }

    public PVarDecl getVarDecl()
    {
        return this._varDecl_;
    }

    public void setVarDecl(PVarDecl node)
    {
        if(this._varDecl_ != null)
        {
            this._varDecl_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._varDecl_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._varDecl_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._varDecl_ == child)
        {
            this._varDecl_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._varDecl_ == oldChild)
        {
            setVarDecl((PVarDecl) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
