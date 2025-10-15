// src/main/java/com/codeup/booksnova/service/PartnerServiceImpl.java
package com.codeup.booksnova.service;

import com.codeup.booksnova.dao.PartnerDAO;
import com.codeup.booksnova.dao.PartnerDaoJdbc;
import com.codeup.booksnova.domain.Partner;

import java.util.List;
import java.util.Optional;

/**
 * JDBC-backed implementation of PartnerService.
 */
public class PartnerServiceImpl implements PartnerService {
    private final PartnerDAO partnerDao = new PartnerDaoJdbc();

    @Override
    public Partner create(Partner partner) throws Exception {
        // Add any extra validation here (e.g. unique name)
        return partnerDao.save(partner);
    }

    @Override
    public Optional<Partner> findById(int partnerId) throws Exception {
        return partnerDao.findById(partnerId);
    }

    @Override
    public List<Partner> findAll() throws Exception {
        return partnerDao.findAll();
    }

    @Override
    public Partner update(Partner partner) throws Exception {
        // Enforce business rules (e.g. status transitions) before updating
        return partnerDao.update(partner);
    }

    @Override
    public void delete(int partnerId) throws Exception {
        // You might check for open loans before deletion
        partnerDao.delete(partnerId);
    }
}
