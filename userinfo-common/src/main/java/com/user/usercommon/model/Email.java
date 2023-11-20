package com.user.usercommon.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Email {
    @ApiModelProperty(value = "receive email address")
    String address;
    @ApiModelProperty(value = "email title")
    String title;
    @ApiModelProperty(value = "email content")
    String content;
}
