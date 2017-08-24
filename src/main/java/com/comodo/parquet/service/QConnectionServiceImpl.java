package com.comodo.parquet.service;

import com.comodo.parquet.db.QConnectionDAO;

/**
 * can | 8/23/17.
 */
public class QConnectionServiceImpl implements QConnectionService {

    private final QConnectionDAO dao;

    public QConnectionServiceImpl(QConnectionDAO dao) {
        this.dao = dao;
    }
}
