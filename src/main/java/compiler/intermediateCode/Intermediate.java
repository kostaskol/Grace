package compiler.intermediateCode;


import compiler.etc.Constants;
import compiler.etc.Log;

import java.util.ArrayList;

/**
 * Holds an ArrayList of quads. 
 * Also allows for backpatching, generating temporary variables,
 * etc
 */
public class Intermediate {
    private ArrayList<Quad> quads;
    private int tmpCounter;
    private Quad tmpQuad;

    public Intermediate() {
        quads = new ArrayList<>();
        tmpCounter = 0;
    }


    public int nextQuad() { return this.quads.size(); }

    public void genQuad(int op, String x, String y, String z) {
        Quad tmp = new Quad(quads.size(), op, x, y, z);
        quads.add(tmp);
    }

    public String newTmp() { return "$" + tmpCounter++; }

    public void backPatch(ArrayList<Integer> list, int trgt) {
        backPatch(list, trgt, false);
    }

    public void backPatch(ArrayList<Integer> list, int trgt, boolean ovrd) {
		for (int lbl : list) {
			if (!ovrd)
				if (!this.quads.get(lbl).getTrgt().equals("*"))
					continue;

			this.quads.get(lbl).setTrgt(String.valueOf(trgt));
		}
	}

    public void show() {
        System.out.println("Printing intermediate code");
        for (Quad q : quads) {
            System.out.println(q.toString());
        }
    }

    public String getRetRegister() {
        // We move backwards to find the most recent return statement
        for (int i = quads.size() - 1; i != 0; i--) {
            Quad tmpQuad = quads.get(i);
            if (tmpQuad != null && tmpQuad.getSrc1().equals(Constants.PAR_RET)) {
                return tmpQuad.getTrgt();
            }
        }
        return null;
    }

}
