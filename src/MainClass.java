public class MainClass
{
    public static void main(String[] args)
    {
       String input="void main(){int a; a=10; int b; b=6; b+a; cout<<a;}";
       LexicalAnalyzer analyzer=new LexicalAnalyzer();
       analyzer.validate(input).forEach((k,v)->System.out.println(k+" is "+v));
    }
}
