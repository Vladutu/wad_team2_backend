package ro.ucv.ace.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ro.ucv.ace.model.Notification;
import ro.ucv.ace.repository.IJpaRepository;
import ro.ucv.ace.repository.INotificationRepository;

/**
 * Created by Geo on 06.12.2016.
 */
@Repository("notificationRepository")
public class NotificationRepository implements INotificationRepository {

    @Autowired
    private IJpaRepository<Notification, Integer> innerNotificationRepository;

    @Override
    public Notification save(Notification notification) {
        return innerNotificationRepository.save(notification);
    }
}
