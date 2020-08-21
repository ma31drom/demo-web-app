package by.pvt.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import by.pvt.service.aspect.Log;

public class CrudServiceJpaRepoImpl<T> implements CrudService<T> {

	@Autowired
	private JpaRepository<T, Integer> repo;
	
	@Log
	@Override
	public T getById(Integer id) {
		Optional<T> findById = repo.findById(id);
		return findById.orElseThrow(() -> {
			return new EntityNotFoundException();
		});
	}

	@Override
	public List<T> getAll() {
		return repo.findAll();
	}

	@Override
	public T save(T t) {
		return repo.save(t);
	}

	@Override
	public void delete(T t) {
		repo.delete(t);
	}

}
