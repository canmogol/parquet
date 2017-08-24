package com.comodo.parquet.db;

import com.comodo.parquet.model.ConnectionModel;

import java.util.List;

/**
 * can | 8/24/17.
 */
public interface CFileDAO {
    void save(List<ConnectionModel> modelList);
}
