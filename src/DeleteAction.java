import java.util.LinkedList;

public class DeleteAction extends Action {

	public DeleteAction(String s) {
		super(s);
	}
	
	@Override
	public void execute(Expression x) {
		if(makesSense(x) == false) return;
		LinkedList<Expression> facts_base = Inference.facts_base;
		for (int i = 0; i < facts_base.size(); i++) {
			if(facts_base.get(i).isIdentical(x)) {
				facts_base.remove(i);
			}
		}
		sentence = getSentence(x);
		System.out.print("DELETE "+sentence);
		
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
		sentence = getSentence(x);
		System.out.println("vymaz "+sentence);
	}
}
