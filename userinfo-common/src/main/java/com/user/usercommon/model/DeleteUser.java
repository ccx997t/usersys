package com.user.usercommon.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DeleteUser{
    @ApiModelProperty(value="user ids", required = true, dataType = "String")
    String ids;
}
