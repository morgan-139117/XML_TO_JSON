/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.xml_to_json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import java.io.IOException;
import java.util.List;

class TransactionLine {
    @JacksonXmlText
    public long LineNumber;
}
class TransactionDetailGroup {
    public List<TransactionLine> TransactionLine;
}
public class Test {
    public static void main(String[] args) throws JsonProcessingException, IOException {
        String xml = "<TransactionDetailGroup><TransactionLine><LineNumber>1</LineNumber></TransactionLine></TransactionDetailGroup>";
        XmlMapper xmlMapper = new XmlMapper();
        Object entry = xmlMapper.readValue(xml, TransactionDetailGroup.class);   
        ObjectMapper jsonMapper = new ObjectMapper();
        System.out.println(jsonMapper.writeValueAsString(entry));
    }
}


//{"TransactionLine":{"LineNumber":1}}   ===> {"TransactionLine":[{"LineNumber":1}]} 