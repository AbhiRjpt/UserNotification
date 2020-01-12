package com.ranag.rest.bean.request;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SimpleRequestData extends OrgRequestData {
    public static void main(String[] args) {
        try {
            System.out.println(new ObjectMapper().writeValueAsString(new SimpleRequestData()));
        }catch (Exception e){

        }
    }
}
