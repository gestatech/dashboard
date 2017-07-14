package be.gestatech.dashboard.infra.audit.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Created by amurifa on 12/07/2017.
 */
@Entity
@Table(name = AuditChange.TABLE_NAME)
public class AuditChange  implements Serializable{

	private static final long serialVersionUID = -4436931792189217191L;

	public final static String TABLE_NAME = "AUDIT_CHANGE";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AUDIT_CHANGE_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EVENT_ID")
	private AuditEvent event;

	@Column(name = "FIELD", nullable = false)
	private String field;

	@Column(name = "OLD_VALUE")
	private String oldValue;

	@Column(name = "NEW_VALUE")
	private String newValue;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUB_EVENT_ID")
	private AuditEvent subEvent;

	@Column(name = "SUB_EVENT_NAME")
	private String subEventName;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public AuditEvent getEvent() {
		return event;
	}

	public void setEvent(AuditEvent event) {
		this.event = event;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public AuditEvent getSubEvent() {
		return subEvent;
	}

	public void setSubEvent(AuditEvent subEvent) {
		this.subEvent = subEvent;
	}

	public String getSubEventName() {
		return subEventName;
	}

	public void setSubEventName(String subEventName) {
		this.subEventName = subEventName;
	}


}
