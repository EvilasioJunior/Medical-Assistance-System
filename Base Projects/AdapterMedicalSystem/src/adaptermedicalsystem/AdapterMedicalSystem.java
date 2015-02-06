/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package adaptermedicalsystem;
import adapter.Analysis;
import adapter.Adapter;
import adapter.Planning;
import adapter.Monitoring;
import java.io.*;
import java.util.List;
/**
 *
 * @author Evilasio
 */
public class AdapterMedicalSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            AdapterObserver sf = new AdapterObserver();
            
            //Files contains list of web services monitors, analyzers anda planners
            File FilePlans = new File("C:\\Users\\Evilasio\\Documents\\NetBeansProjects\\MedicalSystem\\FilePlans.xml");
            File FileMonitors = new File("C:\\Users\\Evilasio\\Documents\\NetBeansProjects\\MedicalSystem\\FileMonitors.xml");
            File FileAnalysis = new File("C:\\Users\\Evilasio\\Documents\\NetBeansProjects\\MedicalSystem\\FileAnalysis.xml");
                       
            //Set list based to Files
            List<Monitoring> monitors = sf.FileXMLtoMonitors(FileMonitors);
            List<Analysis> analyzers = sf.FileXMLtoAnalyzers(FileAnalysis);
            List<Planning> planners = sf.FileXMLtoPlanners(FilePlans);
            
            //Set Adapter for use files
            Adapter adapter = new Adapter(monitors, analyzers, planners);
            
            //Set list observer
            adapter.setObserverListAnalysis(monitors);
            adapter.setObserverListPlanning(analyzers);
            
            //Set observers for Specific Funtions
            sf.setObserver(adapter);
            
            //Execute Adaptation
            adapter.run();
    }    

}