import java.util.ArrayList;
import java.util.Collections;

public class MainClass
{
    public static void main(String[] args)
    {
//       String input="void main(){int a; a=10; int b; b=6; b+a; cout<<a;}";
//       LexicalAnalyzer analyzer=new LexicalAnalyzer();
//       analyzer.validate(input).forEach((k,v)->System.out.println(k+" is "+v));

        LL1Parser parser=new LL1Parser();
        ArrayList<String>list=new ArrayList<>();
        list.add("void");
        list.add("main");
        list.add("(");
        list.add(")");
        list.add("{");
        list.add("int");
        list.add("id");
        list.add(";");
        list.add("id");
        list.add("=");
        list.add("intlit");
        list.add(";");
        list.add("}");
        ArrayList<String> ans=parser.validate(list);
        if(ans!=null)
        {
            for(int i=0;i<ans.size();i++)
            {
                System.out.println(ans.get(i));
            }
        }
        else
        {
            System.out.println("invalid string for parser");
        }
    }
}
