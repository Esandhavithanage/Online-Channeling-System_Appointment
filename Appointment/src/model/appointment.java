package model;

import java.awt.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.JsonObject;

public class appointment {

	private Connection connect() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/helthcare", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	public String addNewAppointment(appointmentPOJO appointmentData) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for readline";
			}

			String sql = "INSERT INTO `appoinment`(`appoinmentDate`,`Description`,`patientId`, `paymentId`, `docId`, `hospitalId`) VALUES (?,?,?,?,?,?)";

			PreparedStatement statement = con.prepareStatement(sql);

			statement.setDate(1, java.sql.Date.valueOf(appointmentData.getAppointmentDate()));
			statement.setString(2, appointmentData.getDescription());
			statement.setInt(3, appointmentData.getPatientId());
			statement.setInt(4, appointmentData.getPaymentId());
			statement.setInt(5, appointmentData.getDocId());
			statement.setInt(6, appointmentData.getHospitalId());
			statement.execute();

			con.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		String newRead = getApointment();

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("status", "success");
		jsonObject.addProperty("data", newRead);
		return jsonObject.toString();
	}

	public ArrayList<appointmentPOJO> getApointmentBydocAndDate(int DocID, String date) {
		String output = "";
		ArrayList<appointmentPOJO> resultSet = new ArrayList<appointmentPOJO>();

		try {
			Connection con = connect();

			if (con == null) {
				return resultSet;
			}

			String query = "SELECT a.appoinmentId,a.appoinmentDate,p.fNmae,h.hospitalName,py.amount,a.docId,a.hospitalId,a.patientId,a.paymentId "
					+ "FROM appoinment a " + "INNER JOIN payment py " + "ON a.paymentId = py.paymentId "
					+ "INNER JOIN patient p " + "ON a.patientId = p.patientId " + "INNER JOIN hospital h "
					+ "ON a.hospitalId = h.hospitalId WHERE a.docId=? AND a.appoinmentDate=?";
			PreparedStatement statement = con.prepareStatement(query);

			statement.setInt(1, DocID);
			statement.setDate(2, java.sql.Date.valueOf(date));
			ResultSet set = statement.executeQuery();

			while (set.next()) {
				appointmentPOJO appointmentPOJO = new appointmentPOJO();
				appointmentPOJO.setAppointmentID(set.getInt("appoinmentId"));
				appointmentPOJO.setHospitalName(set.getString("hospitalName"));
				appointmentPOJO.setPatientName(set.getString("fNmae"));
				appointmentPOJO.setDocId(set.getInt("docId"));
				appointmentPOJO.setHospitalId(set.getInt("hospitalId"));
				appointmentPOJO.setPatientId(set.getInt("patientId"));
				appointmentPOJO.setPaymentId(set.getInt("paymentId"));
				appointmentPOJO.setPayment(set.getDouble("amount"));
				appointmentPOJO.setAppointmentDate(String.valueOf(set.getDate("appoinmentDate")));
				resultSet.add(appointmentPOJO);
				System.out.println(appointmentPOJO.getAppointmentDate());
				System.out.println(appointmentPOJO.getAppointmentID());

			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return resultSet;
	}

	public String getApointment() {
		String output = "";
		output = "<table class=\"table\">" 
				+"<thead>"
		        + "<tr>" 
				+ "<th>Appoinment ID</th>" 
		        + "<th>Appoinment Date</th>"
				+ "<th>Docter Name</th>"
		        + "<th>Hospital Name</th>"
				+ "<th>Amount</th>"
		        + "<th>Description</th>"
				+ "<th>Update</th>"
		        + "<th>Remove</th>"
				+ "</tr>"
		        +"</thead>";
		try {
			Connection con = connect();

			if (con == null) {
				return "Errorwhile connecting to db";
			}

			String query = "SELECT a.appoinmentId,a.appoinmentDate,a.Description,d.fName,h.hospitalName,py.amount,a.docId,a.hospitalId,a.patientId,a.paymentId "
					+ "FROM appoinment a " + "INNER JOIN payment py " + "ON a.paymentId = py.paymentId "
					+ "INNER JOIN doctor d " + "ON a.docId = d.docId " + "INNER JOIN hospital h "
					+ "ON a.hospitalId = h.hospitalId";

			PreparedStatement statement = con.prepareStatement(query);
			ResultSet set = statement.executeQuery();

			while (set.next()) {

				int appoinmentId = set.getInt("appoinmentId");
				int dId = set.getInt("docId");
				int hId = set.getInt("hospitalId");
				String Dname = set.getString("fName");
				String Hname = set.getString("hospitalName");
				Double amount = set.getDouble("amount");
				String date = String.valueOf(set.getDate("appoinmentDate"));
				String Description = set.getString("Description");
				output += "<tr><td><input id=\"hidItemIdUpdate\" value=\"" + appoinmentId
						+ "\" name=\"hidItemIdUpdate\" type=\"hidden\"> " + appoinmentId + " </td>";
				output += "<td>" + date + "</td>";
				output += "<td><input id=\"hiddidUpdate\" value=\"" + dId
						+ "\" name=\"hidItemIdUpdate\" type=\"hidden\">" + Dname + "</td>";
				output += "<td> <input id=\"hidhidUpdate\" value=\"" + hId
						+ "\" name=\"hidItemIdUpdate\" type=\"hidden\">" + Hname + "</td>";
				output += "<td>" + amount + "</td>";
				output += "<td>" + Description + "</td>";

				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btnUpdate btn btn-secondary\"></td>"
						+ "<td><button name=\"btnRemove\" data-itemid='" + appoinmentId + "' type=\"submit\" value=\""
						+ appoinmentId + "\"class=\"btnRemove btn btn-danger\">Remove</button></td></tr>";
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		output += "</table>";
		return output;
	}

	public ArrayList<appointmentPOJO> getApointmentByhospitalAndDate(int hid, String date) {
		String output = "";
		ArrayList<appointmentPOJO> resultSet = new ArrayList<appointmentPOJO>();

		try {
			Connection con = connect();

			if (con == null) {
				return resultSet;
			}

			String query = "SELECT a.appoinmentId,a.appoinmentDate,p.fNmae,d.fName as dname ,py.amount,a.docId,a.hospitalId,a.patientId,a.paymentId "
					+ "FROM appoinment a " + "INNER JOIN payment py " + "ON a.paymentId = py.paymentId "
					+ "INNER JOIN doctor d " + "ON a.docId = d.docId " + "INNER JOIN patient p "
					+ "ON a.patientId = p.patientId " + "WHERE a.hospitalId =? AND a.appoinmentDate =?";
			PreparedStatement statement = con.prepareStatement(query);

			statement.setInt(1, hid);
			statement.setDate(2, java.sql.Date.valueOf(date));
			ResultSet set = statement.executeQuery();

			while (set.next()) {
				appointmentPOJO appointmentPOJO = new appointmentPOJO();
				appointmentPOJO.setAppointmentID(set.getInt("appoinmentId"));
				appointmentPOJO.setPatientName(set.getString("fNmae"));
				appointmentPOJO.setDocName(set.getString("dname"));
				appointmentPOJO.setPayment(set.getDouble("amount"));
				appointmentPOJO.setDocId(set.getInt("docId"));
				appointmentPOJO.setHospitalId(set.getInt("hospitalId"));
				appointmentPOJO.setPatientId(set.getInt("patientId"));
				appointmentPOJO.setPaymentId(set.getInt("paymentId"));
				appointmentPOJO.setAppointmentDate(String.valueOf(set.getDate("appoinmentDate")));
				resultSet.add(appointmentPOJO);
				System.out.println(appointmentPOJO.getAppointmentDate());
				System.out.println(appointmentPOJO.getAppointmentID());

			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return resultSet;
	}

	public String updateappoitment(appointmentPOJO appointmentData) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for readline";
			}

			String sql = "UPDATE `appoinment` SET `appoinmentDate`=?,`docId`=?,`Description`=? WHERE `appoinmentId`=?";

			PreparedStatement statement = con.prepareStatement(sql);
			System.out.println(appointmentData.getAppointmentDate() + " " + appointmentData.getDocId() + " "
					+ appointmentData.getAppointmentID());
			statement.setDate(1, java.sql.Date.valueOf(appointmentData.getAppointmentDate()));
			statement.setInt(2, appointmentData.getDocId());
			statement.setString(3, appointmentData.getDescription());
			statement.setInt(4, appointmentData.getAppointmentID());
			statement.execute();
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		String newRead = getApointment();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("status", "success");
		jsonObject.addProperty("data", newRead);
		return jsonObject.toString();
	}

	public String deleteappoitment(appointmentPOJO appointmentData) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for readline";
			}

			String sql = "DELETE FROM `appoinment` WHERE `appoinmentId` = ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, appointmentData.getAppointmentID());
			statement.execute();

			con.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		String newRead = getApointment();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("status", "success");
		jsonObject.addProperty("data", newRead);
		return jsonObject.toString();
	}

}
