package Repository;

import Models.Identifiable;

import javax.swing.text.html.parser.Entity;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileRepository<T extends Identifiable> implements IRepository<T>  {

    @Override
    public void create(T obj) {

    }

    @Override
    public T get(Integer id) {
        return null;
    }

    @Override
    public void update(T obj) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<T> getAll() {
        return List.of();
    }
}
