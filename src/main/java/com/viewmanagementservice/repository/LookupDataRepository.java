package com.viewmanagementservice.repository;

import com.viewmanagementservice.entity.LookupData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LookupDataRepository extends JpaRepository<LookupData, String> {
}
