package com.example.demomvn.configuration;

import java.util.*;

public class Configuration {

    public static final String CODE_260 = "260";
    public static final String CODE_10615 = "10615";
    public static final String CODE_77290 = "77290";

    private Map<String, List<String>> codeAndExcludedNodesMap;


    public void init() {

        try {
            Properties properties = new Properties();
            String filePath = "compare.properties";
            properties.load(Configuration.class.getClassLoader().getResourceAsStream(filePath));

            codeAndExcludedNodesMap = new HashMap<>();
            codeAndExcludedNodesMap.put(CODE_260, Arrays.asList(properties.getProperty("excluded.code260").split(",")));
            codeAndExcludedNodesMap.put(CODE_10615, Arrays.asList(properties.getProperty("excluded.code10615").split(",")));
            codeAndExcludedNodesMap.put(CODE_77290, Arrays.asList(properties.getProperty("excluded.code77290").split(",")));
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, List<String>> getCodeAndExcludedNodesMap() {
        return codeAndExcludedNodesMap;
    }
}
