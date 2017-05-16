package compiler.translation;


import compiler.etc.Constants;
import compiler.intermediateCode.Intermediate;
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
    private Intermediate iCode;

    // The following 9 functions are utility functions
    private String getPos(Token t) {
        return "E: At line #" + t.getLine();
    }

    // These two could be replaced by the normal "String" one
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

    private int getType(String parType) {
        parType = parType.replaceAll("\\s+", "");
        if (parType.equals("nothing"))          return Constants.NOTHING;
        if (parType.equals("int"))              return Constants.INT;
        if (parType.equals("char"))             return Constants.CHAR;
        if (parType.equals("char[]"))           return Constants.CHAR_ARR;
        if (parType.equals("int[]"))            return Constants.INT_ARR;
                                                return Constants.TYPE_UNKNOWN;
    }

    private int getEntryType(String parType) {
        switch (getType(parType)) {
            case Constants.INT_ARR:
            case Constants.CHAR_ARR:
                return Constants.TYPE_ARR;
            case Constants.INT:
            case Constants.CHAR:
                return Constants.TYPE_SCAL;
            default:
                return Constants.TYPE_FUNC;
        }
    }

    public static String getType(int type) {
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

    // Returns an ArrayList of all of the built-in functions
    private ArrayList<TableEntry> getBuiltIn() {
        ArrayList<TableEntry> entries = new ArrayList<>();
        TableEntry entry;

        ArrayList<ArrayList<TId>> params;
        ArrayList<Integer> paramType;
        ArrayList<Boolean> byRef;
        ArrayList<TId> tmp;
        ArrayList<Integer> dimenTmp;
        ArrayList<ArrayList<Integer>> paramDimens;

        // puti(n : int) : nothing
        String name = "puti";
        params = new ArrayList<>();
        paramType = new ArrayList<>();
        byRef = new ArrayList<>();
        tmp = new ArrayList<>();
        paramDimens = new ArrayList<>();
        dimenTmp = new ArrayList<>();
        dimenTmp.add(-1);
        paramDimens.add(dimenTmp);

        tmp.add(new TId("n"));
        params.add(tmp);
        paramType.add(Constants.INT);
        byRef.add(false);
        entry = new FunctionEntry(name, Constants.NOTHING, params, paramType,
                byRef, paramDimens, true, 0);

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
        entry = new FunctionEntry(name, Constants.NOTHING, params, paramType,
                byRef, paramDimens, true, 0);

        entries.add(entry);


        // fun puts puts(ref s : char[]) : nothing
        name = "puts";
        params = new ArrayList<>();
        paramType = new ArrayList<>();
        byRef = new ArrayList<>();
        tmp = new ArrayList<>();
        paramDimens = new ArrayList<>();
        dimenTmp = new ArrayList<>();

        dimenTmp.add(0);
        paramDimens.add(dimenTmp);

        tmp.add(new TId("s"));
        params.add(tmp);
        paramType.add(Constants.CHAR_ARR);
        byRef.add(true);
        entry = new FunctionEntry(name, Constants.NOTHING, params, paramType,
                byRef, paramDimens, true, 0);

        entries.add(entry);

        // fun geti () : int
        name = "geti";
        params = new ArrayList<>();
        paramType = new ArrayList<>();
        byRef = new ArrayList<>();
        paramDimens = new ArrayList<>();
        dimenTmp = new ArrayList<>();
        dimenTmp.add(-1);
        paramDimens.add(dimenTmp);

        entry = new FunctionEntry(name, Constants.INT, params, paramType,
                byRef, paramDimens, true, 0);

        entries.add(entry);

        // fun getc () : char
        name = "getc";
        params = new ArrayList<>();
        paramType = new ArrayList<>();
        byRef = new ArrayList<>();

        entry = new FunctionEntry(name, Constants.CHAR, params, paramType,
                byRef, paramDimens, true, 0);

        entries.add(entry);

        // fun gets (n : int, ref s : char[]) : nothing
        name = "gets";
        params = new ArrayList<>();
        paramType = new ArrayList<>();
        byRef = new ArrayList<>();
        tmp = new ArrayList<>();
        paramDimens = new ArrayList<>();
        dimenTmp = new ArrayList<>();

        tmp.add(new TId("n"));
        params.add(tmp);
        paramType.add(Constants.INT);
        byRef.add(false);
        dimenTmp.add(-1);
        paramDimens.add(dimenTmp);

        tmp = new ArrayList<>();
        dimenTmp = new ArrayList<>();
        tmp.add(new TId("s"));
        dimenTmp.add(0);
        params.add(tmp);
        paramType.add(Constants.CHAR_ARR);
        byRef.add(true);
        paramDimens.add(dimenTmp);

        entry = new FunctionEntry(name, Constants.NOTHING, params, paramType,
                byRef, paramDimens, true, 0);

        entries.add(entry);

        // fun abs(n : int) : int
        name = "abs";
        params = new ArrayList<>();
        paramType = new ArrayList<>();
        byRef = new ArrayList<>();
        tmp = new ArrayList<>();
        paramDimens = new ArrayList<>();
        dimenTmp = new ArrayList<>();

        tmp.add(new TId("n"));
        params.add(tmp);
        paramType.add(Constants.INT);
        byRef.add(false);
        dimenTmp.add(-1);
        paramDimens.add(dimenTmp);

        entry = new FunctionEntry(name, Constants.INT, params, paramType,
                byRef, paramDimens, true, 0);

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

        entry = new FunctionEntry(name, Constants.INT, params, paramType,
                byRef, paramDimens, true, 0);

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

        entry = new FunctionEntry(name, Constants.CHAR, params, paramType,
                byRef, paramDimens, true, 0);

        entries.add(entry);


        // fun strlen (ref s : char[]) : int
        name = "strlen";
        params = new ArrayList<>();
        paramType = new ArrayList<>();
        byRef = new ArrayList<>();
        tmp = new ArrayList<>();
        paramDimens = new ArrayList<>();
        dimenTmp = new ArrayList<>();

        tmp.add(new TId("s"));
        params.add(tmp);
        paramType.add(Constants.CHAR_ARR);
        byRef.add(true);
        dimenTmp.add(0);
        paramDimens.add(dimenTmp);

        entry = new FunctionEntry(name, Constants.INT, params, paramType,
                byRef, paramDimens, true, 0);

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

        entry = new FunctionEntry(name, Constants.INT, params, paramType,
                byRef, paramDimens, true, 0);

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

        entry = new FunctionEntry(name, Constants.NOTHING, params, paramType,
                byRef, paramDimens, true, 0);

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

        entry = new FunctionEntry(name, Constants.NOTHING, params, paramType,
                byRef, paramDimens, true, 0);

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

    private void exit(String context, String msg) {
        Log.e(context, msg);
        System.exit(-1);
    }

    @Override
    public void caseAProgram(AProgram node) {
        // Initialise the symbol table
        // and enter into the 0-th scope
        symbolTable = new SymbolTable();
        symbolTable.enterScope();
        ArrayList<TableEntry> entries = getBuiltIn();
        for (TableEntry entry : entries) {
            symbolTable.addEntry(entry);
        }

        // Enter into the 1st scope
        symbolTable.enterScope();

        iCode = new Intermediate();

        node.getFuncDef().apply(this);

        iCode.show();

    }

    // Each time we visit a parent node, we properly initialise
    // the following objects, which are filled in by the children as we visit them

    // Parameter names
    private ArrayList<ArrayList<TId>> params;
    // Parameter types
    private ArrayList<Integer> paramType;
    // True if parameter at index <i> is passed by ref
    private ArrayList<Boolean> byRef;
    // Array dimensions for parameter i
    private ArrayList<ArrayList<Integer>> paramDimens;
    // Function name
    private TId funcName;
    // Function type
    private int funcType;


    @Override
    public void caseAFuncDef(AFuncDef node) {
        // Function definition

        // Initialise the parameter lists
        params = new ArrayList<>(node.getPDec().size());
        paramType = new ArrayList<>();
        byRef = new ArrayList<>();
        paramDimens = new ArrayList<>();

        // After each iteration, the above lists will contain one more
        // element each
        for (PPDec dec : node.getPDec()) {
            // Iterate over the parameters
            dec.apply(this);
        }

        // We get the return type of the function
        funcType = getType(node.getRetType());

        // and its name
        funcName = node.getFuncName();


        // At this point, we can declare the function definition in the symbol table
        TableEntry entry = new FunctionEntry(funcName.getText(), funcType, params,
                paramType, byRef, paramDimens, true, symbolTable.getDepth());


        // Try to add the function entry into the symbol table.
        // if there is a redefinition error, it returns false
        if (!symbolTable.addEntry(entry)) {
            eDeclared(funcName);
        }

        symbolTable.enterScope();

        // Each scope has its own type (the function's return type.
        // Used when checking the return statement)
        // We also save the scope's name (used in un-nesting function definitions)
        symbolTable.setScopeData(entry.getName(), entry.getType());

        for (int i = 0; i < params.size(); i++) {
            // After getting the parameters (and
            // saving them in the symbol table as parameters)
            // we save each parameter in the symbol table
            if (paramType.get(i) == Constants.INT_ARR || paramType.get(i) == Constants.CHAR_ARR) {
                for (TId id : params.get(i)) {
                    ArrayList<String> tmp = new ArrayList<>();
                    tmp.add("0");
                    entry = new ArrayEntry(id.getText(), paramType.get(i), true, tmp);
                    if (!symbolTable.addEntry(entry)) {
                        exit(getPos(funcName),
                                "Variable " + funcName + "already exists within this scope");
                    }
                }
            } else {
                for (TId id : params.get(i)) {
                    entry = new ScalarEntry(id.getText(), paramType.get(i), true, byRef.get(i));
                    if (!symbolTable.addEntry(entry)) {
                        exit(getPos(funcName),
                                "Variable " + funcName + "already exists within this scope");
                    }
                }
            }
        }

        // Visit each local definition node
        // (function definition | function declaration | variable declaration)
        for (PLocalDef def : node.getLocalDef()) {
            def.apply(this);
        }

        iCode.genQuad(Constants.OP_UNIT, node.getFuncName().getText(), null, null);

        // Visit each statement node
        for (PStatement st : node.getStatement()) {
            st.apply(this);
        }


        String name = symbolTable.exitScope();
        // Check if all function "promises" have been fulfilled
        if (name != null) {
            exit("Function Undefined", "Function " + name + " was never defined");
        }

        iCode.genQuad(Constants.OP_ENDU, node.getFuncName().getText(), null, null);
    }

    @Override
    public void caseAFuncDecLocalDef(AFuncDecLocalDef node) {
        // Function declaration
        // Same as above, initialise the lists and fill them out
        params = new ArrayList<>(node.getPDec().size());
        paramType = new ArrayList<>();
        byRef = new ArrayList<>();
        paramDimens = new ArrayList<>();
        for (PPDec dec : node.getPDec()) {
            // Iterate over the parameters
            dec.apply(this);
        }


        funcType = getType(node.getRetType());

        funcName = node.getFuncName();

        FunctionEntry entry = new FunctionEntry(funcName.toString().replaceAll("\\s+", ""),
                funcType, params, paramType, byRef, paramDimens, false,
                symbolTable.getDepth());

        if (!symbolTable.addEntry(entry)) {
            eDeclared(funcName);
        }

    }


    @Override
    public void caseAPDec(APDec node) {
        // Add the ids
        params.add(new ArrayList<>(node.getId()));

        // Add byRefs
        byRef.add(node.getRef() != null);

        // The parameter type node will
        // fill out the type and dimensions array
        node.getParType().apply(this);

        // If value isn't passed by ref
        // and the type is array, it's an error
        if (node.getRef() == null &&
                (getEntryType(node.getParType().toString()) == Constants.TYPE_ARR)) {
            exit(getPos(node.getId().get(0)), "Array parameter(s) "
                    + " must be passed by reference");
        }
    }

    @Override
    public void caseAParType(AParType node) {
        ArrayList<Integer> dimenTmp = new ArrayList<>();
        if (node.getOcBrack() != null) {
            dimenTmp.add(0);
        }

        for (TNumber num : node.getNumber()) {
            dimenTmp.add(Integer.parseInt(num.getText()));
        }

        String type = node.getDType().getText();

        // If the ids have been declared as an
        // n-dimensional array, we hold that information in the symbol table
        if (node.getNumber().size() != 0 || node.getOcBrack() != null) {
            type += "[]";
        }

        paramType.add(getType(type));

        paramDimens.add(dimenTmp);
    }

    @Override
    public void caseAVarDecLocalDef(AVarDecLocalDef node) {
        // {var_dec} id* d_type expr*

        TableEntry entry;
        // Iterate over variable names and types and add them to
        // the symbol table
        for (TId id : node.getId()) {
            // Iterate over each id and create a TableEntry object
            String name = id.toString().replace("\\s+", "");
            String type = node.getDType().getText();
            if (getEntryType(node.getDType().getText()) != Constants.TYPE_ARR
                    && node.getExpr().size() == 0){
                if (type.equals("char")) {
                    entry = new ScalarEntry(name, Constants.CHAR, false, false);
                } else {
                    entry = new ScalarEntry(name, Constants.INT, false, false);
                }
            } else {
                ArrayList<String> dimens = new ArrayList<>();
                for (int i = 0; i < node.getExpr().size(); i++) {
                    node.getExpr().get(i).apply(this);
                    dimens.add(value);
                }

                if (type.equals("char")) {
                    entry = new ArrayEntry(name, Constants.CHAR_ARR, false, dimens);
                } else {
                    entry = new ArrayEntry(name, Constants.INT_ARR, false, dimens);
                }
            }

            if (!symbolTable.addEntry(entry)) {
                eDeclared(id);
            }
        }
    }


    private String name;
    private int valType;
    private String value;

    @Override
    public void caseAAddExpr(AAddExpr node) {
        value = null;
        node.getLeft().apply(this);
        if (valType != Constants.INT) {
            exit(getPos(token), "Cannot add non-integer values");
        }

        String leftVal = value;
        node.getRight().apply(this);
        if (valType != Constants.INT) {
            exit(getPos(token), "Cannot add non-integer values");
        }

        String newTmp = iCode.newTmp();
        iCode.genQuad(Constants.OP_ADD, leftVal, value, newTmp);
        value = newTmp;
    }


    @Override
    public void caseASubExpr(ASubExpr node) {
        value = null;

        node.getLeft().apply(this);
        if (valType != Constants.INT) {
            exit(getPos(token), "Cannot subtract non-integer values");
        }
        String leftVal = value;

        node.getRight().apply(this);
        if (valType != Constants.INT) {
            exit(getPos(token), "Cannot subtract non-integer values");
        }

        String newTmp = iCode.newTmp();
        iCode.genQuad(Constants.OP_SUB, leftVal, value, newTmp);
        value = newTmp;
    }


    @Override
    public void caseAMultExpr(AMultExpr node) {
        value = null;
        node.getLeft().apply(this);
        String leftVal = value;
        if (valType != Constants.INT) {
            exit(getPos(token), "Cannot multiply non-integer values");
        }

        node.getRight().apply(this);
        if (valType != Constants.INT) {
            exit(getPos(token), "Cannot multiply non-integer values");
        }

        String newTmp = iCode.newTmp();
        iCode.genQuad(Constants.OP_MULT, leftVal, value, newTmp);
        value = newTmp;
    }


    @Override
    public void caseADivExpr(ADivExpr node) {
        value = null;
        node.getLeft().apply(this);
        if (valType != Constants.INT) {
            exit(getPos(token), "Cannot divide non-integer values");
        }
        String leftVal = value;

        node.getRight().apply(this);
        if (valType != Constants.INT) {
            exit(getPos(token), "Cannot divide non-integer values");
        }

        String newTmp = iCode.newTmp();
        iCode.genQuad(Constants.OP_DIV, leftVal, value, newTmp);
        value = newTmp;
    }


    @Override
    public void caseAModExpr(AModExpr node) {
        node.getLeft().apply(this);
        if (valType != Constants.INT) {
            exit(getPos(token), "Cannot find modulus of non-integer values");
        }

        String leftVal = value;

        node.getRight().apply(this);
        if (valType != Constants.INT) {
            exit(getPos(token), "Cannot find modulus of non-integer values");
        }

        String newTmp = iCode.newTmp();
        iCode.genQuad(Constants.OP_MOD, leftVal, value, newTmp);
        value = newTmp;
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
        // Get the returned expression value and type
        // and query the symbol table to figure out
        // if the returned type is the same as the current scope's one
        node.getExpr().apply(this);
        int scopeType = symbolTable.getCurrScopeType();
        if (scopeType != valType) {
            exit(getPos(node.getReturn()), "Bad return type. Expecting "
                    + getType(scopeType)
                    + ", got " + getType(valType));
        }

//        String register = iCode.getRetRegister();
//        if (register == null) {
//            exit(getPos(node.getReturn()), "Could not find any return statements (why?)");
//        }

        // iCode.genQuad(Constants.OP_ASS, value, null, register);
        iCode.genQuad(Constants.OP_RET, null, null, value);
    }


    @Override
    public void caseAWhileStStatement(AWhileStStatement node) {
        super.caseAWhileStStatement(node);
    }


    private boolean isCond;
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
        // Get the value and the type of the left expression
        node.getLeft().apply(this);
        int leftType = valType;
        String leftValue = value;

        // And of the right one
        node.getRight().apply(this);

        // Make the necessary checks (type compatibility & array checking)
        if (leftType != valType) {
            exit(getPos(node.getCompOperator()), "Invalid comparison between " +
                    getType(leftType) + " and " + getType(valType));
        }

        if (leftType == Constants.INT_ARR || leftType == Constants.CHAR_ARR) {
            exit(getPos(node.getCompOperator()), "Invalid comparison (Array operand)");
        }

        Log.d(getPos(node.getCompOperator()), "Comparison between " + leftValue +
                " and " + value);
    }


    @Override
    public void caseASignedExprExpr(ASignedExprExpr node) {
        node.getExpr().apply(this);

        if (!value.contains("-")) {
            value = "-" + value;
        } else {
            value = value.replace("-", "");
        }
    }


    @Override
    public void caseACharCExpr(ACharCExpr node) {
        // Character constants are considered right values
        lVal = false;
        name = node.getCharConst().getText();
        valType = Constants.CHAR;
        value = node.getCharConst().getText();
    }


    @Override
    public void caseANumberExpr(ANumberExpr node) {
        // Number constants are considered right values
        lVal = false;
        valType = Constants.INT;
        value = node.getNumber().getText();
    }


    @Override
    public void caseASignedIdExpr(ASignedIdExpr node) {
        lVal = true;
        TableEntry entry = symbolTable.getEntry(node.getId().getText());
        if (entry == null) {
            eUndefined(node.getId());
        }

        if (entry.getEntryType() != Constants.TYPE_SCAL) {
            exit(getPos(node.getId()), node.getId() + " was declared as array");
        }

        ScalarEntry sEntry = (ScalarEntry) entry;

        if (sEntry.getValue().contains("-")) {
            value = sEntry.getValue().replace("-", "");
        } else {
            value = "-" + sEntry.getValue();
        }
        valType = sEntry.getType();
    }


    private boolean isString;

    @Override
    public void caseAStrCLVal(AStrCLVal node) {
        // String constants are considered left values
        // but can't be assigned a value
        lVal = true;
        isString = true;
        name = node.getStringConst().toString();
        valType = Constants.CHAR_ARR;
        value = node.getStringConst().getText();
    }


    // If we need the left value in an assignment statement
    // or a function call, <ent> will hold the entire TableEntry of the id
    private TableEntry ent = null;
    private boolean fromAss;
    // Holds the dimensions with which the left value was used (e.g. x[1][2][3]..[n])
    private ArrayList<String> dimenList;


    @Override
    public void caseAIdLVal(AIdLVal node) {
        lVal = true;
        if (node.getExpr().size() == 0) {
            // This is a scalar variable
            token = node.getId();
            name = node.getId().toString().replaceAll("\\s+", "");
            TableEntry entry = symbolTable.getEntry(name);

            if (entry == null) {
                eUndefined(node.getId());
            }


            if (fromAss || fromFunc) {
                // The work will be done by caseAOffsLVal
                // or caseAFuncCallExpr / caseAFuncCallStatement
                ent = entry;
                return;
            }

            switch (entry.getEntryType()) {
                case Constants.TYPE_ARR:
                    exit(getPos(node.getId()), "Array variable " + node.getId().getText() +
                        " used without offset");
                case Constants.TYPE_SCAL:
                    ScalarEntry sEntry = (ScalarEntry) entry;
                    valType = sEntry.getType();
                    value = sEntry.getName();
                    break;
                default:
                    Log.e("IdLVal", "Defaulted on lVal type");
            }
        } else {
            dimenList = new ArrayList<>();

            // This is an array variable
            token = node.getId();
            String name = node.getId().getText();
            TableEntry entry = symbolTable.getEntry(name);

            if (entry == null) {
                eUndefined(node.getId());
            }

            if (entry.getEntryType() != Constants.TYPE_ARR) {
                exit(getPos(node.getId()), "Variable " + name + " was declared scalar " +
                        "but used as array");
            }

            ArrayEntry aEntry = (ArrayEntry) entry;
            if (node.getExpr().size() != aEntry.getDimensionsSize()) {
                exit(getPos(node.getId()), "Variable " + node.getId()
                        + " was declared with " + aEntry.getDimensionsSize()
                        + " dimensions, but was used with " + node.getExpr().size());
            }

            for (PExpr expr : node.getExpr()) {
                expr.apply(this);
                dimenList.add(value);
            }

            if (fromAss || fromFunc) {
                ent = entry;
                return;
            }

            if (aEntry.getType() == Constants.CHAR_ARR) {
                valType = Constants.CHAR;
            } else {
                valType = Constants.INT;
            }

            String offset = aEntry.getLinearOffset(dimenList, iCode);
            value = iCode.newTmp();
            iCode.genQuad(Constants.OP_OFFS, aEntry.getName(), offset, "[" + value + "]");
        }
    }

    // Required to print the line on which the error occurred
    private TId token;
    private boolean lVal = false;


    @Override
    public void caseALValAssStatement(ALValAssStatement node) {
        fromAss = true;
        isString = false;
        node.getLVal().apply(this);

        Token t = token;

        if (isString) {
            exit("Bad assignment value", "Can't assign value to string constant");
        }

        fromAss = false;
        // At this point we have a TableEntry object available

        if (ent.getEntryType() == Constants.TYPE_ARR && dimenList.size() == 0) {
            exit(getPos(token), "Cannot assign value to array");
        }

        if (ent.getEntryType() == Constants.TYPE_ARR) {
            ArrayEntry entry = (ArrayEntry) ent;
            // Since this is an array, we have an offset
            node.getExpr().apply(this);
            try {
                if (dimenList.size() != entry.getDimensionsSize()) {
                    exit(getPos(token), "Array dimensions don't match");
                }
                // TODO: We can't just assign this. We need to get the
                // n-dimensional offset
                // The linear offset for an n-dimensional call is:
                // dimen1 * dimen1Max + dimen2 * dimen2Max + ... + dimenN-1 * dimenN-1Max + dimenN
                int off = 0;

                // We newTmp will hold the value of the offset
                String newTmp = iCode.newTmp();
                iCode.genQuad(Constants.OP_OFFS, ent.getName(), String.valueOf(off), newTmp);
                iCode.genQuad(Constants.OP_ASS, newTmp, null, value);

                /*if (!symbolTable.setValue(entry.getName(), dimenList, value)) {
                    exit("Array Value setting", "Something went wrong");
                }*/
            } catch (Exception e) {
                exit(getPos(token), "Cannot set value");
            }

            //entry = (ArrayEntry) symbolTable.getEntry(entry.getName());
            if (ent.getType() == Constants.INT_ARR) {
                if (valType != Constants.INT) {
                    exit(getPos(t), "Cannot assign char value to int array");
                }
            } else if (ent.getType() == Constants.CHAR_ARR) {
                if (valType != Constants.CHAR) {
                    exit(getPos(t), "Cannot assign int value to char array");
                }
            }
        } else if (ent.getEntryType() == Constants.TYPE_SCAL) {
            ScalarEntry entry = (ScalarEntry) ent;
            node.getExpr().apply(this);
            // We get <value> from the expression
            if (entry.getType() != valType) {
                exit(getPos(token), "Variable " + t.getText() + " cannot be " +
                        "assigned value " + value + ". Type mismatch (expecting "
                        + getType(entry.getType()) + ", got " + getType(valType) + ")");
            }

            if (!symbolTable.setValue(entry.getName(), value)) {
                exit(getPos(token), "Unknown error occurred while setting value");
            }
            iCode.genQuad(Constants.OP_ASS, entry.getName(), null, value);
        } else {
            exit(getPos(token), "Cannot assign value to function");
        }
    }

    private boolean fromFunc;

    @Override
    public void caseAFuncCallExpr(AFuncCallExpr node) {

        token = node.getId();
        FunctionEntry functionEntry = (FunctionEntry) symbolTable.getEntry(node.getId().getText());
        if (functionEntry == null) {
            eUndefined(token);
        }

        lVal = false;
        if (functionEntry.getParamCount() > node.getExpr().size()) {
            exit(getPos(node.getId()), "Too few arguments to function " + funcName
                    + "\nExpecting " + functionEntry.getParamCount() + " got " + node.getExpr().size());
        } else if (functionEntry.getParamCount() < node.getExpr().size()) {
            exit(getPos(node.getId()), "Too many arguments to function " + funcName
                    + "\nExpecting " + functionEntry.getParamCount() + " got " + node.getExpr().size());
        }

        fromFunc = true;
        for (int i = 0; i < node.getExpr().size(); i++) {
            ent = null;
            node.getExpr().get(i).apply(this);
            TableEntry entry = ent;
            int valueType;
            if (entry == null) {
                valueType = valType;
            } else {
                valueType = entry.getType();
            }
            if (valueType != functionEntry.getParamTypeAt(i)) {
                exit(getPos(node.getId()), "Parameter mismatch at argument " + (i + 1)
                        + " for function " + node.getId().getText() + ". Expecting "
                        + getType(functionEntry.getParamTypeAt(i)) + " got " + getType(valType));
            }

            if (valueType == Constants.INT_ARR || valueType == Constants.CHAR_ARR) {
                Log.d("FuncExpr", "isString is " + isString);
                if (lVal && !isString) {
                    if (ent == null) {
                        exit(getPos(token), "Entry is null");
                    }

                    ArrayEntry arrayEntry = (ArrayEntry) ent;
                    if (arrayEntry.getDimensionsSize() != functionEntry.getParamDimensions(i)) {
                        exit(getPos(token), "Array dimension mismatch for argument " +
                                (i + 1) + " to function " + node.getId() + ". Expecting " +
                                functionEntry.getParamDimensions(i) + " got " + arrayEntry.getDimensionsSize());
                    }
                }
            }

            if (functionEntry.byRef(i) && !lVal) {
                exit(getPos(node.getId()), "Cannot pass right value by ref "
                        + " for argument " + i + " for function " + node.getId());
            }

            if (functionEntry.byRef(i)) {
                iCode.genQuad(Constants.OP_PAR, Constants.PAR_REF, null, value);
            } else {
                iCode.genQuad(Constants.OP_PAR, Constants.PAR_VAL, null, value);
            }
        }
        String newTmp = iCode.newTmp();
        iCode.genQuad(Constants.OP_PAR, Constants.PAR_RET, null, newTmp);
        iCode.genQuad(Constants.OP_CALL, node.getId().getText(), null, null);
        fromFunc = false;

        valType = functionEntry.getType();
        name = functionEntry.getName();
        value = newTmp;

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
            exit(getPos(node.getStringConst()), "Requested offset is out of bounds. " +
                    "(Offset: " + offset + ", Length: " + str.length() + ")");
        }
        valType = Constants.CHAR;
        value = str.charAt(offset) + "";
    }



    @Override
    public void caseAFuncCallStatement(AFuncCallStatement node) {
        token = node.getId();
        FunctionEntry functionEntry = (FunctionEntry) symbolTable.getEntry(node.getId().getText());
        if (functionEntry == null) {
            eUndefined(token);
        }

        lVal = false;
        if (functionEntry.getParamCount() > node.getExpr().size()) {
            exit(getPos(node.getId()), "Too few arguments to function " + funcName
                    + "\nExpecting " + functionEntry.getParamCount() + " got " + node.getExpr().size());
        } else if (functionEntry.getParamCount() < node.getExpr().size()) {
            exit(getPos(node.getId()), "Too many arguments to function " + funcName
                    + "\nExpecting " + functionEntry.getParamCount() + " got " + node.getExpr().size());
        }

        fromFunc = true;
        for (int i = 0; i < node.getExpr().size(); i++) {
            ent = null;
            node.getExpr().get(i).apply(this);
            TableEntry entry = ent;
            int valueType;
            if (entry == null) {
                valueType = valType;
            } else {
                valueType = entry.getType();
            }

            try {
                if (valueType != functionEntry.getParamTypeAt(i)) {
                    exit(getPos(node.getId()), "Parameter mismatch at argument " + (i + 1)
                            + " for function " + node.getId().getText() + ". Expecting "
                            + getType(functionEntry.getParamTypeAt(i)) + " got " + getType(valType));
                }
            } catch (IndexOutOfBoundsException ioobe) {
                exit(getPos(node.getId()), "Array index out of bounds");
            }

            if (valueType == Constants.INT_ARR || valueType == Constants.CHAR_ARR) {
                if (lVal && !isString) {
                    if (ent == null) {
                        exit(getPos(token), "Entry is null");
                    }

                    ArrayEntry arrayEntry = (ArrayEntry) ent;
                    if (arrayEntry.getDimensionsSize() != functionEntry.getParamDimensions(i)) {
                        exit(getPos(token), "Array dimension mismatch for argument " +
                                (i + 1) + " to function " + node.getId() + ". Expecting " +
                                functionEntry.getParamDimensions(i) + " got " + arrayEntry.getDimensionsSize());
                    }
                }
            }

            if (functionEntry.byRef(i) && !lVal) {
                exit(getPos(node.getId()), "Cannot pass right value by ref "
                        + " for argument " + i + " for function " + node.getId());
            }

            if (functionEntry.byRef(i)) {
                iCode.genQuad(Constants.OP_PAR, Constants.PAR_REF, null, value);
            } else {
                iCode.genQuad(Constants.OP_PAR, Constants.PAR_VAL, null, value);
            }
        }
        fromFunc = false;
        iCode.genQuad(Constants.OP_PAR, Constants.PAR_RET, null, iCode.newTmp());
        iCode.genQuad(Constants.OP_CALL, node.getId().getText(), null, null);
    }

}