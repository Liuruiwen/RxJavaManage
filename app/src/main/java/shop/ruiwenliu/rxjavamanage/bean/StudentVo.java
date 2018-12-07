package shop.ruiwenliu.rxjavamanage.bean;

import java.util.List;

import javax.xml.transform.Source;

/**
 * Created by Amuse
 * Data:2018/12/6 0006
 * Desc:学生案列
 */

public class StudentVo {
    public String name;//学生名字
    public List<Source> list;
    public static class Source {
        public String sourceScore;//分数
        public String sourceName;//科目名称
        public String studentName;
           }
}
