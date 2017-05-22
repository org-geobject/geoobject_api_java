package org.cendra.commons.util.service.validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

//http://jarroba.com/reflection-en-java/
//http://jarroba.com/annotations-anotaciones-en-java/
public class UtilValidate {

	private final String MSG_1 = "Error interno del sistema: Se quiso retornar un objeto inválido. El objeto es nulo.";
	private final String MSG_2 = "Se recibió como argumento un objeto inválido. El objeto es nulo.";
	private final String MSG_3 = "El atributo ${path} es requerido.";
	private final String MSG_4 = "Error interno del sistema: Se quiso retornar un objeto inválido. El atributo ${path}.${field.getName()} contiene un valor inválido, el valor esperado es '${v}'";
	private final String MSG_5 = "Se recibió como argumento un objeto inválido. El atributo ${path}.${field.getName()} contiene un valor inválido, el valor esperado es '${v}'";
	private final String MSG_6 = "Error interno del sistema: Se quiso retornar un objeto inválido. El atributo ${path}.${field.getName()} es requerido.";
	private final String MSG_7 = "Se recibió como argumento un objeto inválido. El atributo ${path}.${field.getName()} es requerido.";
	private final String MSG_8 = "Error interno del sistema: Se quiso retornar un objeto inválido. El atributo ${path}.${field.getName()} requiere de al menos ${annotation.lengthMin()} elementos, y no ${list.size()} elementos como tiene actualmente.";
	private final String MSG_9 = "Se recibió como argumento un objeto inválido. El atributo ${path}.${field.getName()} requiere de al menos ${annotation.lengthMin()} elementos, y no ${list.size()} elementos como tiene actualmente.";

	public synchronized boolean evalIn(Object o, String path, boolean domain)
			throws IllegalArgumentException, IllegalAccessException {

		return eval(o, path, true, domain);
	}

	public synchronized boolean evalOut(Object o, String path, boolean domain)
			throws IllegalArgumentException, IllegalAccessException {

		return eval(o, path, false, domain);
	}

	private synchronized boolean eval(Object o, String path, boolean in,
			boolean domain) throws IllegalArgumentException,
			IllegalAccessException {

		if (o == null && in) {
			throw new IllegalArgumentException(MSG_1);
		} else if (o == null && in == false) {
			throw new IllegalArgumentException(MSG_2);
		}

		Field[] fields = o.getClass().getDeclaredFields();

		for (Field field : fields) {

			if (field.getType().getCanonicalName()
					.equals(String.class.getCanonicalName()) == true) {

				field.setAccessible(true);
				Object value = field.get(o);
				if (value != null) {
					field.set(o, value.toString().trim());

				}
			}

			List<Validate> validateList = new ArrayList<Validate>();

			Annotation annotations[] = field.getDeclaredAnnotations();

			for (Annotation a : annotations) {

				if (Validate.class.getCanonicalName().equals(
						a.annotationType().getCanonicalName())) {

					Validate validate = (Validate) a;
					validateList.add(validate);
				}

			}

			Validate[] validateArray = new Validate[validateList.size()];

			validateArray = validateList.toArray(validateArray);
			eval(field, o, validateArray, path, in, domain);

		}

		return true;

	}

	@SuppressWarnings("rawtypes")
	public synchronized boolean eval(List list, boolean in, boolean domain)
			throws IllegalArgumentException, IllegalAccessException {

		if (list == null && in) {
			throw new IllegalArgumentException(MSG_1);
		} else if (list == null && in == false) {
			throw new IllegalArgumentException(MSG_2);
		}

		return eval(list, null, in, domain);

	}

	@SuppressWarnings("rawtypes")
	public synchronized boolean eval(List list, String path, boolean in,
			boolean domain) throws IllegalArgumentException,
			IllegalAccessException {

		if (list == null && in) {
			throw new IllegalArgumentException(MSG_1);
		} else if (list == null && in == false) {
			throw new IllegalArgumentException(MSG_2);
		}

		if (path == null) {
			throw new IllegalArgumentException(MSG_3.replace("${path}", path));
		}

		if (path.trim().length() == 0) {
			path = "Element";
		}

		for (int i = 0; i < list.size(); i++) {
			eval(list.get(i), path + "[" + i + "]", in, domain);
		}

		return true;
	}

	private synchronized void eval(Field field, Object o,
			Validate[] annotations, String path, boolean in, boolean domain)
			throws IllegalArgumentException, IllegalAccessException {

		for (Validate annotation : annotations) {

			field.setAccessible(true);
			Object value = field.get(o);

			if (value != null && in == false
					&& annotation.requiredExpectedValueDomain()) {

				// Boolean
				if (field.getType().getPackage().getName()
						.equals(Boolean.class.getPackage().getName()) == true) {

					Boolean b = (Boolean) value;
					Boolean v = annotation.expectedValueBoolean();

					if (v != null && b != v) {
						if (in) {
							throw new IllegalStateException(MSG_4
									.replace("${path}", path)
									.replace("${field.getName()}",
											field.getName())
									.replace("${v}", v + ""));
						} else {
							throw new IllegalStateException(MSG_5
									.replace("${path}", path)
									.replace("${field.getName()}",
											field.getName())
									.replace("${v}", v + ""));
						}
					}
				}

			}

			if (annotation.required()) {

				if (value == null || value.toString().trim().length() == 0) {

					if (in) {
						throw new IllegalStateException(MSG_6.replace(
								"${path}", path).replace("${field.getName()}",
								field.getName()));
					} else {
						throw new IllegalStateException(MSG_7.replace(
								"${path}", path).replace("${field.getName()}",
								field.getName()));
					}

				}

				if (field.getType().getPackage().getName()
						.equals(String.class.getPackage().getName()) == false
						&& field.getType()
								.getPackage()
								.getName()
								.equals(java.util.Date.class.getPackage()
										.getName()) == false
						&& field.getType().getPackage().getName()
								.equals(Timestamp.class.getPackage().getName()) == false) {

					eval(value, path + "." + field.getName(), in, domain);

				}

			}

			if (field.getType().getName()
					.equals(java.util.List.class.getName())
					&& annotation.lengthMin() > 0) {

				if (value == null || value.toString().trim().length() == 0) {

					if (in) {
						throw new IllegalArgumentException(MSG_6.replace(
								"${path}", path).replace("${field.getName()}",
								field.getName()));
					} else {
						throw new IllegalArgumentException(MSG_7.replace(
								"${path}", path).replace("${field.getName()}",
								field.getName()));
					}

				}

				@SuppressWarnings({ "rawtypes" })
				List list = (List) value;

				if (list.size() < annotation.lengthMin()) {

					if (in) {
						throw new IllegalArgumentException(MSG_8
								.replace("${path}", path)
								.replace("${field.getName()}", field.getName())
								.replace("${annotation.lengthMin()}",
										annotation.lengthMin() + "")
								.replace("${list.size()}", list.size() + ""));
					} else {
						throw new IllegalArgumentException(MSG_9
								.replace("${path}", path)
								.replace("${field.getName()}", field.getName())
								.replace("${annotation.lengthMin()}",
										annotation.lengthMin() + "")
								.replace("${list.size()}", list.size() + ""));
					}
				}

			}

		}

	}
}
