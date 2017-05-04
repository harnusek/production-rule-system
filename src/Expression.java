import java.util.Iterator;
import java.util.LinkedList;

public class Expression {

	LinkedList<String> words;
	
	/**
	 * Vytvori logicky vyraz
	 */
	public Expression(String s) {									//??zombie
		words = new LinkedList<String>();
		for(String w : s.split(" ")) {
			words.add(w);
			//System.out.print(w);
		}//System.out.println();
	}
	public Expression() {
		words = new LinkedList<String>();
	}
	/**
	 * Porovna ci jeden vyraz prislucha druhemu
	 */
	public boolean isMatching(Expression exp) {
		if(this.words.size() != exp.words.size()) return false;
		
		Iterator<String> a = this.words.iterator();
		Iterator<String> b = exp.words.iterator();
		while (a.hasNext() && b.hasNext()) {
		    String x = a.next();
		    String y = b.next();
		    if(x.equals(y)==false && (x.charAt(0)!='?' && y.charAt(0)!='?') ) return false;
		}
		return true;
	}
	/**
	 * Porovna či sú vyrazy totožne
	 */
	public boolean isIdentical(Expression exp) {
		if(this.words.size() != exp.words.size()) return false;
		
		Iterator<String> a = this.words.iterator();
		Iterator<String> b = exp.words.iterator();
		while (a.hasNext() && b.hasNext()) {
		    String x = a.next();
		    String y = b.next();
		    if(x.equals(y)==false) return false;
		}
		return true;
	}
	
	public int size() {
		return this.words.size();
	}
}	
