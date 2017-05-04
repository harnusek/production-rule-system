import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Rule {
	public String name;
	public LinkedList<Expression> conditions = new LinkedList<Expression>();
	public LinkedList<Action> actions = new LinkedList<Action>();
	
	/**
	 * Nastavi men pravidla
	 */
	public void setName(String name) {
		this.name = name;	
	}
	/**
	 * Nastavi podmienky pravidla
	 */
	public void setConditions(String[] conditions) {
		for(String s : conditions) {
			this.conditions.add(new Expression(s));
		}
		
	}
	/**
	 * Nastavi akcie pravidla
	 */
	public void setActions(String[] actions) {
		for(String s : actions) {
			String type = s.split(" ")[0];
			String x = s.substring(s.indexOf(' ')+1);
			if(type.equals("pridaj")) {
				this.actions.add(new AddAction(x));
			}
			else if(type.equals("vymaz")) {
				this.actions.add(new DeleteAction(x));
			}
			else if(type.equals("sprava")) {
				this.actions.add(new MessageAction(x));
			}
		}
	}
	/**
	 * Najde vsetky instancie pravidla: naviazania premennych na pravidlo
	 */
	public LinkedList<Binding> findAllBindings() {
		LinkedList<Binding> allBindings = new LinkedList<Binding>();
		for(Expression fact : Inference.facts_base) {
			if(fact.isMatching(conditions.getFirst())) {
				HashMap<String, String> vars = new HashMap<String, String>();
				setVariables(vars, fact, 0);
				if(solveBinding(vars, 0)) {
					allBindings.add(new Binding(this, vars));
				}																
			}
		}
		return allBindings;
	}
	/**
	 * Rekurzivne prechadza elementarne podmienky a nacita premenne
	 */
	private boolean solveBinding(HashMap<String, String> vars, int i) {
		i++;
		if(i>=conditions.size()) {
			return true;
		}
		for(Expression fact : Inference.facts_base) {
			if(setVariables(vars, fact, i)) {
				return solveBinding(vars, i);
			}
		}
		return false;
	}
	/**
	 * Naviaze premenne. Vrati uspesnost naviazania premennych.
	 */
	private boolean setVariables(HashMap<String, String> vars, Expression fact, int i) {
		Iterator<String> factI = fact.words.iterator();
		Iterator<String> condI = conditions.get(i).words.iterator();
		//Specialna podmienka
		if(conditions.get(i).words.get(0).equals("<>")) {
			return areUnique(vars, this.conditions.get(i));
		}
		
		while(factI.hasNext() && condI.hasNext()) {
			String f = factI.next();
			String c = condI.next();

			if(c.charAt(0) == '?') {			//premenna
				if(vars.containsKey(c)) {
					if(vars.get(c).equals(f) == false) return false;//nezhoduju sa v premennych
				}
				else {
					vars.put(c, f);
				}
			}
			else if(c.equals(f) == false) {		//nezhoduju sa v syntaxzy
				return false;
			}
		}
		return true;
	}
	/**
	 * Zisti ci maju dve premenne rovnaky obsah
	 */
	private boolean areUnique(HashMap<String, String> vars, Expression x) {
		String name1 = vars.get(x.words.get(1));
		String name2 = vars.get(x.words.get(2));
		return !name1.equals(name2);
	}
}
