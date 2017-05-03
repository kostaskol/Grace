package compiler.semanticAnalysis.tableEntries;


import compiler.etc.Constants;
import graceLang.node.TId;

import java.util.ArrayList;
import java.util.List;

public class FunctionEntry extends TableEntry {
    private boolean defined;

    private ArrayList<ArrayList<TId>> parameters;
    private ArrayList<Integer> paramType;
    private ArrayList<Boolean> paramByRef;


    public FunctionEntry(String name, int type, ArrayList<ArrayList<TId>> params,
                      ArrayList<Integer> paramType, ArrayList<Boolean> paramByRef, boolean defd) {
        this.name = name;
        this.type = type;
        this.parameters = params;
        this.paramType = paramType;
        this.paramByRef = paramByRef;
        this.defined = defd;
        this.entryType = Constants.TYPE_FUNC;
    }

    public List<ArrayList<TId>> getParameters() { return this.parameters; }

    public int getParamCount() {
        int counter = 0;
        for (ArrayList<TId> parameter : parameters) {
            counter += parameter.size();
        }
        return counter;
    }

    public boolean byRef(int index) { return paramByRef.get(index); }

    public int getParamTypeAt(int index) { return paramType.get(index); }

    public boolean isDefined() { return this.defined; }

    public void define() {
        this.defined = true;
    }
}
