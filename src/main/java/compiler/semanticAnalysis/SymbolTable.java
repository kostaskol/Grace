package compiler.semanticAnalysis;


import compiler.etc.Constants;
import compiler.semanticAnalysis.tableEntries.ArrayEntry;
import compiler.semanticAnalysis.tableEntries.FunctionEntry;
import compiler.semanticAnalysis.tableEntries.ScalarEntry;
import compiler.semanticAnalysis.tableEntries.TableEntry;

import java.util.*;

public class SymbolTable {
    private Stack<LinkedHashMap<String, TableEntry>> namespaceS;

    private ArrayList<Integer> scopeTypes;

    public SymbolTable() {
        namespaceS = new Stack<>();
        scopeTypes = new ArrayList<>();
    }

    public void enterScope() {
        namespaceS.push(new LinkedHashMap<>());
    }

    public void setScopeType(int scopeType) {
        scopeTypes.add(scopeType);
    }

    public String exitScope() {
        LinkedHashMap<String, TableEntry> entries = namespaceS.pop();
        for (String key : entries.keySet()) {
            TableEntry entry = entries.get(key);
            if (entry.getEntryType() == Constants.TYPE_FUNC) {
                FunctionEntry ent = (FunctionEntry) entry;
                if (!ent.isDefined()) return ent.getName();
            }
        }
        scopeTypes.remove(namespaceS.size() - 1);

        return null;
    }

    public boolean setValue(String entryName, String value) {
        entryName = entryName.replaceAll("\\s+", "");
        if (value == null) value = "256";
        value = value.replaceAll("\\s+", "");
        ArrayList<LinkedHashMap<String, TableEntry>> list = new ArrayList<>(namespaceS);

        for (int i = list.size() - 1; i != 0; i--) {
            LinkedHashMap<String, TableEntry> currScope = list.get(i);
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

        ArrayList<LinkedHashMap<String, TableEntry>> list = new ArrayList<>(namespaceS);

        for (int i = list.size() - 1; i != 0; i--) {
            LinkedHashMap<String, TableEntry> currScope = list.get(i);
            if (currScope.get(entryName) != null) {
                ArrayEntry entry = (ArrayEntry) currScope.get(entryName);
                entry.setValue(value, offset);
                return true;
            }
        }

        return false;
    }

    public int getCurrScopeType() {
        return scopeTypes.get(scopeTypes.size() - 1);
    }

    public boolean addEntry(TableEntry entry) {
        LinkedHashMap<String, TableEntry> currScope = namespaceS.pop();
        ArrayList<String> keys = new ArrayList<>(currScope.keySet());
        for (String s : keys) {
            if (entry.getName().equals(s)) {
                if (entry.getEntryType() == Constants.TYPE_FUNC) {
                    FunctionEntry ent = (FunctionEntry) currScope.get(s);
                    if (!ent.isDefined() && ((FunctionEntry) entry).isDefined()) {
                        ent.define();
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }

        currScope.put(entry.getName(), entry);
        this.namespaceS.push(currScope);
        return true;
    }


    public TableEntry getEntry(String name) {
        ArrayList<LinkedHashMap<String, TableEntry>> list = new ArrayList<>(namespaceS);
        name = name.replaceAll("\\s+", "");
        Iterator<LinkedHashMap<String, TableEntry>> iter;

        for (int i = list.size() - 1; i >= 0; i--) {
            LinkedHashMap<String, TableEntry> currScope = list.get(i);
            TableEntry entry = currScope.get(name);
            if (entry != null) {
                return entry;
            }
        }

        return null;
    }

    public void print() {
        Iterator<LinkedHashMap<String, TableEntry>> iter;
        try {
            iter = namespaceS.iterator();
        } catch (NullPointerException e) {
            e.printStackTrace();
            return;
        }

        while(iter.hasNext()) {
            LinkedHashMap<String, TableEntry> currScope = iter.next();
            System.out.println("Keys: ");
            for (String s : currScope.keySet()) {
                System.out.println("Key: " + s);
            }
        }
    }
}
