
function $(id){
    return document.getElementById(id).value;
}

let json;
let xhr = new wrapperFun();

function loginMe(){
    let user = $('userin').trim();
    let pass = $('passin').trim();

    json={
        "password":pass,
        "user":user
    }

    xhr.post('./Login', json, function(data){
        if(data.status == 200){
            document.location.replace("http://localhost:8080/MangaReaderRA/Home.html")
            alert('You have logged in correctly!');
        }else{
            alert('There was an error');
            location.reload();
        }
    });
}

function LogOut(){
	console.log("llamada a logout");
	xhr.get('./Logout', {}, function (data){
		resp = JSON.stringify(data.status);
		if(data.status == "200"){
			alert("You have logged out");
			location.reload();
		}else{
			alert("Error closing the session");
			location.reload();
		}
	});
	
}
