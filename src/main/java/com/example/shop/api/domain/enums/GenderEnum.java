package com.example.shop.api.domain.enums;

import com.example.shop.global.exception.GenderNotAcceptableException;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum GenderEnum {
    MALE("MALE"),
    FEMALE("FEMALE");

    private final String gender;

    GenderEnum(String gender){
        this.gender = gender;
    }

    @JsonCreator
    public static GenderEnum from(String sub) {
        for (GenderEnum gender : GenderEnum.values()) {
            if (gender.getGender().equalsIgnoreCase(sub)) {
                return gender;
            }
        }
        throw new GenderNotAcceptableException();
    }
}