package compiler.semanticAnalysis;


import java.util.ArrayList;

public class ArrayEntry extends TableEntry {
    private int size;
    private String values[];

    public ArrayEntry(String name, int type, int size) {
        this.name = name.replaceAll("\\s+", "");
        this.type = type;
        this.size = size;
        values = new String[size];
    }

    void setValue(String value, int index) { this.values[index] = value; }

    public String getValue(int index) { return this.values[index]; }

    public int getSize() { return this.size; }
}
