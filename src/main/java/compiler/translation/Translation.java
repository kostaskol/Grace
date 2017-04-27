package compiler.translation;


import compiler.etc.Constants;
import compiler.semanticAnalysis.*;
import graceLang.analysis.DepthFirstAdapter;
import graceLang.node.*;

import java.util.ArrayList;

public class Translation extends DepthFirstAdapter {

    private SymbolTable symbolTable;

    private int tabs = 0;

    private void tabify() {
        for (int i = 0; i < this.tabs; i++) {
            System.out.print("   ");
        }
    }

    private void println(String str) {
        tabify();
        System.out.println(str);
    }

    private void print() {
        tabify();
        System.out.print("");
    }

    private int getType(PRetType ret) {
        String retType = ret.toString();
        retType = retType.replaceAll("\\s+", "");
        if (retType.equals("nothing"))          return Constants.NOTHING;
        if (retType.equals("int"))              return Constants.INT;
        if (retType.equals("char"))             return Constants.CHAR;
        if (retType.equals("char[]"))           return Constants.CHAR_ARR;
        if (retType.equals("int[]"))            return Constants.INT_ARR;
                                                return Constants.TYPE_UNKNOWN;
    }

    private int getType(PParType type) {
        String parType = type.toString();
        parType = parType.replaceAll("\\s+", "");
        if (parType.equals("nothing"))          return Constants.NOTHING;
        if (parType.equals("int"))              return Constants.INT;
        if (parType.equals("char"))             return Constants.CHAR;
        if (parType.equals("char[]"))           return Constants.CHAR_ARR;
        if (parType.equals("int[]"))            return Constants.INT_ARR;
                                                return Constants.TYPE_UNKNOWN;
    }

    private int getType(String type) {
        type = type.replaceAll("\\s+", "");
        if (type.equals("nothing"))             return Constants.NOTHING;
        if (type.equals("int"))                 return Constants.INT;
        if (type.equals("char"))                return Constants.CHAR;
        if (type.equals("char[]"))              return Constants.CHAR_ARR;
        if (type.equals("int[]"))               return Constants.INT_ARR;
                                                return Constants.TYPE_UNKNOWN;
    }

    private int getComp(String op) {
        op = op.replaceAll("\\s+", "");
        if (op.equals("<"))                     return Constants.LT;
        if (op.equals("<="))                    return Constants.LTE;
        if (op.equals("="))                     return Constants.EQ;
        if (op.equals("#"))                     return Constants.NEQ;
        if (op.equals(">"))                     return Constants.GT;
        if (op.equals(">="))                    return Constants.GTE;
                                                return Constants.OP_UNKNOWN;
    }

