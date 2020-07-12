package com.genweb2.jasper.service;

import com.genweb2.jasper.model.User;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportGeneratorService {

    private String jrXmlPath;

    public ReportGeneratorService(String jrXmlPath) {
        this.jrXmlPath = jrXmlPath;
    }

    public JasperPrint getListOfUserReport(List<User> userList) throws JRException {
        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(userList);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("dataSource", itemsJRBean);

        JasperReport jasperReport = JasperCompileManager.compileReport(jrXmlPath + "user_list.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

        return jasperPrint;
    }
}
