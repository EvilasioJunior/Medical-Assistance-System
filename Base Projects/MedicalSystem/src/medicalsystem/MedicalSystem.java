/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalsystem;
import java.io.*;
import webservice.WSFuncs;
import webservice.Webservice;
/**
 *
 * @author Evilasio
 */
public class MedicalSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
                          
        //Features (Services)
        Webservice service = new Webservice();
        
        //Weservices functions
        WSFuncs funcs = new WSFuncs();

        //Graphic Interface
        MedicalSystemView view = new MedicalSystemView();
                
        //Specific variables
        Situation situation = Situation.Observation;
        Patient patient = new Patient("Carlos", situation);
        String aux;
        
        //File used for Stop Criteria of the Adapter
        BufferedWriter initialsituation = new BufferedWriter(new FileWriter("Situation.txt"));
        initialsituation.write("");  
        initialsituation.flush();
        initialsituation.close();
        
        //Executing Ambient
        String[] commands = {"","",""};
        commands[0] = "java";
        commands[1] = "-jar";
        commands[2] = "C:\\Users\\Evilasio\\Documents\\NetBeansProjects\\MedicalSystem\\Ambiente"+
                      "\\dist\\Ambient.jar"; //Path od the GenericAdapterProgram.jar 
        Runtime.getRuntime().exec(commands);
        
        //Executing Adapter
        String[] commandsadapter = {"","",""};
        commandsadapter[0] = "java";
        commandsadapter[1] = "-jar";
        commandsadapter[2] = "C:\\Users\\Evilasio\\Documents\\NetBeansProjects\\MedicalSystem\\AdaptadorMedicalSystem_1.5"+
                             "\\dist\\AdaptadorMedicalSystem_1.5.jar"; //Path od the GenericAdapterProgram.jar 
        Runtime.getRuntime().exec(commandsadapter);

       while (situation != Situation.Cured) //inicializando loop
       {       
            //Graphic Interface
            //Loads services
            view.update();
            //Find out whether the alarm has been triggered or not    
            service = view.VerifyActuationAlarm(patient, view.getAlarm());
            
            //If the alarm isn't triggered manually
            if(view.getAlarm() == null || view.getAlarm().equals("")){
                //invokes analysis system
                situation = Situation.valueOf(funcs.fService(service, patient.getSituation().toString(), view.getSymptoms()));
                
                //invokes on action to be taken after the situation be updated
                aux = view.ReturnActionPostAnalysis(situation, patient);
                if(!aux.equals("")){
                    service = view.ReturnServicePostAnalysis(aux);   
                    System.out.println(funcs.fService(service, ""));
                }
            }
            //If the alarm is triggered manually
            else
                System.out.println(funcs.fService(service, ""));  
            
            try {  
                if(!(patient.getSituation() == Situation.Cured))
                Thread.sleep(5*1000); //each 5 seconds 
             } catch (Exception e) {  
                e.printStackTrace();  
             }              
    }
     
    //Inform Situation of the Patient for Environment
    BufferedWriter finalsituation = new BufferedWriter(new FileWriter("Situation.txt"));
    finalsituation.write("Cured");  
    finalsituation.close();             
  }
}
    
    

