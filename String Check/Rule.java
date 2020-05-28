package StringCheck;


import java.util.Arrays;
import java.util.Objects;

public class Rule {

    protected String leftSide;
    protected String[] rightSide;

    public Rule(String leftSide, String[] rightSide) {
        this.rightSide = rightSide;
        this.leftSide = leftSide;
    }

    public Rule(Rule rule) {
        this.leftSide = rule.getLeftSide();
        this.rightSide = rule.rightSide.clone();
    }
    
    public String getLeftSide() {
        return leftSide;
    }

    public String[] getRightSide() {
        return rightSide;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.leftSide);
        hash = 29 * hash + Arrays.deepHashCode(this.rightSide);
        return hash;
    }

    
    @Override
    public String toString() {
        String str = leftSide + " -> ";
        for (int i = 0; i < rightSide.length; i++) {
            str += rightSide[i] + " ";
        }
        return str;
    }
     public String righSidetoString() {
        String str="";
        for (int i = 0; i < rightSide.length; i++) {
            str += rightSide[i] + " ";
        }
        return str;
    }
    public String leftSidetoString() {
        String str = leftSide ;
        return str;
    }

}
