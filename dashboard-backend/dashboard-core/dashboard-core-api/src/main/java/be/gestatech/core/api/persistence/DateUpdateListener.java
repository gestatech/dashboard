package be.gestatech.core.api.persistence;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * Created by amuri on 6/18/2017.
 */
public class DateUpdateListener {

    @PrePersist
    public void onPrePersist(Persistable entity){
        Date now = new Date();
        entity.setCreated(now);
        entity.setUpdated(now);
    }

    @PreUpdate
    public void onPreUpdate(Persistable entity){
        Date now = new Date();
        entity.setUpdated(now);
    }
}
