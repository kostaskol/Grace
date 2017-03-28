package compiler.translation;


import graceLang.analysis.DepthFirstAdapter;
import graceLang.node.*;

public class Translation extends DepthFirstAdapter {
    @Override
    public void caseTIf(TIf node) {
        System.out.println("if : " + node);
    }

    @Override
    public void caseTThen(TThen node) {
        System.out.println("then: " + node);
    }

    @Override
    public void caseTElse(TElse node) {
        System.out.println("else: " + node);
    }

    @Override
    public void caseTNot(TNot node) {
        System.out.println("not: " + node);
    }

    @Override
    public void caseTWhile(TWhile node) {
        System.out.println("while : " + node);
    }

    @Override
    public void caseTDo(TDo node) {
        System.out.println("do: " + node);
    }

    @Override
    public void caseTNlE(TNlE node) {
        System.out.println("new line: " + node);
    }

    @Override
    public void caseTTabE(TTabE node) {
        System.out.println("tab: " + node);
    }

    @Override
    public void caseTCrE(TCrE node) {
        System.out.println("carriage return: " + node);
    }

    @Override
    public void caseTNullTermE(TNullTermE node) {
        System.out.println("null terminator: " + node);
    }

    @Override
    public void caseTBackslashE(TBackslashE node) {
        System.out.println("backslash: " + node);
    }

    @Override
    public void caseTApostropheE(TApostropheE node) {
        System.out.println("apostrophe: " + node);
    }

    @Override
    public void caseTQuotesE(TQuotesE node) {
        System.out.println("quotes: " + node);
    }

    @Override
    public void caseTHexaE(THexaE node) {
        System.out.println("hexadecimal: " + node);
    }

    @Override
    public void caseTVar(TVar node) {
        System.out.println("variable: " + node);
    }

    @Override
    public void caseTAs(TAs node) {
        System.out.println("as (type): " + node);
    }

    @Override
    public void caseTFun(TFun node) {
        System.out.println("fun : " + node);
    }

    @Override
    public void caseTPlus(TPlus node) {
        System.out.println("plus: " + node);
    }

    @Override
    public void caseTMinus(TMinus node) {
        System.out.println("minus: " + node);
    }

    @Override
    public void caseTTimes(TTimes node) {
        System.out.println("times: " + node);
    }

    @Override
    public void caseTDiv(TDiv node) {
        System.out.println("div: " + node);
    }

    @Override
    public void caseTMod(TMod node) {
        System.out.println("mod: " + node);
    }

    @Override
    public void caseTAnd(TAnd node) {
        System.out.println("and: " + node);
    }

    @Override
    public void caseTOr(TOr node) {
        System.out.println("or: " + node);
    }

    @Override
    public void caseTCompOperator(TCompOperator node) {
        System.out.println("comparison operator: " + node);
    }

    @Override
    public void caseTNothing(TNothing node) {
        System.out.println("type-nothing: " + node);
    }

    @Override
    public void caseTRef(TRef node) {
        System.out.println("by reference: " + node);
    }

    @Override
    public void caseTReturn(TReturn node) {
        System.out.println("return: " + node);
    }

    @Override
    public void caseTOpenPar(TOpenPar node) {
        System.out.println("open parenthesis: " + node);
    }

    @Override
    public void caseTClosePar(TClosePar node) {
        System.out.println("close parenthesis: " + node);
    }

    @Override
    public void caseTOpenCbrack(TOpenCbrack node) {
        System.out.println("open curly braces: " + node);
    }

    @Override
    public void caseTCloseCbrack(TCloseCbrack node) {
        System.out.println("close curly braces: " + node);
    }

    @Override
    public void caseTOpenBrack(TOpenBrack node) {
        System.out.println("open brackets: " + node);
    }

    @Override
    public void caseTCloseBrack(TCloseBrack node) {
        System.out.println("close brackets: " + node);
    }

    @Override
    public void caseTComma(TComma node) {
        System.out.println("comma: " + node);
    }

