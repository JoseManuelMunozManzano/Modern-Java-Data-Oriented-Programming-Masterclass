package com.jmunoz.sealedpersistence.sec01.persistence.repository;

import com.jmunoz.sealedpersistence.sec01.persistence.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer> {
}
