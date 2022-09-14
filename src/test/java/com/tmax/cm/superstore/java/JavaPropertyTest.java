package com.tmax.cm.superstore.java;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// LINK https://cr.openjdk.java.net/~iris/se/16/spec/pr/java-se-16-pr-spec/api/system-properties.html
public class JavaPropertyTest {

    Logger logger = (Logger) LoggerFactory.getLogger(JavaPropertyTest.class);

    @Test
    void getClassPathTest() {

        String pathSeparator = System.getProperty("path.separator");
        logger.info("라인 구분자 '{}'", pathSeparator);

        Stream<String> stream = Arrays.stream(System.getProperty("java.class.path").split(pathSeparator));
        stream.filter(path -> !path.matches(".*gradle.*"))
                .filter(path -> !path.matches(".*vscode-server.*"))
                .forEach(path -> logger.info(path));

        logger.info(System.getProperty("ftp.proxyPort"));
    }
}
