package br.com.escolar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.escolar.model.Documento;

public interface DocumentoRepository extends JpaRepository<Documento, Integer> {

	@Query("select d from Documento d where d.status = 'NaoHomologado' or d.status is null")
	public List<Documento> findByStatusNaoHomologado();
	
	@Query("select d from Documento d where d.status = 'Homologado'")
	public List<Documento> findByStatusHomologado();	
	
	@Query("select sum(carga_horaria)  from Documento d where d.status = 'Homologado'")
	Long soma();
	
	@Query("select sum(carga_horaria)  from Documento d where d.status = 'NaoHomologado'")
	Long somaNhomologado();
	
	@Query("select sum(carga_horaria)  from Documento d where d.status is null")
	Long somaRestante();
	
	public List<Documento>findByNomeContainingIgnoreCase(String nome);
	
	
}
