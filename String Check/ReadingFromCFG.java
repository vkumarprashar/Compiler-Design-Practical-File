/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StringCheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;


/**
 *
 * @author Sai Ji
 */
public class ReadingFromCFG {
    static Scanner scan=new Scanner(System.in);
    static BufferedReader input = new BufferedReader (new InputStreamReader (System.in));
    int steps=1;
    public void print(String input){
        System.out.println(steps+" \t "+input);
        steps++;
    }
    public String getmatch(Grammar g,String input)
    {
        ArrayList<Rule> ar=g.getRules();
        Iterator it=ar.iterator();
        while (it.hasNext()) {
            Rule r=(Rule)it.next();
            if(input.trim().equals(r.righSidetoString().trim()))
            {
                String str=r.leftSidetoString().trim();
                return str;
            }
            
        }
        return "false";
    }
    public void check(ArrayList<String> words, Grammar g)
    {
        int index=0,tempindex=0;
        
        ArrayList<String> temp= new ArrayList<String>(words);
        Stack<String> stack=new Stack<String>();
        
        while(index<words.size())
        {
            String nextinput=words.get(index);
            String a=getmatch(g, nextinput);
            if(!a.equals("false"))
            {
                stack.add(a);
                temp.set(tempindex, a);
            }
            else
            {
                stack.add(nextinput);
            }
            print(temp.toString());
            
            String ss="";
            Iterator ii=stack.iterator();
            while(ii.hasNext())
            {
                ss=ss+ii.next();
            }
            
            String aa=getmatch(g, ss);
            if(!aa.equals("false"))
            {
                stack.remove(" ");
                int flag=0;
                for(int i=0;i<tempindex;i++)
                {
                    if(temp.get(i)==stack.get(i))
                        flag++;
                }
                for(int i=flag;i>=0;i--)
                {
                    temp.remove(i);
                    tempindex--;
                }
                stack.removeAllElements();
                stack.add(aa);                
                
                temp.add(0, aa);
                tempindex++;
                print(temp.toString());   
                
            }

            stack.add(" ");
            index++;
            tempindex++;
        }
        
        if(stack.get(0).equals(g.getStartVariable()))
        {
            System.out.println("STRING IS ACCEPTED BY THE GIVEN CFG");
        }
        else{
            System.out.println("STRING IS NOT ACCEPTED BY THE GIVEN CFG");
        }
         
    }
    
    public void rmd(ArrayList<String> words, Grammar g)
    {
        int index=words.size()-1,tempindex;
        tempindex=index;
        ArrayList<String> temp= new ArrayList<String>(words);
        Stack<String> stack=new Stack<String>();
        while(index>=0)
        {
            String nextinput=words.get(tempindex);
            String a=getmatch(g, nextinput);
            if(!a.equals("false"))
            {
                stack.add(a);
                temp.set(tempindex, a);
            }
            else
            {
                stack.add(nextinput);
            }
            print(temp.toString());
            
            String ss="";
            Iterator ii=stack.iterator();
            while(ii.hasNext())
            {
                ss=ss+ii.next();
            }
            
            String aa=getmatch(g, ss);
            if(!aa.equals("false"))
            {
                stack.remove(" ");
                int flag=0;
                for(int i=(temp.size()-1),j=0;i>=tempindex;i--,j++)
                {
                    if(temp.get(i)==stack.get(j))
                        flag++;
                }
                //problem is here what to do
                int ab=temp.size()-1-flag;
                for(int i=temp.size()-1;i>=ab;i--)
                {
                    temp.remove(i);
                    tempindex--;
                }
                if(tempindex<0)
                    tempindex=0;
                stack.removeAllElements();
                stack.add(aa);                
                temp.add(temp.size(), aa);
                tempindex=temp.size()-1;
                print(temp.toString());   
                
            }
            stack.add(" ");
            index--;
            tempindex--;
        }
        
        if(temp.get(0).equals(g.getStartVariable()))
        {
            System.out.println("STRING IS ACCEPTED BY THE GIVEN CFG");
        }
        else
        {
            System.out.println("STRING IS NOT ACCEPTED BY THE GIVEN CFG");
        }

        
    }
    
    public static void main(String[] args) throws IOException {
        ReadingFromCFG rfc=new ReadingFromCFG();
        int np,i=0;
        System.out.print("Enter the number of production rules:- ");
        np=scan.nextInt();
        
        String program="";
        while(i!=np){
            program=program+input.readLine();
            program=program +"\n";
            i++;
        }
        System.out.println("String "+ program); 
        Grammar g=new Grammar(program);
        System.out.print("Enter the String to check: ");
        String str = input.readLine();
        ArrayList<String> words = new ArrayList<>();
        String[] split = str.trim().split("\\s+");
        for(String s: split){
            words.add(s);
        }
        
        System.out.println("Left Most Derivation (LMD) for the given string to check whether it is accepted by the given CFG or not?");
        System.out.println("Steps \t Input");
        System.out.println("0 \t "+str);
        rfc.check(words,g);
        rfc.steps=0;
        System.out.println("Right Most Derivation (RMD) for the given string to check whether it is accepted by the given CFG or not?");
        System.out.println("Steps \t Input");
        System.out.println("0 \t "+str);
        rfc.rmd(words, g);
    }
    
}
