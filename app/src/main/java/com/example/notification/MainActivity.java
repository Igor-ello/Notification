package com.example.notification;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.Operation;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notification.utils.BaseBuilder;
import com.example.notification.utils.NotificationUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotificationUtil.getInstance().createNotificationChannel(this);

        NotificationUtil notificationUtil = NotificationUtil.getInstance();
        Log.d("NOTNOT", String.valueOf(notificationUtil.increaseA()));

        BaseBuilder baseBuilder = new BaseBuilder.Builder()
                .setA(1)
                .setB(2)
                .setD("13")
                .build();

        askNotificationPermission();

        findViewById(R.id.buttonPush).setOnClickListener(v -> {
            //NotificationUtil.getInstance().showNotification(getApplicationContext(), "MY_NOTIFICATION", "Hello world");

            //PeriodicWorkRequest
//            PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(MyWorker.class, 1, TimeUnit.SECONDS).build();
//            WorkManager workManager = WorkManager.getInstance(getApplicationContext());
//            WorkManager.getInstance(getApplicationContext());
//            Operation enqueue = workManager.enqueueUniquePeriodicWork("NOTNOT", ExistingPeriodicWorkPolicy.REPLACE, workRequest);

            //TODO OneTimeWorkRequest
            OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWorker.class).build();
            WorkManager workManager = WorkManager.getInstance(getApplicationContext());
            workManager.enqueue(workRequest);

            EditText edTitle = findViewById(R.id.requestTitle),
                    edBody = findViewById(R.id.requestBody),
                    edTime = findViewById(R.id.requestTime);


        });

    }

    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "BAD", Toast.LENGTH_SHORT).show();
                }
            });



    private void askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
        } else {
            ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS);
        }
    }

}