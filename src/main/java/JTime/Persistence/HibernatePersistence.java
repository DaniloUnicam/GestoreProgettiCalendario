package JTime.Persistence;

public interface HibernatePersistence {

    /**
     * Retrieves the unique identifier of the entity.
     *
     * @return the unique identifier of the entity
     */
    Long getId();

        /**
        * Sets the unique identifier of the entity.
        *
        * @param id the unique identifier to set
        */
    void setId(Long id);
}
