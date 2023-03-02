package org.employee.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

@MappedSuperclass
public abstract class MetaData {

  @Column(name = "create_time")
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column(name = "update_time")
  @UpdateTimestamp
  private LocalDateTime updatedAt;

  @Column(name = "active")
  final private int active=1;

  @CreatedBy
  @Column(name = "created_by")
  private String creator;

  @LastModifiedBy
  @Column(name = "updated_by")
  private String updater;
}