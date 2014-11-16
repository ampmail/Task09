package courses.dao.impl;

import courses.connector.DBConnector;
import courses.dao.DepartmentDAO;
import courses.entity.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOImpl implements DepartmentDAO {

    private DBConnector connector;
    private Connection connection;

    {
        connector = new DBConnector();
    }

    @Override
    public void create(Department department) throws SQLException {
        connection = connector.getConnection();

        String sql = "INSERT INTO department (name) VALUES (?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, department.getName());

        statement.executeUpdate();

        statement.close();
        connection.close();

        department.setId(readId(department));
    }

    @Override
    public Long readId(Department department) throws SQLException {
        connection = connector.getConnection();
        Long departmentId = null;

        String sql = "SELECT id FROM department WHERE name = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, department.getName());

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            departmentId = (resultSet.getLong("id"));
        }

        resultSet.close();
        statement.close();
        connection.close();
        return departmentId;
    }

    @Override
    public Department read(Long id) throws SQLException {
        connection = connector.getConnection();

        String sql = "SELECT * FROM department WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);

        Department department = new Department();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            department.setId(resultSet.getLong("id"));
            department.setName(resultSet.getString("name"));
        }

        resultSet.close();
        statement.close();
        connection.close();
        return department;
    }

    @Override
    public List<Department> readAll() throws SQLException {
        connection = connector.getConnection();

        String sql = "SELECT * FROM department";
        PreparedStatement statement = connection.prepareStatement(sql);

        List<Department> departmentList = new ArrayList<Department>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            departmentList.add(new Department(resultSet.getLong("id"), resultSet.getString("name")));
        }

        resultSet.close();
        statement.close();
        connection.close();
        return departmentList;
    }

    @Override
    public void update(Department department) throws SQLException {
        connection = connector.getConnection();

        String sql = "UPDATE department SET name = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, department.getName());
        statement.setLong(2, department.getId());

        statement.executeUpdate();

        statement.close();
        connection.close();
    }

    @Override
    public void delete(Long id) throws SQLException {
        connection = connector.getConnection();

        String sql = "DELETE FROM department WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);

        statement.executeUpdate();

        statement.close();
        connection.close();
    }
}
