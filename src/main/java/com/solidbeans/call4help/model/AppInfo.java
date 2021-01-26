package com.solidbeans.call4help.model;

public class AppInfo {
    private String version;
    private String name;


    public AppInfo() {
    }

    public AppInfo(String version, String name) {
        this.version = version;
        this.name = name;
    }

    //<editor-fold desc="Getter & Setter">
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //</editor-fold>
}
