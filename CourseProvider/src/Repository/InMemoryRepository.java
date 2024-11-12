package Repository;

import Models.Identifiable;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * An in-memory implementation of the {@link IRepository} interface, designed to store objects of type T in a HashMap.
 * The repository uses the ID of each object as the key to facilitate fast lookups, updates, and deletions.
 *
 * @param <T> The type of object stored in this repository, which must implement the {@link Identifiable} interface.
 */
public class InMemoryRepository<T extends Identifiable> implements IRepository<T> {

    // In-memory data store (map of object ID to object)
    private final Map<Integer, T> dataStore = new HashMap<>();

    /**
     * Creates a new object in the repository. If the object already exists (based on its ID), it will not be added.
     *
     * @param obj The object to be added to the repository.
     */
    @Override
    public void create(T obj) {
        dataStore.putIfAbsent(obj.getId(), obj);
    }

    /**
     * Retrieves an object from the repository by its ID.
     *
     * @param id The ID of the object to retrieve.
     * @return The object with the specified ID, or {@code null} if no such object exists.
     */
    @Override
    public T get(Integer id) {
        return dataStore.get(id);
    }

    /**
     * Updates an existing object in the repository. If an object with the specified ID does not exist, it will be added.
     *
     * @param obj The object with updated data to be stored in the repository.
     */
    @Override
    public void update(T obj) {
        dataStore.replace(obj.getId(), obj);
    }

    /**
     * Deletes an object from the repository by its ID.
     *
     * @param id The ID of the object to delete.
     */
    @Override
    public void delete(Integer id) {
        dataStore.remove(id);
    }

    /**
     * Retrieves all objects stored in the repository as a list.
     *
     * @return A list of all objects stored in the repository.
     */
    @Override
    public List<T> getAll() {
        return dataStore.values().stream().toList();
    }
}
