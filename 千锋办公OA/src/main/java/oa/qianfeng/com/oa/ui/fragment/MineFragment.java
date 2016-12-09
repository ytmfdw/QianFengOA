package oa.qianfeng.com.oa.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import oa.qianfeng.com.oa.R;
import oa.qianfeng.com.oa.entity.MineBean;
import oa.qianfeng.com.oa.impl.OnGetDataListener;
import oa.qianfeng.com.oa.presenter.MinePresenter;
import oa.qianfeng.com.oa.ui.activity.BaseNetActivity;
import oa.qianfeng.com.oa.ui.activity.LoginActivty;
import oa.qianfeng.com.oa.view.IMineView;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class MineFragment extends BaseNetFragment implements IMineView, OnGetDataListener<String> {
    MinePresenter presenter;

    MineBean mine;
    @BindView(R.id.tv_detail)
    TextView tvDetail;
    @BindView(R.id.btn_loginout)
    Button btnLoginout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MinePresenter(this);
        presenter.getData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.initViews();

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void setMineData(MineBean bean) {
        mine = bean;
        if (tvDetail != null && mine != null) {
            tvDetail.setText(bean.toString());
        }
    }

    @Override
    public void setTitle(String str) {
        getActivity().setTitle(str);
    }

    @Override
    public void initViews() {
        setMineData(mine);
    }

    @OnClick(R.id.btn_loginout)
    public void onClick() {
        //退出登录
        presenter.loginOut(this);
    }

    @Override
    public void onGetDataSuccess(String value) {
        //退出到登录界面
        Intent intent = new Intent(getActivity(), LoginActivty.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onGetDataFaild() {
        Toast.makeText(getActivity(), "退出失败", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        BaseNetActivity act = (BaseNetActivity) getActivity();
        act.getShowDialog(true, "正在加载...").show();
    }

    @Override
    public void dismissLoading() {
        BaseNetActivity act = (BaseNetActivity) getActivity();
        act.dismiss();
    }
}
