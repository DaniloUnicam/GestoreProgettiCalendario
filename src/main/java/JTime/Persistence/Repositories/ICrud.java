package JTime.Persistence.Repositories;

import java.util.List;
/**
 * Generic CRUD (Create, Read, Update, Delete) interface (or DAO) for managing entities.
 *
 * @param <T>  The type of the entity.
 * @param <ID> The type of the entity's identifier.
 */
public interface ICrud<T, ID> {
    ID save(T entity);

    T findById(ID id);

    List<T> findAll();

    void update(T entity);

    void delete(T entity);
}
