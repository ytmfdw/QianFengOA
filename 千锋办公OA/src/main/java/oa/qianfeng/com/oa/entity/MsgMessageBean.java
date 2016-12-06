package oa.qianfeng.com.oa.entity;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * <th>编号</th>
 * <th>内容</th>
 * <th>操作时间</th>
 */
public class MsgMessageBean {

    public String codeNum;
    public String content;
    public String time;

    public MsgMessageBean(Element e) {
        Elements tds = e.select("td");
        if (tds.size() == 3) {
            codeNum = tds.get(0).text();
            content = tds.get(1).text();
            time = tds.get(2).text();
        }

    }

    @Override
    public String toString() {
        return content + "\n" + time;
    }
}
