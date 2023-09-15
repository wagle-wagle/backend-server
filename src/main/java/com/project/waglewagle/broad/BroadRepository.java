package com.project.waglewagle.broad;

import com.project.waglewagle.post.DTO.PostsResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BroadRepository extends JpaRepository<Broad,Long> {

}
