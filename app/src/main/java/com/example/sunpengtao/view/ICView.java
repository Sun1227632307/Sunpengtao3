package com.example.sunpengtao.view;

import com.example.sunpengtao.beans.Shuju;

import java.util.List;

public interface ICView {
    void Ok(List<Shuju> arrayList);
    void NO(String error);
}
