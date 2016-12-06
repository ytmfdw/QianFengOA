package oa.qianfeng.com.oa.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import oa.qianfeng.com.oa.R;
import oa.qianfeng.com.oa.adapter.RBaseAdapter;
import oa.qianfeng.com.oa.entity.LeaveBean;
import oa.qianfeng.com.oa.presenter.LeavePresenter;
import oa.qianfeng.com.oa.utils.Constant;
import oa.qianfeng.com.oa.utils.IntentUtils;
import oa.qianfeng.com.oa.view.ILeaveView;
import oa.qianfeng.com.oa.widget.EmptyRecyclerView;

/**
 * 加班。补签，请假列表
 */
public class LeaveActivity extends BaseNetActivity implements ILeaveView {

    int type = Constant.TYPE_LEFAVE;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.erv)
    EmptyRecyclerView erv;
    @BindView(R.id.btn_ask)
    Button btnAsk;

    RBaseAdapter<LeaveBean> adapter;

    List<LeaveBean> data;

    LeavePresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_leave);
        ButterKnife.bind(this);
        type = getIntent().getIntExtra(IntentUtils.INTENT_KEY_TYPE, Constant.TYPE_LEFAVE);

        presenter = new LeavePresenter(this);
        presenter.init(type);

    }

    @OnClick(R.id.btn_ask)
    public void onClick() {
    }

    @Override
    public void initRecyView(EmptyRecyclerView rv) {

    }

    @Override
    public void setTitle(String type) {
        title.setText(type);
        btnAsk.setText("申请" + type);
    }

    @Override
    public void init() {
        data = new ArrayList<>();
        adapter = new RBaseAdapter<LeaveBean>(this, data, android.R.layout.simple_list_item_1) {
            @Override
            public void bindData(RViewHolder holder, int position) {
                TextView tv = (TextView) holder.findViewById(android.R.id.text1);
                tv.setText(data.get(position).toString());
            }
        };
    }
}
