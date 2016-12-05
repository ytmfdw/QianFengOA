package oa.qianfeng.com.oa.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import oa.qianfeng.com.oa.R;
import oa.qianfeng.com.oa.entity.MineBean;
import oa.qianfeng.com.oa.presenter.MinePresenter;
import oa.qianfeng.com.oa.view.IMineView;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class MineFragment extends BaseNetFragment implements IMineView {
    MinePresenter presenter;

    MineBean mine;
    @BindView(R.id.tv_detail)
    TextView tvDetail;

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
    public void setTitle() {
        getActivity().setTitle("这是我的界面");
    }

    @Override
    public void initViews() {
        setMineData(mine);
    }
}
