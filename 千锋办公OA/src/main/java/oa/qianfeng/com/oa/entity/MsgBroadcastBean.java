package oa.qianfeng.com.oa.entity;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * <th>编号</th>
 * <th>标题</th>
 * <th>发布人</th>
 * <th>发布时间</th>
 * <th>类型</th>
 * <th>阅读量</th>
 * <th>状态</th>
 * href
 */
public class MsgBroadcastBean {
    public String codeNum;
    public String title;
    public String publisher;
    public String publishTime;
    public String type;
    public String readNum;
    public String state;
    public String href;

    public MsgBroadcastBean(Element e) {
        Elements tds = e.select("td");
        if (tds.size() == 7) {
            codeNum = tds.get(0).text();
            title = tds.get(1).text();
            publisher = tds.get(2).text();
            publishTime = tds.get(3).text();
            type = tds.get(4).text();
            readNum = tds.get(5).text();
            state = tds.get(6).text();
        }
    }

    @Override
    public String toString() {
        return title + "\n" + publishTime;
    }
}
