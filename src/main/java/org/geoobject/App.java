package org.geoobject;

public class App {

	public static void main(String[] args) {

		// args = new String[] {
		// "/home/java/Descargas/geo_object_swagger.json" };
		args = new String[] {
				"D:\\dev\\source\\geoobject_api_java\\other_files\\swagger_examples\\geo_object_swagger.json" };

		try {
			GeoObjectCx.getInstance().setupEndpoints(args[0]);

		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
			System.exit(-1);
		}

		// System.exit(0);
	}

}
