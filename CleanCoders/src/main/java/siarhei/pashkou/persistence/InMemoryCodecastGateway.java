package siarhei.pashkou.persistence;

import java.util.List;
import java.util.stream.Collectors;

import siarhei.pashkou.model.Codecast;

public class InMemoryCodecastGateway extends CodecastGatewayUtilities implements CodecastGateway {

	@Override
	public Codecast findCodecastByTitle(String codecastTitle) {
		return findAll().stream()
				.filter(c -> c.getTitle().equalsIgnoreCase(codecastTitle))
				.findAny().orElse(null);
	}

	@Override
	public List<Codecast> findAllCodecastsSortedByDate() {
		return findAll().stream()
				.sorted((c1, c2) -> c1.getPublishedDate().compareTo(c2.getPublishedDate()))
				.collect(Collectors.toList());
	}

	@Override
	public Codecast findCodecastByPermalink(String permalink) {
		return findAll().stream()
				.filter(c -> permalink.equals(c.getPermalink()))
				.findAny().orElse(null);
	}
}
