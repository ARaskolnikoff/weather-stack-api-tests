package com.weatherstack.runner;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

/**
 * Класс для запуска тестовых сценариев, написанных с использованием Cucumber.
 *
 * @author Петр Белинский
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.weatherstack.defenitions")
public class CucumberRunnerTest {

}