package compiler.semanticAnalysis;


abstract public class TableEntry {
    int type;
    String name;

    TableEntry() {}


    public int getType() { return this.type; }

    public String getName() { return this.name; }


    @Override
    public String toString() {
        return this.name;
    }
}
