package courses.service;

import courses.dao.DepartmentDAO;
import courses.dao.EmployerDAO;
import courses.dao.impl.DepartmentDAOImpl;
import courses.dao.impl.EmployerDAOImpl;
import courses.entity.Department;
import courses.entity.Employer;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException{

        Department departmentWH = new Department();
        departmentWH.setName("Warehouse");
        DepartmentDAO departmentDAO = new DepartmentDAOImpl();
        departmentDAO.create(departmentWH);
        System.out.println(departmentWH.toString());

        Employer employer = new Employer();
        employer.setName("Jim");
        employer.setAge(34);
        employer.setE_mail("Jim@mail.qq");
        employer.setDepartment_id(departmentWH.getId());

        EmployerDAO employerDAO = new EmployerDAOImpl();
        employerDAO.create(employer);

//        System.out.println(employerDAO.read(18L));
//        System.out.println("-----");
//
//        List<Employer> list = employerDAO.readAll();
//        for (Employer user1 : list) {
//            System.out.println(user1);
//        }
//        System.out.println("-----");
//
//        employer.setName("Bob");
//        employerDAO.update(employer);
//
//        System.out.println(employerDAO.read(19L));
//        System.out.println("-----");
//
//        employerDAO.delete(19L);
//
//        list = employerDAO.readAll();
//        for (Employer user1 : list) {
//            System.out.println(user1);
//        }
        System.out.println("-----");
    }

}
