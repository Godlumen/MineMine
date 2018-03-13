package com.venti.model.dto;

import lombok.Data;

@Data
public class UserLoginDTO {
    private String mobile;
    private String email;
    private String passwd;
}
