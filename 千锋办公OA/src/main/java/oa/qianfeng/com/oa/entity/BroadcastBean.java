package oa.qianfeng.com.oa.entity;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import oa.qianfeng.com.oa.utils.API;
import oa.qianfeng.com.oa.utils.Constant;
import oa.qianfeng.com.oa.utils.L;

/**
 * Created by Administrator on 2016/12/9.
 */
public class BroadcastBean {


    public String title;

    public String subTitle = "";

    private List<ContentEntity> data = new ArrayList<>();

    public BroadcastBean(Element e) {
        Elements es = e.getElementsByClass("form-group");
        title = e.getElementsByClass("page-header").first().text();
        if (es.size() == 2) {
            //标题头部
            Elements divs = es.get(0).select("div").first().select("div");
            for (Element m : divs) {
                subTitle = subTitle + " " + m.text() + " ";
            }
            L.d(subTitle);
            //公告内容
            Elements imgs = es.get(1).select("img");
            for (Element m : imgs) {
                ContentEntity entity = new ContentEntity();
                entity.type = Constant.TYPE_IMG;
                //多一条/
                entity.value = API.URL_BASE.substring(0, API.URL_BASE.length() - 1) + m.attr("src");
                data.add(entity);
            }
        }
    }


    public String getSubTitle() {
        return subTitle;
    }

    public List<ContentEntity> getContent() {
        return data;
    }

    public static class ContentEntity {
        public int type;
        public String value;
    }

}
