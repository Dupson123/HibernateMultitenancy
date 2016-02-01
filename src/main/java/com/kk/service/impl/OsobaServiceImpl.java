package com.kk.service.impl;

import com.kk.dao.OsobaDao;
import com.kk.model.Osoba;
import com.kk.service.OsobaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author kkluz
 */
@Service( "osobaService" )
@Transactional
public class OsobaServiceImpl implements OsobaService
{

    @Autowired
    private OsobaDao osobaDao;

    public void saveOsoba( Osoba osoba )
    {
        osobaDao.saveOsoba( osoba );
    }

    public List<Osoba> findAll()
    {
        return osobaDao.findAll();
    }

    public void deleteOsobaByPesel( String pesel )
    {
        osobaDao.deleteOsobaByPesel( pesel );
    }

    public Osoba findByPesel( String pesel )
    {
        return osobaDao.findByPesel( pesel );
    }

    public void updateOsoba( Osoba osoba )
    {
        osobaDao.updateOsoba( osoba );
    }
}
