package bf_mips;

import bf_mips.back_end.CodeGenerator;
import bf_mips.front_end.BFLexer;
import bf_mips.front_end.BFParser;
import bf_mips.structs.AbstractSyntaxTree.AST;

public class Main {

    public static void main(String[] args) {
        String code = "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.";
        char[] tokens = BFLexer.lex(code);
        AST tree = BFParser.parse(tokens);
        CodeGenerator cGen = new CodeGenerator(tree);
        System.out.println(cGen.generate());
    }
}
