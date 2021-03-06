package com.massoftware.frontend.ui.util;

import org.cendra.commons.util.dao.ex.InsertDuplicateException;

import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

public class LogAndNotification {

	public static void print(Exception e) {
		Notification notification = new Notification(
				"Error Interno del Sistema", e.toString(), Type.ERROR_MESSAGE);
		notification.setStyleName("error bar small closable");
		notification.setPosition(Position.BOTTOM_LEFT);
		Integer delayMsec = -1;
		notification.setDelayMsec(delayMsec);
		
		notification.show(Page.getCurrent());

		e.printStackTrace();
	}

	public static void print(InvalidValueException e) {
		Notification notification = new Notification("Campo inválido",
				e.getMessage(), Type.WARNING_MESSAGE);
//		notification.setStyleName("warning failure");
		notification.setStyleName("tray failure");
		notification.setPosition(Position.BOTTOM_LEFT);
		// notification.setDelayMsec(10000);
		notification.show(Page.getCurrent());

		e.printStackTrace();
	}
	
	public static void print(InsertDuplicateException e) {
		Notification notification = new Notification("Duplicación de Datos",
				e.getMessage(), Type.WARNING_MESSAGE);
//		notification.setStyleName("warning failure");
		notification.setStyleName("tray failure");
		notification.setPosition(Position.BOTTOM_LEFT);
		// notification.setDelayMsec(10000);
		notification.show(Page.getCurrent());

		e.printStackTrace();
	}

	public static void printSuccessOk(String msg) {
		Notification notification = new Notification("Ok", msg,
				Type.HUMANIZED_MESSAGE);
//		notification.setStyleName("humanized success");
		notification.setStyleName("tray success");
		notification.setPosition(Position.BOTTOM_LEFT);
		// notification.setDelayMsec(10000);
		notification.show(Page.getCurrent());
	}

}
