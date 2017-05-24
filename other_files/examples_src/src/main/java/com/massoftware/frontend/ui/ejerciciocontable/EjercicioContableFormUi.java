package com.massoftware.frontend.ui.ejerciciocontable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.cendra.commons.util.dao.ex.InsertDuplicateException;

import com.massoftware.backend.bo.ejerciciocontable.IEjercicioContableBO;
import com.massoftware.frontend.ui.util.LogAndNotification;
import com.massoftware.frontend.ui.util.StringToIntegerConverterUnspecifiedLocale;
import com.massoftware.frontend.ui.util.UtilDate;
import com.massoftware.frontend.ui.util.YesNoDialog;
import com.massoftware.model.ejerciciocontable.EjercicioContable;
import com.vaadin.data.Validator;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class EjercicioContableFormUi extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1215098492704552373L;

	protected VerticalLayout rootVH;
	protected FormLayout formLayout;
	protected Label seccionLBL;
	protected TextField ejercicioTXT;
	protected HorizontalLayout fechasHL;
	protected DateField fechaAperturaTXT;
	protected DateField fechaCierreTXT;
	protected CheckBox ejercicioCerradoCXB;
	protected CheckBox ejercicioCerradoModulosCXB;
	protected TextArea comentarioTXA;

	protected HorizontalLayout footerHL;
	protected Button guardarBTN;
	protected Button cancelarBTN;

	// -------------------------------------------------------------------

	protected String ejercicioTXTFCaption = "Ejercicio";
	protected String fechasHLCaption = "Fechas";
	protected String fechaAperturaTXTCaption = "Apertura";
	protected String fechaCierreTXTCaption = "Cierre";
	protected String ejercicioCerradoCXBCaption = "Ejercicio cerrado";
	protected String ejercicioCerradoModulosCXBCaption = "Ejercicio cerrado módulos";
	protected String comentarioTXACaption = "Comentario";

	protected String seccionLBLValue = "Se copiaran los datos del ejercicio ${maxEjercicio}";

	protected String ejercicioTXTMsgError1 = "El campo " + ejercicioTXTFCaption
			+ " es requerido.";
	protected String fechaAperturaTXTMsgError1 = "El campo "
			+ fechaAperturaTXTCaption + " es requerido.";
	protected String fechaAperturaTXTMsgError2 = "El valor del campo "
			+ fechaAperturaTXTCaption + " debe ser menor al del campo "
			+ fechaCierreTXTCaption + ".";
	protected String fechaCierreTXTMsgError1 = "El campo "
			+ fechaCierreTXTCaption + " es requerido.";
	protected String ejercicioTXTMsgError2 = "El ejercicio debe ser un número entero y positivo de 4 cifras, igual a ${maxEjercicio}. Usted cargo el valor \"{0}\" y es inválido.";

	private String guardarBTNCaptionInsert = "Agregar ejercicio";
	private String guardarBTNCaptionUpdate = "Modificar ejercicio";
	private String cancelarBTNCaption = "Cancelar";

	// -------------------------------------------------------------------

	private Window window;
	private EjercicioContableTableUi ejercicioContableTableUi;

	private IEjercicioContableBO ejercicioContableBO;
	private EjercicioContable ejercicioContable;
	private EjercicioContable maxEjercicioContable;

	private String format = "dd/MM/yyyy";

	private boolean insert = false;

	public EjercicioContableFormUi(EjercicioContable ejercicioContable,
			IEjercicioContableBO ejercicioContableBO, Window window,
			EjercicioContableTableUi ejercicioContableTableUi) {
		this.ejercicioContable = ejercicioContable;
		this.ejercicioContableBO = ejercicioContableBO;
		this.window = window;
		this.ejercicioContableTableUi = ejercicioContableTableUi;
		init();
	}

	private void init() {

		try {

			if (ejercicioContable != null) {
				ejercicioContable = ejercicioContable.clone();

				insert = false;

			} else {

				insert = true;

				ejercicioContable = new EjercicioContable();

				maxEjercicioContable = ejercicioContableBO.findMaxEjercicio();

				ejercicioContable.setEjercicio(maxEjercicioContable
						.getEjercicio() + 1);

				DateFormat df = new SimpleDateFormat(format);

				String nuevaFechaAperturaString = df
						.format(maxEjercicioContable.getFechaApertura());
				Date nuevaFechaApertura = UtilDate.parseDate(
						nuevaFechaAperturaString,
						ejercicioContable.getEjercicio());
				ejercicioContable.setFechaApertura(nuevaFechaApertura);

				String nuevaFechaCierreString = df.format(maxEjercicioContable
						.getFechaCierre());
				Date nuevaFechaCierre = UtilDate.parseDate(
						nuevaFechaCierreString,
						ejercicioContable.getEjercicio());
				ejercicioContable.setFechaCierre(nuevaFechaCierre);

				ejercicioContable.setEjercicioCerrado(maxEjercicioContable
						.getEjercicioCerrado());
				ejercicioContable
						.setEjercicioCerradoModulos(maxEjercicioContable
								.getEjercicioCerradoModulos());
				ejercicioContable.setComentario(maxEjercicioContable
						.getComentario());
			}

			BeanItem<EjercicioContable> item = new BeanItem<EjercicioContable>(
					ejercicioContable);

			// --------------------------------------------------------------------

			rootVH = new VerticalLayout();
			rootVH.setMargin(true);
			rootVH.setSpacing(true);

			// --------------------------------------------------------------------

			formLayout = new FormLayout();
			// formLayout.setSpacing(true);
			// formLayout.setMargin(true);
			formLayout.setWidth("800px");
			// form.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);

			// --------------------------------------------------------------------

			if (maxEjercicioContable != null) {
				seccionLBL = new Label(seccionLBLValue.replace(
						"${maxEjercicio}", maxEjercicioContable.getEjercicio()
								.toString()));
				seccionLBL.addStyleName(ValoTheme.LABEL_H3);
				seccionLBL.addStyleName(ValoTheme.LABEL_COLORED);
				rootVH.addComponent(seccionLBL);
			}
			// --------------------------------------------------------------------

			ejercicioTXT = new TextField(ejercicioTXTFCaption,
					item.getItemProperty("ejercicio"));
			ejercicioTXT.addStyleName(ValoTheme.TEXTFIELD_SMALL);
			ejercicioTXT.setRequired(true);
			ejercicioTXT.setRequiredError(ejercicioTXTMsgError1);
			ejercicioTXT
					.setConverter(new StringToIntegerConverterUnspecifiedLocale());
			ejercicioTXT.setNullRepresentation("");

			ejercicioTXT.addValidator(new IntegerRangeValidator(
					ejercicioTXTMsgError2.replace("${maxEjercicio}",
							ejercicioContable.getEjercicio().toString()),
					ejercicioContable.getEjercicio(), ejercicioContable
							.getEjercicio()));
			ejercicioTXT.setReadOnly(true);
			formLayout.addComponent(ejercicioTXT);

			// --------------------------------------------------------------------

			fechasHL = new HorizontalLayout();
			fechasHL.setSpacing(true);
			fechasHL.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
			fechasHL.setCaption(fechasHLCaption);

			fechaAperturaTXT = new DateField(fechaAperturaTXTCaption,
					item.getItemProperty("fechaApertura")) {

				private static final long serialVersionUID = -1814526872789903256L;

				@Override
				protected Date handleUnparsableDateString(String dateString)
						throws Converter.ConversionException {

					return UtilDate.parseDate(dateString,
							ejercicioContable.getEjercicio());
				}

				public void changeVariables(Object source,
						Map<String, Object> variables) {

					if (variables.containsKey("dateString") == false) {
						variables.put(
								"dateString",
								variables.get("day") + "/"
										+ variables.get("month") + "/"
										+ variables.get("year"));
					}

					variables.put("day", -1);
					variables.put("year", -1);
					variables.put("month", -1);
					super.changeVariables(source, variables);
				}

			};
			fechaAperturaTXT.setDateFormat(format);
			fechaAperturaTXT.setLenient(true);
			fechaAperturaTXT.setRequired(true);
			fechaAperturaTXT.setRequiredError(fechaAperturaTXTMsgError1);
			fechaAperturaTXT.addValidator(new Validator() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 6243776212742269377L;

				@Override
				public void validate(Object value) throws InvalidValueException {

					if (value != null
							&& ejercicioContable.getFechaCierre() != null) {
						Date v = (Date) value;

						if (v.compareTo(fechaCierreTXT.getValue()) > 0) {
							throw new InvalidValueException(
									fechaAperturaTXTMsgError2);
						}

					}

				}
			});
			fechaAperturaTXT.addStyleName(ValoTheme.TEXTFIELD_SMALL);

			fechaCierreTXT = new DateField(fechaCierreTXTCaption,
					item.getItemProperty("fechaCierre")) {

				private static final long serialVersionUID = -1713115531421365273L;

				@Override
				protected Date handleUnparsableDateString(String dateString)
						throws Converter.ConversionException {

					return UtilDate.parseDate(dateString,
							ejercicioContable.getEjercicio());
				}

				public void changeVariables(Object source,
						Map<String, Object> variables) {

					if (variables.containsKey("dateString") == false) {
						variables.put(
								"dateString",
								variables.get("day") + "/"
										+ variables.get("month") + "/"
										+ variables.get("year"));
					}

					variables.put("day", -1);
					variables.put("year", -1);
					variables.put("month", -1);
					super.changeVariables(source, variables);
				}

			};
			fechaCierreTXT.setDateFormat(format);
			fechaCierreTXT.setLenient(true);
			fechaCierreTXT.setRequired(true);
			fechaCierreTXT.setRequiredError(fechaCierreTXTMsgError1);
			fechaCierreTXT.addValidator(new Validator() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 5464053357210381555L;

				@Override
				public void validate(Object value) throws InvalidValueException {

					if (value != null
							&& ejercicioContable.getFechaApertura() != null) {
						Date v = (Date) value;

						if (v.compareTo(fechaAperturaTXT.getValue()) < 0) {
							throw new InvalidValueException(
									fechaAperturaTXTMsgError2);
						}

					}

				}
			});
			fechaCierreTXT.addStyleName(ValoTheme.TEXTFIELD_SMALL);

			fechasHL.addComponents(fechaAperturaTXT, fechaCierreTXT);
			formLayout.addComponent(fechasHL);

			// --------------------------------------------------------------------

			ejercicioCerradoCXB = new CheckBox(ejercicioCerradoCXBCaption,
					item.getItemProperty("ejercicioCerrado"));
			formLayout.addComponent(ejercicioCerradoCXB);

			// --------------------------------------------------------------------

			ejercicioCerradoModulosCXB = new CheckBox(
					ejercicioCerradoModulosCXBCaption,
					item.getItemProperty("ejercicioCerradoModulos"));
			formLayout.addComponent(ejercicioCerradoModulosCXB);

			// --------------------------------------------------------------------

			comentarioTXA = new TextArea(comentarioTXACaption,
					item.getItemProperty("comentario"));
			comentarioTXA.setWidth("400px");
			comentarioTXA.setRows(5);
			comentarioTXA.setNullRepresentation("");
			formLayout.addComponent(comentarioTXA);

			// --------------------------------------------------------------------

			rootVH.addComponent(formLayout);

			buildFooterToolbar();

			setCompositionRoot(rootVH);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void buildFooterToolbar() {

		footerHL = new HorizontalLayout();
		footerHL.setWidth("100%");
		footerHL.setSpacing(true);
		// footerHL.addStyleName(ValoTheme.WINDOW_BOTTOM_TOOLBAR);

		rootVH.addComponent(footerHL);

		if (insert) {
			guardarBTN = new Button(guardarBTNCaptionInsert);
		} else {
			guardarBTN = new Button(guardarBTNCaptionUpdate);
		}
		guardarBTN.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		guardarBTN.addStyleName(ValoTheme.BUTTON_SMALL);
		guardarBTN.setIcon(FontAwesome.CHECK);

		guardarBTN.addClickListener(new ClickListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 5955457044776893082L;

			@Override
			public void buttonClick(ClickEvent event) {
				insert();
			}
		});

		Label footerText = new Label("");
		footerText.setSizeUndefined();

		cancelarBTN = new Button(cancelarBTNCaption);
		cancelarBTN = new Button(cancelarBTNCaption);
		cancelarBTN.addStyleName(ValoTheme.BUTTON_DANGER);
		cancelarBTN.addStyleName(ValoTheme.BUTTON_SMALL);
		cancelarBTN.setIcon(FontAwesome.CLOSE);
		cancelarBTN.addClickListener(new ClickListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 5955457044776893082L;

			@Override
			public void buttonClick(ClickEvent event) {
				exit();
			}
		});

		footerHL.addComponents(guardarBTN, footerText, cancelarBTN);
		footerHL.setExpandRatio(footerText, 1);
	}

	private void insert() {

		try {

			ejercicioTXT.validate();
			fechaAperturaTXT.validate();
			fechaCierreTXT.validate();

			String msg = "";

			if (insert) {
				ejercicioContableBO.insert(ejercicioContable);
				msg = "Se agregó con éxito el ejercicio "
						+ ejercicioContable.getEjercicio();

			} else {
				ejercicioContableBO.update(ejercicioContable);
				msg = "Se modificó con éxito el ejercicio "
						+ ejercicioContable.getEjercicio();
			}

			LogAndNotification.printSuccessOk(msg);

			ejercicioContableTableUi.updateList();

		} catch (InvalidValueException e) {
			LogAndNotification.print(e);
		} catch (InsertDuplicateException e) {
			LogAndNotification.print(e);
		} catch (Exception e) {
			LogAndNotification.print(e);
		}

		exit();
	}

	private void exit() {

		try {

			window.close();

		} catch (Exception e) {
			LogAndNotification.print(e);
		}
	}

}
