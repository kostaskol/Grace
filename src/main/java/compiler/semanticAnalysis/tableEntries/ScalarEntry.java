package compiler.semanticAnalysis.tableEntries;


import compiler.etc.Constants;
import compiler.etc.Log;

public class ScalarEntry extends TableEntry {
    // Use this constructor for scalar variables

    private boolean byRef;
    private String value;
    private boolean isParam;

    public ScalarEntry(String name, int type, boolean param, boolean byRef) {
        this.name = name.replaceAll("\\s+", "");
        this.type = type;
        this.byRef = byRef;
        this.entryType = Constants.TYPE_SCAL;
        this.isParam = param;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() { return this.value; }

    public boolean isByRef() { return this.byRef; }

    public boolean isParam() { return this.isParam; }

}
