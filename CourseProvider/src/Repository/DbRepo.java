package Repository;

import Models.Identifiable;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
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
        // Get the first column name from the values map for where clause
        String firstColumn = values.keySet().iterator().next(); // Assumes first column is used for filtering (could be a primary key or other field)

        // Construct the query to select based on the first column (e.g., the ID)
        String query = "SELECT * FROM " + tableName + " WHERE " + firstColumn + " = ?";

        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            // Set the ID (or primary key) in the prepared statement
            statement.setInt(1, id);

            // Execute the query
            try (ResultSet resultSet = statement.executeQuery()) {
                // Check if the ResultSet contains data
                if (resultSet.next()) {
                    // Map the result set to the object using the mapResultSet method
                    return mapResultSet(resultSet, (Class<T>) Identifiable.class);  // You can change this to the specific class type T
                }
            }

        } catch (SQLException | ReflectiveOperationException e) {
            e.printStackTrace();
            // Handle exceptions appropriately (could log the error or rethrow it)
        }

        return null; // Return null if no record was found
    }



    @Override
    public void update(Identifiable obj) {
        // Create the SET clause of the update query
        String setClause = values.keySet().stream()
                .map(key -> key + " = ?")
                .collect(Collectors.joining(", "));

        // Get the ID of the object to specify which row to update (assumed to be in the values map)
        String idColumn = values.keySet().iterator().next(); // Assumes first key is the identifier column (e.g., id)

        // Build the update query dynamically
        String query = "UPDATE " + tableName + " SET " + setClause + " WHERE " + idColumn + " = ?";

        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            // Set the statement parameters for the update values
            setStatementParameters(statement, values.values().toArray());

            // Set the ID parameter for the WHERE clause (the last parameter in the query)
            statement.setObject(values.size() + 1, obj.getId()); // Assuming getId() returns the id of the object

            // Execute the update query
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }
    }


    @Override
    public void delete(Integer id) {
        // Get the ID column name (assuming it's the first key in the values map)
        String idColumn = values.keySet().iterator().next(); // Assumes first key is the identifier column (e.g., id)

        // Build the delete query dynamically
        String query = "DELETE FROM " + tableName + " WHERE " + idColumn + " = ?";

        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            // Set the ID parameter in the statement
            statement.setInt(1, id);

            // Execute the delete query
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }
    }


    @Override
    public List<T> getAll() {
        // Build the SELECT query to get all rows from the table
        String query = "SELECT * FROM " + tableName;

        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            List<T> results = new ArrayList<>();

            // Process the result set and map each row to an object
            while (resultSet.next()) {
                T obj = mapRow(resultSet);
                results.add(obj);
            }

            return results;

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately (you could throw a custom exception here)
            return new ArrayList<>();
        }
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


    private <T> T mapResultSet(ResultSet resultSet, Class<T> clazz) throws SQLException, ReflectiveOperationException {
        // Create a new instance of the object (target class)
        T obj = clazz.getDeclaredConstructor().newInstance();

        // Get the metadata of the result set to determine the columns
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Loop through each column and set corresponding field in the object
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            Object value = resultSet.getObject(i);

            // Set the value to the object field
            setFieldValue(obj, columnName, value);
        }

        return obj;
    }

    // Helper method to set the field value dynamically using reflection
    private <T> void setFieldValue(T obj, String columnName, Object value) throws ReflectiveOperationException {
        // Construct the setter method name based on the column name (e.g., "setFieldName" for "fieldName")
        String setterMethodName = "set" + capitalize(columnName);

        try {
            // Find the setter method for the field
            var setterMethod = obj.getClass().getMethod(setterMethodName, value.getClass());

            // Invoke the setter method to set the field value
            setterMethod.invoke(obj, value);
        } catch (NoSuchMethodException e) {
            // If the setter is not found, it could be because the field doesn't exist or it is private.
            // Try finding the field directly if no setter is available.
            var field = obj.getClass().getDeclaredField(columnName);
            field.setAccessible(true);  // Allow access to private fields
            field.set(obj, value);
        }
    }

    // Helper method to capitalize the first letter of a string (e.g., "fieldName" -> "FieldName")
    private String capitalize(String columnName) {
        if (columnName == null || columnName.isEmpty()) {
            return columnName;
        }
        return columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
    }

    private T mapRow(ResultSet resultSet) throws SQLException {
        // Create a new instance of the class T
        try {
            T obj = (T) this.getClass().getGenericSuperclass().getClass().getConstructor().newInstance();

            // Iterate through the fields of the object and set them based on the ResultSet
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true); // Make private fields accessible

                String columnName = field.getName(); // Assumes the column name is the same as the field name
                Object value = resultSet.getObject(columnName); // Get the value from the ResultSet

                // Set the field value
                field.set(obj, value);
            }

            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exception appropriately (you could throw a custom exception here)
            return null;
        }
    }


}
