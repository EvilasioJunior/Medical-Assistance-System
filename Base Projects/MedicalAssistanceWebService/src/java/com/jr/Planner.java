/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jr;

import java.io.*;
import java.net.Socket;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.util.ArrayList;
import webservice.Webservice;

/**
 *
 * @author junior
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Planner {
   @WebMethod
    public String funcService(String fileanalysis){
        File fileservices = new File("C:\\Users\\Evilasio\\Documents\\NetBeansProjects\\MedicalSystem\\SummaryWebservices.xml");
        File controlfile = new File("C:\\Users\\Evilasio\\Documents\\NetBeansProjects\\MedicalSystem\\AdapterVariable.txt");
        int idaux;
        String value = "", currentvalue = "";
        
        XStream parser = new XStream(new DomDriver("ISO-8859-1"));
        parser.setMode(XStream.NO_REFERENCES); 
        ArrayList<Webservice> lstwebservice = (ArrayList<Webservice>)parser.fromXML(fileservices);
        //fill alarm values
        try{
                //Current file
                BufferedReader incurrentalarm = new BufferedReader(new FileReader(controlfile));
                do{
                    currentvalue = incurrentalarm.readLine();
                    if(currentvalue == null){
                          currentvalue="";
                          break;
                    }
                } while(!currentvalue.equals("Alarm"));
                currentvalue = incurrentalarm.readLine();
                if(currentvalue == null)
                          currentvalue="";
                incurrentalarm.close();

                //New value
                BufferedReader inalarme = new BufferedReader(new FileReader(fileanalysis));
                do{
                    value = inalarme.readLine();
                    //checks for data in the file filled by analysis
                    if(value == null){
                          value="";
                          break;
                    }
                } while(!value.equals("Alarm"));
                value= inalarme.readLine();
                if(value == null)
                          value="";
                inalarme.close();
            }catch (IOException e){}
        
            
        if (!value.equals("")){
            idaux = Integer.parseInt(value.split(" ")[1]);
            Webservice waux = lstwebservice.get(idaux-1);
            try{           

                        //Write data AnalysisService
                        Socket cliente1 = new Socket("", 12345);
                        PrintStream saida1 = new PrintStream(cliente1.getOutputStream());
                        saida1.println("http://jr.com/;AnalysisServiceService;http://localhost:8080/MeuWebService/AnalysisService?wsdl;AnalysisServicePort");
                        saida1.close();
                        cliente1.close();

                        //Write data DrugService
                        Socket cliente2 = new Socket("", 12346);
                        PrintStream saida2 = new PrintStream(cliente2.getOutputStream());
                        saida2.println("http://jr.com/;DrugServiceService;http://localhost:8080/MeuWebService/DrugService?wsdl;DrugServicePort");
                        saida2.close();
                        cliente2.close(); 
      
                        //Write data AlarmService
                        Socket cliente = new Socket("", 12347);
                        PrintStream saida = new PrintStream(cliente.getOutputStream());
                        saida.println(waux.gettargetName()+";"+waux.getName()+";"+waux.geturl()+";"+waux.getPort());
                        saida.close();
                        cliente.close();

            }catch(IOException e){}
        }
        //inform current alarm
        try{
        BufferedWriter alameatualw = new BufferedWriter(new FileWriter(controlfile));
        alameatualw.write("Alarm"); alameatualw.newLine();
        alameatualw.write(value);   
        alameatualw.close();
        }catch(IOException e){}
        
        return value;
    }
}
