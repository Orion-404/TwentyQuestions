public class QuestionNode
{
    
    public QuestionNode yesNode;
    private String value;
    public QuestionNode noNode;
    
    public QuestionNode( QuestionNode yesNode, String value, QuestionNode noNode )
    {
        this.value = value;
        this.yesNode = yesNode;
        this.noNode = noNode;
    }
    
    public QuestionNode( String data ) { this( null, data, null ); }
    
    public String value() { return value; }
    
    public void setValue( String v )
    {
        value = v;
    }
    
    public QuestionNode yesNode()
    {
        return yesNode;
    }
    
    public QuestionNode noNode()
    {
        return noNode;
    }
    
    public boolean isAnswer()
    {
        return noNode == null && yesNode == null;
    }
    
}