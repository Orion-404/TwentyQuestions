import java.io.PrintStream;
import java.util.*;

//combination of logic from QuestionMain and QuestionGame
public class QuestionTree
{
    private QuestionNode root;
    private Scanner console;
    
    public QuestionTree()
    {
        root = new QuestionNode( "computer" );
        console = new Scanner( System.in );
    }
    
    public void read(Scanner input)
    {
        while ( input.hasNextLine() )
        {
            root = readHelper(input);
        }
    }
    
    private QuestionNode readHelper( Scanner input )
    {
        String type = input.nextLine();
        String data = input.nextLine();
        QuestionNode root = new QuestionNode( data );
        
        if(type.contains( "Q:" ))
        {
            root.yesNode = readHelper( input );
            root.noNode = readHelper( input );
        }
        return root;
    }
    
    public void write( PrintStream output )
    {
        if(output == null)
        {
            throw new IllegalArgumentException();
        }
        writeTree( root, output );
    }
    
    private void writeTree( QuestionNode root, PrintStream output )
    {
        if( root.isAnswer() )
        {
            output.println("A: ");
            output.println(root.value());
        }
        else
        {
            output.println("Q: ");
            output.println(root.value());
            writeTree( root.yesNode, output );
            writeTree( root.noNode, output );
        }
    }
    
    public void askQuestions()
    {
        root = askQuestions(root);
    }
    
    private QuestionNode askQuestions(QuestionNode curr)
    {
        if()
    }
}
