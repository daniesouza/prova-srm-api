package com.srm.provasrm.repository;

import com.srm.provasrm.model.Lancamento;
import com.srm.provasrm.repository.filter.LancamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class LancamentoRepositoryCustomImpl implements LancamentoRepositoryCustom {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Lancamento> findAllByFilter(LancamentoFilter lancamentoFilter, Pageable pageable) {

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Lancamento> criteria = criteriaBuilder.createQuery(Lancamento.class);
        Root<Lancamento> root = criteria.from(Lancamento.class);

        Predicate[] predicates = criarRestricoes(lancamentoFilter, criteriaBuilder, root);
        criteria.where(predicates);

        TypedQuery<Lancamento> query = manager.createQuery(criteria);

        adicionaRestricaoPaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter));
    }

    private Long total(LancamentoFilter lancamentoFilter) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Lancamento> root = criteria.from(Lancamento.class);

        Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
        criteria.where(predicates);
        criteria.select(builder.count(root));

        return manager.createQuery(criteria).getSingleResult();
    }

    private void adicionaRestricaoPaginacao(TypedQuery<?> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPagina = pageable.getPageSize();
        int primeiroRegistroPagina = paginaAtual * totalRegistrosPagina;

        query.setFirstResult(primeiroRegistroPagina);
        query.setMaxResults(totalRegistrosPagina);

    }

    private Predicate[] criarRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder criteriaBuilder, Root<Lancamento> root) {

        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(lancamentoFilter.getNomeCliente())) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("nomeCliente")), "%" + lancamentoFilter.getNomeCliente().toLowerCase() + "%"
                    )
            );
        }

        return predicates.toArray(new Predicate[0]);
    }
}
