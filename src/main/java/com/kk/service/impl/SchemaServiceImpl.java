package com.kk.service.impl;

import com.kk.service.SchemaService;
import org.springframework.stereotype.Service;

/**
 * @author kkluz
 */
@Service("schemaService")
public class SchemaServiceImpl implements SchemaService
{

    private String schemaName;

    public void setSchemaName( String schemaName )
    {
        this.schemaName = schemaName;
    }

    public String getSchemaName()
    {
        return schemaName;
    }
}
