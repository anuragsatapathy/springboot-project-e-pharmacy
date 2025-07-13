
function increment(productid){
	let element = document.getElementById("quantity-"+productid);
	if(element.innerText < 10){
		
		element.innerText++;
		updatecartsquantity(1)
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
		updatecartsquantity(-1)
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

function updatecartsquantity(quantity){
	
	let element = document.getElementById("cartsquantity");
	element.innerText = parseInt(element.innerText)+quantity;
}
