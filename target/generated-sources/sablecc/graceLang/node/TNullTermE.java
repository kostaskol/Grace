/* This file was generated by SableCC (http://www.sablecc.org/). */

package graceLang.node;

import graceLang.analysis.*;

@SuppressWarnings("nls")
public final class TNullTermE extends Token
{
    public TNullTermE()
    {
        super.setText("\\0");
    }

    public TNullTermE(int line, int pos)
    {
        super.setText("\\0");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TNullTermE(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTNullTermE(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TNullTermE text.");
    }
}
