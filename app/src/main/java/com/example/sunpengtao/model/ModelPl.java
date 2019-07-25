package com.example.sunpengtao.model;

import com.example.sunpengtao.apis.MyApplication;
import com.example.sunpengtao.beans.Shuju;
import com.example.sunpengtao.db.ShujuDao;

import java.util.List;

public class ModelPl implements ICModel {

    private ShujuDao shujuDao;

    @Override
    public void GetData(ICBack icBack) {
        shujuDao = MyApplication.getInstance().getDaoSession().getShujuDao();
        List<Shuju> shujus = shujuDao.loadAll();
        icBack.Ok(shujus);
        icBack.NO("错误");
    }
}
