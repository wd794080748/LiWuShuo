package com.wangdong.meilishuo.Fragment;

import android.app.Fragment;
import android.net.Uri;

/**
 * Created by wd794 on 2016/3/14 0014.
 */
public class BaseFragment extends android.support.v4.app.Fragment {
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
