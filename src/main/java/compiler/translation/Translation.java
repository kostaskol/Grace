package compiler.translation;


import compiler.etc.Constants;
import compiler.semanticAnalysis.*;
import compiler.semanticAnalysis.tableEntries.ArrayEntry;
import compiler.semanticAnalysis.tableEntries.FunctionEntry;
import compiler.semanticAnalysis.tableEntries.ScalarEntry;
import compiler.semanticAnalysis.tableEntries.TableEntry;
import graceLang.analysis.DepthFirstAdapter;
import graceLang.node.*;
import compiler.etc.Log;

import java.util.ArrayList;

public class Translation extends DepthFirstAdapter {

    private SymbolTable symbolTable;

    private String getPos(Token t) {
        return "E: At line #" + t.getLine();
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

    private String getType(int type) {
        if (type == Constants.INT)      return "int";
        if (type == Constants.CHAR)     return "char";
        if (type == Constants.CHAR_ARR) return "char[]";
        if (type == Constants.INT_ARR)  return "int[]";
        if (type == Constants.NOTHING)  return "nothing";
                                        return "Unknown type?";
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

    private void eUndefined(Token t) {
        Log.e(getPos(t), t.getText() +
                " was not declared within the " +
                "current scope");
        System.exit(-1);
    }

    private void eDeclared(Token t) {
        Log.e(getPos(t),t.getText() +
                " was already declared within this scope");
        System.exit(-1);
    }

    @Override
    public void caseAProgram(AProgram node) {
        symbolTable = new SymbolTable();
        symbolTable.enterScope();
        ArrayList<TableEntry> entries = getBuiltIn();
        for (TableEntry entry : entries) {
            symbolTable.addEntry(entry);
        }

        node.getFuncDef().apply(this);
    }

    private ArrayList<ArrayList<TId>> params;
    private ArrayList<Integer> paramType;
    private ArrayList<Boolean> byRef;
    private TId funcName;
    private int funcType;

    @Override
    public void caseAFuncDef(AFuncDef node) {
        node.getHead().apply(this);
        // At this point, we can declare the function definition in the symbol table
        TableEntry entry = new FunctionEntry(funcName.toString().replaceAll("\\s+", ""),
                funcType, params, paramType, byRef, true);

        if (!symbolTable.addEntry(entry)) {
            Log.e(funcName.getLine() + "",
                    "Function " + funcName + " already exists within this scope");
            System.exit(-1);
        }

        symbolTable.enterScope();

        symbolTable.setScopeType(entry.getType());

        for (int i = 0; i < params.size(); i++) {
            if (paramType.get(i) == Constants.INT_ARR || paramType.get(i) == Constants.CHAR_ARR) {
                for (TId id : params.get(i)) {
                    ArrayList<String> tmp = new ArrayList<>();
                    tmp.add("0");
                    entry = new ArrayEntry(id.getText(), paramType.get(i), tmp);
                    //Log.d("FuncDef", "Adding " + id.)
                    if (!symbolTable.addEntry(entry)) {
                        Log.e(getPos(funcName),
                                "Variable " + funcName + "already exists within this scope");
                        System.exit(-1);
                    }
                }
            } else {
                for (TId id : params.get(i)) {
                    entry = new ScalarEntry(id.getText(), paramType.get(i), byRef.get(i));
                    if (!symbolTable.addEntry(entry)) {
                        Log.e(getPos(funcName),
                                "Variable " + funcName + "already exists within this scope");
                        System.exit(-1);
                    }
                }
            }
        }


        for (PLocalDef def : node.getLocalDef()) {
            def.apply(this);
        }
        for (PStatement st : node.getStatement()) {
            st.apply(this);
        }

        String name = symbolTable.exitScope();
        if (name != null) {
            Log.e("Function Undefined", "Function " + name + " was never defined");
            System.exit(-1);
        }


    }

    @Override
    public void caseAFuncDecLocalDef(AFuncDecLocalDef node) {
        node.getHead().apply(this);

        FunctionEntry entry = new FunctionEntry(funcName.toString().replaceAll("\\s+", ""),
                funcType, params, paramType, byRef, false);

        if (!symbolTable.addEntry(entry)) {
            Log.e(getPos(funcName),
                    "Function name " + funcName + " was already declared within current scope");
            System.exit(-1);
        }

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

        funcName = node.getFuncName();
    }



    @Override
    public void caseAPDec(APDec node) {
        // Add the ids
        params.add(new ArrayList<>(node.getId()));

        // Add byRefs
        byRef.add(node.getRef() != null);

        // Add type
        for (int i = 0; i < node.getId().size(); i++) {
            paramType.add(getType(node.getParType()));
        }

        // Value passed by ref
        if (node.getRef() == null &&
                (getType(node.getParType()) == Constants.INT_ARR
                || (getType(node.getParType()) == Constants.CHAR_ARR))) {
            Log.e(getPos(node.getId().get(0)), "Array parameter(s) "
                    + " must be passed by reference");
            System.exit(-1);
        }
    }

    @Override
    public void caseAVarDecLocalDef(AVarDecLocalDef node) {
        // Here, we can construct all of the
        //Log.d("Var Dec", "Adding " + node.getId() + " as " + node.getDType());
        int size;
        TableEntry entry;
        for (TId id : node.getId()) {
            String name = id.toString().replace("\\s+", "");
            String type = node.getDType().getText();
            if (node.getExpr().size() == 0) {
                if (type.equals("char")) {
                    entry = new ScalarEntry(name, Constants.CHAR, false);
                } else {
                    entry = new ScalarEntry(name, Constants.INT, false);
                }
            } else {
                ArrayList<String> dimens = new ArrayList<>();
                for (int i = 0; i < node.getExpr().size(); i++) {
                    node.getExpr().get(i).apply(this);
                    dimens.add(value);
                }
                String expr = node.getExpr().toString().replaceAll("\\s+", "");
                size = 256;
                try {
                    size = Integer.parseInt(expr);
                } catch(NumberFormatException e) {
                }
                if (type.equals("char")) {
                    entry = new ArrayEntry(name, Constants.CHAR_ARR, dimens);
                } else {
                    entry = new ArrayEntry(name, Constants.INT_ARR, dimens);
                }
            }

            if (!symbolTable.addEntry(entry)) {
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
        if (valType != Constants.INT) {
            Log.e(getPos(token), "Cannot add non-integer values");
            System.exit(-1);
        }
        String lVal = value;
        node.getRight().apply(this);
        if (valType != Constants.INT) {
            Log.e(getPos(token), "Cannot add non-integer values");
            System.exit(-1);
        }
        try {
            value = String.valueOf(Integer.parseInt(lVal) + Integer.parseInt(value));
        } catch(NumberFormatException e) {

        }
    }

    @Override
    public void caseASubExpr(ASubExpr node) {
        node.getLeft().apply(this);
        String lVal = value;
        if (valType != Constants.INT) {
            Log.e(getPos(token), "Cannot subtract non-integer values");
            System.exit(-1);
        }

        node.getRight().apply(this);
        if (valType != Constants.INT) {
            Log.e(getPos(token), "Cannot subtract non-integer values");
            System.exit(-1);
        }

        try {
            value = String.valueOf(Integer.parseInt(lVal) - Integer.parseInt(value));
        } catch(NumberFormatException e) {
            ;
        }
    }

    @Override
    public void caseAMultExpr(AMultExpr node) {
        node.getLeft().apply(this);
        String lVal = value;
        if (valType != Constants.INT) {
            Log.e(getPos(token), "Cannot multiply non-integer values");
            System.exit(-1);
        }

        node.getRight().apply(this);
        if (valType != Constants.INT) {
            Log.e(getPos(token), "Cannot multiply non-integer values");
            System.exit(-1);
        }

        try {
            value = String.valueOf(Integer.parseInt(lVal) * Integer.parseInt(value));
        } catch(NumberFormatException e) {
        }
    }

    @Override
    public void caseADivExpr(ADivExpr node) {
        System.out.println("Entered a division");
        node.getLeft().apply(this);
        String lVal = value;
        if (valType != Constants.INT) {
            Log.e(getPos(token), "Cannot divide non-integer values");
            System.exit(-1);
        }

        node.getRight().apply(this);
        if (valType != Constants.INT) {
            Log.e(getPos(token), "Cannot divide non-integer values");
            System.exit(-1);
        }

        try {
            if (Integer.parseInt(value) == 0) {
                Log.e(getPos(token), "Divide by zero");
                System.exit(-1);
            }
        } catch(NumberFormatException e) {
        }

        try {
            value = "2";
            try {
                value = String.valueOf(Integer.parseInt(lVal) / Integer.parseInt(value));
            } catch(NumberFormatException e) {
            }
        } catch(NumberFormatException e) {
        }
    }

    @Override
    public void caseAModExpr(AModExpr node) {
        node.getLeft().apply(this);
        if (valType != Constants.INT) {
            Log.e(getPos(token), "Cannot find modulus of non-integer values");
            System.exit(-1);
        }

        String lVal = value;

        node.getRight().apply(this);
        if (valType != Constants.INT) {
            Log.e(getPos(token), "Cannot find modulus of non-integer values");
            System.exit(-1);
        }

        try {
            if (Integer.parseInt(value) == 0) {
                Log.e(getPos(token), "Divide by zero");
                System.exit(-1);
            }
        } catch(NumberFormatException e) {
        }

        try {
            value = String.valueOf(Integer.parseInt(lVal) % Integer.parseInt(value));
        } catch(NumberFormatException e) {
        }
    }

    @Override
    public void caseASepStatement(ASepStatement node) {
        super.caseASepStatement(node);
    }

    @Override
    public void caseABlockStatement(ABlockStatement node) {
        super.caseABlockStatement(node);
    }

    @Override
    public void caseAIfNoElseStatement(AIfNoElseStatement node) {
        super.caseAIfNoElseStatement(node);
    }

    @Override
    public void caseAIfElseStatement(AIfElseStatement node) {
        super.caseAIfElseStatement(node);
    }

    @Override
    public void caseARetStStatement(ARetStStatement node) {
        node.getExpr().apply(this);
        int scopeType = symbolTable.getCurrScopeType();
        if (scopeType != valType) {
            Log.e(getPos(node.getReturn()), "Bad return type. Expecting "
                    + getType(scopeType)
                    + ", got " + getType(valType));
            System.exit(-1);
        }
    }

    @Override
    public void caseAWhileStStatement(AWhileStStatement node) {
        super.caseAWhileStStatement(node);
    }

    @Override
    public void caseAOrCond(AOrCond node) {
        super.caseAOrCond(node);
    }

    @Override
    public void caseAAndCond(AAndCond node) {
        super.caseAAndCond(node);
    }

    @Override
    public void caseANotCond(ANotCond node) {
        super.caseANotCond(node);
    }

    @Override
    public void caseACompCond(ACompCond node) {
        super.caseACompCond(node);
    }

    @Override
    public void caseASignedExprExpr(ASignedExprExpr node) {
        String sign = node.getSign().toString();
        sign = sign.replaceAll("\\s+", "");
        node.getExpr().apply(this);

        if (!value.contains("-")) {
            value = "-" + value;
        } else {
            value = value.replace("-", "");
        }
    }

    @Override
    public void caseACharCExpr(ACharCExpr node) {
        lVal = false;
        name = node.getCharConst().getText();
        valType = Constants.CHAR;
        value = node.getCharConst().toString();
    }

    @Override
    public void caseANumberExpr(ANumberExpr node) {
        lVal = false;
        valType = Constants.INT;
        value = node.getNumber().toString().replaceAll("\\s+", "");
    }

    @Override
    public void caseASignedIdExpr(ASignedIdExpr node) {
        lVal = true;
        TableEntry entry = symbolTable.getEntry(node.getId().getText());
        if (entry == null) {
            eUndefined(node.getId());
        }

        if (entry.getEntryType() != Constants.TYPE_SCAL) {
            Log.e(getPos(node.getId()), node.getId() + " was declared as array");
            System.exit(-1);
        }

        ScalarEntry sEntry = (ScalarEntry) entry;

        if (sEntry.getValue().contains("-")) {
            value = sEntry.getValue().replace("-", "");
        } else {
            value = "-" + sEntry.getValue();
        }
        valType = sEntry.getType();
    }

    @Override
    public void caseAStrCLVal(AStrCLVal node) {
        lVal = false;
        name = node.getStringConst().toString();
        valType = Constants.CHAR_ARR;
        value = node.getStringConst().getText();
    }


    private TableEntry ent;
    private boolean fromAss;

    ArrayList<String> dimenVec;
    @Override
    public void caseAIdLVal(AIdLVal node) {
        lVal = true;
        if (node.getExpr().size() == 0) {
            // This is a scalar variable
            offs = 0;
            token = node.getId();
            name = node.getId().toString().replaceAll("\\s+", "");
            TableEntry entry = symbolTable.getEntry(name);

            if (entry == null) {
                System.err.println("Undeclared variable " + name);
                System.exit(-1);
            }


            if (fromAss) {
                // The work will be done by caseAOffsLVal

                ent = entry;
                return;
            }


            switch (entry.getEntryType()) {
                case Constants.TYPE_ARR:
                    ArrayEntry aEntry = (ArrayEntry) entry;
                    valType = aEntry.getType();
                    value = "256";
                    break;
                case Constants.TYPE_SCAL:
                    ScalarEntry sEntry = (ScalarEntry) entry;
                    valType = sEntry.getType();
                    value = "256";
                    break;
                default:
                    Log.e("IdLVal", "Defaulted on lVal type");
            }
        } else {
            // This is an array variable
            token = node.getId();
            String name = node.getId().getText();
            TableEntry entry = symbolTable.getEntry(name);

            if (entry == null) {
                eUndefined(node.getId());
            }

            if (entry.getEntryType() != Constants.TYPE_ARR) {
                Log.e(getPos(node.getId()), "Variable " + name + " was declared scalar " +
                        "but used as array");
                System.exit(-1);
            }

            ArrayEntry aEntry = (ArrayEntry) entry;
            if (node.getExpr().size() != aEntry.getDimensions()) {
                Log.e(getPos(node.getId()), "Variable " + node.getId()
                        + " was declared with " + aEntry.getDimensions()
                        + " dimensions, but was used with " + node.getExpr().size());
                System.exit(-1);
            }

            for (PExpr expr : node.getExpr()) {
                expr.apply(this);
                dimenVec.add(value);
            }

            if (fromAss) {
                ent = entry;
                return;
            }

            // TODO: Re-enable this to check for indexing
            /*
            if (aEntry.getSize() != 0 && aEntry.getSize() <= index) {
                Log.e(getPos(node.getId()), "Offset " + index + " is out of array bounds " +
                        "(size: " + aEntry.getSize() + ")");
                System.exit(-1);
            }
            */

            if (aEntry.getType() == Constants.CHAR_ARR) {
                valType = Constants.CHAR;
            } else {
                valType = Constants.INT;
            }

            value = aEntry.getValue(dimenVec);
        }
    }

    private int offs = 0;
    private TId token;
    private boolean lVal = false;

    @Override
    public void caseALValAssStatement(ALValAssStatement node) {
        fromAss = true;
        dimenVec = new ArrayList<>();
        node.getLVal().apply(this);
        Token t = token;

        fromAss = false;
        // At this point we have a TableEntry object available

        if (ent.getEntryType() == Constants.TYPE_ARR && dimenVec.size() == 0) {
            Log.e(getPos(token), "Cannot assign value to array");
            System.exit(-1);
        }

        if (ent.getEntryType() == Constants.TYPE_ARR) {
            ArrayEntry entry = (ArrayEntry) ent;
            // Since this is an array, we have an offset
            node.getExpr().apply(this);
            try {
                if (!symbolTable.setValue(entry.getName(), dimenVec, value)) {
                    Log.e("Array Value setting", "Something went wrong");
                    System.exit(-1);
                }
            } catch (Exception e) {
                Log.e(getPos(token), "Cannot set value");
            }

            //entry = (ArrayEntry) symbolTable.getEntry(entry.getName());
            if (ent.getType() == Constants.INT_ARR) {
                if (valType != Constants.INT) {
                    Log.e(getPos(t), "Cannot assign char value to int array");
                    System.exit(-1);
                }
            } else if (ent.getType() == Constants.CHAR_ARR) {
                if (valType != Constants.CHAR) {
                    Log.e(getPos(t), "Cannot assign int value to char array");
                    System.exit(-1);
                }
            }
        } else if (ent.getEntryType() == Constants.TYPE_SCAL) {
            ScalarEntry entry = (ScalarEntry) ent;
            funcCall = true;
            node.getExpr().apply(this);
            // We get <value> from the expression
            funcCall = false;
            if (entry.getType() != valType) {
                Log.e(getPos(token), "Variable " + t.getText() + " cannot be " +
                        "assigned value " + value + ". Type mismatch (expecting "
                        + getType(entry.getType()) + ", got " + getType(valType) + ")");
                System.exit(-1);
            }

            if (!symbolTable.setValue(entry.getName(), value)) {
                System.err.println("Unknown error occurred while setting value");
                System.exit(-1);
            }
            entry = (ScalarEntry) symbolTable.getEntry(entry.getName());
        } else {
            Log.e(getPos(token), "Cannot assign value to function");
            System.exit(-1);
        }
    }

    @Override
    public void caseAFuncCallExpr(AFuncCallExpr node) {

        token = node.getId();
        FunctionEntry entry = (FunctionEntry) symbolTable.getEntry(node.getId().getText());
        if (entry == null) {
            eUndefined(token);
        }

        if (entry.getParamCount() > node.getExpr().size()) {
            System.err.println("Too few arguments to function " + funcName
                    + "\nExpecting " + entry.getParamCount() + " got " + node.getExpr().size());
            System.exit(-1);
        } else if (entry.getParamCount() < node.getExpr().size()) {
            System.err.println("Too many arguments to function " + funcName
                    + "\nExpecting " + entry.getParamCount() + " got " + node.getExpr().size());
            System.exit(-1);
        }

        funcCall = true;
        for (int i = 0; i < node.getExpr().size(); i++) {
            node.getExpr().get(i).apply(this);
            if (valType != entry.getParamTypeAt(i)) {
                Log.e(getPos(node.getId()), "Parameter mismatch at argument " + (i + 1)
                        + " for function " + node.getId().getText() + ". Expecting "
                        + getType(entry.getParamTypeAt(i)) + " got " + getType(valType));
            }

            if (entry.byRef(i) && !lVal) {
                Log.e(getPos(node.getId()), "Cannot pass right value by ref "
                        + " for argument " + i + " for function " + node.getId());
                System.exit(-1);
            }
        }
        funcCall = false;

        valType = entry.getType();
        name = entry.getName();
        switch(entry.getType()) {
            case Constants.CHAR:
                value = "s";
                break;
            case Constants.CHAR_ARR:
                value = "some value";
                break;
            case Constants.INT:
                value = "512";
                break;
            case Constants.INT_ARR:
                value = "TODO this";
                break;
            default:
                value = null;
        }

    }

    @Override
    public void caseAStrOffsLVal(AStrOffsLVal node) {
        String str = node.getStringConst().toString();
        node.getExpr().apply(this);
        int offset = 0;
        try {
            offset = Integer.parseInt(value);
        } catch (NumberFormatException e) {
        }
        if (offset >= str.length()) {
            Log.e(getPos(node.getStringConst()), "Requested offset is out of bounds. " +
                    "(Offset: " + offset + ", Length: " + str.length() + ")");
            System.exit(-1);
        }
        valType = Constants.CHAR;
        value = str.charAt(offset) + "";
    }

    private boolean funcCall;

    @Override
    public void caseAFuncCallStatement(AFuncCallStatement node) {
        String funcName = node.getId().toString();
        funcName = funcName.replaceAll("\\s+", "");
        FunctionEntry entry = (FunctionEntry) symbolTable.getEntry(funcName);

        if (entry == null) {
            eUndefined(node.getId());
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
        funcCall = true;
        for (int i = 0; i < node.getExpr().size(); i++) {
            node.getExpr().get(i).apply(this);
            if (valType != entry.getParamTypeAt(i)) {
                Log.e(getPos(node.getId()), "Parameter mismatch at argument " + (i + 1)
                        + " for function " + node.getId().getText() + ". Expecting "
                        + getType(entry.getParamTypeAt(i)) + " got " + getType(valType));
            }

            if (entry.byRef(i) && !lVal) {
                Log.e(getPos(node.getId()), "Cannot pass right value by ref "
                        + " for argument " + i + " for function " + node.getId());
                System.exit(-1);
            }
        }
        funcCall = false;
    }

}