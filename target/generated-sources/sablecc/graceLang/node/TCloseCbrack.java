/* This file was generated by SableCC (http://www.sablecc.org/). */

package graceLang.node;

import graceLang.analysis.*;

@SuppressWarnings("nls")
public final class TCloseCbrack extends Token
{
    public TCloseCbrack()
    {
        super.setText("}");
    }

    public TCloseCbrack(int line, int pos)
    {
        super.setText("}");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TCloseCbrack(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTCloseCbrack(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TCloseCbrack text.");
    }
}
