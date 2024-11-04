package Repository;

import java.util.Collection;
import java.util.Optional;

public interface IRepository<T> {
    /**
     * Adds a new entity to the repository.
     * @param obj The entity to add.
     * @return The added entity.
     */
    T create(T obj);

    /**
     * Retrieves an entity by its ID.
     * @param id The ID of the entity to retrieve.
     * @return An Optional containing the entity if found, or empty if not found.
     */
    Optional<T> read(int id);

    /**
     * Updates an existing entity.
     * @param obj The entity with updated values.
     * @return The updated entity if the update was successful, or empty if not found.
     */
    Optional<T> update(T obj);

    /**
     * Deletes an entity by its ID.
     * @param id The ID of the entity to delete.
     * @return True if the entity was deleted, false if not found.
     */
    boolean delete(int id);

    /**
     * Returns all entities in the repository.
     * @return A Collection of all entities.
     */
    Collection<T> findAll();
}
