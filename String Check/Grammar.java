package StringCheck;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class Grammar {

    private ArrayList<Rule> rules;
    private HashSet<String> terminals;
    private HashSet<String> variables;
    private String startVariable;


    public Grammar(String s) {
        rules = new ArrayList<>();
        terminals = new HashSet<>();
        variables = new HashSet<>();
        int line = 0;
        for(String st : s.split("\n")){
            String[] sides = st.split("->");
            String leftSide = sides[0].trim();
            variables.add(leftSide);
            String[] rulesRightSide = sides[1].trim().split("\\|");
            for (String rule : rulesRightSide) {
                String[] rightSide = rule.trim().split("\\s+");
                for (String terminal : rightSide) {
                    if (!terminal.equals("epsilon")) {
                        terminals.add(terminal);
                    }
                }
                if (line == 0) {
                    startVariable = leftSide;
                }
                rules.add(new Rule(leftSide, rightSide));
                line++;
            }
        }
        for (String variable : variables) {
            terminals.remove(variable);
        }
        System.out.println("Rules: ");
        for (int i=0 ; i<rules.size() ; i++) {
            System.out.println(i+" : " +rules.get(i));
        }

    }

    public ArrayList<Rule> getRules() {
        return rules;
    }
   
    public HashSet<String> getVariables() {
        return variables;
    }

    public String getStartVariable() {
        return startVariable;
    }
 
    public boolean isVariable(String s) {
        return variables.contains(s);
    }
    
    public HashSet<String> getTerminals() {
        return terminals;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.rules);
        hash = 37 * hash + Objects.hashCode(this.terminals);
        hash = 37 * hash + Objects.hashCode(this.variables);
        return hash;
    }

   
    @Override
    public String toString() {
        String str = "";
        for(Rule rule: rules){
            str += rule + "\n";
        }
        return str;
    }
}
