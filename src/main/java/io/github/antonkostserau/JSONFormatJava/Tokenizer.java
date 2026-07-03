package io.github.antonkostserau.JSONFormatJava;

import java.util.List;
import java.util.ArrayList;

public class Tokenizer {
    private List<Token> list;

    private byte[] array;
    private int index;

    public Tokenizer(byte[] arrayUTF8) {
        list = new ArrayList<Token>();

        array = arrayUTF8;
        index = 0;
    }

    private Token getStringToken() {
        int begin;
        boolean escapeSequence;

        index += 1;
        
        begin = index;
        escapeSequence = false;

        while (index < array.length) {
            if (escapeSequence) {
                if (array[index] == '"' || array[index] == 't' || array[index] == '/' ||
                    array[index] == 'b' || array[index] == 'f' || array[index] == 'n' ||
                    array[index] == 'r' || array[index] == '\\') {

                    escapeSequence = false;
                    
                    index += 1;
                    
                    continue;
                }

                if (array[index] == 'u') {
                    index += 1;

                    for (int i = 0; i < 4; i++) {
                        if (index == array.length)
                            throw new RuntimeException();

                        if (
                            !(
                                (array[index] >= '0' && array[index] <= '9') ||
                                (array[index] >= 'a' && array[index] <= 'f') ||
                                (array[index] >= 'A' && array[index] <= 'F')
                            )
                        ) throw new RuntimeException();

                        index += 1;
                    }

                    escapeSequence = false;
                    
                    continue;
                }

                throw new RuntimeException();
            }

            if (array[index] == '"') {
                index += 1;
                return new Token(Token.STRING, begin, index - 1);
            }

            if (array[index] == '\\') {
                escapeSequence = true;
                
                index += 1;
                
                continue;
            }

            if ((array[index] & 0xFF) < ' ')
                throw new RuntimeException();

            index += 1;
        }

        throw new RuntimeException();
    }
    
    private Token getNumberToken() {
        int begin = index;
        
        if (array[index] == '-') {
            index += 1;
            
            if (index == array.length || array[index] < '0' || array[index] > '9')
                throw new RuntimeException();
        }
        
        if (array[index] == '0') {
            index += 1;
        } else {
            if (array[index] < '1' || array[index] > '9')
                throw new RuntimeException();
            
            while (index < array.length && array[index] >= '0' && array[index] <= '9')
                index += 1;
        }
        
        if (index < array.length && array[index] == '.') {
            index += 1;
            
            if (index == array.length || array[index] < '0' || array[index] > '9')
                throw new RuntimeException();
            
            while (index < array.length && array[index] >= '0' && array[index] <= '9')
                index += 1;
        }
        
        if (index < array.length && (array[index] == 'e' || array[index] == 'E')) {
            index += 1;
            
            if (index < array.length && (array[index] == '+' || array[index] == '-'))
                index += 1;
            
            if (index == array.length || array[index] < '0' || array[index] > '9')
                throw new RuntimeException();
            
            while (index < array.length && array[index] >= '0' && array[index] <= '9')
                index += 1;
        }
        
        return new Token(Token.NUMBER, begin, index);
    }
    
    private Token getBooleanToken() {
        int begin = index;
        
        if (
            index + 3 < array.length &&
            array[index] == 't' &&
            array[index + 1] == 'r' &&
            array[index + 2] == 'u' &&
            array[index + 3] == 'e'
        ) {
            index += 4;
            
            return new Token(Token.BOOLEAN, begin, index);
        }
        
        if (
            index + 4 < array.length &&
            array[index] == 'f' &&
            array[index + 1] == 'a' &&
            array[index + 2] == 'l' &&
            array[index + 3] == 's' &&
            array[index + 4] == 'e'
        ) {
            index += 5;
            
            return new Token(Token.BOOLEAN, begin, index);
        }
        
        throw new RuntimeException();
    }
    
    private Token getNULLToken() {
        int begin = index;
        
        if (
            index + 3 < array.length &&
            array[index] == 'n' &&
            array[index + 1] == 'u' &&
            array[index + 2] == 'l' &&
            array[index + 3] == 'l'
        ) {
            index += 4;
            
            return new Token(Token.NULL, begin, index);
        }
        
        throw new RuntimeException();
    }

    private Token getToken() {
        while (index < array.length) {
            byte x = array[index];

            if (x == ' ' || x == '\t' || x == '\r' || x == '\n') {
                index += 1;
            } else {
                break;
            }
        }

        if (index == array.length)
            return new Token(Token.END);

        if (array[index] == '{') {
            index += 1;
            return new Token(Token.OBJECT_ENABLE);
        }

        if (array[index] == '}') {
            index += 1;
            return new Token(Token.OBJECT_DISABLE);
        }

        if (array[index] == '[') {
            index += 1;
            return new Token(Token.ARRAY_ENABLE);
        }

        if (array[index] == ']') {
            index += 1;
            return new Token(Token.ARRAY_DISABLE);
        }

        if (array[index] == ':') {
            index += 1;
            return new Token(Token.COLON);
        }

        if (array[index] == ',') {
            index += 1;
            return new Token(Token.COMMA);
        }

        if (array[index] == '"')
            return getStringToken();

        if (array[index] == '-' || array[index] >= '0' && array[index] <= '9')
            return getNumberToken();
        
        if (array[index] == 't' || array[index] == 'f')
            return getBooleanToken();
        
        if (array[index] == 'n')
            return getNULLToken();

        throw new RuntimeException();
    }

    public List<Token> getTokenList() {
        if (list.size() != 0)
            return list;

        do {
            list.add(getToken());
        } while (list.get(list.size() - 1).getKey() != Token.END);

        return list;
    }

}
