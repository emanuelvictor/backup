package br.com.emanuelvictor.test.service;

import br.com.agenciaiguassu.domain.entity.Candidato;
import br.com.agenciaiguassu.domain.repository.DAOCandidato;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

@TransactionConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestCandidato {


    @Autowired
    DAOCandidato daoCandidato;

    @Test
    @Rollback(false)
    @Transactional
    public void saveCandidatoContratoVencido() {

        Candidato candidato = daoCandidato.findOne(Long.parseLong("75"));

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)-6);
        candidato.setDataDeContrato(calendar);

        daoCandidato.save(candidato);

         candidato = daoCandidato.findOne(Long.parseLong("76"));


        candidato.setDataDeContrato(calendar);

        daoCandidato.save(candidato);

         candidato = daoCandidato.findOne(Long.parseLong("77"));


        candidato.setDataDeContrato(calendar);

        daoCandidato.save(candidato);

    }


}
