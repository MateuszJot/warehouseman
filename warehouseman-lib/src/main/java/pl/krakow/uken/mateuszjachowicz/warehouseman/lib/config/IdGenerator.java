package pl.krakow.uken.mateuszjachowicz.warehouseman.lib.config;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;
import org.hibernate.id.UUIDGenerator;

import java.io.Serializable;

public class IdGenerator extends UUIDGenerator {

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        Object id = session.getEntityPersister(null, object).getClassMetadata().getIdentifier(object, session);
        return id != null ? id : super.generate(session, object);
    }
}
