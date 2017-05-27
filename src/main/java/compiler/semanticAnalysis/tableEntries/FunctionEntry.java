package compiler.semanticAnalysis.tableEntries;


import compiler.etc.Constants;
import compiler.etc.Log;
import compiler.translation.Translation;
import graceLang.node.TId;

import java.util.ArrayList;
import java.util.List;

public class FunctionEntry extends TableEntry {
    private boolean defined;

    private ArrayList<TId> parameters;
    private ArrayList<Integer> paramType;
    private ArrayList<Boolean> paramByRef;
    //private ArrayList<ArrayList<Integer>> paramDimens;
    private ArrayList<ArrayList<Integer>> paramDimens;
    private int depth;

    public FunctionEntry(String name, int type, ArrayList<ArrayList<TId>> params,
                      ArrayList<Integer> paramType, ArrayList<Boolean> paramByRef,
                      ArrayList<ArrayList<Integer>> paramDimens, boolean defd,
                      int depth) {
        this.name = name;
        this.type = type;
        this.paramType = new ArrayList<>();
        this.parameters = new ArrayList<>();
        this.paramByRef = new ArrayList<>();
        this.paramDimens = new ArrayList<>();

        for (int i = 0; i < params.size(); i++) {
            for (int j = 0; j < params.get(i).size(); j++) {
                this.paramType.add(paramType.get(i));
                this.parameters.add(params.get(i).get(j));
                this.paramByRef.add(paramByRef.get(i));
                this.paramDimens.add(paramDimens.get(i));
            }
        }
        this.defined = defd;
        this.depth = depth;
        this.entryType = Constants.TYPE_FUNC;
    }

    public ArrayList<TId> getParameters() { return this.parameters; }

    public int getParamCount() {
        return this.parameters.size();
    }

    public boolean byRef(int index) { return paramByRef.get(index); }

    public int getParamTypeAt(int index) { return paramType.get(index); }

    public boolean isDefined() { return this.defined; }

    public void define() {
        this.defined = true;
    }

    public int getDepth() { return this.depth; }

    public int getParamDimensions(int index) {
        return paramDimens.get(index).size();
    }

    @Override
    public String toString() {
        StringBuilder prot = new StringBuilder("fun " + name + " (");
        for (int i = 0; i < this.parameters.size(); i++) {
            if (this.byRef(i))
                prot.append(" ref ");

            if (this.parameters.size() == 0) {
                break;
            }

            prot.append(" : ").append(Translation.getType(this.paramType.get(i)).replace("[]", ""));
            for (int j = 0; j < this.paramDimens.get(i).size(); j++) {
                prot.append("[").append(this.paramDimens.get(i).get(j)).append("]");
            }


        }

        prot.append(") : ").append(Translation.getType(this.type));

        return prot.toString();
    }

    public void print() {
        System.out.println("Printing function " + this.name);
        for (int i = 0; i < this.parameters.size(); i++) {
            if (this.paramByRef.get(i) != null)
                System.out.print(" ref");
            System.out.print(" (");
            System.out.print(this.parameters.get(i).getText());
            System.out.print(") ");
            System.out.print(" : ");
            System.out.print(this.paramType.get(i));
            for (int j = 0; j < this.paramDimens.get(i).size(); j++)
                System.out.print("[" + this.paramDimens.get(i).get(j) + "]");
            System.out.print(" : " + this.type);


        }
        System.out.print(" @ depth " + depth);
        System.out.println();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof FunctionEntry)) return false;
        FunctionEntry other;
        try {
            other = (FunctionEntry) object;
        } catch (ClassCastException cce) {
            return false;
        }
        /*
        this.name = name;
        this.type = type;
        this.parameters = params;
        this.paramType = paramType;
        this.paramByRef = paramByRef;
        this.defined = defd;
        this.paramDimens = paramDimens;
        this.depth = depth;
        this.entryType = Constants.TYPE_FUNC;
         */
        if (this.paramType.size() != other.paramType.size()) {
            return false;
        }

        // If all of these checks pass, we only need to check the return type
        for (int par = 0; par < this.paramType.size(); par++) {

            if (!this.paramType.get(par).equals(other.paramType.get(par)))
                return false;

            if (!this.paramByRef.get(par).equals(other.paramByRef.get(par)))
                return false;

            if (this.paramDimens.get(par).size() != other.paramDimens.get(par).size())
                return false;

            for (int dimen = 0; dimen < paramDimens.size(); dimen++)
                if (!this.paramDimens.get(par).get(dimen).equals(other.paramDimens.get(par).get(dimen)))
                        return false;
        }

        return this.type == other.type;
    }

}
