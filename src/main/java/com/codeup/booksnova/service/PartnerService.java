// src/main/java/com/codeup/booksnova/service/PartnerService.java
package com.codeup.booksnova.service;

import com.codeup.booksnova.domain.Partner;
import java.util.List;
import java.util.Optional;

/**
 * Business operations for Partner entity.
 */
public interface PartnerService {
    Partner create(Partner partner) throws Exception;
    Optional<Partner> findById(int partnerId) throws Exception;
    List<Partner> findAll() throws Exception;
    Partner update(Partner partner) throws Exception;
    void delete(int partnerId) throws Exception;
}
