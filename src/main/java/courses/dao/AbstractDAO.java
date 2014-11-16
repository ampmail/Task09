package courses.dao;

import java.sql.SQLException;
import java.util.List;

public interface AbstractDAO<T> {

    void create(T t) throws SQLException;

    T read(Long id) throws SQLException;
    Long readId(T t) throws SQLException;
    List<T> readAll() throws SQLException;

    void update(T t) throws SQLException;

    void delete(Long id) throws SQLException;
}
