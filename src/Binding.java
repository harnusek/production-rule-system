import java.util.HashMap;
import java.util.Iterator;

public class Binding {

	public Rule rule;
	public HashMap<String, String> vars = new HashMap<String, String>();
	

	public Binding(Rule rule, HashMap<String, String> vars) {
		this.rule = rule;
		this.vars = vars;
	}
	/**
	 * Vykona akcie pravidla
	 */
	void executeActions() {
		for(Action a : rule.actions) {
			Expression x = replaceVariables(a.actionExpression);
			a.execute(x);
		}
	}
	/**
	 * Vrati vyraz v ktorom su nezname nahradene premennymi
	 */
	private Expression replaceVariables(Expression actionExpression) {
		Expression binded = new Expression();
		Iterator<String> a = actionExpression.words.iterator();
		while (a.hasNext()) {
		    String wA = a.next();
		    String wB;
		    if(wA.charAt(0) == '?') {
		    	wB = vars.get(wA);
		    }
		    else {
		    	wB = wA;
		    }
		    binded.words.add(wB);
		}

		return binded;
	}
	/**
	 * Vypise instanciu s jej vsetkymi akciami
	 */
	public void printActions() {
		System.out.print(rule.name);
		for(Action a : rule.actions) {
			Expression x = replaceVariables(a.actionExpression);
			System.out.print(", ");
			a.print(x);
		}
		System.out.println();
	}
	/**
	 * Zisti ci je instanciu vhodne pri filtrovani vymazat
	 */
	public boolean needFilter() {
		boolean makesSence=false;
		for(Action a : rule.actions) {
			Expression x = replaceVariables(a.actionExpression);
			makesSence = (a.makesSense(x) || makesSence);
		}
		return !makesSence;
	}

}
