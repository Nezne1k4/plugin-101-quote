package com.plugin101.quote;

import com.intellij.notification.*;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.util.NotNullLazyValue;
import org.jetbrains.annotations.NotNull;

public class Quote101ComponentImpl implements Quote101ProjectComponent {
    @Override
    public void initComponent() {
        final NotNullLazyValue<NotificationGroup> NOTIFICATION_GROUP = new NotNullLazyValue<NotificationGroup>() {
            @NotNull
            @Override
            protected NotificationGroup compute() {
                return new NotificationGroup(
                        "Motivational message",
                        NotificationDisplayType.STICKY_BALLOON,//NotificationDisplayType.BALLOON,
                        true);
            }
        };

        ApplicationManager.getApplication().invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        Notification notification = NOTIFICATION_GROUP.getValue().createNotification(
                                "Quote of the day",
                                "Chưa unit test xong chưa đi ngủ,\nchưa refactor xong chưa đi chơi.\n(ntttin@quoine)",
                                NotificationType.INFORMATION,
                                new NotificationListener.UrlOpeningListener(true));
                        notification.addAction(new NotificationAction("Author") {
                            @Override
                            public void actionPerformed(@NotNull AnActionEvent anActionEvent, @NotNull Notification notification) {

                            }
                        });
                        notification.addAction(new NotificationAction("Another") {
                            @Override
                            public void actionPerformed(@NotNull AnActionEvent anActionEvent, @NotNull Notification notification) {

                            }
                        });
                        Notifications.Bus.notify(notification);
                    }
                }, ModalityState.NON_MODAL);
    }
}
