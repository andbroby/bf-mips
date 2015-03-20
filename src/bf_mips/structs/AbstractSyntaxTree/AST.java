package bf_mips.structs.AbstractSyntaxTree;

public class AST {
    private final Node root = new Node('R');
    private Node current_node = root;

    public void add_node(char token) {
        Node node = new Node(token);
        current_node.addChild(node);

        if (token == '[') {
            current_node = node;
        } else if (token == ']') {
            current_node = node.getParent();
        }
    }

    public Node getCurrentNode() {
        return current_node;
    }

    public Node getRoot() {
        return root;
    }
}
