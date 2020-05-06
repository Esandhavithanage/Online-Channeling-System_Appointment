$(document).on("click", ".btnUpdate", function(event) { 
	
	$("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIdUpdate').val());
	$("#hostpitalName").val($(this).closest("tr").find('#hidhidUpdate').val());
	$("#docterName").val($(this).closest("tr").find('#hiddidUpdate').val());
	$("#description").val($(this).closest("tr").find('td:eq(5)').text());
	$("#date").val($(this).closest("tr").find('td:eq(1)').text());

	
	$("#alertSuccess").text().trim() == "Data Retrived"

});



$(document).ready(function () {
	 document.forms['form'].reset();
	 
});

$(document).ready(function() { 

	if($("#alertSuccess").text().trim() == ""){
		$("#alertSuccess").hide();
	}
	
	$("#alertError").hide(); 
	
});

$(document).on("click", "#btnSave", function(event) { 
	
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text(""); 
	$("#alertError").hide(); 
	
	var status = validateItemForm(); 
	
	if (status != true)  {  
		$("#alertError").text(status);  
		$("#alertError").show();  
		return; 	
		} 
	
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT"; 
	
	$.ajax( { 
		url : "appointmentAPI", 
		type : type,  
		data : $("#detailform").serialize(),  
		dataType : "text",  
		complete : function(response, status) 
		{   
			console.log(response);
			console.log(response.responseText);
			onItemSaveComplete(response.responseText, status);  
		
		} 
	}); 
	
});

function onItemSaveComplete(response, status) {  
	console.log(response);
	var resultSet = JSON.parse(response); 
	 
	if (resultSet.status.trim() == "success") {  
		
		$("#alertSuccess").text("Successfully saved.");  $("#alertSuccess").show(); 
		 
		 $("#divItemsGrid").html(resultSet.data); 
	
	} else if (resultSet.status.trim() == "error") 
	
	{  
		$("#alertError").text(resultSet.data); 
		$("#alertError").show(); 
		
	}
	else if (status == "error") {
		
		$("#alertError").text("Error while saving.");  
		$("#alertError").show(); 
	}
	 else 
	 {  
		 $("#alertError").text("Unknown error while saving.."); 
		 $("#alertError").show(); 
	 }
	
	$("#hidItemIDSave").val(""); 
	$("#form")[0].reset(); 
}


$(document).on("click", ".btnRemove", function(event) { 
	
	console.log(event);
	$.ajax( { 
		url : "appointmentAPI",   
		type : "DELETE",   
		data : "AppointmentID=" + $(this).val(),   
		dataType : "text",   
		complete : function(response, status) 
		{   
			console.log(response);
			console.log(response.responseText);
			onItemDeleteComplete(response.responseText, status);  
		
		} 
	}); 
	
});

function onItemDeleteComplete(response, status) {  
	
	var resultSet = JSON.parse(response); 
	 
	if (resultSet.status.trim() == "success") {  
		
		$("#alertSuccess").text("Successfully deleted."); 
		$("#alertSuccess").show(); 
		 
		 $("#divItemsGrid").html(resultSet.data); 
	
	} else if (resultSet.status.trim() == "error") 
	
	{  
		$("#alertError").text(resultSet.data); 
		$("#alertError").show(); 
		
	}
	else if (status == "error") {
		
		$("#alertError").text("Error while deletingdeleting.");  
		$("#alertError").show(); 
	}
	 else 
	 {  
		 $("#alertError").text("Unknown error while deleting.."); 
		 $("#alertError").show(); 
	 }
	
}



	
	

function validateItemForm() {  
	
	if ($("#docterName").val().trim() == 0) {  
		
		return "Please Select a docter";
	} 
	
	if ($("#hostpitalName").val().trim() == 0) {  
		 
		return "Please Select a hostpital";
	} 
	
	if ($("#description").val().trim() == "") {  

	return "Please Enter description";
	} 
	
	if ($("#date").val().trim() == "dd/mm/yyyy") {  

		return "Please Select Preferd Date";
	} 

	
	
	return true; 
	 
	}
