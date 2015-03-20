package bf_mips.back_end;

import bf_mips.structs.ASMCode;
import bf_mips.structs.AbstractSyntaxTree.AST;
import bf_mips.structs.AbstractSyntaxTree.Node;

import java.util.List;

public class CodeGenerator {
    private AST syntaxTree;

    public CodeGenerator(AST syntaxTree) {
        this.syntaxTree = syntaxTree;
    }

    public String generate() {
        ASMCode MIPSCode = new ASMCode();
        return traverse(syntaxTree.getRoot(), MIPSCode, 0);
    }

    private String traverse(Node startNode, ASMCode MIPSCode, int level) {
        if (! startNode.hasChildren()) {
            return MIPSCode.toString();
        }

        List<Node> children = startNode.getChildren();
        for (Node child : children) {
            String loopLabel = Integer.toString(level);
            switch (child.getToken()) {
                case '+':
                    MIPSCode.incrementValue();
                    break;
                case '-':
                    MIPSCode.decrementValue();
                    break;
                case '>':
                    MIPSCode.incrementPt();
                    break;
                case '<':
                    MIPSCode.decrementPt();
                    break;
                case '[':
                    MIPSCode.beginLoop(loopLabel);
                    break;
                case ']':
                    MIPSCode.endLoop(loopLabel);
                    break;
                case '.':
                    MIPSCode.output();
                    break;
                case ',':
                    MIPSCode.input();
                    break;
            }
            traverse(child, MIPSCode, ++level);
            if (level > 0) {
                MIPSCode.endLoop(loopLabel);
            }
        }

        return MIPSCode.toString();
    }
}