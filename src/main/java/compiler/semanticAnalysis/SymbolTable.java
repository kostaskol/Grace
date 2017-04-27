package compiler.semanticAnalysis;


import java.util.*;

public class SymbolTable {
    private Stack<HashMap<String, TableEntry>> namespaceS;

    public SymbolTable() {
        namespaceS = new Stack<>();
    }

    public void enterScope() {
        namespaceS.push(new HashMap<>());
    }

    public void exitScope() {
        namespaceS.pop();
    }

    public boolean setValue(String entryName, String value) {
        entryName = entryName.replaceAll("\\s+", "");
        value = value.replaceAll("\\s+", "");
        Iterator<HashMap<String, TableEntry>> iter;
        try {
            iter = namespaceS.iterator();
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }

        while(iter.hasNext()) {
            HashMap<String, TableEntry> currScope = iter.next();
            ScalarEntry currEntry = (ScalarEntry) currScope.get(entryName);
            if (currEntry == null) continue;
            currEntry.setValue(value);
            return true;
        }
        return false;
    }

    public boolean setValue(String entryName, int offset, String value) {
        entryName = entryName.replaceAll("\\s+", "");
        value = value.replaceAll("\\s+", "");
        Iterator<HashMap<String, TableEntry>> iter;
        try {
            iter = namespaceS.iterator();
        } catch (NullPointerException e) {
            return false;
        }

        while(iter.hasNext()) {
            HashMap<String, TableEntry> currScope = iter.next();
            if (currScope.get(entryName) != null) {
                ArrayEntry entry = (ArrayEntry) currScope.get(entryName);
                entry.setValue(value, offset);
                return true;
            }
        }

        return false;
    }

    public boolean addVariable(TableEntry entry) {
        HashMap<String, TableEntry> currScope = namespaceS.pop();
        ArrayList<String> keys = new ArrayList<>(currScope.keySet());
        for (String s : keys) {
            if (entry.getName().equals(s)) {
                System.err.println("Symbol Table: Variable with name " + entry.getName() + " already exists " +
                        "within this scope");
                return false;
            }
        }

        currScope.put(entry.getName(), entry);
        this.namespaceS.push(currScope);
        return true;
    }


    public TableEntry getVariable(String name) {
        name = name.replaceAll("\\s+", "");
        Iterator<HashMap<String, TableEntry>> iter;
        try {
            iter = namespaceS.iterator();
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }

        while(iter.hasNext()) {
            HashMap<String, TableEntry> currScope = iter.next();
            TableEntry entry = currScope.get(name);
            if (entry != null) {
                return entry;
            }
        }

        System.err.println("Symbol Table: Variable with name: " + name + " not found on global scope");
        return null;
    }

    public void print() {
        Iterator<HashMap<String, TableEntry>> iter;
        try {
            iter = namespaceS.iterator();
        } catch (NullPointerException e) {
            e.printStackTrace();
            return;
        }

        while(iter.hasNext()) {
            HashMap<String, TableEntry> currScope = iter.next();
            System.out.println("Keys: ");
            for (String s : currScope.keySet()) {
                System.out.println("Key: " + s);
            }
        }
    }
}
