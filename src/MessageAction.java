import java.io.PrintWriter;

public class MessageAction extends Action {

	public static PrintWriter msgStream;

	public MessageAction(String s) {
		super(s);
	}

	@Override
	public void execute(Expression x) {
		msgStream.println(getSentence(x));
		System.out.println("** messages.txt <- "+getSentence(x)+"**");
	}
	/**
	 * Sprava sama o sebe nema nikdy zmysel
	 */
	@Override
	public boolean makesSense(Expression x) {return false;}
	
	@Override
	public void print(Expression x) {
		System.out.print("sprava "+getSentence(x));
	}
}
