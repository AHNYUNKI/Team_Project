package com.api.shop_project.domain.member;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Address {

    private String City;

    private String street;

    private String zipcode;

}
