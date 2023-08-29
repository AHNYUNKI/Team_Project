package com.api.shop_project.repository.member;

import com.api.shop_project.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
=======
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
>>>>>>> 5c8e7fb138c77e53f420c48b8749179e66abb5b1
}
