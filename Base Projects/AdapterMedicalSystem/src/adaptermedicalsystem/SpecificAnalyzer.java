/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adaptermedicalsystem;

import adapter.Analysis;
import webservice.Webservice;

/**
 *
 * @author Evilasio
 */
public class SpecificAnalyzer extends Analysis{

    public SpecificAnalyzer(Webservice wAnalyzer){
        this.setAnalysis(wAnalyzer);
    }
    
    public SpecificAnalyzer(){
    }

    @Override
    public void analyze(){
        this.analyzeWebservice();
        this.Notify();
    }
     
}
