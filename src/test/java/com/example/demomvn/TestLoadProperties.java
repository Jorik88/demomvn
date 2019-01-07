package com.example.demomvn;

import com.example.demomvn.configuration.Configuration;
import org.junit.Test;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static com.example.demomvn.configuration.Configuration.CODE_10615;
import static com.example.demomvn.configuration.Configuration.CODE_260;
import static com.example.demomvn.configuration.Configuration.CODE_77290;

public class TestLoadProperties {

    @Test
    public void testLoad() throws IOException {
        String fileName = "compare.properties";
        Properties properties = new Properties();
        properties.load(TestLoadProperties.class.getClassLoader().getResourceAsStream(fileName));

        String property = properties.getProperty("excluded.code260");

        List<String> prop = Arrays.asList(property.split(","));


        System.out.println(prop);
    }

    @Test
    public void testLoadPropertiesFromClass() {
        Configuration configuration = new Configuration();

        configuration.init();
        Map<String, List<String>> codeAndExcludedNodesMap = configuration.getCodeAndExcludedNodesMap();
        System.out.println(codeAndExcludedNodesMap.get(CODE_260));
        System.out.println(codeAndExcludedNodesMap.get(CODE_10615));
        System.out.println(codeAndExcludedNodesMap.get(CODE_77290));
    }
}
