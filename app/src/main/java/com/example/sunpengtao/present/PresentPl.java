package com.example.sunpengtao.present;

import com.example.sunpengtao.beans.Shuju;
import com.example.sunpengtao.model.ICBack;
import com.example.sunpengtao.model.ModelPl;
import com.example.sunpengtao.view.ICView;

import java.util.List;

public class PresentPl implements ICPresent {
    private final ModelPl modelPl;
    private ICView icView;

    public PresentPl(ICView icView) {
        this.icView = icView;
        modelPl = new ModelPl();

    }

    @Override
    public void GetData() {
        modelPl.GetData(new ICBack() {
            @Override
            public void Ok(List<Shuju> arrayList) {
                icView.Ok(arrayList);
            }

            @Override
            public void NO(String error) {
                icView.NO(error);
            }
        });
    }
}
