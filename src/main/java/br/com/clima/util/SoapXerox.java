package br.com.clima.util;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.util.HashMap;

@Getter
@Setter
@Slf4j
public class SoapXerox {

    public static SOAPElement callServicoXerox(SOAPElement element, HashMap<String, String> params) throws SOAPException {

        element.addChildElement("acao").setValue(params.get("acao"));
        element.addChildElement("matricula").setValue(params.get("matricula"));
        element.addChildElement("guid").setValue(params.get("guid"));
        element.addChildElement("escola").setValue(params.get("escola"));
        element.addChildElement("tipo").setValue(params.get("tipo"));
        element.addChildElement("estado").setValue(params.get("estado"));
        element.addChildElement("cidade").setValue(params.get("cidade"));
        element.addChildElement("confirmacao").setValue(params.get("confirmacao"));
        element.addChildElement("ticket").setValue(params.get("ticket"));

        return element;
    }
}
