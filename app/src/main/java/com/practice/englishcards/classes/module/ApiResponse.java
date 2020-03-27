package com.practice.englishcards.classes.module;

/**
 * 通常 api 回覆，會有 code , message , data
 * data 會有不同名稱時，可使用泛型 <T> , 只需要編寫data字段所對應的POJO
 * {"code":"0","message":"success","data":[]}
 */

public class ApiResponse<T> {
    public int code;
    public String message;
    public T data;

    /**
     * String json = "{..........}";
     * Gson gson = new Gson();
     * UserResult userResult = gson.fromJson(json,UserResult.class);
     * User user = userResult.data;
     *
     * UserListResult userListResult = gson.fromJson(json,UserListResult.class);
     * List<User> users = userListResult.data;
     *
     */

}
