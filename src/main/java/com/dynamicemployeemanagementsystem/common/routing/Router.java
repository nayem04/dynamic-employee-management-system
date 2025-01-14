package com.dynamicemployeemanagementsystem.common.routing;

public final class Router {
    private static final String ROOT = "/api/";

    public static final class Department {
        public static final String GET_DEPARTMENTS = ROOT + "departments";
        public static final String GET_DEPARTMENT = ROOT + "departments/{id}";
        public static final String CREATE_DEPARTMENT = ROOT + "departments";
        public static final String UPDATE_DEPARTMENT = ROOT + "departments/{id}";
        public static final String DELETE_DEPARTMENT = ROOT + "departments/{id}";
    }

    public static final class Role {
        public static final String GET_ROLES = ROOT + "roles";
        public static final String GET_ROLE = ROOT + "roles/{id}";
        public static final String CREATE_ROLE = ROOT + "roles";
        public static final String UPDATE_ROLE = ROOT + "roles/{id}";
        public static final String DELETE_ROLE = ROOT + "roles/{id}";
    }
}
