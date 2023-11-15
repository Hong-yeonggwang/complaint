package com.yongin.complaint.Component.fireBase;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.yongin.complaint.DTO.Firebase.FirebaseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FireBasePush {
    private final FirebaseMessaging firebaseMessaging;

    @Autowired
    public FireBasePush(FirebaseMessaging firebaseMessaging){
        this.firebaseMessaging = firebaseMessaging;
    }

    public String sendPush(FirebaseDTO firebaseDTO){
        Notification notification = Notification.builder()
                .setTitle(firebaseDTO.getTitle())
                .setBody(firebaseDTO.getBody())
                .build();
        Message message = Message.builder()
                .setToken("fPiqmDyBKHzCEbn1xTQ09L:APA91bGr-GrMVmjDzx-YD2NyP-bIp_LIePLywZ0f1w46ArdVm-COqQc-9Jg1EasbHjV1L8aNZrK7l7N_Yx7IsJLWMqUsVcZLdL4PlytRXjEECWxF8D-CL-Ttn-l5gxtD6_y7Eer0ru5j")
                .setNotification(notification)
                .build();
        try{
            firebaseMessaging.send(message);
            return "성공다제~";
        }catch (Exception e){
            return "실패다제ㅜ";
        }
    }
}
