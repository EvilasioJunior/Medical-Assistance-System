/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adaptermedicalsystem;

import adapter.Adapter;
import adapter.Analysis;
import adapter.Monitoring;
import adapter.Planning;
import adapter.AbstractAdapterObserver;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import webservice.Webservice;

/**
 *
 * @author Evilasio
 */
public class AdapterObserver implements AbstractAdapterObserver{
    
    //Attributes Observer Observed
    private Adapter adapterObserved; 
    
    public AdapterObserver(){}    
    
    //Set new object observed
    public void setObserver(Adapter a){
        this.adapterObserved = a;
	adapterObserved.addAdapterObserver(this);
    }
    
    //Methods for Observer Observed   
    @Override
    public void update(Adapter a) {
		if(a == adapterObserved){
                    adapterObserved.setStop(verify());
		}
    }
  
    //-----Specific Functions-------//
    public Boolean verify(){
        String situationpatient;
        String informationFailEnvironment = "C:\\Users\\Evilasio\\Documents\\NetBeansProjects\\MedicalSystem\\Ambiente\\InformationFailEnvironment.txt";
        Boolean baux = true;
        
        try{
            //Verity situation of the patient
            BufferedReader in = new BufferedReader(new FileReader(informationFailEnvironment));                
            situationpatient = in.readLine();
            if(situationpatient!=null){
                if(situationpatient.equals("curedpatient"))
                    baux = false;
            in.close();
            
            //Clean file with situation of the patient
            BufferedWriter out = new BufferedWriter(new FileWriter(informationFailEnvironment));
            out.write("");  
            out.flush();
            out.close();    
            }
        }catch(IOException e){}
        
        return baux;
    }
        
    //---Specific Functions---//
    public List<Monitoring> FileXMLtoMonitors(File file){
        Monitoring monitoraux = new SpecificMonitor();
        List<Monitoring> Monitors = new ArrayList();
        List<Webservice> lwMonitoring = FiletoWebservicesXML(file); 
        
        for(Webservice wmaux: lwMonitoring){
            monitoraux.setMonitoring(wmaux);
            monitoraux.setValuesMonitor(wmaux.getarguments().get(0));
            Monitors.add(monitoraux);
        }
        return Monitors;
    }
    
    public List<Analysis> FileXMLtoAnalyzers(File file){
        Analysis analyzeraux = new SpecificAnalyzer();
        List<Analysis> Analyzers = new ArrayList();
        List<Webservice> lwAnalise = FiletoWebservicesXML(file); 
        
        for(Webservice waaux: lwAnalise){
            analyzeraux.setAnalysis(waaux);
            analyzeraux.setValuesAnalysis(waaux.getarguments().get(0));
            Analyzers.add(analyzeraux);
        }
        return Analyzers;
    }
    
    public List<Planning> FileXMLtoPlanners(File file){
        Planning planneraux = new SpecificPlanner();
        List<Planning> Designers = new ArrayList();
        List<Webservice> lwPlanning = FiletoWebservicesXML(file); 
        
        for(Webservice wpaux: lwPlanning){
            planneraux.setPlanning(wpaux);
            Designers.add(planneraux);
        }
        return Designers;
    }
  
    public static List<Webservice> FiletoWebservicesXML (File file){   
        XStream parser = new XStream(new DomDriver());
        parser.setMode(XStream.NO_REFERENCES);
        List<Webservice> lWaux = (List<Webservice>)parser.fromXML(file);
        
        return lWaux;
    } 
    
        
/*    
    public List<Monitoring> FiletoMonitors(File file){
        Monitoring monitoraux = new SpecificMonitor();
        List<Monitoring> Monitors = new ArrayList();
        List<Webservice> lwMonitoring = FiletoWebservices(file); 
        
        for(Webservice wmaux: lwMonitoring){
            monitoraux.setMonitoring(wmaux);
            monitoraux.setValuesMonitor(wmaux.getarguments().get(0));
            Monitors.add(monitoraux);
        }
        return Monitors;
    }
    
    public List<Analysis> FiletoAnalyzers(File file){
        Analysis analyzeraux = new SpecificAnalyzer();
        List<Analysis> Analyzers = new ArrayList();
        List<Webservice> lwAnalysis = FiletoWebservices(file); 
        
        for(Webservice waaux: lwAnalysis){
            analyzeraux.setAnalysis(waaux);
            analyzeraux.setValuesAnalysis(waaux.getarguments().get(0));
            Analyzers.add(analyzeraux);
        }
        return Analyzers;
    }
    
    public List<Planning> FiletoDesigners(File file){
        Planning planneraux = new SpecificDesigner();
        List<Planning> Planners = new ArrayList();
        List<Webservice> lwPlanning = FiletoWebservices(file); 
        
        for(Webservice wpaux: lwPlanning){
            planneraux.setPlanning(wpaux);
            Planners.add(planneraux);
        }
        return Planners;
    }
       
    public List<Webservice> FiletoWebservices(File file){
        String aux;
        String targetName = "", Name= "", url="", Port ="";
        Webservice waux;
        List<Webservice> laux = new ArrayList();
        
        try{    
            BufferedReader ins = new BufferedReader(new FileReader(file)); 
            aux = ins.readLine(); //first id
            
            if(aux!=null){
                do{
                    targetName = ins.readLine();
                    Name = ins.readLine();
                    url = ins.readLine();
                    Port = ins.readLine();
                    waux = new Webservice(targetName, Name, url, Port);
                    aux = ins.readLine();//argumentos
                    if(aux!=null) {
                        if(!aux.equals(""))
                         waux.addarguments(aux);  
                    }
                    laux.add(waux);

                    aux=ins.readLine();//próximo id
                }while(aux!=null);
            }
            ins.close();
        } catch(IOException e){}
                
        return laux;
    }   
    */
}
