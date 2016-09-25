package com.kimae.forallwebapp.infrastructure;

import javax.ejb.Stateless;

@Stateless
public class ForAllWebService extends WebService {

    @Override
    String getRootUrl() {
        return "http://homolog.delivery.all4mobile.com.br/api/v1/";
    }

}
