package com.example.demo.reposetory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CreatePost;

@Repository
public interface CreatepostRepo extends JpaRepository<CreatePost, Integer> {

    // ✅ HR POSTS → USER DASHBOARD RECENT ACTIVITY
    @Query("SELECT p FROM CreatePost p ORDER BY p.id DESC")
    List<CreatePost> findTop5RecentPosts();
}
