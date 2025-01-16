package com.r3projects.atbp.controllers;

import com.r3projects.atbp.model.ApiAppResponse;

public abstract class AppBaseController {

    protected ApiAppResponse createSuccess(){
        final ApiAppResponse appResponse = new ApiAppResponse();
        appResponse.setStatusCode(0);
        appResponse.setStatusMessage("Success");
        return  appResponse;
    }
}
