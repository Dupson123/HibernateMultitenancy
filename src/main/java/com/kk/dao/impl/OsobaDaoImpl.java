package com.kk.dao.impl;

import com.kk.dao.AbstractDao;
import com.kk.dao.OsobaDao;
import com.kk.model.Osoba;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.cfg.Settings;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author kkluz
 */
@Repository( "osobaDao" )
public class OsobaDaoImpl extends AbstractDao implements OsobaDao
{

    public void saveOsoba( Osoba osoba )
    {
        persist( osoba );
    }

    @SuppressWarnings( "unchecked" )
    public List<Osoba> findAll()
    {
        Criteria criteria = getSession().createCriteria( Osoba.class );
        return (List<Osoba>) criteria.list();
    }

    public void deleteOsobaByPesel( String pesel )
    {
        Query query = getSession().createQuery( "delete from Osoba where pesel = :pesel" );
        query.setString( "pesel", pesel );
        query.executeUpdate();
    }

    public Osoba findByPesel( String pesel )
    {
        Criteria criteria = getSession().createCriteria( Osoba.class );
        criteria.add( Restrictions.eq( "pesel", pesel ) );
        return (Osoba) criteria.uniqueResult();
    }

    public void updateOsoba( Osoba osoba )
    {
        getSession().update( osoba );
    }
}
