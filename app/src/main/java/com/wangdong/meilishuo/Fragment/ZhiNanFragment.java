package com.wangdong.meilishuo.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangdong.meilishuo.R;
import com.wangdong.meilishuo.adapter.ZhiNanViewPageAdapter;
import com.wangdong.meilishuo.http.HttpUtils;
import com.wangdong.meilishuo.http.RequestCallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ZhiNanFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ZhiNanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ZhiNanFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String TABLAYOUT_URL=
            "http://api.liwushuo.com/v2/channels/preset?gender=1&generation=2";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private TabLayout tabLayout;
    private List<String> titleList;
    private ViewPager viewPager;
    private ZhiNanViewPageAdapter zhiNanViewPageAdapter;
    private int fragmentCount=0;
    private OnFragmentInteractionListener mListener;
    private View contentView;
    private ArrayList<Fragment> fragmentList;

    public ZhiNanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ZhiNanFragment.
     */
    public static ZhiNanFragment newInstance(String param1, String param2) {
        ZhiNanFragment fragment = new ZhiNanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(contentView==null){
            contentView=inflater.inflate(R.layout.fragment_zhi_nan,null);
        }
        initView();
        initData();
        return contentView;
    }

    private void initData() {
        titleList=new ArrayList<>();
        fragmentList=new ArrayList<>();
        fragmentList.add(jingxuanFragment.newInstance(null, null));
            HttpUtils.requestGet(TABLAYOUT_URL, new RequestCallBack() {
                @Override
                public void onSuccess(String result, int requestCode) {
                    doJson(result);
                    for (int i=0;i<fragmentCount;i++){
                        fragmentList.add(CommonFragment.newInstance(null,null));
                    }
                    zhiNanViewPageAdapter = new ZhiNanViewPageAdapter(getFragmentManager(), titleList, fragmentList);
                    viewPager.setAdapter(zhiNanViewPageAdapter);
                    tabLayout.setupWithViewPager(viewPager);
                }

                @Override
                public void onFailure(String error) {

                }

                @Override
                public void error(Exception ex) {

                }
            }, 100);

    }

    private void doJson(String result) {
        try {
            JSONObject jsonObject=new JSONObject(result);
            JSONObject dataObject=jsonObject.getJSONObject("data");
            JSONArray arrays=dataObject.getJSONArray("channels");
            fragmentCount=arrays.length();
            for (int i=0;i<fragmentCount;i++){
                JSONObject object=arrays.getJSONObject(i);
                titleList.add(object.getString("name"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void initView() {
        tabLayout= (TabLayout) contentView.findViewById(R.id.tl_zhinan);
        viewPager= (ViewPager) contentView.findViewById(R.id.vp_zhinan);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
