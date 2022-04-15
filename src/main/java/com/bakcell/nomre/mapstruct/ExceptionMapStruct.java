package com.bakcell.nomre.mapstruct;

import com.bakcell.nomre.exception.BaseException;
import com.bakcell.nomre.model.response.ExceptionResponse;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Javidan Alizada
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public abstract class ExceptionMapStruct {


    @Mapping(source = "message", target = "message")
    @Mapping(source = "httpStatus", target = "httpStatus")
    @Mapping(source = "date", target = "date")
    public abstract ExceptionResponse map(BaseException exception);
}
