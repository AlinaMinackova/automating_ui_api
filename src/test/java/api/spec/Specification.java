package api.spec;

import api.helpers.CustomAllureListener;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;

public class Specification {
    public static RequestSpecification requestSpec = with()
            .log().all()
            .baseUri("https://fakestoreapi.com")
            .header("Content-Type", "application/json")
            .filter(CustomAllureListener.withCustomTemplates());
//
    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .build();
}
