package com.massoftware.backend.dao.ejerciciocontable;

import java.util.List;

import org.cendra.commons.model.EntityId;

import com.massoftware.model.ejerciciocontable.EjercicioContable;

public interface IEjercicioContableDAO {

	List<EjercicioContable> findAll() throws Exception;

	List<EjercicioContable> findAll(String ejercicioEndsWith) throws Exception;

	EntityId insert(EjercicioContable item) throws Exception;

	EjercicioContable findMaxEjercicio() throws Exception;

	EjercicioContable findByEjercicio(Integer ejercicio) throws Exception;

	EjercicioContable update(EjercicioContable item) throws Exception;

	EntityId delete(EjercicioContable item) throws Exception;

}
