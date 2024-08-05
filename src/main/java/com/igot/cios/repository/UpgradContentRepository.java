package com.igot.cios.repository;

import com.igot.cios.entity.UpgradContentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UpgradContentRepository extends JpaRepository<UpgradContentEntity, String> {

    Optional<UpgradContentEntity> findByExternalId(String externalId);

    List<UpgradContentEntity> findByIsActive(boolean b);
    @Query("SELECT c.ciosData FROM UpgradContentEntity c WHERE c.isActive = :isActive")
    Page<Object> findAllCiosDataAndIsActive(@Param("isActive") Boolean isActive, Pageable pageable);
}
