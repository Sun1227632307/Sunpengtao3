package com.example.sunpengtao.model;

import com.example.sunpengtao.beans.Shuju;

import java.util.ArrayList;
import java.util.List;

public interface ICBack {
    void Ok(List<Shuju> arrayList);
    void NO(String error);

}
