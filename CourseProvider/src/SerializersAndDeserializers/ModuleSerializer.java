package SerializersAndDeserializers;

import Models.IEntitySerializer;
import Models.Instructor;

/**
 * Serializer and deserializer for the {@link Module} class.
 * Converts {@link Module} objects to and from their string representation for file storage.
 */
public class ModuleSerializer implements IEntitySerializer<Module> {

    /**
     * Serializes an {@link Module} object into a string representation.
     *
     * @param module the {@link Module} object to be serialized.
     * @return a comma-separated string representing the {@link Module} object.
     */
    @Override
    public String serialize(Module module) {
        return "";
    }

    /**
     * Deserializes a string representation into an {@link Module} object.
     *
     * @param data the comma-separated string containing {@link Module} details.
     * @return an {@link Module} object created from the provided data.
     */
    @Override
    public Module deserialize(String data) {
        return null;
    }
}
