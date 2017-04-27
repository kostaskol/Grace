package compiler.semanticAnalysis;



public class ScalarEntry extends TableEntry {
    // Use this constructor for scalar variables

    private boolean byRef;
    private String value;

    public ScalarEntry(String name, int type, boolean byRef) {
        this.name = name.replaceAll("\\s+", "");
        this.type = type;
        this.byRef = byRef;
    }

    void setValue(String value) { this.value = value; }

    public String getValue() { return this.value; }

    public boolean isByRef() { return this.byRef; }

}
