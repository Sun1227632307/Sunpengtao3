package com.example.app2.model;

import com.example.app2.beans.Fuli;
import com.example.app2.beans.ResultsBean;

import java.util.ArrayList;
import java.util.List;

public interface ICBack {
    void Ok(List<ResultsBean> list);
    void No(String error);
}
