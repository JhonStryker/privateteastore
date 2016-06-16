package com.priavteTeaStore.repository;

import com.priavteTeaStore.domain.HAuthAccess;
import com.priavteTeaStore.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Transactional
@Repository
public interface HAuthAccesserRepository extends JpaRepository<HAuthAccess, Long> {

    default User findUserByToken(String token) {
        HAuthAccess access = findByToken(token);
        if (access == null) {
            return null;
        }
        return access.getUser();
    }

    HAuthAccess findByToken(String access_token);


}
