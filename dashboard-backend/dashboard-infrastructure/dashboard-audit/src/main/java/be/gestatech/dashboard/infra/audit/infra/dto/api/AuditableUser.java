package be.gestatech.dashboard.infra.audit.infra.dto.api;

/**
 * Created by amurifa on 13/07/2017.
 */
public interface AuditableUser {
	/**
	 * The technical reference to the user
	 *
	 * @return a nummerical ID or <code>null</code>
	 */
	Long getUserId();

	/**
	 * Set the technical reference to the user
	 *
	 * @param userId a nummerical ID or <code>null</code>
	 */
	void setUserId(Long userId);

	/**
	 * The name of the user.
	 *
	 * @return users name or <code>null</code>
	 */
	String getUserName();

	/**
	 * Set the name of the user.
	 *
	 * @param userName users name or <code>null</code>
	 */
	void setUserName(String userName);
}
