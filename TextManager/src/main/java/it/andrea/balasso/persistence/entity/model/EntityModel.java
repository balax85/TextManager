package it.andrea.balasso.persistence.entity.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class EntityModel {

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "insert_date", nullable = false)
	private Date insertDate;
	
	@PrePersist
	protected void onCreate() {
		insertDate = new Date();
	}
	
}
