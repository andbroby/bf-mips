package bf_mips.front_end;

import bf_mips.BFSyntaxError;

import java.util.HashSet;
import java.util.Arrays;

public class BFLexer {
    public static char[] analyze(String code) throws BFSyntaxError {
        Character[] keywords_array = new Character[] {'<', '>', '+', '-', '.', ',', '[', ']'};
        HashSet<Character> keywords = new HashSet<Character>(
                Arrays.asList(keywords_array)
        );

        char[] lexemes = code.replace(" ", "").toCharArray();

        for (Character lexeme : lexemes) {
            if (! keywords.contains(lexeme)) {
                throw new BFSyntaxError(lexeme);
            }
        }

        return lexemes;
    }
}
