package com.srm.provasrm.service;

import com.srm.provasrm.model.Lancamento;
import com.srm.provasrm.repository.filter.LancamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LancamentoService {
	Lancamento save(Lancamento lancamento);
    Lancamento update(Long codigo, Lancamento lancamento);
    Page<Lancamento> findAllByFilter(LancamentoFilter lancamentoFilter, Pageable pageable);
	Lancamento find(Long id);
	void delete(Long codigo);
}