    @Override
    public void caseTSeparator(TSeparator node) {
        System.out.println("separator (semi-colon): " + node);
    }

    @Override
    public void caseTNumber(TNumber node) {
        System.out.println("number: " + node);
    }

    @Override
    public void caseTBlank(TBlank node) {
        /* Ignored */
    }

    @Override
    public void caseTComment(TComment node) {
        System.out.println("Comment (ignored): " + node);
    }

    @Override
    public void caseTMultComment(TMultComment node) {
        System.out.println("Mult comment (ignored): " + node);
    }

    @Override
    public void caseTOperation(TOperation node) {
        System.out.println("operation operator: " + node);
    }

    @Override
    public void caseTId(TId node) {
        System.out.println("id : " + node);
    }

    @Override
    public void caseTCharConst(TCharConst node) {
        System.out.println("character constant: " + node);
    }

    @Override
    public void caseTStringConst(TStringConst node) {
        System.out.println("string constant: " + node);
    }

    @Override
    public void caseEOF(EOF node) {
        System.out.println("EOF");
    }

    @Override
    public void defaultCase(@SuppressWarnings("unused") Node node) {
        super.defaultCase(node);
    }

    @Override
    public void caseTAssign(TAssign node) {
        System.out.println("assign: " + node);
    }

    @Override
    public void caseTChar(TChar node) {
        System.out.println("character data type: " + node);
    }

