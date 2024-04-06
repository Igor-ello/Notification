package com.example.notification;



import androidx.annotation.NonNull;

import com.example.notification.utils.NotificationUtil;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessageReceiver extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

        if(message.getNotification() != null){
            NotificationUtil.getInstance().showNotification(getApplicationContext(),
                    message.getNotification().getTitle(),
                    message.getNotification().getBody());
        }
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
    }
}
