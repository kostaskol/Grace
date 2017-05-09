package compiler.etc;


public class Constants {
    // Variable types
    public static final int INT             = 100;
    public static final int INT_ARR         = 101;
    public static final int CHAR            = 110;
    public static final int CHAR_ARR        = 111;
    public static final int NOTHING         = 120;
    public static final int TYPE_UNKNOWN    = -101;


    // Comparison operators
    public static final int GT              = 200;
    public static final int GTE             = 201;
    public static final int EQ              = 202;
    public static final int LT              = 203;
    public static final int LTE             = 204;
    public static final int NEQ             = 205;
    public static final int OP_UNKNOWN      = -102;


    // Entry types
    public static final int TYPE_ARR        = 300;
    public static final int TYPE_SCAL       = 301;
    public static final int TYPE_FUNC       = 302;


    // Quad operations
    public static final int OP_UNIT         = 400;
    public static final int OP_ENDU         = 401;

    public static final int OP_ADD          = 4010;
    public static final int OP_SUB          = 4011;
    public static final int OP_MULT         = 4012;
    public static final int OP_DIV          = 4013;
    public static final int OP_MOD          = 4014;

    public static final int OP_ASS          = 402;
    public static final int OP_OFFS         = 403;

    public static final int OP_GT           = 4040;
    public static final int OP_GTE          = 4041;
    public static final int OP_EQ           = 4042;
    public static final int OP_LT           = 4043;
    public static final int OP_LTE          = 4044;
    public static final int OP_NEQ          = 4045;

    public static final int OP_IF           = 405;
    public static final int OP_JUMP         = 406;
    public static final int OP_LBL          = 407;
    public static final int OP_JUMPL        = 4060;

    public static final int OP_CALL         = 408;
    public static final int OP_PAR          = 409;
    public static final int OP_RET          = 410;

    public static final int OP_UNKN         = 411;

    public static final String PAR_REF      = "ref";
    public static final String PAR_VAL      = "val";
}
