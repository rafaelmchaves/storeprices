package com.store;

import io.micronaut.http.annotation.*;

@Controller("/storeprices")
public class StorepricesController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}