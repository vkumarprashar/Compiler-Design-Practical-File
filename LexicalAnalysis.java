/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.design;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Sai Ji
 */
public class LexicalAnalysis {
    static Scanner scan=new Scanner(System.in);
    static InputStreamReader reader=new InputStreamReader(System.in);
    static BufferedReader buffer=new BufferedReader(reader);
    
    static int operator,keywords,identifier,constant,delimeter;
    public static boolean keyword(String word)
    {
        if(word.equalsIgnoreCase("void")||word.equalsIgnoreCase("int")||word.equalsIgnoreCase("static")||word.equalsIgnoreCase("public")||word.equalsIgnoreCase("throws")||word.equalsIgnoreCase("for")
            ||word.equalsIgnoreCase("private")||word.equalsIgnoreCase("null")||word.equalsIgnoreCase("do")||word.equalsIgnoreCase("while")||word.equalsIgnoreCase("if")||word.equalsIgnoreCase("else")
                ||word.equalsIgnoreCase("float")||word.equalsIgnoreCase("char")||word.equalsIgnoreCase("enum")||word.equalsIgnoreCase("switch")||word.equalsIgnoreCase("goto")||word.equalsIgnoreCase("continue")
                ||word.equalsIgnoreCase("class")||word.equalsIgnoreCase("boolean")||word.equalsIgnoreCase("new"))
        {
//            System.out.println(word+" is a KEYWORD");
            System.out.println("\t"+word+"\t|\tKEYWORD");
            keywords++;
            return true;
        }
        return false;
    }
    public static boolean operator(String word)
    {
        if(word.equals("+")||word.equals("-")||word.equals("*")||word.equals("/")||word.equals("^")||word.equals("%")||word.equals("=")||word.equals("==")||word.equals(">")||word.equals("<")||word.equals(">=")||word.equals("<=")||word.equals("!="))
        {
//            System.out.println(word+"is an operator");
            System.out.println("\t"+word+"\t|\tOPERATOR");
            operator++;
            return true;
        }
        return false;
    }
    public static boolean delimeter(String word)
    {
        if(word.equals(";")||word.equals("{")||word.equals("}")||word.equals("(")||word.equals(")")||word.equals(",")||word.equals("end"))
        {
            System.out.println("\t"+word+"\t|\tDELIMETER");
            delimeter++;
            return true;
        }
        return false;
    }
    public static boolean constant(String word)
    {
        Pattern p = Pattern.compile("\\b(?:([1-9][0-9]*)|(0[0-7]*)|0x([0-9A-F]+)|0b([01]+))(L)?\\b");
        if(p.matcher(word).lookingAt())
        {
            System.out.println("\t"+word+"\t|\tCONSTANT");
            constant++;
            return true;
        }
        else
        {
            return false;
        }
    }
    public static boolean identifier(String word)
    {
//        if(word.matches("[a-z]||[A-Z]"))
//        {
        if(word.matches("\\s"))
        {
            return true;
        }
            System.out.println("\t"+word+"\t|\tIDENTIFIER");
            identifier++;
            return true;
//        }

//        return false;
    }
    public static void file() throws FileNotFoundException, IOException
    {
        File f=null;
        FileReader fr=null;
        BufferedReader br=null;
//        FileInputStream file = null;
        int flag=0;
        String answer="no",regex="([a-zA-Z]:)?(\\\\[a-zA-Z0-9._-]+)+\\\\?";
        String program=null,input;
        
        do{
        System.out.println("Enter the path of the file (must in the format : C:\\Users\\Dell......)");
        String path=scan.next();
        boolean ismatched=Pattern.matches(regex, path);
        if(ismatched)
        {
            try{
//                file=new FileInputStream(path);
                  f=new File(path);  
                  fr=new FileReader(f);
                  br=new BufferedReader(fr);
                  while((input=br.readLine())!=null)
                  {
                      program=program+" "+input;
                  }
            }
            catch(FileNotFoundException e)
            {
                System.out.println("No File available. \nDo you want to try again? (yes/no)");
                answer=scan.next();
            }
            flag=1;
        }
        else
        {
            System.out.println("Entered Path does not match the given format. Try Again! ");
            flag=0;
        }
        }while(flag!=1||answer.equals("yes"));
        
       print(program); 

    }
    public static void write() throws IOException
    {
        System.out.println("Write the Program in JAVA Language: -");
        System.out.println("NOTE:  to finish writing the source code; kindly write end");
        String program="";
        do{
            program=program+buffer.readLine();
            program=program +" ";
        }while(!program.contains("end"));
        
        print(program);
        
    }
    
    public static void print(String program)
    {
        System.out.println();
        System.out.println("\tLEXMIES\t|\tTOKENS");
        System.out.println("\t-----------------------------");
        String arr[]=program.split("\\s");
        String programsplitcomma[];
        String comma=",",plus="+",minus="-",multiply="*",divide="/",extra="",semicolon=";";
        int flag=0;
        AA: for (String aaa: arr)
        {
            if(aaa.contains(comma)&&(aaa.length()<=1))
            {
                flag=1;
                extra=comma;
            }
            else if(aaa.contains(plus))
            {
                flag=1;
                extra = plus;
            }
            else if(aaa.contains(minus))
            {
                flag=1;
                extra=minus;
            }
            else if(aaa.contains(divide))
            {
                flag=1;
                extra=divide;
            }
            else if(aaa.contains(multiply))
            {
                flag=1;
                extra=multiply;
            }
            else if(aaa.contains(semicolon))
            {
                flag=1;
                extra=semicolon;
            }
            if(flag==1){
                programsplitcomma=aaa.split("\\"+extra);
                BB:
                for(String aab: programsplitcomma)
                { 
                    if(keyword(aab))
                    {
                            
                    }
                    else if(operator(aab))
                    {
                        
                    }
                    else if(constant(aab))
                    {
                        
                    }
                    else if(delimeter(aab))
                    {
                        
                    }
                    else
                    {
                        identifier(aab);
                        
                    } 
                }
                
                if(!(extra.equals(",")||extra.equals(";")))
                {
                    System.out.println("\t"+extra+"\t|\tOPERATOR");
                    operator++;
                }
                else 
                {
                    System.out.println("\t"+extra+"\t|\tDELIMETER");
                    delimeter++;
                }
                flag=0;
                continue AA;
            }
            if(keyword(aaa))
            {
            }
            else if(operator(aaa))
            {
            }
            else if(constant(aaa))
            {
            }
            else if(delimeter(aaa))
            {
            }
            else
            {
                identifier(aaa);
            } 
        }
        System.out.println();
        System.out.println("TOTAL NUMBER OF TOKENS: ");
        System.out.println("             Operators:    "+operator);
        System.out.println("           Identifiers:    "+identifier);
        System.out.println("              KeyWords:    "+keywords);
        System.out.println("             Constants:    "+constant);
        System.out.println("             Delimeter:    "+delimeter);
        System.out.println("Total Number of Tokens:    "+(operator+delimeter+keywords+identifier+constant));
    }
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        System.out.println("*WELCOME TO THE TOKEN RECOGANIZATION PROGRAM");
        System.out.println("Choose method to implement: ");
        System.out.println("1. Press 1 to read the program from a file");
        System.out.println("2. Press 2 to write a program by yourself at console");
        int choose=scan.nextInt();
        if(choose==1)
        {
            file();
        }
        else
        {
            write();
        }   
    }
}
