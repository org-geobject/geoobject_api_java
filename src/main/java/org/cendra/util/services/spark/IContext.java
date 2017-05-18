package org.cendra.util.services.spark;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.models.Swagger;

public interface IContext {

	public String getPathJsonSwagger();

	public String getJsonSwaggerIn();

	public String getJsonSwaggerOut() throws JsonProcessingException;

	public Swagger getSwagger();

	public void setupEndpoints(String pathJsonSwagger) throws Exception;

	public Object getBean(String name);

}
