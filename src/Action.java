public abstract class Action {
	
	public  Expression actionExpression;
	public Action(String s) {
		actionExpression = new Expression(s);
	}
	/**
	 * Vykonanie akcie
	 */
	public abstract void execute(Expression x);
	/**
	 * Otestuje ci vykonanie akcie dava zmysel: neopakuje fakty/nemaze to co nie je/sprava
	 */
	public abstract boolean makesSense(Expression x);
	/**
	 * Vrati retazec z vyrazu
	 */
	public String getSentence(Expression x) {
		StringBuilder sb = new StringBuilder();
		for(String s : x.words) {
			sb.append(s);
			sb.append(" ");
		}
		return sb.toString();
	}
	/**
	 * Vypisanie akcie
	 */
	public abstract void print(Expression x); 
		

}
