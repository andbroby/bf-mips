package bf_mips.back_end;

import bf_mips.structs.AbstractSyntaxTree.AST;
import bf_mips.structs.AbstractSyntaxTree.Node;

public class CodeGenerator {
    private AST syntaxTree;
    private final String inputVal = new StringBuilder()
            .append("INPUT:\n")
            .append("\tli $v0, 5")
            .append("\tsyscall")
            .append("\tsw $v0, 0($t1)\n")
            .append("\tjr $ra\n")
            .toString();
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
            .append("\tlw $a0, 0($t1)\n")
            .append("\taddi $a0, $a0, -1\n")
            .append("\tsw $a0, 0($t1)\n")
            .append("\tjr $ra\n")
            .toString();
    private final String incVal = new StringBuilder()
            .append("INCVAL:\n")
            .append("\tlw $a0, 0($t1)\n")
            .append("\taddi $a0, $a0, 1\n")
            .append("\tsw $a0, 0($t1)\n")
            .append("\tjr $ra\n")
            .toString();
    private final String printVal = new StringBuilder()
            .append("PRINTVAL:\n")
            .append("\tlw $a0, 0($t1)\n")
            .append("\tli $v0, 1\n")
            .append("\tsyscall\n")
            .append("\tjr $ra\n")
            .toString();
    private final String mainInit = new StringBuilder()
            .append("MAIN:\n")
            .append("\tla $t1, array\n")
            .toString();
    private final String decInstruction = new StringBuilder()
            .append("\tjal INCPT")
            .toString();
    private final String incInstruction = new StringBuilder()
            .append("\tjal INCPT\n")
            .toString();
    private final String printInstruction = new StringBuilder()
            .append("\tjal PRINTVAL\n")
            .toString();
    private final String incValInstruction = new StringBuilder()
            .append("\tjal INCVAL\n")
            .toString();
    private final String decValInstruction = new StringBuilder()
            .append("\tjal DECVAL\n")
            .toString();
    private final String inputInstruction = new StringBuilder()
            .append("\tjal INPUT\n")
            .toString();
    private final String arrayInit = "array: .word 0:30000\n";
    private final String dataInit = ".data\n";
    private final String textInit = ".text\n";

    public CodeGenerator(AST syntaxTree) {
        this.syntaxTree = syntaxTree;
    }

    public String generate() {
        String code = dataInit + arrayInit + textInit + mainInit;

        return traverse(syntaxTree.getRoot(), code, 0) + incPt + decPt + incVal + decVal + printVal;
    }

    private String traverse(Node startNode, String generatedCode, int loops) {
        for (Node child : startNode.getChildren()) {
            switch (child.getToken()) {
                case '+':
                    generatedCode += incValInstruction;
                    break;
                case '-':
                    generatedCode += decValInstruction;
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
                case '.':
                    generatedCode += printInstruction;
                    break;
                case ',':
                    generatedCode += inputInstruction;
            }
            traverse(child, generatedCode, loops);
        }

        return generatedCode;
    }
}