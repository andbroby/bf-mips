package bf_mips.structs;

import java.util.Stack;

public class ASMCode {
    private String initCode = "";
    private String mainCode = "";
    private String entryPoints = "";
    private String sysExit = "\tli $v0, 10\n\tsyscall\n";
    private Stack<String> loops = new Stack<String>();
    private int loopCount = 0;

    private final String INPUT = new StringBuilder()
            .append("INPUT:\n")
            .append("\tli $v0, 5\n")
            .append("\tsyscall\n")
            .append("\tsw $v0, 0($t1)\n")
            .append("\tjr $ra\n")
            .toString();
    private final String INCPT = new StringBuilder()
            .append("INCPT:\n")
            .append("\taddiu $t1, $t1, 4\n")
            .append("\tjr $ra\n")
            .toString();
    private final String DECPT = new StringBuilder()
            .append("DECPT:\n")
            .append("\taddiu $t1, $t1, -4\n")
            .append("\tjr $ra\n")
            .toString();
    private final String DECVAL = new StringBuilder()
            .append("DECVAL:\n")
            .append("\tlw $a0, 0($t1)\n")
            .append("\taddi $a0, $a0, -1\n")
            .append("\tsw $a0, 0($t1)\n")
            .append("\tjr $ra\n")
            .toString();
    private final String INCVAL = new StringBuilder()
            .append("INCVAL:\n")
            .append("\tlw $a0, 0($t1)\n")
            .append("\taddi $a0, $a0, 1\n")
            .append("\tsw $a0, 0($t1)\n")
            .append("\tjr $ra\n")
            .toString();
    private final String OUTPUT = new StringBuilder()
            .append("OUTPUT:\n")
            .append("\tlw $a0, 0($t1)\n")
            .append("\tli $v0, 1\n")
            .append("\tsyscall\n")
            .append("\tjr $ra\n")
            .toString();

    private final String BEGIN_MAIN = new StringBuilder()
            .append("MAIN:\n")
            .append("\tla $t1, array\n")
            .toString();
    private final String DECLARE_ARRAY = "array: .word 0:30000\n";
    private final String DATA_SECTION = ".data\n";
    private final String TEXT_SECTION = ".text\n";

    public ASMCode() {
        this.initCode += DATA_SECTION + DECLARE_ARRAY + TEXT_SECTION;
        this.mainCode += BEGIN_MAIN;
        this.entryPoints += INCPT + DECPT + INCVAL + DECVAL + INPUT + OUTPUT;
    }

    public void input() {
        mainCode += "\tjal INPUT\n";
    }

    public void output() {
        mainCode += "\tjal OUTPUT\n";
    }

    public void incrementValue() {
        mainCode += "\tjal INCVAL\n";
    }

    public void decrementValue() {
        mainCode += "\tjal DECVAL\n";
    }

    public void incrementPt() {
        mainCode += "\tjal INCPT\n";
    }

    public void decrementPt() {
        mainCode += "\tjal DECPT\n";
    }

    public void beginLoop(String label) {
        mainCode += "LOOP_" + label + ":\n";
        mainCode += "\tlw $a0, 0($t1)\n";
        mainCode += "\tbgtz $a0, BEGIN_LOOP_" + label + "\n";
        mainCode += "\tj END_LOOP_" + label + "\n";
        mainCode += "BEGIN_LOOP_" + label + ":" + "\n";
    }

    public void endLoop(String label) {
        mainCode += "\tj LOOP_" + label + "\n";
        mainCode += "END_LOOP_" + label + ":" + "\n";
    }

    public String toString() {
        return initCode + "\n" + mainCode + sysExit + "\n" + entryPoints;
    }
}
