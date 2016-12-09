package oa.qianfeng.com.oa.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import oa.qianfeng.com.oa.R;
import oa.qianfeng.com.oa.adapter.RBaseAdapter;
import oa.qianfeng.com.oa.entity.LeaveBean;
import oa.qianfeng.com.oa.impl.OnGetDataListener;
import oa.qianfeng.com.oa.presenter.LeavePresenter;
import oa.qianfeng.com.oa.utils.Constant;
import oa.qianfeng.com.oa.utils.DividerItemDecoration;
import oa.qianfeng.com.oa.utils.IntentUtils;
import oa.qianfeng.com.oa.view.ILeaveView;
import oa.qianfeng.com.oa.widget.EmptyRecyclerView;
import oa.qianfeng.com.oa.widget.EmptyView;

/**
 * 加班。补签，请假列表
 */
public class LeaveActivity extends BaseNetActivity implements ILeaveView, RBaseAdapter.ItemClick {

    /**
     * 申请界面
     */
    final static int INTENT_REQUEST_ASK = 1;

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
    @BindView(R.id.emptyView)
    EmptyView emptyView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);
        ButterKnife.bind(this);
        type = getIntent().getIntExtra(IntentUtils.INTENT_KEY_TYPE, Constant.TYPE_LEFAVE);

        presenter = new LeavePresenter(this);
        presenter.init(type);
        presenter.loadData(type);

    }

    @OnClick(R.id.btn_ask)
    public void onClick() {
        //跳转到申请界面
        Intent intent = new Intent(this, AskActivity.class);
        intent.putExtra(IntentUtils.INTENT_KEY_TYPE, type);
        startActivityForResult(intent, INTENT_REQUEST_ASK);
    }

    @Override
    public void initRecyView(EmptyRecyclerView rv) {
        emptyView.setText("暂无数据");
        erv.setEmptyView(emptyView);
        erv.setLayoutManager(new LinearLayoutManager(this));
        erv.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL_LIST));
        erv.setAdapter(adapter);

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
        adapter.setOnItemClickListener(this);

        //初始化RecyleView
        initRecyView(erv);
    }

    @Override
    public void showLoading() {
        getShowDialog(true, "正在加载...").show();
    }

    @Override
    public void dismissLoading() {
        dismiss();
    }

    @Override
    public void showSuccess(String str) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showFaild(String str) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setListBean(List<LeaveBean> value) {
        data.clear();
        for (LeaveBean bean : value) {
            //过滤当前的数据
            if (bean.leaveType == type) {
                data.add(bean);
            }
        }
//        data.addAll(value);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, AskActivity.class);
        intent.putExtra(IntentUtils.INTENT_KEY_LEAVEBEAN, data.get(position));
        startActivityForResult(intent, INTENT_REQUEST_ASK);
//        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case INTENT_REQUEST_ASK: {
                //申请界面返回，如果正常，就重新加载数据
                if (resultCode == Constant.SUBMMIT_SUCCESS) {
                    presenter.loadData(type);
                }
            }
            break;
        }
    }

    @Override
    public boolean onLongItemClick(final int position) {
        if (data.get(position).state == Constant.STATE_WAIT) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("删除提示")
                    .setMessage("确定要删除该条数据吗?")
                    .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            presenter.delLeave(data.get(position));
                        }
                    })
                    .setPositiveButton("取消", null);
            builder.show();
        }

        return false;
    }
}
