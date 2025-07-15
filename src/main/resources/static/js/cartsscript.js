function increment(productid){
	let element = document.getElementById("quantity-"+productid);
	if(element.innerText < 10){
		
		element.innerText++;
		fetch("/addproducttocart",{
			method:"POST",
			headers:{
				"Content-Type":"application/json"
			},
			body:JSON.stringify({
				productid:productid
			})
			
		})
	}
	
}

function decrement(productid){
	let element = document.getElementById("quantity-"+productid);
	if(element.innerText > 0){
		
		element.innerText--;
		fetch("/removeproductfromcart",{
			method:"POST",
			headers:{
				"Content-Type":"application/json"
			},
			body:JSON.stringify({
				productid:productid
			})
		})
	}
	
}

function openmodal(){
	document.getElementById("paymentmodal").style.display="block";
}

function closemodal(){
	document.getElementById("paymentmodal").style.display="none";
}

function paynow(){
	let method=document.getElementById("paymentmethod").value;
	alert("payment success:"+method);
	closemodal();
}