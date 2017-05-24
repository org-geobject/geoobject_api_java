package com.massoftware.frontend.ui.ejerciciocontable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.cendra.commons.util.dao.ex.InsertDuplicateException;
import org.cendra.commons.util.error.LogPrinter;

import com.massoftware.backend.bo.ejerciciocontable.IEjercicioContableBO;
import com.massoftware.frontend.ui.util.LogAndNotification;
import com.massoftware.frontend.ui.util.YesNoDialog;
import com.massoftware.model.ejerciciocontable.EjercicioContable;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.converter.StringToBooleanConverter;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.HeaderCell;
import com.vaadin.ui.Grid.HeaderRow;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.renderers.DateRenderer;
import com.vaadin.ui.renderers.HtmlRenderer;
import com.vaadin.ui.themes.ValoTheme;

public class EjercicioContableTableUi extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 392772794314133877L;

	protected VerticalLayout rootVH;
	protected HorizontalLayout toolbarHL;
	protected Grid grid;
	protected TextField filterText;
	protected Button clearFilterTextBtn;
	protected HorizontalLayout footerHL;
	protected Button agregarBtn;
	protected Button cambiarBtn;
	protected Button eliminarBtn;
	protected Button elegirEjercicioBtn;

	protected String filterTextCaption = "ejercicio...";
	protected String clearFilterTextBtnCaption = "Quitar los filtros aplicados a la búsqueda";
	protected String joinedCel2Caption = "Control de cierre";
	protected String ejercicioCaption = "Ejercicio";
	protected String fechaAperturaCaption = "Apertura";
	protected String fechaCierreCaption = "Cierre";
	protected String ejercicioCerradoCaption = "Ejercicio";
	protected String ejercicioCerradoModulosCaption = "Módulos";
	protected String agregarBtnCaption = "Agregar";
	protected String cambiarBtnCaption = "Cambiar";
	protected String eliminarBtnCaption = "Eliminar";
	protected String elegirEjercicioBtnCaption = "Elegir ejercicio";

	private IEjercicioContableBO ejercicioContableBO;

	public EjercicioContableTableUi(IEjercicioContableBO ejercicioContableBO) {
		this.ejercicioContableBO = ejercicioContableBO;

		init();
	}

	private void init() {

		// ------------------------------------------------------------------

		rootVH = new VerticalLayout();
		rootVH.setMargin(true);
		rootVH.setSpacing(true);

		// ------------------------------------------------------------------

		filterText = new TextField();
		filterText.setImmediate(true);
		filterText.setInputPrompt(filterTextCaption);
		filterText.addStyleName(ValoTheme.TEXTFIELD_SMALL);
		filterText.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
		filterText.setIcon(FontAwesome.SEARCH);

		clearFilterTextBtn = new Button(FontAwesome.TIMES);
		clearFilterTextBtn.setDescription(clearFilterTextBtnCaption);
		clearFilterTextBtn.addStyleName(ValoTheme.BUTTON_SMALL);

		CssLayout filtering = new CssLayout();
		filtering.addComponents(filterText, clearFilterTextBtn);
		filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

		toolbarHL = new HorizontalLayout(filtering);
		toolbarHL.setWidth("100%");
		toolbarHL.setSpacing(true);
		// toolbarHL.addStyleName(ValoTheme.WINDOW_BOTTOM_TOOLBAR);

		// ------------------------------------------------------------------

		grid = new Grid();

		grid.setImmediate(true);

		grid.setColumns("ejercicio", "fechaApertura", "fechaCierre",
				"ejercicioCerrado", "ejercicioCerradoModulos");

		grid.setSelectionMode(SelectionMode.SINGLE);

		HeaderRow extraHeader = grid.prependHeaderRow();

		HeaderCell joinedCell = extraHeader.join("ejercicio", "fechaApertura",
				"fechaCierre");
		HeaderCell joinedCel2 = extraHeader.join("ejercicioCerrado",
				"ejercicioCerradoModulos");

		joinedCell.setText("");
		joinedCel2.setText(joinedCel2Caption);

		grid.getColumn("ejercicio").setWidth(100);
		grid.getColumn("fechaApertura").setWidth(130);
		grid.getColumn("fechaCierre").setWidth(130);
		grid.getColumn("ejercicioCerrado").setWidth(100);
		grid.getColumn("ejercicioCerradoModulos").setWidth(100);

		// grid.getColumn("id").setHeaderCaption("id");
		grid.getColumn("ejercicio").setHeaderCaption(ejercicioCaption);
		grid.getColumn("fechaApertura").setHeaderCaption(fechaAperturaCaption);
		grid.getColumn("fechaCierre").setHeaderCaption(fechaCierreCaption);
		grid.getColumn("ejercicioCerrado").setHeaderCaption(
				ejercicioCerradoCaption);
		grid.getColumn("ejercicioCerradoModulos").setHeaderCaption(
				ejercicioCerradoModulosCaption);

		// ------------------------------------------------------------------

		grid.setSizeFull();
		rootVH.addComponents(toolbarHL, grid);

		// ------------------------------------------------------------------

		buildFooterToolbar();

		// ------------------------------------------------------------------

		updateList(filterText.getValue());

		grid.getColumn("fechaApertura").setRenderer(
				new DateRenderer(new SimpleDateFormat("dd/MM/yyyy")));

		grid.getColumn("fechaCierre").setRenderer(
				new DateRenderer(new SimpleDateFormat("dd/MM/yyyy")));

		grid.getColumn("ejercicioCerrado").setRenderer(
				new HtmlRenderer(),
				new StringToBooleanConverter(FontAwesome.CHECK_SQUARE_O
						.getHtml(), FontAwesome.SQUARE_O.getHtml()));

		grid.getColumn("ejercicioCerradoModulos").setRenderer(
				new HtmlRenderer(),
				new StringToBooleanConverter(FontAwesome.CHECK_SQUARE_O
						.getHtml(), FontAwesome.SQUARE_O.getHtml()));

		// ------------------------------------------------------------------

		setCompositionRoot(rootVH);

		// ------------------------------------------------------------------

		filterText.addTextChangeListener(e -> {

			updateList(e.getText());

		});

		clearFilterTextBtn.addClickListener(e -> {
			filterText.clear();
			updateList(null);
		});

		// grid.addSelectionListener(event -> {
		// if (event.getSelected().isEmpty() == false) {
		// EjercicioContable ejercicioContables = (EjercicioContable) event
		// .getSelected().iterator().next();
		// openForm(ejercicioContables);
		// }
		// });

		grid.addItemClickListener(new ItemClickListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -7043113996470420510L;

			@Override
			public void itemClick(ItemClickEvent event) {
				if (event.isDoubleClick()) {

					if (grid.getSelectedRow() != null) {
						EjercicioContable ejercicioContable = (EjercicioContable) grid
								.getSelectedRow();
						openForm(ejercicioContable);
					}

				}
			}
		});
	}

	private void buildFooterToolbar() {

		footerHL = new HorizontalLayout();
		footerHL.setWidth("100%");
		footerHL.setSpacing(true);
		// footerHL.addStyleName(ValoTheme.WINDOW_BOTTOM_TOOLBAR);

		rootVH.addComponent(footerHL);

		agregarBtn = new Button(agregarBtnCaption);
		agregarBtn.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		agregarBtn.addStyleName(ValoTheme.BUTTON_SMALL);
		agregarBtn.setIcon(FontAwesome.PLUS);
		agregarBtn.addClickListener(e -> {
			grid.select(null);
			openForm();
		});

		cambiarBtn = new Button(cambiarBtnCaption);
		cambiarBtn.addStyleName(ValoTheme.BUTTON_PRIMARY);
		cambiarBtn.addStyleName(ValoTheme.BUTTON_SMALL);
		cambiarBtn.setIcon(FontAwesome.PENCIL);
		cambiarBtn.addClickListener(e -> {
			if (grid.getSelectedRow() != null) {
				EjercicioContable ejercicioContable = (EjercicioContable) grid
						.getSelectedRow();
				openForm(ejercicioContable);
			}
		});

		eliminarBtn = new Button(eliminarBtnCaption);
		eliminarBtn.addStyleName(ValoTheme.BUTTON_DANGER);
		eliminarBtn.addStyleName(ValoTheme.BUTTON_SMALL);
		eliminarBtn.setIcon(FontAwesome.TRASH);
		eliminarBtn.addClickListener(e -> {
			deleteEvent();
		});

		Label footerText = new Label("");
		footerText.setSizeUndefined();

		elegirEjercicioBtn = new Button(elegirEjercicioBtnCaption);
		elegirEjercicioBtn.addStyleName(ValoTheme.BUTTON_SMALL);
		elegirEjercicioBtn.setIcon(FontAwesome.CHECK);

		// footerHL.addComponents(agregarBtn, cambiarBtn, eliminarBtn,
		// footerText,
		// elegirEjercicioBtn);
		footerHL.addComponents(agregarBtn, cambiarBtn, footerText, eliminarBtn);

		footerHL.setExpandRatio(footerText, 1);

	}

	// /////////////////////////////////////////////////////////////////////////////////////
	public void updateList() {
		updateList(filterText.getValue());
	}

	// /////////////////////////////////////////////////////////////////////////////////////
	// Events

	private void updateList(String ejercicioEndsWith) {
		try {

			List<EjercicioContable> ejercicioContables = null;

			if (ejercicioEndsWith == null || ejercicioEndsWith.isEmpty()) {
				ejercicioContables = this.ejercicioContableBO.findAll();
			} else {
				ejercicioContables = this.ejercicioContableBO
						.findAll(ejercicioEndsWith);
			}

			grid.setContainerDataSource(new BeanItemContainer<>(
					EjercicioContable.class, ejercicioContables));
		} catch (Exception e) {
			grid.setContainerDataSource(new BeanItemContainer<>(
					EjercicioContable.class, new ArrayList<EjercicioContable>()));

			Notification notification = new Notification("Error", e.toString(),
					Type.ERROR_MESSAGE);
			notification.setPosition(Position.BOTTOM_LEFT);
			notification.show(Page.getCurrent());

			new LogPrinter().print(this.getClass().getCanonicalName(),
					LogPrinter.LEVEL_ERROR, e);
		}

	}

	private void openForm() {
		openForm(null);
	}

	private void openForm(EjercicioContable ejercicioContable) {
		try {

			String title = null;

			if (ejercicioContable != null) {
				title = "Modificar ejercicio contable";
			} else {
				title = "Agregar ejercicio contable";
			}

			Window win = new Window(title);

			String prevHeight = "600px";

			win.setWidth("600px");
			win.setHeight(prevHeight);
			win.setClosable(true);
			win.setResizable(true);
			win.setContent(new EjercicioContableFormUi(ejercicioContable,
					ejercicioContableBO, win, this));
			win.addCloseShortcut(KeyCode.ESCAPE, null);
			win.setModal(true);

			getUI().addWindow(win);
			win.center();
			win.focus();

		} catch (Exception e) {
			grid.setContainerDataSource(new BeanItemContainer<>(
					EjercicioContable.class, new ArrayList<EjercicioContable>()));

			Notification notification = new Notification("Error", e.toString(),
					Type.ERROR_MESSAGE);
			notification.setPosition(Position.BOTTOM_LEFT);
			notification.show(Page.getCurrent());

			new LogPrinter().print(this.getClass().getCanonicalName(),
					LogPrinter.LEVEL_ERROR, e);
		}

	}
	
	private void deleteEvent() {
		
		
		try {

			if (grid.getSelectedRow() != null) {
				EjercicioContable ejercicioContable = (EjercicioContable) grid
						.getSelectedRow();

				getUI().addWindow(
						new YesNoDialog("Eliminar", "Esta seguro de eliminar el ejercicio " + ejercicioContable.getEjercicio(),
								new YesNoDialog.Callback() {
									public void onDialogResult(boolean yes) {
										if (yes) {
											delete();
										}
									}
								}));
			}

		} catch (Exception e) {
			LogAndNotification.print(e);
		}

		
		
	}

	private void delete() {

		try {

			if (grid.getSelectedRow() != null) {
				EjercicioContable ejercicioContable = (EjercicioContable) grid
						.getSelectedRow();

				String msg = "Se eliminó con éxito el ejercicio "
						+ ejercicioContable.getEjercicio();

				ejercicioContableBO.delete(ejercicioContable);

				LogAndNotification.printSuccessOk(msg);

				updateList();
			}

		} catch (Exception e) {
			LogAndNotification.print(e);
		}

	}

}
