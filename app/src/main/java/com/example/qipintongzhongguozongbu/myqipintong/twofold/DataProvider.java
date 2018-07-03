package com.example.qipintongzhongguozongbu.myqipintong.twofold;

import java.util.HashMap;
import java.util.Map;

public class DataProvider {

    public static final String[] summaries = {
            "销售|客服|市场",
            "财务|人力资源|行政",
            "项目|质量|高级管理",
            "IT|互联网|通讯",
            "房产|建筑|物业管理",
            "生产|制造",
            "金融",
            "采购|贸易|交通|物流",
            "传媒|印刷艺术|设计",
			"咨询|法律|教育|翻译",
            "服务业",
            "能源|环保|农业|科研"

    };

    public static final Map<String, String[]> details = new HashMap<>();

    static {
        details.put("销售|客服|市场", new String[]{"销售业务", "销售管理", "销售行政|商务","服务|售前|售后服务|技术支持","市场","公关|媒介","广告|会展"});
        details.put("财务|人力资源|行政", new String[]{"财务|审计|税务", "人力资源", "行政|后勤|文秘"});
        details.put("项目|质量|高级管理", new String[]{"项目管理|项目协调", "质量管理|安全防护", "高级管理"});
        details.put("IT|互联网|通讯", new String[]{"软件|互联网|系统", "硬件开发", "互联网产品|运营管理","IT质量管理|测试|配置","IT运维|技术支持","IT管理|项目协调","电信|通讯技术|"});
        details.put("房产|建筑|物业管理", new String[]{"房地产开发|经济|中介", "土木|建筑|装修|市政", "物业管理"});
        details.put("生产|制造", new String[]{"生产管理|运营", "电子|电器|半导体|仪器", "汽车制造","汽车销售|服务","机械设计|制造|维修","服装|纺织|皮革设计","技工|操作工","生物|制药|医疗器械","化工"});
        details.put("金融", new String[]{"银行", "证券|期货|投资管理", "保险","信托|担保|拍卖|典当"});
        details.put("采购|贸易|交通|物流", new String[]{"交通运输服务", "物流|仓储", "采购|贸易"});
        details.put("传媒|印刷艺术|设计", new String[]{"影视|媒体|出版|印刷", "艺术|设计"});
        details.put("咨询|法律|教育|翻译", new String[]{"咨询|顾问|调研|数据", "教育|培训", "律师|法务|合规","翻译（口译与笔译）"});
        details.put("服务业", new String[]{"商超|酒店|娱乐管理", "旅游|度假|出入境服务", "烹饪|美容|美发|健身","医院|医疗|护理","社区|居民|家政服务"});
        details.put("能源|环保|农业|科研", new String[]{"能源|矿产|地质勘查", "环境科学|环保", "农|林|牧|渔业","公务员|事业单位","科研单位"});
    }

}
