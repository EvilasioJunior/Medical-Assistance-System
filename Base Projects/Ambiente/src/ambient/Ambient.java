/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ambient;
import java.io.*;
import java.util.Random;
/**
 *
 * @author Evilasio
 */
public class Ambient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Random random = new Random();
        int percentsymptom, percentalarm;
        String situation= "";
        String filesituation = "C:\\Users\\Evilasio\\Documents\\NetBeansProjects\\MedicalSystem\\MedicalSystem\\Situation.txt";
        String informationFailEnvironment = "C:\\Users\\Evilasio\\Documents\\NetBeansProjects\\MedicalSystem\\Ambiente\\InformationFailEnvironment.txt";
        String informationEnvironment = "C:\\Users\\Evilasio\\Documents\\NetBeansProjects\\MedicalSystem\\Ambiente\\InformationEnvironment.txt";
        //System.out.println(random.nextInt(100));
        try {
            
         while(!situation.equals("Cured")){
                //Verify situation of the Patient
                BufferedReader in = new BufferedReader(new FileReader(filesituation));
                situation = in.readLine();
                if(situation == null)
                    situation="";
                if(situation.equals("Cured"))
                    continue;
                
                //Clean 
                BufferedWriter out = new BufferedWriter(new FileWriter(informationEnvironment));
                out.write("");  
                out.flush();
                out.close();

                BufferedWriter out2 = new BufferedWriter(new FileWriter(informationFailEnvironment));
                out2.write("");  
                out2.flush();
                out2.close();
                
                //Write
                percentsymptom = random.nextInt(100) + 1;
                percentalarm = random.nextInt(100) + 1;
                BufferedWriter wr = new BufferedWriter(new FileWriter(informationEnvironment));
                
                //symptom
                if(percentsymptom <=20){
                    wr.write("Cough, BreathingDifficulty");
                } else{
                    if(percentsymptom>=80)
                        wr.write("Cough");
                    else
                        wr.write("");
                }
                
                //pularlinha
                wr.newLine();

                //acionou o alarme
                if(percentalarm <=90)
                wr.write("");
                else
                wr.write("alarm");
                              
               //fechar arquivo 1
                wr.close();
             
             //tempo até próxima leitura
             try {  
                Thread.sleep(6*1000);  //a cada 6 segundos 
             } catch (Exception e) {  
                e.printStackTrace();  
             }
            
            }
         
            //limpasituacao 
            BufferedWriter outsituacao = new BufferedWriter(new FileWriter(filesituation));
            outsituacao.write("");  
            outsituacao.flush();
            outsituacao.close();
            
            //Informa fim das informações
            BufferedWriter outambiente = new BufferedWriter(new FileWriter(informationFailEnvironment));
            outambiente.write("curedpatient");  
            outambiente.close();
         
        } 
        catch (IOException e) {
        }
    }
    
}
