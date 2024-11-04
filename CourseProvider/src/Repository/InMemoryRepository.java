package Repository;

import Models.Identifiable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryRepository<T extends Identifiable> implements IRepository<T> {

    private final Map<Integer, T> dataStore = new HashMap<>();

    @Override
    public T create(T obj) {
        dataStore.put(obj.getId(), obj);
        return obj;
    }

    @Override
    public Optional<T> read(int id) {
        return Optional.ofNullable(dataStore.get(id));
    }

    @Override
    public Optional<T> update(T obj) {
        if (dataStore.containsKey(obj.getId())) {
            dataStore.put(obj.getId(), obj);
            return Optional.of(obj);
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(int id) {
        return dataStore.remove(id) != null;
    }

    @Override
    public Collection<T> findAll() {
        return dataStore.values();
    }
}
