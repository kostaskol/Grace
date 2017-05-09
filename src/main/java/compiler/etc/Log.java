package compiler.etc;


public class Log {
    public static void d(String context, String message) {
        System.out.println(context + " : " + message);
    }

    public static void e(String context, String message) {
        System.err.println(context + " : " + message);
    }

    public static void w(String context, String msg) {
        System.out.println("Warning: " + context + " : " + msg);
    }
}
