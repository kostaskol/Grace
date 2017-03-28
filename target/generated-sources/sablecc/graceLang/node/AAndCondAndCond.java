/* This file was generated by SableCC (http://www.sablecc.org/). */

package graceLang.node;

import graceLang.analysis.*;

@SuppressWarnings("nls")
public final class AAndCondAndCond extends PAndCond
{
    private PAndCond _andCond_;
    private TAnd _and_;
    private PNotCond _notCond_;

    public AAndCondAndCond()
    {
        // Constructor
    }

    public AAndCondAndCond(
        @SuppressWarnings("hiding") PAndCond _andCond_,
        @SuppressWarnings("hiding") TAnd _and_,
        @SuppressWarnings("hiding") PNotCond _notCond_)
    {
        // Constructor
        setAndCond(_andCond_);

        setAnd(_and_);

        setNotCond(_notCond_);

    }

    @Override
    public Object clone()
    {
        return new AAndCondAndCond(
            cloneNode(this._andCond_),
            cloneNode(this._and_),
            cloneNode(this._notCond_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAAndCondAndCond(this);
    }

    public PAndCond getAndCond()
    {
        return this._andCond_;
    }

    public void setAndCond(PAndCond node)
    {
        if(this._andCond_ != null)
        {
            this._andCond_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._andCond_ = node;
    }

    public TAnd getAnd()
    {
        return this._and_;
    }

    public void setAnd(TAnd node)
    {
        if(this._and_ != null)
        {
            this._and_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._and_ = node;
    }

    public PNotCond getNotCond()
    {
        return this._notCond_;
    }

    public void setNotCond(PNotCond node)
    {
        if(this._notCond_ != null)
        {
            this._notCond_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._notCond_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._andCond_)
            + toString(this._and_)
            + toString(this._notCond_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._andCond_ == child)
        {
            this._andCond_ = null;
            return;
        }

        if(this._and_ == child)
        {
            this._and_ = null;
            return;
        }

        if(this._notCond_ == child)
        {
            this._notCond_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._andCond_ == oldChild)
        {
            setAndCond((PAndCond) newChild);
            return;
        }

        if(this._and_ == oldChild)
        {
            setAnd((TAnd) newChild);
            return;
        }

        if(this._notCond_ == oldChild)
        {
            setNotCond((PNotCond) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
