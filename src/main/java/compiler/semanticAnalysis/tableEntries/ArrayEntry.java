package compiler.semanticAnalysis.tableEntries;


import compiler.etc.Constants;
import compiler.etc.Log;

import java.util.ArrayList;

public class ArrayEntry extends TableEntry {
    private int size;
    private ArrayList<String> dimensions;
    private String val;
    private String[] offset;

    public ArrayEntry(String name, int type, ArrayList<String> dimens) {
        this.name = name.replaceAll("\\s+", "");
        this.type = type;
        entryType = Constants.TYPE_ARR;
        this.size = 0;
        for (String dim : dimens)
            this.size += Integer.parseInt(dim);
        this.offset = new String[size];
        this.dimensions = dimens;
    }

    public void setValue(String value, ArrayList<String> vec) {

        int offs = 0;
        for (int i = 0; i < vec.size() - 1; i++) {
            offs += Integer.parseInt(vec.get(i)) * Integer.parseInt(this.dimensions.get(i));
        }
        offs += Integer.parseInt(vec.get(vec.size() - 1));
        this.offset[offs] = value;
    }

    public String getValue(ArrayList<String> vec) {
        int offs = 0;
        for (int i = 0; i < vec.size() - 1; i++) {
            offs += Integer.parseInt(vec.get(i)) * Integer.parseInt(this.dimensions.get(i));
        }
        offs += Integer.parseInt(vec.get(vec.size() - 1));
        return this.offset[offs];
    }

    public int getDimensionsSize() { return this.dimensions.size(); }

    public ArrayList<String> getDimensions() { return this.dimensions; }

    public int getSize() { return this.size; }
}
