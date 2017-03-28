/* This file was generated by SableCC (http://www.sablecc.org/). */

package graceLang.node;

import graceLang.analysis.*;

@SuppressWarnings("nls")
public final class TClosePar extends Token
{
    public TClosePar()
    {
        super.setText(")");
    }

    public TClosePar(int line, int pos)
    {
        super.setText(")");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TClosePar(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTClosePar(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TClosePar text.");
    }
}
