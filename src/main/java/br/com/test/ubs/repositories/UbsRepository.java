package br.com.test.ubs.repositories;

import br.com.test.ubs.models.Ubs;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UbsRepository extends CrudRepository<Ubs, Long> {}

