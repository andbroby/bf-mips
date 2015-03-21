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
        return traverse(syntaxTree.getRoot(), MIPSCode, 0, 0);
    }

    private String traverse(Node startNode, ASMCode MIPSCode, int levels, int count) {
        List<Node> children = startNode.getChildren();
        int curr_label = count;
        for (Node child: children) {
            if (child.hasChildren()) {
                count++;
                MIPSCode.beginLoop(Integer.toString(count));
                traverse(child, MIPSCode, levels+1, count);
            }

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
                case '.':
                    MIPSCode.output();
                    break;
                case ',':
                    MIPSCode.input();
                    break;
            }
        }

        if (levels > 0) {
            MIPSCode.endLoop(Integer.toString(curr_label));
        }

        return MIPSCode.toString();
    }
}