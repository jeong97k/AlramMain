package com.example.jkh.myapplication;

        import android.app.Activity;
        import android.app.AlarmManager;
        import android.app.Notification;
        import android.app.NotificationManager;
        import android.app.PendingIntent;
        import android.content.Context;
        import android.content.Intent;
        import android.net.Uri;
        import android.os.Vibrator;
        import android.provider.MediaStore;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.CalendarView;
        import android.widget.CheckBox;
        import android.widget.DatePicker;
        import android.widget.TextView;
        import android.widget.TimePicker;

        import java.util.Calendar;
        import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements DatePicker.OnDateChangedListener, TimePicker.OnTimeChangedListener {


    private AlarmManager _am;
    private DatePicker mDate;
    private CheckBox cb1, cb2, cb3, cb4, cb5, cb6, cb7;
    Button btn6;
    Button btn3, btn2, btn7;
    private GregorianCalendar mCalendar;
    private Button btn4;
       private TextView tvmonth, tvday;
    private TimePicker timep;
    private static int ONE_MINUTE = 5626;




    private NotificationManager mNotification;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            setTitle("시간예약");
            mNotification = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);



            _am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            mCalendar = new GregorianCalendar();
            Log.i("HelloAlarmActivity",mCalendar.getTime().toString());
            Log.i("HelloAlarmActivity",mCalendar.getTime().toString());
            setContentView(R.layout.activity_main);
            btn7 = (Button)findViewById(R.id.button7);
            btn7.setOnClickListener (new View.OnClickListener() {
                    public void onClick (View v){
                        setAlarm();

                    }

            });
            btn2 = (Button)findViewById(R.id.button2);
            btn2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    resetAlarm();
                }
            });


            mDate = (DatePicker)findViewById(R.id.date_picker);
            mDate.init (mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH), this);
            timep = (TimePicker)findViewById(R.id.timePicker3);
            timep.setCurrentHour(mCalendar.get(Calendar.HOUR_OF_DAY));
            timep.setCurrentMinute(mCalendar.get(Calendar.MINUTE));
            timep.setOnTimeChangedListener(this);
        }

    private void setAlarm() {
        _am.set(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pendingIntent());
        Log.i("HelloAlarmActivity", mCalendar.getTime().toString());
    }

    private void resetAlarm() {
        _am.cancel(pendingIntent());
    }

    private PendingIntent pendingIntent() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);

        int icon = R.drawable.icon;
        String tickerText = "알림";
        long when = System.currentTimeMillis();

        Notification noti = new Notification(icon, tickerText, when);
        noti.sound = Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "6");//ringURI;
        Log.v("LEE", "mNotification11");

        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.icon)
                .setContentTitle("111")
                .setTicker("222");

        mNotification.notify(1234, noti); // NOTIFICATION_ID의 고유 ID를 가지는 notification을 표시합니다.
        Log.v("LEE", "mNotification22");

        return pi;
    }

    public void onDateChanged (DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        mCalendar.set (year, monthOfYear, dayOfMonth, timep.getCurrentHour(), timep.getCurrentMinute());
        Log.i("HelloAlarmActivity", mCalendar.getTime().toString());
    }

    public void onTimeChanged (TimePicker view, int hourOfDay, int minute) {
        mCalendar.set (mDate.getYear(), mDate.getMonth(), mDate.getDayOfMonth(), hourOfDay, minute);
        Log.i("HelloAlarmActivity",mCalendar.getTime().toString());
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}


