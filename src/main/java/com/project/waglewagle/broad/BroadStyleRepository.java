package com.project.waglewagle.broad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BroadStyleRepository extends JpaRepository<BroadStyle,Long> {
}
