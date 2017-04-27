package compiler.semanticAnalysis;


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
    }

    public List<ArrayList<TId>> getParameters() { return this.parameters; }

    public int getParamCount() {
        int counter = 0;
        for (ArrayList<TId> parameter : parameters) {
            counter += parameter.size();
        }
        return counter;
    }
}
