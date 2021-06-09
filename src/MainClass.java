import java.util.ArrayList;

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
        parser.validate(list);
    }
}
