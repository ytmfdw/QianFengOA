package oa.qianfeng.com.oa.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import oa.qianfeng.com.oa.R;
import oa.qianfeng.com.oa.utils.L;
import oa.qianfeng.com.oa.view.IMessageView;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class MessageFragment extends BaseNetFragment implements IMessageView {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        L.d("MessageFragment onAttach");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        L.d("MessageFragment onViewCreated");

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void setTitle(String title) {
        getActivity().setTitle("这是公告消息界面");
    }
}
