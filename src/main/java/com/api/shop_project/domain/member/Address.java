package com.api.shop_project.domain.member;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Address {

    @Column(nullable = false)
    private String City;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String zipcode;

}
