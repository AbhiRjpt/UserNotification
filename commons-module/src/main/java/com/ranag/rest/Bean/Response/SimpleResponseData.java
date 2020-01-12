package com.ranag.rest.Bean.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SimpleResponseData extends OrgResponseData {

    @Override
    public String toString() {
        return "SimpleResponseData{}" +super.toString();
    }

    public static void main(String[] args) {
        try {
            System.out.println(new ObjectMapper().writeValueAsString(new SimpleResponseData()));
        }catch (Exception e){

        }
    }
}
