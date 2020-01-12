package com.ranag.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ranag.exception.ExceptionData;
import com.ranag.exception.InternalErrorCodes;
import com.ranag.exception.InternalException;
import com.ranag.rest.bean.response.OrgResponseData;
import com.ranag.rest.bean.response.SimpleResponseData;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestResponseService {

    public OrgResponseData createSuccessResponse(){
        OrgResponseData responseData = new OrgResponseData();
        responseData.setSuccess(true);
        return responseData;
    }

    public Response createSuccessResponse(OrgResponseData response) throws JsonProcessingException {
        response.setSuccess(true);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(response);
        return Response.ok(jsonString, MediaType.APPLICATION_JSON).build();
    }

    public OrgResponseData createFailureResponse(Exception e){
        ExceptionData ed = new ExceptionData("Operation Failed. Please contact to Admin.", InternalErrorCodes.INTERNAL_SERVER_ERROR);
        return setUpErrorResponse(ed, Response.Status.INTERNAL_SERVER_ERROR);
    }

    public OrgResponseData createFailureResponse(InternalException e){
        ExceptionData ed = new ExceptionData(e.getMessage(),e.getErrorCode());
        return setUpErrorResponse(ed,Response.Status.BAD_REQUEST);
    }

    private OrgResponseData setUpErrorResponse(ExceptionData ed, Response.Status responseStatus) {
        OrgResponseData responseData = new OrgResponseData();
        responseData.setSuccess(false);
        ed.setHttpErrorCode(responseStatus.getStatusCode());
        ed.setHttpStatus(responseStatus);
        responseData.setExceptionData(ed);
        return responseData;
    }

    public Response createFailureResponse(Exception e, OrgResponseData orgResponseData) {
        Response response;
        if(orgResponseData==null) {
            orgResponseData = new SimpleResponseData();
        }
        orgResponseData.setSuccess(false);

        if(e instanceof InternalException){
            InternalException ie = (InternalException) e;
            ExceptionData ed = new ExceptionData(ie.getMessage(),ie.getErrorCode());
            orgResponseData.setExceptionData(ed);
            switch (ed.getHttpErrorCode()){
                case InternalErrorCodes.EMAILID_ALREADY_EXISTS:
                case InternalErrorCodes.PHONE_ALREADY_EXISTS:
                    response = Response.status(Response.Status.OK)
                            .entity(orgResponseData)
                            .type(MediaType.APPLICATION_JSON)
                            .build();
                    break;

                case InternalErrorCodes.INVALID_USER_ROLE:
                case InternalErrorCodes.INVALID_REQUEST_DATA:
                case InternalErrorCodes.NOT_FOUND:
                case InternalErrorCodes.DATA_ALREADY_EXISTS:
                case InternalErrorCodes.NOT_COMPATIBLE_ROLE:
                    orgResponseData.getExceptionData().setHttpErrorCode(Response.Status.BAD_REQUEST.getStatusCode());
                    orgResponseData.getExceptionData().setHttpStatus(Response.Status.BAD_REQUEST);
                    response = Response.status(Response.Status.BAD_REQUEST)
                            .entity(orgResponseData)
                            .type(MediaType.APPLICATION_JSON)
                            .build();
                    break;


                case InternalErrorCodes.NO_USERID_IN_REQUEST:
                case InternalErrorCodes.OPERATION_NOT_PERMITTED:
                    orgResponseData.getExceptionData().setHttpErrorCode(Response.Status.METHOD_NOT_ALLOWED.getStatusCode());
                    orgResponseData.getExceptionData().setHttpStatus(Response.Status.METHOD_NOT_ALLOWED);
                    response = Response.status(Response.Status.METHOD_NOT_ALLOWED)
                            .entity(orgResponseData)
                            .type(MediaType.APPLICATION_JSON)
                            .build();
                    break;

                case InternalErrorCodes.INVALID_USER_CREDENTIALS:
                case InternalErrorCodes.INVALID_USER_ID:
                    orgResponseData.getExceptionData().setHttpErrorCode(Response.Status.UNAUTHORIZED.getStatusCode());
                    orgResponseData.getExceptionData().setHttpStatus(Response.Status.UNAUTHORIZED);
                    response = Response.status(Response.Status.UNAUTHORIZED)
                            .entity(orgResponseData)
                            .type(MediaType.APPLICATION_JSON)
                            .build();
                    break;


                case InternalErrorCodes.TEMPLATE_NAME_ALREADY_EXITS:
                    orgResponseData.getExceptionData().setHttpErrorCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
                    orgResponseData.getExceptionData().setHttpStatus(Response.Status.INTERNAL_SERVER_ERROR);
                    response = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity(orgResponseData)
                            .type(MediaType.APPLICATION_JSON)
                            .build();
                    break;

                default:
                    orgResponseData.getExceptionData().setMessage("INTERNAL SERVER ERROR");
                    orgResponseData.getExceptionData().setHttpErrorCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
                    orgResponseData.getExceptionData().setHttpStatus(Response.Status.INTERNAL_SERVER_ERROR);
                    response = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity(orgResponseData)
                            .build();
            }
        } else {
            ExceptionData ed = new ExceptionData("INTERNAL SERVER ERROR",InternalErrorCodes.INTERNAL_SERVER_ERROR);
            ed.setHttpErrorCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            ed.setHttpStatus(Response.Status.INTERNAL_SERVER_ERROR);
            orgResponseData.setExceptionData(ed);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(orgResponseData)
                    .build();
        }
        return response;
    }
}
