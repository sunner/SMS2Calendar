package cn.sunner.sms2calendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    public void addOneNotification(View view){
        SMSParser parser = new N12306Parser("订单EC11541789,孙先生您已购1月24日D2245次14车13F号南京南19:11开。【铁路客服】");
        Notification.add(this, parser.getEvents().get(0));
    }

    public void addMultiNotification(View view){
        SMSParser parser = new N106980000666Parser(
                "(1/2) 机票已出：订单1708724174已出票。CHEN/MEIREN，票号781-1234567890；SUN/ZHIGANG，票号781-1234567890『（1）东方航空 MU779 上海浦东T1-奥克兰I 4月29日0:05-4月29日17:15（2）新西兰航空 NZ549 奥克兰-基督城克赖斯特彻奇 4月29日19:10-4月29日20:35（3）新西兰航空 NZ532 基督城克赖斯特彻奇-奥克兰 5月8日16:45-5月8日18:05（4）东方航空 MU780 奥克兰I-上海浦东T1 5月8日21:00-5月9日5:30』建议您提前3小时到达机场办理值机。温馨提示：航班4月29日凌晨00:05起飞，请留意起飞日期，以免误机。机票按顺序使用。【携程网】"
        );
        List<Event> events = parser.getEvents();
        for (Event event : events) Notification.add(this, event);
    }
}
