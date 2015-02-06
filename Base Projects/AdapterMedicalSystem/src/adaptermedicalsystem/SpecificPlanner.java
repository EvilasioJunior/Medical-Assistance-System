/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adaptermedicalsystem;

import adapter.Planning;
import webservice.Webservice;

/**
 *
 * @author Evilasio
 */
public class SpecificPlanner extends Planning{
    
    public SpecificPlanner(Webservice wDesigner){
        this.setPlanning(wDesigner);
    }
    
    public SpecificPlanner(){
    }
    
    @Override
    public void plan(){
        this.planWebservice();
    }
}