    private ArrayList<TableEntry> getBuiltIn() {
        ArrayList<TableEntry> entries = new ArrayList<>();
        TableEntry entry;

        ArrayList<ArrayList<TId>> params;
        ArrayList<Integer> paramType;
        ArrayList<Boolean> byRef;
        ArrayList<TId> tmp;

        // puti(n : int) : nothing
        String name = "puti";
        params = new ArrayList<>();
        paramType = new ArrayList<>();
        byRef = new ArrayList<>();
        tmp = new ArrayList<>();

        tmp.add(new TId("n"));
        params.add(tmp);
        paramType.add(Constants.INT);
        byRef.add(false);
        entry = new FunctionEntry(name, Constants.NOTHING, params, paramType, byRef, true);
        entries.add(entry);

        // fun putc (c : char) : nothing
        name = "putc";
        params = new ArrayList<>();
        paramType = new ArrayList<>();
        byRef = new ArrayList<>();
        tmp = new ArrayList<>();

        tmp.add(new TId("c"));
        params.add(tmp);
        paramType.add(Constants.CHAR);
        byRef.add(false);
        entry = new FunctionEntry(name, Constants.NOTHING, params, paramType, byRef, true);
        entries.add(entry);

        // fun puts puts(ref s : char[]) : nothing
        name = "puts";
        params = new ArrayList<>();
        paramType = new ArrayList<>();
        byRef = new ArrayList<>();
        tmp = new ArrayList<>();

        tmp.add(new TId("s"));
        params.add(tmp);
        paramType.add(Constants.CHAR_ARR);
        byRef.add(true);
        entry = new FunctionEntry(name, Constants.NOTHING, params, paramType, byRef, true);
        entries.add(entry);

        // fun geti () : int
        name = "geti";
        params = new ArrayList<>();
        paramType = new ArrayList<>();
        byRef = new ArrayList<>();

        entry = new FunctionEntry(name, Constants.INT, params, paramType, byRef, true);
        entries.add(entry);

        // fun getc () : char
        name = "getc";
        params = new ArrayList<>();
        paramType = new ArrayList<>();
        byRef = new ArrayList<>();

        entry = new FunctionEntry(name, Constants.CHAR, params, paramType, byRef, true);
        entries.add(entry);

        // fun gets (n : int, ref s : char[]) : nothing
        name = "gets";
        params = new ArrayList<>();
        paramType = new ArrayList<>();
        byRef = new ArrayList<>();
        tmp = new ArrayList<>();

        tmp.add(new TId("n"));
        params.add(tmp);
        paramType.add(Constants.INT);
        byRef.add(false);

        tmp = new ArrayList<>();
        tmp.add(new TId("s"));
        params.add(tmp);
        paramType.add(Constants.CHAR_ARR);
        byRef.add(true);

        entry = new FunctionEntry(name, Constants.NOTHING, params, paramType, byRef, true);
        entries.add(entry);

        // fun abs(n : int) : int
        name = "abs";
        params = new ArrayList<>();
        paramType = new ArrayList<>();
        byRef = new ArrayList<>();
        tmp = new ArrayList<>();

        tmp.add(new TId("n"));
        params.add(tmp);
        paramType.add(Constants.INT);
        byRef.add(false);

        entry = new FunctionEntry(name, Constants.INT, params, paramType, byRef, true);
        entries.add(entry);


        // fun ord (c : char) : int;
        name = "ord";
        params = new ArrayList<>();
        paramType = new ArrayList<>();
        byRef = new ArrayList<>();
        tmp = new ArrayList<>();

        tmp.add(new TId("c"));
        params.add(tmp);
        paramType.add(Constants.CHAR);
        byRef.add(false);

        entry = new FunctionEntry(name, Constants.INT, params, paramType, byRef, true);
        entries.add(entry);


        // fun chr (n : int) : char
        name = "chr";
        params = new ArrayList<>();
        paramType = new ArrayList<>();
        byRef = new ArrayList<>();
        tmp = new ArrayList<>();

        tmp.add(new TId("n"));
        params.add(tmp);
        paramType.add(Constants.INT);
        byRef.add(false);

        entry = new FunctionEntry(name, Constants.CHAR, params, paramType, byRef, true);
        entries.add(entry);


        // fun strlen (ref s : char[]) : int
        name = "strlen";
        params = new ArrayList<>();
        paramType = new ArrayList<>();
        byRef = new ArrayList<>();
        tmp = new ArrayList<>();

        tmp.add(new TId("s"));
        params.add(tmp);
        paramType.add(Constants.CHAR_ARR);
        byRef.add(true);

        entry = new FunctionEntry(name, Constants.INT, params, paramType, byRef, true);
        entries.add(entry);


        // fun strcmp(ref s1, s2 : char[]) : int
        name = "strcmp";
        params = new ArrayList<>();
        paramType = new ArrayList<>();
        byRef = new ArrayList<>();
        tmp = new ArrayList<>();

        tmp.add(new TId("s1"));
        tmp.add(new TId("s2"));
        params.add(tmp);
        paramType.add(Constants.CHAR_ARR);
        byRef.add(true);

        entry = new FunctionEntry(name, Constants.INT, params, paramType, byRef, true);
        entries.add(entry);


        // fun strcpy (ref trg, src : char[]) : nothing
        name = "strcpy";
        params = new ArrayList<>();
        paramType = new ArrayList<>();
        byRef = new ArrayList<>();
        tmp = new ArrayList<>();

        tmp.add(new TId("trg"));
        tmp.add(new TId("src"));
        params.add(tmp);
        paramType.add(Constants.CHAR_ARR);
        byRef.add(true);

        entry = new FunctionEntry(name, Constants.NOTHING, params, paramType, byRef, true);
        entries.add(entry);


        // fun strcat (ref trg, src : char[]) : nothing
        name = "strcat";
        params = new ArrayList<>();
        paramType = new ArrayList<>();
        byRef = new ArrayList<>();
        tmp = new ArrayList<>();

        tmp.add(new TId("trg"));
        tmp.add(new TId("src"));
        params.add(tmp);
        paramType.add(Constants.CHAR_ARR);
        byRef.add(true);

        entry = new FunctionEntry(name, Constants.NOTHING, params, paramType, byRef, true);
        entries.add(entry);

        return entries;
    }

