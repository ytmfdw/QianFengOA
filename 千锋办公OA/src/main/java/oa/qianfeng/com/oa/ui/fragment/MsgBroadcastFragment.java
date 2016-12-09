package oa.qianfeng.com.oa.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import oa.qianfeng.com.oa.QFApp;
import oa.qianfeng.com.oa.R;
import oa.qianfeng.com.oa.adapter.RBaseAdapter;
import oa.qianfeng.com.oa.entity.MsgBroadcastBean;
import oa.qianfeng.com.oa.ui.activity.BroadcastActivity;
import oa.qianfeng.com.oa.utils.Constant;
import oa.qianfeng.com.oa.utils.DividerItemDecoration;
import oa.qianfeng.com.oa.utils.IntentUtils;
import oa.qianfeng.com.oa.widget.EmptyRecyclerView;
import oa.qianfeng.com.oa.widget.EmptyView;

/**
 * Created by Administrator on 2016/12/6.
 */
public class MsgBroadcastFragment extends BaseNetFragment implements RBaseAdapter.ItemClick {

    @BindView(R.id.erv)
    EmptyRecyclerView erv;
    @BindView(R.id.emptyView)
    EmptyView emptyView;
    RBaseAdapter<MsgBroadcastBean> adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        QFApp.getInstence().getMsgBus().register(this);

        adapter = new RBaseAdapter<MsgBroadcastBean>(getActivity(), android.R.layout.simple_list_item_1) {
            @Override
            public void bindData(RViewHolder holder, int position) {
                TextView tv = (TextView) holder.findViewById(android.R.id.text1);
                tv.setText(adapter.getData(position).toString());
            }
        };

        MessageFragment parent = (MessageFragment) getParentFragment();

        adapter.setDatas(parent.getBroadcastData());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_msg_broadcast, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emptyView.setText("暂无公告");
        erv.setLayoutManager(new LinearLayoutManager(getActivity()));
        erv.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL_LIST));
        erv.setEmptyView(emptyView);
        erv.setAdapter(adapter);

        adapter.setOnItemClickListener(this);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        QFApp.getInstence().getMsgBus().unregister(this);
    }


    @Subscribe
    public void onEventMainThread(Message msg) {
        if (msg.what == Constant.MSG_UPDATE_UI) {
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), BroadcastActivity.class);
        intent.putExtra(IntentUtils.INTENT_KEY_MSG, adapter.getData(position));
        startActivity(intent);
    }

    @Override
    public boolean onLongItemClick(int position) {
        return false;
    }
}
