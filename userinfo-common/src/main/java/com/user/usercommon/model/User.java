package com.user.usercommon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    Integer id;
    String name;
    String email;
    private Integer isSend;
    private Integer isDelete;
    private String password;
}
