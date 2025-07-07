let element = document.getElementById("quantity");

function increment(){
	element.innerText++;
}

function decrement(){
	if(element.innerText > 0){
		
		element.innerText--;
	}
	
}

function neutral(){
	element.innerText=0;
}