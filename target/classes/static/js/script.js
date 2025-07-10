let element = document.getElementById("quantity");

function increment(productid){
	
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

function decrement(){
	if(element.innerText > 0){
		
		element.innerText--;
	}
	
}

function neutral(){
	element.innerText=0;
}