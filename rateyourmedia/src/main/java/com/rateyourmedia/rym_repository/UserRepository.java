package com.rateyourmedia.rym_repository;

import com.rateyourmedia.rym_entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,String> {
}
