/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.design;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CommentAnalysis {
    static Scanner scan=new Scanner(System.in);
    static InputStreamReader reader=new InputStreamReader(System.in);
    static BufferedReader buffer=new BufferedReader(reader);
    static FileWriter fw=null;
    static BufferedWriter bw=null;
    int codeLines = 0, commentLines = 0, totalLines = 0,i=0,j=0;
    boolean commentStarted = false;
    static File fileName = null;
    String multiLine[]=new String[100];
    String singleLine[]=new String[100];
    public static void main(String[] args) throws IOException {
        System.out.println("*WELCOME TO THE PROGRAM TO COUNT SINGLE LINE AND MULTILINE COMMENTS");
        System.out.println("Choose method to implement: ");
        System.out.println("1. Press 1 to read the program from a file");
        System.out.println("2. Press 2 to write a program by yourself at console");
        int choose=scan.nextInt();
        String answer="n",regex="([a-zA-Z]:)?(\\\\[a-zA-Z0-9._-]+)+\\\\?";
        CommentAnalysis obj = new CommentAnalysis();
        if(choose==1)
        {
            do{
            System.out.println("Enter the path of the file (must in the format : C:\\Users\\Dell......)");
            String path=scan.next();
            boolean ismatched=Pattern.matches(regex, path);
                if(ismatched)
                {
                    fileName = new File(path);
                    obj.analyzeFile();
                }
                else
                {
                    System.out.print("Entered Path does not match the given format.Do you want to try again?(y/n): ");
                    answer=buffer.readLine();
                    if(answer.equals("n"))
                    {
                        break;
                    }
                }
            }while(answer.equals("y"));
        }
        else if(choose==2)
        {
            System.out.println("NOTE:  to finish writing the source code; kindly write end");
            try{
            fileName= new File("E:\\IT\\hello.txt.txt");
            fw=new FileWriter(fileName);
            bw=new BufferedWriter(fw);
            String program="";
            do{
                program=buffer.readLine();
                bw.append(program);
                bw.newLine();
            }while(!program.contains("end"));
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            finally{
                try{
                    bw.close();
                    fw.close();
                }
                catch(Exception e)
                {
                    System.out.println(e.getMessage());
                }
            }
            obj.analyzeFile();
        }
        else
        {
                System.out.println("Wrong Option! ");
        }
    }

    public void analyzeFile() {
        BufferedReader br = null;
        String sCurrentLine = null;
        boolean sameLine = false;
        try {
            br = new BufferedReader(new FileReader(fileName));
            while ((sCurrentLine = br.readLine()) != null) {
                sCurrentLine = sCurrentLine.trim();
                sameLine = false;
                //System.out.println(sCurrentLine);
                while(sCurrentLine != null && sCurrentLine.length() > 0) {
                  //  System.out.println("for line: " + sCurrentLine + " and sameLine:" + sameLine);
                    sCurrentLine = analyzeLine(sCurrentLine, sameLine);
                    sameLine = true;
                }
            }
            totalLines = codeLines + commentLines;
            System.out.println("Total number of Lines are : " + totalLines);
            System.out.println("Number of comments: " + commentLines);
            System.out.println("Number of code lines: " + codeLines);
            double cPercent = (double)commentLines/totalLines;
            cPercent = cPercent * 100;
            System.out.println("Ratio is: " + cPercent);
            System.out.println("SINGLE LINES: ");
            for(int k=0;k<j;k++)
            {
                System.out.println(singleLine[k]);
            }
            System.out.println();
            System.out.println("MultiLine LINES: ");
            for(int k=0;k<i;k++)
            {
                System.out.println(multiLine[k]);
            }            
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public String analyzeLine(String sCurrentLine, boolean sameLine) {        
        
        if(commentStarted && sCurrentLine.contains("*/")) {
            if (!sameLine)
                commentLines++;
            commentStarted = false;
            if (!sCurrentLine.endsWith("*/"))
            {
                sCurrentLine = sCurrentLine.substring(sCurrentLine.indexOf("*/")+2).trim();
            }
            else
            {
                multiLine[i]=sCurrentLine;
                i++;
                sCurrentLine = null;
            }
        }
        else if(sCurrentLine.startsWith("//")) {
            if (!sameLine)
                commentLines++;
            singleLine[j]=sCurrentLine;
            j++;
            sCurrentLine = null;
        }
        else if(sCurrentLine.contains("/*")) {            
            commentStarted = true;
            if (!sCurrentLine.startsWith("/*")){
                if (!sameLine)
                {
                    codeLines++;
                }
                sCurrentLine = sCurrentLine.substring(sCurrentLine.indexOf("/*")).trim();                
            }
            else {
                if (!sameLine)
                {
                    commentLines++;
                }if (sCurrentLine.contains("*/")) {
                    commentStarted = false;
                    if (!sCurrentLine.endsWith("*/"))
                    {
                        sCurrentLine = sCurrentLine.substring(sCurrentLine.indexOf("*/")+2).trim();
                    }else
                    {    
                        sCurrentLine = null;
                    }
                }
                else
                {
                    multiLine[i]=sCurrentLine;
                    i++;
                    sCurrentLine = null;
                }
            }
        }        
        else if(commentStarted) {
            if (!sameLine)
                commentLines++;
            multiLine[i]=sCurrentLine;
            i++;
            sCurrentLine = null;
        }
        else {
            commentStarted = false;
            if (!sameLine)
                codeLines++;
            if (sCurrentLine.contains(";")) {
                if (!sCurrentLine.endsWith(";")) {
                    sCurrentLine = sCurrentLine.substring(sCurrentLine.indexOf(";")+1).trim();
                }
                else
                    sCurrentLine = null;
            }
            else
                sCurrentLine = null;
        }
        return sCurrentLine;
    }
}