package bf_mips.front_end;

import bf_mips.BFSyntaxError;

import java.util.HashSet;
import java.util.Arrays;
import java.util.Stack;

public class BFLexer {
    public static char[] lex(String code) {
        Character[] keywords_array = new Character[] {'<', '>', '+', '-', '.', ',', '[', ']'};
        HashSet<Character> keywords = new HashSet<Character>(
                Arrays.asList(keywords_array)
        );

        char[] lexemes = code.replace(" ", "").toCharArray();
        Stack<Character> stack = new Stack<Character>();

        for (Character lexeme : lexemes) {
            if (! keywords.contains(lexeme)) {
                throw new BFSyntaxError(lexeme);
            }

            if (lexeme == '[') {
                stack.push(lexeme);
            } else if (lexeme == ']') {
                if (stack.isEmpty()) {
                    throw new BFSyntaxError(lexeme);
                }
                stack.pop();
            }
        }

        if (! stack.isEmpty()) {
            throw new BFSyntaxError(']');
        }

        return lexemes;
    }
}
