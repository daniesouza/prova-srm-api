package com.srm.provasrm.repository;

import com.srm.provasrm.model.Lancamento;
import com.srm.provasrm.repository.filter.LancamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LancamentoRepositoryCustom {

    Page<Lancamento> findAllByFilter(LancamentoFilter lancamentoFilter, Pageable pageable);
}
