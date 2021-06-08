import java.util.HashMap;
import java.util.LinkedHashMap;

public class LexicalAnalyzer
{
    private int[][] transitionTable;
    private HashMap<String, String> keywords;
    private LinkedHashMap<String, String> ans;
    private int[] acceptingState;
    private String[] tokens;

    public LexicalAnalyzer()
    {
        populateTransitionTable();
        populateKeywords();
        populateAcceptingState();
        populateTokens();
        ans = new LinkedHashMap<>();
    }

    private void populateTokens()
    {
        tokens = new String[]{"", "", "id", "", "int lit", "+ operator", "= operator", "* operator", "", "<< operator", "", ">> operator", "( punctuation", ") punctuation", "; punctuation", "{ punctuation", "} punctuation"};
    }

    private void populateAcceptingState()
    {
        acceptingState = new int[]{-1, -1, 2, -1, 2, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1};
    }

    private void populateKeywords()
    {
        keywords = new HashMap<>();
        keywords.put("void", "void");
        keywords.put("main", "main");
        keywords.put("int", "int");
        keywords.put("cout", "cout");
        keywords.put("cin", "cin");
    }

    private void populateTransitionTable()
    {
        transitionTable = new int[][]
                {
                        {1, 3, 5, 7, 6, 8, 10, 12, 13, 14, 15, 16, 0, -1},//0
                        {1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, -1},//1
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},//2
                        {4, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, -1},//3
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},//4
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},//5
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},//6
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},//7
                        {0, 0, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, -1},//8
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},//9
                        {0, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, -1},//10
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},//11
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},//12
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},//13
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},//14
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},//15
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}//16
                };
    }

    public LinkedHashMap<String, String> validate(String input)
    {
        int state = 0;
        String tkn = "";
        for (int i = 0; i < input.length(); i++)
        {
            char chr = input.charAt(i);
            tkn += chr;

            int indx = index2(chr);
            state = transitionTable[state][indx];

            int acceptance = checkAcceptance(state);
            if (acceptance == 1)
            {
                ans.put(tkn, tokens[state]);
                tkn = "";
                state = 0;
            }
            else
            {
                if (acceptance == 2)
                {
                    if (state == 2)
                    {
                        //special case to recognize if the accepted token is ID or a keyword
                        if (keywords.containsKey(tkn.substring(0, tkn.length() - 1)))
                        {
                            ans.put(tkn.substring(0, tkn.length() - 1), "keyword");
                            tkn = "";
                            state = 0;
                            i--;
                        }
                        else
                        {
                            tkn = tkn.substring(0, tkn.length() - 1);
                            ans.put(tkn, tokens[state]);
                            tkn = "";
                            state = 0;
                            i--;
                        }
                    }
                    else
                    {
                        tkn = tkn.substring(0, tkn.length() - 1);
                        ans.put(tkn, tokens[state]);
                        state = 0;
                        tkn = "";
                        i--;
                    }
                }
            }
            if (tkn.equals(" ") || tkn.equals("\n"))
            {
                tkn = "";
            }
        }
        return ans;
    }

    private int checkAcceptance(int state)
    {
        return this.acceptingState[state];
    }

    private int index2(char ch)
    {
        if (isLetter(ch))
        {
            return 0;
        }
        else
        {
            if (isDigit(ch))
            {
                return 1;
            }
        }
        return switch (ch)
                {
                    case '+' -> 2;
                    case '*' -> 3;
                    case '=' -> 4;
                    case '<' -> 5;
                    case '>' -> 6;
                    case '(' -> 7;
                    case ')' -> 8;
                    case ';' -> 9;
                    case '{' -> 10;
                    case '}' -> 11;
                    default -> 12;
                };
    }

//    private int index(char ch)
//    {
//        if (isLetter(ch))
//        {
//            return 0;
//        }
//        else
//        {
//            if (isDigit(ch))
//            {
//                return 1;
//            }
//            else
//            {
//                if (ch == '+')
//                {
//                    return 2;
//                }
//                else
//                {
//                    if (ch == '*')
//                    {
//                        return 3;
//                    }
//                    else
//                    {
//                        if (ch == '=')
//                        {
//                            return 4;
//                        }
//                        else
//                        {
//                            if (ch == '<')
//                            {
//                                return 5;
//                            }
//                            else
//                            {
//                                if (ch == '>')
//                                {
//                                    return 6;
//                                }
//                                else
//                                {
//                                    if (ch == '(')
//                                    {
//                                        return 7;
//                                    }
//                                    else
//                                    {
//                                        if (ch == ')')
//                                        {
//                                            return 8;
//                                        }
//                                        else
//                                        {
//                                            if (ch == ';')
//                                            {
//                                                return 9;
//                                            }
//                                            else
//                                            {
//                                                if (ch == '{')
//                                                {
//                                                    return 10;
//                                                }
//                                                else
//                                                {
//                                                    if (ch == '}')
//                                                    {
//                                                        return 11;
//                                                    }
//                                                    else
//                                                    {
//                                                        return 12;
//                                                    }
//                                                }
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//    }

    private boolean isLetter(char ch)
    {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
    }

    private boolean isDigit(char ch)
    {
        return (ch >= '0' && ch <= '9');
    }


}
