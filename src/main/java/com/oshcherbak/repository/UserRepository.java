package com.oshcherbak.repository;

import com.oshcherbak.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{
}
