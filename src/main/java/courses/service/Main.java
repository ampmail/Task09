package courses.service;

import courses.dao.DepartmentDAO;
import courses.dao.impl.DepartmentDAOImpl;
import courses.dao.impl.EmployerDAOImpl;
import courses.entity.Department;
import courses.entity.Employer;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {

        try {
            Department departmentWH = new Department();
            departmentWH.setName("Warehouse");
            DepartmentDAO departmentDAO = new DepartmentDAOImpl();
            departmentDAO.create(departmentWH);
            Department departmentLOG = new Department();
            departmentLOG.setName("Logistic");
            departmentDAO.create(departmentLOG);

            System.out.println("-- All Departments --");
            List<Department> depList = departmentDAO.readAll();
            for (Department dep : depList) {
                System.out.println(dep);
            }

            Employer employer = new Employer();
            employer.setName("Jim");
            employer.setAge(34);
            employer.setE_mail("Jim@mail.com");
            employer.setDepartment_id(departmentWH.getId());
            EmployerDAOImpl employerDAO = new EmployerDAOImpl();
            employerDAO.create(employer);

            System.out.println(employerDAO.read(employer.getId()));

            System.out.println("-- All employers --");
            List<Employer> empList = employerDAO.readAll();
            for (Employer empl : empList) {
                System.out.println(empl);
            }

            System.out.println("-- Update User Jim to Bob --");
            employer.setName("Bob");
            employerDAO.update(employer);
            System.out.println(employerDAO.read(employer.getId()));


            System.out.println("-- All employers with department id:" + departmentLOG.getId()+" --");
            empList = employerDAO.readAllbyDepartmentId(departmentLOG.getId());
            for (Employer empl : empList) {
                System.out.println(empl);
            }
            System.out.println("-- All employers with department id:" + departmentWH.getId()+" --");            empList = employerDAO.readAllbyDepartmentId(departmentWH.getId());
            for (Employer empl : empList) {
                System.out.println(empl);
            }

            Long employerId = employer.getId();
            Long departmentId = employerDAO.readDepartmentIdByEmployerId(employerId);
            Department searchDepartment;
            DepartmentDAO searchDepartmentDAO = new DepartmentDAOImpl();
            searchDepartment = searchDepartmentDAO.read(departmentId);
            System.out.println(searchDepartment.getName().toString());

            
            System.out.println("-- Delete User --");
            employerDAO.delete(employer.getId());
            System.out.println("-- All employers --");
            empList = employerDAO.readAll();
            for (Employer empl : empList) {
                System.out.println(empl);
            }

            System.out.println("-- Delete Departments --");
            departmentDAO.delete(departmentWH.getId());
            departmentDAO.delete(departmentLOG.getId());
            System.out.println("-- All Departments --");
            depList = departmentDAO.readAll();
            for (Department dep : depList) {
                System.out.println(dep);
            }

        } catch (SQLException E) {
            System.out.println(E.toString());
        } finally {

        }
        System.out.println("-----");
    }

}