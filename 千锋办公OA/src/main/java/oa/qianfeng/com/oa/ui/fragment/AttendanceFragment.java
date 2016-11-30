package oa.qianfeng.com.oa.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import oa.qianfeng.com.oa.R;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class AttendanceFragment extends BaseNetFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_attendance, container, false);
    }
}
