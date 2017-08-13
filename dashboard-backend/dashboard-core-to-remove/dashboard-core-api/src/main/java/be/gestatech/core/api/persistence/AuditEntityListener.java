package be.gestatech.core.api.persistence;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * Created by amuri on 6/18/2017.
 */
public class AuditEntityListener {

    @PrePersist
    public void onPrePersist(AbstractAuditable auditable){
        LocalDateTime now = LocalDateTime.ofInstant((new Date()).toInstant(), ZoneOffset.systemDefault());
        auditable.setCreatedDate(now);
        auditable.setCreatedBy(auditable.getCreatedBy());
    }

    @PreUpdate
    public void onPreUpdate(AbstractAuditable auditable){
        LocalDateTime now = LocalDateTime.ofInstant((new Date()).toInstant(), ZoneOffset.systemDefault());
        auditable.setLastModifiedDate(now);
        auditable.setLastModifiedBy(auditable.getLastModifiedBy());
    }
}
