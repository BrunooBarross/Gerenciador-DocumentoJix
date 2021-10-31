package br.com.escolar.Enums;

public enum Status {
	Homologado("Homologado"), NaoHomologado("Nao-Homologado");

	private String status;

	private Status(String status) {
		this.status = status;
	}

}
