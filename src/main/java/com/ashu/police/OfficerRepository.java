package com.ashu.police;

import org.springframework.data.jpa.repository.JpaRepository;
import javax.transaction.Transactional;

public interface OfficerRepository extends JpaRepository<AppUser, Integer> {
	boolean existsByUsername(String username);

	Officer findByUsername(String username);

	@Transactional
	void deleteByUsername(String username);
}
