package siarhei.pashkou.persistence;

import java.util.List;

import siarhei.pashkou.model.Codecast;

public interface CodecastGateway {
	Codecast saveCodecast(Codecast codecast);
	void delete(Codecast codecast);
	Codecast findCodecastByTitle(String codecastTitle);
	List<Codecast> findAllCodecastsSortedByDate();
}
