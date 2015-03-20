package bf_mips.structs.AbstractSyntaxTree;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private Node parent;
    private List<Node> children = new ArrayList();
    private final char token;

    public Node(char token) {
        this.token = token;
    }

    public void addParent(Node parent) {
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
