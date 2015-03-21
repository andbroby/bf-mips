package bf_mips.structs.AbstractSyntaxTree;

public class AST {
    private final Node root = new Node('R');
    private Node current_node = root;

    public AST() {
        current_node.addChild(null);
    }

    public void add_node(char token) {
        Node node = new Node(token);

        if (token == ']') {
            current_node = current_node.getParent();
        } else {
            current_node.addChild(node);
            if (token == '[') {
                current_node = node;
                current_node.addChild(null);
            }
        }
    }

    public Node getRoot() {
        return root;
    }
}