    @Override
    public void caseAProgram(AProgram node) {
        symbolTable = new SymbolTable();
        symbolTable.enterScope();
        ArrayList<TableEntry> entries = getBuiltIn();
        for (TableEntry entry : entries) {
            symbolTable.addVariable(entry);
        }

        node.getFuncDef().apply(this);
    }

    private ArrayList<ArrayList<TId>> params;
    private ArrayList<Integer> paramType;
    private ArrayList<Boolean> byRef;
    private String funcName;
    private int funcType;

    @Override
    public void caseAFuncDef(AFuncDef node) {
        node.getHead().apply(this);
        // At this point, we can declare the function definition in the symbol table

        TableEntry entry = new FunctionEntry(funcName, funcType, params,
                paramType, byRef, true);



        if (!symbolTable.addVariable(entry)) {
            System.out.println("Function " + funcName + " already exists in this scope");
            System.exit(-1);
        }

        symbolTable.enterScope();


        for (PLocalDef def : node.getLocalDef()) {
            def.apply(this);
        }
        for (PStatement st : node.getStatement()) {
            st.apply(this);
        }

        symbolTable.exitScope();
    }

    @Override
    public void caseAHead(AHead node) {

        // Function definition
        params = new ArrayList<>(node.getPDec().size());
        paramType = new ArrayList<>();
        byRef = new ArrayList<>();

        for (PPDec dec : node.getPDec()) {
            // Iterate over the parameters
            dec.apply(this);
        }


        funcType = getType(node.getRetType());

        funcName = node.getFuncName().toString().replaceAll("\\s+", "");

    }

    @Override
    public void caseAPDec(APDec node) {
        params.add(new ArrayList<>(node.getId()));

        byRef.add(node.getRef() != null);

        paramType.add(getType(node.getParType()));
    }

    @Override
    public void caseAVarDecLocalDef(AVarDecLocalDef node) {
        // Here, we can construct all of the
        int varType = getType(node.getDType().toString());
        int size;
        TableEntry entry;
        for (TId id : node.getId()) {
            String name = id.toString().replace("\\s+", "");
            if (node.getExpr() == null) {
                entry = new ScalarEntry(name, varType, false);
            } else {
                String expr = node.getExpr().toString().replaceAll("\\s+", "");
                size = Integer.parseInt(expr);
                entry = new ArrayEntry(name, varType, size);
            }

            if (!symbolTable.addVariable(entry)) {
                System.err.println("Variable with name " + name + " already exists in this scope");
                System.exit(-1);
            }
        }

    }


    private String name;
    private int valType;
    private String value;
    @Override
    public void caseAAddExpr(AAddExpr node) {
        node.getLeft().apply(this);
        System.out.println("Add: Left Val value : " + value + " type: " + valType);
        if (valType != Constants.INT) {
            System.err.println("Cannot add non-integer values");
            System.exit(-1);
        }
        node.getRight().apply(this);
        System.out.println("Add: Right Val value : " + value + " type: " + valType);
        if (valType != Constants.INT) {
            System.err.println("Cannot add non-integer values");
            System.exit(-1);
        }
    }

    @Override
    public void inASubExpr(ASubExpr node) {
        node.getLeft().apply(this);
        System.out.println("Sub: Left Val : " + name + " type: " + valType);
        if (valType != Constants.INT) {
            System.err.println("Cannot subtract non-integer values");
            System.exit(-1);
        }
        node.getRight().apply(this);
        System.out.println("Sub: Right Val : " + name + " type: " + valType);
        if (valType != Constants.INT) {
            System.err.println("Cannot subtract non-integer values");
            System.exit(-1);
        }
    }

    @Override
    public void inAMultExpr(AMultExpr node) {
        node.getLeft().apply(this);
        node.getRight().apply(this);
    }

    @Override
    public void inADivExpr(ADivExpr node) {
        println("Division expression: ");
        println("{");
        tabs++;
        println("Left: " + node.getLeft());
        println("Right: " + node.getRight());
        tabs--;
        println("}");
    }

