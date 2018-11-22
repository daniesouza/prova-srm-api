package com.srm.provasrm.service;

import com.srm.provasrm.ProvaSrmApplication;
import com.srm.provasrm.model.Lancamento;
import com.srm.provasrm.model.Risco;
import com.srm.provasrm.repository.LancamentoRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class )
@SpringBootTest(classes = ProvaSrmApplication.class)
public class LancamentoServiceTest {


	@Mock
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private LancamentoService lancamentoService;

	@Before
	public void setUpMock() {
		MockitoAnnotations.initMocks(this);


		Mockito.when(lancamentoRepository.save(any(Lancamento.class)))
				.thenReturn(new Lancamento());
	}


	@Test
	public void lancamentoContabilServiceTest() {

        Lancamento lancamento = new Lancamento();
        lancamento.setNomeCliente("Teste");
        lancamento.setLimiteCredito(BigDecimal.TEN);
        lancamento.setRisco(Risco.A);

		Assert.assertNotNull(lancamentoService.save(lancamento));
	}

}
