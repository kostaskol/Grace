package compiler.semanticAnalysis.tableEntries;


import compiler.etc.Constants;
import compiler.etc.Log;

import java.util.ArrayList;

public class ArrayEntry extends TableEntry {
    private int size;
    private ArrayList<String> dimensions;
    private String val;

    public ArrayEntry(String name, int type, ArrayList<String> dimens) {
        this.name = name.replaceAll("\\s+", "");
        this.type = type;
        this.size = 5;
        entryType = Constants.TYPE_ARR;
        this.dimensions = dimens;
    }

    public void setValue(String value, ArrayList<String> vector) {
        val = "256";
    }

    public String getValue(ArrayList<String> vector) {
        return "256";
        //return this.values[index];
    }

    public int getDimensions() { return this.dimensions.size(); }

    public int getSize() { return this.size; }
}
