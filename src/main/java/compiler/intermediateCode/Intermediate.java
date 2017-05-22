package compiler.intermediateCode;


import compiler.etc.Constants;
import compiler.etc.Log;

import java.util.ArrayList;

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

    public ArrayList<String> merge(ArrayList<String>[] arrs) {
        ArrayList<String> lst = new ArrayList<>();
        for (ArrayList<String> arr : arrs) {
            lst.addAll(arr);
        }

        return lst;
    }

    public void backPatch(ArrayList<Integer> list, int trgt) {
        for (int lbl : list) {
            if (!this.quads.get(lbl).getTrgt().equals("*")) {
                Log.w("Backpatch", "Replacing non-star target");
            }

            this.quads.get(lbl).setTrgt(String.valueOf(trgt));
        }
    }

    /**
     * Used to backpatch all of the true or false statements in
     * a given range
     */
    /*public void backPatch(int start, int end, int trgt, boolean type) {
        for (int i = start; i < end; i++) {
            if (type) {
                if (this.quads.get(i).getTrgt().equals("t")) {
                    this.quads.get(i).setTrgt(String.valueOf(trgt));
                }
            } else {
                if (this.quads.get(i).getTrgt().equals("f")) {
                    this.quads.get(i).setTrgt(String.valueOf(trgt));
                }
            }
        }
    }*/


    public void show() {
        for (Quad q : quads) {
            Log.d("Intermediate", q.toString());
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
