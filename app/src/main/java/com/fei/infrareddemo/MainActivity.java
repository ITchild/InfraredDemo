package com.fei.infrareddemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.ConsumerIrManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ConsumerIrManager cim;
    private RecyclerView contorl_con_rlv;

    private ContorlAdapter mContorlAdapter;

    private List<String> data;

    private TextView contorl_code_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initInfrared();
    }

    private void initView(){
        contorl_con_rlv = findViewById(R.id.contorl_con_rlv);
        contorl_code_tv = findViewById(R.id.contorl_code_tv);
    }

    private void initData(){
        if(null == data){
            data = new ArrayList<>();
        }
        for(String flag : InfraredCodeUtil.contorlNameArr){
            data.add(flag);
        }
        mContorlAdapter = new ContorlAdapter(this,data);
        contorl_con_rlv.setLayoutManager(new GridLayoutManager(this,4));
        contorl_con_rlv.setAdapter(mContorlAdapter);
        mContorlAdapter.setOnItemClick(new ContorlAdapter.OnItemClick() {
            @Override
            public void onClick(int position) {
                contorl_code_tv.setText("");
                int code = InfraredCodeUtil.contorlCodeArr[position];
                int [] codeArr = InfraredCodeUtil.getArrayCode(code);
                cim.transmit(38400, codeArr);
                for (int flag : codeArr){
                    contorl_code_tv.append(flag+",");
                }
            }
        });
    }

    private void initInfrared() {
        // 获取系统的红外遥控服务
        cim = (ConsumerIrManager) getSystemService(Context.CONSUMER_IR_SERVICE);
        if (!cim.hasIrEmitter()) {
            showMsg("当前手机不支持红外遥控");
        }
    }

    private void showMsg (String msg){
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle("提示");
        dialog.setMessage(msg);
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
    }

}
