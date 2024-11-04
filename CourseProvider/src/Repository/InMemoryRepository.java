package Repository;

import Models.Identifiable;

import java.util.List;
import java.util.HashMap;
import java.util.Map;


public class InMemoryRepository<T extends Identifiable> implements IRepository<T> {

    private final Map<Integer, T> dataStore = new HashMap<>();

    @Override
    public void create(T obj) {
        dataStore.putIfAbsent(obj.getId(), obj);
    }

    @Override
    public T get(Integer id) {
        return dataStore.get(id);
    }

    @Override
    public void update(T obj) {
      dataStore.replace(obj.getId(), obj);
    }

    @Override
    public void delete(Integer id) {
        dataStore.remove(id);
    }

    @Override
    public List<T> getAll() {
        return dataStore.values().stream().toList();
    }
}
