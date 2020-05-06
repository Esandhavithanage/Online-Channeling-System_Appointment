package com;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.appointment;
import model.appointmentPOJO;

/**
 * Servlet implementation class appointmentAPI
 */
@WebServlet("/appointmentAPI")
public class appointmentAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    appointment appointment = new appointment();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public appointmentAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		appointmentPOJO appointmentData = new appointmentPOJO();
		appointmentData.setAppointmentDate(request.getParameter("AppointmentDate"));
		appointmentData.setDescription(request.getParameter("Description"));
		appointmentData.setPatientId(Integer.parseInt(request.getParameter("PatientId")));
		appointmentData.setPaymentId(Integer.parseInt(request.getParameter("PaymentId")));
		appointmentData.setDocId(Integer.parseInt(request.getParameter("DocId")));
		appointmentData.setHospitalId(Integer.parseInt(request.getParameter("HospitalId")));
		String	output = appointment.addNewAppointment(appointmentData);
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request);
		Map paras = getParasMap(request);	
		appointmentPOJO appointmentData = new appointmentPOJO();
		System.out.println(paras.get("AppointmentDate"));
		appointmentData.setAppointmentID(Integer.parseInt(paras.get("hidItemIDSave").toString()));
		appointmentData.setAppointmentDate(paras.get("AppointmentDate").toString());
		
		String Description = URLDecoder.decode(paras.get("Description").toString(), "UTF-8");
		
		appointmentData.setDescription(Description);
		appointmentData.setDocId(Integer.parseInt(paras.get("DocId").toString()));
		appointmentData.setHospitalId(Integer.parseInt(paras.get("HospitalId").toString()));
		String	 output = appointment.updateappoitment(appointmentData);
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		System.out.println(paras);
		appointmentPOJO appointmentData = new appointmentPOJO();
		appointmentData.setAppointmentID(Integer.parseInt(paras.get("AppointmentID").toString()));
		String	 output = appointment.deleteappoitment(appointmentData);
		response.getWriter().write(output);
	}

	private static Map getParasMap(HttpServletRequest request) { 
		
		Map<String, String> map = new HashMap<String, String>(); 
		
		try  {   
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");   
			String queryString = scanner.hasNext() ?          
			scanner.useDelimiter("\\A").next() : "";  
			scanner.close(); 
			
			String[] params = queryString.split("&"); 
			
			for (String param : params) {
				String[] p = param.split("=");    
				map.put(p[0], p[1]); 
			}
		}
		catch (Exception e) {
			
			
		}
		
		return map; 
	}
	
}
