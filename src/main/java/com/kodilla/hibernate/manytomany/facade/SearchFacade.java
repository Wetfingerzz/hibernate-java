package com.kodilla.hibernate.manytomany.facade;

import com.kodilla.hibernate.manytomany.Company;
import com.kodilla.hibernate.manytomany.Employee;
import com.kodilla.hibernate.manytomany.dao.CompanyDao;
import com.kodilla.hibernate.manytomany.dao.EmployeeDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchFacade {

    @Autowired
    CompanyDao companyDao;
    @Autowired
    EmployeeDao employeeDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchFacade.class);

    public List<Employee> findEmployeesByAnyGivenSeriesOfLetters(String s) throws SearchFacadeException {
        List<Employee> employees = employeeDao.findEmployeeByAnyGivenSeriesOfLetters(s);
        if(employees.isEmpty()) {
            throw new SearchFacadeException(SearchFacadeException.ERR_EMPLOYEES_NOT_FOUND);
        }
        return employees;
    }
    public List<Company> findCompaniesByAnyGivenSeriesOfLetters(String name) throws SearchFacadeException {
        List<Company> companies = companyDao.findCompaniesWithAnyGivenSeriesOfLetters(name);
        if (companies.isEmpty()) {
            throw new SearchFacadeException(SearchFacadeException.ERR_COMPANIES_NOT_FOUND);
        }
        return companies;
    }
}
