package Repository;

import Models.IEntitySerializer;
import Models.Identifiable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileRepository<T extends Identifiable> implements IRepository<T> {

    private final String filePath;
    private final IEntitySerializer<T> serializer;

    /**
     * Constructor for the FileRepository.
     * @param filePath The path to the file where entities will be stored.
     * @param serializer A serializer to convert entities to and from String format.
     */
    public FileRepository(String filePath, IEntitySerializer<T> serializer) {
        this.filePath = filePath;
        this.serializer = serializer;
    }

    /**
     * Adds a new entity to the file.
     * @param obj The entity to add.
     */
    @Override
    public void create(T obj) {
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            fileWriter.write(serializer.serialize(obj) + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Reads an entity from the file by its ID.
     * @param id The ID of the entity to read.
     * @return The entity if found, or null if not found.
     */
    @Override
    public T get(Integer id) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                T entity = serializer.deserialize(line);
                if (entity.getId() == id) {
                    return entity;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return null;
    }

    /**
     * Updates an entity in the file by replacing the existing entry with the new one.
     * @param obj The entity to update.
     */
    @Override
    public void update(T obj) {
        List<T> entities = getAll();
        try (FileWriter fileWriter = new FileWriter(filePath, false)) {
            for (T entity : entities) {
                if (entity.getId() == obj.getId()) {
                    fileWriter.write(serializer.serialize(obj) + System.lineSeparator());
                } else {
                    fileWriter.write(serializer.serialize(entity) + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Deletes an entity from the file based on its ID.
     * @param id The ID of the entity to delete.
     */
    @Override
    public void delete(Integer id) {
        List<T> entities = getAll();
        try (FileWriter fileWriter = new FileWriter(filePath, false)) {
            for (T entity : entities) {
                if (entity.getId() != id) {
                    fileWriter.write(serializer.serialize(entity) + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Method to read all entities from the file.
     * @return A list of all entities in the file.
     */
    @Override
    public List<T> getAll() {
        List<T> entities = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                T entity = serializer.deserialize(line);
                entities.add(entity);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return entities;
    }

}
