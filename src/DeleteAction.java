import java.util.LinkedList;

public class DeleteAction extends Action {

	public DeleteAction(String s) {
		super(s);
	}
	
	@Override
	public void execute(Expression x) {
		if(makesSense(x) == false) return;
		for (int i = 0; i < Inference.facts_base.size(); i++) {
			if(Inference.facts_base.get(i).isIdentical(x)) {
				Inference.facts_base.remove(i);
				i--;
			}
		}
		System.out.print("DELETE "+getSentence(x));
		
	}
	/**
	 * Kontrola ci sa fakt vobec nachadza v baze faktov
	 */
	@Override
	public boolean makesSense(Expression x) {
		for(Expression fact : Inference.facts_base) {
			if(fact.isIdentical(actionExpression)) return true;
		}

		return false;
	}
	
	@Override
	public void print(Expression x) {
		System.out.println("vymaz "+getSentence(x));
	}
}
