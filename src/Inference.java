import java.util.LinkedList;

public class Inference {
	public static final int LIMIT=25;
	public static LinkedList<Rule> rules_base;
	public static LinkedList<Expression> facts_base;
	/**
	 * Algoritmus inferencie dopredneho produkcneho systemu
	 */
	public Inference() {
		for(int i=0; i<LIMIT; i++) {
			LinkedList<Binding> allInstances = new LinkedList<Binding>();
			allInstances = generateAllInstances();
			
			System.out.println("\n"+i);
			printFactsBase();
			filterInstances(allInstances);
			System.out.println("\n");
			if(allInstances.isEmpty()) return;
			allInstances.get(0).executeActions();
		}
	}
	/**
	 * 
	 */
	private void printFactsBase() {
		for(Expression x : facts_base) {
			System.out.println(x.words);
		}
		System.out.print("----------------------------------");
	}
	/**
	 * Odfiltruje zbytocne instancie pravidiel
	 */
	private void filterInstances(LinkedList<Binding> allInstances) {
		System.out.println();
		for (int i = 0; i < allInstances.size(); i++) {
			if(allInstances.get(i).needFilter()) {
				allInstances.remove(i);
				i--;
				//System.out.println("X ");
			}
			else {
				allInstances.get(i).printActions();
			}
		}
	}

	/**
	 * Vytvor zoznam všetkých aplikovateľných inštancií pravidiel
	 */
	private LinkedList<Binding> generateAllInstances() {
		LinkedList<Binding> instances = new LinkedList<Binding>();
		for(Rule r : rules_base) {
			instances.addAll(r.findAllBindings());															
		}
		return instances;
		
	}

}
