package org.example.productcatelogservice.dtos;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class UserDto {
    private String name;
    private List<String> roles = new ArrayList<>();
}