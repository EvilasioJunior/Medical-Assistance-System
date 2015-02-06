/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jr;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author junior
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class AnalysisService {
   @WebMethod
    public String funcService(String situation, String symptoms){
        
        int currentsituation=0;
        String newsituation = "";
        String s[];
        
        //code of the situations
        if (situation.equals("NeedCare"))
                currentsituation = 1;
        if (situation.equals("Serious"))
                currentsituation = 2;
        if (situation.equals("Observation"))
                currentsituation = 3;
        if (situation.equals("Cured"))
                currentsituation = 4;
        
        //Symptoms
        s = symptoms.split(",");
        
        //Analysis of symptoms
        switch (currentsituation) 
        {
        //In Observation    
        case 3://Observation
            if(s[0].equals("")){
                newsituation = "Cured";
            }
            else {
                    if(s.length>1){
                        if(s[1].equals("BreathingDifficulty"))
                            newsituation = "Serious";
                    }
                    else{
                        if(s[0].equals("Cough")){
                            newsituation = "NeedCare";
                        }
                    }
            }
            break;
        //Need Care
        case 1://Need Care
            if(s[0].equals("")){
                newsituation = "Observation";
            }
            else {
                    if(s.length>1){
                        if(s[1].equals("BreathingDifficulty"))
                            newsituation = "Serious";
                    }
            }
            break;   
        //Serious
        case 2://Serious
            if(s[0].equals("")){
                newsituation = "Observation";
            }
            else {
                   if(s.length==1){
                        if(s[0].equals("Cough")){
                            newsituation = "NeedCare";
                        }
                   }
            }
            break;
        }
        
        //If situation don't change, return current situation
        if(newsituation.equals(""))
            newsituation = situation;
        
        //Return situation
        return newsituation;
    }
}
