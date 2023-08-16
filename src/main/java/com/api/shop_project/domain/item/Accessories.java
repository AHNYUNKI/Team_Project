package com.api.shop_project.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("accessories")
@Getter @Setter
public class Accessories extends Item{

    @Enumerated(value = EnumType.STRING)
    private AccessoryType accessoryType;

}
