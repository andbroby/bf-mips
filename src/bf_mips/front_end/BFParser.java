package bf_mips.front_end;

import bf_mips.structs.AST;

public class BFParser {
    public static AST parse(char[] tokens) {
        AST tree = new AST();

        for (int i = 0; i < tokens.length; i++) {
            tree.add_node(tokens[i]);
        }

        return tree;
    }
}