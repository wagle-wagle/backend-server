package com.project.waglewagle.broad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BroadRepository extends JpaRepository<Broad,Long> {
}
