package ru.zakharenko.javaquerytool.services;

import java.util.List;
import java.util.UUID;

public interface BaseService<Entitiy, ID> {
	Entitiy create(Entitiy entity);

	Entitiy getById(ID id);

	List<Entitiy> getAll();

	Entitiy update(Entitiy entity);

	void delete(ID id);
}
