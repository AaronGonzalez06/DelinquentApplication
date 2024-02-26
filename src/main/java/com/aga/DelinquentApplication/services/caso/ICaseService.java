package com.aga.DelinquentApplication.services.caso;


import com.aga.DelinquentApplication.models.Case;

import java.util.List;


public interface ICaseService {

    public List<Case> listCase();

    public Case getCase(int c);

    public void save(Case c);

    public void delete(Case c);
}
