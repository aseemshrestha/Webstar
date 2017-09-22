package com.webstar.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.webstar.models.UserReposts;
import com.webstar.viewmodels.RepostSubmissionsViewModel;

public interface RepostRepository extends JpaRepository<UserReposts, Long>
{
    @Query(
        value = "select total_reposts from User_Submissions u where u.id = ?1",
        nativeQuery = true )
    public int getTotalRepostsbyPostId(Long id);

    @Query(
        value = "select * from User_Reposts u where u.reposted_by = ?1 order by u.reposted_on desc limit ?2 offset ?3",
        nativeQuery = true )
    Optional<List<RepostSubmissionsViewModel>> findPostRepostsByUser1(Long userid, int limit, int offset);

    //  @Query( value = "select u.id,  u.category, u.subcategory,u.video_Url,u.image_Url, u.submittied_Date,u.contents,r.comments,r.post_id,r.reposted_by,r.reposted_on from User_Submissions u INNER JOIN User_Reposts r on u.id = r.post_id where r.reposted_by = ?1 order by r.reposted_on desc limit ?2 offset ?3", nativeQuery = true )
    @Query(
        value = "select * from User_Reposts u where u.reposted_by = ?1 order by u.reposted_on desc limit ?2 offset ?3",
        nativeQuery = true )
    Optional<List<UserReposts>> findPostRepostsByUser(Long userid, int limit, int offset);
    //Optional<List<RepostSubmissionsViewModel>>findPostRepostsByUser(Long userid, int limit, int offset); 
    
    @Query(
           value = "select * from User_Reposts u where u.reposted_by_username = ?1 order by u.reposted_on desc limit ?2 offset ?3",
           nativeQuery = true )
       Optional<List<UserReposts>> findPostRepostsByUsername(String username, int limit, int offset);

}
