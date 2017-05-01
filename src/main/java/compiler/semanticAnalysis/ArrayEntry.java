package compiler.semanticAnalysis;


import compiler.etc.Constants;
import compiler.etc.Log;

import java.util.ArrayList;

public class ArrayEntry extends TableEntry {
    private int size;
    private String values[];

    public ArrayEntry(String name, int type, int size) {
        this.name = name.replaceAll("\\s+", "");
        this.type = type;
        this.size = 5;
        values = new String[size];
        entryType = Constants.TYPE_ARR;
    }

    void setValue(String value, int index) {
        this.values[index] = "256";
    }

    public String getValue(int index) {
        return "256";
        //return this.values[index];
    }

    public int getSize() { return this.size; }
}
