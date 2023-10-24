package com.project.waglewagle.broad;

import com.project.waglewagle.post.DTO.PostsResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BroadRepository extends JpaRepository<Broad,Long> {
    Optional<Broad> findByUrl(String broadUrl);

}
