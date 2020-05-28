/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.design;

import static compiler.design.LexicalAnalysis.scan;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Comments {
    static Scanner scan=new Scanner(System.in);
    static InputStreamReader reader=new InputStreamReader(System.in);
    static BufferedReader buffer=new BufferedReader(reader);
    static int operator,keywords,identifier,constant,delimeter;
    
    public static void file() throws IOException
    {
        int multiLine=0,single=0,i=0,j=0;
        String singlearray[]=new String[100];
        String multiarray[]=new String[100];
        File f=null;
        FileReader fr=null;
        BufferedReader br=null;
        int flag=0;
        String answer="no",regex="([a-zA-Z]:)?(\\\\[a-zA-Z0-9._-]+)+\\\\?";
        String program=null;
        do{
        System.out.println("Enter the path of the file (must in the format : C:\\Users\\Dell......)");
        String path=scan.next();
        boolean ismatched=Pattern.matches(regex, path);
        if(ismatched)
        {
            try{
                f=new File(path);  
                fr=new FileReader(f);
                br=new BufferedReader(fr);
                
                while((program=br.readLine())!=null)
                {   
                    program = scan.nextLine();
                    if(program.contains("/*")) {
                        multiLine ++;
                        multiarray[j]=program;
                        j++;        
                    }
                    else if(program.contains("//")) {
                        single ++;
                        singlearray[i]=program;
                        i++;
                    }
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
        System.out.println("SINGLE LINES: "+single);
        for(int k=0;k<i;k++)
        {
            System.out.println(singlearray[k]);
        }
        System.out.println();
        System.out.println("MultiLine LINES: "+multiLine);
        for(int k=0;k<j;k++)
        {
            System.out.println(multiarray[k]);
        }
    }
   public static void write() throws IOException
    {
        String singlearray[]=new String[100];
        String multiarray[]=new String[100];
        System.out.println("Write the Program in JAVA Language: -");
        System.out.println("NOTE:  to finish writing the source code; kindly write end");
        String input=null;
        int multiLine=0,single=0,i=0,j=0;
        do{
            input = scan.nextLine();
            if(input.contains("/*")) {
                multiLine ++;
                multiarray[j]=input;
                j++;        
            }
            else if(input.contains("//")) {
                single ++;
                singlearray[i]=input;
                i++;
            }
        }while(!input.contains("end"));
        System.out.println("SINGLE LINES: "+single);
        for(int k=0;k<i;k++)
        {
            System.out.println(singlearray[k]);
        }
        System.out.println();
        System.out.println("MultiLine LINES: "+multiLine);
        for(int k=0;k<j;k++)
        {
            System.out.println(multiarray[k]);
        }
    }
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        System.out.println("*WELCOME TO THE PROGRAM TO COUNT SINGLE LINE AND MULTILINE COMMENTS");
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
