package compiler.intermediateCode;

import compiler.etc.Constants;

public class Quad {
    private int id;
    private int op;
    private String src1;
    private String src2;
    private String trgt;

    Quad(int id, int op, String src1, String src2, String trgt) {
        this.op = op;
        this.src1 = src1;
        this.src2 = src2;
        this.trgt = trgt;
        this.id = id;
    }


    public int getOp() { return op; }

    public String getSrc1() { return this.src1; }

    public String getSrc2() { return this.src2; }

    public String getTrgt() { return this.trgt; }

    public void setTrgt(String trgt) { this.trgt = trgt; }

    private String getOpName(int op) {
        switch(op) {
            case Constants.OP_ADD:
                return "+";
            case Constants.OP_SUB:
                return "-";
            case Constants.OP_MULT:
                return "*";
            case Constants.OP_DIV:
                return "/";
            case Constants.OP_MOD:
                return "%";
            case Constants.OP_ASS:
                return ":=";
            case Constants.OP_OFFS:
                return "offset";
            case Constants.OP_UNIT:
                return "unit";
            case Constants.OP_ENDU:
                return "endu";
            case Constants.OP_GT:
                return ">";
            case Constants.OP_GTE:
                return ">=";
            case Constants.EQ:
                return "=";
            case Constants.NEQ:
                return "#";
            case Constants.OP_LT:
                return "<";
            case Constants.OP_LTE:
                return "<=";
            case Constants.OP_IF:
                return "if";
            case Constants.OP_JMP:
                return "jump";
            case Constants.OP_CALL:
                return "call";
            case Constants.OP_PAR:
                return "parameter";
            case Constants.OP_RET:
                return "return";
            case Constants.OP_NOOP:
                return ";;";
            case Constants.OP_NOT:
                return "~";
            default:
                return "OP_UNKNOWN";
        }
    }

    @Override
    public String toString() {
        return id + ": " + getOpName(op) + ", " + src1 + ", " + src2 + ", " + trgt;
    }
}
