import React, {useState, useRef} from 'react'
import '../styles/styles.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Link, useNavigate} from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCartShopping } from '@fortawesome/free-solid-svg-icons'
import '../styles/styles_modal.css';
import '../styles/styles_navbar.css';


function Navbar() {

    //rzeczy zmieniane: w Home  zmienic na 8081, w logIn wykomentowac,oraz caly navbar zmienony, medicine item też

    const [styleNavbarDropDown, setStyleNavbarDropDown] = useState({'display':'none'});
    const [isLoading, setIsLoading] = useState(true);
    const [cena_razem_hook, setCena_razem_hook] = useState(0);
    const [visibility, setVisibility] = useState("modal_medicine_item");
    const [arrayElement, setArray] = useState([]); //array z zamowieniami
    const [anxInOrdersJSON, setAnxInOrdersJSON] = useState([]); //JSON z zamowieniami
    const czyDownbarOtwarty  = useRef(null);
    
    let navigate = useNavigate();
   
    var czyZalogowany = sessionStorage.getItem('token');

    function wyloguj(){
        sessionStorage.removeItem('token');
        sessionStorage.removeItem('token_id');
        navigate('/');
        window.location.reload();
    }

    function wyswietlKoszyk(){
        setVisibility("modal_medicine_item_open_cart");
        var cena_za_sztuki = 0;
        //wyswietlanie ceny calego zamowienia POCZATEK
        for(var i=0; i<document.querySelectorAll(".cena_za_sztuk").length; i++){
                cena_za_sztuki += parseFloat(document.querySelectorAll(".cena_za_sztuk")[i].textContent);
        }    
        setCena_razem_hook(cena_za_sztuki);
        //wyswietlanie ceny calego zamowienia KONIEC

        //wyswietlanie zamowienia w liście POCZATEK
        var zamowienia_lista = [];
        var zamowienia_JSON = [];
        var zamowienie_id = 1;
        for(var i=0; i<document.querySelectorAll(".cena_za_sztuk").length; i++){    
                if(parseFloat(document.querySelectorAll(".sztuki")[i].textContent) > 0){
                    //alert(parseFloat(document.querySelectorAll(".sztuki")[i].textContent));
                    zamowienia_lista.push({"id": zamowienie_id, 
                    "name": document.querySelectorAll(".nazwa_leku")[i].textContent,
                    "liczba_sztuk" : document.querySelectorAll(".sztuki")[i].textContent,
                    "cena_za_leki" : document.querySelectorAll(".cena_za_sztuk")[i].textContent
                    });
                    zamowienia_JSON.push({
                        "anxieties" :{
                            "name": document.querySelectorAll(".nazwa_leku")[i].textContent
                        },
                        "amount" : parseInt(document.querySelectorAll(".sztuki")[i].textContent)
                    });
                    zamowienie_id +=1;
                }
        }    
        setArray(zamowienia_lista); 
        setAnxInOrdersJSON(zamowienia_JSON);
        //wyswietlanie zamowienia w liście KONIEC
    }

    React.useEffect(() => {
        if (arrayElement.length !== 0) {
          setIsLoading(false);
        }
        
      }, [arrayElement]);

    async function zlozZamowienie(){
        console.log(JSON.stringify({
            realized: "true",
            anxInOrders: anxInOrdersJSON

        }));
        fetch('http://localhost:8081/order/create/order',{
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
                'Authorization': sessionStorage.getItem('token')
            },
            body: JSON.stringify({
                realized: "true",
                anxInOrders: anxInOrdersJSON

            })
        }).then((response) => response)
        .then((response) => {console.log(response)});
    }
    

  return (
      <>
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <div className="container px-4 px-lg-5">
                <a className="navbar-brand" href="#!">Harmonia</a>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span className="navbar-toggler-icon"></span></button>
                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul className="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        <li className="nav-item"><Link className='nav-link active' aria-current="page" to="/">Menu</Link></li>
                        <li className="nav-item"><Link className='nav-link' to="/contact">Kontakt</Link></li>
                        {czyZalogowany ?   
                            <li className="nav-item dropdown"> 
                                <a className="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false" onClick={() => { czyDownbarOtwarty.current.style.display == 'none' ? setStyleNavbarDropDown({ 'display': 'block' }) : setStyleNavbarDropDown({ 'display': 'none' }); } }>Moje konto</a><ul className="dropdown-menu" aria-labelledby="navbarDropdown" style={styleNavbarDropDown} ref={czyDownbarOtwarty}>
                                    <li><Link className='dropdown-item' to="/profile">Profil</Link></li>
                                    <li><hr className="dropdown-divider" /></li>
                                    <li><Link className='dropdown-item' to="/history">Historia zakupów</Link></li>
                                    <li><a className="dropdown-item" href="#!" onClick={wyloguj}>Wyloguj</a></li>
                                </ul>
                            </li>
                        :
                            <li className="nav-item"><Link className='nav-link' to="/logIn">Zaloguj</Link></li>
                        }
                    </ul>
                    {czyZalogowany?
                    
                    <button className="btn btn-outline-dark"  onClick={wyswietlKoszyk}>
                        <FontAwesomeIcon icon={faCartShopping} />
                        Kosz
                        <span  className="badge bg-dark text-white ms-1 rounded-pill">0</span>
                    </button>
                    
                    :
                    <></>
                    }
                </div>
            </div>
        </nav>
        <div  className={visibility}>
            <div className="modal-content_medicine_item">
            <span className="close" onClick={()=>{setVisibility("modal_medicine_item")}} >&times;</span>
                <div>
                    <h3>
                        Twoje zakupy
                    </h3>
                    <table className="table table-striped">
                        <thead >
                            <tr>
                            <th scope="col">#</th>
                            <th scope="col">Nazwa leku</th>
                            <th scope="col">Ilość</th>
                            <th scope="col">Cena</th>
                            </tr>
                        </thead>
                        <tbody>
                        {isLoading ? (
                            <tr><td>Kosz jest pusty</td></tr>
                        ) : (
                            arrayElement.map((element, key)=>(
                              <tr>
                                 <th scope='row'>{key+1}</th>
                                 <td>{element.name}</td>
                                 <td>{element.liczba_sztuk}</td>
                                 <td>{element.cena_za_leki} zł</td>
                              </tr>
                            ))
                        )}
                        </tbody>
                    </table> 
                    <span>Razem: {cena_razem_hook} zł</span>
                    <br/>
                    <button className='btn btn-dark' onClick={zlozZamowienie} style={{marginLeft:'45%'}}>Kupuje</button>
                    
                </div>
            </div>
        </div> 
    </>
  )
}

export default Navbar
