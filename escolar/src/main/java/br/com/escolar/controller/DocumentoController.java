package br.com.escolar.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.escolar.model.Documento;
import br.com.escolar.repository.DocumentoRepository;

@Controller
public class DocumentoController {

	@Autowired
	private DocumentoRepository documentoRepository;

	@GetMapping("/inserirDocumentos")
	public ModelAndView InsertDocumentos(Documento documento) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Documento/formDocumento");
		mv.addObject("documento", new Documento());
		return mv;
	}

	@PostMapping("InsertDocumentos")
	public ModelAndView inserirDocumentos(Documento documento) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/documentos-adicionados");
		documentoRepository.save(documento);
		return mv;
	}

	@GetMapping("documentos-adicionados")
	public ModelAndView listagemDocumentos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Documento/listDocumentos");
		mv.addObject("documentosList", documentoRepository.findAll());
		mv.addObject("soma", documentoRepository.soma());
		mv.addObject("somaRestante", documentoRepository.somaRestante());
		mv.addObject("somaNhomologado", documentoRepository.somaNhomologado());
		return mv;

	}

	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Integer id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Documento/alterar");
		Documento documento = documentoRepository.getOne(id);
		mv.addObject("documento", documento);
		return mv;
	}

	@PostMapping("/alterar")
	public ModelAndView alterar(@Valid Documento documento, BindingResult br) {
		ModelAndView mv = new ModelAndView();
		if (br.hasErrors()) {
			mv.setViewName("Documento/formDocumento");
			mv.addObject("documento");
		} else {
			documentoRepository.save(documento);
			mv.setViewName("redirect:/documentos-adicionados");
		}
		return mv;
	}

	@GetMapping("/excluir/{id}")
	public String excluirAluno(@PathVariable("id") Integer id) {
		documentoRepository.deleteById(id);
		return "redirect:/documentos-adicionados";

	}

	@GetMapping("filtro-documentos")
	public ModelAndView filtroDocumentos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Documento/filtroDocumento");		
		mv.addObject("documentosAnalisando", documentoRepository.findByStatusNaoHomologado());
		return mv;
	}

	@GetMapping("documentos-naohomologados")
	public ModelAndView listaDocumentosAnalise() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Documento/documentos-naohomologados");
		mv.addObject("documentosAnalisando", documentoRepository.findByStatusNaoHomologado());
		mv.addObject("somaNhomologado", documentoRepository.somaNhomologado());
		mv.addObject("somaRestante", documentoRepository.somaRestante());
		return mv;

	}

	@GetMapping("documentos-homologados")
	public ModelAndView listaDocumentosDeferidos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Documento/documentos-homologados");
		mv.addObject("documentosDeferidos", documentoRepository.findByStatusHomologado());
		mv.addObject("soma", documentoRepository.soma());
		return mv;

	}

	@PostMapping("pesquisar-documento")
	public ModelAndView pesquisarDocumento(@RequestParam(required = false) String nome) {
		ModelAndView mv = new ModelAndView();
		List<Documento> listaDocumentos;
		if (nome == null || nome.trim().isEmpty()) {
			listaDocumentos = documentoRepository.findAll();
		} else {
			listaDocumentos = documentoRepository.findByNomeContainingIgnoreCase(nome);
		}
		mv.addObject("ListaDeDocumentos", listaDocumentos);
		mv.setViewName("Documento/pesquisa-resultado");
		return mv;
	}

}
