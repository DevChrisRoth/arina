package de.revaxlabs.backend.UserLogin;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLoginPagingRepository extends PagingAndSortingRepository<UserLogin, Long> {
}
