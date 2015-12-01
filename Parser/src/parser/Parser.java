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
 * @author Mohamed Elkashif
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
            if(q!=null)
            {
                if(t==null)
                    t=p=q;
                else
                {
                    p=q;
                }
            }
        }
        return t;
    }

    
    static public void match(String expected)
    {
        if(token == expected) token = "read identifier";
        else
        {
            System.out.println("Error");
        }
        
        
    }
   static public String stmt()
    {
        String t = null;
        switch(token)
        {
            case "IF" :t=if_stmt();break;
            case "Repeat" : t= repeat_stmt();break;
            case "id" : t= assign_stmt();break;
            case "read" : t=read_stmt();break;
            case "write" : t=write_stmt();break;
            default: System.out.println("Error");
        }
        return t;
    }
   static public String if_stmt()
    {
        match("if");
        exp();
        match("then");
        stmt_seq();
        if(token == "ELSE")
            {
                match("ELSE");
                stmt_seq();
            }
        match("END");
        return null;
    }
   static public String repeat_stmt()
    {
        match("repeat");
        stmt_seq();
        match("until");
        exp();
        return null;
    }
   static public String assign_stmt()
    {
        match("identifier");
        match("assign");
        exp();
        return null;
    }
   static public String read_stmt()
    {
        match("read");
        match("identifier");
        return null;
    }
   static public String write_stmt()
    {
        match("write");
        exp();
        return null;
    }
    static public String exp()
    {
        simple_exp();
        if((token == "<")||(token == "="))
        {
            simple_exp();
        }
        return null;
    }
   static public String term()
    {
        factor();
        while((token == "*")||(token == "/"))
        {
            match(token);
            factor();
        }
        return null;
    }    
   static public String factor()
    {
        switch(token)
        {
            case "NUM" : match("NUM");break;
            case "ID": match("ID");break;
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
        return null;
    }
   static public String simple_exp()
    {
        term();
        while((token == "+") || (token == "-"))
        {
            match(token);
            term();
        }
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