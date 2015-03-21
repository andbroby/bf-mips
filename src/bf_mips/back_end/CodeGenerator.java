package bf_mips.back_end;

import bf_mips.structs.ASMCode;
import bf_mips.structs.AbstractSyntaxTree.AST;
import bf_mips.structs.AbstractSyntaxTree.Node;

import java.util.List;
import java.util.Stack;

public class CodeGenerator {
    private AST syntaxTree;

    public CodeGenerator(AST syntaxTree) {
        this.syntaxTree = syntaxTree;
    }

    public String generate() {
        ASMCode MIPSCode = new ASMCode();
        Stack<String> loops = new Stack<String>();
        return traverse(syntaxTree.getRoot(), MIPSCode, loops, 0);
    }

    private String traverse(Node startNode, ASMCode MIPSCode, Stack<String> loops, int count) {
        List<Node> children = startNode.getChildren();
        for (Node child: children) {
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
                    String label = Integer.toString(count);
                    MIPSCode.beginLoop(Integer.toString(count));
                    count++;
                    loops.push(label);
                    break;
                case '.':
                    MIPSCode.output();
                    break;
                case ',':
                    MIPSCode.input();
                    break;
            }

            if (child.hasChildren()) {
                traverse(child, MIPSCode, loops, count);
            }
        }

        if (! loops.isEmpty()) {
           MIPSCode.endLoop(loops.pop());
        }

        return MIPSCode.toString();
    }
}