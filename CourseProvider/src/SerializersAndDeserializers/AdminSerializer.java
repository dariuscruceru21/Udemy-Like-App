package SerializersAndDeserializers;

import Models.*;

/**
 * Serializer and deserializer for the {@link Admin} class.
 * Converts {@link Admin} objects to and from their string representation for file storage.
 */
public class AdminSerializer implements IEntitySerializer<Admin> {

    /**
     * Serializes an {@link Admin} object into a string representation.
     *
     * @param user the {@link Admin} object to be serialized.
     * @return a comma-separated string representing the {@link Admin} object.
     */
    @Override
    public String serialize(Admin user) {
        return user.getId() + "," + user.getUserName() + "," + user.getPassword() + ","
                + user.getEmail() + "," + user.getType();
    }

    /**
     * Deserializes a string representation into an {@link Admin} object.
     *
     * @param data the comma-separated string containing {@link Admin} details.
     * @return an {@link Admin} object created from the provided data.
     */
    @Override
    public Admin deserialize(String data) {
        String[] parts = data.split(",");
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        String password = parts[2];
        String email = parts[3];
        String type = parts[4];
        return new Admin(id, name, password, email, type);
    }
}
