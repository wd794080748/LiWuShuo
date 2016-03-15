package com.wangdong.meilishuo.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wangdong.meilishuo.Fragment.FenLeiFragment;
import com.wangdong.meilishuo.Fragment.MyFragment;
import com.wangdong.meilishuo.Fragment.ReMenFragment;
import com.wangdong.meilishuo.Fragment.ZhiNanFragment;
import com.wangdong.meilishuo.R;

public class ContentActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    private RadioGroup MradioGroup;
    private RadioButton ZNradioButton;
    private FragmentManager supportFragmentManager;
    private FragmentTransaction fragmentTransaction;
    private ReMenFragment reMenFragment;
    private ZhiNanFragment zhiNanFragment;
    private FenLeiFragment fenLeiFragment;
    private MyFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        supportFragmentManager = getSupportFragmentManager();
        initView();
        MradioGroup.setOnCheckedChangeListener(this);
        ZNradioButton.setChecked(true);

    }


    private void initView() {
        MradioGroup= (RadioGroup) findViewById(R.id.content_rg);
        ZNradioButton= (RadioButton) findViewById(R.id.rb_content_zhinan);
    }
    private void hideFragment(FragmentTransaction fragmentTransaction){
        if(zhiNanFragment!=null){
            fragmentTransaction.hide(zhiNanFragment);
        }
        if(fenLeiFragment!=null){
            fragmentTransaction.hide(fenLeiFragment);
        }
        if (reMenFragment!=null){
            fragmentTransaction.hide(reMenFragment);
        }
        if (myFragment!=null){
            fragmentTransaction.hide(myFragment);
        }
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        fragmentTransaction = supportFragmentManager.beginTransaction();
        hideFragment(fragmentTransaction);
        switch (checkedId){
            case R.id.rb_content_zhinan:
                if(zhiNanFragment==null){
                    zhiNanFragment=ZhiNanFragment.newInstance(null,null);
                    fragmentTransaction.add(R.id.content_fl,zhiNanFragment);
                }else {
                    fragmentTransaction.show(zhiNanFragment);
                }
                break;
            case R.id.rb_content_remnen:
                if(reMenFragment==null){
                    reMenFragment=ReMenFragment.newInstance(null,null);
                    fragmentTransaction.add(R.id.content_fl,reMenFragment);
                }else {
                    fragmentTransaction.show(reMenFragment);
                }
                break;
            case R.id.rb_content_fenlei:
                if(fenLeiFragment==null){
                    fenLeiFragment=FenLeiFragment.newInstance(null,null);
                    fragmentTransaction.add(R.id.content_fl,fenLeiFragment);
                }else {
                    fragmentTransaction.show(fenLeiFragment);
                }
                break;
            case R.id.rb_content_my:
                if(myFragment==null){
                    myFragment=MyFragment.newInstance(null,null);
                    fragmentTransaction.add(R.id.content_fl,myFragment);
                }else {
                    fragmentTransaction.show(myFragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }
}
