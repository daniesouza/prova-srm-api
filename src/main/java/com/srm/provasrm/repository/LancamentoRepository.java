package com.srm.provasrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srm.provasrm.model.Lancamento;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long>,LancamentoRepositoryCustom{

}
