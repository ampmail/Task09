package courses.dao.impl;

import courses.connector.DBConnector;
import courses.dao.EmployerDAO;
import courses.entity.Employer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployerDAOImpl implements EmployerDAO {

    private DBConnector connector;
    private Connection connection;

    {
        connector = new DBConnector();
    }

    @Override
    public void create(Employer employer) throws SQLException {
        connection = connector.getConnection();

        String sql = "INSERT INTO employer (name, age, e_mail, department_id) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, employer.getName());
        statement.setInt(2, employer.getAge());
        statement.setString(3, employer.getE_mail());
        statement.setLong(4, employer.getDepartment_id());

        statement.executeUpdate();

        statement.close();
        connection.close();

        employer.setId(readId(employer));
    }

    @Override
    public Long readId(Employer employer) throws SQLException {
        connection = connector.getConnection();
        Long employerId = null;

        String sql = "SELECT id FROM employer WHERE name = ? AND age = ? AND e_mail = ? AND department_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, employer.getName());
        statement.setInt(2, employer.getAge());
        statement.setString(3, employer.getE_mail());
        statement.setLong(4, employer.getDepartment_id());

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            employerId = (resultSet.getLong("id"));
        }

        resultSet.close();
        statement.close();
        connection.close();
        return employerId;
    }

    @Override
    public Employer read(Long id) throws SQLException {
        connection = connector.getConnection();

        String sql = "SELECT * FROM employer WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);

        Employer employer = new Employer();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            employer.setId(resultSet.getLong("id"));
            employer.setName(resultSet.getString("name"));
            employer.setAge(resultSet.getInt("age"));
            employer.setE_mail(resultSet.getString("e_mail"));
            employer.setDepartment_id(resultSet.getLong("department_id"));
        }

        resultSet.close();
        statement.close();
        connection.close();
        return employer;
    }

    public Long readDepartmentIdByEmployerId(Long id) throws SQLException {
        connection = connector.getConnection();

        String sql = "SELECT department_id FROM employer WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);

        Long department_id = null;
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            department_id = resultSet.getLong("department_id");
        }

        resultSet.close();
        statement.close();
        connection.close();
        return department_id;
    }

    @Override
    public List<Employer> readAll() throws SQLException {
        connection = connector.getConnection();

        String sql = "SELECT * FROM employer";
        PreparedStatement statement = connection.prepareStatement(sql);

        List<Employer> employerList = new ArrayList<Employer>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            employerList.add(new Employer(resultSet.getLong("id"), resultSet.getString("name"),
                    resultSet.getInt("age"), resultSet.getString("e_mail"), resultSet.getLong("department_id")));
        }

        resultSet.close();
        statement.close();
        connection.close();
        return employerList;
    }

    public List<Employer> readAllbyDepartmentId(Long department_id) throws SQLException {
        connection = connector.getConnection();

        String sql = "SELECT * FROM employer WHERE department_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, department_id);

        List<Employer> employerList = new ArrayList<Employer>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            employerList.add(new Employer(resultSet.getLong("id"), resultSet.getString("name"),
                    resultSet.getInt("age"), resultSet.getString("e_mail"), resultSet.getLong("department_id")));
        }

        resultSet.close();
        statement.close();
        connection.close();
        return employerList;
    }

    @Override
    public void update(Employer employer) throws SQLException {
        connection = connector.getConnection();

        String sql = "UPDATE employer SET name = ?, age = ?, e_mail = ?, department_id = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, employer.getName());
        statement.setInt(2, employer.getAge());
        statement.setString(3, employer.getE_mail());
        statement.setLong(4, employer.getDepartment_id());
        statement.setLong(5, employer.getId());

        statement.executeUpdate();

        statement.close();
        connection.close();
    }

    @Override
    public void delete(Long id) throws SQLException {
        connection = connector.getConnection();

        String sql = "DELETE FROM employer WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);

        statement.executeUpdate();

        statement.close();
        connection.close();
    }
}