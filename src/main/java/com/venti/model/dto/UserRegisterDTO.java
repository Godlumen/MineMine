package com.venti.model.dto;

import lombok.Data;

@Data
public class UserRegisterDTO {
    private String mail;
    private String userName;
    private String passwd;
}
