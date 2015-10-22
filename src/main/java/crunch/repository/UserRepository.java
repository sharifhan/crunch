package crunch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import crunch.domain.User;
/*
 * In Memory DB implementation of storing user.
 * 
 * */
public interface UserRepository extends JpaRepository<User, String> {
	
}
