package net.skhu.repository;

import net.skhu.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    List<Publisher> findAll();
}
