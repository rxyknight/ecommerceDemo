var asyncRequest; // variable to hold XMLHttpRequest object

function status(url) {
	try {
		asyncRequest = new XMLHttpRequest(); // create request object
		// register event handler

		asyncRequest.onreadystatechange = processResponse;
		asyncRequest.open('GET', url, true); // prepare the request
		asyncRequest.send(null); // send the request with no body message
	} // end try
	catch (exception) {
		alert('Request Failed');
	} // end catch
} // end function addToCartJS

function processResponse() {

	if (asyncRequest.readyState == 4 && asyncRequest.status == 200) {
		// alert(asyncRequest.responseText);

		var data = eval("(" + asyncRequest.responseText + ")"); // evaluate this
																// to an array
		var statusCell = document.getElementById(data.orderId);
		statusCell.firstChild.nodeValue = data.status;

		var confirmCell = document.getElementById("confirmation");
		

		// confirmCell.appendChild(document.createTextNode("The
		// Order"+data.orderId+" status has been update!"));
		
		confirmCell.appendChild(document.createTextNode("The Order"+data.orderId+" status has been update!"));
		//confirmCell.firstChild.nodeValue = data.orderId;

		asynRequest = null; // clear it
	}
}

function orderDetail(url) {
	try {
		asyncRequest = new XMLHttpRequest(); // create request object
		// register event handler

		asyncRequest.onreadystatechange = processResponse1;
		asyncRequest.open('GET', url, true); // prepare the request
		asyncRequest.send(null); // send the request with no body message
	} // end try
	catch (exception) {
		alert('Request Failed');
	} // end catch
} // end function addToCartJS

function processResponse1() {

	if (asyncRequest.readyState == 4 && asyncRequest.status == 200) {
		// alert(asyncRequest.responseText);
		var order = eval("(" + asyncRequest.responseText + ")"); // evaluate
																	// this to
																	// an array
		// var totalPrice = 0.0;
		var detailContent = document.getElementById("detailcontent");
		while (detailContent.lastChild)
			detailContent.removeChild(detailContent.lastChild);
		// clear everything
		var thead = document.createElement("thead");
		var newRow = document.createElement("tr");
		var newCell = document.createElement("th");
		newCell.appendChild(document.createTextNode("Custom:"));
		newRow.appendChild(newCell);
		newCell = document.createElement("th");
		newCell.appendChild(document.createTextNode(order.customer));
		newRow.appendChild(newCell);
		thead.appendChild(newRow);
		newRow = document.createElement("tr");
		newCell = document.createElement("th");
		newCell.appendChild(document.createTextNode("Delivery Address:"));
		newRow.appendChild(newCell);
		newCell = document.createElement("th");
		newCell.appendChild(document.createTextNode(order.address));
		newRow.appendChild(newCell);

		newRow = document.createElement("tr");
		newCell = document.createElement("th");
		newCell.appendChild(document.createTextNode("Item Title"));
		newRow.appendChild(newCell);
		newCell = document.createElement("th");
		newCell.appendChild(document.createTextNode("Item Quantity"));
		newRow.appendChild(newCell);
		newCell = document.createElement("th");
		newCell.appendChild(document.createTextNode("Item Description"));
		newRow.appendChild(newCell);
		newCell = document.createElement("th");
		newCell.appendChild(document.createTextNode("Picture"));
		newRow.appendChild(newCell);
		newCell = document.createElement("th");
		newCell.appendChild(document.createTextNode("Item Price"));
		newRow.appendChild(newCell);
		thead.appendChild(newRow);
		detailContent.appendChild(thead); // end thead

		var items = order.items;
		var tbody = document.createElement("tbody");
		for (var i = 0; i < items.length; i++) {
			newRow = document.createElement("tr");
			newCell = document.createElement("td");
			newCell
					.appendChild(document
							.createTextNode(items[i].product.title));
			newRow.appendChild(newCell);
			newCell = document.createElement("td");
			newCell.appendChild(document.createTextNode(items[i].quantity));
			newRow.appendChild(newCell);
			newCell = document.createElement("td");
			newCell.appendChild(document
					.createTextNode(items[i].product.description));
			newRow.appendChild(newCell);
			newCell = document.createElement("td");
			var img = new Image();
			img.src = items[i].product.imgUrl;
			img.alt = "image not available";
			// newCell.appendChild(document.createTextNode("<img
			// src="+items[i].product.imgUrl+"alt=image not available>"));
			newCell.appendChild(img);
			newRow.appendChild(newCell);
			newCell = document.createElement("td");
			newCell
					.appendChild(document
							.createTextNode(items[i].product.price));
			newRow.appendChild(newCell);
			tbody.appendChild(newRow);
		}
		detailContent.appendChild(tbody); // end tbody

		var tfoot = document.createElement("tfoot");
		newRow = document.createElement("tr");
		newCell = document.createElement("th");
		newCell.appendChild(document.createTextNode("Total Price:"));
		newRow.appendChild(newCell);
		newCell = document.createElement("th");
		newCell.appendChild(document.createTextNode("$" + order.totalPrice));
		newRow.appendChild(newCell);
		tfoot.appendChild(newRow);
		newRow = document.createElement("tr");
		newCell = document.createElement("th");
		newCell.appendChild(document.createTextNode("Shipping Cost:"));
		newRow.appendChild(newCell);
		newCell = document.createElement("th");
		newCell.appendChild(document.createTextNode("$" + order.shippingCost));
		newRow.appendChild(newCell);
		tfoot.appendChild(newRow);
		newRow = document.createElement("tr");
		newCell = document.createElement("th");
		newCell.appendChild(document.createTextNode("Final Cost:"));
		newRow.appendChild(newCell);
		newCell = document.createElement("th");
		newCell.appendChild(document.createTextNode("$" + order.finalCost));
		newRow.appendChild(newCell);
		tfoot.appendChild(newRow);

		detailContent.appendChild(tfoot);// end tfoot

		asynRequest = null; // clear it
	}
}
