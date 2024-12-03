package Models.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The {@code Mapper} interface is used to convert a {@link ResultSet} from a database query
 * into an object of type {@code T}. This is useful for transforming database rows into Java objects.
 *
 * @param <T> The type of object the {@link ResultSet} will be converted to.
 */
public interface Mapper<T> {

    /**
     * Maps the given {@link ResultSet} to an object of type {@code T}.
     *
     * @param resultSet The {@link ResultSet} containing data from a database query.
     * @return An object of type {@code T} populated with data from the result set.
     * @throws SQLException If there is an error accessing the result set.
     */
    T map(ResultSet resultSet) throws SQLException;
}
