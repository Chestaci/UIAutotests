package com.github.Chestaci.bdd;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        plugin = "json:target/cucumber-reports/CucumberTestReport.json",
        features = "src/test/resources/features",
        glue = "com.github.Chestaci.bdd.stepDefinitions")
public class CucumberRunnerTest extends AbstractTestNGCucumberTests {
    @DataProvider(parallel = true)
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }
}