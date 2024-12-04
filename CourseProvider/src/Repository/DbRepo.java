package Repository;

import Models.Identifiable;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DbRepo<T extends Identifiable> implements IRepository<T> {
    private String tableName;
    private Map<String, Object> values = new HashMap<>();

    private static final String URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("DB_USER");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    /**
     * Establishes a connection to the database.
     * @return A Connection object.
     * @throws SQLException If a database access error occurs.
     */
    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    //constructor
    public DbRepo(String tableName, Map<String, Object> values) {
        this.tableName = tableName;
        this.values = values;
    }


    @Override
    public void create(Identifiable obj) {
        String columns = String.join(", ", values.keySet());
        String placeholders = values.keySet().stream()
                .map(key -> "?")
                .collect(Collectors.joining(", "));
        String query = "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + placeholders + ")";

        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            // Set the statement parameters
            setStatementParameters(statement, values.values().toArray());

            // Execute the query
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }
    }

    @Override
    public T get(Integer id) {
        return null;
    }




    @Override
    public void update(Identifiable obj) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List getAll() {
        return List.of();
    }


    private void setStatementParameters(PreparedStatement statement, Object[] parameters) throws SQLException {
        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                if (parameters[i] instanceof String) {
                    statement.setString(i + 1, (String) parameters[i]);
                } else if (parameters[i] instanceof Integer) {
                    statement.setInt(i + 1, (Integer) parameters[i]);
                } else if (parameters[i] instanceof Long) {
                    statement.setLong(i + 1, (Long) parameters[i]);
                } else if (parameters[i] instanceof Double) {
                    statement.setDouble(i + 1, (Double) parameters[i]);
                } else if (parameters[i] instanceof Boolean) {
                    statement.setBoolean(i + 1, (Boolean) parameters[i]);
                } else if (parameters[i] instanceof Date) {
                    statement.setDate(i + 1, (Date) parameters[i]);
                } else if (parameters[i] == null) {
                    statement.setNull(i + 1, Types.NULL);
                } else {
                    throw new SQLException("Unsupported parameter type: " + parameters[i].getClass().getName());
                }
            }
        }
    }
}
