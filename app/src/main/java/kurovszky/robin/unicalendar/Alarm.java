package kurovszky.robin.unicalendar;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.widget.TextView;
import android.widget.Toast;

import kurovszky.robin.unicalendar.model.Requirement;

import static android.content.Context.NOTIFICATION_SERVICE;

public class Alarm extends BroadcastReceiver 
{
    Requirement requirement;
    @Override
    public void onReceive(Context context, Intent intent) 
    {   
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        wl.acquire();
        String subjectName = intent.getStringExtra("SubjectName");
        String type = intent.getStringExtra("Type");
        String date = intent.getStringExtra("Date");
        String hardiness = intent.getStringExtra("Hardiness");
        // TODO notification

        String sound = PreferenceManager.getDefaultSharedPreferences(context).getString("notification_sound", "DEFAULT_SOUND");

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.icon)
                        .setContentTitle(context.getString(R.string.notificationTitle))
                        .setContentText(type + context.getString(R.string.from) + subjectName + context.getString(R.string.at) + date + context.getString(R.string.its) + hardiness).setAutoCancel(true);
        if(sound.equals("DEFAULT_SOUND"))
            ;
        else {
            mBuilder.setSound(Uri.parse(sound));
        }
        Intent resultIntent = new Intent(context, SubjectActivity.class);
        resultIntent.putExtra("SubjectName", subjectName);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        // Sets an ID for the notification
        int mNotificationId = 001;
        // Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        // Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
        wl.release();
    }

    public void setAlarm(Context context, long timeInMilisec, Requirement requirement)
    {
        this.requirement = requirement;
        AlarmManager am =( AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, Alarm.class);
        i.putExtra("SubjectName", requirement.getSubject());
        i.putExtra("Type", requirement.getType());
        i.putExtra("Date", requirement.getTime());
        String hardinessString;
        int hardiness = requirement.getHardiness();
        switch (hardiness){
            case 0:
                hardinessString = context.getString(R.string.veryeasy);
                break;
            case 1:
                hardinessString = context.getString(R.string.easy);
                break;
            case 2:
                hardinessString = context.getString(R.string.moderate);
                break;
            case 3:
                hardinessString = context.getString(R.string.hard);
                break;
            case 4:
                hardinessString = context.getString(R.string.veryhard);
                break;
            default:
                hardinessString = context.getString(R.string.veryhard);
                break;
        }
        i.putExtra("Hardiness", hardinessString);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);

        am.set(AlarmManager.RTC_WAKEUP,timeInMilisec,pi);
    }

    public void cancelAlarm(Context context)
    {
        Intent intent = new Intent(context, Alarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }
}