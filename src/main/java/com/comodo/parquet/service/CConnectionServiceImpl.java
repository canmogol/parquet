package com.comodo.parquet.service;

import com.comodo.parquet.db.CConnectionDAO;

/**
 * can | 8/23/17.
 */
public class CConnectionServiceImpl implements CConnectionService {

    private final CConnectionDAO dao;

    public CConnectionServiceImpl(CConnectionDAO dao) {
        this.dao = dao;
    }

}
