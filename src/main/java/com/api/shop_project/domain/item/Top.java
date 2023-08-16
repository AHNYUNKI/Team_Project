package com.api.shop_project.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("top")
@Getter
@Setter
public class Top extends Item{

    private String size;

}
