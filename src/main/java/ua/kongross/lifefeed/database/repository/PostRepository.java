package ua.kongross.lifefeed.database.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.kongross.lifefeed.database.entity.Post;
import ua.kongross.lifefeed.database.entity.User;

import java.util.Collection;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    Collection<Post> findByOrderByCreatedAtDesc();

    Collection<Post> findByAuthorOrderByCreatedAtDesc(User author);

    @Modifying
    void deleteAllByIdAndAuthor(Long id, User author);
}
