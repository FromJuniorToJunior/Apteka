import React, {useState, useRef} from 'react'
import '../styles/styles.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Link, useNavigate} from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCartShopping } from '@fortawesome/free-solid-svg-icons'
import '../styles/styles_modal.css';
import '../styles/styles_navbar.css';


function Navbar() {

    const [styleNavbarDropDown, setStyleNavbarDropDown] = useState({'display':'none'});
    const [visibility, setVisibility] = useState("modal_medicine_item");
    const czyDownbarOtwarty  = useRef(null);
    let navigate = useNavigate();
   
    var czyZalogowany = sessionStorage.getItem('token');

    function wyloguj(){
        sessionStorage.removeItem('token');
        sessionStorage.removeItem('token_id');
        navigate('/');
        window.location.reload();
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
                    
                    <button className="btn btn-outline-dark"  onClick={()=>{setVisibility("modal_medicine_item_open_cart");}}>
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
                            <th scope="col">First</th>
                            <th scope="col">Last</th>
                            <th scope="col">Handle</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                            <th scope="row">1</th>
                            <td>Mark</td>
                            <td>Otto</td>
                            <td>@mdo</td>
                            </tr>
                            <tr>
                            <th scope="row">2</th>
                            <td>Jacob</td>
                            <td>Thornton</td>
                            <td>@fat</td>
                            </tr>
                            <tr>
                            <th scope="row">3</th>
                            <td>Larry</td>
                            <td>the Bird</td>
                            <td>@twitter</td>
                            </tr>
                        </tbody>
                    </table> 
                    Razem:
                    <br/>
                    <button className='btn btn-dark' style={{marginLeft:'45%'}}>Kupuje</button>
                    
                </div>
            </div>
        </div> 
    </>
  )
}

export default Navbar
