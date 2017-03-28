/* This file was generated by SableCC (http://www.sablecc.org/). */

package graceLang.node;

import graceLang.analysis.*;

@SuppressWarnings("nls")
public final class TApostropheE extends Token
{
    public TApostropheE()
    {
        super.setText("\'");
    }

    public TApostropheE(int line, int pos)
    {
        super.setText("\'");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TApostropheE(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTApostropheE(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TApostropheE text.");
    }
}
