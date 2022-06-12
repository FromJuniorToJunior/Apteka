import React,{useRef, useState} from 'react'
import '../styles/styles.css';
import {  useNavigate } from "react-router-dom";
import '../styles/styles_registration.css';



function Registration() {

    const [zarejestruj_sie, setZarejestruj_sie] = useState("Zarejestruj się");
    const f_ref = useRef(null);
    const s_ref = useRef(null);
    const t_ref = useRef(null);

    const [nick, setNick] = useState("");
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");

    const [name, setName] = useState("");
    const [surname, setSurname] = useState("");
    const [age, setAge] = useState("");
    const [gender, setGender] = useState("");
    const [phone, setPhone] = useState("");

    const [country, setCountry] = useState("");
    const [city, setCity] = useState("");
    const [street, setStreet] = useState("");
    const [b_number, setB_number] = useState("");
    const [m_number, setM_number] = useState("");
    // const [bearerToken, setBearerToken] = useState("");
    var bearerToken;
    let navigate = useNavigate();
    let czy_udalo_sie_zarejestrowac ;
    
    async function registrationKEYCLOCK(){
        const data_form = new URLSearchParams();
        data_form.append("grant_type", "password");
        data_form.append("client_id", "admin-cli");
        data_form.append("client_secret","iOIcYYdRTReNoQ3IZjBHxsDNhpuU3yLv");
        data_form.append("username","admin");
        data_form.append("password","admin");
        fetch('http://localhost:8080/auth/realms/master/protocol/openid-connect/token', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: data_form
        }).then((response) => response.json())
        .then((json) => {
                //   setBearerToken(json.access_token);
                bearerToken = 'bearer '+ json.access_token;
                registrationACCKEYCLOAK();
                   
          
        });
    }
