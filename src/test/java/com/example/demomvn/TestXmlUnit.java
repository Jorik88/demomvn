package com.example.demomvn;



import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Test;
import org.xml.sax.SAXException;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.builder.Input;


import java.io.*;
import java.util.List;

public class TestXmlUnit {


    @Test
    public void test() throws IOException, SAXException {
        // reading two xml file to compare in Java program
        FileInputStream fis1 = new FileInputStream("src/test/resources/test260.xml");
        FileInputStream fis2 = new FileInputStream("src/test/resources/test260!.xml");

        // using BufferedReader for improved performance
        BufferedReader source = new BufferedReader(new InputStreamReader(fis1));
        BufferedReader  target = new BufferedReader(new InputStreamReader(fis2));

        //configuring XMLUnit to ignore white spaces
        XMLUnit.setIgnoreWhitespace(true);

        //comparing two XML using XMLUnit in Java
        List<Difference> differences = compareXML(source, target);

        //showing differences found in two xml files
        printDifferences(differences);
    }

    @Test
    public void exclude() throws FileNotFoundException {
        // reading two xml file to compare in Java program
        FileInputStream fis1 = new FileInputStream("src/test/resources/test260.xml");
        FileInputStream fis2 = new FileInputStream("src/test/resources/test260!.xml");

        // using BufferedReader for improved performance
        BufferedReader  source = new BufferedReader(new InputStreamReader(fis1));
        BufferedReader  target = new BufferedReader(new InputStreamReader(fis2));

        //configuring XMLUnit to ignore white spaces
        XMLUnit.setIgnoreWhitespace(true);
        System.out.println(com(source, target));
    }

    public static List<Difference> compareXML(Reader source, Reader target) throws
            SAXException, IOException {

        //creating Diff instance to compare two XML files
        Diff xmlDiff = new Diff(source, target);

        //for getting detailed differences between two xml files
        DetailedDiff detailXmlDiff = new DetailedDiff(xmlDiff);


        return detailXmlDiff.getAllDifferences();
    }

    public static void printDifferences(List<Difference> differences){
        int totalDifferences = differences.size();
        System.out.println("===============================");
        System.out.println("Total differences : " + totalDifferences);
        System.out.println("================================");

        for(Difference difference : differences){
            System.out.println(difference);
        }
    }

    public boolean com(Reader source, Reader target){
        org.xmlunit.diff.Diff diff = DiffBuilder
                .compare(Input.fromReader(source))
                .withTest(Input.fromReader(target))
//                .withNodeFilter(node -> !node.getNodeName().equals("Snils"))
                .build();
       return diff.hasDifferences();
    }
}
