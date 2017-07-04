package com.jbstore.o2o.gitlibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.jbstore.o2o.mylibrary.SingleSelector;

public class MainActivity extends AppCompatActivity implements SingleSelector.SelectListener {
    String str = "{\"result\":0,\"message\":\"Success\",\"data\":[{\"Id\":3,\"Name\":\"餐饮美食\"},{\"Id\":16,\"Name\":\"食品百货\"},{\"Id\":5,\"Name\":\"服饰百货\"},{\"Id\":14,\"Name\":\"日用百货\"},{\"Id\":11,\"Name\":\"母婴用品\"},{\"Id\":8,\"Name\":\"酒店宾馆\"},{\"Id\":9,\"Name\":\"旅行票务\"},{\"Id\":20,\"Name\":\"休闲娱乐\"},{\"Id\":10,\"Name\":\"美容护理\"},{\"Id\":15,\"Name\":\"摄影婚庆\"},{\"Id\":19,\"Name\":\"鲜花礼品\"},{\"Id\":17,\"Name\":\"数码家电\"},{\"Id\":13,\"Name\":\"汽车行业\"},{\"Id\":7,\"Name\":\"家居建材\"},{\"Id\":4,\"Name\":\"房地产业\"},{\"Id\":21,\"Name\":\"医疗器械\"},{\"Id\":18,\"Name\":\"文体办公\"},{\"Id\":6,\"Name\":\"广告印刷\"},{\"Id\":12,\"Name\":\"其它行业\"}],\"command\":\"Categories\",\"action\":\"GetCategories\"}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gson gson = new Gson();
        MsgBean msgBean = gson.fromJson(str, MsgBean.class);
        SingleSelector singleSelector=new SingleSelector(this, msgBean.getData(), R.layout.singselector);
        singleSelector.setListener(this);
//        singleSelector.show();
    }

    @Override
    public void select(String s, int i) {
        Log.e("gao", "select: " + s);
    }
}
