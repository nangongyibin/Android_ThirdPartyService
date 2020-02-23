package com.ngyb.amap;

import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/2/23 12:12
 */
public class LocationActivity extends AppCompatActivity {
    private static final String TAG = "LocationActivity";
    private AMapLocationClient client;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        setTitle("定位");
    }

    public void location(View view) {
        client = new AMapLocationClient(getApplicationContext());
        client.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    Log.e(TAG, "onLocationChanged: " + aMapLocation.getErrorCode() + "========" + aMapLocation.getErrorInfo());
                    if (aMapLocation.getErrorCode() == 0) {
                        //获取当前定位结果来源
                        int locationType = aMapLocation.getLocationType();
                        Log.e(TAG, "onLocationChanged: " + locationType);
                        //获取维度
                        double latitude = aMapLocation.getLatitude();
                        Log.e(TAG, "onLocationChanged: " + latitude);
                        //获取经度
                        double longitude = aMapLocation.getLongitude();
                        Log.e(TAG, "onLocationChanged: " + longitude);
                        //获取精度信息
                        float accuracy = aMapLocation.getAccuracy();
                        Log.e(TAG, "onLocationChanged: " + accuracy);
                        //地址
                        String address = aMapLocation.getAddress();
                        Log.e(TAG, "onLocationChanged: " + address);
                        //国家信息
                        String country = aMapLocation.getCountry();
                        Log.e(TAG, "onLocationChanged: " + country);
                        //省信息
                        String provider = aMapLocation.getProvider();
                        Log.e(TAG, "onLocationChanged: " + provider);
                        //城市信息
                        String city = aMapLocation.getCity();
                        Log.e(TAG, "onLocationChanged: " + city);
                        //城区信息
                        String district = aMapLocation.getDistrict();
                        Log.e(TAG, "onLocationChanged: " + district);
                        //街道信息
                        String street = aMapLocation.getStreet();
                        Log.e(TAG, "onLocationChanged: " + street);
                        //街道门牌号信息
                        String streetNum = aMapLocation.getStreetNum();
                        Log.e(TAG, "onLocationChanged: " + street);
                        //城市编码
                        String cityCode = aMapLocation.getCityCode();
                        Log.e(TAG, "onLocationChanged: " + cityCode);
                        //地区编码
                        String adCode = aMapLocation.getAdCode();
                        Log.e(TAG, "onLocationChanged: " + adCode);
                        //获取当前定位点的AOI信息
                        String aoiName = aMapLocation.getAoiName();
                        Log.e(TAG, "onLocationChanged: " + aoiName);
                        //获取当前室内定位的建筑物Id
                        String buildingId = aMapLocation.getBuildingId();
                        Log.e(TAG, "onLocationChanged: " + buildingId);
                        //获取当前室内定位的楼层
                        String floor = aMapLocation.getFloor();
                        Log.e(TAG, "onLocationChanged: " + floor);
                        //获取GPS的当前状态
                        int gpsAccuracyStatus = aMapLocation.getGpsAccuracyStatus();
                        Log.e(TAG, "onLocationChanged: " + gpsAccuracyStatus);
                        //获取定位时间
                        long time = aMapLocation.getTime();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date(time);
                        String format = simpleDateFormat.format(date);
                        Log.e(TAG, "onLocationChanged: " + format);
                        client.stopLocation();
                    }
                }
            }
        });
        AMapLocationClientOption option = new AMapLocationClientOption();
        //设置定位模式
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //关闭缓存机制
        option.setLocationCacheEnable(false);
        //设置定位间隔
        option.setInterval(2000);
        //设置单次定位
        option.setOnceLocation(false);
        //true,将最近3s内精度最高的一次定位结果
        option.setOnceLocationLatest(true);
        //设置是否返回地址信息
        option.setNeedAddress(true);
        //设置是否允许模拟位置
        option.setMockEnable(false);
        //true,驱动设备扫描周边wifi,获取最新的wifi列表
        option.setWifiActiveScan(false);
        //设置定位请求超时时间
        option.setHttpTimeOut(30000);
        //设定网络定位时所采用的的协议
        option.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);
        client.setLocationOption(option);
        client.startLocation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        client.onDestroy();
    }
}
