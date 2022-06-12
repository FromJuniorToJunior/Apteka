import React, {useState, useRef} from 'react'
import { useNavigate } from 'react-router-dom';
import '../styles/styles_Profile.css';



function Profile() {

  const [data, setData] = React.useState([]);

  const [city, setCity] = React.useState("");
  const [country, setCountry] = React.useState("");
  const [street, setStreet] = React.useState("");
  const [bnumer, setBnumer] = React.useState("");
  const [lnumber, setLnumber] = React.useState("");
  
  

  React.useEffect(() => {
    const url = "http://localhost:8081/user/user/session";
    
    fetch(url,{
      method: 'GET',
      headers:{
        Accept: 'application/json',
        'Content-Type': 'application/json',
        'Authorization': sessionStorage.getItem('token')
      }
  })
      .then((response) => response.json())
      .then((json) =>{
        console.log(json);
         setData(json);
         setCity(json['address'].city);
         setStreet(json['address'].street);
         setCountry(json['address'].country);
        setBnumer(json['address'].bnumer);
         setLnumber(json['address'].lnumber);
      }).catch((error) => console.log(error));
      
  }, []);

  let navigate = useNavigate();

  React.useEffect(
    ()=>{
          if((sessionStorage.getItem('token') == false) || (sessionStorage.getItem('token') == null)){
              navigate('/logIn')
          }
    }
    ,[])      

  const [hasloZakryte, setHasloZakryte] = useState("pokaż");
  // state odpowiadajace za wyswietlanie modalow 
  const [visibility, setVisibility]= useState("modal_medicine_item");
  const [visibility2, setVisibility2]= useState("modal_medicine_item");

 
  return (
    <div className='profile'>
      <div className='container'>
        <div className='row'>
            <div className="col-md-6">
              <div className="card2 mb-3">
                <div className="card2-body">
                  <div className="row">
                    <div className="col-sm-3">
                      <h6 className="mb-0">Nick</h6>
                    </div>
                    <div className="col-sm-9 text-secondary">
                      {data.nickname}
                    </div>
                  </div>
                  <hr></hr>
                  <div className="row">
                    <div className="col-sm-3">
                      <h6 className="mb-0">Hasło</h6>
                    </div>
                    <div className="col-sm-9 text-secondary">
                      <div className='pokaz_haslo' onClick={()=>{hasloZakryte == "pokaż"  ?  setHasloZakryte("haslo1234567") : setHasloZakryte("pokaż")}}>{hasloZakryte}</div>
                    </div>
                  </div>
                  <hr></hr>
                  <div className="row">
                    <div className="col-sm-3">
                      <h6 className="mb-0">Email</h6>
                    </div>
                    <div className="col-sm-9 text-secondary">
                     {data.email}
                    </div>
                  </div>
                  <hr></hr>
                  <div className="row">
                    <div className="col-sm-3">
                      <h6 className="mb-0">Imię</h6>
                    </div>
                    <div className="col-sm-9 text-secondary">
                      {data.name}
                    </div>
                  </div>
                  <hr></hr>
                  <div className="row">
                    <div className="col-sm-3">
                      <h6 className="mb-0">Nazwisko</h6>
                    </div>
                    <div className="col-sm-9 text-secondary">
                      {data.surname}
                    </div>
                  </div>
                  <hr></hr>
                  <div className="row">
                    <div className="col-sm-3">
                      <h6 className="mb-0">Wiek</h6>
                    </div>
                    <div className="col-sm-9 text-secondary">
                      {data.age}
                    </div>
                  </div>
                  <hr></hr>
                  <div className="row">
                    <div className="col-sm-12">
                    <button className="btn btn-info " onClick={()=>{setVisibility("modal_medicine_item_open")}} target="__blank" >Edit</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>   

            <div className="col-md-6">
              <div className="card2 mb-3">
                <div className="card2-body">
                  <div className="row">
                    <div className="col-sm-3">
                      <h6 className="mb-0">Kraj</h6>
                    </div>
                    <div className="col-sm-9 text-secondary">
                      {country}
                    </div>
                  </div>
                  <hr></hr>
                  <div className="row">
                    <div className="col-sm-3">
                      <h6 className="mb-0">Miasto</h6>
                    </div>
                    <div className="col-sm-9 text-secondary">
                      {city}
                    </div>
                  </div>
                  <hr></hr>
                  <div className="row">
                    <div className="col-sm-3">
                      <h6 className="mb-0">Ulica</h6>
                    </div>
                    <div className="col-sm-9 text-secondary">
                    {street}
                    </div>
                  </div>
                  <hr></hr>
                  <div className="row">
                    <div className="col-sm-3">
                      <h6 className="mb-0">Nr bloku</h6>
                    </div>
                    <div className="col-sm-9 text-secondary">
                      {bnumer}
                    </div>
                  </div>
                  <hr></hr>
                  <div className="row">
                    <div className="col-sm-3">
                      <h6 className="mb-0">Nr mieszkania</h6>
                    </div>
                    <div className="col-sm-9 text-secondary">
                      {lnumber}
                    </div>
                  </div>
                  <hr></hr>
                  <div className="row">
                    <div className="col-sm-3">
                      <h6 className="mb-0">Telefon</h6>
                    </div>
                    <div className="col-sm-9 text-secondary">
                      {data.phone}
                    </div>
                  </div>
                  <hr></hr>
                  <div className="row">
                    <div className="col-sm-12">
                      <button className="btn btn-info " onClick={()=>{setVisibility2("modal_medicine_item_open")}} target="__blank" >Edit</button>
                    </div>
                  </div>
                </div>
              </div>
            </div> 
        </div>  
        {/* modal 1 */}
        <div  className={visibility}>
                <div className="modal-content_medicine_item" style={{width:'30%'}}>
                <span className="close" onClick={()=>{setVisibility("modal_medicine_item")}} >&times;</span>
                <br></br>
                <form>
                      <div className="form-group input_area">
                          <label>Nick:</label>
                          <input type="text" className="profile_input" defaultValue={data.nickname}  />
                      </div>

                      <div className="form-group input_area">
                          <label>Hasło:</label>
                          <input type="password" className="profile_input" />
                      </div>

                      <div className="form-group input_area">
                          <label>Email:</label>
                          <input type="email" className="profile_input" defaultValue={data.email}/>
                      </div>

                      <div className="form-group input_area">
                          <label>Imię:</label>
                          <input type="text" className="profile_input" defaultValue={data.name} />
                      </div>

                      <div className="form-group input_area">
                          <label>Nazwisko:</label>
                          <input type="text" className="profile_input"  defaultValue={data.surname}/>
                      </div>

                      <div className="form-group input_area">
                          <label>Wiek:</label>
                          <input type="number" className="profile_input" defaultValue={data.age} />
                      </div>

                      <button className="btn btn-info" style={{marginTop:'5px'}} target="__blank" >Zmień dane</button>
                </form>
                      
                
                </div>
        </div> 
        {/* modal 2 */}
        <div  className={visibility2}>
                <div className="modal-content_medicine_item" style={{width:'30%'}}>
                <span className="close" onClick={()=>{setVisibility2("modal_medicine_item")}} >&times;</span>
                <br></br>
                  <form>
                        <div className="form-group input_area">
                            <label>Kraj:</label>
                            <input type="text" className="profile_input"  defaultValue={country}/>
                        </div>

                        <div className="form-group input_area">
                            <label>Miasto:</label>
                            <input type="text" className="profile_input"  defaultValue={city}/>
                        </div>

                        <div className="form-group input_area">
                            <label>Ulica:</label>
                            <input type="text" className="profile_input" defaultValue={street} />
                        </div>

                        <div className="form-group input_area">
                            <label>Nr bloku:</label>
                            <input type="number" className="profile_input" defaultValue={bnumer} />
                        </div>

                        <div className="form-group input_area">
                            <label>Nr mieszkania:</label>
                            <input type="number" className="profile_input" defaultValue={lnumber} />
                        </div>

                        <div className="form-group input_area">
                            <label>Telefon:</label>
                            <input type="number" className="profile_input" defaultValue={data.phone} />
                        </div>

                        <button className="btn btn-info" style={{marginTop:'5px'}} target="__blank" >Zmień dane</button>
                  </form>
                </div>
        </div> 

      </div>              
    </div>
  )
}

export default Profile
