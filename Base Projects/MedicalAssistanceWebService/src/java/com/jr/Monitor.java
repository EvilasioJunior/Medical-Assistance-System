/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jr;

import java.io.*;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author junior
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Monitor {
   @WebMethod
    public String funcService(String file){
        
        //variáveis que conterão os valores de falhas do serviço de alarme
        String chancefailalarm1 = "";
        String chancefailalarm2 = ""; 
        String chancefailalarm3 = "";
        
        try{
        BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Evilasio\\Documents\\NetBeansProjects\\MedicalSystem\\AdaptadorMedicalSystem_1.5\\ValuesFailEnvironment.txt"));
        chancefailalarm1 = in.readLine();
        chancefailalarm2 = in.readLine();
        chancefailalarm3 = in.readLine();
        in.close();
        }catch(IOException e){}
        
        try{
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write("Alarms"); out.newLine();
        out.write("Alarm 1: "+chancefailalarm1); out.newLine();
        out.write("Alarm 2: "+chancefailalarm2); out.newLine();
        out.write("Alarm 3: "+chancefailalarm3);
        out.close();
        }catch(IOException e){}
        
        return "";
    }
}