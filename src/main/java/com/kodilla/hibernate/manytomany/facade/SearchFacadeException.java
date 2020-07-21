package com.kodilla.hibernate.manytomany.facade;

public class SearchFacadeException extends Exception {

    public static String ERR_COMPANIES_NOT_FOUND = "Companies don't figure in the database";
    public static String ERR_EMPLOYEES_NOT_FOUND = "Employees don't figure in the database";

    public SearchFacadeException(String message) {
        super(message);
    }
}
