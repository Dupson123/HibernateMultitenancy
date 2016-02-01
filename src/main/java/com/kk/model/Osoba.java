package com.kk.model;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author kkluz
 */
@Entity
@Table( name = "Osoba" )
public class Osoba implements Serializable
{

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id", unique = true, nullable = false )
    private Integer id;

    @Column( name = "imie", unique = false, nullable = false )
    private String imie;

    @Column( name = "pesel", unique = true, nullable = false )
    private String pesel;

    @Column( name = "data_urodzenia", unique = false, nullable = true )
    @Type( type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate" )
    private LocalDate dataUrodzenia;

    public void setId( Integer id )
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }

    public LocalDate getDataUrodzenia()
    {
        return dataUrodzenia;
    }

    public void setDataUrodzenia( LocalDate dataUrodzenia )
    {
        this.dataUrodzenia = dataUrodzenia;
    }

    public String getImie()
    {
        return imie;
    }

    public void setImie( String imie )
    {
        this.imie = imie;
    }

    public String getPesel()
    {
        return pesel;
    }

    public void setPesel( String pesel )
    {
        this.pesel = pesel;
    }

    @Override
    public String toString()
    {
        return "Osoba{" +
                "id=" + id +
                ", imie='" + imie + '\'' +
                ", pesel='" + pesel + '\'' +
                ", dataUrodzenia=" + dataUrodzenia +
                '}';
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        Osoba osoba = (Osoba) o;
        return Objects.equals( id, osoba.id ) &&
                Objects.equals( pesel, osoba.pesel );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id, pesel );
    }
}