    @Override
    public void caseTInt(TInt node) {
        System.out.println("integer data type: " + node);
    }
/*
    @Override
    public void inStart(Start node) {
        super.inStart(node);
    }

    @Override
    public void outStart(Start node) {
        super.inStart(node);
    }

    @Override
    public void defaultIn(@SuppressWarnings("unused") Node node) {
        super.defaultIn(node);
    }

    @Override
    public void defaultOut(@SuppressWarnings("unused") Node node) {
        super.defaultOut(node);
    }

    @Override
    public void caseStart(Start node) {
        super.caseStart(node);
    }

    @Override
    public void inAProgram(AProgram node) {
        super.inAProgram(node);
    }

    @Override
    public void outAProgram(AProgram node) {
        super.outAProgram(node);
    }

    @Override
    public void caseAProgram(AProgram node) {
        System.out.println("Program");
    }

    @Override
    public void inAFuncDef(AFuncDef node) {
        super.inAFuncDef(node);
    }

    @Override
    public void outAFuncDef(AFuncDef node) {
        super.outAFuncDef(node);
    }

    @Override
    public void caseAFuncDef(AFuncDef node) {
        System.out.println("Function declaration");
    }

    @Override
    public void inAHeader(AHeader node) {
        super.inAHeader(node);
    }

    @Override
    public void outAHeader(AHeader node) {
        super.outAHeader(node);
    }

    @Override
    public void caseAHeader(AHeader node) {
        System.out.println("Header");
    }

    @Override
    public void inAParDef(AParDef node) {
        super.inAParDef(node);
    }

    @Override
    public void outAParDef(AParDef node) {
        super.outAParDef(node);
    }

    @Override
    public void caseAParDef(AParDef node) {
        super.caseAParDef(node);
    }

    @Override
    public void inAMoreParDef(AMoreParDef node) {
        super.inAMoreParDef(node);
    }

    @Override
    public void outAMoreParDef(AMoreParDef node) {
        super.outAMoreParDef(node);
    }

    @Override
    public void caseAMoreParDef(AMoreParDef node) {
        super.caseAMoreParDef(node);
    }

    @Override
    public void inAMultipleParams(AMultipleParams node) {
        super.inAMultipleParams(node);
    }

    @Override
    public void outAMultipleParams(AMultipleParams node) {
        super.outAMultipleParams(node);
    }

    @Override
    public void caseAMultipleParams(AMultipleParams node) {
        super.caseAMultipleParams(node);
    }

    @Override
    public void inAParType(AParType node) {
        super.inAParType(node);
    }

    @Override
    public void outAParType(AParType node) {
        super.outAParType(node);
    }

    @Override
    public void caseAParType(AParType node) {
        super.caseAParType(node);
    }

    @Override
    public void inAFuncLocalDef(AFuncLocalDef node) {
        super.inAFuncLocalDef(node);
    }

    @Override
    public void outAFuncLocalDef(AFuncLocalDef node) {
        super.outAFuncLocalDef(node);
    }

    @Override
    public void caseAFuncLocalDef(AFuncLocalDef node) {
        super.caseAFuncLocalDef(node);
    }

    @Override
    public void inAFuncDecLocalDef(AFuncDecLocalDef node) {
        super.inAFuncDecLocalDef(node);
    }

    @Override
    public void outAFuncDecLocalDef(AFuncDecLocalDef node) {
        super.outAFuncDecLocalDef(node);
    }

    @Override
    public void caseAFuncDecLocalDef(AFuncDecLocalDef node) {
        super.caseAFuncDecLocalDef(node);
    }

    @Override
    public void inAVarDefLocalDef(AVarDefLocalDef node) {
        super.inAVarDefLocalDef(node);
    }

    @Override
    public void outAVarDefLocalDef(AVarDefLocalDef node) {
        super.outAVarDefLocalDef(node);
    }

    @Override
    public void caseAVarDefLocalDef(AVarDefLocalDef node) {
        super.caseAVarDefLocalDef(node);
    }

    @Override
    public void inABlock(ABlock node) {
        super.inABlock(node);
    }

    @Override
    public void outABlock(ABlock node) {
        super.outABlock(node);
    }

    @Override
    public void caseABlock(ABlock node) {
        super.caseABlock(node);
    }

    @Override
    public void inAFuncDec(AFuncDec node) {
        super.inAFuncDec(node);
    }

    @Override
    public void outAFuncDec(AFuncDec node) {
        super.outAFuncDec(node);
    }

    @Override
    public void caseAFuncDec(AFuncDec node) {
        super.caseAFuncDec(node);
    }

    @Override
    public void inAVarDef(AVarDef node) {
        super.inAVarDef(node);
    }

    @Override
    public void outAVarDef(AVarDef node) {
        super.outAVarDef(node);
    }

    @Override
    public void caseAVarDef(AVarDef node) {
        super.caseAVarDef(node);
    }

    @Override
    public void inAMoreIds(AMoreIds node) {
        super.inAMoreIds(node);
    }

    @Override
    public void outAMoreIds(AMoreIds node) {
        super.outAMoreIds(node);
    }

    @Override
    public void caseAMoreIds(AMoreIds node) {
        super.caseAMoreIds(node);
    }

    @Override
    public void inADataIntDataType(ADataIntDataType node) {
        super.inADataIntDataType(node);
    }

    @Override
    public void outADataIntDataType(ADataIntDataType node) {
        super.outADataIntDataType(node);
    }

    @Override
    public void caseADataIntDataType(ADataIntDataType node) {
        super.caseADataIntDataType(node);
    }

    @Override
    public void inADataCharDataType(ADataCharDataType node) {
        super.inADataCharDataType(node);
    }

    @Override
    public void outADataCharDataType(ADataCharDataType node) {
        super.outADataCharDataType(node);
    }

    @Override
    public void caseADataCharDataType(ADataCharDataType node) {
        super.caseADataCharDataType(node);
    }

    @Override
    public void inAType(AType node) {
        super.inAType(node);
    }

    @Override
    public void outAType(AType node) {
        super.outAType(node);
    }

    @Override
    public void caseAType(AType node) {
        super.caseAType(node);
    }

    @Override
    public void inARetNothingRetType(ARetNothingRetType node) {
        super.inARetNothingRetType(node);
    }

    @Override
    public void outARetNothingRetType(ARetNothingRetType node) {
        super.outARetNothingRetType(node);
    }

    @Override
    public void caseARetNothingRetType(ARetNothingRetType node) {
        super.caseARetNothingRetType(node);
    }

    @Override
    public void inARetDataTypeRetType(ARetDataTypeRetType node) {
        super.inARetDataTypeRetType(node);
    }

    @Override
    public void outARetDataTypeRetType(ARetDataTypeRetType node) {
        super.outARetDataTypeRetType(node);
    }

    @Override
    public void caseARetDataTypeRetType(ARetDataTypeRetType node) {
        super.caseARetDataTypeRetType(node);
    }

    @Override
    public void inAIfStatement(AIfStatement node) {
        super.inAIfStatement(node);
    }

    @Override
    public void outAIfStatement(AIfStatement node) {
        super.outAIfStatement(node);
    }

    @Override
    public void caseAIfStatement(AIfStatement node) {
        super.caseAIfStatement(node);
    }

    @Override
    public void inAIfElseStatement(AIfElseStatement node) {
        super.inAIfElseStatement(node);
    }

    @Override
    public void outAIfElseStatement(AIfElseStatement node) {
        super.outAIfElseStatement(node);
    }

    @Override
    public void caseAIfElseStatement(AIfElseStatement node) {
        super.caseAIfElseStatement(node);
    }

    @Override
    public void inASepStatement(ASepStatement node) {
        super.inASepStatement(node);
    }

    @Override
    public void outASepStatement(ASepStatement node) {
        super.outASepStatement(node);
    }

    @Override
    public void caseASepStatement(ASepStatement node) {
        super.caseASepStatement(node);
    }

    @Override
    public void inALValAssStStatement(ALValAssStStatement node) {
        super.inALValAssStStatement(node);
    }

    @Override
    public void outALValAssStStatement(ALValAssStStatement node) {
        super.outALValAssStStatement(node);
    }

    @Override
    public void caseALValAssStStatement(ALValAssStStatement node) {
        super.caseALValAssStStatement(node);
    }

    @Override
    public void inAWhileStStatement(AWhileStStatement node) {
        super.inAWhileStStatement(node);
    }

    @Override
    public void outAWhileStStatement(AWhileStStatement node) {
        super.outAWhileStStatement(node);
    }

    @Override
    public void caseAWhileStStatement(AWhileStStatement node) {
        super.caseAWhileStStatement(node);
    }

    @Override
    public void inAReturnStStatement(AReturnStStatement node) {
        super.inAReturnStStatement(node);
    }

    @Override
    public void outAReturnStStatement(AReturnStStatement node) {
        super.outAReturnStStatement(node);
    }

    @Override
    public void caseAReturnStStatement(AReturnStStatement node) {
        super.caseAReturnStStatement(node);
    }

    @Override
    public void inABlockStatement(ABlockStatement node) {
        super.inABlockStatement(node);
    }

    @Override
    public void outABlockStatement(ABlockStatement node) {
        super.outABlockStatement(node);
    }

    @Override
    public void caseABlockStatement(ABlockStatement node) {
        super.caseABlockStatement(node);
    }

    @Override
    public void inAFuncCallStatement(AFuncCallStatement node) {
        super.inAFuncCallStatement(node);
    }

    @Override
    public void outAFuncCallStatement(AFuncCallStatement node) {
        super.outAFuncCallStatement(node);
    }

    @Override
    public void caseAFuncCallStatement(AFuncCallStatement node) {
        super.caseAFuncCallStatement(node);
    }

    @Override
    public void inAIfStatementWoEl(AIfStatementWoEl node) {
        super.inAIfStatementWoEl(node);
    }

    @Override
    public void outAIfStatementWoEl(AIfStatementWoEl node) {
        super.outAIfStatementWoEl(node);
    }

    @Override
    public void caseAIfStatementWoEl(AIfStatementWoEl node) {
        super.caseAIfStatementWoEl(node);
    }

    @Override
    public void inAElseStatementWoEl(AElseStatementWoEl node) {
        super.inAElseStatementWoEl(node);
    }

    @Override
    public void outAElseStatementWoEl(AElseStatementWoEl node) {
        super.outAElseStatementWoEl(node);
    }

    @Override
    public void caseAElseStatementWoEl(AElseStatementWoEl node) {
        super.caseAElseStatementWoEl(node);
    }

    @Override
    public void inAStatementWElse(AStatementWElse node) {
        super.inAStatementWElse(node);
    }

    @Override
    public void outAStatementWElse(AStatementWElse node) {
        super.outAStatementWElse(node);
    }

    @Override
    public void caseAStatementWElse(AStatementWElse node) {
        super.caseAStatementWElse(node);
    }

    @Override
    public void inAWhileStatement(AWhileStatement node) {
        super.inAWhileStatement(node);
    }

    @Override
    public void outAWhileStatement(AWhileStatement node) {
        super.outAWhileStatement(node);
    }

    @Override
    public void caseAWhileStatement(AWhileStatement node) {
        super.caseAWhileStatement(node);
    }

    @Override
    public void inARetSt(ARetSt node) {
        super.inARetSt(node);
    }

    @Override
    public void outARetSt(ARetSt node) {
        super.outARetSt(node);
    }

    @Override
    public void caseARetSt(ARetSt node) {
        super.caseARetSt(node);
    }

    @Override
    public void inAFuncCall(AFuncCall node) {
        super.inAFuncCall(node);
    }

    @Override
    public void outAFuncCall(AFuncCall node) {
        super.outAFuncCall(node);
    }

    @Override
    public void caseAFuncCall(AFuncCall node) {
        super.caseAFuncCall(node);
    }

    @Override
    public void inAFuncParams(AFuncParams node) {
        super.inAFuncParams(node);
    }

    @Override
    public void outAFuncParams(AFuncParams node) {
        super.outAFuncParams(node);
    }

    @Override
    public void caseAFuncParams(AFuncParams node) {
        super.caseAFuncParams(node);
    }

    @Override
    public void inAMultFuncParams(AMultFuncParams node) {
        super.inAMultFuncParams(node);
    }

    @Override
    public void outAMultFuncParams(AMultFuncParams node) {
        super.outAMultFuncParams(node);
    }

    @Override
    public void caseAMultFuncParams(AMultFuncParams node) {
        super.caseAMultFuncParams(node);
    }

    @Override
    public void inALValIdLVal(ALValIdLVal node) {
        super.inALValIdLVal(node);
    }

    @Override
    public void outALValIdLVal(ALValIdLVal node) {
        super.outALValIdLVal(node);
    }

    @Override
    public void caseALValIdLVal(ALValIdLVal node) {
        super.caseALValIdLVal(node);
    }

    @Override
    public void inALValStrLVal(ALValStrLVal node) {
        super.inALValStrLVal(node);
    }

    @Override
    public void outALValStrLVal(ALValStrLVal node) {
        super.outALValStrLVal(node);
    }

    @Override
    public void caseALValStrLVal(ALValStrLVal node) {
        super.caseALValStrLVal(node);
    }

    @Override
    public void inALValArrLVal(ALValArrLVal node) {
        super.inALValArrLVal(node);
    }

    @Override
    public void outALValArrLVal(ALValArrLVal node) {
        super.outALValArrLVal(node);
    }

    @Override
    public void caseALValArrLVal(ALValArrLVal node) {
        super.caseALValArrLVal(node);
    }

    @Override
    public void inAArr(AArr node) {
        super.inAArr(node);
    }

    @Override
    public void outAArr(AArr node) {
        super.outAArr(node);
    }

    @Override
    public void caseAArr(AArr node) {
        super.caseAArr(node);
    }

    @Override
    public void inALValAssign(ALValAssign node) {
        super.inALValAssign(node);
    }

    @Override
    public void outALValAssign(ALValAssign node) {
        super.outALValAssign(node);
    }

    @Override
    public void caseALValAssign(ALValAssign node) {
        super.caseALValAssign(node);
    }

    @Override
    public void inAFuncCallExpression(AFuncCallExpression node) {
        super.inAFuncCallExpression(node);
    }

    @Override
    public void outAFuncCallExpression(AFuncCallExpression node) {
        super.outAFuncCallExpression(node);
    }

    @Override
    public void caseAFuncCallExpression(AFuncCallExpression node) {
        super.caseAFuncCallExpression(node);
    }

    @Override
    public void inANumericExpression(ANumericExpression node) {
        super.inANumericExpression(node);
    }

    @Override
    public void outANumericExpression(ANumericExpression node) {
        super.outANumericExpression(node);
    }

    @Override
    public void caseANumericExpression(ANumericExpression node) {
        super.caseANumericExpression(node);
    }

    @Override
    public void inAPlusNumericExpr(APlusNumericExpr node) {
        super.inAPlusNumericExpr(node);
    }

    @Override
    public void outAPlusNumericExpr(APlusNumericExpr node) {
        super.outAPlusNumericExpr(node);
    }

    @Override
    public void caseAPlusNumericExpr(APlusNumericExpr node) {
        super.caseAPlusNumericExpr(node);
    }

    @Override
    public void inAMinusNumericExpr(AMinusNumericExpr node) {
        super.inAMinusNumericExpr(node);
    }

    @Override
    public void outAMinusNumericExpr(AMinusNumericExpr node) {
        super.outAMinusNumericExpr(node);
    }

    @Override
    public void caseAMinusNumericExpr(AMinusNumericExpr node) {
        super.caseAMinusNumericExpr(node);
    }

    @Override
    public void inATermNumericExpr(ATermNumericExpr node) {
        super.inATermNumericExpr(node);
    }

    @Override
    public void outATermNumericExpr(ATermNumericExpr node) {
        super.outATermNumericExpr(node);
    }

    @Override
    public void caseATermNumericExpr(ATermNumericExpr node) {
        super.caseATermNumericExpr(node);
    }

    @Override
    public void inATimesTerm(ATimesTerm node) {
        super.inATimesTerm(node);
    }

    @Override
    public void outATimesTerm(ATimesTerm node) {
        super.outATimesTerm(node);
    }

    @Override
    public void caseATimesTerm(ATimesTerm node) {
        super.caseATimesTerm(node);
    }

    @Override
    public void inADivTerm(ADivTerm node) {
        super.inADivTerm(node);
    }

    @Override
    public void outADivTerm(ADivTerm node) {
        super.outADivTerm(node);
    }

    @Override
    public void caseADivTerm(ADivTerm node) {
        super.caseADivTerm(node);
    }

    @Override
    public void inAModTerm(AModTerm node) {
        super.inAModTerm(node);
    }

    @Override
    public void outAModTerm(AModTerm node) {
        super.outAModTerm(node);
    }

    @Override
    public void caseAModTerm(AModTerm node) {
        super.caseAModTerm(node);
    }

    @Override
    public void inAFactorTerm(AFactorTerm node) {
        super.inAFactorTerm(node);
    }

    @Override
    public void outAFactorTerm(AFactorTerm node) {
        super.outAFactorTerm(node);
    }

    @Override
    public void caseAFactorTerm(AFactorTerm node) {
        super.caseAFactorTerm(node);
    }

    @Override
    public void inAParExprFactor(AParExprFactor node) {
        super.inAParExprFactor(node);
    }

    @Override
    public void outAParExprFactor(AParExprFactor node) {
        super.outAParExprFactor(node);
    }

    @Override
    public void caseAParExprFactor(AParExprFactor node) {
        super.caseAParExprFactor(node);
    }

    @Override
    public void inALValueFactor(ALValueFactor node) {
        super.inALValueFactor(node);
    }

    @Override
    public void outALValueFactor(ALValueFactor node) {
        super.outALValueFactor(node);
    }

    @Override
    public void caseALValueFactor(ALValueFactor node) {
        super.caseALValueFactor(node);
    }

    @Override
    public void inACharConstFactor(ACharConstFactor node) {
        super.inACharConstFactor(node);
    }

    @Override
    public void outACharConstFactor(ACharConstFactor node) {
        super.outACharConstFactor(node);
    }

    @Override
    public void caseACharConstFactor(ACharConstFactor node) {
        super.caseACharConstFactor(node);
    }

    @Override
    public void inANumberFactor(ANumberFactor node) {
        super.inANumberFactor(node);
    }

    @Override
    public void outANumberFactor(ANumberFactor node) {
        super.outANumberFactor(node);
    }

    @Override
    public void caseANumberFactor(ANumberFactor node) {
        super.caseANumberFactor(node);
    }

    @Override
    public void inACondCondition(ACondCondition node) {
        super.inACondCondition(node);
    }

    @Override
    public void outACondCondition(ACondCondition node) {
        super.outACondCondition(node);
    }

    @Override
    public void caseACondCondition(ACondCondition node) {
        super.caseACondCondition(node);
    }

    @Override
    public void inAAndCondCondition(AAndCondCondition node) {
        super.inAAndCondCondition(node);
    }

    @Override
    public void outAAndCondCondition(AAndCondCondition node) {
        super.outAAndCondCondition(node);
    }

    @Override
    public void caseAAndCondCondition(AAndCondCondition node) {
        super.caseAAndCondCondition(node);
    }

    @Override
    public void inAAndCondAndCond(AAndCondAndCond node) {
        super.inAAndCondAndCond(node);
    }

    @Override
    public void outAAndCondAndCond(AAndCondAndCond node) {
        super.outAAndCondAndCond(node);
    }

    @Override
    public void caseAAndCondAndCond(AAndCondAndCond node) {
        super.caseAAndCondAndCond(node);
    }

    @Override
    public void inANotCondAndCond(ANotCondAndCond node) {
        super.inANotCondAndCond(node);
    }

    @Override
    public void outANotCondAndCond(ANotCondAndCond node) {
        super.outANotCondAndCond(node);
    }

    @Override
    public void caseANotCondAndCond(ANotCondAndCond node) {
        super.caseANotCondAndCond(node);
    }

    @Override
    public void inANotCondNotCond(ANotCondNotCond node) {
        super.inANotCondNotCond(node);
    }

    @Override
    public void outANotCondNotCond(ANotCondNotCond node) {
        super.outANotCondNotCond(node);
    }

    @Override
    public void caseANotCondNotCond(ANotCondNotCond node) {
        super.caseANotCondNotCond(node);
    }

    @Override
    public void inAOperatorCondNotCond(AOperatorCondNotCond node) {
        super.inAOperatorCondNotCond(node);
    }

    @Override
    public void outAOperatorCondNotCond(AOperatorCondNotCond node) {
        super.outAOperatorCondNotCond(node);
    }

    @Override
    public void caseAOperatorCondNotCond(AOperatorCondNotCond node) {
        super.caseAOperatorCondNotCond(node);
    }

    @Override
    public void inAOperatorCondOperatorCond(AOperatorCondOperatorCond node) {
        super.inAOperatorCondOperatorCond(node);
    }

    @Override
    public void outAOperatorCondOperatorCond(AOperatorCondOperatorCond node) {
        super.outAOperatorCondOperatorCond(node);
    }

    @Override
    public void caseAOperatorCondOperatorCond(AOperatorCondOperatorCond node) {
        super.caseAOperatorCondOperatorCond(node);
    }

    @Override
    public void inAParCondOperatorCond(AParCondOperatorCond node) {
        super.inAParCondOperatorCond(node);
    }

    @Override
    public void outAParCondOperatorCond(AParCondOperatorCond node) {
        super.outAParCondOperatorCond(node);
    }

    @Override
    public void caseAParCondOperatorCond(AParCondOperatorCond node) {
        super.caseAParCondOperatorCond(node);
    }

    @Override
    public void inAParCondParCond(AParCondParCond node) {
        super.inAParCondParCond(node);
    }

    @Override
    public void outAParCondParCond(AParCondParCond node) {
        super.outAParCondParCond(node);
    }

    @Override
    public void caseAParCondParCond(AParCondParCond node) {
        super.caseAParCondParCond(node);
    }*/
}