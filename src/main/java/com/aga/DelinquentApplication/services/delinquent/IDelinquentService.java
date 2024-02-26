package com.aga.DelinquentApplication.services.delinquent;

import com.aga.DelinquentApplication.models.Delinquent;

import java.util.List;

public interface IDelinquentService {

    public List<Delinquent> listDelinquent();

    public Delinquent getDelinquent(int id);

    public void save(Delinquent delinquent);

    public void delete(Delinquent delinquent);
}
