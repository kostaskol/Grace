package compiler.main;

import compiler.translation.Translation;

import java.io.*;
import graceLang.lexer.Lexer;
import graceLang.node.Start;
import graceLang.parser.Parser;

public class Main {
    public static void main(String[] args) {
        InputStream io = null;
        if (args.length == 1) try {
            io = new FileInputStream(args[0]);
        } catch (IOException ioe) {
            ;
        }
        else {
            io = System.in;
        }

        if (io == null) {
            io = System.in;
        }
        Parser p = new Parser(
                new Lexer(
                        new PushbackReader(
                                new InputStreamReader(io), 1024
                        )
                )
        );

        try {
            Start tree = p.parse();
            tree.apply(new Translation());
            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}