package com.massoftware.frontend.ui.util;

import org.cendra.commons.util.service.ResponseEntity;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

public class NotificationPrinter {

	public void print(ResponseEntity responseEntity) {

		Notification notification = null;

		switch (responseEntity.getStatusCode()) {
		case OK:
			notification = new Notification("Info", responseEntity.getMsg(),
					Type.HUMANIZED_MESSAGE);
//			notification.setStyleName("closable small");
			notification.setPosition(Position.BOTTOM_LEFT);
			notification.setDelayMsec(10000);
			notification.show(Page.getCurrent());			
			break;
		case CREATED:
			// Notification notification = new Notification("Error",
			// responseEntity.getMsg(), Type.ERROR_MESSAGE);
			// // notification.setPosition(Position.BOTTOM_LEFT);
			// notification.show(Page.getCurrent());
			break;
		case NO_CONTENT:
			notification = new Notification("Info", responseEntity.getMsg(),
					Type.HUMANIZED_MESSAGE);
//			notification.setStyleName("closable small");
			notification.setPosition(Position.BOTTOM_LEFT);
			notification.setDelayMsec(10000);
			notification.show(Page.getCurrent());			
			break;
		case BAD_REQUEST:
		case UNAUTHORIZED:
		case FORBIDDEN:
		case NOT_FOUND:
		case METHOD_NOT_ALLOWED:
		case CONFLICT:
		case GONE:
		case UNSUPPORTED_MEDIA_TYPE:
		case UNPROCESSABLE_ENTITY:
		case INTERNAL_SERVER_ERROR:
			notification = new Notification("Error", responseEntity.getMsg(),
					Type.ERROR_MESSAGE);
			notification.show(Page.getCurrent());
			break;
		case NOT_IMPLEMENTED:
		case SERVICE_UNAVAILABLE:

		}

		// if (responseEntity.hasBodyIncidents()) {
		//
		// } else if (responseEntity.hasBodyServiceError()) {
		//
		// Notification notification = new Notification("Error",
		// responseEntity.getMsg(), Type.ERROR_MESSAGE);
		// // notification.setPosition(Position.BOTTOM_LEFT);
		// notification.show(Page.getCurrent());
		//
		// } else if (responseEntity.hasBodyServiceException()) {
		// Notification notification = new Notification("Error",
		// responseEntity.getMsg(), Type.ERROR_MESSAGE);
		// // notification.setPosition(Position.BOTTOM_LEFT);
		// notification.show(Page.getCurrent());
		// } else {
		// Notification notification = new Notification("Error",
		// responseEntity.getMsg(), Type.ERROR_MESSAGE);
		// // notification.setPosition(Position.BOTTOM_LEFT);
		// notification.show(Page.getCurrent());
		// }

	}

}
