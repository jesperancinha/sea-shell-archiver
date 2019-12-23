package org.jesperancinha.shell.client.costumes;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.3.4
 * 2019-12-20T11:26:09.214+01:00
 * Generated source version: 3.3.4
 *
 */
@WebService(targetNamespace = "http://costumeor/SeaShellsWSDLCostumes/", name = "SeaShellsWSDLCostumes")
@XmlSeeAlso({ObjectFactory.class})
public interface SeaShellsWSDLCostumes {

    @WebMethod(action = "http://org.jesperancinha.shells/SeaShellsWSDLCostumes/costumes")
    @RequestWrapper(localName = "costumes", targetNamespace = "http://org.jesperancinha.shells/SeaShellsWSDLCostumes/", className = "org.jesperancinha.shell.client.costumes.Costumes")
    @ResponseWrapper(localName = "costumesResponse", targetNamespace = "http://org.jesperancinha.shells/SeaShellsWSDLCostumes/", className = "org.jesperancinha.shell.client.costumes.CostumesResponse")
    @WebResult(name = "Costume", targetNamespace = "")
    public Costume costumes(

            @WebParam(name = "costumeId", targetNamespace = "")
                    int costumeId
    );
}
