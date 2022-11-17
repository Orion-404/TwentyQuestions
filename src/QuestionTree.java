import java.io.PrintStream;
import java.util.*;

//combination of logic from QuestionMain and QuestionGame
public class QuestionTree
{
    private QuestionNode root;
    private final Scanner console;
    
    public QuestionTree()
    {
        root = new QuestionNode( "computer" );
        console = new Scanner( System.in );
    }
    
    public void read( Scanner input )
    {
        while ( input.hasNextLine() )
        {
            root = readHelper( input );
        }
    }
    
    private QuestionNode readHelper( Scanner input )
    {
        String type = input.nextLine();
        String data = input.nextLine();
        QuestionNode root = new QuestionNode( data );
        
        if ( type.contains( "Q:" ) )
        {
            root.yesNode = readHelper( input );
            root.noNode = readHelper( input );
        }
        return root;
    }
    
    public void write( PrintStream output )
    {
        if ( output == null )
        {
            throw new IllegalArgumentException();
        }
        writeTree( root, output );
    }
    
    private void writeTree( QuestionNode root, PrintStream output )
    {
        if ( root.isAnswer() )
        {
            output.println( "A: " );
            output.println( root.value() );
        }
        else
        {
            output.println( "Q: " );
            output.println( root.value() );
            writeTree( root.yesNode, output );
            writeTree( root.noNode, output );
        }
    }
    
    public void askQuestions()
    {
        root = askQuestions( root );
    }
    
    private QuestionNode askQuestions( QuestionNode curr )
    {
        if ( curr.isAnswer() )
        {
            if ( yesTo( "Is your object " + curr.value() ) )
            {
                System.out.println( "I win!" );
            }
            else
            {
                System.out.print( "What is the name of your object? " );
                QuestionNode answer = new QuestionNode( console.nextLine() );
                System.out.println( "Please give me a yes/no question that" );
                System.out.println( "distinguishes between your object" );
                System.out.print( "and mine-> " );
                String question = console.nextLine();
                if ( yesTo( "And what is the answer for your object?" ) )
                {
                    curr = new QuestionNode( answer, question, curr );
                }
                else
                {
                    curr = new QuestionNode( curr, question, answer );
                }
            }
        }
        else
        {
            if ( yesTo( curr.value() ) )
            {
                curr.yesNode = askQuestions( curr.yesNode );
            }
            else
            {
                curr.noNode = askQuestions( curr.noNode );
            }
        }
        return curr;
    }
    
    public boolean yesTo( String prompt )
    {
        System.out.print( prompt + " (y/n)? " );
        String response = console.nextLine().trim().toLowerCase();
        while ( !response.equals( "y" ) && !response.equals( "n" ) )
        {
            System.out.println( "Please answer y or n." );
            System.out.print( prompt + " (y/n)? " );
            response = console.nextLine().trim().toLowerCase();
        }
        return response.equals( "y" );
    }
    
}
