/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package adapter;

import java.io.File;
import webservice.WSFuncs;
import webservice.Webservice;

/**
 *
 * @author junior
 */
public abstract class Planning{

    private File ValuesAnalysis;
    private Webservice wPlanning;
   
    //Atributtes for Observer
    private Analysis analyzerObserved; 
    
    public File getValuesAnalysis(){
        return this.ValuesAnalysis;
    }
    
    //Set new planner
    public void setPlanning(Webservice wPlanning){
        this.wPlanning = wPlanning;
    }
    
    //Set new object observer
    public void setObserver(Analysis a){
        this.analyzerObserved = a;
	analyzerObserved.addDesigner(this);
    }
    
    //Planning
    public void planWebservice(){
        WSFuncs service = new WSFuncs();
        service.fService(wPlanning, this.ValuesAnalysis.getAbsolutePath());
    }
    
    //Method for Observer    
    public void update(Analysis m) {
		if(m == analyzerObserved){
                    this.ValuesAnalysis = analyzerObserved.getValueAnalysis();
                    plan();
		}
	}
    
    //----Abstract Method----//
    //planning
    public abstract void plan();

}
