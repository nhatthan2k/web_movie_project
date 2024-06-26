package ra.webmovieapp.model.base;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDate;

public class EntityAuditListener {
	@PrePersist
	public void beforePersist(AuditableEntity auditableEntity) {
		LocalDate date = LocalDate.now();
		auditableEntity.setCreatedDate(date);
		auditableEntity.setModifyDate(date);
	}
	
	@PreUpdate
	public void beforeMerge(AuditableEntity auditableEntity) {
		LocalDate date = LocalDate.now();
		auditableEntity.setModifyDate(date);
	}
}
