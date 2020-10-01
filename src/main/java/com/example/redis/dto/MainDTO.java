package com.example.redis.dto;

import org.modelmapper.ModelMapper;

public class MainDTO<T> {



    public static <T> T transferObject(Object model, Class<T> type){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(model, type);
    }

}
