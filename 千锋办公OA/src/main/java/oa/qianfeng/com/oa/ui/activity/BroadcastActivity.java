package oa.qianfeng.com.oa.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import oa.qianfeng.com.oa.R;
import oa.qianfeng.com.oa.entity.BroadcastBean;
import oa.qianfeng.com.oa.entity.MsgBroadcastBean;
import oa.qianfeng.com.oa.presenter.BroadcastPresenter;
import oa.qianfeng.com.oa.utils.IntentUtils;
import oa.qianfeng.com.oa.view.IBroadcasdView;
import oa.qianfeng.com.oa.widget.BroadcaseView;

/**
 * Created by Administrator on 2016/12/9.
 */
public class BroadcastActivity extends BaseNetActivity implements IBroadcasdView {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.bv)
    BroadcaseView bv;

    MsgBroadcastBean bean;

    BroadcastPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        ButterKnife.bind(this);
        bean = getIntent().getParcelableExtra(IntentUtils.INTENT_KEY_MSG);
        presenter = new BroadcastPresenter(this);

        presenter.loadData(bean);
    }


    @Override
    public void setupViews() {
        title.setText(bean.title);
    }

    @Override
    public void setData(BroadcastBean bean) {
        bv.setData(bean);
    }

    @Override
    public void showLoading() {
        getShowDialog(true, "正在加载...").show();
    }

    @Override
    public void dismissLoading() {
        dismiss();
    }
}
