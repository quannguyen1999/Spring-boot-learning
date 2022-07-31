package com.example.springbatch.config;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;
import org.springframework.oxm.xstream.XStreamMarshaller;

import java.util.Collection;

//ignore field if not exists
public class XStreamMarshallerConfig extends XStreamMarshaller {
    @Override
    protected void configureXStream(final XStream xstream) {
        super.configureXStream(xstream);
        // clear out existing permissions and set own ones
        xstream.addPermission(NoTypePermission.NONE);
        // allow some basics
        xstream.addPermission(NullPermission.NULL);
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        xstream.allowTypeHierarchy(Collection.class);
        // allow any type from the same package
        xstream.allowTypesByWildcard(new String[] {
                "com.example.**"
        });
        xstream.ignoreUnknownElements(); // will it blend?
    }
}
