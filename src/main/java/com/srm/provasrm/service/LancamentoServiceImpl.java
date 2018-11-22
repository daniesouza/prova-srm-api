package com.srm.provasrm.service;

import com.srm.provasrm.model.Lancamento;
import com.srm.provasrm.repository.LancamentoRepository;
import com.srm.provasrm.repository.filter.LancamentoFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.validation.Valid;

@Service
public class LancamentoServiceImpl implements LancamentoService {
	
	private static final Logger logger = LoggerFactory.getLogger(LancamentoServiceImpl.class);

	private LancamentoRepository lancamentoRepository;

	@Autowired
	public LancamentoServiceImpl(LancamentoRepository lancamentoRepository) {
		this.lancamentoRepository = lancamentoRepository;
	}

	@Override
	public Lancamento save(@Valid Lancamento lancamento) {
		
	    if (logger.isDebugEnabled()) {
            logger.debug("save lancamento "+lancamento);
        }
	        
	    lancamento.setTaxaJuros(lancamento.getRisco().getTaxaJuros());

		return lancamentoRepository.save(lancamento);
	}

	@Override
    public Lancamento update(Long codigo, Lancamento lancamento) {

        if (logger.isDebugEnabled()) {
            logger.debug("update lancamento "+lancamento);
        }

        Lancamento lancamentoDb = lancamentoRepository.findById(codigo).orElseThrow(() -> new EmptyResultDataAccessException(1));

        lancamento.setTaxaJuros(lancamento.getRisco().getTaxaJuros());

        BeanUtils.copyProperties(lancamento, lancamentoDb, "id");
        return lancamentoRepository.save(lancamentoDb);
    }


	public Page<Lancamento> findAllByFilter(LancamentoFilter lancamentoFilter, Pageable pageable) {
		return lancamentoRepository.findAllByFilter(lancamentoFilter, pageable);
	}

    @Override
    public Lancamento find(Long id) {
        return lancamentoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    @Override
    public void delete(Long codigo) {
        lancamentoRepository.deleteById(codigo);
    }

}
