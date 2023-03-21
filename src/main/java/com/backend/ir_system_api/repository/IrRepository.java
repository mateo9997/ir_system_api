package com.backend.ir_system_api.repository;

import com.backend.ir_system_api.entity.IrEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IrRepository extends JpaRepository<IrEntity, String> {
}
