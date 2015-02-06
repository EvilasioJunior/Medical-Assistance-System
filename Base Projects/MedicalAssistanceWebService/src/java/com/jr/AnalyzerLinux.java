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
public class AnalyzerLinux{
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
        BufferedReader inAlarm = new BufferedReader(new FileReader(filemonitor));
        aux = inAlarm.readLine();
        //If the monitor file is null returns null
        if (aux==null || aux.equals(""))
            return null;

        //Get chancefail values
        do{
        aux = inAlarm.readLine();
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

            try {
            //drives the prism for second property
            String[] commands = {"", "", "", "", "", "", "", "", ""};
            commands[0] = "/home/junior/Downloads/prism-4.1.beta2-src/bin/prism";
            commands[1] = "/home/junior/Downloads/prism-4.1.beta2-src/MeusModelos/Assistente_Medico.pm";
            commands[2] = "/home/junior/Downloads/prism-4.1.beta2-src/MeusModelos/Assistente_Medico.pctl";
            commands[3] = "-const";
            commands[4] = "x="+chancefail.get(countaux)+",y=0.0015,z=0.0012";
            commands[5] = "-prop";
            commands[6] = "2";
            commands[7] = "-exportresults";
            commands[8] = "/home/junior/Downloads/MedicalSystem/AdaptadorMedicalSystem/result.txt";
            Process p  = Runtime.getRuntime().exec(commands);

            //time for execution
               Thread.sleep(1*3000);  //each 3 seconds 
            } catch (Exception e) {  
               e.printStackTrace();  
            }
            //verify results
            BufferedReader in3 = new BufferedReader(new FileReader("/home/junior/Downloads/MedicalSystem/AdaptadorMedicalSystem/result.txt"));
            in3.readLine();
            result = in3.readLine();
            in3.close();
            //gets the next alarm value
            count++;
        }
        inAlarm.close();
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
