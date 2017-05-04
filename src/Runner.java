import java.io.*;
import java.util.LinkedList;

public class Runner {

	private static final String[] DOMAIN = {"family","vehicle"};
	
	public static void main(String[] args) throws IOException {		
		int index = Integer.valueOf(args[0]);											index=0;

		Inference.rules_base = readRules(DOMAIN[index]);
		Inference.facts_base = readFacts(DOMAIN[index]);
		MessageAction.msgStream = openMessages(DOMAIN[index]);
		new Inference();
		MessageAction.msgStream.close();
	}
	/**
	 * Otvori subor pre spravy
	 */
	private static PrintWriter openMessages(String domain) throws IOException {
	    PrintWriter writer = new PrintWriter(domain+"/messages.txt", "UTF-8");
		return writer;
	}
	/**
	 * Nacita zo suboru a vrati fakty
	 */
	private static LinkedList<Expression> readFacts(String domain) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(domain+"/facts.txt")));
		String line = null;
		LinkedList<Expression> facts = new LinkedList<Expression>();
		while ((line = r.readLine()) != null) {
			Expression x = new Expression(line);
			facts.add(x);
		}
		r.close();
		return facts;
	}
	/**
	 * Nacita zo suboru a vrati pravidla
	 * @param domain 
	 */
	private static LinkedList<Rule> readRules(String domain) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(domain+"/rules.txt")));
		String line = null;
		LinkedList<Rule> rules = new LinkedList<Rule>();
		while ((line = r.readLine()) != null) {
			Rule rule = new Rule();
			
			rule.setName(line.substring(6));
			
	        line = r.readLine();
	        rule.setConditions(line.substring(6).split(","));
	        
	        line = r.readLine();
	        rule.setActions(line.substring(6).split(","));
	        
	        line = r.readLine();
	        
	        rules.add(rule);
		}
		r.close();
		
		return rules;
	}

}
