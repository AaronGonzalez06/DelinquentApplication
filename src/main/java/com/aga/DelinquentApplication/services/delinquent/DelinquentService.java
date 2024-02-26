package com.aga.DelinquentApplication.services.delinquent;

import com.aga.DelinquentApplication.models.Delinquent;
import com.aga.DelinquentApplication.repository.DelinquentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DelinquentService implements IDelinquentService {

    @Autowired
    private DelinquentRepository delinquentRepository;

    @Override
    public List<Delinquent> listDelinquent() {

        List<Delinquent> delinquents = delinquentRepository.findAll();
        return delinquents;
    }

    @Override
    public Delinquent getDelinquent(int id) {
        Delinquent delinquent = delinquentRepository.findById(id).orElse(null);
        return delinquent;
    }

    @Override
    public void save(Delinquent delinquent) {
        delinquentRepository.save(delinquent);
    }

    @Override
    public void delete(Delinquent delinquent) {
        delinquentRepository.delete(delinquent);
    }
}
