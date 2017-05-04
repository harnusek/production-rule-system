

public class AddAction extends Action {

	public AddAction(String s) {
		super(s);
	}

	@Override
	public void execute(Expression x) {
		if(makesSense(x) == false) return;
		Inference.facts_base.add(x);
	}


	/**
	 * Kontrola ci sa rovnaky fakt uz nenachadza v baze faktov
	 */
	@Override
	public boolean makesSense(Expression x) {
		for(Expression fact : Inference.facts_base) {
			if(fact.isIdentical(x)) return false;
		}

		return true;
	}

	@Override
	public void print(Expression x) {
		System.out.print("pridaj "+getSentence(x));
	}



}
