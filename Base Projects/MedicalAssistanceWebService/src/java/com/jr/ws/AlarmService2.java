/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jr.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author junior
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class AlarmService2 {
    @WebMethod
    public String funcService(String alarme){
        return "Alarm 2 fires! Patient in serious condition, need immediate care.";
    }
}