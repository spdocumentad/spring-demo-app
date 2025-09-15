package com.example.demo.core.common;

import java.util.List;

public interface BaseService<Req,Res> {
    List<Res> getAll();
    Res getById(Req id);
    void delete(Req id);
}
