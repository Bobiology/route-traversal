package io.mglobe.landroutes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.mglobe.landroutes.model.RouteModel;

@Repository
public interface RouteRepository {
	RouteModel[] findByCca3(String cca3);
}
