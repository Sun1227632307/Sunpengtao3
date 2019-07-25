package com.example.sunpengtao;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sunpengtao.adapters.ReccycViewAdapter;
import com.example.sunpengtao.apis.MyApplication;
import com.example.sunpengtao.beans.Shuju;
import com.example.sunpengtao.db.ShujuDao;
import com.example.sunpengtao.present.PresentPl;
import com.example.sunpengtao.view.ICView;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity implements ICView {

    private RecyclerView mRec;
    private ArrayList<Shuju> shujus;
    private ReccycViewAdapter reccycViewAdapter;
    private ShujuDao shujuDao;
    private PresentPl presentPl;
    private int _position;
    private EditText et1;
    private String sss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        shujuDao = MyApplication.getInstance().getDaoSession().getShujuDao();
        initView();
        initData();
        registerForContextMenu(mRec);
        //initDta();
    }

    private void initDta() {
        presentPl = new PresentPl(this);
        presentPl.GetData();
    }

    private void initData() {
        List<Shuju> shujusa = shujuDao.loadAll();
        shujus.addAll(shujusa);
        reccycViewAdapter.notifyDataSetChanged();
    }

    private void initView() {
        mRec = (RecyclerView) findViewById(R.id.Rec);
        mRec.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        mRec.setLayoutManager(new LinearLayoutManager(this));
        shujus = new ArrayList<>();
        reccycViewAdapter = new ReccycViewAdapter(shujus, this);
        mRec.setAdapter(reccycViewAdapter);
        reccycViewAdapter.setA(new ReccycViewAdapter.A() {
            @Override
            public void OMclicked(int position) {
                _position=position;
            }
        });
    }

    @Override
    public void Ok(List<Shuju> arrayList) {
        shujus.addAll(arrayList);
        reccycViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void NO(String error) {
        Toast.makeText(this, "数据错误"+error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==1){
            shujus.remove(_position);
            reccycViewAdapter.notifyDataSetChanged();
        }else {
            View view = View.inflate(this, R.layout.popwindow, null);
            PopupWindow window = new PopupWindow(view,400,500);
            window.setOutsideTouchable(true);
            window.setFocusable(true);
            window.setOutsideTouchable(true);
            window.setBackgroundDrawable(new ColorDrawable());
            window.showAsDropDown(mRec,-400,-500);
            et1 = view.findViewById(R.id.et1);

            Button bt = view.findViewById(R.id.bt);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sss = et1.getText().toString();
                    Shuju shuju = shujus.get(_position);
                    shuju.setName(sss);
                    shujuDao.insertOrReplace(shuju);
                    reccycViewAdapter.notifyDataSetChanged();
                }
            });

        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(1,1,1,"删除");
        menu.add(2,2,2,"修改");
        super.onCreateContextMenu(menu, v, menuInfo);
    }
}
