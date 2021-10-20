package com.qa.stepDefs;

import com.qa.utilities.ConfigPropertyReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import static io.restassured.RestAssured.baseURI;

public class Hook {

    @Before
    public void setUp() {
        baseURI = ConfigPropertyReader.get("baseUrl");
    }

}
