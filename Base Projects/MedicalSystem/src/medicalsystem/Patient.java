/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalsystem;
/**
 *
 * @author Evilasio
 */
public class Patient {
    
    public String name;
    private Situation situation;
    
    public Patient(String Name, Situation patientsituation){
        this.name = Name;
        this.situation = patientsituation;
    } 
                    
    public Situation getSituation(){
        return this.situation;
    }     
    
    public void setSituacao(Situation currentsituation){
        this.situation = currentsituation;
    }
    
    public void setSituacao(String currentsituation){
        this.situation = Situation.valueOf(currentsituation);
    }
}
