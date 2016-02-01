package com.kk.multitenancy;

import com.kk.service.SchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * @author kkluz
 */
public class CurrentTenantIdentifierResolverImpl implements org.hibernate.context.spi.CurrentTenantIdentifierResolver

{
    @Autowired
    SchemaService schemaService;

    @Autowired
    Environment environment;

    public String resolveCurrentTenantIdentifier()
    {
        String tenantId = schemaService.getSchemaName();
        if ( tenantId == null ) {
            tenantId = environment.getRequiredProperty( "default.schema.name" );
        }

        System.out.println( "tenant id = " + tenantId );
        return tenantId;
    }

    public boolean validateExistingCurrentSessions()
    {
        return true;
    }

}