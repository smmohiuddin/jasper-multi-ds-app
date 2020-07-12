package com.genweb2.jasper.servlet;

import com.genweb2.jasper.model.User;
import com.genweb2.jasper.service.ReportDataService;
import com.genweb2.jasper.service.ReportGeneratorService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

@WebServlet("/Report/UserList")
public class ReportServlet extends HttpServlet {

    private ReportDataService dataService;
    private ReportGeneratorService reportGeneratorService;

    @Override
    public void init() throws ServletException {
        this.dataService = new ReportDataService();
        this.reportGeneratorService = new ReportGeneratorService(this.getJRXMLFilePath());
    }

    private String getJRXMLFilePath() {
        return this.getServletContext().getInitParameter("report_path");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("reportType"));

        try {
            List<User> userList = this.dataService.getUserList();

            JasperPrint listOfUserReport = this.reportGeneratorService.getListOfUserReport(userList);

            /* Write content to PDF file */
            JasperExportManager.exportReportToPdfStream(listOfUserReport, resp.getOutputStream());

        } catch (SQLException | JRException e) {
            e.printStackTrace();
            resp.getOutputStream().close();
        }
    }
}
