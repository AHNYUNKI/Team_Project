package com.api.shop_project.dto.response.member;

import com.api.shop_project.domain.BaseTime;
import com.api.shop_project.domain.member.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter

//@Builder
//@AllArgsConstructor

@Getter
@NoArgsConstructor
public class MemberSave extends BaseTime{
    private Long id;

    private String email;

    private String password;

    private String name;

    private String phone;

    private Role role;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String city;

    private String street;

    private String zipcode;


//        @Builder
//        public MemberSave(Long id, String email, String password, String name, String phone, Role role, String city) {
//            this.id = id;
//            this.email = email;
//            this.password = password;
//            this.name = name;
//            this.phone = phone;
//            this.role = role;
//            this.city = city;
//        }

    @Builder
    public MemberSave(Long id, String email, String password, String name, String phone, Role role, String city, String street, String zipcode, LocalDateTime createTime, LocalDateTime updateTime
    ) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.role = role;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

}

