package com.venti.model.dto;

import lombok.Data;

@Data
public class UserRegisterDTO {
    private String mail;
    private String mobile;
    private String verifyCode;
    private String userName;
    private String passwd;
}
