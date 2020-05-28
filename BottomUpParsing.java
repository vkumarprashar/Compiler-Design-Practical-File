/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.design;

import java.util.Scanner;

public class BottomUpParsing {
    static Scanner scan=new Scanner(System.in);
    static class grammer{
        public char[] p=new char[1];
        public char[] prod=new char[10];
    }
    public static void main(String[] args) {
        grammer[] g=new grammer[20];
        
        int i=0,stpos,j=0,k,l,m,o,p,r,np,tspos,cr=1;
        System.out.print("Enter the number of production rules:- ");
        np=scan.nextInt();
        char sc;
        String productionrule;
        char productionrulearray[]=new char[10];
        for(o=0;o<np;o++)
        {
            g[o]=new grammer();
        }
        
        for(i=0;i<np;i++)
        {
            System.out.print("Enter the "+ i +" production rule: ");
            j=0;
            productionrule=null;
            productionrulearray=null;
            productionrule=scan.next();
            productionrulearray=productionrule.toCharArray();
            for(int aa=0;aa<productionrulearray.length;aa++)
            {
                if(aa==0)
                {
                    g[i].p[aa]=productionrulearray[aa];
                }
                else if(aa>2)
                {
                    g[i].prod[j]=productionrulearray[aa];
                    j++;
                }
            }
        }
        
        
        String input;
        System.out.print("Enter Input: ");
        {
            input=scan.next();
        }
        char[] inputarray=new char[10];
//        ip=input.toCharArray();
        for(int ii=0;ii<input.length();ii++)
        {
            inputarray[ii]=input.charAt(ii);
        }
        int inputlength=input.length();
        char stack[]=new char[10];
        stpos=0;
        i=0;
        sc=inputarray[i];
        stack[stpos]=sc;
        i++;
        stpos++;
        int slength=0;
        System.out.println("\tStack\tInput\tAction");
        do
        {
            r=1;
            slength=0;
            while(r!=0)
            {
                System.out.println("\t");
                System.out.print("\t");
                for(p=0;p<stpos;p++)
                {
                    System.out.print(stack[p]);
                }
                System.out.print("\t");
                for(p=i;p<inputlength;p++)
                {
                    System.out.print(inputarray[p]);
                }
                System.out.print("$");
                if(r==2)
                {
                    System.out.print("\tReduced");
                }
                else
                {
                    System.out.print("\tShifted");
                }
                r=0;

                //try reducing
               productionrulearray=new char[11];
                
                for(k=0;k<stpos;k++)
                {
                    for(l=0;l<10;l++)
                    {
                        productionrulearray[l]='\0';
                    }
                    tspos=0;
                    for(l=k;l<10;l++) //removing first caharcter
                    {
                        productionrulearray[tspos]=stack[l];
                        tspos++;
                    }
                    //now compare each possibility with production
                    for(m=0;m<np;m++)
                    {
//                        cr = strcmp(ts,g[m].prod);
                        for(int jh=0;jh<10;jh++)
                        {
//                            System.out.print("TS: "+ts[jh]+"\t\t");
//                            System.out.println("PROD: "+g[m].prod[jh]);
                            if(productionrulearray[jh]==g[m].prod[jh])
                            {
                                cr=0;
                            }
                            else
                            {
                                cr=1;
                                break;
                            }    
                        }
//                        System.out.println("\nCR"+cr);
                        //if cr is zero then match is found
                        if(cr==0)
                        {
                            for(l=k;l<10;l++) //removing matched part from stack
                            {
                                stack[l]='\0';
                                stpos--;
                            }

                            stpos=k;

                            //concatinate the string
//                            strcat(stack,g[m].p);
                            stack[k]=g[m].p[0];
                            stpos++;
                            r=2;
                        }
                    }
                }
            }
         
            //moving input
            sc=inputarray[i];
            stack[stpos]=sc;
            i++;
            stpos++;
            for(int jh=0;jh<stack.length;jh++)
            {
                if(stack[jh]!='\0')
                {
                    slength++;
                }
                else
                {
                    break;
                }
            }
        }while(slength!=1 && stpos!=inputlength);
        
    if(slength==1)
    {
        System.out.println("\nString Accepted!");
    }
    else
    {
        System.out.println("\nString Not Accepted");
    }
    
        
    }

}