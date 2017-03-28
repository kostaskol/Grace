/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.example.compiler.node;

import com.example.compiler.analysis.*;

@SuppressWarnings("nls")
public final class AIfStmt extends PStmt
{
    private PIfStmt _ifStmt_;

    public AIfStmt()
    {
        // Constructor
    }

    public AIfStmt(
        @SuppressWarnings("hiding") PIfStmt _ifStmt_)
    {
        // Constructor
        setIfStmt(_ifStmt_);

    }

    @Override
    public Object clone()
    {
        return new AIfStmt(
            cloneNode(this._ifStmt_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAIfStmt(this);
    }

    public PIfStmt getIfStmt()
    {
        return this._ifStmt_;
    }

    public void setIfStmt(PIfStmt node)
    {
        if(this._ifStmt_ != null)
        {
            this._ifStmt_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._ifStmt_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._ifStmt_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._ifStmt_ == child)
        {
            this._ifStmt_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._ifStmt_ == oldChild)
        {
            setIfStmt((PIfStmt) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
