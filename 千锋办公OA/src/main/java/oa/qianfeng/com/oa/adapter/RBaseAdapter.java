package oa.qianfeng.com.oa.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/3 0003.
 */
public abstract class RBaseAdapter<T> extends RecyclerView.Adapter<RBaseAdapter.RViewHolder> {

    int[] layoutIds;
    List<T> data;
    LayoutInflater inflater;


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
     * 设置数据源
     *
     * @param list
     */
    public void setDatas(List<T> list) {
        this.data.clear();
        this.data.addAll(list);
        notifyDataSetChanged();
    }

    public void addData(T t) {
        this.data.add(t);
        notifyDataSetChanged();
    }

    public void addData(List<T> list) {
        this.data.addAll(list);
        notifyDataSetChanged();
    }

    public T getData(int position) {
        return this.data.get(position);
    }

    @Override
    public RViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(layoutIds[viewType], parent, false);
        return new RViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RViewHolder holder, int position) {
        bindData(holder, position);
    }

    public abstract void bindData(RViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return data.size();
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
}
