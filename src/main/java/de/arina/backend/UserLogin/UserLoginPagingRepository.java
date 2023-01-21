package de.arina.backend.UserLogin;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginPagingRepository extends PagingAndSortingRepository<UserLogin, Long> {
}
