package br.com.escolar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.escolar.Enums.Status;
import br.com.escolar.Enums.TipoAtividade;

@Entity

@Table(name = "documento")
public class Documento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "nome")
	@Size(min = 5, max = 35, message = "O nome do certificado deve conter no mínimo 5 caracteres")
	@NotBlank(message = "O nome não pode ser vazio.")
	private String nome;

	@Column(name = "tp_atividade")
	@Enumerated(EnumType.STRING)
	private TipoAtividade tipoatividade;

	@Column(name = "carga_horaria")
	private Long carga_horaria;

	@Column(name = "status", columnDefinition="varchar(100) default 'Nao-Homologado'")
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Lob
	@Column(name = "arquivo", columnDefinition="BLOB")
	private byte[] arquivo;

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCarga_horaria() {
		return carga_horaria;
	}

	public void setCarga_horaria(Long carga_horaria) {
		this.carga_horaria = carga_horaria;
	}

	public TipoAtividade getTipoatividade() {
		return tipoatividade;
	}

	public void setTipoatividade(TipoAtividade tipoatividade) {
		this.tipoatividade = tipoatividade;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
