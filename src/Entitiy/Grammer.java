package Entitiy;

import java.util.HashMap;

public class Grammer
{
    private String LHS;
    private String RHS;
    private HashMap<String, String> nonTerminals;



    public Grammer()
    {
        LHS = "";
        RHS = "";
        nonTerminals=new HashMap<>();
    }

    public Grammer(String LHS, String RHS)
    {
        this.LHS = LHS;
        this.RHS = RHS;
        nonTerminals = new HashMap<>();

    }

    public Grammer(String LHS, String RHS,HashMap<String, String> nonTerminals)
    {
        this.LHS = LHS;
        this.RHS = RHS;
        this.nonTerminals = nonTerminals;

    }


    public String getLHS()
    {
        return LHS;
    }

    public void setLHS(String LHS)
    {
        this.LHS = LHS;
    }

    public String getRHS()
    {
        return RHS;
    }

    public void setRHS(String RHS)
    {
        this.RHS = RHS;
    }

    public HashMap<String, String> getNonTerminals()
    {
        return nonTerminals;
    }

    public void setNonTerminals(HashMap<String, String> nonTerminals)
    {
        this.nonTerminals = nonTerminals;
    }

    public void addInNonTerminals(String s)
    {
        this.nonTerminals.put(s, s);
    }


}
