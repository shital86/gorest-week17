package com.studentapp.extractingresponsedata;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SearchJsonPathExample {

    static ValidatableResponse response;
    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI="https://gorest.co.in";

        response =given()
                .when()
                .get("/public/v2/users")
                .then().statusCode(200);
    }
    @Test
    public void getAllUsers(){

        List<Object> totalRecord=response.extract().path("");
        System.out.println("Total records are : "+totalRecord.size());

        List<String> idName=response.extract().path("findAll{it.id==2353}.name");
        System.out.println("id  has name : "+idName);

        List<String> idemail=response.extract().path("findAll{it.id==2353}.email");
        System.out.println("id has email : "+idemail);
        List<HashMap<String,?>> status = response.extract().path("findAll{it.status == 'active'}.id");
        System.out.println("All ID has status : "+status);

        List<String> gender=response.extract().path("findAll{it.id==2353}.gender");
        System.out.println("id  has gender : "+gender);

        List<String> gender1=response.extract().path("findAll{it.id==2348}.gender");
        System.out.println("id  has gender : "+gender1);
        System.out.println("------------------End of Test----");
    }

}
