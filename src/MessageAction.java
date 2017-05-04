import java.io.PrintWriter;

public class MessageAction extends Action {

	public static PrintWriter msgStream;
	/**
	 * 
	 */
	public MessageAction(String s) {
		super(s);
	}

	@Override
	public void execute(Expression x) {
		sentence = getSentence(x);
		msgStream.println(sentence);
		System.out.println("***MSG "+sentence);
	}
	/**
	 * Sprava sama o sebe nema nikdy zmysel
	 */
	@Override
	public boolean makesSense(Expression x) {return false;}
	
	@Override
	public void print(Expression x) {
		sentence = getSentence(x);
		System.out.print("sprava "+sentence);
	}
}
