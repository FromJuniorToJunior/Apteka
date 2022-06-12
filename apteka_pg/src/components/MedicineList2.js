import React, {useState, useRef} from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faPlus, faMinus} from '@fortawesome/free-solid-svg-icons'
import '../styles/styles_modal.css'

function MedicineList({image, name, price, description}) {
    const [liczba_sztuk, setLiczba_sztuk] = useState(0);
    const [cena_zl, setCena_zl] = useState(0);
    const [visibility, setVisibility] = useState("modal_medicine_item");

   const cenaProduktu = useRef(null);
   const liczbaSztuk = useRef(null);
   
  
  return (
        <>
            <div className="col mb-5">
                <div className="card h-100">
                    <img className="card-img-top" src={`data:image/jpeg;base64,${image}`} style={{width:'100%',height:'100%'}} alt="..." />
                    <div className="card-body p-4">
                        <div className="text-center">
                            
                            <h5 className="fw-bolder">{name}</h5>
                            
                            <span ref={cenaProduktu}>{price}</span> zł
                            <br></br>

                            <FontAwesomeIcon style={{'color':'green','marginRight':'4px','cursor':'pointer'}} icon={faPlus} onClick={()=>{setLiczba_sztuk(x => x+1);
                                setCena_zl(parseFloat(parseFloat(cenaProduktu.current.textContent) * (parseFloat(liczbaSztuk.current.textContent)+1)).toFixed(2));
                                document.querySelectorAll('.badge')[0].textContent = parseInt(document.querySelectorAll('.badge')[0].textContent) +1}}/>

                                <span ref={liczbaSztuk}>{liczba_sztuk}</span> | {cena_zl} zł
                                
                            <FontAwesomeIcon style={{'color':'red','marginLeft':'4px','cursor':'pointer'}} icon={faMinus}  onClick={()=>{setLiczba_sztuk(x => x!=0 ? x-1 : x+1);
                                setCena_zl(x=> x!=0 ? parseFloat(cena_zl-parseFloat(cenaProduktu.current.textContent)).toFixed(2): parseFloat(cenaProduktu.current.textContent).toFixed(2) );
                                document.querySelectorAll('.badge')[0].textContent = parseInt(document.querySelectorAll('.badge')[0].textContent) -1}}/>

                        </div>
                    </div>
                    <div className="card-footer p-4 pt-0 border-top-0 bg-transparent">
                        <div className="text-center"><button onClick={()=>{setVisibility("modal_medicine_item_open")}}  className="btn btn-outline-dark mt-auto">Więcej...</button></div>
                    </div>
                </div>
            </div>
            <div  className={visibility}>
                <div className="modal-content_medicine_item">
                <span className="close" onClick={()=>{setVisibility("modal_medicine_item")}} >&times;</span>
                
                <h3 style={{textAlign:'center'}}>{name}</h3>
                <div>
                
                </div>
                
         
                </div>
            </div> 
        </> 
  )
}

export default MedicineList
