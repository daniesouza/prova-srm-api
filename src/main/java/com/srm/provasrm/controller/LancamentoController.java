package com.srm.provasrm.controller;

import com.srm.provasrm.model.Lancamento;
import com.srm.provasrm.repository.filter.LancamentoFilter;
import com.srm.provasrm.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

	
	private LancamentoService lancamentoService;
	
	@Autowired
	public LancamentoController(LancamentoService lancamentoService) {
		this.lancamentoService = lancamentoService;
	}

	@PostMapping()
	public ResponseEntity<Lancamento> save(@RequestBody @Valid Lancamento lancamento) {
		Lancamento lancamentoDb = lancamentoService.save(lancamento);
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoDb);
	}

    @PutMapping("/{id}")
    public ResponseEntity<Lancamento> update(@PathVariable Long id, @RequestBody @Valid Lancamento lancamento) {
        return ResponseEntity.status(HttpStatus.OK).body(lancamentoService.update(id, lancamento));
    }


    @GetMapping
	public Page<Lancamento> findAllByFilter(LancamentoFilter lancamentoFilter, Pageable pageable) {
		return lancamentoService.findAllByFilter(lancamentoFilter, pageable);
	}


	@GetMapping("/{id}")
	public ResponseEntity<Lancamento> find(@PathVariable Long id) {
		Lancamento lancamentoDb = lancamentoService.find(id);
		return ResponseEntity.ok(lancamentoDb);
	}


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        lancamentoService.delete(id);
    }


}
