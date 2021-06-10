import java.util.ArrayList;

public class MainClass
{
    public static void main(String[] args)
    {
        String input = "void main(){int a; cin>>a; cout<<a; int b; cin>>b; int c; c=a+b; cout<<c;}";
        LexicalAnalyzer analyzer = new LexicalAnalyzer();
        ArrayList<String>ansl=analyzer.validate(input);
        System.out.println("output of the lexical analyzer");
        System.out.println(ansl.toString());

        LL1Parser parser=new LL1Parser();

        System.out.println("output of the LL1 parser");
        ArrayList<String> ans=parser.validate(ansl);
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
