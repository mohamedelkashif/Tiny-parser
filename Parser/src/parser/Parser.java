/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.parser.TokenType;
import static jdk.nashorn.internal.parser.TokenType.EQ;

/**
 *
 * @author Mohamed
 */
public class Parser {
    static String token;
   static public String stmt_seq()
    {
        String t = stmt();
        String p =t;
        while((token!="\n") && (token!="ELSE")&&(token!="END")&&(token!="UNTIL"))
        {
            String q;
            match(";");
            q=stmt();
//            if(q!=null)
//            {
//                if(t==null)
//                    t=p=q;
//                else
//                {
//                    p=q;
//                }
//            }
        }
        return t;
    }

    
    static public String match(String x)
    {
        return null;
        
    }
   static public String stmt()
    {
        return null;
    }
   static public String if_stmt()
    {
        return null;
    }
   static public String repeat_stmt()
    {
        return null;
    }
   static public String assign_stmt()
    {
        return null;
    }
   static public String read_stmt()
    {
        return null;
    }
   static public String write_stmt()
    {
        return null;
    }
    static public String exp()
    {
        return null;
    }
   static public String term()
    {
        return null;
    }    
   static public String factor()
    {
        return null;
    }
   static public String simple_exp()
    {
        return null;
    }
    public static void main(String[] args) {
        
        //input file
        //output file
        String t;
        token = "read identifier";
        t=stmt_seq();
        if(token=="\n")
        {
            System.out.println("error");
        }
            
    }
}