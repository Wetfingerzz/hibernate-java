package com.kodilla.hibernate.manytomany.facade;

import com.kodilla.hibernate.manytomany.Company;
import com.kodilla.hibernate.manytomany.Employee;
import com.kodilla.hibernate.manytomany.dao.CompanyDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchFacadeTestSuite {
    @Autowired
    private SearchFacade searchFacade;
    @Autowired
    private CompanyDao companyDao;

    private List<Company> resultCompanyList = new ArrayList<>();
    private List<Employee> resultEmployeeList = new ArrayList<>();

    @Test
    public void testCompanySearchFacade() throws SearchFacadeException {
        //Given
        Employee johnSmith = new Employee("John", "Smith");
        Employee stephanieClarckson = new Employee("Stephanie", "Clarckson");
        Employee lindaSmith = new Employee("Linda", "Smith");

        Company softwareMachine = new Company("Software Machine");
        Company softDataMasters = new Company("Soft Data Masters");
        Company greyMatter = new Company("Grey Matter");

        softwareMachine.getEmployees().add(johnSmith);
        softDataMasters.getEmployees().add(stephanieClarckson);
        softDataMasters.getEmployees().add(lindaSmith);
        greyMatter.getEmployees().add(johnSmith);
        greyMatter.getEmployees().add(lindaSmith);

        johnSmith.getCompanies().add(softwareMachine);
        johnSmith.getCompanies().add(greyMatter);
        stephanieClarckson.getCompanies().add(softDataMasters);
        lindaSmith.getCompanies().add(softDataMasters);
        lindaSmith.getCompanies().add(greyMatter);

        companyDao.save(softwareMachine);
        companyDao.save(softDataMasters);
        companyDao.save(greyMatter);

        resultCompanyList.add(softwareMachine);
        resultCompanyList.add(softDataMasters);

        resultEmployeeList.add(johnSmith);
        resultEmployeeList.add(lindaSmith);
        //When
        List<Company> queryResult = searchFacade.findCompaniesByAnyGivenSeriesOfLetters("oft");
        //Then
        Assert.assertEquals(resultCompanyList.size(), queryResult.size());
        //CleanUp
        try {
            companyDao.delete(softwareMachine);
            companyDao.delete(softDataMasters);
            companyDao.delete(greyMatter);
        } catch (Exception e) {
            //do nothing
        }


    }

    @Test
    public void testEmployeeSearchFacade() throws SearchFacadeException {
        //given
        Employee johnSmith = new Employee("John", "Smith");
        Employee stephanieClarckson = new Employee("Stephanie", "Clarckson");
        Employee lindaSmith = new Employee("Linda", "Smith");

        Company softwareMachine = new Company("Software Machine");
        Company softDataMasters = new Company("Soft Data Masters");
        Company greyMatter = new Company("Grey Matter");

        softwareMachine.getEmployees().add(johnSmith);
        softDataMasters.getEmployees().add(stephanieClarckson);
        softDataMasters.getEmployees().add(lindaSmith);
        greyMatter.getEmployees().add(johnSmith);
        greyMatter.getEmployees().add(lindaSmith);

        johnSmith.getCompanies().add(softwareMachine);
        johnSmith.getCompanies().add(greyMatter);
        stephanieClarckson.getCompanies().add(softDataMasters);
        lindaSmith.getCompanies().add(softDataMasters);
        lindaSmith.getCompanies().add(greyMatter);

        companyDao.save(softwareMachine);
        companyDao.save(softDataMasters);
        companyDao.save(greyMatter);

        resultCompanyList.add(softwareMachine);
        resultCompanyList.add(softDataMasters);

        resultEmployeeList.add(johnSmith);
        resultEmployeeList.add(lindaSmith);

        //when
        List<Employee> queryResultList = searchFacade.findEmployeesByAnyGivenSeriesOfLetters("mit");
        //Then
        Assert.assertEquals(resultEmployeeList.size(), queryResultList.size());
        //CleanUp
        try {
            companyDao.delete(softwareMachine);
            companyDao.delete(softDataMasters);
            companyDao.delete(greyMatter);
        } catch (Exception e) {
            //do nothing
        }
    }
}