    @Override
    public void inAModExpr(AModExpr node) {
        println("Modulus expression: ");
        println("{");
        tabs++;
        println("Left: " + node.getLeft());
        println("Right: " + node.getRight());
        tabs--;
        println("}");
    }

    @Override
    public void inASignedExprExpr(ASignedExprExpr node) {
        println("Signed expression: ");
        println("{");
        tabs++;
        println("Sign: " + node.getSign());
        println("Expression: " + node.getExpr());
        tabs--;
        println("}");
    }

    @Override
    public void inAParExprExpr(AParExprExpr node) {
        println("Parenthesised expression: ");
        println("{");
        tabs++;
    }

    @Override
    public void outAParExprExpr(AParExprExpr node) {
        tabs--;
        println("}");
    }

    @Override
    public void caseACharCExpr(ACharCExpr node) {
        valType = Constants.CHAR;
        value = node.getCharConst().toString();
    }

    @Override
    public void caseANumberExpr(ANumberExpr node) {
        valType = Constants.INT;
        value = node.getNumber().toString().replaceAll("\\s+", "");
    }

    @Override
    public void inASignedIdExpr(ASignedIdExpr node) {

    }

    @Override
    public void caseAStrCLVal(AStrCLVal node) {
        name = node.getStringConst().toString();
        valType = Constants.CHAR_ARR;
    }


    private TableEntry ent;
    private boolean fromOffs;
    private boolean fromAss;
    @Override
    public void caseAIdLVal(AIdLVal node) {
        name = node.getId().toString().replaceAll("\\s+", "");
        TableEntry entry = symbolTable.getVariable(name);

        if (entry == null) {
            System.err.println("Undeclared variable " + name);
            System.exit(-1);
        }

        if (fromOffs || fromAss) {
            // The work will be done by caseAOffsLVal
            ent = entry;
            return;
        }

        try {
            // We need this to get the value later on
            ScalarEntry sEntry = (ScalarEntry) entry;
            valType = sEntry.getType();
            value = sEntry.getValue();
        } catch(ClassCastException e) {
            System.err.println("Variable " + name + " was declared as an array");
            System.exit(-1);
        }
    }


    private int offs = -1;

    @Override
    public void caseAOffsLVal(AOffsLVal node) {
        System.out.println("Got in a left value with offset");
        fromOffs = true;
        node.getLVal().apply(this);
        fromOffs = false;
        node.getExpr().apply(this);
        int index;
        index = Integer.parseInt(value);

        offs = index;

        if (fromAss) {
            return;
        }

        ArrayEntry entry = null;
        try {
            entry = (ArrayEntry) ent;
        } catch (ClassCastException e) {
            System.err.println("Variable " + name + " was declared scalar, but is used as an array");
            System.exit(-1);
        }
        if (entry.getSize() <= index) {
            System.err.println("Offset " + index + " is out of array bounds " +
                    "(size: " + entry.getSize() + ")");
            System.exit(-1);
        }

        value = entry.getValue(index);
        if (entry.getType() == Constants.INT_ARR)
            valType = Constants.INT;
        else if (entry.getType() == Constants.CHAR_ARR)
            valType = Constants.CHAR;

        // We should check whether the
    }

    @Override
    public void caseALValAssStatement(ALValAssStatement node) {
        fromAss = true;
        node.getLVal().apply(this);
        fromAss = false;
        // At this point we have a TableEntry object available

        try {
            ScalarEntry entry = (ScalarEntry) ent;
            node.getExpr().apply(this);
            // We get <value> from the expression

            if (!symbolTable.setValue(entry.getName(), value)) {
                System.err.println("Unknown error occurred while setting value");
                System.exit(-1);
            }
            entry = (ScalarEntry) symbolTable.getVariable(entry.getName());
            System.out.println("Variable " + entry.getName() + " now has a value of "
                    + entry.getValue());
        } catch (ClassCastException oe) {
            try {
                ArrayEntry entry = (ArrayEntry) ent;
                // Since this is an array, we have an offset
                System.out.println("Assignment offset is: " + offs);
                node.getExpr().apply(this);
                if (!symbolTable.setValue(entry.getName(), offs, value)) {
                    System.err.println("Something went wrong");
                    System.exit(-1);
                }

                entry = (ArrayEntry) symbolTable.getVariable(entry.getName());
                System.out.println("Variable " + entry.getName() + ", index " + offs + " now has a value of "
                        + entry.getValue(offs));
            } catch (ClassCastException ie) {
                ie.printStackTrace();
                System.err.println("Unknown instance of TableEntry class");
                System.exit(-1);
            }
        }
    }

