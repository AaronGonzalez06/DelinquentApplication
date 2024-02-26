package com.aga.DelinquentApplication.services.caso;

import com.aga.DelinquentApplication.models.Case;
import com.aga.DelinquentApplication.models.Delinquent;
import com.aga.DelinquentApplication.repository.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CaseService implements ICaseService{

    @Autowired
    CaseRepository caseRepository;

    @Override
    public List<Case> listCase() {
        List<Case> c = caseRepository.findAll();
        return c;
    }

    @Override
    public Case getCase(int c) {

        Case caseData = caseRepository.findById(c).orElse(null);
        return caseData;
    }

    @Override
    public void save(Case c) {
        caseRepository.save(c);

    }

    @Override
    public void delete(Case c) {
        caseRepository.delete(c);
    }


    public void updateClosedCase(int id, Date date){ caseRepository.updateStateById(id,date); }
}
