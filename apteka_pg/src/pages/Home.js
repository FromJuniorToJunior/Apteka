import React,{useState} from 'react'
import 'bootstrap/dist/css/bootstrap.min.css';
import '../styles/styles.css';
import MedicineList from '../components/MedicineList';


function Home() {

  const [isLoading, setIsLoading] = React.useState(true);
  const [data, setData] = React.useState([]);
 

  React.useEffect(() => {
    const url = "http://localhost:8081/anxieties/anxieties";
    
    if(sessionStorage.getItem('token') != undefined){// patrzy po tokenie 
    fetch(url,{
      method: 'GET',
      headers:{
        'Authorization': sessionStorage.getItem('token')
      } 
    })
      .then((response) => response.json())
      .then((json) => setData(json))
      .catch((error) => console.log(error));

    }else{
      fetch(url)
        .then((response) => response.json())
        .then((json) => setData(json))
        .catch((error) => console.log(error));
    }
  }, []);

  React.useEffect(() => {
    if (data.length !== 0) {
      setIsLoading(false);
    }
    console.log(data);
  }, [data]);

  
  return (
    <div className='home'>
        <section className='py-5'>
            <div className="container px-4 px-lg-5 mt-5">
                <div className="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
               
                  {isLoading ? (
                    "Loading..."
                  ) : (
                    data.map((user, key) => (
                      <MedicineList key={key}
                       image={user.img} 
                       name={user.name} 
                       price={user.price} 
                       description={user.description}/>
                    ))
                  )}

                </div>
                
            </div>
        </section>    
      
    </div>
  )
}

export default Home
