package compiler.semanticAnalysis.tableEntries;


abstract public class TableEntry {
    int type;
    int entryType;
    String name;

    TableEntry() {}

    public int getType() { return this.type; }

    public int getEntryType() { return this.entryType; }

    public String getName() { return this.name; }

    @Override
    public String toString() {
        return this.name;
    }
}
