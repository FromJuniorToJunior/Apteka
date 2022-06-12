import React from 'react'
import '../styles/styles_History.css';
import {useNavigate} from 'react-router-dom';

function History() {

  let navigate = useNavigate();
  const [isLoading, setIsLoading] = React.useState(true);
  const [data, setData] = React.useState([]);


  

  React.useEffect(()=>{
    if((sessionStorage.getItem('token') == false) || (sessionStorage.getItem('token') == null)){
        navigate('/logIn')
    }
    const url = "http://localhost:8081/order/get/session";//ZMIENIC NA 8081
    
    fetch(url,{
      method: 'GET',
      headers:{
        'Authorization': sessionStorage.getItem('token')
      } 
    })
      .then((response) => response.json())
      .then((json) => setData(json))
      .catch((error) => console.log(error));
    
    },[])   

  React.useEffect(() => {
    if (data.length !== 0) {
      setIsLoading(false);
    }
    console.log(data);
  }, [data]);     

  return (
    <div className='history'>
      <section className='py-5'>
            <div className="container px-4 px-lg-5 ">
            <table className="table table-dark">
              <thead>
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">Data</th>
                  <th scope="col">Koszt</th>
                  <th scope="col">Leki</th>
                </tr>
              </thead>
              <tbody>
              {isLoading ? (
                    <tr><td>Brak danych</td></tr>
                ) : (
                    data.map((element, key)=>(
                      <tr>
                          <th scope='row'>{key+1}</th>
                          <td>{element.date}</td>
                          <td>{element.cost}</td>
                          <td>
                          {element.anxInOrderDTO.map((ogniwo, klucz) =>(
                            <>{ogniwo.anxieties.name}, {ogniwo.amount} sztuki 
                            <br />

                            </>
                            
                          ))}


                          </td>
                   
                      </tr>
                    ))
              )}
              </tbody>
          </table>
                        
          
            </div>
        </section>    
    </div>
  )
}

export default History
