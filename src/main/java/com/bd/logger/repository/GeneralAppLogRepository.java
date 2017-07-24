package com.bd.logger.repository;

import com.bd.logger.domain.GeneralAppLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Spring Data JPA repository for the GeneralAppLog entity.
 */
@SuppressWarnings("unused")
public interface GeneralAppLogRepository extends JpaRepository<GeneralAppLog,Long> {

}

