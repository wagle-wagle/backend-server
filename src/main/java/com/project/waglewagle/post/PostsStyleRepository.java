package com.project.waglewagle.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsStyleRepository extends JpaRepository<PostStyle, Long> {
}
