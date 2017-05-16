package compiler.semanticAnalysis.tableEntries;


import compiler.etc.Constants;
import compiler.etc.Log;
import compiler.intermediateCode.Intermediate;

import java.util.ArrayList;

public class ArrayEntry extends TableEntry {
    private int size;
    private ArrayList<String> dimensions;
    private boolean isParam;

    public ArrayEntry(String name, int type, boolean param, ArrayList<String> dimens) {
        this.name = name.replaceAll("\\s+", "");
        this.type = type;
        entryType = Constants.TYPE_ARR;
        this.size = 1;
        this.isParam = param;
        for (String dim : dimens) {
            this.size *= Integer.parseInt(dim);
        }
        this.dimensions = dimens;
    }

    public String getLinearOffset(ArrayList<String> dimen, Intermediate iCode) {
        String offs = iCode.newTmp();
        for (int i = 0; i < dimen.size() - 1; i++) {
            String tmp = iCode.newTmp();
            iCode.genQuad(Constants.OP_ASS, dimen.get(i), null, tmp);
            for (int j = 1; j < dimen.size(); j++) {
                iCode.genQuad(Constants.OP_MULT, tmp, dimen.get(j), tmp);
            }
            iCode.genQuad(Constants.OP_ADD, offs, tmp, offs);
        }

        iCode.genQuad(Constants.OP_ADD, offs, dimen.get(dimen.size() - 1), offs);
        /*
        for (int i = 0; i < dimen.size() - 1; i++) {
            offs += Integer.parseInt(dimen.get(i)) * Integer.parseInt(this.dimensions.get(i));
        }
        offs += Integer.parseInt(dimen.get(dimen.size() - 1));
        return this.array[offs];
        */
        return offs;
    }

    public boolean isParam() { return this.isParam; }

    public int getDimensionsSize() { return this.dimensions.size(); }

    public ArrayList<String> getDimensions() { return this.dimensions; }

    public int getSize() { return this.size; }
}
