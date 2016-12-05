package oa.qianfeng.com.oa.entity;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import oa.qianfeng.com.oa.utils.L;

/**
 * Created by Administrator on 2016/12/5.
 */
public class MineBean {

    public String name;
    public String id;
    public String email;
    public String tel;
    public String boss;
    public String qq;

    public List<BossBean> bossList = new ArrayList<>();

    public MineBean(Element e) {
        L.d(e.text());
        Elements es = e.select("input");
        //基本信息
        for (Element tmp : es) {
            String attrName = tmp.attr("name");
            if (attrName.equals("RealName")) {
                name = tmp.attr("value");
                L.d("我的名字：" + name);
            }
            if (attrName.equals("Email")) {
                email = tmp.attr("value");
                L.d("我的邮箱：" + email);
            }
            if (attrName.equals("QQ")) {
                qq = tmp.attr("value");
                L.d("我的QQ：" + qq);
            }
            if (attrName.equals("Tel")) {
                tel = tmp.attr("value");
                L.d("我的Tel：" + tel);
            }
            if (attrName.equals("Tel")) {
                tel = tmp.attr("value");
                L.d("我的Tel：" + tel);
            }
        }
        //直属领导
        Element boss = e.getElementsByClass("selectpicker").first();
        Elements bosses = boss.select("option");
        bossList.clear();
        for (Element b : bosses) {
            String value = b.attr("value");
            L.d("BossBean b" + b.text());
            if (value != null && value.length() > 0) {
                L.d("BossBean add new BossBean" + b.text());
                BossBean bossBean = new BossBean(b);
                if (bossBean.isSelected) {
                    this.boss = bossBean.name;
                }
                bossList.add(bossBean);
            }
        }
        L.d("直属领导：" + this.boss);
    }

    @Override
    public String toString() {
        return "姓名：" + name + "\n\n" +
                "企业邮箱：" + email + "\n\n" +
                "手机：" + tel + "\n\n" +
                "QQ：" + qq + "\n\n" +
                "直属领导：" + boss + "\n\n";
    }
}
