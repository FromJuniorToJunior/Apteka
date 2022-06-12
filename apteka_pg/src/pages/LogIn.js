import React, { Component, useEffect, useState } from "react";
import '../styles/styles.css';
import '../styles/styles_LogIn.css';
import {Link, useNavigate, useSearchParams} from 'react-router-dom'


function LogIn() {

    const [email, setEmail] = useState("");
    const [token, setToken] = useState("");
    const [password, setPassword] = useState("");
    const [searchParams, setParams] = useSearchParams();
    const status = searchParams.get("status");
    let czy_po_rejestracji = sessionStorage.getItem("czyUdaloSieZalogowac");
    let navigate = useNavigate();

    async function login(){
        
        
        //const data_form = new FormData();
        const data_form = new URLSearchParams();
        data_form.append("grant_type", "password");
        data_form.append("client_id", "apteka-application");
        data_form.append("client_secret", "iOIcYYdRTReNoQ3IZjBHxsDNhpuU3yLv");
        data_form.append("username", email);
        data_form.append("password", password);
        fetch("http://localhost:8080/auth/realms/Apteka/protocol/openid-connect/token",{
            method: 'POST',
            headers:{
                'Content-Type': 'application/x-www-form-urlencoded'
            }, 
            body: data_form
        }).then((response) => response.json())
        .then((json) => {
            if(json.access_token!=undefined){
                sessionStorage.setItem('token','bearer '+json.access_token);
                wyslanieAutoryzacji();
                navigate('/');
            }else {
                navigate('/logIn?status=niezalogowany');
                
            }
    });
    
    async function wyslanieAutoryzacji(){
        fetch("http://localhost:8081/main/auth",{
            method: 'GET',
            headers:{
                'Authorization': sessionStorage.getItem('token')
            }
        }).then((response) => response)
        .then((response) => {
            console.log( response.body)
            navigate('/');
        });
    }
       
        
        


        // fetch("http://localhost:8081/main/auth",{
        //         method: 'GET',
        //         headers:{
        //             'Authorization': `bearer ${token}`,
        //             'Content-Type': 'application/x-www-form-urlencoded'
        //         }

        // }).then((response) => response)
        // .then((response) => console.log(response));
        
       

       
        //sesja zrobiona ja poprawic
        // sessionStorage.setItem('token', true);
        // sessionStorage.setItem('token_id', 4);
        // navigate('/');
        // window.location.reload();
    }

    React.useEffect(
        ()=>{
            sessionStorage.removeItem('czyUdaloSieZalogowac');
        }
    ,[])

  return (
    <div className='logIn'>
      <section className='py-5'>
            <div className="container px-4 px-lg-5 center">
            {/* <form> */}
            {czy_po_rejestracji ?
                <h4 className="rejestracja_przebiegla_pomyslnie">Rejestracja przebiegła pomyślnie</h4>
            :null
            }    
            {status == "niezalogowany"?
            <><span style={{color: 'red', marginLeft: '6%'}}>Wprowadzono błędny login lub hasło</span></>
            : 
             null
}
                <h3>Zaloguj się</h3>
                
                <div className="form-group input_area">
                    <label>Email:</label>
                    <input type="text" className="form-control" placeholder="email"  onChange={(e)=>setEmail(e.target.value)}/>
                </div>

                <div className="form-group input_area">
                    <label>Password:</label>
                    <input type="password" className="form-control" placeholder="password"   onChange={(e)=>setPassword(e.target.value)} />
                </div>

                <div className="form-group">
                    <div className="custom-control custom-checkbox">
                        <input type="checkbox" className="custom-control-input" id="customCheck1" />
                        <label className="custom-control-label" htmlFor="customCheck1">Remember me</label>
                    </div>
                </div>

                <button  onClick={login} className="btn btn-dark btn-lg btn-block">Zaloguj</button>
                <p className="forgot-password text-right">
                    Nie masz konta?<Link  to="/registration"> Zarejestruj się</Link>
                </p>
                <br></br>
            {/* </form> */}
                        

            </div>
        </section> 
    </div>
  )
}

export default LogIn

