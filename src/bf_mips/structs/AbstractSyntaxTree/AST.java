package bf_mips.structs.AbstractSyntaxTree;

public class AST {
    private final Node root = new Node('R');
    private Node current_node = root;

    public void add_node(char token) {
        Node node = new Node(token);

        if (token == ']') {
            current_node = current_node.getParent();
        } else {
            current_node.addChild(node);
            if (token == '[') {
                current_node = node;
            }
        }
    }
    public Node getRoot() {
        return root;
    }
}
