package com.shiro.entity;

public class RolesPermissions {
    private Integer id;
    private String role_name;
    private String permission;
    private String url;

    public RolesPermissions() {
    }

    public RolesPermissions(Integer id, String role_name, String permission, String url) {
        this.id = id;
        this.role_name = role_name;
        this.permission = permission;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
