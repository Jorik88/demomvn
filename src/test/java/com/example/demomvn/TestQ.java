package com.example.demomvn;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Test;
import org.w3c.dom.Element;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.Diff;
import org.xmlunit.util.Nodes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TestQ {

    @Test
    public void test() throws FileNotFoundException {
        FileInputStream fis1 = new FileInputStream("src/test/resources/test260.xml");
        FileInputStream fis2 = new FileInputStream("src/test/resources/test260!.xml");

        // using BufferedReader for improved performance
        BufferedReader source = new BufferedReader(new InputStreamReader(fis1));
        BufferedReader  target = new BufferedReader(new InputStreamReader(fis2));

        //configuring XMLUnit to ignore white spaces
        XMLUnit.setIgnoreWhitespace(true);
        System.out.println(com(source, target));
    }

    public boolean com(Reader source, Reader target){
        Diff diff = DiffBuilder
                .compare(source)
                .withTest(target)
//                .withNodeFilter(node -> !node.getNodeName().equals("Snils"))
                .build();
        return diff.hasDifferences();
    }

    @Test
    public void test2() throws IOException {
        FileInputStream fis1 = new FileInputStream("src/test/resources/test260.xml");
        FileInputStream fis2 = new FileInputStream("src/test/resources/test260!.xml");
        InputStream stream = TestQ.class.getClassLoader().getResourceAsStream("test260.xml");
        InputStream stream2 = TestQ.class.getClassLoader().getResourceAsStream("test260!.xml");
        System.out.println(stream);

//        Diff d = DiffBuilder.compare(Input.fromReader())
    }

    @Test
    public void test1() {
        List<String> exclude = new ArrayList<>();
        exclude.add("Snils");
        Diff d = DiffBuilder.compare("<DetailsSearchResidentPreferentialCategoriesFullResponse xmlns:ns2=\"http://asur/dszn/\" xmlns=\"http://asur/dszn/\">\n" +
                "    <Result>\n" +
                "        <DetailsSearchResidentPreferentialCategories>\n" +
                "            <EndDate>0001-01-01T00:00:00</EndDate>\n" +
                "            <Snils>125-471-254 42</Snils>\n" +
                "            <NamePreferentialCategory>Ветеран труда</NamePreferentialCategory>\n" +
                "            <PreferentialCategoryId>15</PreferentialCategoryId>\n" +
                "            <StartDate>1997-03-04T00:00:00+03:00</StartDate>\n" +
                "            <StartDateReasons>\n" +
                "                <ReasonDocument>\n" +
                "                    <DateOfIssue xmlns:ns3=\"uri:citsp-br-social-support\" xmlns=\"uri:citsp-br-social-support\">1997-03-\n" +
                "                        04T00:00:00+03:00\n" +
                "                    </DateOfIssue>\n" +
                "                    <Name xmlns:ns4=\"uri:citsp-br-social-support\" xmlns=\"uri:citsp-br-social-support\">удостоверение\n" +
                "                        \"Ветеран труда\"\n" +
                "                    </Name>\n" +
                "                    <Number xmlns:ns5=\"uri:citsp-br-social-support\" xmlns=\"uri:citsp-br-social-support\">606410</Number>\n" +
                "                    <PlaceOfIssue xmlns:ns6=\"uri:citsp-br-social-support\" xmlns=\"uri:citsp-br-social-support\">УСЗН\n" +
                "                        БУТЫРСКОГО РАЙОНА\n" +
                "                    </PlaceOfIssue>\n" +
                "                    <Series xmlns:ns7=\"uri:citsp-br-social-support\" xmlns=\"uri:citsp-br-social-support\">Т-II</Series>\n" +
                "                </ReasonDocument>\n" +
                "            </StartDateReasons>\n" +
                "        </DetailsSearchResidentPreferentialCategories>\n" +
                "        <DetailsSearchResidentPreferentialCategories>\n" +
                "            <EndDate>0001-01-01T00:00:00</EndDate>\n" +
                "            <Snils>125-471-254 42</Snils>\n" +
                "            <NamePreferentialCategory>Инвалид 2 группы</NamePreferentialCategory>\n" +
                "            <PreferentialCategoryId>22</PreferentialCategoryId>\n" +
                "            <StartDate>1998-04-17T00:00:00+04:00</StartDate>\n" +
                "            <StartDateReasons>\n" +
                "                <ReasonDocument>\n" +
                "                    <DateOfIssue xmlns:ns8=\"uri:citsp-br-social-support\" xmlns=\"uri:citsp-br-social-support\">1998-04-\n" +
                "                        17T00:00:00+04:00\n" +
                "                    </DateOfIssue>\n" +
                "                    <Name xmlns:ns9=\"uri:citsp-br-social-support\" xmlns=\"uri:citsp-br-social-support\">справка МСЭ об\n" +
                "                        установлении инвалидности\n" +
                "                    </Name>\n" +
                "                    <Number xmlns:ns10=\"uri:citsp-br-social-support\" xmlns=\"uri:citsp-br-social-support\">94431</Number>\n" +
                "                    <PlaceOfIssue xmlns:ns11=\"uri:citsp-br-social-support\" xmlns=\"uri:citsp-br-social-support\">БМСЭ\n" +
                "                        Бутырское\n" +
                "                    </PlaceOfIssue>\n" +
                "                    <Series xmlns:ns12=\"uri:citsp-br-social-support\" xmlns=\"uri:citsp-br-social-support\">264</Series>\n" +
                "                </ReasonDocument>\n" +
                "            </StartDateReasons>\n" +
                "        </DetailsSearchResidentPreferentialCategories>\n" +
                "    </Result>\n" +
                "    <DocumentReady>true</DocumentReady>\n" +
                "    <InputData>\n" +
                "        <ServiceProperties xmlns=\"\">\n" +
                "            <snils>125-471-254 42</snils>\n" +
                "        </ServiceProperties>\n" +
                "    </InputData>\n" +
                "</DetailsSearchResidentPreferentialCategoriesFullResponse>")
                .withTest("<DetailsSearchResidentPreferentialCategoriesFullResponse xmlns:ns2=\"http://asur/dszn/\" xmlns=\"http://asur/dszn/\">\n" +
                        "    <Result>\n" +
                        "        <DetailsSearchResidentPreferentialCategories>\n" +
                        "            <EndDate>0001-01-01T00:00:00</EndDate>\n" +
                        "            <Snils>125-471-254 42</Snils>\n" +
                        "            <NamePreferentialCategory>Ветеран труда</NamePreferentialCategory>\n" +
                        "            <PreferentialCategoryId>15</PreferentialCategoryId>\n" +
                        "            <StartDate>1997-03-04T00:00:00+03:00</StartDate>\n" +
                        "            <StartDateReasons>\n" +
                        "                <ReasonDocument>\n" +
                        "                    <DateOfIssue xmlns:ns3=\"uri:citsp-br-social-support\" xmlns=\"uri:citsp-br-social-support\">1997-03-\n" +
                        "                        04T00:00:00+03:00\n" +
                        "                    </DateOfIssue>\n" +
                        "                    <Name xmlns:ns4=\"uri:citsp-br-social-support\" xmlns=\"uri:citsp-br-social-support\">удостоверение\n" +
                        "                        \"Ветеран труда\"\n" +
                        "                    </Name>\n" +
                        "                    <Number xmlns:ns5=\"uri:citsp-br-social-support\" xmlns=\"uri:citsp-br-social-support\">606410</Number>\n" +
                        "                    <PlaceOfIssue xmlns:ns6=\"uri:citsp-br-social-support\" xmlns=\"uri:citsp-br-social-support\">УСЗН\n" +
                        "                        БУТЫРСКОГО РАЙОНА\n" +
                        "                    </PlaceOfIssue>\n" +
                        "                    <Series xmlns:ns7=\"uri:citsp-br-social-support\" xmlns=\"uri:citsp-br-social-support\">Т-II</Series>\n" +
                        "                </ReasonDocument>\n" +
                        "            </StartDateReasons>\n" +
                        "        </DetailsSearchResidentPreferentialCategories>\n" +
                        "        <DetailsSearchResidentPreferentialCategories>\n" +
                        "            <EndDate>0001-01-01T00:00:00</EndDate>\n" +
                        "            <Snils>125-471-254 42</Snils>\n" +
                        "            <NamePreferentialCategory>Инвалид 2 группы</NamePreferentialCategory>\n" +
                        "            <PreferentialCategoryId>22</PreferentialCategoryId>\n" +
                        "            <StartDate>1998-04-17T00:00:00+04:00</StartDate>\n" +
                        "            <StartDateReasons>\n" +
                        "                <ReasonDocument>\n" +
                        "                    <DateOfIssue xmlns:ns8=\"uri:citsp-br-social-support\" xmlns=\"uri:citsp-br-social-support\">1998-04-\n" +
                        "                        17T00:00:00+04:00\n" +
                        "                    </DateOfIssue>\n" +
                        "                    <Name xmlns:ns9=\"uri:citsp-br-social-support\" xmlns=\"uri:citsp-br-social-support\">справка МСЭ об\n" +
                        "                        установлении инвалидности\n" +
                        "                    </Name>\n" +
                        "                    <Number xmlns:ns10=\"uri:citsp-br-social-support\" xmlns=\"uri:citsp-br-social-support\">94431</Number>\n" +
                        "                    <PlaceOfIssue xmlns:ns11=\"uri:citsp-br-social-support\" xmlns=\"uri:citsp-br-social-support\">БМСЭ\n" +
                        "                        Бутырское\n" +
                        "                    </PlaceOfIssue>\n" +
                        "                    <Series xmlns:ns12=\"uri:citsp-br-social-support\" xmlns=\"uri:citsp-br-social-support\">264</Series>\n" +
                        "                </ReasonDocument>\n" +
                        "            </StartDateReasons>\n" +
                        "        </DetailsSearchResidentPreferentialCategories>\n" +
                        "    </Result>\n" +
                        "    <DocumentReady>true</DocumentReady>\n" +
                        "    <InputData>\n" +
                        "        <ServiceProperties xmlns=\"\">\n" +
                        "            <snils>125-471-254 42</snils>\n" +
                        "        </ServiceProperties>\n" +
                        "    </InputData>\n" +
                        "</DetailsSearchResidentPreferentialCategoriesFullResponse>")
//                .withNodeFilter(n -> !(n instanceof Element && "Snils".equals(Nodes.getQName(n).getLocalPart())))
                .withNodeFilter(n -> !exclude.contains(n.getNodeName()))
                        //                .withNodeFilter(node -> !node.getNodeName().equals("Snils"))
                .build();
        System.err.println("Different? " + d.hasDifferences());
        System.out.println(d.getDifferences());
    }

    @Test
    public void test22() {
        String s = "<StartDateReasons xmlns:ns8=\"uri:citsp-br-social-support\">\n" +
                "                <ReasonDocument>\n" +
                "                    <DateOfIssue xmlns:ns8=\"uri:citsp-br-social-support\" xmlns=\"uri:citsp-br-social-support\">1998-04-\n" +
                "                        17T00:00:00+04:00\n" +
                "                    </DateOfIssue>\n" +
                "                    <Name xmlns:ns9=\"uri:citsp-br-social-support\" xmlns=\"uri:citsp-br-social-support\">справка МСЭ об\n" +
                "                        установлении инвалидности\n" +
                "                    </Name>\n" +
                "                    <Number xmlns:ns10=\"uri:citsp-br-social-support\" xmlns=\"uri:citsp-br-social-support\">94431</Number>\n" +
                "                    <PlaceOfIssue xmlns:ns11=\"uri:citsp-br-social-support\" xmlns=\"uri:citsp-br-social-support\">БМСЭ\n" +
                "                        Бутырское\n" +
                "                    </PlaceOfIssue>\n" +
                "                    <Series xmlns:ns12=\"uri:citsp-br-social-support\" xmlns=\"uri:citsp-br-social-support\">264</Series>\n" +
                "                </ReasonDocument>\n" +
                "            </StartDateReasons>";


        String s1 = StringUtils.substringBetween(s, "<StartDateReasons>", "</StartDateReasons>");
        System.out.println(s1);
    }
}
