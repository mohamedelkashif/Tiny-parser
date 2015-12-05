/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.parser.TokenType;
import static jdk.nashorn.internal.parser.TokenType.EOF;
import static jdk.nashorn.internal.parser.TokenType.EQ;


/**
 *
 * @author Mohamed Elkashif
 */
public class Parser {
    //static String token;
     static String sd;
     public static ArrayList<String> splitted =new ArrayList<String>();
     public static int counter = 0;
     static FileReader fileReader;
     //static FileWriter fw;
    // static BufferedWriter bw;
     static PrintWriter output;
    static int maxcounter;
    
    static public void program()
    {
        stmt_seq();
        output.println("Program found");
    }
  
   static public void stmt_seq()
    {
         stmt();
         System.out.println(splitted.get(counter));
        
      //while((splitted.r)(!splitted.get(counter).equals("else"))&&(!splitted.get(counter).equals("end"))&&(!splitted.get(counter).equals("until")))
        // while((!splitted.get(counter).equals(EOF))&&(!splitted.get(counter).equals("else"))&&(!splitted.get(counter).equals("end"))&&(!splitted.get(counter).equals("until")))
         while(splitted.get(counter).equals(";"))
        {
             
            match(";");
            stmt();
        }
         //match(";");
         //stmt();
         
        
    }

    
    
    
    
    public  static void match(String expected)
    {
        
        if(splitted.get(counter).equals(expected))
        {
            
            System.out.println("matched");
            System.out.println(splitted.get(counter));
            if(counter< maxcounter-1)
            {
            counter++;
            System.out.println(splitted.get(counter));
            System.out.println(counter);
//            if(counter==maxcounter-1)
//            {
//                System.out.println("exception");
//            }
            }
            else
                System.out.println("dfv");
        }
        else {
            System.out.println("Error");
        output.println("match error");
System.exit(0);
        }
     }
        
        
   //}
  static public void stmt()
    {
        String t = null;

        if(splitted.get(counter).equals("if"))
        {
            if_stmt();
            
        }
        else if(splitted.get(counter).equals("repeat"))
        {
            repeat_stmt();
           
        }
        else if(splitted.get(counter).equals("identifier"))
        {
            assign_stmt();
        }
        else if(splitted.get(counter).equals("read"))
        {
            read_stmt();
        }
        else if(splitted.get(counter).equals("write"))
        {
            write_stmt();
        }
        else{
            System.out.println("Error");
            //counter++;
        }
        //
        
        
    }
   static public void if_stmt()
    {
        match("if");
        exp();
        match("then");
        stmt_seq();
        
        if(splitted.get(counter).equals("else"))
            {
                match("else");
                stmt_seq();
            }
        
        match("end");
        System.out.println("if statement found");
        output.println("if statement found");
       // output.close();
    }
   static public void repeat_stmt()
    {
        match("repeat");
        stmt_seq();
        match("until");
        exp();
        System.out.println("repeat statement found");
        output.println("repeat statement found");
        //output.close();
        
    }
   static public void assign_stmt()
    {
        match("identifier");
        match(":=");
        exp();
        System.out.println("Assign statement found");
        output.println("Assign statement found");
        //output.close();
    }
 static public void read_stmt()
    {
        match("read");
        match("identifier");
        System.out.println("read statement found");
        output.println("read statement found");
       // output.close();
       
        
    }
   static public void write_stmt()
    {
        match("write");
        exp();
        System.out.println("write statement found");
        output.println("write statement found");
        //output.close();
    }
    static void  exp()
    {
        simple_exp();
        if((splitted.get(counter).equals("<"))||(splitted.get(counter).equals("=")))
        {
            match(splitted.get(counter));
            simple_exp();
        }
        
    }
   static public void term()
    {
        factor();
        while((splitted.get(counter).equals("*"))||(splitted.get(counter).equals("/")))
        {
            match(splitted.get(counter));
            factor();
        }
        
    }    
   static public void factor()
    {

        if(splitted.get(counter).equals("("))
        {
            match("(");
            exp();
            match(")");
        }
        else if(splitted.get(counter).equals("number"))
        {
            match("number");
        }
        else if(splitted.get(counter).equals("identifier"))
        {
            match("identifier");
                    
        }
        else{
            System.out.println("error");
            counter++;
        }
        
    }
   static public void simple_exp()
    {
        term();
        while((splitted.get(counter).equals("+")) || (splitted.get(counter).equals("-")))
        {
            match(splitted.get(counter));
            term();
        }
        
    }
    public static void main(String[] args) throws IOException {
        
        
            try {
       File file = new File("parser_input.txt");
       fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
                        StringBuffer stringBuffer = new StringBuffer();
			String line;
                        while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("\n");
                                String[] splited = line.split(" ");
                                
                                for (String part : splited) {
                                    splitted.add(part);
  System.out.println(part);
}
			}
                        maxcounter = splitted.size();
                        //for(String s:splitted)
                        System.out.println(splitted);
                        System.out.println(maxcounter);
                        
                       
//             
			fileReader.close();
			
			//System.out.println(stringBuffer.toString());
                        } catch (IOException e) {
			e.printStackTrace();
		}
           
           output = new PrintWriter("parser_output.txt");
            
            //stmt_seq();
            program();
            System.out.println("Program found");
           // output.println("Program found");
            output.close();
    }}