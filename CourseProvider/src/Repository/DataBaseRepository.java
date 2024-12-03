package Repository;

import Models.Identifiable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Abstract class that provides base functionality for database repositories.
 * @param <T> The type of entity managed by the repository.
 */
public abstract class DataBaseRepository<T extends Identifiable> implements IRepository<T> {

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

    /**
     * Abstract method to create an entity in the database.
     * @param obj The entity to create.
     */
    @Override
    public abstract void create(T obj);

    /**
     * Abstract method to retrieve an entity by its ID.
     * @param id The ID of the entity to retrieve.
     * @return The retrieved entity, or null if not found.
     */
    @Override
    public abstract T get(Integer id);

    /**
     * Abstract method to update an entity in the database.
     * @param obj The entity to update.
     */
    @Override
    public abstract void update(T obj);

    /**
     * Abstract method to delete an entity by its ID.
     * @param id The ID of the entity to delete.
     */
    @Override
    public abstract void delete(Integer id);

    /**
     * Abstract method to retrieve all entities.
     * @return A list of all entities in the repository.
     */
    @Override
    public abstract List<T> getAll();
}
