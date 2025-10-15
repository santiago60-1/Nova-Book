// src/main/java/com/codeup/booksnova/dao/PartnerDAO.java
package com.codeup.booksnova.dao;

import com.codeup.booksnova.domain.Partner;
import java.util.List;
import java.util.Optional;

/**
 * CRUD operations for Partner entity.
 */
public interface PartnerDAO {
    /**
     * Insert a new partner.
     * @param partner the partner to save
     * @return the saved partner with generated id
     */
    Partner save(Partner partner) throws Exception;

    /**
     * Find a partner by its id.
     * @param id the partner id
     * @return an Optional with the partner if found
     */
    Optional<Partner> findById(Integer id) throws Exception;

    /**
     * Retrieve all partners.
     * @return list of partners
     */
    List<Partner> findAll() throws Exception;

    /**
     * Update an existing partner.
     * @param partner the partner with new data
     * @return the updated partner
     */
    Partner update(Partner partner) throws Exception;

    /**
     * Delete a partner by its id.
     * @param id the id to delete
     */
    void delete(Integer id) throws Exception;
}
