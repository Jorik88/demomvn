package com.example.demomvn.service;


import com.example.demomvn.model.AmlCheckResult;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Objects;

@Slf4j
@Service
public class AmlService {

    private static final String DOCUMENT_PATH = "src/main/resources/aml.xml";
    private static final String DOCUMENT_ENCODING = "windows-1251";
    private static final String PARAMETERS_TAG_NAME = "parameters";
    private static final String P_TAG_NAME = "p";
    private static final String ATTRIBUTE_FULL_NAME_VALUE = "people_full_name";
    private static final String ATTRIBUTE_ADDRESS_VALUE = "person_address";
    private static final String ATTRIBUTE_DOCUMENT_VALUE = "people_document";
    private static final String DOCUMENT_TAG_NAME = "Description";
    private static final String AML_REQUEST_URL = "http://localhost:9001/itwGateWS/exec/FISPut";

    @Value("${aml.file.path}")
    private String filePath;


    //    @Scheduled(fixedDelay = 60000)
//    public void checkAml() {
//
//        try {
//
//            List<VerificationDataEntity> records = dataRepository.findFirst5ByCurrentStage("Ready_for_aml");
//
//            for (VerificationDataEntity entity : records) {
//
//                AmlCheckResult amlCheckResult = sendAmlRequest(entity);
//
//                if (amlCheckResult.getPassportData().isEmpty()
//                        && amlCheckResult.getPersonalData().isEmpty()
//                        && amlCheckResult.getAddressData().isEmpty()) {
//
//                    entity.setAmlCheckResult(amlCheckResult);
//                    verificationDataService.approve(entity);
//                } else {
//                    entity.setAmlCheckResult(amlCheckResult);
//                    verificationDataService.decline(entity, "Data of user was found in aml response");
//                }
//            }
//
//        } catch (Exception e) {
//            log.warn("Error while changing user verification stage", e);
//        }
//    }

    public AmlCheckResult sendAmlRequest() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(filePath);

        Node item = doc.getElementsByTagName(PARAMETERS_TAG_NAME).item(0);
        Element element = (Element) item;

        setContent(doc, element);
        doc.setXmlStandalone(true);

        OutputFormat format = new OutputFormat(doc);
        format.setEncoding(DOCUMENT_ENCODING);
        // as a String
        StringWriter stringOut = new StringWriter();
        XMLSerializer serial = new XMLSerializer(stringOut, format);
        serial.serialize(doc);
        OkHttpClient client = new OkHttpClient();

        okhttp3.MediaType mediaType = okhttp3.MediaType.parse("text/xml");
        RequestBody body = RequestBody.create(mediaType, stringOut.toString());
        Request request = new Request.Builder()
                .url(AML_REQUEST_URL)
                .post(body)
                .addHeader("content-type", "text/xml")
                .build();

        Response response = client.newCall(request).execute();

        return parseResponse(Objects.requireNonNull(response.body()).string());
    }

    private void setContent(Document doc, Element element) {

        Element pName = doc.createElement(P_TAG_NAME);
        pName.setAttribute("name", ATTRIBUTE_FULL_NAME_VALUE);
        pName.appendChild(doc.createTextNode("Иванов иван иванович"));
        element.appendChild(pName);

        Element pPassportData = doc.createElement(P_TAG_NAME);
        pPassportData.setAttribute("name", ATTRIBUTE_DOCUMENT_VALUE);
        pPassportData.appendChild(doc.createTextNode("AK353217"));
        element.appendChild(pPassportData);

        Element pAddress = doc.createElement(P_TAG_NAME);
        pAddress.setAttribute("name", ATTRIBUTE_ADDRESS_VALUE);
        pAddress.appendChild(doc.createTextNode("UNITED STATES, PATERSON, RAILWAY AVENUE, 345 E"));
        element.appendChild(pAddress);
    }

    public AmlCheckResult parseResponse(String responseString) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.parse(new InputSource(new StringReader(responseString)));

        NodeList childNodes = doc.getElementsByTagName(DOCUMENT_TAG_NAME).item(0).getChildNodes();
        NodeList nodes = ((Node) childNodes).getChildNodes();

        AmlCheckResult amlCheckResult = new AmlCheckResult();

        for (int i = 0; i < nodes.getLength(); i++) {
            if ("PEOPLE_FULL_NAME".equals(     nodes.item(i).getFirstChild().getTextContent())) {
                String textContent = nodes.item(i).getLastChild().getTextContent();
                amlCheckResult.getPersonalData().add(textContent);
            } else if ("PERSON_ADDRESS".equals(nodes.item(i).getFirstChild().getTextContent())) {
                String textContent = nodes.item(i).getLastChild().getTextContent();
                amlCheckResult.getAddressData().add(textContent);
            } else if ("PEOPLE_DOCUMENT".equals(nodes.item(i).getFirstChild().getTextContent())) {
                String textContent = nodes.item(i).getLastChild().getTextContent();
                amlCheckResult.getPassportData().add(textContent);
            }
        }
        return amlCheckResult;
    }

//    private String getFullName(Map<String, String> fields) {
//        String middleName = fields.get("middleName");
//        if (middleName == null) {
//            middleName = "";
//        }
//        return String.format("%s %s %s",
//                fields.get("firstName"),
//                fields.get("lastName"),
//                middleName);
//    }
//
//    private String getAddressData(Map<String, String> fields) {
//        String registrationAddress = fields.get("registrationAddress1");
//        if (registrationAddress.contains("undefined")) {
//            registrationAddress = registrationAddress.replace(" undefined", "");
//        }
//
//        String registrationCountryName =
//                countryDataService.getCountryNameByCountryCode(fields.get("registrationCountryCode"), getLang(registrationAddress));
//        return String.format("%s %s", registrationCountryName, registrationAddress);
//    }
//
//    private String getLang(String registrationAddress) {
//        if (registrationAddress.matches("[а-яА-Я0-9 ]*")) {
//            return "ru";
//        }
//        return "en";
//    }
}
