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
public class OldMonitor {
   @WebMethod
    public String funcService(String server){
        
        //variáveis que conterão os valores de falhas do serviço de alarme
        String chancefailalarm1 = "";
        String chancefailalarm2 = ""; 
        String chancefailalarm3 = "";
        String currentchancefail = "";
        
        try{
        BufferedReader in2 = new BufferedReader(new FileReader("C:\\Users\\Evilasio\\Documents\\NetBeansProjects\\MedicalSystem\\AdaptadorMedicalSystem_1.5\\ValuesFailEnvironment.txt"));
        chancefailalarm1 = in2.readLine();
        chancefailalarm2 = in2.readLine();
        chancefailalarm3 = in2.readLine();
        in2.close();
        }catch(IOException e){}
        
        if(server.equals("1"))
            currentchancefail = chancefailalarm1;
        if(server.equals("2"))
            currentchancefail = chancefailalarm2;
        if(server.equals("3"))
            currentchancefail = chancefailalarm3;
        
        return currentchancefail;
    }
}
