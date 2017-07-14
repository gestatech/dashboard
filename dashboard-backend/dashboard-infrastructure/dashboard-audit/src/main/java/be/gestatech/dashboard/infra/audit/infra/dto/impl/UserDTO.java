package be.gestatech.dashboard.infra.audit.infra.dto.impl;

import be.gestatech.dashboard.infra.audit.infra.dto.api.AuditableUser;

/**
 * Created by amurifa on 13/07/2017.
 */
public class UserDTO implements AuditableUser{

	private Long userId;

	private String userName;

	public UserDTO(AuditableUser auditableUser) {
		super();
		this.userId = auditableUser.getUserId();
		this.userName = auditableUser.getUserName();
	}

	@Override
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
