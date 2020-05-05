<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="view/css/bootstrap.min.css" rel="stylesheet">
<script src="component/jquery-3.5.0.min.js" type="text/javascript"></script>
<script src="component/appointmentUI.js" type="text/javascript"></script>


</head>
<body>
	<div class="container">

		<div id="signupbox" margin-top:50px" class="mainbox">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Sign Up</div>
				</div>
				<div class="panel-body">
					<form id="signupform" class="form-horizontal" role="form">

						<div id="alertSuccess" class="alert alert-success"></div>

						<div id="alertError" class="alert alert-danger"></div>

						<div class="form-group">
							<label for="firstname" class="col-md-3 control-label">Doctor
								names</label>
							<div class="col-md-9">
								<select class="form-control" id="hostpitalName">
									<option value="0">choose....</option>
									<option value="1">Dr Saman Perera</option>
								
								</select>
							</div>
						</div>

						<div class="form-group">
							<label for="email" class="col-md-3 control-label">Hospital
								name</label>
							<div class="col-md-9">
								<select class="form-control" id="docterName">
									<option value="0">choose....</option>
										<option value="1">Hemas</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label for="lastname" class="col-md-3 control-label">Description</label>
							<div class="col-md-9">
								<input type="text" class="form-control" name="lastname"
									placeholder="Last Name" id="description">
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-md-3 control-label">Appointment date</label>
							<div class="col-md-9">
								<input type="date" class="form-control" name="date" id="date"
									placeholder="Appointment date">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>