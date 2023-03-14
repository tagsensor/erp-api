package com.carleonis.erpapi.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.carleonis.erpapi.model.ClienteStatus;

@Repository
public interface ClienteStatusRepository extends JpaRepository<ClienteStatus, Long> {

}
