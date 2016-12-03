package oa.qianfeng.com.oa.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import oa.qianfeng.com.oa.R;
import oa.qianfeng.com.oa.view.IMineView;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class MineFragment extends BaseNetFragment implements IMineView {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void setTitle() {
        getActivity().setTitle("这是我的界面");
    }
}
