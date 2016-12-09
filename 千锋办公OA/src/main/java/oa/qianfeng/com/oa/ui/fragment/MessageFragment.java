package oa.qianfeng.com.oa.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.greenrobot.eventbus.Subscribe;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import oa.qianfeng.com.oa.QFApp;
import oa.qianfeng.com.oa.R;
import oa.qianfeng.com.oa.entity.MsgBroadcastBean;
import oa.qianfeng.com.oa.entity.MsgMessageBean;
import oa.qianfeng.com.oa.impl.OnGetDataListener;
import oa.qianfeng.com.oa.presenter.MessagePresenter;
import oa.qianfeng.com.oa.utils.Constant;
import oa.qianfeng.com.oa.utils.L;
import oa.qianfeng.com.oa.view.IMessageView;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class MessageFragment extends BaseNetFragment implements OnGetDataListener<String>, IMessageView, RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.rb_broadcast)
    RadioButton rbBroadcast;
    @BindView(R.id.rb_message)
    RadioButton rbMessage;
    @BindView(R.id.rg)
    RadioGroup rg;

    List<MsgBroadcastBean> broadcastData = new ArrayList<>();
    List<MsgMessageBean> msgData = new ArrayList<>();


    MessagePresenter presenter;

    BaseNetFragment[] msgs = new BaseNetFragment[2];

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QFApp.getInstence().getMsgBus().register(this);
        presenter = new MessagePresenter(this);

        presenter.loadData(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rg.setOnCheckedChangeListener(this);
        if (msgs[0] == null) {
            msgs[0] = new MsgBroadcastFragment();
        }
        getChildFragmentManager().beginTransaction().replace(R.id.fragmentLayout, msgs[0]).commit();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void setTitle() {
        getActivity().setTitle("公告消息");
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_broadcast: {
                if (msgs[0] == null) {
                    msgs[0] = new MsgBroadcastFragment();
                }
                getChildFragmentManager().beginTransaction().replace(R.id.fragmentLayout, msgs[0]).commit();
            }
            break;
            case R.id.rb_message: {
                if (msgs[1] == null) {
                    msgs[1] = new MsgMessageFragment();
                }
                getChildFragmentManager().beginTransaction().replace(R.id.fragmentLayout, msgs[1]).commit();
            }
            break;
        }
    }


    public List<MsgBroadcastBean> getBroadcastData() {
        return broadcastData;
    }

    public List<MsgMessageBean> getMsgData() {
        return msgData;
    }

    @Override
    public void onGetDataSuccess(String value) {
        L.d(value);
        msgData.clear();
        Document doc = Jsoup.parse(value);
        //===========公告列表
        Element broadcastes = doc.getElementById("broadcast");
        Element body = broadcastes.select("tbody").first();
        Elements tres = body.select("tr");
        for (Element e : tres) {
            if (e.select("td").size() == 7) {
                MsgBroadcastBean broadcastBean = new MsgBroadcastBean(e);
                broadcastData.add(broadcastBean);
            }
        }
        //===========消息列表
        Element message = doc.getElementById("message");
        Element msgBody = message.select("tbody").first();
        Elements msgTr = msgBody.select("tr");
        for (Element e : msgTr) {
            if (e.select("td").size() == 3) {
                MsgMessageBean msg = new MsgMessageBean(e);
                msgData.add(msg);
            }
        }

        Message updateMsg = Message.obtain();
        updateMsg.what = Constant.MSG_UPDATE_UI;
        QFApp.getInstence().getMsgBus().post(updateMsg);
    }

    @Override
    public void onGetDataFaild() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        QFApp.getInstence().getMsgBus().unregister(this);
    }

    @Subscribe
    public void onEventMainThread(Message msg) {
        if (msg.what == Constant.MSG_GET_MSGDATA) {
            presenter.loadData(this);
        }
    }
}
