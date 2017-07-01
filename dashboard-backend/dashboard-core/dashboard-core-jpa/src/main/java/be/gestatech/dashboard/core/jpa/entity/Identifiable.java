package be.gestatech.dashboard.core.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import be.gestatech.dashboard.core.jpa.entity.user.Users;

/**
 * Identifiable object interface
 * Created by amurifa on 30/06/2017.
 */
public interface Identifiable<PK extends Serializable> {

	PK getId();

	void setId(PK id);

	Users getUserCreated();

	void setUserCreated(Users userCreated);

	Date getDateCreated();

	void setDateCreated(Date datereated);

	boolean getDeleted();

	void setDeleted(boolean deleted);
}
