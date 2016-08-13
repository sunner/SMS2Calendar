package cn.sunner.sms2calendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
}
