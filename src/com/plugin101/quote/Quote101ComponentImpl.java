package com.plugin101.quote;

import com.intellij.notification.*;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.util.NotNullLazyValue;
import org.jetbrains.annotations.NotNull;

public class Quote101ComponentImpl implements Quote101Component {
    @Override
    public void initComponent() {

        ApplicationManager.getApplication().invokeLater(
                () -> Notifications.Bus.notify(createNotificationQuote(new NotificationAction("Who said so?") {
                    @Override
                    public void actionPerformed(@NotNull AnActionEvent anActionEvent, @NotNull Notification notification) {
                        // new notification
                        Notifications.Bus.notify(createNotificationAuthor());
                        // update current notification
                        //Notifications.Bus.notify(notification.setContent("said ntttin @ quoine"));

                    }
                })), ModalityState.defaultModalityState());
    }

    private Notification createNotificationAuthor() {
        Notification notification = createNotificationGroup("Author message", NotificationDisplayType.BALLOON)
                .getValue().createNotification(
                        "Said", // null will NPE
                        "ntttin @ quoine",
                        NotificationType.INFORMATION, null);
        return notification;
    }

    private Notification createNotificationQuote(NotificationAction action1) {
        Notification notification = createNotificationGroup("Quote message", NotificationDisplayType.STICKY_BALLOON)
                .getValue().createNotification(
                        "Quote of the day",
                        "Chưa unit test xong chưa đi ngủ, chưa refactor xong chưa đi chơi.",
                        NotificationType.INFORMATION, null);
        notification.addAction(action1);
        return notification;
    }

    private NotNullLazyValue<NotificationGroup> createNotificationGroup(String displayId, NotificationDisplayType notificationDisplayType) {
        return new NotNullLazyValue<NotificationGroup>() {
            @NotNull
            @Override
            protected NotificationGroup compute() {
                return new NotificationGroup(displayId, notificationDisplayType, true);
            }
        };
    }
}
