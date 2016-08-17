// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IndexController.java

package br.com.faturamento.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.faturamento.dao.FaturamentoDao;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.inject.Inject;

@Controller
public class IndexController{
	@Inject
	private Result result;
    public IndexController(Result result)
    {
        this.result = result;
    }

    public IndexController(){}

    @Path("/")
    public void index(){
    	
        FaturamentoDao dao = new FaturamentoDao();
        BigDecimal valorServicos = new BigDecimal(0);
        BigDecimal valorPecas = new BigDecimal(0);
        BigDecimal valorTotal = new BigDecimal(0);
        try
        {
        	
            valorServicos = dao.buscaServicos();
            valorPecas = dao.buscaPecas();
            valorTotal = dao.buscaTotal();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        if (valorPecas != null || valorServicos != null || valorTotal != null){
        result.include("servicos", (new DecimalFormat("###,###,##0.00")).format(valorServicos.setScale(2, 0)));
        result.include("pecas", (new DecimalFormat("###,###,##0.00")).format(valorPecas.setScale(2, 0)));
        result.include("total", (new DecimalFormat("###,###,##0.00")).format(valorTotal.setScale(2, 0)));
        }
        else {
        	result.include("servicos", 0.00);
            result.include("pecas", 0.00);
            result.include("total", 0.00);	
        }
        }

    
}
