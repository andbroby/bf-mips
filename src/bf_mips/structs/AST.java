package bf_mips.structs;

/**
 * Created by broby on 17/03/15.
 */
public class AST {
    abstract class Node {

    }

    abstract class IncrementPt extends Node {}
    abstract class DecrementPt extends Node {}
    abstract class IncrementByte extends Node {}
    abstract class DecrementByte extends Node {}
    abstract class Output extends Node {}
    abstract class Input extends Node {}

    public AST() {}

    public void add_node(char token) {

    }
}
