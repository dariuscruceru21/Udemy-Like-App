package Models;

/**
 * An interface for serializing and deserializing entities.
 * @param <T> The type of the entity.
 */
public interface IEntitySerializer<T> {
    /**
     * Serializes an entity into a CSV-compatible String.
     * @param entity The entity to serialize.
     * @return The String representation of the entity.
     */
    String serialize(T entity);

    /**
     * Deserializes an entity from a CSV-compatible String.
     * @param data The String representation of the entity.
     * @return The deserialized entity.
     */
    T deserialize(String data);
}

