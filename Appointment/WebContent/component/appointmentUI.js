$(document).ready(function() {
	
	if ($("#alertSuccess").text().trim() == "") {
	    $("#alertSuccess").hide();		
	}
	$("#alertError").hide();
	
	$.ajax(
			{
				url: 'localhost:8080/Appointment/AppointmentService/appointment/bypatient',
	            type: 'post',
	            dataType: 'json',
	            data: JSON.stringify({
	            	"patientId":"1"
	            }),
	            contentType: 'application/json',
	            success: function (response, status) {
	            	onTabalDataload(response.responseText, status); 
	            }
	            
			});
});

function onTabalDataload(response, status) {
	console.log(response);
}

