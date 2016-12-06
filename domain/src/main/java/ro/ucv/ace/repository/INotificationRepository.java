package ro.ucv.ace.repository;

import ro.ucv.ace.model.Notification;

/**
 * Created by Geo on 06.12.2016.
 */
public interface INotificationRepository {

    Notification save(Notification notification);
}
