package crunch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import crunch.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
}
