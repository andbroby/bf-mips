package bf_mips;

/**
 * Created by broby on 17/03/15.
 */
public class BFSyntaxError extends Exception {
    public BFSyntaxError(Character unrecognizedCharacter) {
        super(String.format("Unrecognized token: %s", unrecognizedCharacter));
    }
}
