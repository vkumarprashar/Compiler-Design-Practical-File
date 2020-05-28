package compiler.design;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class DFAdesigningFinal {
    static Scanner scan=new Scanner(System.in);
    static InputStreamReader reader=new InputStreamReader(System.in);
    static BufferedReader buffer=new BufferedReader(reader);
    static String[] alphabetarray=new String[2];
    static int[] states;
    static int transitiontable[][];
    static char acceptarray[];
    public static void prefix(int alphabetchoose, String accept) throws IOException
    {
        LinkedList<Integer> list=new LinkedList<>();
        LinkedList<Integer> listextra=new LinkedList<>();
        HashMap<Integer,String> map=new HashMap();
        HashMap<Integer,String> mapextra=new HashMap();
        if(alphabetchoose==1)
        {
            alphabetarray[0]="0";
            alphabetarray[1]="1";    
        }
        else{
            alphabetarray[0]="a";
            alphabetarray[1]="b";
        }
        states=new int[accept.length()+2];
        transitiontable =new int[accept.length()+2][2];
        acceptarray=new char[accept.length()];
        acceptarray=accept.toCharArray();
        for (int i=0;i<accept.length()+1;i++)
        {
            states[i]=i;
        }
        for(int i=0;i<accept.length();i++)
        {
            String temp=String.valueOf(acceptarray[i]);
            map.put(states[i], temp);
            list.add(i, i+1);
        }
        int i=0;
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            String value = entry.getValue();
            if (value.equals("a"))
            {
                mapextra.put(i, "b");
                listextra.add(i, accept.length()+1);
            }
            else if (value.equals("b"))
            {
                mapextra.put(i, "a");
                listextra.add(i, accept.length()+1);
            }
            if (value.equals("0"))
            {
                mapextra.put(i, "1");
                listextra.add(i, accept.length()+1);
            }
            else if (value.equals("1"))
            {
                mapextra.put(i, "0");
                listextra.add(i, accept.length()+1);
            }
            i++;
        }
        
        
        for(int j=0;j<accept.length();j++)
        {
            if(map.get(j).equals("a")||map.get(j).equals("0"))
            {
	 transitiontable[j][0]=list.get(j);
            }
            else if(map.get(j).equals("b")||map.get(j).equals("1"))
            {
                transitiontable[j][1]=list.get(j);
            }
            
            if(mapextra.get(j).equals("a")||mapextra.get(j).equals("0"))
            {
                transitiontable[j][0]=listextra.get(j);
            }
            else if(mapextra.get(j).equals("b")||mapextra.get(j).equals("1"))
            {
                transitiontable[j][1]=listextra.get(j);
            }
        }
        
        for(int j=accept.length();j<=accept.length()+1;j++)
        {
            for(int k=0;k<2;k++)
            {
                transitiontable[j][k]=j;
            }
        }
        printTransitiontable(accept, transitiontable);
        System.out.print("Do you Want to do the String Processing ?(yes / no)");
        String answer=buffer.readLine();
        if(answer.equalsIgnoreCase("yes"))
        {
            System.out.print("Enter the String to Test: -");
            String test=buffer.readLine();
            char testarray[]=test.toCharArray();
            int flag=0;
            for(int j=0;j<accept.length();j++)
            {
                if(testarray[j]!=acceptarray[j])
                {
                    flag=1;
                    break;
                }
            }
            int number=0;
            if(flag==0)
            {
                System.out.println("ACCEPTED");
                for(int k=0;k<test.length();k++)
                {
                    if(testarray[k]=='a'||testarray[k]=='0')
                    {
                        System.out.print("q"+number+"--"+testarray[k]+"->" );
                        number=transitiontable[number][0];
                    }
                    else
                    {
                        System.out.print("q"+number+"--"+testarray[k]+"->" );
                        number=transitiontable[number][1];
                    }
                }
                System.out.print("q"+number );
            }
            else
            {
                System.out.println("NOT ACCEPTED");
            }
        }
        else
        {
            System.out.println("Thank You");
        }
    }
    
    public static void substring(int alphabetchoose, String accept) throws IOException
    {
        LinkedList<Integer> list=new LinkedList<>();
        LinkedList<Integer> listextra=new LinkedList<>();
        HashMap<Integer,String> map=new HashMap();
        HashMap<Integer,String> mapextra=new HashMap();
        if(alphabetchoose==1)
        {
            alphabetarray[0]="0";
            alphabetarray[1]="1";    
        }
        else{
            alphabetarray[0]="a";
            alphabetarray[1]="b";
        }
        states=new int[accept.length()+1];
        transitiontable =new int[accept.length()+1][2];
        acceptarray=new char[accept.length()];
        acceptarray=accept.toCharArray();
        for (int i=0;i<accept.length()+1;i++)
        {
            states[i]=i;
        }
        for(int i=0;i<accept.length();i++)
        {
            String temp=String.valueOf(acceptarray[i]);
            map.put(states[i], temp);
            list.add(i, i+1);
        }
        int i=0;
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            String value = entry.getValue();
            if (value.equals("a"))
            {
                mapextra.put(i, "b");
                if(acceptarray[0]=='b')
                {
                    listextra.add(i, 1);
                }
                else
                {
                    listextra.add(i, 0);
                }
                
            }
            else if (value.equals("b"))
            {
                mapextra.put(i, "a");
                if(acceptarray[0]=='a')
                {
                    listextra.add(i, 1);
                }
                else
                {
                    listextra.add(i, 0);
                }
            }
            
            if (value.equals("0"))
            {
                mapextra.put(i, "1");
                if(acceptarray[0]=='1')
                {
                    listextra.add(i, 1);
                }
                else
                {
                    listextra.add(i, 0);
                }
                
            }
            else if (value.equals("1"))
            {
                mapextra.put(i, "0");
                if(acceptarray[0]=='0')
                {
                    listextra.add(i, 1);
                }
                else
                {
                    listextra.add(i, 0);
                }
            }
            i++;
        }
        for(int j=0;j<accept.length();j++)
        {
            if(map.get(j).equals("a")||map.get(j).equals("0"))
            {
                transitiontable[j][0]=list.get(j);
            }
            else if(map.get(j).equals("b")||map.get(j).equals("1"))
            {
                transitiontable[j][1]=list.get(j);
            }
            
            if(mapextra.get(j).equals("a")||mapextra.get(j).equals("0"))
            {
                transitiontable[j][0]=listextra.get(j);
            }
            else if(mapextra.get(j).equals("b")||mapextra.get(j).equals("1"))
            {
                transitiontable[j][1]=listextra.get(j);
            }
        }
        if(acceptarray[0]=='a'||acceptarray[0]=='0')
        {
            transitiontable[0][1]=0;
        }
        else if(acceptarray[0]=='b'||acceptarray[0]=='1')
        {
            transitiontable[0][0]=0;
        }
        
        transitiontable[accept.length()][0]=accept.length();
        transitiontable[accept.length()][1]=accept.length();
        
        if(acceptarray[1]==acceptarray[0])
        {
            if(acceptarray[2]=='b'||acceptarray[2]=='1')
            {
                transitiontable[2][0]=2;
            }
            else if(acceptarray[2]=='a'||acceptarray[2]=='0')
            {
                transitiontable[2][1]=2;
            }
        }
        
        prinTransitiontablesubstring(accept, transitiontable);
        System.out.print("Do you Want to do the String Processing ?(yes / no)");
        String answer=buffer.readLine();
        if(answer.equalsIgnoreCase("yes"))
        {
            System.out.print("Enter the String to Test: -");
            String test=buffer.readLine();
            char testarray[]=test.toCharArray();
            if(test.contains(accept))
            {
                System.out.println("STRING ACCEPTED ");
            }
            
            int number=0;
            for(int k=0;k<test.length();k++)
            {
                if(testarray[k]=='a'||testarray[k]=='0')
                {
                    number=transitiontable[number][0];
                }
                else
                {
                    number=transitiontable[number][1];
                }
            }
            if(number==accept.length())
            {
                int process=0;
                for(int k=0;k<test.length();k++)
                {
                    
                    if(testarray[k]=='a'||testarray[k]=='0')
                    {
                        System.out.print("q"+process+"--"+testarray[k]+"->" );
                        process=transitiontable[process][0];
                    }
                    else
                    {
                        System.out.print("q"+process+"--"+testarray[k]+"->" );
                        process=transitiontable[process][1];
                    }
                }
                System.out.print("q"+process );
            }
            else
            {
                System.out.println("NOT ACCEPTED");
            }
        }
        else
        {
            System.out.println("Thank You");
        }
    }
    
    public static void suffix(int alphabetchoose, String accept) throws IOException
    {
        LinkedList<Integer> list=new LinkedList<>();
        LinkedList<Integer> listextra=new LinkedList<>();
        HashMap<Integer,String> map=new HashMap();
        HashMap<Integer,String> mapextra=new HashMap();
        if(alphabetchoose==1)
        {
            alphabetarray[0]="0";
            alphabetarray[1]="1";    
        }
        else{
            alphabetarray[0]="a";
            alphabetarray[1]="b";
        }
        states=new int[accept.length()+1];
        transitiontable =new int[accept.length()+1][2];
        acceptarray=new char[accept.length()];
        acceptarray=accept.toCharArray();
        for (int i=0;i<accept.length()+1;i++)
        {
            states[i]=i;
        }
        for(int i=0;i<accept.length();i++)
        {
            String temp=String.valueOf(acceptarray[i]);
            map.put(states[i], temp);
            list.add(i, i+1);
        }
        int i=0;
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            String value = entry.getValue();
            if (value.equals("a"))
            {
                mapextra.put(i, "b");
                if(acceptarray[0]=='b')
                {
                    listextra.add(i, 1);
                }
                else
                {
                    listextra.add(i, 0);
                }
                
            }
            else if (value.equals("b"))
            {
                mapextra.put(i, "a");
                if(acceptarray[0]=='a')
                {
                    listextra.add(i, 1);
                }
                else
                {
                    listextra.add(i, 0);
                }
            }
            if (value.equals("0"))
            {
                mapextra.put(i, "1");
                if(acceptarray[0]=='1')
                {
                    listextra.add(i, 1);
                }
                else
                {
                    listextra.add(i, 0);
                }
                
            }
            else if (value.equals("1"))
            {
                mapextra.put(i, "0");
                if(acceptarray[0]=='0')
                {
                    listextra.add(i, 1);
                }
                else
                {
                    listextra.add(i, 0);
                }
            }
            i++;
        }
        for(int j=0;j<accept.length();j++)
        {
            if(map.get(j).equals("a")||map.get(j).equals("0"))
            {
                transitiontable[j][0]=list.get(j);
            }
            else if(map.get(j).equals("b")||map.get(j).equals("1"))
            {
                transitiontable[j][1]=list.get(j);
            }
            
            if(mapextra.get(j).equals("a")||mapextra.get(j).equals("0"))
            {
                transitiontable[j][0]=listextra.get(j);
            }
            else if(mapextra.get(j).equals("b")||mapextra.get(j).equals("1"))
            {
                transitiontable[j][1]=listextra.get(j);
            }
        }
        
        int flag_a=0,flag_b=0,j=1;
        
        while(j<accept.length())
        {
            if(acceptarray[j]==acceptarray[j-1]&&(acceptarray[j]=='a'||acceptarray[j]=='0'))
            {
                flag_a=0;
            }
            else if(acceptarray[j]=='b')
            {
                flag_a=1;   
            }
            else if(acceptarray[j]==acceptarray[j-1]&&(acceptarray[j]=='b'||acceptarray[j]=='1'))
            {
                flag_b=0;
            }
            else if(acceptarray[j]=='a'||acceptarray[j]=='0')
            {
                flag_b=1;   
            }
            
            if(flag_a==1)
            {
                transitiontable[j][0]=1;
                flag_a=0;
            }
            if(flag_b==1)
            {
             transitiontable[j][1]=1;
             flag_b=0;
            }
            j++;
        }
        
        printTransitiontablesuffix(accept, transitiontable);
        System.out.print("Do you Want to do the String Processing ?(yes / no)");
        String answer=buffer.readLine();
        if(answer.equalsIgnoreCase("yes"))
        {
            System.out.print("Enter the String to Test: -");
            String test=buffer.readLine();
            char testarray[]=test.toCharArray();
            
            int number=0;
            for(int k=0;k<test.length();k++)
            {
                if(testarray[k]=='a'||testarray[k]=='0')
                {
                    number=transitiontable[number][0];
                }
                else
                {
                    number=transitiontable[number][1];
                }
            }
            if(number==accept.length())
            {
                int process=0;
                System.out.println("ACCEPTED");
                
                for(int k=0;k<test.length();k++)
                {
                    
                    if(testarray[k]=='a'||testarray[k]=='0')
                    {
                        System.out.print("q"+process+"--"+testarray[k]+"->" );
                        process=transitiontable[process][0];
                    }
                    else
                    {
                        System.out.print("q"+process+"--"+testarray[k]+"->" );
                        process=transitiontable[process][1];
                    }
                }
                
                System.out.print("q"+process );
                
            }
            else
            {
                System.out.println("NOT ACCEPTED");
            }
        }
        else
        {
            System.out.println("Thank You");
        }
    }
    public static void prinTransitiontablesubstring(String accept, int transitiontable[][])
    {
        System.out.println("TRANSITION TABLE");
        if(accept.contains("a"))
        {
            System.out.println("\t\ta\t|\tb");
        }
        else
        {
            System.out.println("\t\t0\t|\t1");
        }
        for(int j=0;j<=accept.length();j++)
        {
            if(j==0)
            {
                System.out.print("->q"+j+"\t|");
            }
            else if(j==accept.length())
            {
                System.out.print("*q"+j+"\t|");
            }
            else
            {
                System.out.print("q"+j+"\t|");
            }
            for(int k=0;k<2;k++)
            {
               System.out.print("\tq"+transitiontable[j][k]+"\t|");
            }
            System.out.println();
        }
    }
    public static void printTransitiontablesuffix(String accept, int transitiontable[][])
    {
        System.out.println("TRANSITION TABLE");
        if(accept.contains("a"))
        {
            System.out.println("\t\ta\t|\tb");
        }
        else
        {
            System.out.println("\t\t0\t|\t1");
        }
        for(int j=0;j<=accept.length();j++)
        {
            if(j==0)
            {
                System.out.print("->q"+j+"\t|");
            }
            else if(j==accept.length())
            {
                System.out.print("*q"+j+"\t|");
            }
            else
            {
                System.out.print("q"+j+"\t|");
            }
            for(int k=0;k<2;k++)
            {
               System.out.print("\tq"+transitiontable[j][k]+"\t|");
            }
            System.out.println();
        }
    }
    
    public static void printTransitiontable(String accept, int transitiontable[][])
    {
        System.out.println("TRANSITION TABLE");
        if(accept.contains("a"))
        {
            System.out.println("\t\ta\t|\tb");
        }
        else
        {
            System.out.println("\t\t0\t|\t1");
        }
        for(int j=0;j<=accept.length()+1;j++)
        {
            if(j==0)
            {
                System.out.print("->q"+j+"\t|");
            }
            else if(j==accept.length())
            {
                System.out.print("*q"+j+"\t|");
            }
            else
            {
                System.out.print("q"+j+"\t|");
            }
            for(int k=0;k<2;k++)
            {
               System.out.print("\tq"+transitiontable[j][k]+"\t|");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) throws IOException {
        int choose,flag=0,alphabetchoose;
        System.out.println("WELCOME TO COMPILER DESIGN PRCTICAL 2 \n IMPLEMENTATION OF THE DIFFERENT TYPES OF DFA.");
        System.out.println("\n *Alphabets you want to choose: *");
        System.out.println("Press 1 for {0,1}");
        System.out.println("Press 2 for {a,b}");
        alphabetchoose=scan.nextInt();
        do{
        System.out.println("Choose the DFA to design from the given DFA's type:  ");
        System.out.println("press 1. To design a DFA with a particular prefix");
        System.out.println("press 2. To design a DFA with a particular suffix");
        System.out.println("press 3. To design a DFA with a particular substring");
        System.out.print("Enter your choice: ");
        choose=scan.nextInt();
        String accept;
        switch(choose)
        {
            case    1:  System.out.print("DFA must accept: ");
                        accept=scan.next();
                        prefix(alphabetchoose, accept);
                        flag=0;
                        break;    
            case    2:  System.out.print("DFA must accept: ");
                        accept=scan.next();
                        suffix(alphabetchoose, accept);
                        flag=0;
                        break;
            case    3:  System.out.print("DFA must accept: ");
                        accept=scan.next();
                        substring(alphabetchoose,accept);
                        flag=0; 
                        break;
            default:    System.out.print("You have entered wrong input");
                        flag=1;
                        break;
        }
        }while(flag==1);
    }
}