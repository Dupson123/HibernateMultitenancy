package com.kk.service;

import com.kk.model.Osoba;

import java.util.List;

/**
 * @author kkluz
 */
public interface OsobaService
{

    void saveOsoba( Osoba osoba );

    List<Osoba> findAll();

    void deleteOsobaByPesel( String pesel );

    Osoba findByPesel( String pesel );

    void updateOsoba( Osoba osoba );
}
