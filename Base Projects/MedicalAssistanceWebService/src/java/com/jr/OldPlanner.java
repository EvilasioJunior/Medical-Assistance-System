/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jr;

import java.io.*;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author junior
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class OldPlanner {
   @WebMethod
    public String funcService(String valor, String valoratual){
        //String alarmeatual = "";
        int alarme = 0;
        String arquivo = "C:\\Users\\Evilasio\\Documents\\NetBeansProjects\\MedicalSystem\\Webservice.txt";
        String arquivosumario = "C:\\Users\\Evilasio\\Documents\\NetBeansProjects\\MedicalSystem\\SumarioWebservices.txt";
        String aux = "", idaux= "";
        
        try{
            //Obtém valor atual de alarme
            /*
            BufferedReader in = new BufferedReader(new FileReader("/home/junior/Downloads/MedicalSystem/AdaptadorMedicalSystem/Adaptar.txt"));
            alarmeatual = in.readLine();
            in.close();
            */
            
            //Verifica e seleciona adaptação se preciso
            if(!valor.equals(valoratual)){
                    
                    //limpa arquivos do adaptador
                    BufferedWriter aout = new BufferedWriter(new FileWriter(arquivo));
                    aout.write("");  
                    aout.flush();
                    aout.close();
                    
                    //preenche arquivo adaptador com webservice a ser utilizado
                    BufferedWriter awr = new BufferedWriter(new FileWriter(arquivo));
                    BufferedReader ins = new BufferedReader(new FileReader(arquivosumario));
                    alarme = Integer.parseInt(valor.split(" ")[1]);
                    switch(alarme){
                        case 1:
                            //Procurar webservice
                            do{
                            aux = ins.readLine();
                            if(aux.split(" ").length > 1)
                                idaux= aux.split(" ")[1];
                            }while(!idaux.equals(Integer.toString(alarme)));
                            
                            //Escrever dados do webservice selecionado
                            for(int i=0; i<4; i++){
                                aux = ins.readLine();
                                awr.write(aux); awr.newLine();
                            }
                            //Escrever argumento do webservice method
                            awr.write(" ");
                            break;
                        case 2:
                            //Procurar webservice
                            do{
                            aux = ins.readLine();
                            if(aux.split(" ").length > 1)
                                idaux= aux.split(" ")[1];
                            }while(!idaux.equals(Integer.toString(alarme)));
                            
                            //Escrever dados do webservice selecionado
                            for(int i=0; i<4; i++){
                                aux = ins.readLine();
                                awr.write(aux); awr.newLine();
                            }
                            //Escrever argumento do webservice method
                            awr.write(" ");
                            break;
                        case 3:
                            //Procurar webservice
                            do{
                            aux = ins.readLine();
                            if(aux.split(" ").length > 1)
                                idaux= aux.split(" ")[1];
                            }while(!idaux.equals(Integer.toString(alarme)));
                            
                            //Escrever dados do webservice selecionado
                            for(int i=0; i<4; i++){
                                aux = ins.readLine();
                                awr.write(aux); awr.newLine();
                            }
                            //Escrever argumento do webservice method
                            awr.write(" "); 
                            break;
                    }
                    ins.close();
                    awr.close();
                 }   
            
        }catch(IOException e){}
        
        return "Alarme "+valor.split(" ")[1];
    }
}