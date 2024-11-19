package SerializersAndDeserializers;

import Models.IEntitySerializer;

public class ModuleSerializer implements IEntitySerializer<Module> {
    @Override
    public String serialize(Module entity) {
        return "";
    }

    @Override
    public Module deserialize(String data) {
        return null;
    }
}
