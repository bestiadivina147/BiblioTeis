package com.example.biblioteis.mapper;

import com.example.biblioteis.API.models.User;
import com.example.biblioteis.models.LogingData;

public class UserMapper {

    public static LogingData user2LogingData(User user){
        return new LogingData(user.getId(),user.getName(), user.getPasswordHash());
    }
}
