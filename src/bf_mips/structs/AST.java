package bf_mips.structs;

import java.util.ArrayList;
import java.util.List;

public class AST {
    private final Node root = new Node('R');
    private Node current_node = root;

    public AST() {}

    public void add_node(char token) {
        Node node = new Node(token);
        current_node.addChild(node);

        if (node.token == '[' || node.token == ']') {
            current_node = node;
        }
    }

    private class Node {
        private Node parent;
        private List<Node> children = new ArrayList();
        private final char token;

        public Node(char token) {
            this.token = token;
        }

        private void addParent(Node parent) {
            this.parent = parent;
        }

        public void addChild(Node child) {
            children.add(child);
            child.addParent(this);
        }

        public Node getParent() {
            return parent;
        }

        public List<Node> getChildren() {
            return children;
        }

        public char getToken() {
            return token;
        }

        public String toString() {
            return String.valueOf(token);
        }
    }

}
