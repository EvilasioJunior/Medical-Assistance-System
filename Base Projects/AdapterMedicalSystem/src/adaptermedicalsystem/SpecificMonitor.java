/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adaptermedicalsystem;

import adapter.Monitoring;
import webservice.Webservice;

/**
 *
 * @author Evilasio
 */
public class SpecificMonitor extends Monitoring{
    
    public SpecificMonitor(Webservice wMonitor){
        this.setMonitoring(wMonitor);
    }
    
    public SpecificMonitor(){
    }

    @Override
    public void monitoring(){
        this.monitoringWebservice();
        //Alert Observers
        this.Notify();
        
        //Time until next read
        try {  
           Thread.sleep(getTime()*1000);  //each getTime() seconds
        } catch (Exception e) {  
        }
    }
    //alterAlert();
     
    
}
