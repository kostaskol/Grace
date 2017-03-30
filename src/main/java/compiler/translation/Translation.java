package compiler.translation;


import graceLang.analysis.DepthFirstAdapter;
import graceLang.node.*;

public class Translation extends DepthFirstAdapter {

    private int tabs = 0;

    private void tabify() {
        for (int i = 0; i < this.tabs; i++) {
            System.out.print("   ");
        }
    }

    private void print(String str) {
        tabify();
        System.out.println(str);
    }

    @Override
    public void inAFuncDef(AFuncDef node) {
        print("Function Definition:");
        print("{");
        tabs++;
    }

    @Override
    public void outAFuncDef(AFuncDef node) {
        tabs--;
        print("}");
    }

    @Override
    public void inAHeader(AHeader node) {
        print("Header:");
        print("{");
        tabs++;
        print("Function name: " + node.getFuncName());
        print("Function parameters: " + node.getParams());
        print("Return type: " + node.getReturnType());
        tabs--;
        print("}");
    }

    @Override
    public void inAFuncDeclLocalDef(AFuncDeclLocalDef node) {
        print("Function declaration:");
        print("{");
        tabs++;

    }

    @Override
    public void outAFuncDeclLocalDef(AFuncDeclLocalDef node) {
        tabs--;
        print("}");
    }

    @Override
    public void inAVarDeclLocalDef(AVarDeclLocalDef node) {
        print("Variables declaration:");
        print("{");
        tabs++;
    }

    @Override
    public void outAVarDeclLocalDef(AVarDeclLocalDef node) {
        tabs--;
        print("}");
    }

