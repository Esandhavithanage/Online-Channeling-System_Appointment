<%@page import="model.appointment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Online Channling</title>
<link href="view/css/bootstrap.min.css" rel="stylesheet">
<script src="component/jquery-3.5.0.min.js" type="text/javascript"></script>
<script src="component/appointmentUI.js" type="text/javascript"></script>


</head>
<body>
<%session.setAttribute("uID",1);   %>
	<div>
		<nav class="navbar navbar-expand-lg  navbar-dark bg-primary">
		    <a class="navbar-brand" href="#">Online Channling</a>
		 </nav>
		 <br/>
			<div class="container">

			<div id="signupbox" margin-top:50px" class="mainbox">
				<div class="panel panel-info">
					
					<div class="panel-body">
						<form id="detailform" class="form-horizontal" role="form">
	
	
							<div class="form-group">
								<label for="firstname" class="col-md-3 control-label"><h5>Select Doctor
									name :</h5></label>
								<div class="col-md-9">
									<select class="form-control" id="docterName" name ="DocId">
										<option value="0">choose....</option>
										<option value="1">Dr Saman Perera</option>
										<option value="2">Dr Kasun sadruwan</option>
										<option value="3">Dr Gimhan wanasinha</option>
	
									</select>
								</div>
							</div>
	
							<div class="form-group">
								<label for="email" class="col-md-3 control-label"><h5>Hospital
									name :</h5></label>
								<div class="col-md-9">
									<select class="form-control" id="hostpitalName" name ="HospitalId">
										<option value="0">choose....</option>
										<option value="1">Nevil hospital</option>
										<option value="2">Navaloka hospital</option>
										<option value="3">Hemas hospital</option>
									</select>
								</div>
							</div>
	
							<div class="form-group">
								<label for="lastname" class="col-md-3 control-label"><h5>Description</h5></label>
								<div class="col-md-9">
									<input type="text" class="form-control" name="Description"
										placeholder="description" id="description">
								</div>
							</div>
							<div class="form-group">
								<label for="password" class="col-md-3 control-label"><h5>Appointment
									date :</h5></label>
								<div class="col-md-9">
									<input type="date" class="form-control" name="AppointmentDate" id="date"
										placeholder="Appointment date">
								</div>
							</div>
								<input type="hidden" id="hidItemIDSave" name="hidItemIDSave"
								value="">
							
							<button type="button" id="btnSave" class="btn btn-primary">Book
								Now</button>
								
							<input type="hidden" id="PatientId" name="PatientId"
								value="<%out.print(String.valueOf(session.getAttribute("uID")));%>">
								<input type="hidden" id="PaymentId" name="PaymentId"
								value="1">
								
								<br>
								
							<div id="alertSuccess" class="alert alert-success"></div>
	
							<div id="alertError" class="alert alert-danger"></div>
								
						</form>
					</div>
				</div>
			</div>
			<br/>
		</div>
		<div  class="container">
			<div id="divItemsGrid" ">
				<%
					appointment appointment = new appointment();
				out.print(appointment.getApointment());
				%>
			</div>
		</div>
	</div>
</body>
</html>