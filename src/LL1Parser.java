import Entitiy.Grammer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LL1Parser
{
    private Grammer[][] table;

    public LL1Parser()
    {
        populateParseTable();
    }

    private void populateParseTable()
    {
        Grammer g1 = new Grammer("cprogram", "void main ( ) { stmtset }", (HashMap<String, String>) Map.of("stmtset", "stmtset"));
        Grammer g2 = new Grammer("stmtset", "stmt ; stmtset' ", (HashMap<String, String>) Map.of("stmt", "stmt", "stmtset'", "stmtset'"));
        Grammer g3 = new Grammer("stmt", "decstmt", (HashMap<String, String>) Map.of("decstmt", "decstmt"));
        Grammer g4 = new Grammer("stmt", "AS", (HashMap<String, String>) Map.of("AS", "AS"));
        Grammer g5 = new Grammer("stmt", "coutstmt", (HashMap<String, String>) Map.of("coutstmt", "coutstmt"));
        Grammer g6 = new Grammer("stmt", "cinstmt", (HashMap<String, String>) Map.of("cinstmt", "cinstmt"));
        Grammer g7 = new Grammer("decstmt", "type idset", (HashMap<String, String>) Map.of("type", "type", "idset", "idset"));
        Grammer g8 = new Grammer("type", "int");
        Grammer g9 = new Grammer("idset", "id idset'", (HashMap<String, String>) Map.of("idset'", "idset'"));
        Grammer g10 = new Grammer("AS", "id = E", (HashMap<String, String>) Map.of("E", "E"));
        Grammer g11 = new Grammer("E", "T E'", (HashMap<String, String>) Map.of("T", "T", "E'", "E'"));
        Grammer g12 = new Grammer("T", "F T'", (HashMap<String, String>) Map.of("F", "F", "T'", "T'"));
        Grammer g13 = new Grammer("F", "( E )", (HashMap<String, String>) Map.of("E", "E"));
        Grammer g14 = new Grammer("F", "id");
        Grammer g15 = new Grammer("F", "intleteral");
        Grammer g16 = new Grammer("coutstmt", "cout < < id");
        Grammer g17 = new Grammer("cinstmt", "cin > > id");
        Grammer g18 = new Grammer("E'", "* T E'", (HashMap<String, String>) Map.of("T", "T", "E'", "E'"));
        Grammer g19 = new Grammer("E'", "!");
        Grammer g20 = new Grammer("T'", "+ F T'", (HashMap<String, String>) Map.of("F", "F", "T'", "T'"));
        Grammer g21 = new Grammer("T'", "!");
        Grammer g22 = new Grammer("stmtset'", "!");
        Grammer g23 = new Grammer("stmtset'", "stmtset", (HashMap<String, String>) Map.of("stmtset", "stmtset"));
        Grammer g24 = new Grammer("idset'", "!");
        Grammer g25 = new Grammer("idset'", ", idset", (HashMap<String, String>) Map.of("idset", "idset"));

        table = new Grammer[][]{
                /*          void    main    id  (     )   intlit  *    +     ;    cout  cin    int    <    >    ,      $      }       {   */
                /*Cprogram*/{g1, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                /*AS*/      {null, null, g10, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                /*E*/       {null, null, g11, g11, null, g11, null, null, null, null, null, null, null, null, null, null, null, null},
                /*E'*/      {null, null, null, null, g19, null, g18, null, g19, null, null, null, null, null, null, null, null, null},
                /*F*/       {null, null, g14, g13, null, g15, null, null, null, null, null, null, null, null, null, null, null, null},
                /*T*/       {null, null, g12, g12, null, g12, null, null, null, null, null, null, null, null, null, null, null, null},
                /*T'*/      {null, null, null, null, g21, null, g21, g20, g21, null, null, null, null, null, null, null, null, null},
                /*cinstmt*/ {null, null, null, null, null, null, null, null, null, null, g17, null, null, null, null, null, null, null},
                /*coutstmt*/{null, null, null, null, null, null, null, null, null, g16, null, null, null, null, null, null, null, null},
                /*decstmt*/ {null, null, null, null, null, null, null, null, null, null, null, g7, null, null, null, null, null, null},
                /*idset*/   {null, null, g9, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                /*idset' */ {null, null, null, null, null, null, null, null, g24, null, null, null, null, null, g25, null, null, null},
                /*stmt*/    {null, null, g4, null, null, null, null, null, null, g5, g6, g3, null, null, null, null, null, null},
                /*stmtset*/ {null, null, g2, null, null, null, null, null, null, g2, g2, g2, null, null, null, null, null, null},
                /*stmtset'*/{null, null, g23, null, null, null, null, null, null, g23, g23, g23, null, null, null, null, g22, null},
                /*type*/    {null, null, null, null, null, null, null, null, null, null, null, g8, null, null, null, null, null, null}

        };
    }

    private int xAxis(String str)
    {
        return switch (str)
                {
                    case "void" -> 0;
                    case "main" -> 1;
                    case "id" -> 2;
                    case "(" -> 3;
                    case ")" -> 4;
                    case "intlit" -> 5;
                    case "*" -> 6;
                    case "+" -> 7;
                    case ";" -> 8;
                    case "cout" -> 9;
                    case "cin" -> 10;
                    case "int" -> 11;
                    case "<" -> 12;
                    case ">" -> 13;
                    case "," -> 14;
                    case "$" -> 15;
                    case "}" -> 16;
                    case "{" -> 17;
                    default -> -1;
                };
    }

    private int yAxis(String str)
    {
        return switch (str)
                {
                    case "cprogram" -> 0;
                    case "AS" -> 1;
                    case "E" -> 2;
                    case "E'" -> 3;
                    case "F" -> 4;
                    case "T" -> 5;
                    case "T'" -> 6;
                    case "cinstmt" -> 7;
                    case "coutstmt" -> 8;
                    case "decstmt" -> 9;
                    case "idset" -> 10;
                    case "idset'" -> 11;
                    case "stmt" -> 12;
                    case "stmtset" -> 13;
                    case "stmtset'" -> 14;
                    case "type'" -> 15;
                    default -> -1;

                };
    }

    public void validate(ArrayList<String> list)
    {
        Stack<String> s = new Stack<>();
        s.push("cprogram");
        boolean flag = false;
        String inputPointer, topOfStack;
        int pointerNumber = 0;
        int i, j;
        while (true)
        {
            inputPointer = list.get(pointerNumber);
            topOfStack = s.peek();
            i = xAxis(inputPointer);
            j = yAxis(topOfStack);
            if (i == -1 && j == -1)
            {
                break;
            }
            Grammer grammerReplace = table[i][j];

            if (grammerReplace == null)
            {
                break;
            }
            ArrayList<String>productions=grammerReplace.getProductions();
            for(int k=0;i<productions.size();k++)
            {
                s.push(productions.get(k));
            }
            while(true)
            {
                if (topOfStack.equals(inputPointer))
                {
                    s.pop();
                    pointerNumber++;
                }
                else
                {
                    break;
                }
            }
        }
        if (!flag)
        {
            System.out.println("error string rejected");
        }
        else
        {
            System.out.println("tokens accepted");
        }

    }


}
