/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jr;

import java.io.*;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/** 
 *
 * @author junior
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class AnalyzerWindows{
   @WebMethod
    public String funcService(String file, String filemonitor){
        //Control variables
        String result = ""; 
        ArrayList<String> chancefail = new ArrayList<String>();
        String Alarm = "";
        String aux;
        int count = 0, countaux=0;
        
        try{
        //Finding alarm values
        BufferedReader inAlarme = new BufferedReader(new FileReader(filemonitor));
        aux = inAlarme.readLine();
        //If the monitor file is null returns null
        if (aux==null || aux.equals(""))
            return null;

        //Get chancefail values
        do{
        aux = inAlarme.readLine();
        if(aux==null)
            break;
        chancefail.add(aux.split(": ")[1]);
        }while(true);
        
        while(!result.equals("true")||count==3){
            //check order of alarms
            switch(count){
                case 0:
                    countaux=count+1;
                    break;
                case 1:
                    countaux=count-1;
                    break;
                case 2:
                    countaux=count;
                    break;
            }
            //keeping signature of the current alarm
            Alarm = "Alarm "+Integer.toString(countaux+1);

            //Analyze fail values    
            if(Double.parseDouble(chancefail.get(countaux))>0.041)
                result = "false";
            else
                result = "true";    
            //gets the next alarm value
            count++;
        }
        inAlarme.close();
        }catch(IOException e){}
        
        if (result.equals("false"))
            Alarm = "alarm 2";
        
        //fills result file
        try{
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write("Alarm"); out.newLine();
        out.write(Alarm);
        out.close();
        }catch(IOException e){}
        
        return "";
    }
}
