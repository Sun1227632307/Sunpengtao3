package com.example.app2.present;

import com.example.app2.beans.Fuli;
import com.example.app2.beans.ResultsBean;
import com.example.app2.model.ICBack;
import com.example.app2.model.ModelPl;
import com.example.app2.view.ICView;

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
            public void Ok(List<ResultsBean> list) {
                icView.Ok(list);
            }

            @Override
            public void No(String error) {
                icView.No(error);
            }
        });
    }
}
