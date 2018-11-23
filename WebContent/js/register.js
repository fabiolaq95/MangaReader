function $(id){
    return document.getElementById(id).value;
}

let json;
let xhr = new wrapperFun();

function Register(){
    let name = $('user_name');
    let pass = $('user_pass');
    let user = $('user_username');
    let email = $('user_email');
    let Rpass = $('repeat_pass');

    let d = new Date();
    let date;
    let month = d.getMonth() + 1;

    date = `${d.getDate()}-${month}-${d.getFullYear()}`;
    
    if(pass != Rpass){
        alert("Passwords do not match");
        location.reload(true);
    }
    else{
        if(name != "" && pass != "" && user != "" && email != ""){
            json={
                "password":pass,
                "user":user,
                "name":name,
                "date":date,
                "email":email
            }

    xhr.post('./Signup', json, function(data){
        if(data.status == 200){
            alert('You have signed up correctly!');
            document.location.replace('http://localhost:8080/MangaReaderRA/Login.html');
        }else{
            alert('Sign up error');
            location.reload();
        }
    });

        }//End blank space if
        else{
            alert('You must complete the information')
        }
    }//End first else
}//End function
