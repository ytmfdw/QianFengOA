package oa.qianfeng.com.oa.entity;

import org.jsoup.nodes.Element;

import oa.qianfeng.com.oa.utils.L;

/**
 * Created by Administrator on 2016/12/5.
 */
public class BossBean {
    public String name;
    public String value;
    public boolean isSelected;

    public BossBean(Element e) {
        name = e.text();
        value = e.attr("value");
        isSelected = e.hasAttr("selected");

        L.d(toString());
    }

    @Override
    public String toString() {
        return "BossBean{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }
}
