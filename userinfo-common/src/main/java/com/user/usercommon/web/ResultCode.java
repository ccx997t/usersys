package com.user.usercommon.web;

public enum ResultCode {

    SUCCESS(200, "Success"),

    DATA_ACCESS_ERROR(401, " Data_access_error"),
    PARAM_IS_INVALID(403, "Param_invalid"),
    PARAM_IS_BLANK(402, "Param_null"),
    PARAM_NOT_COMPLETE(405, "Param_missing"),
    USER_NAME_EXISTS(406, "User_name_exists"),
    Server_ERROR(500,"Server_error");

    private Integer code;

    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
