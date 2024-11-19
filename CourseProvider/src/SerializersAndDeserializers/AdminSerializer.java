package SerializersAndDeserializers;

import Models.*;

public class AdminSerializer implements IEntitySerializer<Admin> {

    @Override
    public String serialize(Admin user) {
        return user.getId() + "," + user.getUserName() + "," + user.getPassword() + "," + user.getEmail() + "," + user.getType();
    }

    @Override
    public Admin deserialize(String data) {
        String[] parts = data.split(",");
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        String password = parts[2];
        String email = parts[3];
        String type = parts[4];
        return new Admin(id,name,password,email,type);

    }

}