    @Override
    public void inAVarDecl(AVarDecl node) {
        print("Variable id: " + node.getVarName());
        if (!node.getMultipleIds().isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < node.getMultipleIds().size(); i++) {
                builder.append(node.getMultipleIds().get(i));
            }
            String[] ids = builder.toString().split(",");
            builder = new StringBuilder();
            for (String s : ids) {
                builder.append(s);
            }
            print("Multiple ids: " + builder);
        }
        if (node.getArrayInit() != null) {
            print("Type (array): " + node.getDataType() + "[]");
            print("Array size: " + node.getArrayInit());
        } else {
            print("Type: " + node.getDataType());
        }
    }

    @Override
    public void inABlock(ABlock node) {
        print("Block:");
        print("{");
        tabs++;
    }

    @Override
    public void outABlock(ABlock node) {
        tabs--;
        print("}");
    }

    @Override
    public void inALValAssStatement(ALValAssStatement node) {
        print("Left value assignment: " + node.getLValAssign());
    }

    @Override
    public void inARetStStatement(ARetStStatement node) {
        print("Return statement: " + node);
    }

    @Override
    public void inAFuncCallStatement(AFuncCallStatement node) {
        print("Function call: ");
        print("{");
        tabs++;
    }

    @Override
    public void outAFuncCallStatement(AFuncCallStatement node) {
        tabs--;
        print("}");
    }



    @Override
    public void inAWhileSt(AWhileSt node) {
        print("While statement:");
        print("{");
        tabs++;
        print("Condition: " + node.getCondition());
    }

    @Override
    public void outAWhileSt(AWhileSt node) {
        tabs--;
        print("}");
    }

    @Override
    public void inAWhileWElse(AWhileWElse node) {
        super.inAWhileWElse(node);
    }

    @Override
    public void inAIfSt(AIfSt node) {
        print("If statement:");
        print("{");
        tabs++;
    }

    @Override
    public void outAIfSt(AIfSt node) {
        tabs--;
        print("}");
    }

    @Override
    public void inAIfHeader(AIfHeader node) {
        print("Condition: " + node.getCondition());
    }

    @Override
    public void inANoElseIfTail(ANoElseIfTail node) {
        print("No else statement:");
        print("{");
        tabs++;
    }

    @Override
    public void outANoElseIfTail(ANoElseIfTail node) {
        tabs--;
        print("}");
    }

    @Override
    public void inAElseIfTail(AElseIfTail node) {
        print("Else statement:");
        print("{");
        tabs++;
    }

    @Override
    public void outAElseIfTail(AElseIfTail node) {
        tabs--;
        print("}");
    }

    @Override
    public void inAIfStWElse(AIfStWElse node) {
        print("If statement with else:");
        print("{");
        tabs++;
    }

    @Override
    public void outAIfStWElse(AIfStWElse node) {
        tabs--;
        print("}");
    }

    @Override
    public void inAWhileStWElse(AWhileStWElse node) {
        print("While with else:");
        print("{");
        tabs++;
    }

    @Override
    public void outAWhileStWElse(AWhileStWElse node) {
        tabs--;
        print("}");
    }

    @Override
    public void inAIfElse(AIfElse node) {
        print("If with else: ");
        print("{");
        tabs++;
    }

    @Override
    public void outAIfElse(AIfElse node) {
        tabs--;
        print("}");
    }

    @Override
    public void inARetSt(ARetSt node) {
        print("Return statement: ");
        print("{");
        tabs++;
    }

    @Override
    public void outARetSt(ARetSt node) {
        tabs--;
        print("}");
    }

    @Override
    public void inAFuncCall(AFuncCall node) {
        print("id: " + node.getId());
        if (node.getFuncParams() == null) {
            print("Function parameters: None");
        }
    }

    @Override
    public void inAMultFuncParams(AMultFuncParams node) {
        print("Rest of parameters: " + node.getExpression());
    }

    @Override
    public void inAFuncParams(AFuncParams node) {
        print("Number of parameters: " + (node.getMultFuncParams().size() + 1));
        print("First parameter: " + node.getExpression());
    }

    @Override
    public void inALValWOffs(ALValWOffs node) {
        super.inALValWOffs(node);
    }

    @Override
    public void inALValIdLVal(ALValIdLVal node) {
        super.inALValIdLVal(node);
    }

    @Override
    public void inALValStrLVal(ALValStrLVal node) {
        super.inALValStrLVal(node);
    }

    @Override
    public void inALValOffsLVal(ALValOffsLVal node) {
        super.inALValOffsLVal(node);
    }

    @Override
    public void inAOffs(AOffs node) {
        super.inAOffs(node);
    }

    @Override
    public void inAFuncCallExpression(AFuncCallExpression node) {
        super.inAFuncCallExpression(node);
    }

    @Override
    public void inANumericExpression(ANumericExpression node) {
        print("Numeric expression:");
        print("{");
        tabs++;
    }

    @Override
    public void outANumericExpression(ANumericExpression node) {
        tabs--;
        print("}");
    }

    @Override
    public void inAAddNumericExpr(AAddNumericExpr node) {
        print("Addition:");
        print("{");
        tabs++;
        print("Left operand: " + node.getLeft());
        print("Right operand: " + node.getRight());
        tabs--;
        print("}");
    }

    @Override
    public void inASubNumericExpr(ASubNumericExpr node) {
        print("Subtraction:");
        print("{");
        tabs++;
        print("Left operand: " + node.getLeft());
        print("Right operand: " + node.getRight());
        tabs--;
        print("}");
    }

    @Override
    public void inATimesTerm(ATimesTerm node) {
        print("Multiplication:");
        print("{");
        tabs++;
        print("Left operand: " + node.getLeft());
        print("Right operand: " + node.getRight());
        tabs--;
        print("}");
    }

    @Override
    public void inADivTerm(ADivTerm node) {
        print("Division:");
        print("{");
        tabs++;
        print("Left operand: " + node.getLeft());
        print("Right operand: " + node.getRight());
        tabs--;
        print("}");
    }

    @Override
    public void inAModTerm(AModTerm node) {
        print("Modulus:");
        print("{");
        tabs++;
        print("Left operand: " + node.getLeft());
        print("Right operand: " + node.getRight());
        tabs--;
        print("}");
    }

    @Override
    public void inAParExprFactor(AParExprFactor node) {
        print("Parenthesised expression:");
        print("{");
        tabs++;
    }

    @Override
    public void outAParExprFactor(AParExprFactor node) {
        tabs--;
        print("}");
    }

    @Override
    public void inALValueFactor(ALValueFactor node) {
        print("Left value: ");
        print("{");
        tabs++;
        print("Value: " + node.getLVal());
        tabs--;
        print("}");
    }

    @Override
    public void inACharConstFactor(ACharConstFactor node) {
        print("Character constant: " + node.getCharConst());
    }

    @Override
    public void inANumberFactor(ANumberFactor node) {
        print("Number: " + node.getNumber());
    }

    @Override
    public void inASignedFactor(ASignedFactor node) {
        print("Signed number:");
        print("{");
        tabs++;
    }

    @Override
    public void outASignedFactor(ASignedFactor node) {
        tabs--;
        print("}");
    }

    @Override
    public void inAPositiveSignedNumber(APositiveSignedNumber node) {
        print("Sign: " + node.getPlus());
        print("Number: " + node.getNumber());
    }

    @Override
    public void inANegativeSignedNumber(ANegativeSignedNumber node) {
        print("Sign: " + node.getMinus());
        print("Number: " + node.getNumber());
    }

    @Override
    public void inAOrCondCondition(AOrCondCondition node) {
        print("Or Condition:");
        print("{");
        tabs++;
        print("Left operand: " + node.getLeft());
        print("Right operand: " + node.getRight());
        tabs--;
        print("}");
    }

    @Override
    public void inANotCondNotCond(ANotCondNotCond node) {
        print("Not condition:");
        print("{");
        tabs++;
        print("Operand: " + node.getOperand());
        tabs--;
        print("}");
    }

    @Override
    public void inAOperatorCondOperatorCond(AOperatorCondOperatorCond node) {
        print("Operation condition:");
        print("{");
        tabs++;
        print("Left: " + node.getLeft());
        print("Operation: " + node.getCompOperator());
        print("Right: " + node.getRight());
        tabs--;
        print("}");
    }

    @Override
    public void inAParCondParCond(AParCondParCond node) {
        print("Parenthesised condition:");
        print("{");
        tabs++;
    }

    @Override
    public void outAParCondParCond(AParCondParCond node) {
        tabs--;
        print("}");
    }

    @Override
    public void inAAndCondAndCond(AAndCondAndCond node) {
        print("And:");
        print("{");
        tabs++;
        print("Left: " + node.getLeft());
        print("Right: " + node.getRight());
        tabs--;
        print("}");
    }




}