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
  
   static public void stmt_seq()
    {
         stmt();
         System.out.println(splitted.get(counter));
        
       while((!splitted.get(counter).equals("else"))&&(!splitted.get(counter).equals("end"))&&(!splitted.get(counter).equals("until")))
        // while(!splitted.get(counter).equals(";"))
        {
            match(";");
            stmt();
        }
        
    }

    
    
    
    
    public  static void match(String expected)
    {
        if(splitted.get(counter).equals(expected))
        {
            System.out.println("matched");
            System.out.println(splitted.get(counter));
            counter++;
            System.out.println(splitted.get(counter));
        }
        else 
            System.out.println("Error");
     }
        
        
   //}
  static public void stmt()
    {
        String t = null;
//        switch(splitted.get(counter))
//        {
//            case "if" :if_stmt();break;
//            case "repeat" : repeat_stmt();break;
//            case "id" : assign_stmt();break;
//            case "read" :read_stmt();break;
//            case "write" : write_stmt();break;
//            default: System.out.println("Error");
//        }
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
        else
            System.out.println("Error");
        
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
    }
   static public void repeat_stmt()
    {
        match("repeat");
        stmt_seq();
        match("until");
        exp();
        
    }
   static public void assign_stmt()
    {
        match("identifier");
        match("assign");
        exp();
        
    }
 static public void read_stmt()
    {
        match("read");
        match("identifier");
        System.out.println("read statement found");
        
    }
   static public void write_stmt()
    {
        match("write");
        exp();
        
    }
    static void  exp()
    {
        simple_exp();
        if((splitted.get(counter).equals("<"))||(splitted.get(counter).equals("=")))
        {
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
        switch(splitted.get(counter))
        {
            case "number" : match("number");break;
            case "identifier": match("identifier");break;
            case "(" : 
                match("(");
                exp();
                match(")");
                break;
            default:
                System.out.println("Unexpected token");
                //token=gettoken();
                break;
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
        
        //try {
            //        //input file
//        //output file
//        String t;
//        token = "read identifier";
//        t=stmt_seq();
//        if(token=="\n")
//        {
//            System.out.println("error");
//        }
//        Scanner s = new Scanner(System.in);
//        token = s.nextLine();
//        
//          g();
//        //System.out.println(parts[1]);
//        String testString = "ThisIsTest";
//		char[] stringToCharArray = token.toCharArray();
// 
////		for (char output : stringToCharArray) {
////                    
////			System.out.println(output);
////            
////    }
//                for (int i=0;i<stringToCharArray.length;i++)
//                {
//                    System.out.println(stringToCharArray[i]);
//                }
            try {
       File file = new File("parser_input.txt");
       FileReader fileReader = new FileReader(file);
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
                        //for(String s:splitted)
                        System.out.println(splitted);
                        
                       
//             
			fileReader.close();
			
			//System.out.println(stringBuffer.toString());
                        } catch (IOException e) {
			e.printStackTrace();
		}
           // stmt_seq();
            try{
            File files = new File("parser_output.txt");
            if (!files.exists()) {
				files.createNewFile();
			}
            FileWriter fw = new FileWriter(files.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("jhfjk");
			bw.close();
                        } catch (IOException e) {
			e.printStackTrace();
		}
            //match(";");
            //match(splitted.get(counter));
            stmt_seq();
            
    }}