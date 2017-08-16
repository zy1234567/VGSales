package com.ztstech.vgmate.activitys.location_select;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.location_select.adapter.CityAdapter;
import com.ztstech.vgmate.activitys.location_select.adapter.ProvinceAdapter;
import com.ztstech.vgmate.data.beans.LocationBean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LocationInfoActivity extends AppCompatActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.listview1)
    ListView mListview1;
    @BindView(R.id.linearLayout1)
    LinearLayout linearLayout1;
    @BindView(R.id.txt1)
    TextView txt1;
    @BindView(R.id.listview2)
    ListView mListview2;
    @BindView(R.id.linearLayout2)
    LinearLayout linearLayout2;
    @BindView(R.id.txt2)
    TextView txt2;
    @BindView(R.id.listview3)
    ListView mListview3;
    @BindView(R.id.linearLayout3)
    LinearLayout linearLayout3;

    private ProvinceAdapter adapter1;
    private CityAdapter adapter2;
    List<LocationBean.CityBean> citylist;
    private String rec_Province;
    private String rec_City;
    private Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_info_select);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {


        String s = getDataFromAssets("location.txt");

        final List<LocationBean> list = new Gson().fromJson(s, new TypeToken<List<LocationBean>>() {
        }.getType());


        adapter1 = new ProvinceAdapter(list, this);
        mListview1.setAdapter(adapter1);

        mListview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                linearLayout1.setVisibility(View.GONE);
                linearLayout2.setVisibility(View.VISIBLE);
                txt1.setVisibility(View.VISIBLE);
                txt1.setText(list.get(i).getSname());
                intent.putExtra("province", list.get(i).getSname());
                intent.putExtra(("pcode"),list.get(i).getSid());
                citylist = list.get(i).getCity();
                adapter2 = new CityAdapter(citylist, LocationInfoActivity.this);
                mListview2.setAdapter(adapter2);
                ItemListener listener = new ItemListener();
                mListview2.setOnItemClickListener(listener);
//                ToastUtil.toastCenter(LocationInfoActivity.this,list.get(i).getSname());
            }
        });
    }


    public class ItemListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            intent.putExtra("city", citylist.get(i).getSname());
            intent.putExtra("ccode",citylist.get(i).getSid());
            LocationInfoActivity.this.setResult(RESULT_OK,intent);
            LocationInfoActivity.this.finish();

//            ToastUtil.toastCenter(LocationInfoActivity.this, citylist.get(i).getSname());
        }
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        back();
    }


    public void back(){
        if(linearLayout1.getVisibility() == View.GONE){
            linearLayout1.setVisibility(View.VISIBLE);
        }else{
            finish();
        }
    }
    /***
     * 从assets文件中获得Json串
     */

    public String getDataFromAssets(String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onBackPressed() {
        back();
    }
}