async function registrationACCKEYCLOAK(){
        fetch('http://localhost:8080/auth/admin/realms/Apteka/users', {
        method: 'POST',
        headers: {
                    'Authorization': bearerToken,
                    'Content-Type': 'application/json'
        },
        body: JSON.stringify(
            {
                "firstName":name,
                "lastName":surname,
                "email":email,
                "enabled":"true", 
                "username":nick,
                "groups" : ["users"],
                "credentials":[ 
                  { 
                     "temporary": false,
                     "type":"password",
                     "value":password
                  }
               ]
              
            }
            
            )
                
        }).then((response) => response)
        .then((response) => {
            registration();
        });
    }
    async function registration(){
        fetch('http://localhost:8081/user/create', {
        method: 'POST',
        headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            
                nickname : nick,
                name : name,
                surname : surname,
                gender : gender,
                age : age,
                email : email,
                password : password,
                phone : phone,
                address : {
                    country : country,
                    city : city,
                    street : street,
                    bnumer : b_number,
                    lnumber : m_number
                }
            })
                
        }).then((response) => response)
        .then((response) => {
                    console.log(response.ok);
                    czy_udalo_sie_zarejestrowac = response.ok;
                    if(czy_udalo_sie_zarejestrowac){
                        // navigate('/logIn');
                        // czy_po_rejestracji =true;
                        sessionStorage.setItem('czyUdaloSieZalogowac', true);
                        navigate('/logIn');
                    }else{
                        // TODO zrobic obsluge bledow
                        // f_ref.current.style.display = 'inherit';
                        // t_ref.current.style.display='none';
                        // setZarejestruj_sie('Nie udało się zarejestrować! Spróbuj ponownie');
                        alert("Nie udało sie zalogowac");
                        f_ref.current.style.display = 'inherit';
                        t_ref.current.style.display='none';
                    }
        });
        
    }



  return (
    <div className='registration'>
      <section className='py-5'>
            <div className="container px-4 px-lg-5 center">
                
                    <div className='first_formulage' ref={f_ref}>
                        
                        <h3>{zarejestruj_sie}</h3>
                        <div className='box_element'>
                            <div className='box' style={{backgroundColor: 'rgb(38, 145, 217,1)'}}>1</div>
                            <div className='box' onClick={()=>{s_ref.current.style.display = 'inherit'; t_ref.current.style.display='none';f_ref.current.style.display = 'none';}}>2</div>
                            <div className='box' onClick={()=>{s_ref.current.style.display = 'none'; t_ref.current.style.display='inherit';f_ref.current.style.display = 'none';}}>3</div>
                            <div style={{'clear':'both'}}></div>
                        </div>
                        <div className="form-group input_area">
                            <label>Nick:</label>
                            <input type="text" className="form-control" onChange={(e)=>setNick(e.target.value)} />
                        </div>

                        <div className="form-group input_area">
                            <label>Hasło:</label>
                            <input type="password" className="form-control"  onChange={(e)=>setPassword(e.target.value)}/>
                        </div>

                        <div className="form-group input_area">
                            <label>Powtórz hasło:</label>
                            <input type="password" className="form-control"  />
                        </div>

                        <div className="form-group input_area">
                            <label>Email:</label>
                            <input type="email" className="form-control age" onChange={(e)=>setEmail(e.target.value)}/>
                        </div>

                        <button type="button" onClick={()=>{ f_ref.current.style.display = 'none'; s_ref.current.style.display='inherit'}} className="btn btn-dark btn-lg btn-block" >Dalej</button>
                    </div>
                    <div className='second_formulage' ref={s_ref}>
                            <h3>Dane</h3>
                            <div className='box_element'>
                                <div className='box'  onClick={()=>{s_ref.current.style.display = 'none'; t_ref.current.style.display='none';f_ref.current.style.display = 'inherit';}}>1</div>
                                <div className='box'  style={{backgroundColor: 'rgb(38, 145, 217,1)'}}>2</div>
                                <div className='box' onClick={()=>{s_ref.current.style.display = 'none'; t_ref.current.style.display='inherit';f_ref.current.style.display = 'none';}}>3</div>
                                <div style={{'clear':'both'}}></div>
                            </div>

                            <div className="form-group input_area">
                                <label>Imię:</label>
                                <input type="text" className="form-control" onChange={(e)=>setName(e.target.value)} />
                            </div>

                            <div className="form-group input_area">
                                <label>Nazwisko:</label>
                                <input type="text" className="form-control"  onChange={(e)=>setSurname(e.target.value)}/>
                            </div>

                            <div className="form-group input_area">
                                <label>Wiek:</label>
                                <input type="number" className="form-control"  onChange={(e)=>setAge(e.target.value)}/>
                            </div>

                            <div className="form-group">
                                <div className="custom-control custom-checkbox">
                                    <input type="checkbox" className="custom-control-input" id="customCheck1" onChange={(e)=>setGender("male")}/>
                                    <label className="custom-control-label" htmlFor="customCheck1">Mężczyzna</label>
                                    <input type="checkbox" className="custom-control-input" id="customCheck2" style={{marginLeft:'5px'}} onChange={(e)=>setGender("female")}/>
                                    <label className="custom-control-label" htmlFor="customCheck2">Kobieta</label>
                                </div>
                            </div>


                            <div className="form-group input_area">
                                <label>Telefon:</label>
                                <input type="text" className="form-control" onChange={(e)=>setPhone(e.target.value)} />
                            </div>
                            <button type="button" onClick={()=>{ s_ref.current.style.display = 'none'; t_ref.current.style.display='inherit'}} className="btn btn-dark btn-lg btn-block" >Dalej</button>

                    </div>
                    <div className='third_formulage' ref={t_ref}>
                            <h3>Adres</h3>
                            <div className='box_element'>
                                <div className='box'  onClick={()=>{s_ref.current.style.display = 'none'; t_ref.current.style.display='none';f_ref.current.style.display = 'inherit';}}>1</div>
                                <div className='box'  onClick={()=>{s_ref.current.style.display = 'inherit'; t_ref.current.style.display='none';f_ref.current.style.display = 'none';}}>2</div>
                                <div className='box' style={{backgroundColor: 'rgb(38, 145, 217,1)'}}>3</div>
                                <div style={{'clear':'both'}}></div>
                            </div>

                            <div className="form-group input_area">
                                <label>Kraj:</label>
                                <input type="text" className="form-control"  onChange={(e)=>setCountry(e.target.value)}/>
                            </div>

                            <div className="form-group input_area">
                                <label>Miasto:</label>
                                <input type="text" className="form-control" onChange={(e)=>setCity(e.target.value)} />
                            </div>

                            <div className="form-group input_area">
                                <label>Ulica:</label>
                                <input type="text" className="form-control"  onChange={(e)=>setStreet(e.target.value)}/>
                            </div>


                            <div className="form-group input_area">
                                <label>Nr bloku:</label>
                                <input type="number" className="form-control"  onChange={(e)=>setB_number(e.target.value)}/>
                            </div>

                            <div className="form-group input_area">
                                <label>Nr mieszkania:</label>
                                <input type="number" className="form-control" onChange={(e)=>setM_number(e.target.value)} />
                            </div>

                            <button className='btn btn-dark btn-lg btn-block' onClick={registrationKEYCLOCK} >Zajerestruj się</button>

                    </div>
                
                
            </div>            

           
        </section> 
    </div>
  )
}

export default Registration
