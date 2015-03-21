package bf_mips.exceptions;

public class BFSyntaxError extends Error {
    public BFSyntaxError(Character unrecognizedCharacter) {
        super(String.format("Unrecognized token: %s", unrecognizedCharacter));
    }
}
