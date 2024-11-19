package Models;

public class UserSerializer implements IEntitySerializer<User> {

    @Override
    public String serialize(User user) {
        return user.getId() + "," + user.getUserName() + "," + user.getPassword() + "," + user.getEmail() + "," + user.getType();
    }

    @Override
    public User deserialize(String data) {
        String[] parts = data.split(",");
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        String password = parts[2];
        String email = parts[3];
        String type = parts[4];
        if(type == "student"){
            return new Student(id,name,password,email,type);
        }else if(type == "admin"){
            return new Admin(id,name,password,email,type);
        }else
            return new Instructor(id, name, password, email, type);
        
    }
}

