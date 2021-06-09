package Entitiy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Grammer
{
    private String LHS;
    private String RHS;




    public Grammer()
    {
        LHS = "";
        RHS = "";

    }

    public Grammer(String LHS, String RHS)
    {
        this.LHS = LHS;
        this.RHS = RHS;


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

    public ArrayList<String> getProductions ()
    {
        ArrayList<String> ans =new ArrayList<>();
        StringTokenizer tokenizer=new StringTokenizer(RHS, " ");
        while (tokenizer.hasMoreTokens())
        {
            ans.add(tokenizer.nextToken());
        }
        Collections.reverse(ans);
        return ans;
    }



}
