package com.lawyer.pdfgeneration;

import java.io.IOException;

import javassist.convert.Transformer;

import javax.xml.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PRIndirectReference;
import com.itextpdf.text.pdf.PRStream;
import com.itextpdf.text.pdf.PRTokeniser;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.RandomAccessFileOrArray;

public class ConvertPDFToXML {
    static StreamResult streamResult;
    static TransformerHandler handler;
    static AttributesImpl atts;

    public static void main(String[] args) throws IOException {

            try {
                    Document document = new Document();
                    document.open();
                    PdfReader reader = new PdfReader("C:\\hello.pdf");
                    PdfDictionary page = reader.getPageN(1);
                    PRIndirectReference objectReference = (PRIndirectReference) page
                                    .get(PdfName.CONTENTS);
                    PRStream stream = (PRStream) PdfReader
                                    .getPdfObject(objectReference);
                    byte[] streamBytes = PdfReader.getStreamBytes(stream);
                    PRTokeniser tokenizer = new PRTokeniser(streamBytes);

                    StringBuffer strbufe = new StringBuffer();
                    while (tokenizer.nextToken()) {
                            if (tokenizer.getTokenType() == PRTokeniser.TK_STRING) {
                                    strbufe.append(tokenizer.getStringValue());
                            }
                    }
                    String test = strbufe.toString();
                    streamResult = new StreamResult("data.xml");
                    initXML();
                    process(test);
                    closeXML();
                    document.add(new Paragraph(".."));
                    document.close();
            } catch (Exception e) {
            }
    }

    public static void initXML() throws ParserConfigurationException,TransformerConfigurationException, SAXException 
    {
            SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory
                            .newInstance();

            handler = tf.newTransformerHandler();
            javax.xml.transform.Transformer serializer = handler.getTransformer();
            serializer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            serializer.setOutputProperty(
                            "{http://xml.apache.org/xslt}indent-amount", "4");
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            handler.setResult(streamResult);
            handler.startDocument();
            atts = new AttributesImpl();
            handler.startElement("", "", "data", atts);
    }

    public static void process(String s) throws SAXException {
            String[] elements = s.split("\\|");
            atts.clear();
            handler.startElement("", "", "Message", atts);
            handler.characters(elements[0].toCharArray(), 0, elements[0].length());
            handler.endElement("", "", "Message");
    }

    public static void closeXML() throws SAXException {
            handler.endElement("", "", "data");
            handler.endDocument();
    }
}