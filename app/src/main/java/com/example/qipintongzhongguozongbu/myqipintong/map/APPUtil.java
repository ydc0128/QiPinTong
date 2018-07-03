package com.example.qipintongzhongguozongbu.myqipintong.map;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * function   : 地图导航获取用户导航软件工具类
 * Author     : 感觉自己懵懵哒
 * E-mail     : anber1229423614@163.com
 * Date       : 2017/4/19  下午4:36
 */
public class APPUtil {

    public static String[] paks = new String[]{"com.baidu.BaiduMap",
            "com.autonavi.minimap"};

    //######################################
    //通过URI API接口启调地图工具
    //######################################

    public static void startNative_Baidu(Context context, Location loc) {
        if (loc == null) {
            return;
        }

        if (loc.getAddress() == null || "".equals(loc.getAddress())) {
            loc.setAddress("目的地");
        }
        try {

            //起点的经纬度不设置 以用户当前位置为准
            Intent intent = Intent.getIntent("intent://map/direction?" + "destination=latlng:" + loc.getStringLatLng() + "|name:" + loc.getAddress() + "|name:" + loc.getAddress() +        //终点
                    "&mode=driving&" +          //导航路线方式
                    "region=重庆" +           //region 城市信息
                    "&src=宁静村#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
            //Intent intent = Intent.getIntent("intent://map/direction?origin=latlng:"+loc1.getStringLatLng()+"|name:"+loc1.getAddress()+"&destination=latlng:"+loc2.getStringLatLng()+"|name:"+loc2.getAddress()+"&mode=driving&src=重庆快易科技|CC房车-车主#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "地址解析错误", Toast.LENGTH_SHORT).show();
        }
    }

    public static void startNative_Gaode(Context context, Location loc) {
        if (loc == null) {
            return;
        }
        if (loc.getAddress() == null || "".equals(loc.getAddress())) {
            loc.setAddress("目的地");
        }
        try {
            Intent intent = new Intent("android.intent.action.VIEW",
                    android.net.Uri.parse("androidamap://navi?sourceApplication=CC房车-车主&poiname=重庆快易科技&lat=" + loc.getLat() + "&lon=" + loc.getLng() + "&dev=1&style=2"));
            intent.setPackage("com.autonavi.minimap");
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "地址解析错误", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 检查手机上是否安装了指定的软件
     *
     * @param context
     * @param packageName：应用包名
     * @return
     */
    public static boolean isAvilible(Context context, String packageName) {
        // 获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        // 获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);

        packageManager.getInstalledApplications(packageManager.GET_META_DATA);
        // 用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        // 从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        // 判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }

    /**
     * 通过包名获取应用信息
     *
     * @param context
     * @param packageName
     * @return
     */
    public static AppInfo getAppInfoByPak(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        for (PackageInfo packageInfo : packageInfos) {
            if (packageName.equals(packageInfo.packageName)) {
                AppInfo tmpInfo = new AppInfo();
                tmpInfo.setAppName(packageInfo.applicationInfo.loadLabel(packageManager).toString());
                tmpInfo.setPackageName(packageInfo.packageName);
                tmpInfo.setVersionName(packageInfo.versionName);
                tmpInfo.setVersionCode(packageInfo.versionCode);
                tmpInfo.setAppIcon(packageInfo.applicationInfo.loadIcon(packageManager));
                return tmpInfo;
            }
        }
        return null;
    }

    /**
     * 返回当前设备上的地图应用集合
     *
     * @param context
     * @return
     */
    public static List<AppInfo> getMapApps(Context context) {
        LinkedList<AppInfo> apps = new LinkedList<AppInfo>();

        for (String pak : paks) {
            AppInfo appinfo = getAppInfoByPak(context, pak);
            if (appinfo != null) {
                apps.add(appinfo);
            }
        }
        return apps;
    }

}
