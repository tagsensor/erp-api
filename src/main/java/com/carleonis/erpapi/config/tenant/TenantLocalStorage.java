package com.carleonis.erpapi.config.tenant;

public class TenantLocalStorage {

    private static ThreadLocal<String> tenant = new ThreadLocal<>();

    public static void setTenantName(String tenantName) {
        tenant.set(tenantName);
    }

    public static String getTenantName() {
        return tenant.get();
    }

}
