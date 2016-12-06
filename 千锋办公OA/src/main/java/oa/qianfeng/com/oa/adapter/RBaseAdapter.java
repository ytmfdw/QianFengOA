package oa.qianfeng.com.oa.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import oa.qianfeng.com.oa.utils.L;

/**
 * Created by Administrator on 2016/12/3 0003.
 */
public abstract class RBaseAdapter<T> extends RecyclerView.Adapter<RBaseAdapter.RViewHolder> {

    /**
     * item 类型
     */
    public final static int TYPE_NORMAL = 0;
    public final static int TYPE_HEADER = 1;//头部--支持头部增加一个headerView
    public final static int TYPE_FOOTER = 2;//底部--往往是loading_more
    public final static int TYPE_LIST = 3;//代表item展示的模式是list模式
    public final static int TYPE_STAGGER = 4;//代码item展示模式是网格模式

    public interface ItemClick {
        public void onItemClick(int position);

        public boolean onLongItemClick(int position);
    }

    int[] layoutIds;
    List<T> data;
    LayoutInflater inflater;

    private View headView;
    private View footerView;

    /**
     * Item点击监听
     */
    ItemClick listener;


    public RBaseAdapter(Context context, List<T> data, int... layoutIds) {
        this.layoutIds = layoutIds;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    public RBaseAdapter(Context context, int... layoutIds) {
        this.layoutIds = layoutIds;
        this.data = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    /**
     * 设置监听
     *
     * @param listener
     */
    public void setOnItemClickListener(ItemClick listener) {
        this.listener = listener;
    }

    /**
     * 设置数据源
     *
     * @param list
     */
    public void setDatas(List<T> list) {
        this.data.clear();
        if (list != null) {
            this.data = list;
        }
        notifyDataSetChanged();
    }

    public void addData(T t) {
        this.data.add(t);
        notifyDataSetChanged();
    }

    public void addData(List<T> list) {
        if (list != null && list.size() > 0) {
            this.data.addAll(list);
            notifyDataSetChanged();
        }
    }

    public T getData(int position) {
        return this.data.get(position);
    }

    @Override
    public RViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new RViewHolder(headView);
        }
        if (viewType == TYPE_FOOTER) {
            return new RViewHolder(footerView);
        }
        View view = inflater.inflate(layoutIds[viewType], parent, false);
        return new RViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RViewHolder holder, final int position) {
        int type = getItemViewType(position);
//        L.d("onBindViewHOlder type=" + type);
        if (type != TYPE_FOOTER && type != TYPE_HEADER) {
            if (listener != null) {
                holder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onItemClick(position);
                    }
                });
                holder.root.setOnLongClickListener(new View.OnLongClickListener() {

                    @Override
                    public boolean onLongClick(View v) {
                        return listener.onLongItemClick(position);
//                        return false;
                    }
                });
            }
            bindData(holder, position);
        }
    }

    public abstract void bindData(RViewHolder holder, int position);

    @Override
    public int getItemCount() {
        int count = data.size();
        if (footerView != null) {
            count++;
        }
        if (headView != null) {
            count++;
        }
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        int headerPosition = 0;
        int footerPosition = getItemCount() - 1;

        if (headerPosition == position && headView != null) {
            return TYPE_HEADER;
        }
        if (footerPosition == position && footerView != null) {
            return TYPE_FOOTER;
        }
        return super.getItemViewType(position);
    }

    public static class RViewHolder extends RecyclerView.ViewHolder {

        View root;

        public RViewHolder(View itemView) {
            super(itemView);
            root = itemView;
        }

        public View findViewById(int id) {
            return root.findViewById(id);
        }
    }

    private boolean isHeaderPosition(int position) {
        return position < data.size();
    }

    private boolean isFooterPosition(int position) {
        return position >= data.size() + getItemCount();
    }

    public void addHeadView(View view) {
        headView = view;
    }

    public void addFooterView(View view) {
        footerView = view;
    }

    public void setFootetViewVisiable(boolean flag) {
        if (footerView != null) {
            footerView.setVisibility(flag ? View.VISIBLE : View.GONE);
        }
    }
}
