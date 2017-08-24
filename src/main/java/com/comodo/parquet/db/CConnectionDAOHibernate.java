package com.comodo.parquet.db;

import com.comodo.parquet.model.ConnectionModel;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

/**
 * can | 8/23/17.
 */
public class CConnectionDAOHibernate extends AbstractDAO<ConnectionModel> implements CConnectionDAO {

    public CConnectionDAOHibernate(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
