/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.tipo_cambio.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.springframework.xml.transform.StringSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import java.io.StringReader;

import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

@Component
public class BancoGuatemalaClient {

    private static final String SOAP_URL = "https://www.banguat.gob.gt/variables/ws/TipoCambio.asmx";
    private static final String SOAP_ACTION = "http://www.banguat.gob.gt/variables/ws/TipoCambioDiaString";

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    public String obtenerTipoCambio() {
        String requestPayload = "<Envelope xmlns=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                                "<Body>" +
                                "<TipoCambioDiaString xmlns=\"http://www.banguat.gob.gt/variables/ws/\"/>" +
                                "</Body>" +
                                "</Envelope>";

        StringSource requestSource = new StringSource(requestPayload);
        StringWriter responseWriter = new StringWriter();

        // Utilizamos SoapActionCallback para agregar el encabezado SOAPAction
        SoapActionCallback soapActionCallback = new SoapActionCallback(SOAP_ACTION);

        // Envía la solicitud SOAP y recibe la respuesta
        webServiceTemplate.sendSourceAndReceiveToResult(
                SOAP_URL,
                requestSource,
                soapActionCallback,
                new StreamResult(responseWriter)
        );

        // Procesa y devuelve la respuesta del tipo de cambio
        return procesarRespuesta(responseWriter.toString());
    }

    private String procesarRespuesta(String xmlResponse) {
        try {
        // Convertimos la respuesta en un documento XML
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(xmlResponse)));

        // Creamos una expresión XPath para extraer el valor del tipo de cambio
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xpath = xPathFactory.newXPath();
        XPathExpression expr = xpath.compile("//*[local-name()='valor']/text()");

        // Evaluamos la expresión para obtener el valor
        String tipoCambio = (String) expr.evaluate(doc, XPathConstants.STRING);
        return tipoCambio;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
    }
}


