package bf_mips.back_end;

import bf_mips.structs.AbstractSyntaxTree.AST;
import bf_mips.structs.AbstractSyntaxTree.Node;

public class CodeGenerator {
    private AST syntaxTree;
    private final String incPt = new StringBuilder()
            .append("INCPT:\n")
            .append("\taddiu $t1, $t1, 4\n")
            .append("\tjr $ra\n")
            .toString();
    private final String decPt = new StringBuilder()
            .append("DECPT:\n")
            .append("\taddiu $t1, $t1, -4\n")
            .append("\tjr $ra\n")
            .toString();
    private final String decVal = new StringBuilder()
            .append("DECVAL:\n")
            .append("\taddiu $t2, 0($t1), -1\n")
            .append("\tsw $t2 0($t1)\n")
            .append("\tjr $ra\n")
            .toString();
    private final String incVal = new StringBuilder()
            .append("INCVAL:\n")
            .append("\taddiu $t2, 0($t1), 1\n")
            .append("\tsw $t2 0($t1)\n")
            .append("\tjr $ra\n")
            .toString();
    private final String mainInit = new StringBuilder()
            .append("MAIN:\n")
            .append("\tli $t1, 0\n")
            .append("\tla $s0, array\n")
            .toString();
    private final String decInstruction = new StringBuilder()
            .append("\tjal INC")
            .toString();
    private final String incInstruction = new StringBuilder()
            .append("\tjal INC\n")
            .toString();
    private final String arrayInit = "array: .word 30000\n";

    public CodeGenerator(AST syntaxTree) {
        this.syntaxTree = syntaxTree;
    }

    public String generate() {
        String code = incPt + decPt + incVal + decVal + arrayInit + mainInit;

        return traverse(syntaxTree.getRoot(), code);
    }

    private String traverse(Node startNode, String generatedCode) {
        for (Node child : startNode.getChildren()) {
            switch (child.getToken()) {
                case '+':
                    generatedCode += "PLUS";
                    break;
                case '-':
                    generatedCode += "MINUS";
                    break;
                case '>':
                    generatedCode += incInstruction;
                    break;
                case '<':
                    generatedCode += decInstruction;
                    break;
                case '[':
                    generatedCode += "BEGIN LOOP";
                    break;
                case ']':
                    generatedCode += "END LOOP";
                    break;
            }
            traverse(child, generatedCode);
        }

        return generatedCode;
    }
}