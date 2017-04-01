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
            System.out.println("Could not open supplied input file. Reading from stdin");
        } else {
            io = System.in;
        }

        if (io == null) {
            io = System.in;
        }

        // Create a new parser
        Parser p = new Parser(
                new Lexer(
                        new PushbackReader(
                                new InputStreamReader(io), 1024
                        )
                )
        );

        try {
            // Parse the tree
            Start tree = p.parse();
            tree.apply(new Translation());
            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.exit(0);
    }
}