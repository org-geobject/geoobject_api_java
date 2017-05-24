package com.massoftware.frontend;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.annotation.WebServlet;

import com.massoftware.backend.service.BackendContext;
import com.massoftware.frontend.ui.ejerciciocontable.EjercicioContableTableUi;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of a html page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
public class MasSoftware extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2467123584653780193L;

	private static final Logger LOGGER = Logger.getLogger(MasSoftware.class
			.getName());

	@Override
	protected void init(VaadinRequest vaadinRequest) {

		try {
			BackendContext cx = new BackendContext();

			final VerticalLayout layout = new VerticalLayout();

			@SuppressWarnings("serial")
			Button show = new Button("Open Window", new ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {

					EjercicioContableTableUi ejerciciosContablesDesing = new EjercicioContableTableUi(
							cx.buildEjercicioContableBO());

					Window win = new Window("Ejercicios contables");
					// win.setWidth((float)(ejerciciosContablesDesing.rootVH.getWidth()
					// * 1.2), ejerciciosContablesDesing.grid.getHeightUnits());
					win.setWidth("660px");
					win.setClosable(true);
					win.setResizable(false);
					win.setContent(ejerciciosContablesDesing);
					// win.addCloseShortcut(KeyCode.ESCAPE, null);
					getUI().addWindow(win);

					win.center();
					win.focus();

					// event.getButton().setEnabled(false);
				}
			});
			show.addStyleName(ValoTheme.BUTTON_PRIMARY);
			layout.addComponent(show);

			setContent(layout);

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.log(Level.SEVERE, e.toString());
		}
	}

	@WebServlet(urlPatterns = "/*", name = "MasSoftwareServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MasSoftware.class, productionMode = false)
	public static class MasSoftwareServlet extends VaadinServlet {

		/**
		 * 
		 */
		private static final long serialVersionUID = 3384068185250400739L;
	}
}
