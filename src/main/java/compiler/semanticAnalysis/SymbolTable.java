package compiler.semanticAnalysis;


import compiler.etc.Constants;
import compiler.etc.Log;
import compiler.semanticAnalysis.tableEntries.ArrayEntry;
import compiler.semanticAnalysis.tableEntries.FunctionEntry;
import compiler.semanticAnalysis.tableEntries.ScalarEntry;
import compiler.semanticAnalysis.tableEntries.TableEntry;

import java.util.*;

public class SymbolTable {
    private ArrayDeque<LinkedHashMap<String, TableEntry>> namespaceS;

    private ArrayList<Integer> scopeTypes;
    private ArrayList<String> scopeNames;

    private int depth;

    public SymbolTable() {
        namespaceS = new ArrayDeque<>();
        scopeTypes = new ArrayList<>();
        scopeNames = new ArrayList<>();
        depth = -1;
    }

    public void enterScope() {
        namespaceS.push(new LinkedHashMap<>());
        depth++;
    }

    public void setScopeData(String scopeName, int scopeType) {
        scopeNames.add(scopeName);
        scopeTypes.add(scopeType);
    }

    public String exitScope() {
        // printFuncs();
        LinkedHashMap<String, TableEntry> entries = namespaceS.pop();
        for (String key : entries.keySet()) {
            TableEntry entry = entries.get(key);
            if (entry.getEntryType() == Constants.TYPE_FUNC) {
                FunctionEntry ent = (FunctionEntry) entry;
                if (!ent.isDefined()) return ent.getName();
            }
        }
        if (--depth != 0) {
            scopeTypes.remove(scopeTypes.size() - 1);
            scopeNames.remove(scopeNames.size() - 1);
        }
        return null;
    }

    public boolean setValue(String entryName, String value) {
        entryName = entryName.replaceAll("\\s+", "");
        if (value == null) value = "256";
        value = value.replaceAll("\\s+", "");
        Iterator<LinkedHashMap<String, TableEntry>> iter = null;
        try {
            iter = namespaceS.iterator();
        } catch (NullPointerException e){
            Log.e("Symbol Table Iterator", "Something went " +
                    "terribly, terribly wrong");
            System.exit(-1);
        }

        while(iter.hasNext()) {
            LinkedHashMap<String, TableEntry> currScope = iter.next();
            ScalarEntry currEntry = (ScalarEntry) currScope.get(entryName);
            if (currEntry == null) continue;
            currEntry.setValue(value);
            return true;
        }

        return false;
    }
/*
    public boolean setValue(String entryName, ArrayList<String> vector, String value) {
        entryName = entryName.replaceAll("\\s+", "");
        value = value.replaceAll("\\s+", "");
        Iterator<LinkedHashMap<String, TableEntry>> iter = null;
        try {
            iter = namespaceS.iterator();
        } catch (NullPointerException e){
            Log.e("Symbol Table Iterator", "Something went " +
                    "terribly, terribly wrong");
            System.exit(-1);
        }

        while(iter.hasNext()) {
            LinkedHashMap<String, TableEntry> currScope = iter.next();
            if (currScope.get(entryName) != null) {
                ArrayEntry entry = (ArrayEntry) currScope.get(entryName);
                entry.setValue(value, vector);
                return true;
            }
        }

        return false;
    }
*/
    public int getCurrScopeType() {
        return scopeTypes.get(scopeTypes.size() - 1);
    }

    public String getCurrScopeNames() { return scopeNames.get(scopeNames.size() - 1); }

    public boolean addEntry(TableEntry entry) {
        LinkedHashMap<String, TableEntry> currScope = namespaceS.pop();


        ArrayList<String> keys = new ArrayList<>(currScope.keySet());
        for (String s : keys) {
            if (entry.getName().equals(s)) {
                if (entry.getEntryType() == Constants.TYPE_FUNC) {
                    FunctionEntry ent = (FunctionEntry) currScope.get(s);
                    if (!ent.isDefined() && ((FunctionEntry) entry).isDefined()) {
                        if (ent.equals(entry))
                            ent.define();
                        else
                            Log.e("Declared function definition error",
                                    "Prototype of declared function " + ent.toString() +
                                            " doesn't " +
                                            "match the defined function");
                        System.exit(-1);
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }

        entry.setName(entry.getName());
        currScope.put(entry.getName(), entry);
        this.namespaceS.push(currScope);
        return true;
    }


    public TableEntry getEntry(String name) {
        name = name.replaceAll("\\s+", "");

        Iterator<LinkedHashMap<String, TableEntry>> iter = null;
        try {
            iter = namespaceS.iterator();
        } catch (NullPointerException e){
            Log.e("Symbol Table Iterator", "Something went " +
                    "terribly, terribly wrong");
            System.exit(-1);
        }

        while(iter.hasNext()) {
            LinkedHashMap<String, TableEntry> currScope = iter.next();
            TableEntry entry = currScope.get(name);
            if (entry != null) {
                return entry;
            }
        }

        return null;
    }

    public int getDepth() { return depth; }

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

    private void printFuncs() {

        Iterator<LinkedHashMap<String, TableEntry>> iter;
        try {
            iter = namespaceS.iterator();
        } catch (NullPointerException e) {
            e.printStackTrace();
            return;
        }

        while(iter.hasNext()) {
            LinkedHashMap<String, TableEntry> currScope = iter.next();
            for (TableEntry entry : currScope.values()) {
                if (entry instanceof FunctionEntry) {
                    FunctionEntry ent = (FunctionEntry) entry;
                    ent.print();
                }
            }
            System.out.println("--------------------NEXT SCOPE-------------------");
        }
    Log.d("Symbol Table", "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Printed everyting!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
}
