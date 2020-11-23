package com.gusrubin.lab.jpa.config;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class UUIDStringGenerator implements IdentifierGenerator {

    /**
     * Generate String ID only if new.
     * @return Serializable
     */
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        Serializable id = session.getEntityPersister(null, object).getClassMetadata().getIdentifier(object, session);
        return id != null ? id : UUID.randomUUID();
    }

}
