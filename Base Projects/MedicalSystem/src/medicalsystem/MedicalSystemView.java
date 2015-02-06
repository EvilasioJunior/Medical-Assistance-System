/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalsystem;

//Importações básicas necessárias
import java.io.*;
import webservice.Webservice;

/**
 *
 * @author Evilasio
 */

public class MedicalSystemView {
    
    String fileEnvironment = "C:\\Users\\Evilasio\\Documents\\NetBeansProjects\\MedicalSystem\\Ambiente\\InformationEnvironment.txt";;
    Webservice wAnalysis = new Webservice();
    Webservice wDrug = new Webservice();
    Webservice wAlarm = new Webservice();
    
    
    public MedicalSystemView(){
    }
    
    //Update services
    public void update(){
        try{ 
         this.wAnalysis.initializerBySocket(12345);
         this.wDrug.initializerBySocket(12346);
         this.wAlarm.initializerBySocket(12347); 
        }catch(Exception e){}
    }
    
    //Methods used to receive information from the environment
    public String getSymptoms(){
        //control variables
        String str = "";
 
        try{       
        BufferedReader in = new BufferedReader(new FileReader(fileEnvironment));                

        //Gets symptoms
        str = in.readLine();
        
        in.close();
        }catch (IOException e){} 
        
        return str;
    }
    
    public String getAlarm(){
        //control variables
        String alarm = "";
        try{       
        BufferedReader in = new BufferedReader(new FileReader(fileEnvironment));                

        //Alarm triggered manually?
        in.readLine();
        alarm = in.readLine();
        
        in.close();
        }catch (IOException e){}
        
        return alarm;
    }
    
    
    //Methods that treat environmental information
    public Webservice VerifyActuationAlarm(Patient patient, String alarm){
        Webservice waux = new Webservice();
        
       //Returns the port to be used by sockets
      if(alarm == null){
            alarm="";
            waux = this.wAnalysis;
      }else{
            if(alarm.equals("alarm")){
                patient.setSituacao(Situation.Serious);
                waux = this.wAlarm;
         }
        }
        
        return waux;
    }
    
    public String ReturnActionPostAnalysis(Situation situation, Patient patient){
        
        //Return of variable
        String stop = "";
       
        //Update the patient situation
        patient.setSituacao(situation);

        switch (patient.getSituation())
        {
            case Cured:
                System.out.println(patient.name + " is cured!");
                break;
            case Observation:
                System.out.println(patient.name + " must be observed");
                break;
            case NeedCare:
                System.out.println(patient.name + " 0need medication.");
                stop = "1"; //DrugService
                break;
            case Serious:
                stop = "2"; //AnalysisService
                break;
        }
        
        return stop;
    }
    
    public Webservice ReturnServicePostAnalysis(String tipo){
        Webservice waux = new Webservice();
        if(tipo.equals("1"))
            waux = this.wDrug;
        if(tipo.equals("2"))
            waux = this.wAlarm;
        return waux;
    }
}
