package com.api.shop_project.dto.response.member;

import com.api.shop_project.domain.member.Address;
import com.api.shop_project.domain.member.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Setter

//@Builder
//@AllArgsConstructor

@Getter
@NoArgsConstructor
public class MemberDto {
    private Long id;

    private String email;

    private String password;

    private String name;

    private String phone;

    private Address address;

    private Role role;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;



    @Builder
    public MemberDto(Long id, String email, String password, String name, String phone, Address address, Role role, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.role = role;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

//    @Builder
//    public MemberDto(Long id, String email, String password, String name, String phone, Role role) {
//        this.id = id;
//        this.email = email;
//        this.password = password;
//        this.name = name;
//        this.phone = phone;
//        this.role = role;
//    }

//    @Builder
//    public Update(String password, String name) {
//        this.password = password;
//        this.name = name;
//    }

//    @Builder
//    public Update(Long id, String password, String name, String email, Address address) {
//        this.id = id;
//        this.password = password;
//        this.name = name;
//        this.email = email;
//        this.address = address;
//    }



}