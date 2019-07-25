package com.example.app2.view;

import com.example.app2.beans.Fuli;
import com.example.app2.beans.ResultsBean;

import java.util.List;

public interface ICView {
    void Ok(List<ResultsBean> list);
    void No(String error);
}
