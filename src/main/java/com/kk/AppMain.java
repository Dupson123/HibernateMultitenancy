package com.kk;

import com.kk.configuration.ApplicationConfiguration;
import com.kk.model.Osoba;
import com.kk.service.OsobaService;
import com.kk.service.SchemaService;
import org.joda.time.LocalDate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.util.List;

/**
 * @author kkluz
 */
public class AppMain
{

    public static void main( String[] args )
    {
        AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext( ApplicationConfiguration.class );

        OsobaService osobaService = (OsobaService) applicationContext.getBean( "osobaService" );

        SchemaService schemaService= (SchemaService) applicationContext.getBean( "schemaService" );

        Osoba osoba1 = new Osoba();
        osoba1.setImie( "Edek" );
        osoba1.setPesel( "123456789" );
        osoba1.setDataUrodzenia( new LocalDate( 1990, 5, 14 ) );

        Osoba osoba2 = new Osoba();
        osoba2.setImie( "Stefan" );
        osoba2.setPesel( "987654321" );
        osoba2.setDataUrodzenia( new LocalDate( 1991, 11, 14 ) );

        osobaService.saveOsoba( osoba1 );
        List<Osoba> osoby = osobaService.findAll();
        for ( Osoba osoba : osoby ) {
            System.out.println(osoba);
        }
        osobaService.deleteOsobaByPesel( "123456789" );

        schemaService.setSchemaName( "schemat1" );

        osobaService.saveOsoba( osoba2 );
        osoby = osobaService.findAll();
        for ( Osoba osoba : osoby ) {
            System.out.println(osoba);
        }
        osobaService.deleteOsobaByPesel( "987654321" );

        applicationContext.close();
    }
}
