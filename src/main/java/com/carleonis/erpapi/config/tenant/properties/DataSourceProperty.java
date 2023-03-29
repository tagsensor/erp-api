package com.carleonis.erpapi.config.tenant.properties;

import lombok.Data;

@Data
public class DataSourceProperty {

    private String name;
    private String url;
    private String username;
    private String password;
    private String driverClassName;
}