    @Override
    public void inABlockStatement(ABlockStatement node) {
        println("Block statement: ");
        println("{");
        tabs++;
    }

    @Override
    public void outABlockStatement(ABlockStatement node) {
        tabs--;
        println("}");
    }

    @Override
    public void inARetStStatement(ARetStStatement node) {
        println("Return statement: ");
        println("Expression: " + node.getExpr());
    }

    @Override
    public void inAIfNoElseStatement(AIfNoElseStatement node) {
        println("No else if: ");
        println("{");
        tabs++;
        println("Condition: " + node.getCond());
    }

    @Override
    public void caseAFuncCallStatement(AFuncCallStatement node) {
        String funcName = node.getId().toString();
        funcName = funcName.replaceAll("\\s+", "");
        FunctionEntry entry = (FunctionEntry) symbolTable.getVariable(funcName);

        if (entry == null) {
            System.err.println("Function : " + node.getId().toString() + " was not declared in this scope");
            System.exit(-1);
        } else {
            if (entry.getParamCount() > node.getExpr().size()) {
                System.err.println("Too few arguments to function " + funcName
                            + "\nExpecting " + entry.getParamCount() + " got " + node.getExpr().size());
                System.exit(-1);
            } else if (entry.getParamCount() < node.getExpr().size()) {
                System.err.println("Too many arguments to function " + funcName
                            + "\nExpecting " + entry.getParamCount() + " got " + node.getExpr().size());
                System.exit(-1);
            }

        }
        println("Function call: ");
        println("{");
        tabs++;
        println("Function name: " + node.getId());
        for (PExpr expr : node.getExpr()) {
            System.out.print(expr.toString());
            System.out.print(", ");
        }
        println("");
        tabs--;
        println("}");
    }

    @Override
    public void outAIfNoElseStatement(AIfNoElseStatement node) {
        tabs--;
        println("}");
    }

    @Override
    public void inAIfElseStatement(AIfElseStatement node) {
        println("If - else: ");
        println("{");
        tabs++;
        println("Condition: " + node.getCond());
        println("If statements: " + node.getIf());
        println("If else statements: " + node.getElse());

    }

    @Override
    public void outAIfElseStatement(AIfElseStatement node) {
        tabs--;
        println("}");
    }

    @Override
    public void inAIdLVal(AIdLVal node) {
        println("ID: " + node.getId());
    }

    @Override
    public void inAOffsLVal(AOffsLVal node) {
        println("Left value: " + node.getLVal());
        println("Offset: " + node.getExpr());
    }

    @Override
    public void inAStrCLVal(AStrCLVal node) {
        println("String constant: " + node.getStringConst());
    }

    @Override
    public void inAWhileStStatement(AWhileStStatement node) {
        println("While statement: ");
        println("{");
        tabs++;
        println("Condition: " + node.getCond());
        println("Statement: ");
    }

    @Override
    public void outAWhileStStatement(AWhileStStatement node) {
        tabs--;
        println("}");
    }

    @Override
    public void inAOrCond(AOrCond node) {
        println("Or condition: ");
        println("{");
        tabs++;
        println("Left: " + node.getLeft());
        println("Right: " + node.getRight());
        tabs--;
        println("}");
    }

    @Override
    public void inAAndCond(AAndCond node) {
        println("And condition: ");
        println("{");
        tabs++;
        println("Left: " + node.getLeft());
        println("Right: " + node.getRight());
        tabs--;
        println("}");
    }

    @Override
    public void inANotCond(ANotCond node) {
        println("Not condition: ");
        println("{");
        tabs++;
        println("Condition: " + node.getOperand());
        tabs--;
        println("}");
    }

    @Override
    public void inACompCond(ACompCond node) {
        println("Expression comparison: ");
        println("{");
        tabs++;
        println("Left: " + node.getLeft());
        println("Operator: " + node.getCompOperator());
        println("Right: " + node.getRight());
        tabs--;
        println("}");
    }


}