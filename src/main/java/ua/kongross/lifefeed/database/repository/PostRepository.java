package ua.kongross.lifefeed.database.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ua.kongross.lifefeed.database.entity.Post;

import java.util.Collection;

@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
    Collection<Post> findByOrderByCreatedAtAsc();
}
