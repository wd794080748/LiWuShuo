package com.wangdong.meilishuo.http;

/**
 * Created by yangjw on 2016/3/4.
 */
public interface RequestCallBack {

    public void onSuccess(String result, int requestCode);

    public void onFailure(String error);

    public void error(Exception ex) ;
}
